package net.tslat.aoa3.client.gui.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.containers.ContainerFrameBench;
import net.tslat.aoa3.common.packet.PacketGuiData;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.FrameItem;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.Collections;

@SideOnly(Side.CLIENT)
public class GuiFrameBench extends GuiContainer {
	private static final ResourceLocation textures = new ResourceLocation("aoa3", "textures/gui/containers/frame_bench.png");

	private static String currentSelection = "helmet";
	private static RenderItem itemRenderer;

	public GuiFrameBench(EntityPlayer player) {
		super(new ContainerFrameBench(player, player.world, BlockPos.ORIGIN));

		currentSelection = "helmet";
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.add(new FrameSelectButton(0, guiLeft + 45, guiTop + 33, "Helmet", "helmet", ItemRegister.HELMET_FRAME));
		buttonList.add(new FrameSelectButton(1, guiLeft + 65, guiTop + 33, "Chestplate", "chestplate", ItemRegister.CHESTPLATE_FRAME));
		buttonList.add(new FrameSelectButton(2, guiLeft + 85, guiTop + 33, "Leggings", "leggings", ItemRegister.LEGGINGS_FRAME));
		buttonList.add(new FrameSelectButton(3, guiLeft + 105, guiTop + 33, "Boots", "boots", ItemRegister.BOOTS_FRAME));
		buttonList.add(new FrameSelectButton(4, guiLeft + 55, guiTop + 13, "Archergun", "archergun", ItemRegister.ARCHERGUN_FRAME));
		buttonList.add(new FrameSelectButton(5, guiLeft + 75, guiTop + 13, "Blaster", "blaster", ItemRegister.BLASTER_FRAME));
		buttonList.add(new FrameSelectButton(6, guiLeft + 95, guiTop + 13, "Cannon", "cannon", ItemRegister.CANNON_FRAME));
		buttonList.add(new FrameSelectButton(7, guiLeft + 55, guiTop + 53, "Gun", "gun", ItemRegister.GUN_FRAME));
		buttonList.add(new FrameSelectButton(8, guiLeft + 75, guiTop + 53, "Shotgun", "shotgun", ItemRegister.SHOTGUN_FRAME));
		buttonList.add(new FrameSelectButton(9, guiLeft + 95, guiTop + 53, "Sniper", "sniper", ItemRegister.SNIPER_FRAME));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);

		for (GuiButton button : buttonList) {
			if (button.isMouseOver() && button instanceof FrameSelectButton) {
				((FrameSelectButton)button).renderHoverTooltip(mc, mouseX, mouseY);

				break;
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(textures);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button instanceof FrameSelectButton && mc.player.openContainer instanceof ContainerFrameBench) {
			currentSelection = ((FrameSelectButton)button).selectionValue;
			PacketUtil.network.sendToServer(new PacketGuiData("FrameBenchSelect", currentSelection));
			((ContainerFrameBench)mc.player.openContainer).changeSelection(currentSelection);
		}
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		super.setWorldAndResolution(mc, width, height);

		itemRenderer = itemRender;
	}

	private static class FrameSelectButton extends GuiButton {
		private static final int buttonWidth = 18;
		private static final int buttonHeight = 18;

		private final String selectionValue;
		private final FrameItem frame;

		private FrameSelectButton(int buttonId, int x, int y, String buttonText, String selectionValue, FrameItem frame) {
			super(buttonId, x, y, buttonWidth, buttonHeight, buttonText);

			this.selectionValue = selectionValue;
			this.frame = frame;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			mc.getTextureManager().bindTexture(textures);
			GlStateManager.pushMatrix();
			GlStateManager.color(1f, 1f, 1f, 1f);

			hovered = isMouseInRegion(mouseX, mouseY, x, y);
			int textureX = 176;
			int textureY = 21 + buttonHeight * (selectionValue.equals(currentSelection) ? 0 : (getHoverState(this.hovered) == 2) ? 2 : 1);

			drawTexturedModalRect(x, y, textureX, textureY, buttonWidth, buttonHeight);
			GlStateManager.translate(0, 0, 32);
			itemRenderer.renderItemIntoGUI(new ItemStack(frame), x + 1, y + 1);
			GlStateManager.popMatrix();
			GlStateManager.color(1f, 1f, 1f, 1f);
		}

		public void renderHoverTooltip(Minecraft mc, int mouseX, int mouseY) {
			ItemStack stack = new ItemStack(frame);
			ScaledResolution res = new ScaledResolution(mc);

			GuiUtils.drawHoveringText(stack, Collections.singletonList(StringUtil.getLocaleString(frame.getTranslationKey() + ".name")), mouseX, mouseY, res.getScaledWidth(), res.getScaledHeight(), -1, mc.fontRenderer);
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
		}

		@Override
		public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
			return enabled && hovered && !currentSelection.equals(selectionValue);
		}
	}
}
