package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.util.RenderUtil;

public class Generic2SlotContainerScreen<C extends ExtensibleContainerMenu<? extends Container>> extends AbstractContainerScreen<C> {
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("aoa3", "textures/gui/containers/basic_block.png");

	public Generic2SlotContainerScreen(C container, Inventory inv, Component guiTitle) {
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
		C container = getMenu();
		PoseStack poseStack = guiGraphics.pose();

		RenderUtil.prepRenderTexture(BACKGROUND_TEXTURE);
		RenderUtil.renderCustomSizedTexture(poseStack, this.leftPos, this.topPos, 0, 0, 175, 141, 256, 256);

		if ((!container.getInventory().getItem(0).isEmpty() || !container.getInventory().getItem(1).isEmpty()) && !container.getOutputSlot().hasItem())
			RenderUtil.renderCustomSizedTexture(poseStack, this.leftPos + 99, this.topPos + 21, this.imageWidth, 0, 28, 21, 256, 256);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		RenderUtil.renderCenteredScaledText(guiGraphics.pose(), getTitle(), 88, 6, 1, 0x404040, RenderUtil.TextRenderType.NORMAL);
	}
}
