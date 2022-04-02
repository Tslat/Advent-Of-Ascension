package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class PlayerHasLevel implements LootItemCondition {
	private final AoASkill skill;
	private final int level;

	public PlayerHasLevel(AoASkill skill, int level) {
		this.skill = skill;
		this.level = level;
	}

	@Override
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.PLAYER_HAS_LEVEL;
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		return entity instanceof ServerPlayer && PlayerUtil.doesPlayerHaveLevel((ServerPlayer)entity, skill, level);
	}

	public AoASkill getSkill() {
		return this.skill;
	}

	public int getLevel() {
		return this.level;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<PlayerHasLevel> {
		@Override
		public void serialize(JsonObject json, PlayerHasLevel playerHasLevel, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("skill", playerHasLevel.skill.getRegistryName().toString());
			json.addProperty("level", playerHasLevel.level);
		}

		@Override
		public PlayerHasLevel deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasLevel(AoASkills.getSkill(new ResourceLocation(GsonHelper.getAsString(json, "skill"))), GsonHelper.getAsInt(json, "level"));
		}
	}
}
