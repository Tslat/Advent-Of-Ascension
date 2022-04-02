package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.util.LocaleUtil;

public class UpgradeKitRecipeCategory implements IRecipeCategory<UpgradeKitRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "upgrade_kit");
	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.upgradeKit");
	private final IDrawable background;
	private final IDrawable icon;

	public UpgradeKitRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/basic_block.png");
		this.background = guiHelper.createDrawable(texture, 26, 22, 125, 18);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(AoABlocks.DIVINE_STATION.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<UpgradeKitRecipe> getRecipeClass() {
		return UpgradeKitRecipe.class;
	}

	@Override
	public Component getTitle() {
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
	public void setIngredients(UpgradeKitRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, UpgradeKitRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, true, 0, 0);
		guiStacks.init(1, true, 49, 0);
		guiStacks.init(2, false, 107, 0);
		guiStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(1));
		guiStacks.set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
