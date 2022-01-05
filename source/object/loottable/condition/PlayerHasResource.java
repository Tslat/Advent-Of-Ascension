package net.tslat.aoa3.object.loottable.condition;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.Set;

public class PlayerHasResource implements ILootCondition {
	private final AoAResource resource;
	private final float amount;
	private final boolean consume;

	public PlayerHasResource(AoAResource resource, float amount, boolean consume) {
		this.resource = resource;
		this.amount = amount;
		this.consume = consume;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.PLAYER_HAS_RESOURCE;
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
			return consume ? PlayerUtil.consumeResource((ServerPlayerEntity)entity, resource, amount, false) : PlayerUtil.getResourceValue((ServerPlayerEntity)entity, resource) >= amount;

		return false;
	}

	public AoAResource getResource() {
		return this.resource;
	}

	public float getAmount() {
		return this.amount;
	}

	public static class Serializer implements ILootSerializer<PlayerHasResource> {
		@Override
		public void serialize(JsonObject json, PlayerHasResource playerHasResource, JsonSerializationContext jsonSerializationContext) {
			json.addProperty("resource", playerHasResource.resource.getRegistryName().toString());
			json.addProperty("amount", playerHasResource.amount);
			json.addProperty("consume", playerHasResource.consume);
		}

		@Override
		public PlayerHasResource deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new PlayerHasResource(AoAResources.getResource(new ResourceLocation(JSONUtils.getAsString(json, "resource"))), JSONUtils.getAsFloat(json, "amount"), JSONUtils.isValidNode(json, "consume") && JSONUtils.getAsBoolean(json, "consume"));
		}
	}
}
