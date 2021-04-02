package net.tslat.aoa3.data.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.resource.VanillaResourceType;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.FileUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.Predicate;

public class GuidesManager implements ISelectiveResourceReloadListener {
	public static final ArrayList<Guide> GUIDES = new ArrayList<Guide>();

	public GuidesManager() {}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		if (resourcePredicate.test(getResourceType())) {
			try {
				GUIDES.clear();
				Minecraft mc = Minecraft.getInstance();
				String langCode = mc.options.languageCode;

				if (mc.getLanguageManager().getSelected() != null)
					langCode = mc.getLanguageManager().getSelected().getCode();

				for (ResourceLocation resourceLocation : resourceManager.listResources("guides/" + langCode, (path) -> path.endsWith(".txt"))) {
					for (IResource resource : resourceManager.getResources(resourceLocation)) {
						GUIDES.add(new Guide(FileUtil.bufferedReaderToString(new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)))));
					}
				}
			}
			catch (IOException ex) {
				Logging.logMessage(Level.ERROR, "Failed to retrieve AoA3 Guides resources, skipping.", ex);
			}
		}
	}

	@Nullable
	@Override
	public IResourceType getResourceType() {
		return VanillaResourceType.LANGUAGES;
	}

	public static class Guide {
		public final String title;
		public final String content;

		private Guide(String content) {
			String[] data = content.split("\n", 2);

			this.title = data[0];
			this.content = data[1];
		}
	}
}
