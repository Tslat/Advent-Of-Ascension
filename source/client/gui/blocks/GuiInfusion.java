package net.tslat.aoa3.client.gui.blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.containers.ContainerInfusionTable;

@SideOnly(Side.CLIENT)
public class GuiInfusion extends GuiContainer {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/infusion_table.png");

	public GuiInfusion(EntityPlayer player) {
		super(new ContainerInfusionTable(player, player.world, BlockPos.ORIGIN));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f,1f, 1f, 1f);
		mc.getTextureManager().bindTexture(textures);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
