package net.nevermine.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

@SideOnly(Side.CLIENT)
public class GuiEternalMerchant extends GuiContainer {
	private static final Logger logger;
	private IMerchant theIMerchant;
	private ModMerchantButton nextRecipeButtonIndex;
	private ModMerchantButton previousRecipeButtonIndex;
	private int currentRecipeIndex;
	protected String name;
	private String texture;

	public GuiEternalMerchant(final ContainerEternalMerchant container, final IMerchant mer, final String name, final String tex) {
		super(container);
		currentRecipeIndex = 0;
		theIMerchant = mer;
		texture = tex;
		this.name = name;
	}

	public void initGui() {
		super.initGui();
		final int var1 = (width - xSize) / 2;
		final int var2 = (height - ySize) / 2;
		buttonList.add(nextRecipeButtonIndex = new ModMerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true, texture));
		buttonList.add(previousRecipeButtonIndex = new ModMerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false, texture));
		nextRecipeButtonIndex.enabled = false;
		previousRecipeButtonIndex.enabled = false;
	}

	protected void drawGuiContainerForegroundLayer(final int var1, final int var2) {
	}

	public void updateScreen() {
		super.updateScreen();
		if (Minecraft.getMinecraft().thePlayer != null) {
			final MerchantRecipeList var1 = theIMerchant.getRecipes(Minecraft.getMinecraft().thePlayer);
			if (var1 != null) {
				nextRecipeButtonIndex.enabled = (currentRecipeIndex < var1.size() - 1);
				previousRecipeButtonIndex.enabled = (currentRecipeIndex > 0);
			}
		}
	}

	protected void actionPerformed(final GuiButton var1) {
		boolean var2 = false;
		if (var1 == nextRecipeButtonIndex) {
			++currentRecipeIndex;
			var2 = true;
		}
		else if (var1 == previousRecipeButtonIndex) {
			--currentRecipeIndex;
			var2 = true;
		}
		if (var2) {
			((ContainerEternalMerchant)inventorySlots).setCurrentRecipeIndex(currentRecipeIndex);
			final ByteArrayOutputStream var3 = new ByteArrayOutputStream();
			final DataOutputStream var4 = new DataOutputStream(var3);
			try {
				var4.writeInt(currentRecipeIndex);
				mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|TrSel", var3.toByteArray()));
			} catch (Exception var5) {
				GuiEternalMerchant.logger.error("Couldn't send trade info", var5);
			}
		}
	}

	protected void drawGuiContainerBackgroundLayer(final float f, final int x, final int y) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/" + texture + ".png"));
		final int k = (width - xSize) / 2;
		final int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		if (Minecraft.getMinecraft().thePlayer != null) {
			final MerchantRecipeList merchantrecipelist = theIMerchant.getRecipes(Minecraft.getMinecraft().thePlayer);
			if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
				final int i1 = currentRecipeIndex;
				final MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
				if (merchantrecipe.isRecipeDisabled()) {
					mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/" + texture + ".png"));
					GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
					GL11.glDisable(2896);
					drawTexturedModalRect(guiLeft + 83, guiTop + 21, 212, 0, 28, 21);
					drawTexturedModalRect(guiLeft + 83, guiTop + 51, 212, 0, 28, 21);
				}
			}
		}
	}

	public void drawScreen(final int par1, final int par2, final float par3) {
		super.drawScreen(par1, par2, par3);
		if (Minecraft.getMinecraft().thePlayer != null) {
			final MerchantRecipeList merchantrecipelist = theIMerchant.getRecipes(Minecraft.getMinecraft().thePlayer);
			if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
				final int k = (width - xSize) / 2;
				final int l = (height - ySize) / 2;
				final int i1 = currentRecipeIndex;
				final MerchantRecipe merchantrecipe = (MerchantRecipe)merchantrecipelist.get(i1);
				GL11.glPushMatrix();
				final ItemStack itemstack = merchantrecipe.getItemToBuy();
				final ItemStack itemstack2 = merchantrecipe.getSecondItemToBuy();
				final ItemStack itemstack3 = merchantrecipe.getItemToSell();
				RenderHelper.enableGUIStandardItemLighting();
				GL11.glDisable(2896);
				GL11.glEnable(32826);
				GL11.glEnable(2903);
				GL11.glEnable(2896);
				GuiEternalMerchant.itemRender.zLevel = 100.0f;
				GuiEternalMerchant.itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack, k + 36, l + 24);
				GuiEternalMerchant.itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack, k + 36, l + 24);
				if (itemstack2 != null) {
					GuiEternalMerchant.itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack2, k + 62, l + 24);
					GuiEternalMerchant.itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack2, k + 62, l + 24);
				}
				GuiEternalMerchant.itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack3, k + 120, l + 24);
				GuiEternalMerchant.itemRender.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), itemstack3, k + 120, l + 24);
				GuiEternalMerchant.itemRender.zLevel = 0.0f;
				GL11.glDisable(2896);
				if (func_146978_c(36, 24, 16, 16, par1, par2)) {
					renderToolTip(itemstack, par1, par2);
				}
				else if (itemstack2 != null && func_146978_c(62, 24, 16, 16, par1, par2)) {
					renderToolTip(itemstack2, par1, par2);
				}
				else if (func_146978_c(120, 24, 16, 16, par1, par2)) {
					renderToolTip(itemstack3, par1, par2);
				}
				GL11.glPopMatrix();
				GL11.glEnable(2896);
				GL11.glEnable(2929);
				RenderHelper.enableStandardItemLighting();
			}
		}
	}

	public IMerchant getIMerchant() {
		return theIMerchant;
	}

	static {
		logger = LogManager.getLogger();
	}

	@SideOnly(Side.CLIENT)
	static class ModMerchantButton extends GuiButton {
		private final boolean rev;
		private static String texture;

		public ModMerchantButton(final int par1, final int par2, final int par3, final boolean par4, final String tex) {
			super(par1, par2, par3, 12, 19, "");
			ModMerchantButton.texture = tex;
			rev = par4;
		}

		public void drawButton(final Minecraft mc, final int x, final int y) {
			if (visible) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/" + ModMerchantButton.texture + ".png"));
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				final boolean flag = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
				int k = 0;
				int l = 176;
				if (!enabled) {
					l += width * 2;
				}
				else if (flag) {
					l += width;
				}
				if (!rev) {
					k += height;
				}
				drawTexturedModalRect(xPosition, yPosition, l, k, width, height);
			}
		}
	}
}
