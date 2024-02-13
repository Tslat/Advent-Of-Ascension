package net.tslat.aoa3.integration.jei.recipe.imbuing;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.library.gui.ingredients.RecipeSlot;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ItemLike;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.integration.jei.ingredient.type.imbuing.ImbuingIngredientType;
import net.tslat.aoa3.integration.jei.recipe.ContainerRecipeCategory;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.List;

public class ImbuingRecipeCategory extends ContainerRecipeCategory<ImbuingRecipe> {
	public static final RecipeType<ImbuingRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "imbuing", ImbuingRecipe.class);

	public ImbuingRecipeCategory(IGuiHelper guiHelper, IModIdHelper idHelper, IIngredientManager ingredientHelper) {
        super(RECIPE_TYPE, guiHelper, idHelper, ingredientHelper);
    }

	@Override
	public RecipeType<ImbuingRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	protected ItemLike getRecipeCatalyst() {
		return AoABlocks.IMBUING_CHAMBER;
	}

	@Override
	protected int backgroundTextureU() {
		return 11;
	}

	@Override
	protected int backgroundTextureV() {
		return 11;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return AdventOfAscension.id("textures/gui/containers/imbuing_chamber.png");
	}

	@Override
	protected IDrawable createBackgroundDrawRegion(IGuiHelper guiHelper, ResourceLocation backgroundTexture) {
		return guiHelper.createDrawable(backgroundTexture, backgroundTextureU(), backgroundTextureV(), 154, 64);
	}

	@Override
	protected void createRecipeLayout(IRecipeLayoutBuilder builder, ImbuingRecipe recipe, IFocusGroup focuses) {
		final NonNullList<Ingredient> ingredients = recipe.getIngredients();

		builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT)
				.addIngredient(ImbuingIngredientType.INSTANCE, new EnchantmentInstance(recipe.getEnchant().left(), recipe.getEnchant().rightInt()));
		builder.addSlot(RecipeIngredientRole.CATALYST, 139, 35)
				.addItemStacks(AoARegistries.ITEMS.getAllRegisteredObjects().map(Item::getDefaultInstance).filter(recipe::canEnchantInput).toList());

		for (int slotIndex = 0; slotIndex < 6; slotIndex++) {
			IRecipeSlotBuilder slotBuilder = builder.addSlot(RecipeIngredientRole.INPUT, slotIndex == 0 ? 17 : 19 + 19 * slotIndex, 35);

			if (slotIndex < ingredients.size())
				slotBuilder.addIngredients(ingredients.get(slotIndex));
		}
	}

	@Override
	public List<Component> getTooltipStrings(ImbuingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		if (mouseX < 140 || mouseY > 14)
			return super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);

		final List<Component> tooltip = new ObjectArrayList<>();
		AoASkill.Instance skill = ClientPlayerDataManager.get().getSkill(AoASkills.IMBUING.get());
		boolean hasLevel = skill.hasLevel(recipe.getImbuingLevelReq());

		tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGuiLocaleKey("tooltip.skillReq"), ChatFormatting.LIGHT_PURPLE));
		tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.Keys.SKILL_REQUIREMENT, Component.literal(String.valueOf(recipe.getImbuingLevelReq())), skill.getName()).withStyle(style -> style.withColor(hasLevel ? 0xFF80FF20: 0xFFFF6060)));

		if (recipe.getXpOverrideProvider().isPresent()) {
			FloatProvider xpProvider = recipe.getXpOverrideProvider().get();
			String minXp = NumberUtil.floorAndAppendSuffix(xpProvider.getMinValue(), true);
			String maxXp = NumberUtil.floorAndAppendSuffix(xpProvider.getMaxValue(), true);

			tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGuiLocaleKey("misc.xpAmount"), ChatFormatting.YELLOW, Component.literal(minXp + "-" + maxXp), skill.getName()));
		}
		else {
			tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGuiLocaleKey("misc.xpAmount"), ChatFormatting.YELLOW, Component.literal(NumberUtil.roundToNthDecimalPlace(recipe.getXp(Minecraft.getInstance().player), 2)), skill.getName()));
		}

		return tooltip;
	}

	@Override
	public void draw(ImbuingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		final int xOffset = -backgroundTextureU();
		final int yOffset = -backgroundTextureU();
		final PoseStack poseStack = guiGraphics.pose();

		RenderUtil.prepRenderTexture(getBackgroundTexture());
		RenderUtil.resetShaderColour();

		for (IRecipeSlotView slotView : recipeSlotsView.getSlotViews(RecipeIngredientRole.INPUT)) {
			Rect2i slotRegion = ((RecipeSlot)slotView).getRect();

			RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), slotRegion.getX() - 1, slotRegion.getY() - 1, 26, 166, 18, 18, 256, 256);
		}

		Rect2i outputSlotRegion = ((RecipeSlot)recipeSlotsView.getSlotViews(RecipeIngredientRole.CATALYST).get(0)).getRect();

		RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), outputSlotRegion.getX() - 5, outputSlotRegion.getY() - 5, 0, 166, 26, 26, 256, 256);

		if (recipe == null)
			return;

		final Component enchant = recipe.getEnchant().left().getFullname(recipe.getEnchant().rightInt());

		RenderUtil.drawRectangle(guiGraphics.pose(), 16 + xOffset, 59 + yOffset, Minecraft.getInstance().font.width(enchant) + 1, 10, 0xCC000000);
		RenderUtil.renderText(guiGraphics.pose(), enchant, 17 + xOffset, 60 + yOffset, 0xB2B2B2, RenderUtil.TextRenderType.NORMAL);

		AoASkillRenderer skillRenderer = AoAGuiElementRenderers.getSkillRenderer(AoASkills.IMBUING.get());
		AoASkill.Instance skill = ClientPlayerDataManager.get().getSkill(AoASkills.IMBUING.get());

		poseStack.pushPose();
		poseStack.translate(153 - skillRenderer.guiRenderWidth(skill) * 0.5f, 1, 0);
		poseStack.scale(0.5f, 0.5f, 1);

		skillRenderer.renderInHud(RenderContext.of(guiGraphics), skill, Minecraft.getInstance().getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);

		poseStack.popPose();
	}
}
