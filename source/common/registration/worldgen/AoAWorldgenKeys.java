package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoAWorldgenKeys {
	public static final class Features {
		public static final class Configured {
			// Generic
			public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_CACTUS = key("tall_cactus");
			public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_CACTUS_PATCH = key("tall_cactus_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BETTER_CLAY_DISK = key("better_clay_disk");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BETTER_SAND_DISK = key("better_sand_disk");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BETTER_GRAVEL_DISK = key("better_gravel_disk");
			public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_DISK = key("mud_disk");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_DISK = key("basalt_disk");
			public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVEL_ORE = key("gravel_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_ORE = key("granite_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_ORE = key("diorite_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_ORE = key("andesite_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_ORE = key("tuff_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_MAGMA_BLOBS = key("basalt_magma_blobs");
			public static final ResourceKey<ConfiguredFeature<?, ?>> HOT_SPRINGS = key("hot_springs");

			// Overworld
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE_LARGE = key("ore_jade_large");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_JADE_SMALL = key("ore_jade_small");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMONITE_LARGE = key("ore_limonite_large");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMONITE_SMALL = key("ore_limonite_small");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUNIUM_SMALL = key("ore_runium_small");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUNIUM_LARGE = key("ore_runium_large");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BRUSHABLE_JUNGLE_GRAVEL = key("ore_brushable_jungle_gravel");

			// Nether
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_EMBERSTONE = key("ore_emberstone");
			public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_RUNIUM = key("ore_nether_runium");

			// Precasia
			public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_CALAB_OR_VANILLA_GRASS = key("single_calab_or_vanilla_grass");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_SPRING = key("precasian_lava_spring");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_SPRING = key("precasian_spring");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_LAKE = key("precasian_lava_lake");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAKE = key("precasian_lake");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_TAR_PIT = key("precasian_tar_pit");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_BLOB = key("precasian_lava_blob");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_GRASS_PATCH = key("precasian_grass_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_TALL_GRASS_PATCH = key("precasian_tall_grass_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_JUNGLE_GRASS_PATCH = key("precasian_jungle_grass_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_ASHFERN = key("single_ashfern");
			public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_PRECASIAN_GRASS_ASSORTMENT = key("single_precasian_grass_assortment");
			public static final ResourceKey<ConfiguredFeature<?, ?>> LUCALUS_TREE = key("lucalus_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_TREE = key("baobab_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_STRANGLEWOOD_TREE = key("giant_stranglewood_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> STRANGLEWOOD_TREE = key("stranglewood_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> SPINDLY_STRANGLEWOOD_TREE = key("spindly_stranglewood_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> CAVES_TALL_SPINDLY_STRANGLEWOOD_TREE = key("tall_spindly_stranglewood_tree");
			public static final ResourceKey<ConfiguredFeature<?, ?>> STRANGLEWOOD_BUSH = key("stranglewood_bush");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_JUNGLE_GROUND_FLORA = key("precasian_jungle_ground_flora");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DEADLANDS_DEAD_TREE_1 = key("deadlands_dead_tree_1");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DEADLANDS_DEAD_TREE_2 = key("deadlands_dead_tree_2");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DEADLANDS_DEAD_TREES = key("deadlands_dead_trees");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_IRON_ORE = key("precasian_iron_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_SMALL_IRON_ORE = key("precasian_small_iron_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_BURIED_GOLD_ORE = key("precasian_buried_gold_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_QUARTZ_ORE = key("precasian_quartz_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_BURIED_LAPIS_ORE = key("precasian_buried_lapis_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAPIS_ORE = key("precasian_lapis_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_COPPER_ORE = key("precasian_copper_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_REDSTONE_ORE = key("precasian_redstone_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_FRAGMENTS_ORE = key("bone_fragments_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_CHUTE = key("precasian_lava_chute");
			public static final ResourceKey<ConfiguredFeature<?, ?>> SKELETAL_REMAINS = key("skeletal_remains");
			public static final ResourceKey<ConfiguredFeature<?, ?>> STRANGLEWOOD_STUMP_DECORATION = key("stranglewood_stump_decoration");
			public static final ResourceKey<ConfiguredFeature<?, ?>> LUCALUS_STUMP_DECORATION = key("lucalus_stump_decoration");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_CAVE_MOSS = key("precasian_cave_moss");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_CAVE_MOSS_FLORA = key("precasian_cave_moss_flora");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_CAVE_MOSS_FOLIAGE = key("precasian_cave_moss_foliage");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_CAVE_CEILING_MOSS = key("precasian_cave_ceiling_moss");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_CAVE_CEILING_VINES = key("precasian_cave_ceiling_vines");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_SUSPICIOUS_CAVE_GRAVEL = key("precasian_suspicious_cave_gravel");
			public static final ResourceKey<ConfiguredFeature<?, ?>> OPTERYX_NEST = key("opteryx_nest");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DESERT_COCOON = key("desert_cocoon");

			// Barathos
			public static final ResourceKey<ConfiguredFeature<?, ?>> FIRE_PATCH = key("fire_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> HELLSTONE_LAVA_LAKE = key("hellstone_lava_lake");
			public static final ResourceKey<ConfiguredFeature<?, ?>> LOOSE_SAND_PATCH = key("loose_sand_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> DRIED_OASIS_LAKE = key("dried_oasis_lake");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BARON_STONE_PILLARS = key("baron_stone_pillars");
			public static final ResourceKey<ConfiguredFeature<?, ?>> VARSIUM_ORE = key("varsium_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BARONYTE_ORE = key("baronyte_ore");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BLAZIUM_ORE = key("blazium_ore");

			public static final ResourceKey<ConfiguredFeature<?, ?>> BARON_FOSSIL_SITE = key("baron_fossil_site");
			public static final ResourceKey<ConfiguredFeature<?, ?>> FALSE_BARON_OASIS = key("false_baron_oasis");

			public static final ResourceKey<ConfiguredFeature<?, ?>> BARATHOS_SAND_SHRUBS = key("barathos_sand_shrubs");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BARATHOS_STONE_SHRUBS = key("barathos_stone_shrubs");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BARATHOS_SHRUBS = key("barathos_shrubs");
			public static final ResourceKey<ConfiguredFeature<?, ?>> BARATHOS_DEAD_TREE = key("barathos_dead_tree");

			private static ResourceKey<ConfiguredFeature<?, ?>> key(String id) {
				return ResourceKey.create(Registries.CONFIGURED_FEATURE, AdventOfAscension.id(id));
			}
		}

		public static final class Placed {
			// Generic
			public static final ResourceKey<PlacedFeature> TALL_CACTUS = key("tall_cactus");
			public static final ResourceKey<PlacedFeature> TALL_CACTUS_PATCH = key("tall_cactus_patch");
			public static final ResourceKey<PlacedFeature> BETTER_SAND_DISK = key("better_sand_disk");
			public static final ResourceKey<PlacedFeature> BETTER_CLAY_DISK = key("better_clay_disk");
			public static final ResourceKey<PlacedFeature> BETTER_GRAVEL_DISK = key("better_gravel_disk");
			public static final ResourceKey<PlacedFeature> MUD_DISK = key("mud_disk");
			public static final ResourceKey<PlacedFeature> BASALT_DISK = key("basalt_disk");
			public static final ResourceKey<PlacedFeature> GRAVEL_ORE = key("gravel_ore");
			public static final ResourceKey<PlacedFeature> TUFF_ORE = key("tuff_ore");
			public static final ResourceKey<PlacedFeature> HIGH_ALTITUDE_GRANITE_ORE = key("high_altitude_granite_ore");
			public static final ResourceKey<PlacedFeature> LOW_ALTITUDE_GRANITE_ORE = key("low_altitude_granite_ore");
			public static final ResourceKey<PlacedFeature> HIGH_ALTITUDE_DIORITE_ORE = key("high_altitude_diorite_ore");
			public static final ResourceKey<PlacedFeature> LOW_ALTITUDE_DIORITE_ORE = key("low_altitude_diorite_ore");
			public static final ResourceKey<PlacedFeature> HIGH_ALTITUDE_ANDESITE_ORE = key("high_altitude_andesite_ore");
			public static final ResourceKey<PlacedFeature> LOW_ALTITUDE_ANDESITE_ORE = key("low_altitude_andesite_ore");
			public static final ResourceKey<PlacedFeature> BASALT_MAGMA_BLOBS = key("basalt_magma_blobs");
			public static final ResourceKey<PlacedFeature> HOT_SPRINGS = key("hot_springs");

			// Overworld
			public static final ResourceKey<PlacedFeature> ORE_JADE_LARGE = key("ore_jade_large");
			public static final ResourceKey<PlacedFeature> ORE_JADE_SMALL = key("ore_jade_small");
			public static final ResourceKey<PlacedFeature> ORE_LIMONITE_LARGE = key("ore_limonite_large");
			public static final ResourceKey<PlacedFeature> ORE_LIMONITE_SMALL = key("ore_limonite_small");
			public static final ResourceKey<PlacedFeature> ORE_RUNIUM_SMALL = key("ore_runium_small");
			public static final ResourceKey<PlacedFeature> ORE_RUNIUM_LARGE = key("ore_runium_large");
			public static final ResourceKey<PlacedFeature> ORE_BRUSHABLE_JUNGLE_GRAVEL = key("ore_brushable_jungle_gravel");

			// Nether
			public static final ResourceKey<PlacedFeature> ORE_EMBERSTONE = key("ore_emberstone");
			public static final ResourceKey<PlacedFeature> ORE_NETHER_RUNIUM = key("ore_nether_runium");

			// Precasia
			public static final ResourceKey<PlacedFeature> PRECASIAN_GRASS_BONEMEAL = key("precasian_grass_bonemeal");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LAVA_SPRING = key("precasian_lava_spring");
			public static final ResourceKey<PlacedFeature> PRECASIAN_SPRING = key("precasian_spring");
			public static final ResourceKey<PlacedFeature> UNDERGROUND_PRECASIAN_LAVA_LAKE = key("underground_precasian_lava_lake");
			public static final ResourceKey<PlacedFeature> SURFACE_PRECASIAN_LAVA_LAKE = key("surface_precasian_lava_lake");
			public static final ResourceKey<PlacedFeature> SURFACE_PRECASIAN_LAKE = key("surface_precasian_lake");
			public static final ResourceKey<PlacedFeature> PRECASIAN_TAR_PIT = key("precasian_tar_pit");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LAVA_BLOBS = key("precasian_lava_blobs");
			public static final ResourceKey<PlacedFeature> PRECASIAN_GRASS_PATCH = key("precasian_grass_patch");
			public static final ResourceKey<PlacedFeature> PRECASIAN_TALL_GRASS_PATCH = key("precasian_tall_grass_patch");
			public static final ResourceKey<PlacedFeature> PRECASIAN_JUNGLE_GRASS_PATCH = key("precasian_jungle_grass_patch");
			public static final ResourceKey<PlacedFeature> SPARSE_ASHFERN = key("sparse_ashfern");
			public static final ResourceKey<PlacedFeature> PRECASIAN_SPARSE_GRASS = key("precasian_sparse_grass");
			public static final ResourceKey<PlacedFeature> SPARSE_LUCALUS_TREES = key("sparse_lucalus_trees");
			public static final ResourceKey<PlacedFeature> RARE_BAOBAB_TREES = key("rare_baobab_trees");
			public static final ResourceKey<PlacedFeature> COMMON_BAOBAB_TREES = key("common_baobab_trees");
			public static final ResourceKey<PlacedFeature> SPARSE_ACACIA_TREES = key("sparse_acacia_trees");
			public static final ResourceKey<PlacedFeature> DENSE_LUCALUS_TREES = key("dense_lucalus_trees");
			public static final ResourceKey<PlacedFeature> DENSE_STRANGLEWOOD_TREES = key("dense_stranglewood_trees");
			public static final ResourceKey<PlacedFeature> DENSE_GIANT_STRANGLEWOOD_TREES = key("dense_giant_stranglewood_trees");
			public static final ResourceKey<PlacedFeature> PRECASIAN_JUNGLE_GROUND_FLORA = key("precasian_jungle_ground_flora");
			public static final ResourceKey<PlacedFeature> SPINDLY_STRANGLEWOOD_TREE = key("single_spindly_stranglewood_tree");
			public static final ResourceKey<PlacedFeature> STRANGLEWOOD_BUSH = key("stranglewood_bush");
			public static final ResourceKey<PlacedFeature> DEADLANDS_DEAD_TREE_1 = key("deadlands_dead_tree_1");
			public static final ResourceKey<PlacedFeature> DEADLANDS_DEAD_TREE_2 = key("deadlands_dead_tree_2");
			public static final ResourceKey<PlacedFeature> DEADLANDS_DEAD_TREES = key("deadlands_dead_trees");
			public static final ResourceKey<PlacedFeature> PRECASIAN_HIGH_ALTITUDE_IRON_ORE = key("precasian_high_altitude_iron_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_IRON_ORE = key("precasian_iron_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LOW_ALTITUDE_IRON_ORE = key("precasian_low_altitude_iron_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_SMALL_IRON_ORE = key("precasian_small_iron_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_GOLD_ORE = key("precasian_gold_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LOW_ALTITUDE_GOLD_ORE = key("precasian_low_altitude_gold_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_REDSTONE_ORE = key("precasian_redstone_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LOW_ALTITUDE_REDSTONE_ORE = key("precasian_low_altitude_redstone_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LAPIS_ORE = key("precasian_lapis_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_BURIED_LAPIS_ORE = key("precasian_buried_lapis_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_COPPER_ORE = key("precasian_copper_ore");
			public static final ResourceKey<PlacedFeature> BONE_FRAGMENTS_ORE = key("bone_fragments_ore");
			public static final ResourceKey<PlacedFeature> SHALLOW_BONE_FRAGMENTS_ORE = key("shallow_bone_fragments_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_QUARTZ_ORE = key("precasian_quartz_ore");
			public static final ResourceKey<PlacedFeature> PRECASIAN_VOLCANO_CHUTE = key("precasian_volcano_chute");
			public static final ResourceKey<PlacedFeature> SKELETAL_REMAINS = key("skeletal_remains");
			public static final ResourceKey<PlacedFeature> STRANGLEWOOD_STUMP_DECORATIONS = key("stranglewood_stump_decorations");
			public static final ResourceKey<PlacedFeature> LUCALUS_STUMP_DECORATIONS = key("lucalus_stump_decorations");
			public static final ResourceKey<PlacedFeature> PRECASIAN_CAVE_FLOOR_VEGETATION = key("precasian_cave_floor_vegetation");
			public static final ResourceKey<PlacedFeature> PRECASIAN_CAVE_CEILING_VEGETATION = key("precasian_cave_ceiling_vegetation");
			public static final ResourceKey<PlacedFeature> PRECASIAN_SUSPICIOUS_CAVE_GRAVEL = key("precasian_suspicious_cave_gravel");
			public static final ResourceKey<PlacedFeature> DESERT_COCOONS = key("desert_cocoons");

			// Barathos
			public static final ResourceKey<PlacedFeature> FIRE_PATCH = key("fire_patch");
			public static final ResourceKey<PlacedFeature> HELLSTONE_LAVA_LAKE = key("hellstone_lava_lake");
			public static final ResourceKey<PlacedFeature> LOOSE_SAND_PATCH = key("loose_sand_patch");
			public static final ResourceKey<PlacedFeature> DRIED_OASIS_LAKE = key("dried_oasis_lake");
			public static final ResourceKey<PlacedFeature> BARON_STONE_PILLARS = key("baron_stone_pillars");
			public static final ResourceKey<PlacedFeature> VARSIUM_ORE = key("varsium_ore");
			public static final ResourceKey<PlacedFeature> BARONYTE_ORE = key("baronyte_ore");
			public static final ResourceKey<PlacedFeature> BLAZIUM_ORE = key("blazium_ore");

			public static final ResourceKey<PlacedFeature> BARON_FOSSIL_SITES = key("baron_fossil_sites");
			public static final ResourceKey<PlacedFeature> FALSE_BARON_OASIS = key("false_baron_oasis");

			public static final ResourceKey<PlacedFeature> BARATHOS_SAND_SHRUBS = key("barathos_sand_shrubs");
			public static final ResourceKey<PlacedFeature> BARATHOS_STONE_SHRUBS = key("barathos_stone_shrubs");
			public static final ResourceKey<PlacedFeature> BARATHOS_SHRUBS = key("barathos_shrubs");
			public static final ResourceKey<PlacedFeature> BARATHOS_DEAD_TREE = key("barathos_dead_tree");

			private static ResourceKey<PlacedFeature> key(String id) {
				return ResourceKey.create(Registries.PLACED_FEATURE, AdventOfAscension.id(id));
			}
		}
	}

	public static final class Structures {
		public static final ResourceKey<Structure> RUINED_TELEPORTER_FRAME = key("ruined_teleporter_frame");
		public static final ResourceKey<Structure> NETHENGEIC_PIT = key("nethengeic_pit");
		public static final ResourceKey<Structure> ATTERCOPUS_NEST = key("attercopus_nest");
		public static final ResourceKey<Structure> PRECASIAN_LOTTO_HOVEL = key("precasian_lotto_hovel");

		private static ResourceKey<Structure> key(String id) {
			return ResourceKey.create(Registries.STRUCTURE, AdventOfAscension.id(id));
		}
	}

	public static final class BiomeModifiers {
		// Overworld
		public static final ResourceKey<BiomeModifier> ORE_JADE_LARGE = key("ore_jade_large");
		public static final ResourceKey<BiomeModifier> ORE_JADE_SMALL = key("ore_jade_small");
		public static final ResourceKey<BiomeModifier> ORE_LIMONITE_LARGE = key("ore_limonite_large");
		public static final ResourceKey<BiomeModifier> ORE_LIMONITE_SMALL = key("ore_limonite_small");
		public static final ResourceKey<BiomeModifier> ORE_RUNIUM_SMALL = key("ore_runium_small");
		public static final ResourceKey<BiomeModifier> ORE_RUNIUM_LARGE = key("ore_runium_large");
		public static final ResourceKey<BiomeModifier> ORE_BRUSHABLE_JUNGLE_GRAVEL = key("ore_brushable_jungle_gravel");
		public static final ResourceKey<BiomeModifier> SHINY_SQUID_SPAWNS = key("shiny_squid_spawns");
		public static final ResourceKey<BiomeModifier> ICE_GIANT_SPAWNS = key("ice_giant_spawns");
		public static final ResourceKey<BiomeModifier> LEAFY_GIANT_SPAWNS = key("leafy_giant_spawns");
		public static final ResourceKey<BiomeModifier> STONE_GIANT_SPAWNS = key("stone_giant_spawns");
		public static final ResourceKey<BiomeModifier> WOOD_GIANT_SPAWNS = key("wood_giant_spawns");
		public static final ResourceKey<BiomeModifier> SAND_GIANT_SPAWNS = key("sand_giant_spawns");
		public static final ResourceKey<BiomeModifier> CHARGER_SPAWNS = key("charger_spawns");
		public static final ResourceKey<BiomeModifier> KING_CHARGER_SPAWNS = key("king_charger_spawns");
		public static final ResourceKey<BiomeModifier> CYCLOPS_SPAWNS = key("cyclops_spawns");
		public static final ResourceKey<BiomeModifier> CHOMPER_SPAWNS = key("chomper_spawns");
		public static final ResourceKey<BiomeModifier> SASQUATCH_SPAWNS = key("sasquatch_spawns");
		public static final ResourceKey<BiomeModifier> BUSH_BABY_SPAWNS = key("bush_baby_spawns");
		public static final ResourceKey<BiomeModifier> VOID_WALKER_SPAWNS = key("void_walker_spawns");
		public static final ResourceKey<BiomeModifier> END_VOID_WALKER_SPAWNS = key("end_void_walker_spawns");
		public static final ResourceKey<BiomeModifier> ANCIENT_GOLEM_SPAWNS = key("ancient_golem_spawns");
		public static final ResourceKey<BiomeModifier> GHOST_SPAWNS = key("ghost_spawns");
		public static final ResourceKey<BiomeModifier> BOMB_CARRIER_SPAWNS = key("bomb_carrier_spawns");
		public static final ResourceKey<BiomeModifier> TREE_SPIRIT_SPAWNS = key("tree_spirit_spawns");
		public static final ResourceKey<BiomeModifier> YETI_SPAWNS = key("yeti_spawns");
		public static final ResourceKey<BiomeModifier> GOBLIN_SPAWNS = key("goblin_spawns");

		// Nether
		public static final ResourceKey<BiomeModifier> ORE_NETHER_RUNIUM = key("ore_nether_runium");
		public static final ResourceKey<BiomeModifier> ORE_EMBERSTONE = key("ore_emberstone");
		public static final ResourceKey<BiomeModifier> LITTLE_BAM_SPAWNS = key("little_bam_spawns");
		public static final ResourceKey<BiomeModifier> FLAMEWALKER_SPAWNS = key("flamewalker_spawns");
		public static final ResourceKey<BiomeModifier> EMBRAKE_SPAWNS = key("embrake_spawns");
		public static final ResourceKey<BiomeModifier> INFERNAL_SPAWNS = key("infernal_spawns");

		private static ResourceKey<BiomeModifier> key(String id) {
			return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, AdventOfAscension.id(id));
		}
	}

	public static final class NoiseParams {
		public static final ResourceKey<NormalNoise.NoiseParameters> TEMPERATURE_MEDIUM = key("temperature_medium");
		public static final ResourceKey<NormalNoise.NoiseParameters> VEGETATION_MEDIUM = key("vegetation_medium");

		private static ResourceKey<NormalNoise.NoiseParameters> key(String id) {
			return ResourceKey.create(Registries.NOISE, AdventOfAscension.id(id));
		}
	}
}
