package net.tslat.aoa3.hooks.jei.recipewrapper;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.crafting.recipes.UpgradeKitRecipe;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class UpgradeRecipeWrapper implements IRecipeWrapper {
	private final UpgradeKitRecipe recipe;

	protected UpgradeRecipeWrapper(UpgradeKitRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> ingredientsCollection = new ArrayList<List<ItemStack>>();
		ItemStack output = recipe.getRecipeOutput();

		for (Ingredient ing : recipe.getIngredients()) {
			ingredientsCollection.add(Lists.newArrayList(ing.getMatchingStacks()));
		}

		ingredients.setInputLists(VanillaTypes.ITEM, ingredientsCollection);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

	public static class Factory implements IRecipeWrapperFactory<UpgradeKitRecipe> {
		@Override
		public IRecipeWrapper getRecipeWrapper(UpgradeKitRecipe recipe) {
			return new UpgradeRecipeWrapper(recipe);
		}
	}
}
