package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.world.gen.biomeprovider.FloatingIslandsBiomeProvider;
import net.tslat.aoa3.world.gen.biomeprovider.LayeredBiomeProvider;

public final class AoABiomes {
	public static void postInit() {
		registerBiomeTypes();
		registerBiomeProviders();
	}

	public static void registerBiomeTypes() {
		applyBiomeTypes("abyss", BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY);
		applyBiomeTypes("abyssal_vents", BiomeDictionary.Type.DEAD, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WASTELAND);
		applyBiomeTypes("barathos", BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY);
		applyBiomeTypes("candyland", BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("celeve", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.VOID);
		applyBiomeTypes("creeponia", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("crystevia", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET);
		applyBiomeTypes("deeplands", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("dustopia", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST);
		applyBiomeTypes("gardencia", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET, BiomeDictionary.Type.HILLS);
		applyBiomeTypes("greckon", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL);
		applyBiomeTypes("haven", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID);
		applyBiomeTypes("iromine", BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("lborean", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
		applyBiomeTypes("lelyetia", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT, BiomeDictionary.Type.VOID);
		applyBiomeTypes("lunalus", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("mysterium", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MUSHROOM);
		applyBiomeTypes("nowhere", BiomeDictionary.Type.VOID);
		applyBiomeTypes("precasia", BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE);
		applyBiomeTypes("runandor", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MOUNTAIN);
		applyBiomeTypes("shyrelands", BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT);
		applyBiomeTypes("vox_ponds", BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD);
	}

	private static void applyBiomeTypes(String biomeId, BiomeDictionary.Type... types) {
		RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, biomeId));

		BiomeDictionary.addTypes(key, types);
	}

	private static void registerBiomeProviders() {
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(AdventOfAscension.MOD_ID, "layered"), LayeredBiomeProvider.CODEC);
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(AdventOfAscension.MOD_ID, "floating_islands"), FloatingIslandsBiomeProvider.CODEC);
	}
}
