package net.tslat.aoa3.worldgen.worlds.overworld;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.worldgen.structures.overworld.proper.AmphibiyteCoveStructure;
import net.tslat.aoa3.worldgen.structures.overworld.proper.RuinedTeleporterFrameStructure;
import net.tslat.aoa3.worldgen.structures.overworld.proper.WindRuneShrineStructure;

import java.util.Set;

public class OverworldGen {
	public static void init() {
		registerOreGen();
		registerStructures();
	}

	public static void registerOreGen() {
		for (Biome biome : BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD)) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.AMETHYST_ORE.get().getDefaultState(), 7)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 14, 0, 17))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.ROSITE_ORE.get().getDefaultState(), 10)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 17, 0, 31))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.LIMONITE_ORE.get().getDefaultState(), 12)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 8, 0, 60))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.RUNIUM_ORE.get().getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(7, 5, 0, 127))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.JADE_ORE.get().getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(5, 7, 0, 13))));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.SAPPHIRE_ORE.get().getDefaultState(), 4)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 4, 0, 8))));
		}
	}

	public static void registerStructures() {
		ConfiguredFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> windRuneShrine = ((WindRuneShrineStructure)AoAWorldGen.Structures.WIND_RUNE_SHRINE.get()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
		ConfiguredFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> ruinedPortalFrame = ((RuinedTeleporterFrameStructure)AoAWorldGen.Structures.RUINED_TELEPORTER_FRAME.get()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
		ConfiguredFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> amphibiyteCove = ((AmphibiyteCoveStructure)AoAWorldGen.Structures.AMPHIBIYTE_COVE.get()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

		Set<Biome> overworldbiomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD);

		for (Biome biome : overworldbiomes) {
			biome.addStructure(ruinedPortalFrame);
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ruinedPortalFrame.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}

		for (Biome oceanBiome : BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN)) {
			if (overworldbiomes.contains(oceanBiome)) {
				oceanBiome.addStructure(amphibiyteCove);
				oceanBiome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, amphibiyteCove.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			}
		}

		for (Biome mountainBiome : BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN)) {
			if (overworldbiomes.contains(mountainBiome)) {
				mountainBiome.addStructure(windRuneShrine);
				mountainBiome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, windRuneShrine.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			}
		}
	}
}
