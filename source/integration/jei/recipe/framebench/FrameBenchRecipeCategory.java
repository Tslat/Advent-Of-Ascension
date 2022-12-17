/*
package net.tslat.aoa3.integration.jei.recipe.framebench;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.recipe.FrameBenchRecipe;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

@SuppressWarnings("removal")
public class FrameBenchRecipeCategory implements IRecipeCategory<FrameBenchRecipe> {
	public static final RecipeType<FrameBenchRecipe> RECIPE_TYPE = RecipeType.create(AdventOfAscension.MOD_ID, "frame_bench", FrameBenchRecipe.class);
	public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "frame_bench");

	public static final ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/containers/frame_bench.png");
	private final Component title = LocaleUtil.getLocaleMessage("recipe.aoa3.frameBench");
	private final IDrawable background;
	private final IDrawable icon;

	public FrameBenchRecipeCategory(IGuiHelper guiHelper) {
		this.background = guiHelper.createDrawable(texture, 10, 12, 156, 60);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AoABlocks.FRAME_BENCH.get()));
	}

	@Override
	public RecipeType<FrameBenchRecipe> getRecipeType() {
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
	public void draw(FrameBenchRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrix, double mouseX, double mouseY) {
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

	private void drawButton(PoseStack matrix, Minecraft mc, Item frame, FrameBenchRecipe recipe, int x, int y) {
		RenderUtil.prepRenderTexture(texture);
		matrix.pushPose();
		RenderUtil.resetShaderColour();
		RenderUtil.renderCustomSizedTexture(matrix, x, y, 176, recipe.getResultItem().getItem() == frame ? 21 : 39, 18, 18, 256, 256);
		matrix.translate(0, 0, 32);
		RenderUtil.renderItemInGui(matrix, mc, new ItemStack(frame), x + 1, y + 1);
		matrix.popPose();
		RenderUtil.resetShaderColour();
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, FrameBenchRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.OUTPUT, 139, 22)
				.addItemStack(recipe.getResultItem());
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 22)
				.addIngredients(recipe.getIngredients().get(0));
	}
}
*/
