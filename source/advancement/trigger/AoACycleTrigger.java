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


public class AoACycleTrigger extends SimpleCriterionTrigger<AoACycleTrigger.Instance> {
	private static final ResourceLocation triggerId = new ResourceLocation("aoa3", "cycle");

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

		int trigger = json.has("cycle") ? Mth.clamp(GsonHelper.getAsInt(json, "cycle"), 1, 10) : 0;

		return new Instance(skill, trigger);
	}

	public void trigger(ServerPlayer player, AoASkill skill, int level) {
		trigger(player, instance -> instance.test(skill, level));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final int cycle;

		public Instance(@Nullable AoASkill skill, int cycle, ContextAwarePredicate playerPredicate) {
			super(triggerId, playerPredicate);

			this.skill = skill;
			this.cycle = Mth.clamp(cycle, 1, 10);
		}

		public Instance(@Nullable AoASkill skill, int cycle) {
			this(skill, cycle, ContextAwarePredicate.ANY);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			JsonObject obj = super.serializeToJson(conditions);

			if (skill != null)
				obj.addProperty("skill", AoARegistries.AOA_SKILLS.getId(skill).toString());

			if (cycle > 0)
				obj.addProperty("cycle", cycle);

			return obj;
		}

		public boolean test(AoASkill skill, int level) {
			return (this.skill == null || this.skill == skill) && (this.cycle == 0 || level >= this.cycle);
		}
	}
}
