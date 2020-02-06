package net.tslat.aoa3.client.gui.merchants;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityList;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.containers.ContainerBankerTrade;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import org.apache.logging.log4j.Level;

import java.util.List;

@SideOnly(Side.CLIENT)
public class BankerGui extends GuiContainer {
	private final AoATrader banker;
	private final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/traders/banker_trade.png");

	private String guiTitle;

	public BankerGui(Container container, AoATrader banker) {
		super(container);

		this.ySize = 187;
		this.banker = banker;
		this.guiTitle = banker.getDisplayName().getUnformattedComponentText();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);

		drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);

		if (mc.player != null)
			renderCoinPlaceholders(centerX, centerY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);

		if (titleWidth > xSize - 28 && banker != null) {
			guiTitle = StringUtil.getLocaleString("entity." + EntityList.getEntityString(banker) + ".name");
			titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);
		}

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(28, 4, 176, 15, 1, 12);

		for (int i = 0; i < titleWidth - 2; i++) {
			drawTexturedModalRect(29 + i, 4, 177, 15, 1, 12);
		}

		drawTexturedModalRect(28 + titleWidth - 2, 4, 178, 15, 1, 12);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.fontRenderer.drawString(guiTitle, 30, 6, Enums.RGBIntegers.WHITE);
	}

	private void renderCoinPlaceholders(int centerX, int centerY) {
		for (int i = 0; i < 12; i++) {
			Slot slot = inventorySlots.getSlot(i);
			ItemStack stack = slot.getStack();

			if (stack.isEmpty()) {
				ItemStack coinStack = new ItemStack(ContainerBankerTrade.getCoinForSlot(i));

				itemRender.renderItemIntoGUI(coinStack, slot.xPos + centerX, slot.yPos + centerY);
				shadeRenderedStack(coinStack, slot.xPos + centerX, slot.yPos + centerY);
			}

			if (i >= 6 && stack.isEmpty()) {
				mc.getTextureManager().bindTexture(guiTexture);
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
				GlStateManager.disableLighting();
				drawTexturedModalRect(centerX + slot.xPos - 22, centerY + slot.yPos + 1, 176, 0, 15, 15);
			}
		}
	}

	private void shadeRenderedStack(ItemStack stack, int posX, int posY) {
		try {
			zLevel = 100;
			itemRender.zLevel = 150;
			IBakedModel model = itemRender.getItemModelWithOverrides(stack, null, mc.player);

			GlStateManager.pushMatrix();
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableAlpha();
			GlStateManager.alphaFunc(516, 0.1f);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.translate((float)posX, (float)posY, 100 + itemRender.zLevel);
			GlStateManager.translate(8, 8, 8);
			GlStateManager.scale(1f, -1f, 1f);
			GlStateManager.scale(16f, 16f, 16f);

			if (model.isGui3d()) {
				GlStateManager.enableLighting();
			}
			else {
				GlStateManager.disableLighting();
			}

			model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GUI, false);

			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5f, -0.5f, -0.5f);

			if (model.isBuiltInRenderer()) {
				GlStateManager.color(1, 1, 1, 1);
				GlStateManager.enableRescaleNormal();
				stack.getItem().getTileEntityItemStackRenderer().renderByItem(stack);
			}
			else {
				Tessellator tess = Tessellator.getInstance();
				BufferBuilder buff = tess.getBuffer();

				buff.begin(7, DefaultVertexFormats.ITEM);

				for (EnumFacing facing : EnumFacing.VALUES) {
					List<BakedQuad> quads = model.getQuads(null, facing, 0);

					for (int i = 0; i < quads.size(); ++i) {
						LightUtil.renderQuadColor(buff, quads.get(i), Enums.RGBIntegers.BLACK | (0x99 << 24));
					}
				}

				List<BakedQuad> quads = model.getQuads(null, null, 0);

				for (int i = 0; i < quads.size(); ++i) {
					LightUtil.renderQuadColor(buff, quads.get(i), Enums.RGBIntegers.BLACK | (0x99 << 24));
				}

				tess.draw();
			}

			GlStateManager.popMatrix();
			GlStateManager.disableAlpha();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();

			itemRender.zLevel = 0;
			zLevel = 0;
		}
		catch (Exception e) {
			AdventOfAscension.logMessage(Level.WARN, "Error while rendering itemstack");
			e.printStackTrace();
		}
	}
}
