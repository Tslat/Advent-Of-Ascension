package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.util.ObjectUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AddItemsLootModifier extends LootModifier {
	private final List<ItemStack> additionalStacks;

	public AddItemsLootModifier(LootItemCondition[] conditions, List<ItemStack> additionalStacks) {
		super(conditions);

		this.additionalStacks = additionalStacks;
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.addAll(additionalStacks);

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<AddItemsLootModifier> {
		@Override
		public AddItemsLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootConditions) {
			JsonArray items = GsonHelper.getAsJsonArray(object, "items");
			ArrayList<ItemStack> extraItems = new ArrayList<ItemStack>(items.size());

			for (JsonElement element : items) {
				if (element.isJsonObject()) {
					extraItems.add(CraftingHelper.getItemStack(element.getAsJsonObject(), true));
				}
				else if (element.isJsonPrimitive()) {
					extraItems.add(new ItemStack(Registry.ITEM.getOptional(new ResourceLocation(element.getAsString())).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + element.getAsString() + "'"))));
				}
			}

			return new AddItemsLootModifier(lootConditions, extraItems);
		}

		@Override
		public JsonObject write(AddItemsLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);
			JsonArray items = new JsonArray();

			for (ItemStack stack : instance.additionalStacks) {
				items.add(ObjectUtil.stackToJson(stack));
			}

			json.add("items", items);
			json.addProperty("type", getRegistryName().toString());

			return json;
		}
	}
}
