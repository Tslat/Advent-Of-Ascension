package net.tslat.aoa3.client.gui.blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.containers.ContainerBasicUtility;

@SideOnly(Side.CLIENT)
public class BasicBlockGui extends GuiContainer {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/basic_block_gui.png");

	private String guiTitle;

	public BasicBlockGui(EntityPlayer player, String title, Container container) {
		super(container);

		guiTitle = title;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		ContainerBasicUtility container = (ContainerBasicUtility)inventorySlots;

		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(textures);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if ((!container.inputs.getStackInSlot(0).isEmpty() || !container.inputs.getStackInSlot(1).isEmpty()) && container.output.getStackInSlot(0).isEmpty())
			drawTexturedModalRect(guiLeft + 99, guiTop + 21, xSize, 0, 28, 21);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(guiTitle, 88 - (mc.fontRenderer.getStringWidth(guiTitle) / 2), 6, 4210752);
	}
}
