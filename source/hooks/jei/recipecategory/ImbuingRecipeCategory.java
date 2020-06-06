package net.tslat.aoa3.hooks.jei.recipecategory;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.hooks.jei.recipewrapper.InfusionRecipeWrapper;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ImbuingRecipeCategory implements IRecipeCategory<InfusionRecipeWrapper> {
	private final IDrawable background;
	private final IDrawable icon;
	private final ICraftingGridHelper craftingGridHelper;

	public ImbuingRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(new ResourceLocation("aoa3", "textures/gui/containers/infusion_table.png"), 10, 10, 156, 66);
		icon = guiHelper.createDrawableIngredient(new ItemStack(BlockRegister.INFUSION_TABLE));
		craftingGridHelper = guiHelper.createCraftingGridHelper(1, 0);
	}

	@Override
	public String getUid() {
		return "aoa3.imbuing";
	}

	@Override
	public String getTitle() {
		return StringUtil.getLocaleString("container.aoa3.imbuing");
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
	public void drawExtras(Minecraft minecraft) {}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, InfusionRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

		guiStacks.init(0, false, 127, 24);
		guiStacks.init(1, true, 6, 24);

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				guiStacks.init(2 + x + y * 3, true, 34 + x * 18, 6 + y * 18);
			}
		}

		List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
		List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
		ResourceLocation recipeRegistryName = recipeWrapper.getRegistryName();

		guiStacks.set(0, outputs.get(0));
		craftingGridHelper.setInputs(guiStacks, inputs, 3, 3);
		recipeLayout.setShapeless();

		if (recipeRegistryName != null) {
			guiStacks.addTooltipCallback(((slotIndex, input, ingredient, tooltip) -> {
				if (slotIndex == 0) {
					if (!recipeRegistryName.getNamespace().equals("aoa3"))
						tooltip.add(TextFormatting.GRAY + StringUtil.getLocaleStringWithArguments("jei.tooltip.recipe.by", recipeRegistryName.getNamespace()));

					if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips || GuiScreen.isShiftKeyDown())
						tooltip.add(TextFormatting.DARK_GRAY + StringUtil.getLocaleStringWithArguments("jei.tooltip.recipe.id", recipeRegistryName.toString()));
				}
			}));
		}
	}
}
