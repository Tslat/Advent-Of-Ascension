package net.tslat.aoa3.integration.jei.recipe.whitewashing;

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
import net.tslat.aoa3.content.recipe.WhitewashingRecipe;
import net.tslat.aoa3.util.LocaleUtil;

public class WhitewashingRecipeCategory implements IRecipeCategory<WhitewashingRecipe> {
	public static final RecipeType<WhitewashingRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "whitewashing", WhitewashingRecipe.class);
	public static final ResourceLocation ID = AdventOfAscension.id("whitewashing");

	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.whitewashing");
	private final IDrawable background;
	private final IDrawable icon;

	public WhitewashingRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = AdventOfAscension.id("textures/gui/containers/basic_block.png");
		this.background = guiHelper.createDrawable(texture, 26, 22, getWidth(), getHeight());
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.WHITEWASHING_TABLE.get()));
	}

	@Override
	public RecipeType<WhitewashingRecipe> getRecipeType() {
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
	public void setRecipe(IRecipeLayoutBuilder builder, WhitewashingRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
				.addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
				.addItemStack(RecipeUtil.getResultItem(recipe));
	}

	@Override
	public void draw(WhitewashingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}
}
