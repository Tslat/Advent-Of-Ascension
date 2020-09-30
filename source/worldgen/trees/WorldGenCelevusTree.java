package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.block.generation.leaves.LeavesBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenCelevusTree extends WorldGenTree {
	private final IBlockState leavesBlock;

	public WorldGenCelevusTree(@Nullable SaplingBlock sapling, @Nullable LeavesBlock leaves, Random rand) {
		super(sapling);

		if (leaves == null) {
			switch (rand.nextInt(6)) {
				case 0:
					leavesBlock = BlockRegister.BLUE_CELEVUS_LEAVES.getDefaultState();
					break;
				case 1:
					leavesBlock = BlockRegister.GREEN_CELEVUS_LEAVES.getDefaultState();
					break;
				case 2:
					leavesBlock = BlockRegister.PURPLE_CELEVUS_LEAVES.getDefaultState();
					break;
				case 3:
					leavesBlock = BlockRegister.RED_CELEVUS_LEAVES.getDefaultState();
					break;
				case 4:
				default:
					leavesBlock = BlockRegister.YELLOW_CELEVUS_LEAVES.getDefaultState();
					break;
			}
		}
		else {
			leavesBlock = leaves.getDefaultState();
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(world, rand, pos, 2);

		if (multiSaplingPos == null || (sapling == null && rand.nextInt(6) != 0))
			return genSmallTree(world, rand, pos);

		return genBigTree(world, rand, multiSaplingPos);
	}

	public boolean genBigTree(World world, Random rand, BlockPos pos) {
		int trunkHeight = 15 + rand.nextInt(10);

		if (!checkSafeHeight(world, pos, trunkHeight + 5, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		IBlockState log = BlockRegister.CELEVE_STEM.getDefaultState();
		boolean clockwise = rand.nextBoolean();
		boolean thickTwist = rand.nextBoolean();
		int twistOffset = 1 + rand.nextInt(3);

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);
				}
			}

			switch ((i + twistOffset) % 4) {
				case 0:
					placeBlock(world, pos.add(0, i, 0), leavesBlock, true);

					if (thickTwist && i > 0)
						placeBlock(world, pos.add(0, i - 1, 0), leavesBlock, true);

					break;
				case 1:
					if (clockwise) {
						placeBlock(world, pos.add(0, i, 1), leavesBlock, true);

						if (thickTwist && i > 0)
							placeBlock(world, pos.add(0, i - 1, 1), leavesBlock, true);
					}
					else {
						placeBlock(world, pos.add(1, i, 0), leavesBlock, true);

						if (thickTwist && i > 0)
							placeBlock(world, pos.add(1, i - 1, 0), leavesBlock, true);
					}
					break;
				case 2:
					placeBlock(world, pos.add(1, i, 1), leavesBlock, true);

					if (thickTwist && i > 0)
						placeBlock(world, pos.add(1, i - 1, 1), leavesBlock, true);

					break;
				case 3:
					if (clockwise) {
						placeBlock(world, pos.add(1, i, 0), leavesBlock, true);

						if (thickTwist && i > 0)
							placeBlock(world, pos.add(1, i - 1, 0), leavesBlock, true);
					}
					else {
						placeBlock(world, pos.add(0, i, 1), leavesBlock, true);

						if (thickTwist && i > 0)
							placeBlock(world, pos.add(0, i - 1, 1), leavesBlock, true);
					}
					break;
			}
		}

		pos = pos.up(trunkHeight);

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				placeBlock(world, pos.add(x, 0, z), leavesBlock);
				placeBlock(world, pos.add(x, 5, z), leavesBlock);
			}
		}

		for (int x = -2; x <= 3; x++) {
			for (int y = 1; y <= 4; y++) {
				for (int z = -2; z <= 3; z++) {
					placeBlock(world, pos.add(x, y, z), leavesBlock);
				}
			}
		}

		return true;
	}

	public boolean genSmallTree(World world, Random rand, BlockPos pos) {
		int trunkHeight = 8 + rand.nextInt(7);

		if (!checkSafeHeight(world, pos, trunkHeight + 5, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.CELEVE_STEM.getDefaultState();
		IBlockState whiteLeaves = BlockRegister.WHITE_CELEVUS_LEAVES.getDefaultState();
		int leafRingGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);

			if (leafRingGap > 1 && i < trunkHeight - 1 && rand.nextBoolean()) {
				leafRingGap = 0;

				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						placeBlock(world, movablePos.add(x, 0, z), leavesBlock);
					}
				}
			}

			leafRingGap++;
		}

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				placeBlock(world, movablePos.add(x, 0, z), whiteLeaves);
				placeBlock(world, movablePos.add(x, 5, z), leavesBlock);
			}
		}

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				placeBlock(world, movablePos.add(x, 1, z), leavesBlock);
				placeBlock(world, movablePos.add(x, 2, z), whiteLeaves);
				placeBlock(world, movablePos.add(x, 3, z), leavesBlock);
				placeBlock(world, movablePos.add(x, 4, z), whiteLeaves);
			}
		}

		return true;
	}
}
