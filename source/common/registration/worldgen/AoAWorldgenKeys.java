package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.tslat.aoa3.advent.AdventOfAscension;

public final class AoAWorldgenKeys {
	public static final class Features {
		public static final class Configured {
			public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_CALAB_OR_VANILLA_GRASS = key("single_calab_or_vanilla_grass");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_SPRING = key("precasian_lava_spring");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_SPRING = key("precasian_spring");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_LAVA_LAKE = key("precasian_lava_lake");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_GRASS_PATCH = key("precasian_grass_patch");
			public static final ResourceKey<ConfiguredFeature<?, ?>> PRECASIAN_TALL_GRASS_PATCH = key("precasian_tall_grass_patch");

			private static ResourceKey<ConfiguredFeature<?, ?>> key(String id) {
				return ResourceKey.create(Registries.CONFIGURED_FEATURE, AdventOfAscension.id(id));
			}
		}

		public static final class Placed {
			public static final ResourceKey<PlacedFeature> PRECASIAN_GRASS_BONEMEAL = key("precasian_grass_bonemeal");
			public static final ResourceKey<PlacedFeature> PRECASIAN_LAVA_SPRING = key("precasian_lava_spring");
			public static final ResourceKey<PlacedFeature> PRECASIAN_SPRING = key("precasian_spring");
			public static final ResourceKey<PlacedFeature> UNDERGROUND_PRECASIAN_LAVA_LAKE = key("underground_precasian_lava_lake");
			public static final ResourceKey<PlacedFeature> SURFACE_PRECASIAN_LAVA_LAKE = key("surface_precasian_lava_lake");
			public static final ResourceKey<PlacedFeature> PRECASIAN_GRASS_PATCH = key("precasian_grass_patch");
			public static final ResourceKey<PlacedFeature> PRECASIAN_TALL_GRASS_PATCH = key("precasian_tall_grass_patch");

			private static ResourceKey<PlacedFeature> key(String id) {
				return ResourceKey.create(Registries.PLACED_FEATURE, AdventOfAscension.id(id));
			}
		}
	}
}
