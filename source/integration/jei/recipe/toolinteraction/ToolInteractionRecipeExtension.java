/*
package net.tslat.aoa3.integration.jei.recipe.toolinteraction;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.tslat.aoa3.content.recipe.ToolInteractionRecipe;

import javax.annotation.Nullable;

public class ToolInteractionRecipeExtension implements ICraftingCategoryExtension {
	private final ToolInteractionRecipe recipe;

	public ToolInteractionRecipeExtension(ToolInteractionRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = recipe.getIngredients();

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				IRecipeSlotBuilder slotBuilder = builder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, y * 18 + 1);

				if (x + y * 3 < ingredients.size())
					slotBuilder.addIngredients(ingredients.get(x + y * 3));
			}
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 19)
				.addItemStack(recipe.getResultItem());

		builder.setShapeless();
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}
}
*/
