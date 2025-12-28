package net.tslat.aoa3.integration.patchouli;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAItems;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.client.book.gui.GuiBook;
import vazkii.patchouli.common.book.BookRegistry;
import vazkii.patchouli.common.item.PatchouliDataComponents;
import vazkii.patchouli.common.item.PatchouliItems;

import java.util.List;
import java.util.Optional;


public class PatchouliIntegration {
	public static void preInit() {
		if (FMLEnvironment.dist == Dist.CLIENT)
			PatchouliClientIntegration.init();
	}

	public static Optional<ResourceLocation> getBookFromStack(ItemStack stack) {
		return Optional.ofNullable(stack.has(PatchouliDataComponents.BOOK) ? stack.get(PatchouliDataComponents.BOOK) : null);
	}

	public static ItemStack setPatchouliBook(ItemStack stack, ResourceLocation book) {
		stack.set(PatchouliDataComponents.BOOK, book);

		return stack;
	}

	public static boolean isBookLoaded(ResourceLocation id) {
		return BookRegistry.INSTANCE.books.containsKey(id);
	}

	public static ItemStack getBook(ResourceLocation id) {
		ItemStack book = BookRegistry.INSTANCE.books.get(id).getBookItem();

		book.set(PatchouliDataComponents.BOOK, id);

		return book;
	}

	public static boolean isValidBook(ItemStack stack) {
		return getBookFromStack(stack).filter(PatchouliIntegration::isBookLoaded).isPresent();
	}

	public static void openBook(ResourceLocation id) {
		PatchouliAPI.get().openBookGUI(id);
	}

	public static Optional<ResourceLocation> getBookIdFromScreen(Screen screen) {
		return Optional.ofNullable(screen instanceof GuiBook bookGui ? bookGui.book.id : null);
	}

	public static List<ItemStack> getAoAPatchouliBooks() {
		return BookRegistry.INSTANCE.books.keySet().stream().filter(AdventOfAscension::isAoA).map(bookId -> {
			ItemStack bookStack = switch (bookId.getPath()) {
				case "worn_book" -> AoAItems.WORN_BOOK.toStack();
				case "aoa_essentia" -> PatchouliItems.BOOK.getDefaultInstance();
				default -> AoAItems.TORN_PAGES.toStack();
			};

			bookStack.set(PatchouliDataComponents.BOOK, bookId);

			return bookStack;
		}).toList();
	}
}
