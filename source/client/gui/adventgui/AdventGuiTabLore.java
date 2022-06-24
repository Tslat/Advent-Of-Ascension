package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AdventGuiTabLore extends Screen {
	private int adjustedMouseX;
	private int adjustedMouseY;

	private static final ConcurrentHashMap<ResourceLocation, ItemStack> loreBooks = new ConcurrentHashMap<>();

	protected AdventGuiTabLore() {
		super(Component.translatable("gui.aoa3.adventGui.lore"));
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

			addRenderableWidget(new PatchouliBook(bookEntry.getKey(), bookEntry.getValue(), AdventMainGui.scaledTabRootX + accumWidth + 25 + 25 * index, height));

			accumWidth += width;
			index++;
		}
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		super.render(matrixStack, mouseX, mouseY, partialTicks);

		for (GuiEventListener book : children()) {
			((PatchouliBook)book).render(matrixStack, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		setDragging(false);

		for (GuiEventListener listener : children()) {
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
			CompoundTag tag = book.getOrCreateTag();

			tag.putString("patchouli:book", id.toString());
			loreBooks.put(id, book);
		}
	}

	public static void bookOpened(ResourceLocation id) {
		if (!loreBooks.containsKey(id))
			AoAPackets.messageServer(new PatchouliBookOpenPacket(id));
	}

	private static class PatchouliBook extends AbstractWidget {
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
						Player pl = Minecraft.getInstance().player;
						Item patchouliBook = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));

						if (patchouliBook == null || patchouliBook == Items.AIR)
							return true;

						for (ItemStack stack : pl.getInventory().items) {
							if (stack.getItem() == patchouliBook && stack.hasTag()) {
								CompoundTag bookTag = stack.getTag();

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
		public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				this.isHovered = isMouseOver(mouseX, mouseY);

				Font fontRenderer = Minecraft.getInstance().font;
				ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
				int itemX = mouseHolding ? (int)(mouseX - 16 * AdventMainGui.SCALE) : (int)((x + titleWidth / 2f) * AdventMainGui.SCALE);
				int itemY = mouseHolding ? (int)(mouseY - 16 * AdventMainGui.SCALE) : (int)(((y + 8 * 1.5f) + 10) * AdventMainGui.SCALE);

				RenderUtil.drawScaledMessage(matrix, fontRenderer, book.getHoverName(), x, y, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.DROP_SHADOW);

				itemRenderer.renderAndDecorateItem(book, itemX, itemY);
				itemRenderer.renderGuiItemDecorations(fontRenderer, book, itemX, itemY - 2, "");

				if (isHovered && !mouseHolding) {
					matrix.pushPose();
					matrix.scale(1.5f, 1.5f, 1.5f);
					AdventMainGui.instance.renderTooltip(matrix, Collections.singletonList(Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.clickBook")), Optional.empty(), (int)(mouseX / AdventMainGui.SCALE / 1.5f), (int)(mouseY / AdventMainGui.SCALE / 1.5f), fontRenderer, book);
					matrix.popPose();
				}
			}
		}

		@Override
		public void updateNarration(NarrationElementOutput pNarrationElementOutput) {}
	}
}
