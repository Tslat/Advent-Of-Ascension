package net.tslat.aoa3.hooks.jei.recipewrapper;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.FrameItem;

import java.util.List;

@SideOnly(Side.CLIENT)
public class FrameRecipeWrapper implements IRecipeWrapper {
	private final FrameItem frame;

	public FrameRecipeWrapper(FrameItem frame) {
		this.frame = frame;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, new ItemStack(ItemRegister.SCRAP_METAL));
		ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(frame));
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

	public FrameItem getFrame() {
		return frame;
	}
}
