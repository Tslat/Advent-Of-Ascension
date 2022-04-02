package net.tslat.aoa3.content.world.gen.feature.features.trees;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class AoATree extends AbstractTreeGrower {
	private final Supplier<? extends AoATreeFeature> treeFeature;

	public AoATree(Supplier<? extends AoATreeFeature> treeFeature) {
		this.treeFeature = treeFeature;
	}

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive) {
		return null;
	}

	@Override
	public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
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
