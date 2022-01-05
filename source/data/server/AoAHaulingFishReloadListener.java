package net.tslat.aoa3.data.server;

import com.google.gson.*;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.misc.GenericEntryPool;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class AoAHaulingFishReloadListener extends JsonReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/misc/hauling_fish";

	private static final FishEntryRetriever FISH_RETRIEVER = new FishEntryRetriever();
	private static final FishEntryRetriever TRAPS_RETRIEVER = new FishEntryRetriever();

	public AoAHaulingFishReloadListener() {
		super(GSON, folder);
	}

	public static GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getFishListForBiome(Biome biome, boolean isLava) {
		return FISH_RETRIEVER.getEntityList(biome, isLava);
	}

	public static GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getTrapListForBiome(Biome biome, boolean isLava) {
		return TRAPS_RETRIEVER.getEntityList(biome, isLava);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, IResourceManager resourceManager, IProfiler profiler) {
		FISH_RETRIEVER.reset();
		TRAPS_RETRIEVER.reset();

		for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
			ResourceLocation id = entry.getKey();
			JsonObject obj = entry.getValue().getAsJsonObject();

			if (id.equals(AdventOfAscension.id("fish_default"))) {
				parseEntityList(JSONUtils.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.FALLBACK);
			}
			else if (id.equals(AdventOfAscension.id("fish_lava_default"))) {
				parseEntityList(JSONUtils.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.LAVA_FALLBACK);
			}
			else if (id.equals(AdventOfAscension.id("traps_default"))) {
				parseEntityList(JSONUtils.getAsJsonArray(obj, "entities"), TRAPS_RETRIEVER.FALLBACK);
			}
			else if (id.equals(AdventOfAscension.id("traps_lava_default"))) {
				parseEntityList(JSONUtils.getAsJsonArray(obj, "entities"), TRAPS_RETRIEVER.LAVA_FALLBACK);
			}
			else {
				try {
					parseEntry(obj.getAsJsonObject());
				}
				catch (IllegalArgumentException ex) {
					Logging.logMessage(Level.WARN, "Invalid Hauling fish json: " + entry.getKey().toString(), ex);
				}
			}
		}
	}

	private void parseEntry(JsonObject json) throws IllegalArgumentException {
		boolean replace = JSONUtils.getAsBoolean(json, "replace", false);
		ArrayList<Biome.Category> categories = new ArrayList<Biome.Category>();
		ArrayList<RegistryKey<Biome>> biomes = new ArrayList<RegistryKey<Biome>>();
		boolean forLava = JSONUtils.getAsBoolean(json, "for_lava", false);
		boolean forTraps = JSONUtils.getAsBoolean(json, "for_traps", false);

		if (json.has("categories")) {
			JsonArray categoryArray = json.getAsJsonArray("categories");

			for (JsonElement element : categoryArray) {
				categories.add(Biome.Category.byName(element.getAsString()));
			}
		}

		if (json.has("biomes")) {
			JsonArray biomeArray = json.getAsJsonArray("biomes");

			for (JsonElement element : biomeArray) {
				biomes.add(RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(element.getAsString())));
			}
		}

		if (categories.isEmpty() && biomes.isEmpty())
			throw new IllegalArgumentException("No biome category or biome ids listed, must have one or the other.");

		FishEntryRetriever retriever = forTraps ? TRAPS_RETRIEVER : FISH_RETRIEVER;

		if (!categories.isEmpty()) {
			for (Biome.Category category : categories) {
				GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> list = retriever.getOrCreateCategoryPool(forLava, category);

				if (replace)
					list.clear();

				parseEntityList(json.get("entities").getAsJsonArray(), list);
			}
		}

		if (!biomes.isEmpty()) {
			for (RegistryKey<Biome> biome : biomes) {
				GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> list = retriever.getOrCreateBiomePool(forLava, biome);

				if (replace)
					list.clear();

				parseEntityList(json.get("entities").getAsJsonArray(), list);
			}
		}
	}

	private void parseEntityList(JsonArray array, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> list) {
		final Predicate<ServerPlayerEntity> fallbackPredicate = player -> true;

		for (JsonElement element : array) {
			if (element.isJsonObject()) {
				JsonObject obj = element.getAsJsonObject();
				int weight = JSONUtils.getAsInt(obj, "weight", 1);
				Predicate<ServerPlayerEntity> predicate = obj.has("level") ? pl -> PlayerUtil.doesPlayerHaveLevel(pl, AoASkills.HAULING.get(), obj.get("level").getAsInt()) : fallbackPredicate;
				float weightMod = JSONUtils.getAsFloat(obj, "weight_mod", 0);
				Function<World, Entity> factory;

				if (obj.has("item")) {
					Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(obj.get("item").getAsString()));

					if (item == null)
						continue;

					factory = world -> {
						ItemEntity entity = EntityType.ITEM.create(world);

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
			else if (element.isJsonPrimitive()) {
				EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(element.getAsString()));

				if (entityType != null)
					list.add(entityType::create, 1);
			}
		}
	}

	private static class FishEntryRetriever {
		private final GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> FALLBACK = new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>();
		private final GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> LAVA_FALLBACK = new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>();

		private HashMap<RegistryKey<Biome>, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>> BIOME_MAP = null;
		private HashMap<RegistryKey<Biome>, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>> LAVA_BIOME_MAP = null;
		private HashMap<Biome.Category, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>> CATEGORY_MAP = null;
		private HashMap<Biome.Category, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>> LAVA_CATEGORY_MAP = null;

		private GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getOrCreateBiomePool(boolean forLava, RegistryKey<Biome> key) {
			if (forLava) {
				if (LAVA_BIOME_MAP == null)
					LAVA_BIOME_MAP = new HashMap<RegistryKey<Biome>, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>>();

				return LAVA_BIOME_MAP.computeIfAbsent(key, biome -> new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>());
			}
			else {
				if (BIOME_MAP == null)
					BIOME_MAP = new HashMap<RegistryKey<Biome>, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>>();

				return BIOME_MAP.computeIfAbsent(key, biome -> new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>());
			}
		}

		private GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getOrCreateCategoryPool(boolean forLava, Biome.Category category) {
			if (forLava) {
				if (LAVA_CATEGORY_MAP == null)
					LAVA_CATEGORY_MAP = new HashMap<Biome.Category, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>>();

				return LAVA_CATEGORY_MAP.computeIfAbsent(category, cat -> new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>());
			}
			else {
				if (CATEGORY_MAP == null)
					CATEGORY_MAP = new HashMap<Biome.Category, GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>>();

				return CATEGORY_MAP.computeIfAbsent(category, cat -> new GenericEntryPool<Function<World, Entity>, ServerPlayerEntity>());
			}
		}

		private GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getEntityList(Biome biome, boolean isLava) {
			return isLava ? getLavaEntry(biome) : getWaterEntry(biome);
		}

		private GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getLavaEntry(Biome biome) {
			RegistryKey<Biome> registryKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());

			if (LAVA_BIOME_MAP != null && LAVA_BIOME_MAP.containsKey(registryKey))
				return LAVA_BIOME_MAP.get(registryKey);

			if (LAVA_CATEGORY_MAP != null && LAVA_CATEGORY_MAP.containsKey(biome.getBiomeCategory()))
				return LAVA_CATEGORY_MAP.get(biome.getBiomeCategory());

			return LAVA_FALLBACK;
		}

		private GenericEntryPool<Function<World, Entity>, ServerPlayerEntity> getWaterEntry(Biome biome) {
			RegistryKey<Biome> registryKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());

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
