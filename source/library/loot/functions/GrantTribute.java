package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Random;

public class GrantTribute extends LootFunction {
	private final Enums.Deities deity;
	private final int amount;

	public GrantTribute(LootCondition[] conditionsIn, Enums.Deities deity, int amount) {
		super(conditionsIn);

		this.deity = deity;
		this.amount	= amount;
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		if (context.getKillerPlayer() instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)context.getKillerPlayer()).stats().addTribute(deity, amount);

		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<GrantTribute> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "give_tribute"), GrantTribute.class);
		}

		@Override
		public void serialize(JsonObject json, GrantTribute functionInstance, JsonSerializationContext serializationContext) {
			json.addProperty("deity", functionInstance.deity.toString().toLowerCase());
			json.addProperty("amount", functionInstance.amount);
		}

		@Override
		public GrantTribute deserialize(JsonObject json, JsonDeserializationContext deserializationContext, LootCondition[] lootConditions) {
			return new GrantTribute(lootConditions, Enums.Deities.valueOf(JsonUtils.getString(json, "deity").toUpperCase()), JsonUtils.getInt(json, "amount"));
		}
	}
}
