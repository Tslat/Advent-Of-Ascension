package net.tslat.aoa3.advancement.trigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;


public class AoALevelUpTrigger extends SimpleCriterionTrigger<AoALevelUpTrigger.Instance> {
	private static final ResourceLocation triggerId = new ResourceLocation("aoa3", "level_up");

	@Override
	public ResourceLocation getId() {
		return triggerId;
	}

	@Override
	public Instance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext conditions) {
		AoASkill skill = null;

		if (json.has("skill")) {
			ResourceLocation skillId = new ResourceLocation(GsonHelper.getAsString(json, "skill"));
			skill = AoASkills.getSkill(skillId);

			if (skill == null)
				throw new IllegalArgumentException("Invalid AoASkill ID: '" + skillId + "'");
		}

		int lvl = json.has("level") ? Mth.clamp(GsonHelper.getAsInt(json, "level"), 1, 1000) : 0;

		return new Instance(skill, lvl);
	}

	public void trigger(ServerPlayer player, AoASkill skill, int newLevel) {
		trigger(player, instance -> instance.test(skill, newLevel));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final int level;

		public Instance(@Nullable AoASkill skill, int lvl, ContextAwarePredicate playerPredicate) {
			super(triggerId, playerPredicate);

			this.skill = skill;
			this.level = Mth.clamp(lvl, 1, 1000);
		}

		public Instance(@Nullable AoASkill skill, int lvl) {
			this(skill, lvl, ContextAwarePredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			JsonObject obj = super.serializeToJson(conditions);

			if (skill != null)
				obj.addProperty("skill", AoARegistries.AOA_SKILLS.getId(skill).toString());

			if (level > 0)
				obj.addProperty("level", level);

			return obj;
		}

		public boolean test(AoASkill skill, int level) {
			return (this.skill == null || this.skill == skill) && (this.level == 0 || level >= this.level);
		}
	}
}
