package net.tslat.aoa3.integration.jei.recipe.imbuing;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

public class ImbuingRecipeCategory implements IRecipeCategory<ImbuingRecipe> {
	public static final RecipeType<ImbuingRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "imbuing", ImbuingRecipe.class);
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "imbuing");

	private final IModIdHelper idHelper;
	private final IIngredientManager ingredientHelper;

	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.imbuing");
	private final IDrawable background;
	private final IDrawable icon;

	public ImbuingRecipeCategory(IGuiHelper guiHelper, IModIdHelper idHelper, IIngredientManager ingredientHelper) {
		this.idHelper = idHelper;
		this.ingredientHelper = ingredientHelper;
		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/infusion_table.png");
		this.background = guiHelper.createDrawable(texture, 10, 10, 156, 66);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.INFUSION_TABLE.get()));
	}

	@Override
	public RecipeType<ImbuingRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
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
	public void setRecipe(IRecipeLayoutBuilder builder, ImbuingRecipe recipe, IFocusGroup focuses) {
		NonNullList<Ingredient> ingredients = recipe.getIngredients();

		builder.addSlot(RecipeIngredientRole.OUTPUT, 128, 25)
				.addItemStack(recipe.getEnchantmentAsBook())
				.addTooltipCallback((recipeSlotView, tooltip) -> {
					recipeSlotView.getDisplayedIngredient().ifPresent(output -> {
						ResourceLocation recipeId = getRegistryName(recipe);

						tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.jei.imbuing"), ChatFormatting.DARK_RED));

						if (recipeId == null)
							return;

						if (this.idHelper.isDisplayingModNameEnabled()) {
							String recipeModId = recipeId.getNamespace();

							if (!recipeModId.equals(this.ingredientHelper.getIngredientHelper(output).getResourceLocation((ITypedIngredient)output.getIngredient()).getNamespace())) {
								String modName = this.idHelper.getFormattedModNameForModId(recipeModId);
								MutableComponent recipeBy = Component.translatable("jei.tooltip.recipe.by", modName);
								tooltip.add(recipeBy.withStyle(ChatFormatting.GRAY));
							}
						}

						if (Minecraft.getInstance().options.advancedItemTooltips || Screen.hasShiftDown())
							tooltip.add(Component.translatable("jei.tooltip.recipe.id", recipeId.toString()).withStyle(ChatFormatting.DARK_GRAY));
					});
				});
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 25)
				.addItemStack(new ItemStack(Items.BOOK))
				.addTooltipCallback((recipeSlotView, tooltip) -> tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.jei.imbuing"), ChatFormatting.DARK_RED)));

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				IRecipeSlotBuilder slotBuilder = builder.addSlot(RecipeIngredientRole.INPUT, 35 + x * 18, 7 + y * 18);

				if (x + y * 3 < ingredients.size())
					slotBuilder.addIngredients(ingredients.get(x + y * 3));
			}
		}

		builder.setShapeless();
	}

	@Override
	public void draw(ImbuingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		if (recipe == null)
			return;

		Minecraft mc = Minecraft.getInstance();
		Component message;
		int textColour;
		int shadowColour;
		int width;
		int posX;
		int posY;
		RenderContext renderContext = RenderContext.of(guiGraphics);
		int infusionLevelReq = recipe.getInfusionLevelReq();
		FloatProvider xpProvider = recipe.getXpProvider();

		if (infusionLevelReq > 1) {
			message = AoASkills.IMBUING.get().getName().append(": " + infusionLevelReq);
			textColour = (ClientPlayerDataManager.get().getSkill(AoASkills.IMBUING.get()).getLevel(true) < infusionLevelReq) ? 0xFFFF6060 : 0xFF80FF20;
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = renderContext.textWidth(message);
			posX = 150 - width;
			posY = 10;

			renderContext.renderText(message, posX, posY, textColour, shadowColour, RenderUtil.TextRenderType.DROP_SHADOW, LightTexture.FULL_BRIGHT);
		}

		if (xpProvider.getMaxValue() > 0) {
			message = LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "misc.xpAmount"), Component.literal(String.valueOf((xpProvider.getMinValue() == xpProvider.getMaxValue() ? xpProvider.getMaxValue() : xpProvider.getMinValue() + "-" + xpProvider.getMaxValue()))));
			textColour = 0xFF8F8F8F;
			shadowColour = 0xFF000000 | (textColour & 0xFCFCFC) >> 2;
			width = mc.font.width(message);
			posX = 150 - width;
			posY = 50;

			renderContext.renderText(message, posX, posY, textColour, shadowColour, RenderUtil.TextRenderType.DROP_SHADOW, LightTexture.FULL_BRIGHT);
		}
	}
}
