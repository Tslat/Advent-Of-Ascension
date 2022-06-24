package net.tslat.aoa3.integration.patchouli;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.common.book.BookRegistry;

public class PatchouliIntegration {
	public static void preInit() {
		if (FMLEnvironment.dist == Dist.CLIENT)
			PatchouliClientIntegration.init();
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
