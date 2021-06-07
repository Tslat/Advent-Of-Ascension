package net.tslat.aoa3.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.Hand;
import net.minecraft.util.JSONUtils;
import net.tslat.aoa3.common.registration.AoALootOperations;

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
		Entity entity = lootContext.getParamOrNull(target.getParam());

		if (entity instanceof LivingEntity) {
			if (hand != null) {
				return predicate.matches(((LivingEntity)entity).getItemInHand(hand));
			}
			else {
				for (ItemStack stack : entity.getHandSlots()) {
					if (predicate.matches(stack))
						return true;
				}
			}
		}

		return false;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.HOLDING_ITEM;
	}

	public static class Serializer implements ILootSerializer<HoldingItem> {
		@Override
		public void serialize(JsonObject json, HoldingItem holdingItem, JsonSerializationContext jsonSerializationContext) {
			json.add("target", jsonSerializationContext.serialize(holdingItem.target));
			json.add("predicate", holdingItem.predicate.serializeToJson());

			if (holdingItem.hand != null)
				json.addProperty("hand", holdingItem.hand.toString().toLowerCase());
		}

		@Override
		public HoldingItem deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new HoldingItem(JSONUtils.getAsObject(json, "target", jsonDeserializationContext, LootContext.EntityTarget.class), ItemPredicate.fromJson(json.get("predicate")), json.has("hand") ? Hand.valueOf(json.get("hand").getAsString().toUpperCase()) : null);
		}
	}
}
