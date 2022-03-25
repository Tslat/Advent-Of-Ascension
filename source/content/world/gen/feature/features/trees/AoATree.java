package net.tslat.aoa3.content.world.gen.feature.features.trees;

import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class AoATree extends Tree {
	private final Supplier<? extends AoATreeFeature> treeFeature;

	public AoATree(Supplier<? extends AoATreeFeature> treeFeature) {
		this.treeFeature = treeFeature;
	}

	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
		return null;
	}

	@Override
	public boolean growTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
		if (treeFeature == null)
			return false;

		if (treeFeature.get().generate(world, rand, pos)) {
			return true;
		}
		else {
			world.setBlock(pos, state, 4);

			return false;
		}
	}
}
