package net.tslat.aoa3.data.client;

import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.resource.VanillaResourceType;
import net.tslat.aoa3.util.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class AdventGuiThemeReloadListener implements ISelectiveResourceReloadListener {
	private static ArrayList<AdventGuiTheme> THEMES = new ArrayList<AdventGuiTheme>(7);
	private static int pointer = -1;

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		if (resourcePredicate.test(getResourceType())) {
			THEMES.clear();

			HashMap<String, ResourceLocation[]> textures = new HashMap<String, ResourceLocation[]>();

			textures.put("default", new ResourceLocation[3]);

			for (ResourceLocation resourceLocation : resourceManager.listResources("textures/gui/adventgui/themes", path -> path.endsWith(".png"))) {
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
	}

	@Nullable
	@Override
	public IResourceType getResourceType() {
		return VanillaResourceType.TEXTURES;
	}

	public static AdventGuiTheme getNextTheme() {
		if (++pointer >= THEMES.size())
			pointer = 0;

		return THEMES.get(pointer);
	}

	public static AdventGuiTheme setTheme(String name) {
		pointer = 0;

		for (int i = 0; i < THEMES.size(); i++) {
			if (THEMES.get(i).getName().equals(name)) {
				pointer = i;

				break;
			}
		}

		return THEMES.get(pointer);
	}

	public static class AdventGuiTheme {
		private final String name;
		@Nullable
		private final ResourceLocation overlayTexture;
		private final ResourceLocation backgroundTexture;
		private final ResourceLocation menuButtonTexture;

		private AdventGuiTheme(String name, @Nullable ResourceLocation backgroundTexture, @Nullable ResourceLocation menuButtonTexture, @Nullable ResourceLocation overlayTexture) {
			this.name = name;
			this.backgroundTexture = backgroundTexture == null ? new ResourceLocation("aoa3", "textures/gui/adventgui/themes/default/background.png") : backgroundTexture;
			this.menuButtonTexture = menuButtonTexture == null ? new ResourceLocation("aoa3", "textures/gui/adventgui/themes/default/tab_buttons.png") : menuButtonTexture;
			this.overlayTexture = overlayTexture;
		}

		public String getName() {
			return this.name;
		}

		@Nullable
		public ResourceLocation getOverlayTexture() {
			return overlayTexture;
		}

		public ResourceLocation getBackgroundTexture() {
			return backgroundTexture;
		}

		public ResourceLocation getMenuButtonTexture() {
			return menuButtonTexture;
		}
	}
}
