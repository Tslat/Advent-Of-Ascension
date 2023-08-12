package net.tslat.aoa3.integration.patchouli;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.integration.IntegrationManager;
import vazkii.patchouli.client.book.gui.GuiBook;

public final class PatchouliClientIntegration {
	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ScreenEvent.Opening.class, PatchouliClientIntegration::onBookOpen);
	}

	private static void onBookOpen(final ScreenEvent.Opening ev) {
		if (!IntegrationManager.isPatchouliActive())
			return;

		if (ev.getScreen() instanceof GuiBook) {
			ResourceLocation bookId = ((GuiBook)ev.getScreen()).book.id;

			if (bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
				AdventGuiTabLore.bookOpened(bookId);
		}
	}
}
