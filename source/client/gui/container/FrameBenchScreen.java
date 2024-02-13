package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.menu.FrameBenchMenu;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.RenderUtil;

import java.util.function.Supplier;

public class FrameBenchScreen extends AbstractContainerScreen<FrameBenchMenu> {
	private static final ResourceLocation TEXTURES = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");

	public FrameBenchScreen(FrameBenchMenu container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		this.titleLabelY -= 2;
	}

	@Override
	public void init() {
		super.init();

		addRenderableWidget(new FrameSelectButton(this, 0, this.leftPos + 55, this.topPos + 13, AoAItems.CROSSBOW_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 1, this.leftPos + 75, this.topPos + 13, AoAItems.BLASTER_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 2, this.leftPos + 95, this.topPos + 13, AoAItems.CANNON_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 3, this.leftPos + 45, this.topPos + 33, AoAItems.HELMET_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 4, this.leftPos + 65, this.topPos + 33, AoAItems.CHESTPLATE_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 5, this.leftPos + 85, this.topPos + 33, AoAItems.LEGGINGS_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 6, this.leftPos + 105,this.topPos + 33, AoAItems.BOOTS_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 7, this.leftPos + 55, this.topPos + 53, AoAItems.GUN_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 8, this.leftPos + 75, this.topPos + 53, AoAItems.SHOTGUN_FRAME));
		addRenderableWidget(new FrameSelectButton(this, 9, this.leftPos + 95, this.topPos + 53, AoAItems.SNIPER_FRAME));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderUtil.resetShaderColour();
		RenderUtil.prepRenderTexture(TEXTURES);
		RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), leftPos, topPos, 0, 0, 175, 165, 256, 256);
	}

	private static class FrameSelectButton extends Button {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final FrameBenchScreen screen;
		private final Item frame;
		private final int selectionId;

		private FrameSelectButton(FrameBenchScreen screen, int selectionId, int x, int y, Supplier<Item> frame) {
			super(x, y, buttonWidth, buttonHeight, Component.translatable(frame.get().getDescriptionId()), button -> {}, DEFAULT_NARRATION);

			this.screen = screen;
			this.frame = frame.get();
			this.selectionId = selectionId;

			setTooltip(Tooltip.create(this.frame.getDefaultInstance().getHoverName()));
		}

		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();
			PoseStack poseStack = guiGraphics.pose();

			poseStack.pushPose();
			RenderUtil.prepRenderTexture(TEXTURES);
			RenderUtil.resetShaderColour();

			isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
			int textureX = 176;
			int textureY = 21 + buttonHeight * (this.selectionId == this.screen.menu.getCurrentSelection() ? 0 : RenderUtil.selectVForWidgetState(this, 1, 1, 2));

			RenderUtil.renderCustomSizedTexture(poseStack, getX(), getY(), textureX, textureY, buttonWidth, buttonHeight, 256, 256);
			poseStack.translate(0, 0, 32);
			guiGraphics.renderItem(new ItemStack(frame), getX() + 1, getY() + 1);
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
			poseStack.popPose();
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
		}

		@Override
		protected boolean clicked(double mouseX, double mouseY) {
			return active && isHovered && this.selectionId != this.screen.menu.getCurrentSelection();
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			if (this.screen.menu.clickMenuButton(Minecraft.getInstance().player, this.selectionId))
				Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.screen.menu.containerId, this.selectionId);
		}
	}
}
