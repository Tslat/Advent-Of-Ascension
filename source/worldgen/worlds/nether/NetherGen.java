package net.tslat.aoa3.worldgen.worlds.nether;

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
import net.tslat.aoa3.worldgen.structures.nether.proper.FireRuneShrineStructure;
import net.tslat.aoa3.worldgen.structures.nether.proper.NethengeicPitStructure;

import java.util.Set;

public class NetherGen {
	public static void init() {
		registerOreGen();
		registerStructures();
	}

	public static void registerOreGen() {
		for (Biome biome : BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER)) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, AoABlocks.EMBERSTONE_ORE.get().getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(6, 5, 0, 125))));
		}
	}

	public static void registerStructures() {
		ConfiguredFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> fireRuneShrine = ((FireRuneShrineStructure)AoAWorldGen.Structures.FIRE_RUNE_SHRINE.get()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
		ConfiguredFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> nethengeicPit = ((NethengeicPitStructure)AoAWorldGen.Structures.NETHENGEIC_PIT.get()).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

		Set<Biome> overworldbiomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER);

		for (Biome biome : BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER)) {
			biome.addStructure(fireRuneShrine);
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, fireRuneShrine.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			biome.addStructure(nethengeicPit);
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, nethengeicPit.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}
	}
}
