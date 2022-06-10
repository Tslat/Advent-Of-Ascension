package net.tslat.aoa3.data.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ObjectUtil;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

public class MiscellaneousReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<Object, String> DATA = new HashMap<Object, String>();

	public MiscellaneousReloadListener() {}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		try {
			DATA.clear();
			Minecraft mc = Minecraft.getInstance();
			String langCode = mc.options.languageCode;

			if (mc.getLanguageManager().getSelected() != null)
				langCode = mc.getLanguageManager().getSelected().getCode();

			Optional<Resource> wornBook = resourceManager.getResource(new ResourceLocation(AdventOfAscension.MOD_ID, "misc/" + langCode + "/worn_book.txt"));

			if (!wornBook.isPresent())
				return;

			DATA.put(AoAItems.WORN_BOOK.get(), ObjectUtil.bufferedReaderToString(new BufferedReader(new InputStreamReader(wornBook.get().open(), StandardCharsets.UTF_8))));
		}
		catch (IOException ex) {
			Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 additional resources, skipping.", ex);
		}
	}
}
