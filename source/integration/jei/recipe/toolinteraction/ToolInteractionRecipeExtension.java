package net.tslat.aoa3.integration.jei.recipe.toolinteraction;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;
import net.tslat.aoa3.object.recipe.ToolInteractionRecipe;

import javax.annotation.Nullable;

public class ToolInteractionRecipeExtension implements ICraftingCategoryExtension {
	private final ToolInteractionRecipe recipe;

	public ToolInteractionRecipeExtension(ToolInteractionRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void setIngredients(IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}

	@Nullable
	@Override
	public Size2i getSize() {
		return null;
	}
}
