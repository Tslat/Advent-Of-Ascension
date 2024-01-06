package net.tslat.aoa3.content.world.genold.feature.features.trees;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class AoATreeOld extends AbstractTreeGrower {
	private final Supplier<? extends AoATreeFeature> treeFeature;

	public AoATreeOld(Supplier<? extends AoATreeFeature> treeFeature) {
		this.treeFeature = treeFeature;
	}

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
		return null;
	}

	@Override
	public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource rand) {
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
