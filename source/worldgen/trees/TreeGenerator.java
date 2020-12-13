package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.worldgen.WorldGenerator;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public abstract class TreeGenerator extends WorldGenerator {
	protected final SaplingBlock sapling;

	public TreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling != null);

		this.sapling = sapling;
	}

	@Override
	public abstract boolean generate(IWorld world, Random rand, BlockPos pos);

	protected void placeBlock(IWorld world, BlockPos pos, BlockState state, boolean overwriteBlocks) {
		if (!overwriteBlocks && !isSafeBlock(world, pos))
			return;

		setBlockAndNotifyAdequately(world, pos, state);
	}

	protected void placeBlock(IWorld world, BlockPos pos, BlockState state) {
		placeBlock(world, pos, state, false);
	}

	protected static boolean isSafeBlock(IWorld world, BlockPos pos) {
		BlockState existingState = world.getBlockState(pos);
		Block existingBlock = existingState.getBlock();

		return existingBlock.isAir(existingState, world, pos) || existingState.getMaterial().isReplaceable() || existingBlock.isIn(BlockTags.LEAVES);
	}

	protected boolean checkSafeHeight(IWorld world, BlockPos pos, int maxHeight, int trunkWidth) {
		if (pos.getY() >= 1 && pos.getY() + maxHeight < 256) {
			if (sapling == null)
				return true;

			for (int i = 1; i <= maxHeight; i++) {
				for (int x = 0; x < trunkWidth; x++) {
					for (int z = 0; z < trunkWidth; z++) {
						if (!isSafeBlock(world, pos.add(x, i, z)))
							return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	protected boolean checkAndPrepSoil(IWorld world, BlockPos pos, int trunkWidth) {
		if (sapling == null)
			return true;

		ArrayList<Triple<BlockState, Block, BlockPos>> soils = new ArrayList<Triple<BlockState, Block, BlockPos>>(trunkWidth * trunkWidth);

		for (int x = 0; x < trunkWidth; x++) {
			for (int z = 0; z < trunkWidth; z++) {
				BlockPos soilPos = pos.add(x, -1, z);
				BlockState soilState = world.getBlockState(soilPos);
				Block soil = soilState.getBlock();

				if (soil.isAir(soilState, world, soilPos.up()) || !soil.canSustainPlant(soilState, world, soilPos, Direction.UP, sapling))
					return false;

				soils.add(Triple.of(soilState, soil, soilPos));
			}
		}

		for (Triple<BlockState, Block, BlockPos> soil : soils) {
			soil.getMiddle().onPlantGrow(soil.getLeft(), world, soil.getRight(), soil.getRight().up());
			world.setBlockState(soil.getRight().up(), Blocks.AIR.getDefaultState(), 2);
		}

		return true;
	}

	@Nullable
	protected BlockPos findMultiSaplingPosition(IWorld world, Random rand, BlockPos pos, int size) {
		if (sapling == null)
			return pos;

		for (int x = 0; x >= -(size - 1); --x) {
			shuffleSearch:
			for (int z = 0; z >= -(size - 1); --z) {
				BlockPos checkPos = pos;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (world.getBlockState(checkPos = pos.add(x + i, 0, z + j)).getBlock() != sapling)
							continue shuffleSearch;
					}
				}

				return checkPos.add(-size + 1, 0, -size + 1);
			}
		}

		return null;
	}
}
