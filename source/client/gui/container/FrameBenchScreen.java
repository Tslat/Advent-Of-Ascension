package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tslat.aoa3.common.container.FrameBenchContainer;

public class FrameBenchScreen extends AbstractContainerScreen<FrameBenchContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");

	private static String currentSelection = "helmet";

	public FrameBenchScreen(FrameBenchContainer container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);

		currentSelection = "helmet";
	}

	@Override
	public void init() {
		super.init();

		/*addButton(new FrameSelectButton(leftPos + 45, topPos + 33, "Helmet", "helmet", AoAItems.HELMET_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 65, topPos + 33, "Chestplate", "chestplate", AoAItems.CHESTPLATE_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 85, topPos + 33, "Leggings", "leggings", AoAItems.LEGGINGS_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 105, topPos + 33, "Boots", "boots", AoAItems.BOOTS_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 55, topPos + 13, "Crossbow", "crossbow", AoAItems.CROSSBOW_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 75, topPos + 13, "Blaster", "blaster", AoAItems.BLASTER_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 95, topPos + 13, "Cannon", "cannon", AoAItems.CANNON_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 55, topPos + 53, "Gun", "gun", AoAItems.GUN_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 75, topPos + 53, "Shotgun", "shotgun", AoAItems.SHOTGUN_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 95, topPos + 53, "Sniper", "sniper", AoAItems.SNIPER_FRAME.get()));*/
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		/*renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);

		for (Widget button : buttons) {
			if (button.isHovered())
				button.renderToolTip(matrix, mouseX, mouseY);
		}*/
	}

	@Override
	protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
		/*RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		Minecraft.getInstance().getTextureManager().bind(textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 165, 256, 256);*/
	}

	/*private static class FrameSelectButton implements Widget {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final String selectionValue;
		private final Item frame;

		private FrameSelectButton(int x, int y, String buttonText, String selectionValue, Item frame) {
			super(x, y, buttonWidth, buttonHeight, new TranslatableComponent(frame.getDescriptionId()));

			this.selectionValue = selectionValue;
			this.frame = frame;
		}

		@Override
		public void renderButton(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();

			mc.getTextureManager().bind(textures);
			matrix.pushPose();
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

			isHovered = isMouseInRegion(mouseX, mouseY, x, y);
			int textureX = 176;
			int textureY = 21 + buttonHeight * (selectionValue.equals(currentSelection) ? 0 : (getYImage(this.isHovered) == 2) ? 2 : 1);

			RenderUtil.renderCustomSizedTexture(matrix, x, y, textureX, textureY, buttonWidth, buttonHeight, 256, 256);
			matrix.translate(0, 0, 32);
			mc.getItemRenderer().renderGuiItem(new ItemStack(frame), x + 1, y + 1);
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
			matrix.popPose();
		}

		@Override
		public void renderToolTip(PoseStack matrix, int mouseX, int mouseY) {
			ItemStack stack = new ItemStack(frame);
			Window window = Minecraft.getInstance().getWindow();

			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
			GuiUtils.drawHoveringText(matrix, Collections.singletonList(new TextComponent(LocaleUtil.getItemName(frame))), mouseX, mouseY, window.getGuiScaledWidth(), window.getGuiScaledHeight(), -1, Minecraft.getInstance().font);
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
	}*/
}
