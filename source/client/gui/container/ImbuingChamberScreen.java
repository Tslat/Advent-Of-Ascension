package net.tslat.aoa3.client.gui.container;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.content.item.misc.PowerStone;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.List;

public class ImbuingChamberScreen extends AbstractContainerScreen<ImbuingChamberMenu> {
	private static final ResourceLocation TEXTURES = new ResourceLocation("aoa3", "textures/gui/containers/imbuing_chamber.png");

	public ImbuingChamberScreen(ImbuingChamberMenu container, Inventory inv, Component guiTitle) {
		super(container, inv, guiTitle);
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xB2B2B2, false);
		guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 1, 0xB2B2B2, false);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		final ImbuingChamberMenu menu = getMenu();

		RenderUtil.prepRenderTexture(TEXTURES);
		RenderUtil.resetShaderColour();
		RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		int activationColour = renderBeam(guiGraphics, menu, partialTicks, mouseX, mouseY);
		RenderUtil.resetShaderColour();

		for (int i = 0; i < menu.inputSlotCount(); i++) {
			final Slot slot = menu.getSlot(i);

			RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos + slot.x - 1, this.topPos + slot.y - 1, 26, 166, 18, 18, 256, 256);
		}

		if (activationColour >= 0) {
			RenderSystem.setShaderColor(ColourUtil.getRed(activationColour) / 255f, ColourUtil.getGreen(activationColour) / 255f, ColourUtil.getBlue(activationColour) / 255f, ClientOperations.getPlayer().getRandom().nextFloat() * 0.2f + 0.8f);
			RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos + 134, this.topPos + 30, 0, 166, 26, 26, 256, 256);
			RenderUtil.resetShaderColour();
			RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos + 138, this.topPos + 34, 4, 170, 18, 18, 256, 256);

			((ImbuingChamberMenu.ImbuingResultSlot)getMenu().getOutputSlot()).getCurrentRecipe().ifPresent(recipe -> {
				final Component enchant = recipe.getEnchant().left().getFullname(recipe.getEnchant().rightInt());

				RenderUtil.drawRectangle(guiGraphics.pose(), this.leftPos + 16, this.topPos + 59, Minecraft.getInstance().font.width(enchant) + 1, 10, 0xCC000000);
				RenderUtil.renderText(guiGraphics.pose(), enchant, this.leftPos + 17, this.topPos + 60, 0xB2B2B2, RenderUtil.TextRenderType.NORMAL);
			});
		}
		else {
			RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos + 134, this.topPos + 30, 0, 166, 26, 26, 256, 256);
		}
	}

	private int renderBeam(GuiGraphics guiGraphics, ImbuingChamberMenu menu, float partialTicks, int mouseX, int mouseY) {
		final List<ItemStack> contents = menu.getItems();

		if (!(contents.get(0).getItem() instanceof PowerStone powerStone))
			return -1;

		int colour = powerStone.getColour();
		int currentIndex = 1;
		int lastIndex = 0;
		ItemStack slotStack;

		do {
			if (!(slotStack = menu.getInputItem(currentIndex)).isEmpty() || currentIndex == 6) {
				int start = lastIndex * 18;
				float width = (currentIndex - lastIndex) * 18 - 2;

				RenderSystem.setShaderColor(ColourUtil.getRed(colour) / 255f, ColourUtil.getGreen(colour) / 255f, ColourUtil.getBlue(colour) / 255f, ClientOperations.getPlayer().getRandom().nextFloat() * 0.4f + 0.6f);
				RenderUtil.renderCustomSizedTexture(guiGraphics.pose(), this.leftPos + 32 + start, this.topPos + 36, 44 + start, 166, width, 14, 256, 256);

				if (slotStack.getItem() instanceof AspectFocusItem focus)
					colour = ColourUtil.lerpColour(colour, focus.getFocus().colour(), 0.75f);

				lastIndex = currentIndex;
			}

			currentIndex++;
		} while (currentIndex < contents.size());

		return colour;
	}
}
