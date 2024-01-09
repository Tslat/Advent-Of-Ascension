package net.tslat.aoa3.client.gui.container;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.container.BankerContainer;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

public class BankerScreen extends AbstractContainerScreen<BankerContainer> {
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("aoa3", "textures/gui/containers/banker.png");
	private final Minecraft mc;
	public BankerScreen(BankerContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.imageHeight = 187;
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

		renderContext.resetShaderColour();
		renderContext.setTextureForRendering(GUI_TEXTURE);
		renderContext.renderCustomSizedTexture(centerX, centerY, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		if (this.mc.player != null)
			renderCoinPlaceholders(renderContext, centerX, centerY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		int titleWidth = 4 + mc.font.width(title);
		RenderContext renderContext = RenderContext.of(guiGraphics);

		renderContext.setTextureForRendering(GUI_TEXTURE);
		renderContext.resetShaderColour();
		renderContext.renderCustomSizedTexture(28, 4, 176, 15, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			renderContext.renderCustomSizedTexture(29 + i, 4, 177, 15, 1, 12, 256, 256);
		}

		renderContext.renderCustomSizedTexture(28 + titleWidth - 2, 4, 178, 15, 1, 12, 256, 256);
		renderContext.resetShaderColour();
		renderContext.renderText(this.title, 30, 60, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
	}

	private void renderCoinPlaceholders(RenderContext renderContext, int centerX, int centerY) {
		for (int i = 0; i < 12; i++) {
			Slot slot = menu.getSlot(i);
			ItemStack stack = slot.getItem();

			if (stack.isEmpty()) {
				ItemStack coinStack = new ItemStack(BankerContainer.getCoinForSlot(i), (i < 3 || i > 8) ? 20 : 1);

				renderContext.renderDummyItemAndDetails(coinStack, slot.x + centerX, slot.y + centerY);




				/*BakedModel model = mc.getItemRenderer().getModel(coinStack, null, null, 0);

				renderContext.pushPose();
				renderContext.setTextureForRendering(InventoryMenu.BLOCK_ATLAS);
				mc.textureManager.getTexture(InventoryMenu.BLOCK_ATLAS).setFilter(false, false);
				//RenderSystem.enableRescaleNormal();
				//RenderSystem.enableAlphaTest();
				RenderSystem.enableBlend();
				RenderUtil.setDefaultAlphaBlend();
				RenderUtil.resetShaderColour();
				poseStack.translate(slot.x + centerX, slot.y + centerY, 100);
				poseStack.translate(8, 8, 0);
				poseStack.scale(1, -1, 1);
				poseStack.scale(16, 16, 16);

				MultiBufferSource.BufferSource renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
				boolean diffuseLighting = !model.usesBlockLight();

				if (diffuseLighting)
					Lighting.setupForFlatItems();

				mc.getItemRenderer().render(coinStack, ItemDisplayContext.GUI, false, poseStack, renderTypeBuffer, 1000, OverlayTexture.NO_OVERLAY, model);
				renderTypeBuffer.endBatch();
				RenderSystem.enableDepthTest();
				if (diffuseLighting)
					Lighting.setupFor3DItems();

				//RenderSystem.disableAlphaTest();
				//RenderSystem.disableRescaleNormal();
				poseStack.popPose();
				mc.getItemRenderer().renderGuiItemDecorations(poseStack, mc.font, coinStack, slot.x + centerX, slot.y + centerY);
				//RenderSystem.enableAlphaTest();*/
			}
		}
	}
}
