package net.tslat.aoa3.integration.jei.recipe.whitewashing;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.container.recipe.WhitewashingRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.ArrayList;

public class WhitewashingRecipeCategory implements IRecipeCategory<WhitewashingRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "whitewashing");
	private final String title = LocaleUtil.getLocaleString("recipe.aoa3.whitewashing");
	private final IDrawable background;
	private final IDrawable icon;

	public WhitewashingRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/basic_block.png");
		this.background = guiHelper.createDrawable(texture, 26, 22, 125, 18);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<WhitewashingRecipe> getRecipeClass() {
		return WhitewashingRecipe.class;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(WhitewashingRecipe recipe, IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>(2);
		Item output = recipe.getRecipeOutput().getItem();

		inputs.add(new ItemStack(Blocks.OBSIDIAN));

		if (output == AoABlocks.WHITEWASH_BRICKS.get().asItem()) {
			inputs.add(new ItemStack(AoAItems.WHITEWASHING_SOLUTION.get()));
		}
		else if (output == AoABlocks.DARKWASH_BRICKS.get().asItem()) {
			inputs.add(new ItemStack(AoAItems.DARKLY_POWDER.get()));
		}

		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(output, 2));
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, WhitewashingRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, true, 0, 0);
		guiStacks.init(1, true, 49, 0);
		guiStacks.init(2, false, 107, 0);
		guiStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(1));
		guiStacks.set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
