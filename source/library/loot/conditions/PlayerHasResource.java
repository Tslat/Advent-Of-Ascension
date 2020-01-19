package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Random;

public class PlayerHasResource implements LootCondition {
	private final Enums.Resources resource;
	private final float amount;

	public PlayerHasResource(Enums.Resources resource, float amount) {
		this.resource = resource;
		this.amount = amount;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		return context.getKillerPlayer() != null && PlayerUtil.getAdventPlayer((EntityPlayer)context.getKillerPlayer()).stats().getResourceValue(resource) >= amount;
	}

	public static class Serializer extends LootCondition.Serializer<PlayerHasResource> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "player_has_resource"), PlayerHasResource.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasResource value, JsonSerializationContext context) {
			json.addProperty("resource", value.resource.toString().toLowerCase());
			json.addProperty("amount", value.amount);
		}

		@Override
		public PlayerHasResource deserialize(JsonObject json, JsonDeserializationContext context) {
			return new PlayerHasResource(Enums.Resources.valueOf(JsonUtils.getString(json, "resource").toUpperCase()), JsonUtils.getFloat(json, "amount"));
		}
	}
}
