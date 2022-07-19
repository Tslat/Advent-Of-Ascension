package net.tslat.aoa3.data.server;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
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
import net.tslat.aoa3.util.TagUtil;

import java.util.ArrayList;
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
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.WATER_FALLBACK, false);
			}
			else if (id.equals(AdventOfAscension.id("fish_lava_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), FISH_RETRIEVER.LAVA_FALLBACK, true);
			}
			else if (id.equals(AdventOfAscension.id("traps_default"))) {
				parseEntityList(GsonHelper.getAsJsonArray(obj, "entities"), TRAPS_RETRIEVER.WATER_FALLBACK, false);
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
		ArrayList<TagKey<Biome>> tags = new ArrayList<>();
		ArrayList<ResourceKey<Biome>> biomes = new ArrayList<>();
		boolean forLava = GsonHelper.getAsBoolean(json, "for_lava", false);
		boolean forTraps = GsonHelper.getAsBoolean(json, "for_traps", false);

		if (json.has("tags")) {
			JsonArray tagArray = json.getAsJsonArray("tags");

			for (JsonElement element : tagArray) {
				tags.add(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(element.getAsString())));
			}
		}

		if (json.has("biomes")) {
			JsonArray biomeArray = json.getAsJsonArray("biomes");

			for (JsonElement element : biomeArray) {
				biomes.add(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(element.getAsString())));
			}
		}

		if (tags.isEmpty() && biomes.isEmpty())
			throw new IllegalArgumentException("No biome tags or biome ids listed, must have one or the other.");

		FishEntryRetriever retriever = forTraps ? TRAPS_RETRIEVER : FISH_RETRIEVER;

		if (!tags.isEmpty()) {
			for (TagKey<Biome> tag : tags) {
				GenericEntryPool<Function<Level, Entity>, ServerPlayer> list = retriever.getOrCreateBiomePool(forLava, tag);

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
					EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(obj.get("entity").getAsString()));

					if (entityType == null)
						continue;

					factory = entityType::create;
				}

				list.add(factory, predicate, weight, weightMod);
			}
		}
	}

	private static class FishEntryRetriever {
		private final GenericEntryPool<Function<Level, Entity>, ServerPlayer> WATER_FALLBACK = new GenericEntryPool<>();
		private final GenericEntryPool<Function<Level, Entity>, ServerPlayer> LAVA_FALLBACK = new GenericEntryPool<>();

		private Object2ObjectOpenHashMap<Object, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> WATER_MAP = null;
		private Object2ObjectOpenHashMap<Object, GenericEntryPool<Function<Level, Entity>, ServerPlayer>> LAVA_MAP = null;

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getOrCreateBiomePool(boolean forLava, Object biomeOrTag) {
			if (forLava) {
				if (LAVA_MAP == null)
					LAVA_MAP = new Object2ObjectOpenHashMap<>();

				return LAVA_MAP.computeIfAbsent(biomeOrTag, biome -> new GenericEntryPool<>());
			}
			else {
				if (WATER_MAP == null)
					WATER_MAP = new Object2ObjectOpenHashMap<>();

				return WATER_MAP.computeIfAbsent(biomeOrTag, biome -> new GenericEntryPool<>());
			}
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getEntityList(Biome biome, boolean isLava) {
			return isLava ? getLavaEntry(biome) : getWaterEntry(biome);
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getLavaEntry(Biome biome) {
			ResourceKey<Biome> resourceKey = ResourceKey.create(Registry.BIOME_REGISTRY, ForgeRegistries.BIOMES.getKey(biome));
			GenericEntryPool<Function<Level, Entity>, ServerPlayer> entry = LAVA_MAP.get(resourceKey);

			if (entry != null)
				return entry;

			TagKey<Biome> matchedTag = TagUtil.getAllTagsFor(TagUtil.biomeTags(), biome).filter(tag -> LAVA_MAP.containsKey(tag)).findFirst().orElse(null);

			if (matchedTag != null)
				return LAVA_MAP.get(matchedTag);

			return null;
		}

		private GenericEntryPool<Function<Level, Entity>, ServerPlayer> getWaterEntry(Biome biome) {
			ResourceKey<Biome> resourceKey = ResourceKey.create(Registry.BIOME_REGISTRY, ForgeRegistries.BIOMES.getKey(biome));
			GenericEntryPool<Function<Level, Entity>, ServerPlayer> entry = WATER_MAP.get(resourceKey);

			if (entry != null)
				return entry;

			TagKey<Biome> matchedTag = TagUtil.getAllTagsFor(TagUtil.biomeTags(), biome).filter(tag -> WATER_MAP.containsKey(tag)).findFirst().orElse(null);

			if (matchedTag != null)
				return WATER_MAP.get(matchedTag);

			return null;
		}

		private void reset() {
			WATER_FALLBACK.clear();
			LAVA_FALLBACK.clear();
			WATER_MAP = null;
			LAVA_MAP = null;
		}
	}
}
