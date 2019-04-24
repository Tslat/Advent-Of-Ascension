package net.nevermine.gui.pouch;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiRunePouch extends GuiContainer {
	private static final ResourceLocation background;
	private IInventory playerInv;
	private IInventory lowerChestInventory;
	private int inventoryRows;

	public GuiRunePouch(final IInventory player, final IInventory p_i1083_2_) {
		super(new ContainerRunePouch(player, p_i1083_2_));
		playerInv = player;
		lowerChestInventory = p_i1083_2_;
		allowUserInput = false;
		final short short1 = 222;
		final int i = short1 - 108;
		inventoryRows = p_i1083_2_.getSizeInventory() / 9;
		ySize = i + inventoryRows * 18;
	}

	protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
		fontRendererObj.drawString(lowerChestInventory.hasCustomInventoryName() ? lowerChestInventory.getInventoryName() : I18n.format(lowerChestInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
		fontRendererObj.drawString(playerInv.hasCustomInventoryName() ? playerInv.getInventoryName() : I18n.format(playerInv.getInventoryName(), new Object[0]), 8, ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(GuiRunePouch.background);
		final int k = (width - xSize) / 2;
		final int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(k, l + inventoryRows * 18 + 17, 0, 126, xSize, 96);
	}

	static {
		background = new ResourceLocation("textures/gui/container/generic_54.png");
	}
}
