package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.tslat.aoa3.advent.AdventOfAscension;
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
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.get(LootParameters.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.get(LootParameters.THIS_ENTITY);

		return entity instanceof ServerPlayerEntity && PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)entity, skill, level);
	}

	public static class Serializer extends AbstractSerializer<PlayerHasLevel> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "player_has_level"), PlayerHasLevel.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasLevel playerHasLevel, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("skill", playerHasLevel.skill.toString().toLowerCase());
			json.addProperty("level", playerHasLevel.level);
		}

		@Override
		public PlayerHasLevel deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasLevel(Skills.valueOf(JSONUtils.getString(json, "skill").toUpperCase()), JSONUtils.getInt(json, "level"));
		}
	}
}
