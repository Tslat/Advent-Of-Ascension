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
import net.tslat.aoa3.hooks.jei.recipewrapper.UpgradeRecipeWrapper;
import net.tslat.aoa3.item.misc.FrameItem;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class UpgradeRecipeCategory implements IRecipeCategory<UpgradeRecipeWrapper> {
	private static ResourceLocation textureResource;
	private final IDrawable background;
	private final IDrawable icon;

	public UpgradeRecipeCategory(IGuiHelper guiHelper) {
		textureResource = new ResourceLocation("aoa3", "textures/gui/containers/basic_block_gui.png");
		background = guiHelper.createDrawable(textureResource, 26, 22, 125, 18);
		icon = guiHelper.createDrawableIngredient(new ItemStack(BlockRegister.DIVINE_STATION));
	}

	@Override
	public String getUid() {
		return "aoa3.upgradeKits";
	}

	@Override
	public String getTitle() {
		return BlockRegister.DIVINE_STATION.getLocalizedName();
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
	public void setRecipe(IRecipeLayout recipeLayout, UpgradeRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, true, 0, 0);
		guiStacks.init(1, true, 49, 0);
		guiStacks.init(2, false, 107, 0);
		guiStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
		guiStacks.set(1, ingredients.getInputs(VanillaTypes.ITEM).get(1));
		guiStacks.set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
	}
}
