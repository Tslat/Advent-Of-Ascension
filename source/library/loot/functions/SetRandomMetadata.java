package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

import java.util.Random;

public class SetRandomMetadata extends LootFunction {
	public SetRandomMetadata(LootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
		Item item = stack.getItem();

		if (item.getHasSubtypes()) {
			NonNullList<ItemStack> subItems = NonNullList.<ItemStack>create();

			item.getSubItems(CreativeTabs.SEARCH, subItems);
			stack.setItemDamage(subItems.get(rand.nextInt(subItems.size())).getMetadata());

			return stack;
		}

		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<SetRandomMetadata> {
		public Serializer() {
			super(new ResourceLocation("aoa3", "set_random_metadata"), SetRandomMetadata.class);
		}

		@Override
		public void serialize(JsonObject json, SetRandomMetadata functionInstance, JsonSerializationContext serializationContext) {}

		@Override
		public SetRandomMetadata deserialize(JsonObject json, JsonDeserializationContext deserializationContext, LootCondition[] lootConditions) {
			return new SetRandomMetadata(lootConditions);
		}
	}
}
