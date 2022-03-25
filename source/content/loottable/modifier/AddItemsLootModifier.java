package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.util.ObjectUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AddItemsLootModifier extends LootModifier {
	private final List<ItemStack> additionalStacks;

	public AddItemsLootModifier(ILootCondition[] conditions, List<ItemStack> additionalStacks) {
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
		public AddItemsLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			JsonArray items = JSONUtils.getAsJsonArray(object, "items");
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
