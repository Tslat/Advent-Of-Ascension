package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.util.RenderUtil;

public class DivineStationScreen extends AbstractContainerScreen<DivineStationContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/basic_block.png");

	public DivineStationScreen(DivineStationContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
		DivineStationContainer container = getMenu();

		RenderUtil.prepRenderTexture(textures);
		RenderUtil.resetShaderColour();
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 141, 256, 256);

		if ((!container.inputs.getItem(0).isEmpty() || !container.inputs.getItem(1).isEmpty()) && container.output.getItem(0).isEmpty())
			RenderUtil.renderCustomSizedTexture(matrix, leftPos + 99, topPos + 21, imageWidth, 0, 28, 21, 256, 256);
	}

	@Override
	protected void renderLabels(PoseStack matrix, int mouseX, int mouseY) {
		RenderUtil.drawCenteredScaledMessage(matrix, Minecraft.getInstance().font, getTitle(), 88, 6, 1, 4210752, RenderUtil.StringRenderType.NORMAL);
	}
}
