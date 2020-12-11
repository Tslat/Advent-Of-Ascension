package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class CelevusTreeGenerator extends TreeGenerator {
	private final BlockState leavesBlock;

	public CelevusTreeGenerator(@Nullable SaplingBlock sapling, @Nullable LeavesBlock leaves, Random rand) {
		super(sapling);

		if (leaves == null) {
			switch (rand.nextInt(6)) {
				case 0:
					leavesBlock = AoABlocks.BLUE_CELEVUS_LEAVES.get().getDefaultState();
					break;
				case 1:
					leavesBlock = AoABlocks.GREEN_CELEVUS_LEAVES.get().getDefaultState();
					break;
				case 2:
					leavesBlock = AoABlocks.PURPLE_CELEVUS_LEAVES.get().getDefaultState();
					break;
				case 3:
					leavesBlock = AoABlocks.RED_CELEVUS_LEAVES.get().getDefaultState();
					break;
				case 4:
				default:
					leavesBlock = AoABlocks.YELLOW_CELEVUS_LEAVES.get().getDefaultState();
					break;
			}
		}
		else {
			leavesBlock = leaves.getDefaultState();
		}
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(world, rand, pos, 2);

		if (multiSaplingPos == null || (sapling == null && rand.nextInt(6) != 0))
			return genSmallTree(world, rand, pos);

		return genBigTree(world, rand, multiSaplingPos);
	}

	public boolean genBigTree(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 15 + rand.nextInt(10);

		if (!checkSafeHeight(world, pos, trunkHeight + 5, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		BlockState log = AoABlocks.CELEVE_STEM.get().getDefaultState();
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

	public boolean genSmallTree(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 8 + rand.nextInt(7);

		if (!checkSafeHeight(world, pos, trunkHeight + 5, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.CELEVE_STEM.get().getDefaultState();
		BlockState whiteLeaves = AoABlocks.WHITE_CELEVUS_LEAVES.get().getDefaultState();
		int leafRingGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);

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
