package net.tslat.aoa3.data.server;

import com.google.gson.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.library.object.GenericEntryPool;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class AoAHaulingFishReloadListener extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/misc/hauling_fish";

	private static final FishEntryRetriever FISH_RETRIEVER = new FishEntryRetriever();
	private static final FishEntryRetriever TRAPS_RETRIEVER = new FishEntryRetriever();

	public AoAHaulingFishReloadListener() {
		super(GSON, folder);
	}

	public static GenericEntryPool<Function<Level, Entity>, ServerPlayer> getFishListForBiome(Biome biome, boolean isLava) {
		return FISH_RETRIEVER.getEntityList(biome, isLava);
	}

	public static GenericEntryPool<Function<Level, Entity>, ServerPlayer> getTrapListForBiome(Biome biome, boolean isLava) {
		return TRAPS_RETRIEVER.getEntityList(biome, isLava);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		FISH_RETRIEVER.reset();
		TRAPS_RETRIEVER.reset();

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
			ResourceLocation id = entry.getKey();
			JsonObject obj = entry.getValue().getAsJsonObject();

			if (id.equals(AdventOfAscension.id("fish_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.FALLBACK, false);
			}
			else if (id.equals(AdventOfAscension.id("fish_lava_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.LAVA_FALLBACK, true);
			}
			else if (id.equals(AdventOfAscension.id("traps_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), TRAPS_RETRIEVER.FALLBACK, false);
			}
			else if (id.equals(AdventOfAscension.id("traps_lava_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), TRAPS_RETRIEVER.LAVA_FALLBACK, true);
			}
			else {
				try {
					parseEntry(obj.getAsJsonObject());
				}
				catch (IllegalArgumentException ex) {
					Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Invalid Hauling fish json: " + entry.getKey().toString(), ex);
				}
			}
		}
	}

	private void parseEntry(JsonObject json) throws IllegalArgumentException {
		boolean replace = GsonHelper.getAsBoolean(json, "replace", false);
		ArrayList<Biome.BiomeCategory> categories = new ArrayList<>();
		ArrayList<ResourceKey<Biome>> biomes = new ArrayList<>();
		boolean forLava = GsonHelper.getAsBoolean(json, "for_lava", false);
		boolean forTraps = GsonHelper.getAsBoolean(json, "for_traps", false);

		if (json.has("categories")) {
			JsonArray categoryArray = json.getAsJsonArray("categories");

			for (JsonElement element : categoryArray) {
				categories.add(Biome.BiomeCategory.byName(element.getAsString()));
			}
		}

		if (json.has("biomes")) {
			JsonArray biomeArray = json.getAsJsonArray("biomes");

			for (JsonElement element : biomeArray) {
				biomes.add(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(element.getAsString())));
			}
		}

		if (categories.isEmpty() && biomes.isEmpty())
			throw new IllegalArgumentException("No biome category or biome ids listed, must have one or the other.");

		FishEntryRetriever retriever = forTraps ? TRAPS_RETRIEVER : FISH_RETRIEVER;

		if (!categories.isEmpty()) {
			for (Biome.BiomeCategory category : categories) {
				GenericEntryPool<Function<Level, Entity>, ServerPlayer> list = retriever.getOrCreateCategoryPool(forLava, category);

				if (replace)
					list.clear();

				parseEntityList(json.get("entities").getAsJsonArray(), list, forLava);
			}
		}

		if (!biomes.isEmpty()) {
			for (ResourceKey<Biome> biome : biomes) {
				GenericEntryPool<Function<Level, Entity>, ServerPlayer> list = retriever.getOrCreateBiomePool(forLava, biome);

				if (replace)
					list.clear();

				parseEntityList(json.get("entities").getAsJsonArray(), list, forLava);
			}
		}
	}

	private void parseEntityList(JsonArray array, GenericEntryPool<Function<Level, Entity>, ServerPlayer> list, boolean forLava) {
		final Predicate<ServerPlayer> fallbackPredicate = player -> true;

		for (JsonElement element : array) {
			if (element.isJsonObject()) {
				JsonObject obj = element.getAsJsonObject();
				int weight = GsonHelper.getAsInt(obj, "weight", 1);
				Predicate<ServerPlayer> predicate = obj.has("level") ? pl -> PlayerUtil.doesPlayerHaveLevel(pl, AoASkills.HAULING.get(), obj.get("level").getAsInt()) : fallbackPredicate;
				float weightMod = GsonHelper.getAsFloat(obj, "weight_mod", 0);
				Function<Level, Entity> factory;

				if (obj.has("item")) {
					Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(obj.get("item").getAsString()));

					if (item == null)
						continue;

					factory = world -> {
						ItemEntity entity = new ItemEntity(EntityType.ITEM, world) {
							@Override
							public boolean fireImmune() {
								return forLava || super.fireImmune();
							}
						};

						entity.setItem(new ItemStack(item));
						entity.setNeverPickUp();

						return entity;
					};
				}
				else {
					EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(obj.get("entity").getAsString()));

					if (entityType == null)
						continue;

					factory = entityType::create;
				}

				list.add(factory, predicate, weight, weightMod);
			}
		}
	}

	private static class FishEntryRetriever {
		private final GenericEntryPool<Function<Level, Entity>, ServerPlayer> FALLBACK = new GenericEntryPool<>();
		private final GenericEntryPool<Function<Level, Entity>, ServerPlayer> LAVA_FALLBACK = new GenericEntryPool<>();

		private HashMap<ResourceKey<Biome>, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> BIOME_MAP = null;
		private HashMap<ResourceKey<Biome>, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> LAVA_BIOME_MAP = null;
		private HashMap<Biome.BiomeCategory, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> CATEGORY_MAP = null;
		private HashMap<Biome.BiomeCategory, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> LAVA_CATEGORY_MAP = null;

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getOrCreateBiomePool(boolean forLava, ResourceKey<Biome> key) {
			if (forLava) {
				if (LAVA_BIOME_MAP == null)
					LAVA_BIOME_MAP = new HashMap<>();

				return LAVA_BIOME_MAP.computeIfAbsent(key, biome -> new GenericEntryPool<>());
			}
			else {
				if (BIOME_MAP == null)
					BIOME_MAP = new HashMap<>();

				return BIOME_MAP.computeIfAbsent(key, biome -> new GenericEntryPool<>());
			}
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getOrCreateCategoryPool(boolean forLava, Biome.BiomeCategory category) {
			if (forLava) {
				if (LAVA_CATEGORY_MAP == null)
					LAVA_CATEGORY_MAP = new HashMap<>();

				return LAVA_CATEGORY_MAP.computeIfAbsent(category, cat -> new GenericEntryPool<>());
			}
			else {
				if (CATEGORY_MAP == null)
					CATEGORY_MAP = new HashMap<>();

				return CATEGORY_MAP.computeIfAbsent(category, cat -> new GenericEntryPool<>());
			}
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getEntityList(Biome biome, boolean isLava) {
			return isLava ? getLavaEntry(biome) : getWaterEntry(biome);
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getLavaEntry(Biome biome) {
			ResourceKey<Biome> registryKey = ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());

			if (LAVA_BIOME_MAP != null && LAVA_BIOME_MAP.containsKey(registryKey))
				return LAVA_BIOME_MAP.get(registryKey);

			if (LAVA_CATEGORY_MAP != null && LAVA_CATEGORY_MAP.containsKey(biome.getBiomeCategory()))
				return LAVA_CATEGORY_MAP.get(biome.getBiomeCategory());

			return LAVA_FALLBACK;
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getWaterEntry(Biome biome) {
			ResourceKey<Biome> registryKey = ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());

			if (BIOME_MAP != null && BIOME_MAP.containsKey(registryKey))
				return BIOME_MAP.get(registryKey);

			if (CATEGORY_MAP != null && CATEGORY_MAP.containsKey(biome.getBiomeCategory()))
				return CATEGORY_MAP.get(biome.getBiomeCategory());

			return FALLBACK;
		}

		private void reset() {
			FALLBACK.clear();
			LAVA_FALLBACK.clear();
			BIOME_MAP = null;
			LAVA_BIOME_MAP = null;
			CATEGORY_MAP = null;
			LAVA_CATEGORY_MAP = null;
		}
	}
}
