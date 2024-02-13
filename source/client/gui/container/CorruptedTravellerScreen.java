package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.menu.CorruptedTravellerMenu;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CorruptedTravellerScreen extends AbstractContainerScreen<CorruptedTravellerMenu> {
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("aoa3", "textures/gui/containers/corrupted_traveller.png");
	private static final ArrayList<Item> APPLICABLE_FOOD = new ArrayList<>();

	private long nextFoodTick = 0;
	private Item currentGhostlyFood = Items.APPLE;
	private final Minecraft mc;

	public CorruptedTravellerScreen(CorruptedTravellerMenu container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		final int centerX = (this.width - this.imageWidth) / 2;
		final int centerY = (this.height - this.imageHeight) / 2;
		final RenderContext renderContext = RenderContext.of(guiGraphics);

		renderContext.setTextureForRendering(GUI_TEXTURE);
		renderContext.resetShaderColour();
		renderContext.renderCustomSizedTexture(centerX, centerY, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		renderGhostlyFood(renderContext, centerX, centerY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		final RenderContext renderContext = RenderContext.of(guiGraphics);
		final int titleWidth = 4 + renderContext.textWidth(this.title);

		renderContext.setTextureForRendering(GUI_TEXTURE);
		renderContext.renderCustomSizedTexture(28, 4, 176, 0, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			renderContext.renderCustomSizedTexture(29 + i, 4, 177, 0, 1, 12, 256, 256);
		}

		renderContext.renderCustomSizedTexture(28 + titleWidth - 2, 4, 178, 0, 1, 12, 256, 256);
		renderContext.resetShaderColour();
		renderContext.renderText(this.title, 30, 6, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
	}

	private void renderGhostlyFood(RenderContext renderContext, int centerX, int centerY) {
		Slot slot = menu.getSlot(0);

		if (!slot.hasItem()) {
			ItemStack stack = new ItemStack(getGhostlyFood());
			ItemRenderer itemRenderer = mc.getItemRenderer();
			BakedModel model = itemRenderer.getModel(stack, null, null, 0);

			mc.textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
			RenderUtil.setRenderingTexture(TextureAtlas.LOCATION_BLOCKS);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderUtil.resetShaderColour();
			PoseStack modelViewPose = RenderSystem.getModelViewStack();

			modelViewPose.pushPose();
			modelViewPose.translate(slot.x + centerX, slot.y + centerY, 100f);
			modelViewPose.translate(8, 8, 0);
			modelViewPose.scale(1, -1, 1);
			modelViewPose.scale(16, 16, 16);
			RenderSystem.applyModelViewMatrix();

			PoseStack poseStack = new PoseStack();
			MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
			boolean changeLighting = !model.usesBlockLight();

			if (changeLighting)
				Lighting.setupForFlatItems();

			itemRenderer.render(stack, ItemDisplayContext.GUI, false, poseStack, bufferSource, 40, OverlayTexture.NO_OVERLAY, model);
			bufferSource.endBatch();
			RenderSystem.enableDepthTest();

			if (changeLighting)
				Lighting.setupFor3DItems();

			modelViewPose.popPose();
			RenderSystem.applyModelViewMatrix();
		}
	}

	private void compileFoodList() {
		AoARegistries.ITEMS.getAllRegisteredObjects().filter(Item::isEdible).forEach(APPLICABLE_FOOD::add);
	}

	@NotNull
	private Item getGhostlyFood() {
		if (APPLICABLE_FOOD.isEmpty())
			compileFoodList();

		long worldTick = mc.level.getGameTime();

		if (worldTick >= nextFoodTick) {
			currentGhostlyFood = APPLICABLE_FOOD.get(RandomUtil.randomNumberUpTo(APPLICABLE_FOOD.size()));
			nextFoodTick = worldTick + 20;
		}

		return currentGhostlyFood;
	}
}
