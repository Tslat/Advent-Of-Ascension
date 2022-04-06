package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.util.LocaleUtil;

public class UpgradeKitRecipeCategory implements IRecipeCategory<UpgradeKitRecipe> {
	public static final RecipeType<UpgradeKitRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "upgrade_kit", UpgradeKitRecipe.class);
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "upgrade_kit");

	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.upgradeKit");
	private final IDrawable background;
	private final IDrawable icon;

	public UpgradeKitRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/basic_block.png");
		this.background = guiHelper.createDrawable(texture, 26, 22, 125, 18);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(AoABlocks.DIVINE_STATION.get()));
	}

	@Deprecated
	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Deprecated
	@Override
	public Class<UpgradeKitRecipe> getRecipeClass() {
		return UpgradeKitRecipe.class;
	}

	@Override
	public RecipeType<UpgradeKitRecipe> getRecipeType() {
		return RECIPE_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, UpgradeKitRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
				.addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
				.addItemStack(recipe.getResultItem());
	}
}
