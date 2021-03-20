package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.tslat.aoa3.common.container.FrameBenchContainer;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GuiDataPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.Collections;

public class FrameBenchScreen extends ContainerScreen<FrameBenchContainer> {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");

	private static String currentSelection = "helmet";

	public FrameBenchScreen(FrameBenchContainer container, PlayerInventory inv, ITextComponent guiTitle) {
		super(container, inv, guiTitle);

		currentSelection = "helmet";
	}

	@Override
	public void init() {
		super.init();

		addButton(new FrameSelectButton(leftPos + 45, topPos + 33, "Helmet", "helmet", AoAItems.HELMET_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 65, topPos + 33, "Chestplate", "chestplate", AoAItems.CHESTPLATE_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 85, topPos + 33, "Leggings", "leggings", AoAItems.LEGGINGS_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 105, topPos + 33, "Boots", "boots", AoAItems.BOOTS_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 55, topPos + 13, "Crossbow", "crossbow", AoAItems.CROSSBOW_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 75, topPos + 13, "Blaster", "blaster", AoAItems.BLASTER_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 95, topPos + 13, "Cannon", "cannon", AoAItems.CANNON_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 55, topPos + 53, "Gun", "gun", AoAItems.GUN_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 75, topPos + 53, "Shotgun", "shotgun", AoAItems.SHOTGUN_FRAME.get()));
		addButton(new FrameSelectButton(leftPos + 95, topPos + 53, "Sniper", "sniper", AoAItems.SNIPER_FRAME.get()));
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);
		super.render(matrix, mouseX, mouseY, partialTicks);
		renderTooltip(matrix, mouseX, mouseY);

		for (Widget button : buttons) {
			if (button.isHovered())
				button.renderToolTip(matrix, mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		Minecraft.getInstance().getTextureManager().bind(textures);
		RenderUtil.renderCustomSizedTexture(matrix, leftPos, topPos, 0, 0, 175, 165, 256, 256);
	}

	private static class FrameSelectButton extends Widget {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final String selectionValue;
		private final Item frame;

		private FrameSelectButton(int x, int y, String buttonText, String selectionValue, Item frame) {
			super(x, y, buttonWidth, buttonHeight, new TranslationTextComponent(frame.getDescriptionId()));

			this.selectionValue = selectionValue;
			this.frame = frame;
		}

		@Override
		public void renderButton(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
			Minecraft mc = Minecraft.getInstance();

			mc.getTextureManager().bind(textures);
			matrix.pushPose();
			RenderSystem.color4f(1f, 1f, 1f, 1f);

			isHovered = isMouseInRegion(mouseX, mouseY, x, y);
			int textureX = 176;
			int textureY = 21 + buttonHeight * (selectionValue.equals(currentSelection) ? 0 : (getYImage(this.isHovered) == 2) ? 2 : 1);

			RenderUtil.renderCustomSizedTexture(matrix, x, y, textureX, textureY, buttonWidth, buttonHeight, 256, 256);
			matrix.translate(0, 0, 32);
			mc.getItemRenderer().renderGuiItem(new ItemStack(frame), x + 1, y + 1);
			RenderSystem.color4f(1f, 1f, 1f, 1f);
			matrix.popPose();
		}

		@Override
		public void renderToolTip(MatrixStack matrix, int mouseX, int mouseY) {
			ItemStack stack = new ItemStack(frame);
			MainWindow window = Minecraft.getInstance().getWindow();

			RenderSystem.color4f(1f, 1f, 1f, 1f);
			GuiUtils.drawHoveringText(matrix, Collections.singletonList(new StringTextComponent(LocaleUtil.getItemName(frame))), mouseX, mouseY, window.getGuiScaledWidth(), window.getGuiScaledHeight(), -1, Minecraft.getInstance().font);
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
