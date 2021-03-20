package net.tslat.aoa3.data.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.resource.VanillaResourceType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.FileUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.function.Predicate;

public class MiscTextFileManager implements ISelectiveResourceReloadListener {
	public static final HashMap<Object, String> DATA = new HashMap<Object, String>();

	public MiscTextFileManager() {}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		if (resourcePredicate.test(VanillaResourceType.LANGUAGES)) {
			try {
				DATA.clear();
				Minecraft mc = Minecraft.getInstance();
				String langCode = mc.options.languageCode;

				if (mc.getLanguageManager().getSelected() != null)
					langCode = mc.getLanguageManager().getSelected().getCode();

				IResource wornBook = resourceManager.getResource(new ResourceLocation(AdventOfAscension.MOD_ID, "misc/" + langCode + "/worn_book.txt"));

				DATA.put(AoAItems.WORN_BOOK.get(), FileUtil.bufferedReaderToString(new BufferedReader(new InputStreamReader(wornBook.getInputStream(), StandardCharsets.UTF_8))));
			}
			catch (IOException ex) {
				Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 additional resources, skipping.", ex);
			}
		}
	}

	@Nullable
	@Override
	public IResourceType getResourceType() {
		return VanillaResourceType.LANGUAGES;
	}
}
