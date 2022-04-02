package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.container.BankerContainer;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

public class BankerScreen extends AbstractContainerScreen<BankerContainer> {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/containers/banker.png");
	private final Minecraft mc;
	public BankerScreen(BankerContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.imageHeight = 187;
		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderUtil.prepRenderTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);

		if (mc.player != null)
			renderCoinPlaceholders(matrix, centerX, centerY);
	}

	@Override
	protected void renderLabels(PoseStack matrix, int mouseX, int mouseY) {
		int titleWidth = 4 + mc.font.width(title);

		RenderUtil.resetShaderColour();
		RenderUtil.prepRenderTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 15, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 15, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 15, 1, 12, 256, 256);
		RenderUtil.resetShaderColour();
		mc.font.draw(matrix, title, 30, 6, ColourUtil.WHITE);
	}

	private void renderCoinPlaceholders(PoseStack matrix, int centerX, int centerY) {
		for (int i = 0; i < 12; i++) {
			Slot slot = menu.getSlot(i);
			ItemStack stack = slot.getItem();

			if (stack.isEmpty()) {
				ItemStack coinStack = new ItemStack(BankerContainer.getCoinForSlot(i), (i < 3 || i > 8) ? 20 : 1);
				BakedModel model = mc.getItemRenderer().getModel(coinStack, null, null, 0);

				matrix.pushPose();
				RenderUtil.prepRenderTexture(InventoryMenu.BLOCK_ATLAS);
				mc.textureManager.getTexture(InventoryMenu.BLOCK_ATLAS).setFilter(false, false);
				//RenderSystem.enableRescaleNormal();
				//RenderSystem.enableAlphaTest();
				RenderSystem.enableBlend();
				RenderUtil.setDefaultAlphaBlend();
				RenderUtil.resetShaderColour();
				matrix.translate(slot.x + centerX, slot.y + centerY, 100);
				matrix.translate(8, 8, 0);
				matrix.scale(1, -1, 1);
				matrix.scale(16, 16, 16);

				MultiBufferSource.BufferSource renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
				boolean diffuseLighting = !model.usesBlockLight();

				if (diffuseLighting)
					Lighting.setupForFlatItems();

				mc.getItemRenderer().render(coinStack, ItemTransforms.TransformType.GUI, false, matrix, renderTypeBuffer, 1000, OverlayTexture.NO_OVERLAY, model);
				renderTypeBuffer.endBatch();
				RenderSystem.enableDepthTest();
				if (diffuseLighting)
					Lighting.setupFor3DItems();

				//RenderSystem.disableAlphaTest();
				//RenderSystem.disableRescaleNormal();
				matrix.popPose();
				mc.getItemRenderer().renderGuiItemDecorations(mc.font, coinStack, slot.x + centerX, slot.y + centerY);
				//RenderSystem.enableAlphaTest();
			}
		}
	}
}
