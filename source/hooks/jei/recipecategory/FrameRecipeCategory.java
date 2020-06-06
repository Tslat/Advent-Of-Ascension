package net.tslat.aoa3.hooks.jei.recipecategory;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.hooks.jei.recipewrapper.FrameRecipeWrapper;
import net.tslat.aoa3.item.misc.FrameItem;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FrameRecipeCategory implements IRecipeCategory<FrameRecipeWrapper> {
	private static ResourceLocation textureResource;
	private final IDrawable background;
	private final IDrawable icon;

	public FrameRecipeCategory(IGuiHelper guiHelper) {
		textureResource = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");
		background = guiHelper.createDrawable(textureResource, 10, 12, 156, 60);
		icon = guiHelper.createDrawableIngredient(new ItemStack(BlockRegister.FRAME_BENCH));
	}

	@Override
	public String getUid() {
		return "aoa3.frameBench";
	}

	@Override
	public String getTitle() {
		return BlockRegister.FRAME_BENCH.getLocalizedName();
	}

	@Override
	public String getModName() {
		return "Advent of Ascension";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Nullable
	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void drawExtras(Minecraft mc) {
		drawButton(mc, ItemRegister.ARCHERGUN_FRAME, 45, 1);
		drawButton(mc, ItemRegister.BLASTER_FRAME, 65, 1);
		drawButton(mc, ItemRegister.CANNON_FRAME, 85, 1);
		drawButton(mc, ItemRegister.HELMET_FRAME, 35, 21);
		drawButton(mc, ItemRegister.CHESTPLATE_FRAME, 55, 21);
		drawButton(mc, ItemRegister.LEGGINGS_FRAME, 75, 21);
		drawButton(mc, ItemRegister.BOOTS_FRAME, 95, 21);
		drawButton(mc, ItemRegister.GUN_FRAME, 45, 41);
		drawButton(mc, ItemRegister.SHOTGUN_FRAME, 65, 41);
		drawButton(mc, ItemRegister.SNIPER_FRAME, 85, 41);
	}

	private void drawButton(Minecraft mc, FrameItem frame, int x, int y) {
		mc.getTextureManager().bindTexture(textureResource);
		GlStateManager.pushMatrix();
		GlStateManager.color(1f, 1f, 1f, 1f);
		GuiUtils.drawTexturedModalRect(x, y, 176, 21, 18, 18, 0);
		GlStateManager.translate(0, 0, 32);
		mc.getRenderItem().renderItemIntoGUI(new ItemStack(frame), x + 1, y + 1);
		GlStateManager.popMatrix();
		GlStateManager.color(1f, 1f, 1f, 1f);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FrameRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, false, 138, 21);
		guiStacks.init(1, true, 0, 21);
		guiStacks.set(0, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
	}
}
