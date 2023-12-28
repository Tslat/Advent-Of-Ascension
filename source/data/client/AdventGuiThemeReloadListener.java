package net.tslat.aoa3.data.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.tslat.aoa3.util.StringUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AdventGuiThemeReloadListener implements PreparableReloadListener {
	private static final ArrayList<AdventGuiTheme> THEMES = new ArrayList<>(7);
	private static int pointer = -1;

	@Override
	public CompletableFuture<Void> reload(PreparationBarrier stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
		return stage.wait(Unit.INSTANCE).thenRunAsync(() -> handleResourcesReload(resourceManager, reloadProfiler));
	}

	private void handleResourcesReload(ResourceManager resourceManager, ProfilerFiller reloadProfiler) {
		THEMES.clear();

		HashMap<String, ResourceLocation[]> textures = new HashMap<>();

		textures.put("default", new ResourceLocation[3]);

		for (ResourceLocation resourceLocation : resourceManager.listResources("textures/gui/adventgui/themes", path -> path.getPath().endsWith(".png")).keySet()) {
			int subfolderIndexStart = resourceLocation.getPath().indexOf("themes/") + 7;
			int subfolderIndexEnd = resourceLocation.getPath().lastIndexOf("/");

			if (subfolderIndexStart == subfolderIndexEnd)
				continue;

			String theme = resourceLocation.getPath().substring(subfolderIndexStart, subfolderIndexEnd);
			ResourceLocation[] themeTextures = textures.getOrDefault(theme, new ResourceLocation[3]);

			switch (resourceLocation.getPath().substring(subfolderIndexEnd + 1)) {
				case "background.png":
					themeTextures[0] = resourceLocation;
					break;
				case "tab_buttons.png":
					themeTextures[1] = resourceLocation;
					break;
				case "overlay.png":
					themeTextures[2] = resourceLocation;
				default:
					break;
			}

			textures.put(theme, themeTextures);
		}

		for (Map.Entry<String, ResourceLocation[]> entry : textures.entrySet()) {
			ResourceLocation[] paths = entry.getValue();

			THEMES.add(new AdventGuiTheme(StringUtil.toTitleCase(entry.getKey()), paths[0], paths[1], paths[2]));
		}
	}

	public static AdventGuiTheme getNextTheme() {
		if (++pointer >= THEMES.size())
			pointer = 0;

		return THEMES.get(pointer);
	}

	public static AdventGuiTheme setTheme(String name) {
		pointer = 0;

		for (int i = 0; i < THEMES.size(); i++) {
			if (THEMES.get(i).name().equals(name)) {
				pointer = i;

				break;
			}
		}

		return THEMES.get(pointer);
	}

	public record AdventGuiTheme(String name, ResourceLocation backgroundTexture, ResourceLocation menuButtonTexture, @Nullable ResourceLocation overlayTexture) {
		public AdventGuiTheme(String name, @Nullable ResourceLocation backgroundTexture, @Nullable ResourceLocation menuButtonTexture, @Nullable ResourceLocation overlayTexture) {
			this.name = name;
			this.backgroundTexture = backgroundTexture == null ? new ResourceLocation("aoa3", "textures/gui/adventgui/themes/default/background.png") : backgroundTexture;
			this.menuButtonTexture = menuButtonTexture == null ? new ResourceLocation("aoa3", "textures/gui/adventgui/themes/default/tab_buttons.png") : menuButtonTexture;
			this.overlayTexture = overlayTexture;
		}
	}
}
