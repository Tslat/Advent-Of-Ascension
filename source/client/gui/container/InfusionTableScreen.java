package net.tslat.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.util.RenderUtil;

public class InfusionTableScreen extends AbstractContainerScreen<InfusionTableContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/infusion_table.png");

	public InfusionTableScreen(InfusionTableContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderUtil.prepRenderTexture(textures);
		RenderUtil.resetShaderColour();
		RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
