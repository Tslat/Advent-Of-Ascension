package net.tslat.aoa3.integration.jei.recipe.framebench;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.recipe.FrameBenchRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

public class FrameBenchRecipeCategory implements IRecipeCategory<FrameBenchRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench");
	public static final ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/frame_bench.png");
	private final String title = LocaleUtil.getLocaleString("recipe.aoa3.frameBench");
	private final IDrawable background;
	private final IDrawable icon;

	public FrameBenchRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(texture, 10, 12, 156, 60);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(AoABlocks.FRAME_BENCH.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<FrameBenchRecipe> getRecipeClass() {
		return FrameBenchRecipe.class;
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
	public void draw(FrameBenchRecipe recipe, MatrixStack matrix, double mouseX, double mouseY) {
		Minecraft mc = Minecraft.getInstance();

		drawButton(matrix, mc, AoAItems.CROSSBOW_FRAME.get(), recipe, 45, 1);
		drawButton(matrix, mc, AoAItems.BLASTER_FRAME.get(), recipe, 65, 1);
		drawButton(matrix, mc, AoAItems.CANNON_FRAME.get(), recipe, 85, 1);
		drawButton(matrix, mc, AoAItems.HELMET_FRAME.get(), recipe, 35, 21);
		drawButton(matrix, mc, AoAItems.CHESTPLATE_FRAME.get(), recipe, 55, 21);
		drawButton(matrix, mc, AoAItems.LEGGINGS_FRAME.get(), recipe, 75, 21);
		drawButton(matrix, mc, AoAItems.BOOTS_FRAME.get(), recipe, 95, 21);
		drawButton(matrix, mc, AoAItems.GUN_FRAME.get(), recipe, 45, 41);
		drawButton(matrix, mc, AoAItems.SHOTGUN_FRAME.get(), recipe, 65, 41);
		drawButton(matrix, mc, AoAItems.SNIPER_FRAME.get(), recipe, 85, 41);
	}

	private void drawButton(MatrixStack matrix, Minecraft mc, Item frame, FrameBenchRecipe recipe, int x, int y) {
		mc.getTextureManager().bind(texture);
		matrix.pushPose();
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		RenderUtil.renderCustomSizedTexture(matrix, x, y, 176, recipe.getResultItem().getItem() == frame ? 21 : 39, 18, 18, 256, 256);
		matrix.translate(0, 0, 32);
		RenderUtil.renderItemInGui(matrix, mc, new ItemStack(frame), x + 1, y + 1);
		matrix.popPose();
		RenderSystem.color4f(1f, 1f, 1f, 1f);
	}

	@Override
	public void setIngredients(FrameBenchRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FrameBenchRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, false, 138, 21);
		guiStacks.init(1, true, 0, 21);
		guiStacks.set(0, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
	}
}
