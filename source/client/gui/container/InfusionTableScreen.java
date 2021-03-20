package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
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
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f,1f, 1f, 1f);
		Minecraft.getInstance().getTextureManager().bind(textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
