package net.tslat.aoa3.client.gui.container;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.menu.DivineStationMenu;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.RenderUtil;

public class DivineStationScreen extends AbstractContainerScreen<DivineStationMenu> {
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("aoa3", "textures/gui/containers/basic_block.png");

	public DivineStationScreen(DivineStationMenu container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		DivineStationMenu container = getMenu();
		RenderContext renderContext = RenderContext.of(guiGraphics);

		renderContext.setTextureForRendering(GUI_TEXTURE);
		renderContext.resetShaderColour();
		renderContext.renderCustomSizedTexture(this.leftPos, this.topPos, 0, 0, 175, 141, 256, 256);

		if ((!container.getInputItem(0).isEmpty() || !container.getInputItem(1).isEmpty()) && container.getOutputItem().isEmpty())
			renderContext.renderCustomSizedTexture(this.leftPos + 99, this.topPos + 21, this.imageWidth, 0, 28, 21, 256, 256);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		RenderContext.of(guiGraphics).renderCenteredScaledText(getTitle(), 88, 6, 1, 4210752, RenderUtil.TextRenderType.NORMAL);
	}
}
