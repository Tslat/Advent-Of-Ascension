package net.tslat.aoa3.content.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.WorldUtil;

public class AshfernCookingRecipe extends CustomRecipe {
	public AshfernCookingRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public String getGroup() {
		return "food";
	}

	@Override
	public boolean matches(CraftingContainer container, Level level) {
		boolean hasFern = false;
		boolean hasFood = false;

		for (int i = 0; i < container.getContainerSize(); i++) {
			final ItemStack stack = container.getItem(i);

			if (stack.isEmpty())
				continue;

			if (stack.is(AoAItems.ASHFERN.get())) {
				if (hasFern)
					return false;

				hasFern = true;

				continue;
			}

			if (hasFood || !stack.isEdible())
				return false;

			if (level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level).map(recipe -> !recipe.getResultItem(level.registryAccess()).isEmpty()).orElse(false)) {
				hasFood = true;
			}
			else {
				return false;
			}
		}

		return hasFern && hasFood;
	}

	@Override
	public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
		final Level level = WorldUtil.getServer().overworld();

		if (level == null)
			return ItemStack.EMPTY;

		boolean foundFern = false;
		ItemStack output = ItemStack.EMPTY;

		for (int i = 0; i < container.getContainerSize(); i++) {
			final ItemStack stack = container.getItem(i);

			if (stack.isEmpty())
				continue;

			if (stack.is(AoAItems.ASHFERN.get())) {
				foundFern = true;

				if (!output.isEmpty())
					break;

				continue;
			}

			output = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level).map(recipe -> recipe.getResultItem(registryAccess)).orElse(ItemStack.EMPTY);

			if (foundFern)
				break;
		}

		if (!foundFern)
			return ItemStack.EMPTY;

		output = output.copy();

		return output;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 2 || height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return AoARecipes.ASHFERN_COOKING.serializer().get();
	}

	@Override
	public CraftingBookCategory category() {
		return CraftingBookCategory.MISC;
	}
}
