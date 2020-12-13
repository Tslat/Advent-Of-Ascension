package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.util.RenderUtil;

public class InfusionTableScreen extends ContainerScreen<InfusionTableContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/infusion_table.png");

	public InfusionTableScreen(InfusionTableContainer container, PlayerInventory inv, ITextComponent guiTitle) {
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
		RenderSystem.color4f(1f,1f, 1f, 1f);
		Minecraft.getInstance().getTextureManager().bindTexture(textures);
		RenderUtil.renderCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, 256, 256);
	}
}
