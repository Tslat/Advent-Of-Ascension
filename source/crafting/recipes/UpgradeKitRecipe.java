package net.tslat.aoa3.crafting.recipes;

import com.google.gson.JsonObject;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.tslat.aoa3.common.containers.ContainerDivineStation;

public class UpgradeKitRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
	private final String group;

	private final ItemStack input;
	private final ItemStack upgradeKit;
	private final ItemStack output;

	public UpgradeKitRecipe(String group, ItemStack input, ItemStack upgradeKit, ItemStack output) {
		this.group = group;
		this.input = input;
		this.upgradeKit = upgradeKit;
		this.output = output;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		if (inv instanceof ContainerDivineStation.InventoryDivineStation)
			return ItemStack.areItemsEqual(input, inv.getStackInSlot(0)) && ItemStack.areItemsEqual(upgradeKit, inv.getStackInSlot(1));

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return output.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height <= 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return output.copy();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> remainingItems = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); i++) {
			ItemStack stack = inv.getStackInSlot(i);

			remainingItems.set(i, ForgeHooks.getContainerItem(stack));
		}

		return remainingItems;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.<Ingredient>create();

		ingredients.add(CraftingHelper.getIngredient(input));
		ingredients.add(CraftingHelper.getIngredient(upgradeKit));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public static class Factory implements IRecipeFactory {
		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			String group = JsonUtils.getString(json, "group", "");
			ItemStack inputItem = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "input"), context);
			ItemStack upgradeKit = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "upgrade_kit"), context);
			ItemStack output = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new UpgradeKitRecipe(group, inputItem, upgradeKit, output);
		}
	}
}
