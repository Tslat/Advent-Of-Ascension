package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.common.container.BankerContainer;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

public class BankerScreen extends ContainerScreen<BankerContainer> {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/containers/banker.png");
	private final Minecraft mc;
	public BankerScreen(BankerContainer container, PlayerInventory inv, ITextComponent guiTitle) {
		super(container, inv, guiTitle);

		this.imageHeight = 187;
		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
		final int centerX = (width - imageWidth) / 2;
		final int centerY = (height - imageHeight) / 2;

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bind(guiTexture);

		RenderUtil.renderCustomSizedTexture(matrix, centerX, centerY, 0, 0, imageWidth, imageHeight, 256, 256);

		if (mc.player != null)
			renderCoinPlaceholders(matrix, centerX, centerY);
	}

	@Override
	protected void renderLabels(MatrixStack matrix, int mouseX, int mouseY) {
		int titleWidth = 4 + mc.font.width(title);

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bind(guiTexture);
		RenderUtil.renderCustomSizedTexture(matrix, 28, 4, 176, 15, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(matrix, 29 + i, 4, 177, 15, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(matrix, 28 + titleWidth - 2, 4, 178, 15, 1, 12, 256, 256);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.font.draw(matrix, title, 30, 6, ColourUtil.WHITE);
	}

	private void renderCoinPlaceholders(MatrixStack matrix, int centerX, int centerY) {
		for (int i = 0; i < 12; i++) {
			Slot slot = menu.getSlot(i);
			ItemStack stack = slot.getItem();

			if (stack.isEmpty()) {
				ItemStack coinStack = new ItemStack(BankerContainer.getCoinForSlot(i), (i < 3 || i > 8) ? 20 : 1);
				IBakedModel model = mc.getItemRenderer().getModel(coinStack, null, null);

				matrix.pushPose();
				mc.textureManager.bind(AtlasTexture.LOCATION_BLOCKS);
				mc.textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS).setFilter(false, false);
				RenderSystem.enableRescaleNormal();
				RenderSystem.enableAlphaTest();
				RenderSystem.defaultAlphaFunc();
				RenderSystem.enableBlend();
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				matrix.translate(slot.x + centerX, slot.y + centerY, 100.0F);
				matrix.translate(8.0F, 8.0F, 0.0F);
				matrix.scale(1.0F, -1.0F, 1.0F);
				matrix.scale(16.0F, 16.0F, 16.0F);

				IRenderTypeBuffer.Impl renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
				boolean diffuseLighting = !model.usesBlockLight();

				if (diffuseLighting)
					RenderHelper.setupForFlatItems();

				mc.getItemRenderer().render(coinStack, ItemCameraTransforms.TransformType.GUI, false, matrix, renderTypeBuffer, 1000, OverlayTexture.NO_OVERLAY, model);
				renderTypeBuffer.endBatch();
				RenderSystem.enableDepthTest();

				if (diffuseLighting)
					RenderHelper.setupFor3DItems();

				RenderSystem.disableAlphaTest();
				RenderSystem.disableRescaleNormal();
				matrix.popPose();
				mc.getItemRenderer().renderGuiItemDecorations(mc.font, coinStack, slot.x + centerX, slot.y + centerY);
				RenderSystem.enableAlphaTest();
			}
		}
	}
}
