package net.tslat.aoa3.common.registration;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.worldgen.carvers.*;
import net.tslat.aoa3.worldgen.chunkgenerator.AdjustableOceanChunkBuilder;
import net.tslat.aoa3.worldgen.chunkgenerator.CavernChunkBuilder;
import net.tslat.aoa3.worldgen.chunkgenerator.GenericAoAChunkBuilder;
import net.tslat.aoa3.worldgen.chunkgenerator.StructureChunkBuilder;
import net.tslat.aoa3.worldgen.chunkgenerator.config.CavernGenerationSettings;
import net.tslat.aoa3.worldgen.features.BiomeFriendlyLakeFeature;
import net.tslat.aoa3.worldgen.structures.nether.proper.FireRuneShrinePiece;
import net.tslat.aoa3.worldgen.structures.nether.proper.FireRuneShrineStructure;
import net.tslat.aoa3.worldgen.structures.nether.proper.NethengeicPitPiece;
import net.tslat.aoa3.worldgen.structures.nether.proper.NethengeicPitStructure;
import net.tslat.aoa3.worldgen.structures.overworld.proper.*;
import net.tslat.aoa3.worldgen.surfacebuilder.OceanlessSurfaceBuilder;
import net.tslat.aoa3.worldgen.surfacebuilder.TieredSurfaceBuilder;
import net.tslat.aoa3.worldgen.surfacebuilder.VoidSurfaceBuilder;
import net.tslat.aoa3.worldgen.surfacebuilder.config.TieredSurfaceBuilderConfig;

import java.util.Locale;
import java.util.function.Supplier;

public final class AoAWorldGen {
	public static class SurfaceBuilders {
		public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, AdventOfAscension.MOD_ID);

		static {
			registerSurfaceBuilders();
		}

		public static final SurfaceBuilder<SurfaceBuilderConfig> OCEANLESS = new OceanlessSurfaceBuilder(SurfaceBuilderConfig::deserialize);
		public static final SurfaceBuilder<SurfaceBuilderConfig> VOID = new VoidSurfaceBuilder((Dynamic<?> dataFixer) -> new SurfaceBuilderConfig(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState()));
		public static final SurfaceBuilder<TieredSurfaceBuilderConfig> TIERED = new TieredSurfaceBuilder(TieredSurfaceBuilderConfig::deserialize);

