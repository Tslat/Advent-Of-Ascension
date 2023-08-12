package net.tslat.aoa3.content.world.genold.feature.features.trees;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AoAThickTreeGrower extends AbstractMegaTreeGrower {
	private final ResourceKey<ConfiguredFeature<?, ?>> thinTreeFeature;
	private final ResourceKey<ConfiguredFeature<?, ?>> thickTreeFeature;

	public AoAThickTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> configuredTree) {
		this(null, configuredTree);
	}

	public AoAThickTreeGrower(@Nullable ResourceKey<ConfiguredFeature<?, ?>> thinTree, ResourceKey<ConfiguredFeature<?, ?>> thickTree) {
		this.thinTreeFeature = thinTree;
		this.thickTreeFeature = thickTree;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
		return this.thinTreeFeature;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom) {
		return this.thickTreeFeature;
	}
}
