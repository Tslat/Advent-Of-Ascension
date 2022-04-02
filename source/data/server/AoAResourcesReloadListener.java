package net.tslat.aoa3.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.resource.AoAResource;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

public class AoAResourcesReloadListener extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/resources";

	private static final HashMap<AoAResource, JsonObject> RESOURCES = new HashMap<>();

	public AoAResourcesReloadListener() {
		super(GSON, folder);
	}

	public static void populateResourceMap(ServerPlayerDataManager plData, HashMap<AoAResource, AoAResource.Instance> resourceMap) {
		resourceMap.clear();

		for (Map.Entry<AoAResource, JsonObject> resource : RESOURCES.entrySet()) {
			resourceMap.put(resource.getKey(), resource.getKey().buildDefaultInstance(plData, resource.getValue()));
		}
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		RESOURCES.clear();

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
			ResourceLocation resourceId = entry.getKey();
			JsonElement json = entry.getValue();
			AoAResource resource = AoAResources.getResource(resourceId);

			if (resource == null) {
				Logging.logMessage(Level.WARN, "Unable to find registered resource: '" + resourceId.toString() + "' from datapack entry.");

				continue;
			}

			if (!json.isJsonObject()) {
				Logging.logMessage(Level.ERROR, "Invalidly formatted resource json '" + resourceId.toString() + "' from datapack entry.");

				continue;
			}

			RESOURCES.put(resource, json.getAsJsonObject());
		}
	}
}
