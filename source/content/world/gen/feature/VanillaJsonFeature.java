package net.tslat.aoa3.content.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VanillaJsonFeature extends Feature<VanillaJsonFeature.VanillaJsonFeatureConfig> {
	public VanillaJsonFeature(Codec<VanillaJsonFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<VanillaJsonFeatureConfig> context) {
		WorldGenLevel level = context.level();

		return level.registryAccess().registryOrThrow(Registry.PLACED_FEATURE_REGISTRY).get(context.config().feature()).place(level, context.chunkGenerator(), context.random(), context.origin());
	}

	public record VanillaJsonFeatureConfig(ResourceKey<PlacedFeature> feature) implements FeatureConfiguration {
		public static final Codec<VanillaJsonFeatureConfig> CODEC = ResourceKey.codec(Registry.PLACED_FEATURE_REGISTRY)
				.xmap(VanillaJsonFeatureConfig::new, VanillaJsonFeatureConfig::feature)
				.fieldOf("feature")
				.codec();
	}
}
