package net.tslat.aoa3.client.gui.merchants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IMerchant;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CorruptedTravellerGui extends GuiContainer {
	private static final ResourceLocation guiTexture = new ResourceLocation("aoa3", "textures/gui/traders/corrupted_traveller_trade.png");
	private static final ArrayList<ItemStack> validFoods = new ArrayList<ItemStack>();

	private long nextFoodTick = 0;
	private ItemStack currentGhostlyFood = new ItemStack(Items.APPLE);

	private final IMerchant corruptedTraveller;
	private String guiTitle;

	public CorruptedTravellerGui(Container container, IMerchant corruptedTraveller) {
		super(container);

		this.corruptedTraveller = corruptedTraveller;
		this.guiTitle = corruptedTraveller.getDisplayName().getUnformattedComponentText();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);

		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;

		drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);
		renderGhostlyFood(centerX, centerY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);

		if (titleWidth > xSize - 28 && corruptedTraveller instanceof Entity) {
			guiTitle = StringUtil.getLocaleString("entity." + EntityList.getEntityString((Entity)corruptedTraveller) + ".name");
			titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);
		}

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(28, 4, 176, 0, 1, 12);

		for (int i = 0; i < titleWidth - 2; i++) {
			drawTexturedModalRect(29 + i, 4, 177, 0, 1, 12);
		}

		drawTexturedModalRect(28 + titleWidth - 2, 4, 178, 0, 1, 12);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.fontRenderer.drawString(guiTitle, 30, 6, Enums.RGBIntegers.WHITE);
	}

	private void renderStack(ItemStack stack, int posX, int posY) {
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
			GlStateManager.color(1.0f, 1, 1, 1);
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
						BakedQuad bakedQuad = quads.get(i);
						int colour = -1;

						if (bakedQuad.hasTintIndex()) {
							colour = mc.getItemColors().colorMultiplier(stack, bakedQuad.getTintIndex());

							if (EntityRenderer.anaglyphEnable)
								colour = TextureUtil.anaglyphColor(colour);

							colour = colour | -16777216;
						}

						LightUtil.renderQuadColor(buff, bakedQuad, -(0x90 << 24));
					}
				}

				List<BakedQuad> quads = model.getQuads(null, null, 0);

				for (int i = 0; i < quads.size(); ++i) {
					BakedQuad bakedQuad = quads.get(i);
					int colour = -1;

					if (bakedQuad.hasTintIndex()) {
						colour = mc.getItemColors().colorMultiplier(stack, bakedQuad.getTintIndex());

						if (EntityRenderer.anaglyphEnable)
							colour = TextureUtil.anaglyphColor(colour);

						colour = colour | -16777216;
					}

					LightUtil.renderQuadColor(buff, bakedQuad, -(0x2F << 24));
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
		catch (Exception e) {}
	}

	private void renderGhostlyFood(int centerX, int centerY) {
		Slot slot = inventorySlots.getSlot(0);

		if (!slot.getHasStack()) {
			ItemStack stack = getGhostlyStack();

			itemRender.renderItemIntoGUI(stack, slot.xPos + centerX, slot.yPos + centerY);
			renderStack(stack, slot.xPos + centerX, slot.yPos + centerY);
		}
	}

	@Nonnull
	private ItemStack getGhostlyStack() {
		if (validFoods.isEmpty())
			validFoods.addAll(OreDictionary.getOres("listAllFood"));

		long worldTick = Minecraft.getMinecraft().world.getTotalWorldTime();

		if (worldTick >= nextFoodTick) {
			currentGhostlyFood = validFoods.get(AdventOfAscension.rand.nextInt(validFoods.size()));
			nextFoodTick = worldTick + 20;
		}

		return currentGhostlyFood;
	}
}
