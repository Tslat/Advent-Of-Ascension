package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.common.container.DivineStationContainer;
import net.tslat.aoa3.util.RenderUtil;

public class DivineStationScreen extends ContainerScreen<DivineStationContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/basic_block.png");

	public DivineStationScreen(DivineStationContainer container, PlayerInventory inv, ITextComponent guiTitle) {
		super(container, inv, guiTitle);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		DivineStationContainer container = getContainer();

		RenderSystem.color4f(1f, 1f, 1f, 1f);
		Minecraft.getInstance().getTextureManager().bindTexture(textures);
		RenderUtil.renderCustomSizedTexture(guiLeft, guiTop, 0, 0, 175, 141, 256, 256);

		if ((!container.inputs.getStackInSlot(0).isEmpty() || !container.inputs.getStackInSlot(1).isEmpty()) && container.output.getStackInSlot(0).isEmpty())
			RenderUtil.renderCustomSizedTexture(guiLeft + 99, guiTop + 21, xSize, 0, 28, 21, 256, 256);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		RenderUtil.drawCenteredScaledString(Minecraft.getInstance().fontRenderer, getTitle().getFormattedText(), 88, 6, 1, 4210752, RenderUtil.StringRenderType.NORMAL);
	}
}
