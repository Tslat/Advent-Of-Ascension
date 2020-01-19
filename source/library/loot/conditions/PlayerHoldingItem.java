package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

public class PlayerHoldingItem implements LootCondition {
	private final Item tool;
	private final EnumHand hand;

	public PlayerHoldingItem(Item heldTool, @Nullable EnumHand hand) {
		this.tool = heldTool;
		this.hand = hand;
	}

	@Override
	public boolean testCondition(Random rand, LootContext context) {
		if (hand != null) {
			return context.getKillerPlayer() instanceof EntityPlayer && ((EntityPlayer)context.getKillerPlayer()).getHeldItem(hand).getItem() == tool;
		}
		else {
			if (context.getKillerPlayer() instanceof EntityPlayer) {
				for (ItemStack stack : context.getKillerPlayer().getHeldEquipment()) {
					if (stack.getItem() == tool)
						return true;
				}
			}
		}

		return false;
	}

	public static class Serializer extends LootCondition.Serializer<PlayerHoldingItem> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "player_holding_item"), PlayerHoldingItem.class);
		}

		@Override
		public void serialize(JsonObject json, PlayerHoldingItem value, JsonSerializationContext context) {
			json.addProperty("id", value.tool.getRegistryName() == null ? "": value.tool.getRegistryName().toString());
			json.addProperty("hand", value.hand.toString());
		}

		@Override
		public PlayerHoldingItem deserialize(JsonObject json, JsonDeserializationContext context) {
			return new PlayerHoldingItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(JsonUtils.getString(json, "id"))), JsonUtils.hasField(json, "hand") ? EnumHand.valueOf(JsonUtils.getString(json, "hand").toUpperCase()) : null);
		}
	}
}