		private static void registerSurfaceBuilders() {
			SURFACE_BUILDERS.register("oceanless", () -> OCEANLESS);
			SURFACE_BUILDERS.register("void", () -> VOID);
			SURFACE_BUILDERS.register("tiered", () -> TIERED);
		}
	}

	public static class ChunkGeneratorTypes {
		public static final DeferredRegister<ChunkGeneratorType<?, ?>> CHUNKGEN_TYPES = DeferredRegister.create(ForgeRegistries.CHUNK_GENERATOR_TYPES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<ChunkGeneratorType<GenerationSettings, GenericAoAChunkBuilder>> GENERIC = registerChunkGenerator("generic", () -> new ChunkGeneratorType<GenerationSettings, GenericAoAChunkBuilder>(GenericAoAChunkBuilder::new, true, GenerationSettings::new));
		public static final RegistryObject<ChunkGeneratorType<GenerationSettings, StructureChunkBuilder>> STRUCTURE = registerChunkGenerator("structure", () -> new ChunkGeneratorType<GenerationSettings, StructureChunkBuilder>(StructureChunkBuilder::new, false, GenerationSettings::new));
		public static final RegistryObject<ChunkGeneratorType<OverworldGenSettings, AdjustableOceanChunkBuilder>> ADJUSTABLE_OCEAN = registerChunkGenerator("adjustable_ocean", () -> new ChunkGeneratorType<OverworldGenSettings, AdjustableOceanChunkBuilder>(AdjustableOceanChunkBuilder::new, false, OverworldGenSettings::new));
		public static final RegistryObject<ChunkGeneratorType<CavernGenerationSettings, CavernChunkBuilder>> CAVERN = registerChunkGenerator("cavern", () -> new ChunkGeneratorType<CavernGenerationSettings, CavernChunkBuilder>(CavernChunkBuilder::new, false, CavernGenerationSettings::new));

		private static <S extends GenerationSettings, B extends ChunkGenerator<S>> RegistryObject<ChunkGeneratorType<S, B>> registerChunkGenerator(String id, Supplier<ChunkGeneratorType<S, B>> chunkGenerator) {
			return CHUNKGEN_TYPES.register(id, chunkGenerator);
		}
	}

	public static class Carvers {
		public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, AdventOfAscension.MOD_ID);

		static {
			registerWorldCarvers();
		}

		public static final WorldCarver<ProbabilityConfig> ABYSS_CAVES = new AbyssCaveCarver(ProbabilityConfig::deserialize, 256);
		public static final WorldCarver<ProbabilityConfig> BARATHOS_CAVES = new BarathosCaveCarver(ProbabilityConfig::deserialize, 256);
		public static final WorldCarver<ProbabilityConfig> CREEPONIA_CAVES = new CreeponiaCaveCarver(ProbabilityConfig::deserialize, 256);
		public static final WorldCarver<ProbabilityConfig> GRECKON_CAVES = new GreckonCaveCarver(ProbabilityConfig::deserialize, 256);
		public static final WorldCarver<ProbabilityConfig> IROMINE_CAVES = new IromineCaveCarver(ProbabilityConfig::deserialize, 256);
		public static final WorldCarver<ProbabilityConfig> PRECASIA_CAVES = new PrecasiaCaveCarver(ProbabilityConfig::deserialize, 256);

		private static void registerWorldCarvers() {
			CARVERS.register("abyss_caves", () -> ABYSS_CAVES);
			CARVERS.register("barathos_caves", () -> BARATHOS_CAVES);
			CARVERS.register("creeponia_caves", () -> CREEPONIA_CAVES);
			CARVERS.register("greckon_caves", () -> GRECKON_CAVES);
			CARVERS.register("iromine_caves", () -> IROMINE_CAVES);
			CARVERS.register("precasia_caves", () -> PRECASIA_CAVES);
		}
	}

	public static class Structures {
		public static final DeferredRegister<Feature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.FEATURES, AdventOfAscension.MOD_ID);

		public static final IStructurePieceType AMCOVE = register(AmphibiyteCovePiece::new, "AoAAmCove");
		public static final IStructurePieceType RTFrame = register(RuinedTeleporterFramePiece::new, "AoARTFrame");
		public static final IStructurePieceType WRShrine = register(WindRuneShrinePiece::new, "WRShrine");
		public static final IStructurePieceType NethPit = register(NethengeicPitPiece::new, "NethPit");
		public static final IStructurePieceType FRShrine = register(FireRuneShrinePiece::new, "FRShrine");

		public static final RegistryObject<? extends Feature<?>> AMPHIBIYTE_COVE = register("amphibiyte_cove", () -> new AmphibiyteCoveStructure(NoFeatureConfig::deserialize));
		public static final RegistryObject<? extends Feature<?>> WIND_RUNE_SHRINE = register("wind_rune_shrine", () -> new WindRuneShrineStructure(NoFeatureConfig::deserialize));
		public static final RegistryObject<? extends Feature<?>> RUINED_TELEPORTER_FRAME = register("ruined_teleporter_frame", () -> new RuinedTeleporterFrameStructure(NoFeatureConfig::deserialize));
		public static final RegistryObject<? extends Feature<?>> NETHENGEIC_PIT = register("nethengeic_pit", () -> new NethengeicPitStructure(NoFeatureConfig::deserialize));
		public static final RegistryObject<? extends Feature<?>> FIRE_RUNE_SHRINE = register("fire_rune_shrine", () -> new FireRuneShrineStructure(NoFeatureConfig::deserialize));

		private static RegistryObject<? extends Feature<?>> register(String id, Supplier<Feature<?>> structure) {
			return STRUCTURES.register(id, structure);
		}

		private static IStructurePieceType register(IStructurePieceType type, String key) {
			return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), type);
		}
	}

	public static class Features {
		public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AdventOfAscension.MOD_ID);

		public static final Supplier<Feature<BlockStateFeatureConfig>> BIOME_FRIENDLY_LAKE = register("biome_friendly_lake", () -> new BiomeFriendlyLakeFeature(BlockStateFeatureConfig::deserialize));

		private static <C extends IFeatureConfig> Supplier<Feature<C>> register(String id, Supplier<Feature<C>> feature) {
			FEATURES.register(id, feature);

			return feature;
		}
	}
}
