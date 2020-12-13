package net.tslat.aoa3.common.container.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoARecipes;

import javax.annotation.Nullable;

public class UpgradeKitRecipe implements IRecipe<DivineStationContainer.DivineStationInventory> {
	private final ResourceLocation id;
	private final String group;

	private final ItemStack input;
	private final ItemStack upgradeKit;
	private final ItemStack output;

	public UpgradeKitRecipe(ResourceLocation id, String group, ItemStack input, ItemStack upgradeKit, ItemStack output) {
		this.id = id;
		this.group = group;
		this.input = input;
		this.upgradeKit = upgradeKit;
		this.output = output;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public boolean matches(DivineStationContainer.DivineStationInventory inv, World world) {
		return ItemStack.areItemsEqual(input, inv.getStackInSlot(0)) && ItemStack.areItemsEqual(upgradeKit, inv.getStackInSlot(1));
	}

	@Override
	public ItemStack getCraftingResult(DivineStationContainer.DivineStationInventory inv) {
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
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<UpgradeKitRecipe> getSerializer() {
		return AoARecipes.UPGRADE_KIT.getB().get();
	}

	@Override
	public IRecipeType<UpgradeKitRecipe> getType() {
		return AoARecipes.UPGRADE_KIT.getA();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(DivineStationContainer.DivineStationInventory inv) {
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

		ingredients.add(Ingredient.fromStacks(input));
		ingredients.add(Ingredient.fromStacks(upgradeKit));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public static class Factory extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<UpgradeKitRecipe> {
		@Override
		public UpgradeKitRecipe read(ResourceLocation recipeId, JsonObject json) {
			String group = JSONUtils.getString(json, "group", "");
			ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "input"), false);
			ItemStack upgradeKit = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "upgrade_kit"), false);
			ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), true);

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Nullable
		@Override
		public UpgradeKitRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String group = buffer.readString(32767);
			ItemStack inputItem = buffer.readItemStack();
			ItemStack upgradeKit = buffer.readItemStack();
			ItemStack output = buffer.readItemStack();

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Override
		public void write(PacketBuffer buffer, UpgradeKitRecipe recipe) {
			buffer.writeString(recipe.getGroup(), 32767);
			buffer.writeItemStack(recipe.input);
			buffer.writeItemStack(recipe.upgradeKit);
			buffer.writeItemStack(recipe.output);
		}
	}
}
