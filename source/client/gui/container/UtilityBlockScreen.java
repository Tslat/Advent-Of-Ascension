package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.container.UtilityBlockContainer;
import net.tslat.aoa3.util.RenderUtil;

public class UtilityBlockScreen extends AbstractContainerScreen<UtilityBlockContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/basic_block.png");

	public UtilityBlockScreen(UtilityBlockContainer container, Inventory inv, Component guiTitle) {
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
		UtilityBlockContainer container = getMenu();
		PoseStack poseStack = guiGraphics.pose();

		RenderUtil.prepRenderTexture(textures);
		RenderUtil.renderCustomSizedTexture(poseStack, leftPos, topPos, 0, 0, 175, 141, 256, 256);

		if ((!container.inputs.getItem(0).isEmpty() || !container.inputs.getItem(1).isEmpty()) && container.output.getItem(0).isEmpty())
			RenderUtil.renderCustomSizedTexture(poseStack, leftPos + 99, topPos + 21, imageWidth, 0, 28, 21, 256, 256);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		RenderUtil.renderCenteredScaledText(guiGraphics.pose(), getTitle(), 88, 6, 1, 4210752, RenderUtil.TextRenderType.NORMAL);
	}
}
