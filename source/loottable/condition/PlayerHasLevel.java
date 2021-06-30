package net.tslat.aoa3.loottable.condition;

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
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

public class PlayerHasLevel implements ILootCondition {
	private final Skills skill;
	private final int level;

	public PlayerHasLevel(Skills skill, int level) {
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

	public Skills getSkill() {
		return this.skill;
	}

	public int getLevel() {
		return this.level;
	}

	public static class Serializer implements ILootSerializer<PlayerHasLevel> {
		@Override
		public void serialize(JsonObject json, PlayerHasLevel playerHasLevel, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("skill", playerHasLevel.skill.toString().toLowerCase());
			json.addProperty("level", playerHasLevel.level);
		}

		@Override
		public PlayerHasLevel deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasLevel(Skills.valueOf(JSONUtils.getAsString(json, "skill").toUpperCase()), JSONUtils.getAsInt(json, "level"));
		}
	}
}
