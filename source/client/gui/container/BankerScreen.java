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
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

public class BankerScreen extends ContainerScreen<BankerContainer> {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/containers/banker.png");
	private final Minecraft mc;
	public BankerScreen(BankerContainer container, PlayerInventory inv, ITextComponent guiTitle) {
		super(container, inv, guiTitle);

		this.ySize = 187;
		this.mc = Minecraft.getInstance();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);

		RenderUtil.renderCustomSizedTexture(centerX, centerY, 0, 0, xSize, ySize, 256, 256);

		if (mc.player != null)
			renderCoinPlaceholders(centerX, centerY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int titleWidth = 4 + mc.fontRenderer.getStringWidth(title.getFormattedText());

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		RenderUtil.renderCustomSizedTexture(28, 4, 176, 15, 1, 12, 256, 256);

		for (int i = 0; i < titleWidth - 2; i++) {
			RenderUtil.renderCustomSizedTexture(29 + i, 4, 177, 15, 1, 12, 256, 256);
		}

		RenderUtil.renderCustomSizedTexture(28 + titleWidth - 2, 4, 178, 15, 1, 12, 256, 256);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.fontRenderer.drawString(title.getFormattedText(), 30, 6, NumberUtil.RGB(255, 255, 255));
	}

	private void renderCoinPlaceholders(int centerX, int centerY) {
		for (int i = 0; i < 12; i++) {
			Slot slot = container.getSlot(i);
			ItemStack stack = slot.getStack();

			if (stack.isEmpty()) {
				ItemStack coinStack = new ItemStack(BankerContainer.getCoinForSlot(i), (i < 3 || i > 8) ? 20 : 1);

				IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(coinStack, null, null);

				RenderSystem.pushMatrix();
				mc.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
				mc.textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmapDirect(false, false);
				RenderSystem.enableRescaleNormal();
				RenderSystem.enableAlphaTest();
				RenderSystem.defaultAlphaFunc();
				RenderSystem.enableBlend();
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.translatef(slot.xPos + centerX, slot.yPos + centerY, 100.0F);
				RenderSystem.translatef(8.0F, 8.0F, 0.0F);
				RenderSystem.scalef(1.0F, -1.0F, 1.0F);
				RenderSystem.scalef(16.0F, 16.0F, 16.0F);

				MatrixStack matrixstack = new MatrixStack();
				IRenderTypeBuffer.Impl renderTypeBuffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
				boolean diffuseLighting = !model.func_230044_c_();

				if (diffuseLighting)
					RenderHelper.setupGuiFlatDiffuseLighting();

				mc.getItemRenderer().renderItem(coinStack, ItemCameraTransforms.TransformType.GUI, false, matrixstack, renderTypeBuffer, 1000, OverlayTexture.NO_OVERLAY, model);
				renderTypeBuffer.finish();
				RenderSystem.enableDepthTest();

				if (diffuseLighting)
					RenderHelper.setupGui3DDiffuseLighting();

				RenderSystem.disableAlphaTest();
				RenderSystem.disableRescaleNormal();
				RenderSystem.popMatrix();
				mc.getItemRenderer().renderItemOverlays(mc.fontRenderer, coinStack, slot.xPos + centerX, slot.yPos + centerY);
				RenderSystem.enableAlphaTest();
			}
		}
	}
}
