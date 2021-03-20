package net.tslat.aoa3.library.loot.conditions;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerUtil;

import java.util.Set;

public class PlayerHasTribute implements ILootCondition {
	private final Deities deity;
	private final int amount;

	public PlayerHasTribute(Deities deity, int amount) {
		this.deity = deity;
		this.amount = amount;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.PLAYER_HAS_TRIBUTE;
	}

	@Override
	public Set<LootParameter<?>> getReferencedContextParams() {
		return ImmutableSet.of(LootParameters.KILLER_ENTITY);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootParameters.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootParameters.THIS_ENTITY);

		if (entity instanceof ServerPlayerEntity)
			return PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).stats().getTribute(deity) >= amount;

		return false;
	}

	public static class Serializer implements ILootSerializer<PlayerHasTribute> {
		@Override
		public void serialize(JsonObject json, PlayerHasTribute playerHasTribute, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("deity", playerHasTribute.deity.toString().toLowerCase());
			json.addProperty("amount", playerHasTribute.amount);
		}

		@Override
		public PlayerHasTribute deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasTribute(Deities.valueOf(JSONUtils.getAsString(json, "deity").toUpperCase()), JSONUtils.getAsInt(json, "amount"));
		}
	}
}
