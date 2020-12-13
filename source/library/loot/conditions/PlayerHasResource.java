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
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;

import java.util.Set;

public class PlayerHasResource implements ILootCondition {
	private final Resources resource;
	private final float amount;
	private boolean consume;

	public PlayerHasResource(Resources resource, float amount, boolean consume) {
		this.resource = resource;
		this.amount = amount;
		this.consume = consume;
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
			return consume ? PlayerUtil.consumeResource((ServerPlayerEntity)entity, resource, amount, false) : PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity).stats().getResourceValue(resource) >= amount;

		return false;
	}

	public static class Serializer extends AbstractSerializer<PlayerHasResource> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "player_has_resource"), PlayerHasResource.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasResource playerHasResource, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("resource", playerHasResource.resource.toString().toLowerCase());
			json.addProperty("amount", playerHasResource.amount);
			json.addProperty("consume", playerHasResource.consume);
		}

		@Override
		public PlayerHasResource deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasResource(Resources.valueOf(JSONUtils.getString(json, "resource").toUpperCase()), JSONUtils.getFloat(json, "amount"), JSONUtils.hasField(json, "consume") && JSONUtils.getBoolean(json, "consume"));
		}
	}
}
