package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class PlayerHasLevel implements ILootCondition {
	private final AoASkill skill;
	private final int level;

	public PlayerHasLevel(AoASkill skill, int level) {
		this.skill = skill;
		this.level = level;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.PLAYER_HAS_LEVEL;
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootParameters.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootParameters.THIS_ENTITY);

		return entity instanceof ServerPlayerEntity && PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)entity, skill, level);
	}

	public AoASkill getSkill() {
		return this.skill;
	}

	public int getLevel() {
		return this.level;
	}

	public static class Serializer implements ILootSerializer<PlayerHasLevel> {
		@Override
		public void serialize(JsonObject json, PlayerHasLevel playerHasLevel, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("skill", playerHasLevel.skill.getRegistryName().toString());
			json.addProperty("level", playerHasLevel.level);
		}

		@Override
		public PlayerHasLevel deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasLevel(AoASkills.getSkill(new ResourceLocation(JSONUtils.getAsString(json, "skill"))), JSONUtils.getAsInt(json, "level"));
		}
	}
}
