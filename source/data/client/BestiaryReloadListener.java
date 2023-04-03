package net.tslat.aoa3.data.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.ObjectUtil;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BestiaryReloadListener implements ResourceManagerReloadListener {
	public static final HashMap<ResourceLocation, String> BESTIARY = new HashMap<ResourceLocation, String>();

	public BestiaryReloadListener() {}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		try {
			BESTIARY.clear();
			Minecraft mc = Minecraft.getInstance();
			String langCode = mc.options.languageCode;

			if (mc.getLanguageManager().getSelected() != null)
				langCode = mc.getLanguageManager().getSelected();

			for (Map.Entry<ResourceLocation, Resource> entry : resourceManager.listResources("bestiary/" + langCode, path -> path.getPath().endsWith(".txt")).entrySet()) {
				String relativePath = entry.getKey().getPath().substring(15);
				String[] pathParts = relativePath.split("/");

				if (pathParts.length < 2) {
					Logging.logMessage(Level.DEBUG, "Invalid resource path for bestiary entry, skipping. " + relativePath);

					continue;
				}

				ResourceLocation entryId = new ResourceLocation(pathParts[0], pathParts[1].substring(0, pathParts[1].length() - 4));

				try (BufferedReader reader = new BufferedReader(new InputStreamReader(entry.getValue().open(), StandardCharsets.UTF_8)) ) {
					BESTIARY.put(entryId, ObjectUtil.bufferedReaderToString(reader));
				}
			}
		}
		catch (IOException ex) {
			Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 Bestiary resources, skipping.", ex);
		}
	}
}
