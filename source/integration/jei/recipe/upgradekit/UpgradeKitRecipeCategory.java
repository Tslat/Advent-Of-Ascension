package net.tslat.aoa3.integration.jei.recipe.upgradekit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.util.LocaleUtil;

public class UpgradeKitRecipeCategory implements IRecipeCategory<UpgradeKitRecipe> {
	public static final RecipeType<UpgradeKitRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "upgrade_kit", UpgradeKitRecipe.class);
	public static final ResourceLocation ID = AdventOfAscension.id("upgrade_kit");

	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.upgradeKit");
	private final IDrawable background;
	private final IDrawable icon;

	public UpgradeKitRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = AdventOfAscension.id("textures/gui/containers/basic_block.png");
		this.background = guiHelper.createDrawable(texture, 26, 22, getWidth(), getHeight());
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.DIVINE_STATION.get()));
	}

	@Override
	public RecipeType<UpgradeKitRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
		return this.title;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return 125;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, UpgradeKitRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
				.addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
				.addItemStack(RecipeUtil.getResultItem(recipe));
	}

	@Override
	public void draw(UpgradeKitRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}
}
