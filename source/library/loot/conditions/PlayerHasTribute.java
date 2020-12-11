package net.tslat.aoa3.library.loot.conditions;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameter;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.tslat.aoa3.advent.AdventOfAscension;
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
	public Set<LootParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(LootParameters.KILLER_ENTITY);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.get(LootParameters.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.get(LootParameters.THIS_ENTITY);

		if (entity instanceof ServerPlayerEntity)
			return PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).stats().getTribute(deity) >= amount;

		return false;
	}

	public static class Serializer extends AbstractSerializer<PlayerHasTribute> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "player_has_tribute"), PlayerHasTribute.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasTribute playerHasTribute, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("deity", playerHasTribute.deity.toString().toLowerCase());
			json.addProperty("amount", playerHasTribute.amount);
		}

		@Override
		public PlayerHasTribute deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasTribute(Deities.valueOf(JSONUtils.getString(json, "deity").toUpperCase()), JSONUtils.getInt(json, "amount"));
		}
	}
}
