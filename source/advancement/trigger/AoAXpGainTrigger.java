package net.tslat.aoa3.advancement.trigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.registries.RegistryManager;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;

public class AoAXpGainTrigger extends SimpleCriterionTrigger<AoAXpGainTrigger.Instance> {
	private static final ResourceLocation triggerId = new ResourceLocation("aoa3", "gain_xp");

	@Override
	public ResourceLocation getId() {
		return triggerId;
	}

	@Override
	public Instance createInstance(JsonObject json, EntityPredicate.Composite predicate, DeserializationContext conditions) {
		AoASkill skill = null;

		if (json.has("skill")) {
			ResourceLocation skillId = new ResourceLocation(GsonHelper.getAsString(json, "skill"));
			skill = RegistryManager.ACTIVE.getRegistry(AoASkill.class).getValue(skillId);

			if (skill == null)
				throw new IllegalArgumentException("Invalid AoASkill ID: '" + skillId + "'");
		}

		float xp = json.has("xp") ? GsonHelper.getAsFloat(json, "xp") : 0f;

		return new Instance(skill, xp);
	}

	public void trigger(ServerPlayer player, AoASkill skill, float xp) {
		trigger(player, instance -> instance.test(skill, xp));
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final AoASkill skill;
		private final float xp;

		public Instance(@Nullable AoASkill skill, float xp, EntityPredicate.Composite playerPredicate) {
			super(triggerId, playerPredicate);

			this.skill = skill;
			this.xp = xp;
		}

		public Instance(@Nullable AoASkill skill, float xp) {
			this(skill, xp, EntityPredicate.Composite.ANY);
		}

		public boolean test(AoASkill skill, float xp) {
			return (this.skill == null || this.skill == skill) && (this.xp == 0 || this.xp <= xp);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			JsonObject obj = super.serializeToJson(conditions);

			if (skill != null)
				obj.addProperty("skill", skill.getRegistryName().toString());

			if (xp > 0)
				obj.addProperty("xp", xp);

			return obj;
		}
	}
}
