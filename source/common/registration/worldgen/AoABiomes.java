package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoABiomes {
	public static void postInit() {
		registerBiomeTypes();
		registerBiomeProviders();
	}

	public static void registerBiomeTypes() {
		applyBiomeTypes("abyssal_plains", BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY);
		applyBiomeTypes("barren_grounds", BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY);
		applyBiomeTypes("candy_hills", BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("celevian_highlands", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.VOID);
		applyBiomeTypes("creepoid_forest", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("crystevian_caverns", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET);
		applyBiomeTypes("cavern_depths", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("dustopian_forest", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST);
		applyBiomeTypes("floral_islands", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET, BiomeDictionary.Type.HILLS);
		applyBiomeTypes("greckon_mountains", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("haven", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID);
		applyBiomeTypes("iromine", BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("coral_fields", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN);
		applyBiomeTypes("lelyetian_plains", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT, BiomeDictionary.Type.VOID);
		applyBiomeTypes("asteroid_belt", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("fungal_caverns", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MUSHROOM);
		applyBiomeTypes("nowhere", BiomeDictionary.Type.VOID);
		applyBiomeTypes("precasian_tall_forest", BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("runic_cliffs", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MOUNTAIN);
		applyBiomeTypes("shyre_remnants", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT);
		applyBiomeTypes("vox_wastes", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD);
	}

	private static void applyBiomeTypes(String biomeId, BiomeDictionary.Type... types) {
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, biomeId));

		BiomeDictionary.addTypes(key, types);
	}

	private static void registerBiomeProviders() {
		//Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(AdventOfAscension.MOD_ID, "layered"), LayeredBiomeProvider.CODEC);
		//Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(AdventOfAscension.MOD_ID, "floating_islands"), FloatingIslandsBiomeProvider.CODEC);
	}
}
