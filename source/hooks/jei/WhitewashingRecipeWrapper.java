package net.tslat.aoa3.hooks.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class WhitewashingRecipeWrapper implements IRecipeWrapper {
	private final Block output;

	protected WhitewashingRecipeWrapper(Block output) {
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>(2);

		inputs.add(new ItemStack(Blocks.OBSIDIAN));

		if (output == BlockRegister.bricksWhitewash) {
			inputs.add(new ItemStack(ItemRegister.whitewashingSolution));
		}
		else if (output == BlockRegister.bricksDarkwash) {
			inputs.add(new ItemStack(ItemRegister.darklyPowder));
		}
		ingredients.setInputs(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(output, 2));
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
}
