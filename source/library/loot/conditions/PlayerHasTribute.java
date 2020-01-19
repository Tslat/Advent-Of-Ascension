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

public class PlayerHasTribute implements LootCondition {
	private final Enums.Deities deity;
	private final int amount;

	public PlayerHasTribute(Enums.Deities deity, int amount) {
		this.deity = deity;
		this.amount = amount;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		return context.getKillerPlayer() != null && PlayerUtil.getAdventPlayer((EntityPlayer)context.getKillerPlayer()).stats().getTribute(deity) >= amount;
	}

	public static class Serializer extends LootCondition.Serializer<PlayerHasTribute> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "player_has_tribute"), PlayerHasTribute.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHasTribute value, JsonSerializationContext context) {
			json.addProperty("deity", value.deity.toString().toLowerCase());
			json.addProperty("amount", value.amount);
		}

		@Override
		public PlayerHasTribute deserialize(JsonObject json, JsonDeserializationContext context) {
			return new PlayerHasTribute(Enums.Deities.valueOf(JsonUtils.getString(json, "deity").toUpperCase()), JsonUtils.getInt(json, "amount"));
		}
	}
}
