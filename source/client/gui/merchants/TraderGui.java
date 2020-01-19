package net.tslat.aoa3.client.gui.merchants;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IMerchant;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import org.apache.logging.log4j.Level;

@SideOnly(Side.CLIENT)
public class TraderGui extends GuiContainer {
	private final IMerchant trader;
	private final ResourceLocation guiTexture;

	private TraderButton nextRecipeButton;
	private TraderButton previousRecipeButton;

	private int currentRecipeIndex = 0;
	private String guiTitle;
	private int lastTradesSize = -1;

	public TraderGui(ContainerMerchant container, IMerchant trader, String guiName) {
		super(container);

		this.trader = trader;
		this.guiTexture = new ResourceLocation("aoa3", "textures/gui/traders/" + guiName + ".png");
		this.guiTitle = trader.getDisplayName().getUnformattedComponentText();
	}

	@Override
	public void initGui() {
		super.initGui();
		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;

		buttonList.add(nextRecipeButton = new TraderButton(1, centerX + 147, centerY + 23, true, guiTexture));
		buttonList.add(previousRecipeButton = new TraderButton(2, centerX + 17, centerY + 23, false, guiTexture));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		if (mc.player != null) {
			MerchantRecipeList trades = trader.getRecipes(Minecraft.getMinecraft().player);

			if (trades != null) {
				if (lastTradesSize < 0) {
					lastTradesSize = 0;
				}
				else if (trades.size() > lastTradesSize) {
					lastTradesSize = trades.size();

					((ContainerMerchant)inventorySlots).getMerchantInventory().resetRecipeAndSlots();
				}

				nextRecipeButton.enabled = currentRecipeIndex < trades.size() - 1;
				previousRecipeButton.enabled = currentRecipeIndex > 0;
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		boolean changeRecipe = false;

		if (button == nextRecipeButton) {
			currentRecipeIndex++;
			changeRecipe = true;
		}
		else if (button == previousRecipeButton) {
			currentRecipeIndex--;
			changeRecipe = true;
		}

		if (changeRecipe) {
			PacketBuffer packet = new PacketBuffer(Unpooled.buffer());

			((ContainerMerchant)inventorySlots).setCurrentRecipeIndex(currentRecipeIndex);
			packet.writeInt(currentRecipeIndex);

			try {
				mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrSel", packet));
			}
			catch (NullPointerException ex) {
				AdventOfAscension.logMessage(Level.ERROR, "Unable to send trader recipe info", ex);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		final int centerX = (width - xSize) / 2;
		final int centerY = (height - ySize) / 2;
		drawTexturedModalRect(centerX, centerY, 0, 0, xSize, ySize);

		if (mc.player != null) {
			final MerchantRecipeList trades = trader.getRecipes(mc.player);

			if (trades != null && !trades.isEmpty()) {
				final MerchantRecipe trade = trades.get(currentRecipeIndex);

				if (trade.isRecipeDisabled()) {
					mc.getTextureManager().bindTexture(guiTexture);
					GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
					GlStateManager.disableLighting();
					drawTexturedModalRect(guiLeft + 83, guiTop + 21, 212, 0, 28, 21);
					drawTexturedModalRect(guiLeft + 83, guiTop + 51, 212, 0, 28, 21);
				}
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawTradeItems(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	private void drawTradeItems(int mouseX, int mouseY, float partialTicks) {
		final MerchantRecipeList trades = trader.getRecipes(mc.player);

		if (trades != null && !trades.isEmpty()) {
			final int centreX = (width - xSize) / 2;
			final int centreY = (height - ySize) / 2;
			final MerchantRecipe trade = trades.get(currentRecipeIndex);
			final ItemStack buyStack1 = trade.getItemToBuy();
			final ItemStack buyStack2 = trade.getSecondItemToBuy();
			final ItemStack sellStack = trade.getItemToSell();

			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableColorMaterial();
			GlStateManager.enableLighting();

			itemRender.zLevel = 100;

			itemRender.renderItemAndEffectIntoGUI(buyStack1, centreX + 36, centreY + 24);
			itemRender.renderItemOverlays(this.fontRenderer, buyStack1, centreX + 36, centreY + 24);

			if (!buyStack2.isEmpty()) {
				itemRender.renderItemAndEffectIntoGUI(buyStack2, centreX + 62, centreY + 24);
				itemRender.renderItemOverlays(this.fontRenderer, buyStack2, centreX + 62, centreY + 24);
			}

			itemRender.renderItemAndEffectIntoGUI(sellStack, centreX + 120, centreY + 24);
			itemRender.renderItemOverlays(this.fontRenderer, sellStack, centreX + 120, centreY + 24);

			itemRender.zLevel = 0;

			GlStateManager.disableLighting();

			if (!buyStack1.isEmpty() && isPointInRegion(36, 24, 16, 16, mouseX, mouseY)) {
				renderToolTip(buyStack1, mouseX, mouseY);
			}
			else if (!buyStack2.isEmpty() && isPointInRegion(62, 24, 16, 16, mouseX, mouseY)) {
				renderToolTip(buyStack2, mouseX, mouseY);
			}
			else if (!sellStack.isEmpty() && isPointInRegion(120, 24, 16, 16, mouseX, mouseY)) {
				renderToolTip(sellStack, mouseX, mouseY);
			}
			else if (trade.isRecipeDisabled() && (isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
				drawHoveringText(I18n.format("merchant.deprecated"), mouseX, mouseY);
			}

			GlStateManager.popMatrix();
			GlStateManager.enableLighting();
			GlStateManager.enableDepth();
			RenderHelper.enableStandardItemLighting();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);

		if (titleWidth > xSize - 28 && trader instanceof Entity) {
			guiTitle = StringUtil.getLocaleString("entity." + EntityList.getEntityString((Entity)trader) + ".name");
			titleWidth = 4 + mc.fontRenderer.getStringWidth(guiTitle);
		}

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(28, 4, 212, 21, 1, 12);

		for (int i = 0; i < titleWidth - 2; i++) {
			drawTexturedModalRect(29 + i, 4, 213, 21, 1, 12);
		}

		drawTexturedModalRect(28 + titleWidth - 2, 4, 214, 21, 1, 12);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.fontRenderer.drawString(guiTitle, 30, 6, Enums.RGBIntegers.WHITE);
	}

	@SideOnly(Side.CLIENT)
	private class TraderButton extends GuiButton {
		private final ResourceLocation buttonTexture;
		private final boolean isNextButton;

		private TraderButton(int buttonId, int x, int y, boolean isNextButton, ResourceLocation textureResource) {
			super(buttonId, x, y, 12, 19, "");
			this.isNextButton = isNextButton;
			this.buttonTexture = textureResource;
			enabled = false;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				mc.getTextureManager().bindTexture(buttonTexture);
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

				int textureXIndex = 176;

				if (!enabled) {
					textureXIndex += width * 2;
				}
				else if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height) {
					textureXIndex += width;
				}

				drawTexturedModalRect(x, y, textureXIndex, isNextButton ? 0 : height, width, height);
			}
		}
	}
}
