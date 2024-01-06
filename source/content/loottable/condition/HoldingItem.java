package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoALootOperations;
import org.jetbrains.annotations.Nullable;


public class HoldingItem implements LootItemCondition {
	private final LootContext.EntityTarget target;
	private final ItemPredicate predicate;
	@Nullable
	private final InteractionHand hand;

	public HoldingItem(LootContext.EntityTarget target, ItemPredicate predicate, @Nullable InteractionHand hand) {
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
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.HOLDING_ITEM.get();
	}

	public LootContext.EntityTarget getTarget() {
		return this.target;
	}

	public ItemPredicate getPredicate() {
		return this.predicate;
	}

	@Nullable
	public InteractionHand getHand() {
		return this.hand;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<HoldingItem> {
		@Override
		public void serialize(JsonObject json, HoldingItem holdingItem, JsonSerializationContext jsonSerializationContext) {
			json.add("target", jsonSerializationContext.serialize(holdingItem.target));
			json.add("predicate", holdingItem.predicate.serializeToJson());

			if (holdingItem.hand != null)
				json.addProperty("hand", holdingItem.hand.toString().toLowerCase());
		}

		@Override
		public HoldingItem deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			return new HoldingItem(GsonHelper.getAsObject(json, "target", jsonDeserializationContext, LootContext.EntityTarget.class), ItemPredicate.fromJson(json.get("predicate")), json.has("hand") ? InteractionHand.valueOf(json.get("hand").getAsString().toUpperCase()) : null);
		}
	}
}
