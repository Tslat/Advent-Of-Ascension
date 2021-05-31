package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.worldgen.carver.carvers.ConfigurableCarver;
import net.tslat.aoa3.worldgen.carver.config.ConfigurableCarverConfig;
import net.tslat.aoa3.worldgen.feature.features.BlockPileFeature;
import net.tslat.aoa3.worldgen.feature.features.*;
import net.tslat.aoa3.worldgen.feature.features.config.ColumnConfig;
import net.tslat.aoa3.worldgen.feature.features.config.*;
import net.tslat.aoa3.worldgen.feature.features.trees.*;
import net.tslat.aoa3.worldgen.feature.placement.*;
import net.tslat.aoa3.worldgen.feature.placement.config.IntRangeConfig;
import net.tslat.aoa3.worldgen.feature.placement.config.*;

import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class AoAFeatures {
	public static class Features {
		public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AdventOfAscension.MOD_ID);

		public static final RegistryObject<Feature<BlockStateFeatureConfig>> NO_CONTEXT_BLOCK = register("no_context_block", () -> new NoContextBlockFeature(BlockStateFeatureConfig.CODEC));
		public static final RegistryObject<Feature<CappedColumnConfig>> CAPPED_COLUMN = register("capped_column", () -> new CappedColumnFeature(CappedColumnConfig.CODEC));
		public static final RegistryObject<Feature<ColumnConfig>> COLUMN = register("column", () -> new ColumnFeature(ColumnConfig.CODEC));
		public static final RegistryObject<Feature<LiquidDrainConfig>> LIQUID_DRAIN = register("liquid_drain", () -> new LiquidDrainFeature(LiquidDrainConfig.CODEC));
		public static final RegistryObject<Feature<BlockStateFeatureConfig>> BIOME_FRIENDLY_LAKE = register("biome_friendly_lake", () -> new BiomeFriendlyLake(BlockStateFeatureConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> BLOCK_PILE = register("block_pile", () -> new BlockPileFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> CUBE = register("cube", () -> new CubeFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<StructureFeatureConfig>> STRUCTURE_PIECE = register("structure_piece", () -> new StructurePieceGenFeature(StructureFeatureConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> BENDY_COLUMN = register("bendy_column", () -> new BendyColumnFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> STALAGMITE = register("stalagmite", () -> new StalagmiteFeature(MiscStateAndVariablesConfig.CODEC));
		public static final RegistryObject<Feature<MiscStateAndVariablesConfig>> RANDOM_BLOB = register("random_blob", () -> new RandomBlobFeature(MiscStateAndVariablesConfig.CODEC));

		public static final RegistryObject<AchonyTreeFeature> ACHONY_TREE = register("achony_tree", () -> new AchonyTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.ACHONY_SAPLING));
		public static final RegistryObject<BloodtwisterTreeFeature> BLOODTWISTER_TREE = register("bloodtwister_tree", () -> new BloodtwisterTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.BLOODTWISTER_SAPLING));
		public static final RegistryObject<BlueCelevusTreeFeature> BLUE_CELEVUS_TREE = register("blue_celevus_tree", () -> new BlueCelevusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.BLUE_CELEVUS_SAPLING));
		public static final RegistryObject<BlueHavenTreeFeature> BLUE_HAVEN_TREE = register("blue_haven_tree", () -> new BlueHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.BLUE_HAVEN_SAPLING));
		public static final RegistryObject<BrightShyreTreeFeature> BRIGHT_SHYRE_TREE = register("bright_shyre_tree", () -> new BrightShyreTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.BRIGHT_SHYRE_SAPLING));
		public static final RegistryObject<ChurryTreeFeature> CHURRY_TREE = register("churry_tree", () -> new ChurryTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.CHURRY_SAPLING));
		public static final RegistryObject<CreepTreeFeature> CREEP_TREE = register("creep_tree", () -> new CreepTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.CREEP_SAPLING));
		public static final RegistryObject<DawnwoodTreeFeature> DAWNWOOD_TREE = register("dawnwood_tree", () -> new DawnwoodTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.DAWNWOOD_SAPLING));
		public static final RegistryObject<EyebushTreeFeature> EYEBUSH_TREE = register("eyebush_tree", () -> new EyebushTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.EYEBUSH_SAPLING));
		public static final RegistryObject<EyeHangerTreeFeature> EYE_HANGER_TREE = register("eye_hanger_tree", () -> new EyeHangerTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.GREEN_CELEVUS_SAPLING));
		public static final RegistryObject<GreenCelevusTreeFeature> GREEN_CELEVUS_TREE = register("green_celevus_tree", () -> new GreenCelevusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.GREEN_CELEVUS_SAPLING));
		public static final RegistryObject<HauntedTreeFeature> HAUNTED_TREE = register("haunted_tree", () -> new HauntedTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.HAUNTED_SAPLING));
		public static final RegistryObject<InvertedAchonyTreeFeature> INVERTED_ACHONY_TREE = register("inverted_achony_tree", () -> new InvertedAchonyTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.ACHONY_SAPLING));
		public static final RegistryObject<InvertedChurryTreeFeature> INVERTED_CHURRY_TREE = register("inverted_churry_tree", () -> new InvertedChurryTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.CHURRY_SAPLING));
		public static final RegistryObject<IrodustTreeFeature> IRODUST_TREE = register("irodust_tree", () -> new IrodustTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.IRODUST_SAPLING));
		public static final RegistryObject<IrogoldTreeFeature> IROGOLD_TREE = register("irogold_tree", () -> new IrogoldTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.IROGOLD_SAPLING));
		public static final RegistryObject<LucalusTreeFeature> LUCALUS_TREE = register("lucalus_tree", () -> new LucalusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.LUCALUS_SAPLING));
		public static final RegistryObject<LuniciaTreeFeature> LUNICIA_TREE = register("lunicia_tree", () -> new LuniciaTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.LUNICIA_SAPLING));
		public static final RegistryObject<LunossoTreeFeature> LUNOSSO_TREE = register("lunosso_tree", () -> new LunossoTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.LUNOSSO_SAPLING));
		public static final RegistryObject<NormalShyreTreeFeature> NORMAL_SHYRE_TREE = register("normal_shyre_tree", () -> new NormalShyreTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.SHYRE_SAPLING));
		public static final RegistryObject<PinkHavenTreeFeature> PINK_HAVEN_TREE = register("pink_haven_tree", () -> new PinkHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.PINK_HAVEN_SAPLING));
		public static final RegistryObject<PurpleCelevusTreeFeature> PURPLE_CELEVUS_TREE = register("purple_celevus_tree", () -> new PurpleCelevusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.PURPLE_CELEVUS_SAPLING));
		public static final RegistryObject<PurpleHavenTreeFeature> PURPLE_HAVEN_TREE = register("purple_haven_tree", () -> new PurpleHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.PURPLE_HAVEN_SAPLING));
		public static final RegistryObject<RedCelevusTreeFeature> RED_CELEVUS_TREE = register("red_celevus_tree", () -> new RedCelevusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.RED_CELEVUS_SAPLING));
		public static final RegistryObject<RedHavenTreeFeature> RED_HAVEN_TREE = register("red_haven_tree", () -> new RedHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.RED_HAVEN_SAPLING));
		public static final RegistryObject<RunicTreeFeature> RUNIC_TREE = register("runic_tree", () -> new RunicTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.RUNIC_SAPLING));
		public static final RegistryObject<ShadowTreeFeature> SHADOW_TREE = register("shadow_tree", () -> new ShadowTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.SHADOW_SAPLING));
		public static final RegistryObject<StranglewoodTreeFeature> STRANGLEWOOD_TREE = register("stranglewood_tree", () -> new StranglewoodTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.STRANGLEWOOD_SAPLING));
		public static final RegistryObject<TurquoiseHavenTreeFeature> TURQUOISE_HAVEN_TREE = register("turquoise_haven_tree", () -> new TurquoiseHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.TURQUOISE_HAVEN_SAPLING));
		public static final RegistryObject<YellowCelevusTreeFeature> YELLOW_CELEVUS_TREE = register("yellow_celevus_tree", () -> new YellowCelevusTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.YELLOW_CELEVUS_SAPLING));
		public static final RegistryObject<YellowHavenTreeFeature> YELLOW_HAVEN_TREE = register("yellow_haven_tree", () -> new YellowHavenTreeFeature(BlockStateFeatureConfig.CODEC, AoABlocks.YELLOW_HAVEN_SAPLING));

		private static <C extends IFeatureConfig, F extends Feature<C>> RegistryObject<F> register(String id, Supplier<F> feature) {
			return FEATURES.register(id, feature);
		}
	}

	public static class Carvers {
		public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, AdventOfAscension.MOD_ID);

		public static final RegistryObject<WorldCarver<ConfigurableCarverConfig>> CONFIGURABLE_CARVER = register("configurable_carver", () -> new ConfigurableCarver(ConfigurableCarverConfig.CODEC));

		private static <C extends ICarverConfig> RegistryObject<WorldCarver<C>> register(String id, Supplier<WorldCarver<C>> carver) {
			return CARVERS.register(id, carver);
		}
	}

	public static class Placements {
		public static final DeferredRegister<Placement<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, AdventOfAscension.MOD_ID);

		public static final RegistryObject<Placement<NoPlacementConfig>> ALL_X = register("all_x", () -> new AllXPlacement(NoPlacementConfig.CODEC));
		public static final RegistryObject<Placement<TopSolidRangeConfig>> Y_FILL = register("y_fill", () -> new YFillPlacement(TopSolidRangeConfig.CODEC));
		public static final RegistryObject<Placement<TopSolidRangeConfig>> Y_FILL_CEILING = register("y_fill_ceiling", () -> new YFillCeilingPlacement(TopSolidRangeConfig.CODEC));
		public static final RegistryObject<Placement<NoPlacementConfig>> ALL_Z = register("all_z", () -> new AllZPlacement(NoPlacementConfig.CODEC));
		public static final RegistryObject<Placement<DepthAverageConfig>> AVERAGED_DEPTH = register("averaged_depth", () -> new AveragedDepthPlacement(DepthAverageConfig.CODEC));
		public static final RegistryObject<Placement<IntRangeConfig>> UP_TO_SURFACE = register("up_to_surface", () -> new UpToSurfacePlacement(IntRangeConfig.CODEC));
		public static final RegistryObject<Placement<UndergroundSurfaceConfig>> UNDERGROUND_SURFACE = register("underground_surface", () -> new UndergroundSurfacePlacement(UndergroundSurfaceConfig.CODEC));
		public static final RegistryObject<Placement<IntRangeConfig>> N_TIMES = register("n_times", () -> new NTimesPlacement(IntRangeConfig.CODEC));
		public static final RegistryObject<Placement<IntRangeConfig>> ABOVE_HEIGHTMAP = register("above_heightmap", () -> new AboveHeightmapPlacement(IntRangeConfig.CODEC));
		public static final RegistryObject<Placement<CountDividedRangeConfig>> WORLD_HEIGHT_DEPENDENT = register("world_height_dependent", () -> new CountWorldHeightDependentPlacement(CountDividedRangeConfig.CODEC));
		public static final RegistryObject<Placement<BlockStatePlacementConfig>> ONLY_ON = register("only_on", () -> new OnlyOnPlacement(BlockStatePlacementConfig.CODEC));
		public static final RegistryObject<Placement<OffsetPlacementConfig>> OFFSET = register("offset", () -> new OffsetPlacement(OffsetPlacementConfig.CODEC));

		private static <C extends IPlacementConfig> RegistryObject<Placement<C>> register(String id, Supplier<Placement<C>> feature) {
			return PLACEMENTS.register(id, feature);
		}
	}

	public static class Configured {
		private static final ConfiguredFeature<?, ?> AMETHYST_ORE = register("ore_amethyst",
				Feature.ORE.configured(
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.AMETHYST_ORE.get().defaultBlockState(), 7))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(14, 0, 17)))
						.squared()
						.count(5));
		private static final ConfiguredFeature<?, ?> ROSITE_ORE = register("ore_rosite",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.ROSITE_ORE.get().defaultBlockState(), 9))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(17, 0, 31)))
						.squared()
						.count(4));
		private static final ConfiguredFeature<?, ?> LIMONITE_ORE = register("ore_limonite",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.LIMONITE_ORE.get().defaultBlockState(), 11))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(8, 0, 60)))
						.squared()
						.count(5));
		private static final ConfiguredFeature<?, ?> RUNIUM_ORE = register("ore_runium",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.RUNIUM_ORE.get().defaultBlockState(), 13))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(5, 0, 128)))
						.squared()
						.count(5));
		private static final ConfiguredFeature<?, ?> JADE_ORE = register("ore_jade",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.JADE_ORE.get().defaultBlockState(), 7))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(7, 0, 13)))
						.squared()
						.count(4));
		private static final ConfiguredFeature<?, ?> SAPPHIRE_ORE = register("ore_sapphire",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, AoABlocks.SAPPHIRE_ORE.get().defaultBlockState(), 6))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(4, 0, 8)))
						.squared()
						.count(2));
		private static final ConfiguredFeature<?, ?> EMBERSTONE_ORE = register("ore_emberstone",
				Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, AoABlocks.EMBERSTONE_ORE.get().defaultBlockState(), 7))
						.decorated(net.minecraft.world.gen.feature.Features.Placements.RANGE_10_20_ROOFED)
						.squared()
						.count(15));

		public static void postInit() {}

		private static <C extends IFeatureConfig, F extends Feature<C>> ConfiguredFeature<C, F> register(String id, ConfiguredFeature<C, F> feature) {
			return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(AdventOfAscension.MOD_ID, id), feature);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoad(final BiomeLoadingEvent ev) {
		if (ev.getName() == null)
			return;

		RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, ev.getName());
		Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biomeKey);
		BiomeGenerationSettingsBuilder builder = ev.getGeneration();

		if (biomeTypes.contains(BiomeDictionary.Type.OVERWORLD)) {
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.AMETHYST_ORE);
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.ROSITE_ORE);
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.LIMONITE_ORE);
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.RUNIUM_ORE);
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.JADE_ORE);
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.SAPPHIRE_ORE);
		}
		else if (biomeTypes.contains(BiomeDictionary.Type.NETHER)) {
			builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.EMBERSTONE_ORE);
		}
	}
}
