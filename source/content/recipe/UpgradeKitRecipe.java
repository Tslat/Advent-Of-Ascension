package net.tslat.aoa3.content.recipe;

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
	public ItemStack getToastSymbol() {
		return new ItemStack(AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public boolean matches(DivineStationContainer.DivineStationInventory inv, World world) {
		return ItemStack.isSame(input, inv.getItem(0)) && ItemStack.isSame(upgradeKit, inv.getItem(1));
	}

	@Override
	public ItemStack assemble(DivineStationContainer.DivineStationInventory inv) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height <= 3;
	}

	@Override
	public ItemStack getResultItem() {
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
		NonNullList<ItemStack> remainingItems = NonNullList.<ItemStack>withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); i++) {
			ItemStack stack = inv.getItem(i);

			remainingItems.set(i, ForgeHooks.getContainerItem(stack));
		}

		return remainingItems;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> ingredients = NonNullList.<Ingredient>create();

		ingredients.add(Ingredient.of(input));
		ingredients.add(Ingredient.of(upgradeKit));

		return ingredients;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public static class Factory extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<UpgradeKitRecipe> {
		@Override
		public UpgradeKitRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String group = JSONUtils.getAsString(json, "group", "");
			ItemStack inputItem = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "input"), false);
			ItemStack upgradeKit = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "upgrade_kit"), false);
			ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Nullable
		@Override
		public UpgradeKitRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			String group = buffer.readUtf(32767);
			ItemStack inputItem = buffer.readItem();
			ItemStack upgradeKit = buffer.readItem();
			ItemStack output = buffer.readItem();

			return new UpgradeKitRecipe(recipeId, group, inputItem, upgradeKit, output);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, UpgradeKitRecipe recipe) {
			buffer.writeUtf(recipe.getGroup(), 32767);
			buffer.writeItem(recipe.input);
			buffer.writeItem(recipe.upgradeKit);
			buffer.writeItem(recipe.output);
		}
	}
}
