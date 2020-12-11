package net.tslat.aoa3.library.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;

public class HoldingItem implements ILootCondition {
	private final LootContext.EntityTarget target;
	private final ItemPredicate predicate;
	@Nullable
	private final Hand hand;

	public HoldingItem(LootContext.EntityTarget target, ItemPredicate predicate, @Nullable Hand hand) {
		this.target = target;
		this.predicate = predicate;
		this.hand = hand;
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.get(target.getParameter());

		if (entity instanceof LivingEntity) {
			if (hand != null) {
				return predicate.test(((LivingEntity)entity).getHeldItem(hand));
			}
			else {
				for (ItemStack stack : entity.getHeldEquipment()) {
					if (predicate.test(stack))
						return true;
				}
			}
		}

		return false;
	}

	public static class Serializer extends AbstractSerializer<HoldingItem> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "holding_item"), HoldingItem.class);
		}

		@Override
		public void serialize(JsonObject json, HoldingItem holdingItem, JsonSerializationContext jsonSerializationContext) {
			json.add("target", jsonSerializationContext.serialize(holdingItem.target));
			json.add("predicate", holdingItem.predicate.serialize());

			if (holdingItem.hand != null)
				json.addProperty("hand", holdingItem.hand.toString().toLowerCase());
		}

		@Override
		public HoldingItem deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new HoldingItem(JSONUtils.deserializeClass(json, "target", jsonDeserializationContext, LootContext.EntityTarget.class), ItemPredicate.deserialize(json.get("predicate")), json.has("hand") ? Hand.valueOf(json.get("hand").getAsString().toUpperCase()) : null);
		}
	}
}
