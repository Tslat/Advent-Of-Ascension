package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.PatchouliBookOpenPacket;
import net.tslat.aoa3.common.packet.packets.PatchouliGiveBookPacket;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdventGuiTabLore extends Screen {
	private int adjustedMouseX;
	private int adjustedMouseY;

	private static final ConcurrentHashMap<ResourceLocation, ItemStack> loreBooks = new ConcurrentHashMap<ResourceLocation, ItemStack>();

	protected AdventGuiTabLore() {
		super(new TranslationTextComponent("gui.aoa3.adventGui.lore"));
	}

	@Override
	protected void init() {
		int index = 0;
		int accumWidth = 0;
		int height = AdventMainGui.scaledTabRootY + 20;

		for (Map.Entry<ResourceLocation, ItemStack> bookEntry : loreBooks.entrySet()) {
			int width = (int)(font.width(bookEntry.getValue().getHoverName()) * 1.5f);

			if (accumWidth + width + 25 + 25 * index >= 764) {
				height += 80;
				accumWidth = 0;
				index = 0;
			}

			addWidget(new PatchouliBook(bookEntry.getKey(), bookEntry.getValue(), AdventMainGui.scaledTabRootX + accumWidth + 25 + 25 * index, height));

			accumWidth += width;
			index++;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		for (IGuiEventListener book : children()) {
			((PatchouliBook)book).render(matrixStack, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		setDragging(false);

		for (IGuiEventListener listener : children()) {
			if (listener.mouseReleased(mouseX, mouseY, button))
				return true;
		}

		return false;
	}

	public static void syncBooks(Collection<ResourceLocation> bookIds) {
		loreBooks.clear();

		if (!IntegrationManager.isPatchouliActive())
			return;

		Item guideBook = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));

		for (ResourceLocation id : bookIds) {
			if (!PatchouliIntegration.isBookLoaded(id))
				continue;

			ItemStack book = new ItemStack(guideBook);
			CompoundNBT tag = book.getOrCreateTag();

			tag.putString("patchouli:book", id.toString());
			loreBooks.put(id, book);
		}
	}

	public static void bookOpened(ResourceLocation id) {
		if (!loreBooks.containsKey(id))
			AoAPackets.messageServer(new PatchouliBookOpenPacket(id));
	}

	private static class PatchouliBook extends Widget {
		private final ResourceLocation id;
		private final ItemStack book;
		private final int titleWidth;

		private boolean mouseHolding = false;

		private PatchouliBook(ResourceLocation id, ItemStack book, int x, int y) {
			super(x, y, 100, 100, book.getDisplayName());

			this.id = id;
			this.book = book;
			this.titleWidth = Minecraft.getInstance().font.width(book.getHoverName());
		}

		protected boolean clicked(double mouseX, double mouseY) {
			return isMouseOver(mouseX, mouseY);
		}

		@Override
		public boolean mouseReleased(double mouseX, double mouseY, int button) {
			if (isValidClickButton(button)) {
				if (!mouseHolding) {
					if (isHovered) {
						PatchouliIntegration.openBook(id);

						return true;
					}
				}
				else {
					mouseHolding = false;

					if (mouseX / AdventMainGui.SCALE < AdventMainGui.scaledRootX ||
							mouseY / AdventMainGui.SCALE < AdventMainGui.scaledRootY ||
							mouseX / AdventMainGui.SCALE > AdventMainGui.scaledRootX + AdventMainGui.BACKGROUND_TEXTURE_WIDTH ||
							mouseY / AdventMainGui.SCALE > AdventMainGui.scaledRootY + AdventMainGui.BACKGROUND_TEXTURE_HEIGHT) {
						PlayerEntity pl = Minecraft.getInstance().player;
						Item patchouliBook = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));

						if (patchouliBook == null || patchouliBook == Items.AIR)
							return true;

						for (ItemStack stack : pl.inventory.items) {
							if (stack.getItem() == patchouliBook && stack.hasTag()) {
								CompoundNBT bookTag = stack.getTag();

								if (bookTag.contains("patchouli:book") && bookTag.getString("patchouli:book").equals(id.toString()))
									return true;
							}
						}

						AoAPackets.messageServer(new PatchouliGiveBookPacket(id));

						return true;
					}
				}
			}

			return false;
		}

		@Override
		protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
			if (isHovered)
				mouseHolding = true;
		}

		@Override
		public boolean isMouseOver(double mouseX, double mouseY) {
			if (!this.active || !this.visible)
				return false;

			if (mouseX < (this.x + this.titleWidth / 2f - 5) * AdventMainGui.SCALE)
				return false;

			if (mouseY < (this.y + 22 - 5) * AdventMainGui.SCALE)
				return false;

			if (mouseX > (this.x + this.titleWidth / 2f + 32 + 5) * AdventMainGui.SCALE)
				return false;

			return mouseY < (this.y + 22 + 32 + 5) * AdventMainGui.SCALE;
		}

		@Override
		public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				this.isHovered = isMouseOver(mouseX, mouseY);

				FontRenderer fontRenderer = Minecraft.getInstance().font;
				ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
				int itemX = mouseHolding ? (int)(mouseX - 16 * AdventMainGui.SCALE) : (int)((x + titleWidth / 2f) * AdventMainGui.SCALE);
				int itemY = mouseHolding ? (int)(mouseY - 16 * AdventMainGui.SCALE) : (int)(((y + 8 * 1.5f) + 10) * AdventMainGui.SCALE);

				RenderUtil.drawScaledMessage(matrix, fontRenderer, book.getHoverName(), x, y, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.DROP_SHADOW);

				itemRenderer.renderAndDecorateItem(book, itemX, itemY);
				itemRenderer.renderGuiItemDecorations(fontRenderer, book, itemX, itemY - 2, "");

				if (isHovered && !mouseHolding) {
					matrix.pushPose();
					matrix.scale(1.5f, 1.5f, 1.5f);
					GuiUtils.drawHoveringText(book, matrix, Collections.singletonList(new TranslationTextComponent("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.clickBook")), (int)(mouseX / AdventMainGui.SCALE / 1.5f), (int)(mouseY / AdventMainGui.SCALE / 1.5f), AdventMainGui.GUI_WIDTH, AdventMainGui.GUI_HEIGHT, -1, fontRenderer);
					matrix.popPose();
				}
			}
		}
	}
}
