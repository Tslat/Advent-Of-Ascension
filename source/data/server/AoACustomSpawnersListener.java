package net.tslat.aoa3.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.world.spawner.AoACustomSpawner;
import net.tslat.aoa3.content.world.spawner.RoamingTraderSpawner;

import java.util.List;
import java.util.Map;

public class AoACustomSpawnersListener extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String FOLDER = "spawners";
	private static final Map<ResourceLocation, Codec<? extends AoACustomSpawner>> REGISTERED_SPAWNERS = Util.make(new Object2ObjectOpenHashMap<>(), map ->
			map.put(AdventOfAscension.id("roaming_traders"), RoamingTraderSpawner.CODEC));

	public static final List<AoACustomSpawner> SPAWNERS = new ObjectArrayList<>();

	public AoACustomSpawnersListener() {
		super(GSON, FOLDER);
	}

	public static void registerSpawner(ResourceLocation id, Codec<? extends AoACustomSpawner> codec) {
		REGISTERED_SPAWNERS.putIfAbsent(id, codec);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> dataMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		 for (Map.Entry<ResourceLocation, JsonElement> entry : dataMap.entrySet()) {
			 Codec<? extends AoACustomSpawner> codec = REGISTERED_SPAWNERS.get(entry.getKey());

			 if (codec == null) {
				 Logging.warning("Found spawner json file in spawners data directory, but no codec has been registered for it. (" + entry.getKey() + ")");

				 continue;
			 }

			 codec.decode(JsonOps.INSTANCE, entry.getValue()).resultOrPartial(Logging::error).ifPresent(pair -> SPAWNERS.add(pair.getFirst()));
		 }
	}
}
