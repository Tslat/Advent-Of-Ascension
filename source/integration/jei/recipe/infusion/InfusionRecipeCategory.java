package net.tslat.aoa3.integration.jei.recipe.infusion;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.object.recipe.InfusionRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.ArrayList;
import java.util.List;

public class InfusionRecipeCategory implements IRecipeCategory<InfusionRecipe> {
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "infusion");
	private final String title = LocaleUtil.getLocaleString("recipe.aoa3.infusion");
	private final IDrawable background;
	private final IDrawable icon;
	private final ICraftingGridHelper craftingGridHelper;

	public InfusionRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/infusion_table.png");
		this.background = guiHelper.createDrawable(texture, 10, 10, 156, 66);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(AoABlocks.INFUSION_TABLE.get()));
		this.craftingGridHelper = guiHelper.createCraftingGridHelper(1);
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends InfusionRecipe> getRecipeClass() {
		return InfusionRecipe.class;
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
	public void setIngredients(InfusionRecipe recipe, IIngredients ingredients) {
		List<List<ItemStack>> ingredientsCollection = new ArrayList<List<ItemStack>>();
		List<ItemStack> inputStackList = new ArrayList<ItemStack>(1);
		ItemStack output = recipe.getResultItem();

		if (recipe.isEnchanting())
			output = recipe.getEnchantmentAsBook();

		inputStackList.add(recipe.getRecipeInput());
		ingredientsCollection.add(inputStackList);

		for (Ingredient ing : recipe.getIngredients()) {
			ingredientsCollection.add(Lists.newArrayList(ing.getItems()));
		}

		ingredients.setInputLists(VanillaTypes.ITEM, ingredientsCollection);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, InfusionRecipe recipe, IIngredients ingredients) {
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
		ResourceLocation recipeRegistryName = recipe.getId();

		guiStacks.set(0, outputs.get(0));
		craftingGridHelper.setInputs(guiStacks, inputs, 3, 3);
		recipeLayout.setShapeless();

		if (recipeRegistryName != null) {
			guiStacks.addTooltipCallback(((slotIndex, input, ingredient, tooltip) -> {
				if (slotIndex == 0) {
					if (!recipeRegistryName.getNamespace().equals("aoa3"))
						tooltip.add(LocaleUtil.getLocaleMessage("jei.tooltip.recipe.by", TextFormatting.GRAY, new StringTextComponent(recipeRegistryName.getNamespace())));

					if (Minecraft.getInstance().options.advancedItemTooltips || Screen.hasShiftDown())
						tooltip.add(LocaleUtil.getLocaleMessage("jei.tooltip.recipe.id", TextFormatting.DARK_GRAY, new StringTextComponent(recipeRegistryName.toString())));
				}
			}));
		}
	}

	@Override
	public void draw(InfusionRecipe recipe, MatrixStack matrix, double mouseX, double mouseY) {
		if (recipe == null)
			return;

		Minecraft mc = Minecraft.getInstance();
		String message;
		int textColour;
		int shadowColour;
		int width;
		int posX;
		int posY;

		if (recipe.getInfusionReq() > 1) {
			message = AoASkills.IMBUING.get().getName().append(": " + recipe.getInfusionReq()).getString();
			textColour = 0xFFFF6060;//AdventGuiTabPlayer.getSkillLevel(Skills.INFUSION) < recipe.getInfusionReq() ? 0xFFFF6060 : 0xFF80FF20; TODO
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = mc.font.width(message);
			posX = 150 - width;
			posY = 10;

			mc.font.draw(matrix, message, posX + 1, posY, shadowColour);
			mc.font.draw(matrix, message, posX, posY + 1, shadowColour);
			mc.font.draw(matrix, message, posX + 1, posY + 1, shadowColour);
			mc.font.draw(matrix, message, posX, posY, textColour);
		}

		if (recipe.getMaxXp() > 0) {
			message = LocaleUtil.getLocaleString("gui.misc.skills.xp", String.valueOf((recipe.getMinXp() == recipe.getMaxXp() ? recipe.getMaxXp() : recipe.getMinXp() + "-" + recipe.getMaxXp())));
			textColour = 0xFF8F8F8F;
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = mc.font.width(message);
			posX = 150 - width;
			posY = 50;

			mc.font.draw(matrix, message, posX + 1, posY, shadowColour);
			mc.font.draw(matrix, message, posX, posY + 1, shadowColour);
			mc.font.draw(matrix, message, posX + 1, posY + 1, shadowColour);
			mc.font.draw(matrix, message, posX, posY, textColour);
		}
	}
}
