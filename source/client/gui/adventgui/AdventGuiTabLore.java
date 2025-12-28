package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.patchouli.AccountPatchouliBookPacket;
import net.tslat.aoa3.common.networking.packets.patchouli.GivePatchouliBookPacket;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.tme.api.object.extension.Text;

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

		if (loreBooks.isEmpty())
			loreBooks.put(AdventOfAscension.id("aoa_essentia"), PatchouliIntegration.getBook(AdventOfAscension.id("aoa_essentia")));

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
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));

		for (Renderable renderable : this.renderables) {
			renderable.render(guiGraphics, mouseX, mouseY, partialTick);
		}
	}

	@Override
	public void renderTransparentBackground(GuiGraphics pGuiGraphics) {}

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

		for (ResourceLocation id : bookIds) {
			if (!PatchouliIntegration.isBookLoaded(id))
				continue;

			ItemStack book = PatchouliIntegration.getBook(id);

			if (book.is(AoAItems.TORN_PAGES))
				book.set(DataComponents.CUSTOM_NAME, Text.of(LocaleUtil.createGenericLocaleKey("item", "torn_pages." + id.getPath()), ChatFormatting.ITALIC));

			loreBooks.put(id, book);
		}
	}

	public static void bookOpened(ResourceLocation id) {
		if (!loreBooks.containsKey(id))
			AoANetworking.sendToServer(new AccountPatchouliBookPacket(id));
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
			this.titleWidth = Minecraft.getInstance().font.width(book.getDisplayName());
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

						for (ItemStack stack : pl.getInventory().items) {
							if (PatchouliIntegration.isValidBook(stack))
								return true;
						}

						AoANetworking.sendToServer(new GivePatchouliBookPacket(id));

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

			if (mouseX < (this.getX() + this.titleWidth / 2f - 4) * AdventMainGui.SCALE)
				return false;

			if (mouseY < (this.getY() + 22 - 5) * AdventMainGui.SCALE)
				return false;

			if (mouseX > (this.getX() + this.titleWidth / 2f + 32 + 4) * AdventMainGui.SCALE)
				return false;

			return mouseY < (this.getY() + 22 + 32 + 5) * AdventMainGui.SCALE;
		}

		@Override
		protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			this.isHovered = isMouseOver(mouseX, mouseY);
			int itemX = mouseHolding ? (int)((mouseX - 24) / AdventMainGui.SCALE) : (int)(getX() + titleWidth / 2f);
			int itemY = mouseHolding ? (int)((mouseY - 24) / AdventMainGui.SCALE) : (int)((getY() + 8 * 1.5f) + 10);
			RenderContext renderContext = RenderContext.of(guiGraphics);
			PoseStack poseStack = guiGraphics.pose();

			renderContext.renderScaledText(book.getHoverName(), getX(), getY(), 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.DROP_SHADOW);
			poseStack.pushPose();
			poseStack.scale(2, 2, 1);
			renderContext.renderDummyItem(book, (int)(itemX / 2f), (int)(itemY / 2f));
			poseStack.popPose();

			if (isHovered && !mouseHolding) {
				poseStack.pushPose();
				poseStack.scale(1.5f, 1.5f, 1.5f);
				guiGraphics.renderTooltip(Minecraft.getInstance().font, Collections.singletonList(Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.clickBook")), Optional.empty(), book, (int)(mouseX / AdventMainGui.SCALE / 1.5f), (int)(mouseY / AdventMainGui.SCALE / 1.5f));
				poseStack.popPose();
			}
		}

		@Override
		protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {}
	}
}
