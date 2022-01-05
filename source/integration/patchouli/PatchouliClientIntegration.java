package net.tslat.aoa3.integration.patchouli;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import vazkii.patchouli.client.book.gui.GuiBook;

public final class PatchouliClientIntegration {
	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, GuiOpenEvent.class, PatchouliClientIntegration::onBookOpen);
	}

	private static void onBookOpen(final GuiOpenEvent ev) {
		if (ev.getGui() instanceof GuiBook) {
			ResourceLocation bookId = ((GuiBook)ev.getGui()).book.id;

			if (bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
				AdventGuiTabLore.bookOpened(bookId);
		}
	}
}
