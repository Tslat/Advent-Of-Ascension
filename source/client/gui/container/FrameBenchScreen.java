package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.container.FrameBenchContainer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GuiDataPacket;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

public class FrameBenchScreen extends AbstractContainerScreen<FrameBenchContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");
	private static FrameBenchScreen instance = null;

	private static String currentSelection = "helmet";

	public FrameBenchScreen(FrameBenchContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		currentSelection = "helmet";
		instance = this;
	}

	@Override
	public void init() {
		super.init();

		addRenderableWidget(new FrameSelectButton(leftPos + 45, topPos + 33, "helmet", AoAItems.HELMET_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 65, topPos + 33, "chestplate", AoAItems.CHESTPLATE_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 85, topPos + 33, "leggings", AoAItems.LEGGINGS_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 105, topPos + 33, "boots", AoAItems.BOOTS_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 55, topPos + 13, "crossbow", AoAItems.CROSSBOW_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 75, topPos + 13, "blaster", AoAItems.BLASTER_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 95, topPos + 13, "cannon", AoAItems.CANNON_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 55, topPos + 53, "gun", AoAItems.GUN_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 75, topPos + 53, "shotgun", AoAItems.SHOTGUN_FRAME.get()));
		addRenderableWidget(new FrameSelectButton(leftPos + 95, topPos + 53, "sniper", AoAItems.SNIPER_FRAME.get()));
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
		RenderUtil.resetShaderColour();
		RenderUtil.prepRenderTexture(textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 165, 256, 256);
	}

	private static class FrameSelectButton extends Button {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final String selectionValue;
		private final Item frame;

		private FrameSelectButton(int x, int y, String selectionValue, Item frame) {
			super(x, y, buttonWidth, buttonHeight, Component.translatable(frame.getDescriptionId()), button -> {}, DEFAULT_NARRATION);

			this.selectionValue = selectionValue;
			this.frame = frame;

			setTooltip(Tooltip.create(Component.literal(LocaleUtil.getItemName(this.frame))));
		}

		@Override
		public void renderButton(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();


			matrix.pushPose();
			RenderUtil.prepRenderTexture(textures);
			RenderUtil.resetShaderColour();

			isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
			int textureX = 176;
			int textureY = 21 + buttonHeight * (selectionValue.equals(currentSelection) ? 0 : (getYImage(this.isHovered) == 2) ? 2 : 1);

			RenderUtil.renderCustomSizedTexture(matrix, getX(), getY(), textureX, textureY, buttonWidth, buttonHeight, 256, 256);
			matrix.translate(0, 0, 32);
			mc.getItemRenderer().renderGuiItem(new ItemStack(frame), getX() + 1, getY() + 1);
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
			matrix.popPose();
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered && !selectionValue.equals(currentSelection);
		}

		@Override
		public void onClick(double p_onClick_1_, double p_onClick_3_) {
			currentSelection = selectionValue;

			AoAPackets.messageServer(new GuiDataPacket(GuiDataPacket.Type.FRAME_BENCH_SELECTION, currentSelection));
			((FrameBenchScreen)Minecraft.getInstance().screen).getMenu().changeSelection(currentSelection);
		}
	}
}
