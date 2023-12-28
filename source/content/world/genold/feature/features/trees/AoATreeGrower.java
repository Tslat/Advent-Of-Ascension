package net.tslat.aoa3.content.world.genold.feature.features.trees;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;


public class AoATreeGrower extends AbstractTreeGrower {
	private final ResourceKey<ConfiguredFeature<?, ?>> treeFeature;

	public AoATreeGrower(ResourceKey<ConfiguredFeature<?, ?>> configuredTree) {
		this.treeFeature = configuredTree;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
		return this.treeFeature;
	}
}
