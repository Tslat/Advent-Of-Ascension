package net.tslat.aoa3.integration.patchouli;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.common.book.BookRegistry;

public class PatchouliIntegration {
	public static void preInit() {
		MinecraftForge.EVENT_BUS.addListener(PatchouliIntegration::onBookOpen);
	}

	@SubscribeEvent
	public static void onBookOpen(final GuiOpenEvent ev) {
		if (ev.getGui() instanceof GuiBook) {
			ResourceLocation bookId = ((GuiBook)ev.getGui()).book.id;

			if (bookId.getNamespace().equals(AdventOfAscension.MOD_ID))
				AdventGuiTabLore.bookOpened(bookId);
		}
	}

	public static boolean isBookLoaded(ResourceLocation id) {
		return BookRegistry.INSTANCE.books.containsKey(id);
	}

	public static ItemStack getBook(ResourceLocation id) {
		return BookRegistry.INSTANCE.books.get(id).getBookItem();
	}

	public static void openBook(ResourceLocation id) {
		PatchouliAPI.get().openBookGUI(id);
	}
}
