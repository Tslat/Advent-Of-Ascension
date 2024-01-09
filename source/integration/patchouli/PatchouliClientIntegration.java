package net.tslat.aoa3.integration.patchouli;

public final class PatchouliClientIntegration {
	/*public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, ScreenEvent.Opening.class, PatchouliClientIntegration::onBookOpen);
	}

	private static void onBookOpen(final ScreenEvent.Opening ev) {
		if (!IntegrationManager.isPatchouliActive())
			return;

		if (ev.getScreen() instanceof GuiBook) {
			ResourceLocation bookId = ((GuiBook)ev.getScreen()).book.id;

			if (bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
				AdventGuiTabLore.bookOpened(bookId);
		}
	}*/
}
