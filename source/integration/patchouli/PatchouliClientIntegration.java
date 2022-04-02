package net.tslat.aoa3.integration.patchouli;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import vazkii.patchouli.client.book.gui.GuiBook;

public final class PatchouliClientIntegration {
	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ScreenOpenEvent.class, PatchouliClientIntegration::onBookOpen);
	}

	private static void onBookOpen(final ScreenOpenEvent ev) {
		if (ev.getScreen() instanceof GuiBook) {
			ResourceLocation bookId = ((GuiBook)ev.getScreen()).book.id;

			//if (bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
			//	AdventGuiTabLore.bookOpened(bookId);
		}
	}
}
