package net.tslat.aoa3.integration.jei.recipe.toolinteraction;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tslat.aoa3.content.recipe.ToolInteractionRecipe;

import java.util.List;


public class ToolInteractionRecipeExtension implements ICraftingCategoryExtension<ToolInteractionRecipe> {
	@Override
	public void setRecipe(RecipeHolder<ToolInteractionRecipe> recipeHolder, IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		final CraftingRecipe recipe = recipeHolder.value();
		final List<List<ItemStack>> inputs = recipe.getIngredients().stream()
				.map(ingredient -> List.of(ingredient.getItems()))
				.toList();

		craftingGridHelper.createAndSetOutputs(builder, List.of(RecipeUtil.getResultItem(recipe)));
		craftingGridHelper.createAndSetInputs(builder, inputs, getWidth(recipeHolder), getHeight(recipeHolder));
	}

	@Override
	public int getWidth(RecipeHolder<ToolInteractionRecipe> recipeHolder) {
		return 0;
	}

	@Override
	public int getHeight(RecipeHolder<ToolInteractionRecipe> recipeHolder) {
		return 0;
	}
}
