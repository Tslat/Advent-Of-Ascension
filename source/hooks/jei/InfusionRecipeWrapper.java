package net.tslat.aoa3.hooks.jei;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.crafting.recipes.InfusionTableRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class InfusionRecipeWrapper implements IRecipeWrapper {
	private final InfusionTableRecipe recipe;

	private InfusionRecipeWrapper(InfusionTableRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> ingredientsCollection = new ArrayList<List<ItemStack>>();
		List<ItemStack> inputStackList = new ArrayList<ItemStack>(1);
		ItemStack output = recipe.getRecipeOutput();

		if (recipe.isEnchanting())
			output = recipe.getEnchantmentAsBook();

		inputStackList.add(recipe.getRecipeInput());
		ingredientsCollection.add(inputStackList);

		for (Ingredient ing : recipe.getIngredients()) {
			ingredientsCollection.add(Lists.newArrayList(ing.getMatchingStacks()));
		}

		ingredients.setInputLists(VanillaTypes.ITEM, ingredientsCollection);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		if (recipe == null)
			return;

		String message;
		int textColour;
		int shadowColour;
		int width;
		int posX;
		int posY;

		if (recipe.getInfusionReq() > 1) {
			message = StringUtil.getLocaleString("skills.infusion.name") + ": " + recipe.getInfusionReq();
			textColour = AdventGuiTabPlayer.getSkillLevel(Enums.Skills.INFUSION) < recipe.getInfusionReq() ? 0xFFFF6060 : 0xFF80FF20;
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = minecraft.fontRenderer.getStringWidth(message);
			posX = recipeWidth - 4 - width;
			posY = 10;

			if (minecraft.fontRenderer.getUnicodeFlag()) {
				Gui.drawRect(posX - 2, posY - 2, posX + width + 2, posY + 10, 0xFF000000);
				Gui.drawRect(posX - 1, posY - 1, posX + width + 1, posY + 9, 0xFF3B3B3B);
			}
			else {
				minecraft.fontRenderer.drawString(message, posX + 1, posY, shadowColour);
				minecraft.fontRenderer.drawString(message, posX, posY + 1, shadowColour);
				minecraft.fontRenderer.drawString(message, posX + 1, posY + 1, shadowColour);
			}

			minecraft.fontRenderer.drawString(message, posX, posY, textColour);
		}

		if (recipe.getMaxXp() > 0) {
			message = StringUtil.getLocaleStringWithArguments("gui.misc.skills.xp", String.valueOf((recipe.getMinXp() == recipe.getMaxXp() ? recipe.getMaxXp() : recipe.getMinXp() + "-" + recipe.getMaxXp())));
			textColour = 0xFF8F8F8F;
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = minecraft.fontRenderer.getStringWidth(message);
			posX = recipeWidth - 8 - width;
			posY = 50;

			if (minecraft.fontRenderer.getUnicodeFlag()) {
				Gui.drawRect(posX - 2, posY - 2, posX + width + 2, posY + 10, 0xFF000000);
				Gui.drawRect(posX - 1, posY - 1, posX + width + 1, posY + 9, 0xFF3B3B3B);
			}
			else {
				minecraft.fontRenderer.drawString(message, posX + 1, posY, shadowColour);
				minecraft.fontRenderer.drawString(message, posX, posY + 1, shadowColour);
				minecraft.fontRenderer.drawString(message, posX + 1, posY + 1, shadowColour);
			}

			minecraft.fontRenderer.drawString(message, posX, posY, textColour);
		}
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

	@Nullable
	public ResourceLocation getRegistryName() {
		return recipe.getRegistryName();
	}

	public InfusionTableRecipe getRecipe() {
		return recipe;
	}

	public static class Factory implements IRecipeWrapperFactory<InfusionTableRecipe> {
		@Override
		public IRecipeWrapper getRecipeWrapper(InfusionTableRecipe recipe) {
			return new InfusionRecipeWrapper(recipe);
		}
	}
}
