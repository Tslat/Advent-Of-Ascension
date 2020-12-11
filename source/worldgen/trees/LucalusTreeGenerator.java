package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class LucalusTreeGenerator extends TreeGenerator {
	public LucalusTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		pos = findMultiSaplingPosition(world, rand, pos, 2);

		if (pos == null)
			return false;

		int trunkHeight = 16 + rand.nextInt(16);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		BlockState log = AoABlocks.LUCALUS_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().getDefaultState();
		int northBranchGap = 0;
		int southBranchGap = 0;
		int eastBranchGap = 0;
		int westBranchGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);

					if (i < trunkHeight - 2) {
						if (northBranchGap > 4 && rand.nextInt(4) == 0) {
							northBranchGap = 0;

							buildNorthSouthBranch(world, pos.add(0, i, 0), rand, true);
						}

						if (eastBranchGap > 4 && rand.nextInt(4) == 0) {
							eastBranchGap = 0;

							buildEastWestBranch(world, pos.add(0, i, 0), rand, true);
						}

						if (southBranchGap > 4 && rand.nextInt(4) == 0) {
							southBranchGap = 0;

							buildNorthSouthBranch(world, pos.add(0, i, 0), rand, false);
						}

						if (westBranchGap > 4 && rand.nextInt(4) == 0) {
							westBranchGap = 0;

							buildEastWestBranch(world, pos.add(0, i, 0), rand, false);
						}
					}
				}
			}

			northBranchGap++;
			southBranchGap++;
			eastBranchGap++;
			westBranchGap++;
		}

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				if (x != -1 && x != 2 || z != -1 && z != 2)
					placeBlock(world, pos.add(x, trunkHeight - 1, z), leaves);
			}
		}

		for (int x = -2; x <= 3; x++) {
			for (int z = -2; z <= 3; z++) {
				if (x != -2 && x != 3 || z != -2 && z != 3)
					placeBlock(world, pos.add(x, trunkHeight, z), leaves);
			}
		}

		placeBlock(world, pos.add(0, trunkHeight + 1, 0), leaves);
		placeBlock(world, pos.add(1, trunkHeight + 1, 0), leaves);
		placeBlock(world, pos.add(0, trunkHeight + 1, 1), leaves);
		placeBlock(world, pos.add(1, trunkHeight + 1, 1), leaves);

		return true;
	}

	private void buildNorthSouthBranch(IWorld world, BlockPos branchPos, Random rand, boolean north) {
		BlockState log = AoABlocks.LUCALUS_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().getDefaultState();
		int branchLength = 3 + rand.nextInt(2);

		if (north) {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(world, branchPos.add(0, i, 2 + i), log);
				placeBlock(world, branchPos.add(1, i, 2 + i), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int z = -2; z <= 2; z++) {
				for (int x = -2; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						int x2 = x;

						if (x > 0)
							x2--;

						if (Math.abs(x2) <= leafMod - Math.abs(z) - y)
							placeBlock(world, branchPos.add(x, branchLength - 2 + y, branchLength + 1 + z), leaves);
					}
				}
			}
		}
		else {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(world, branchPos.add(0, i, -1 - i), log);
				placeBlock(world, branchPos.add(1, i, -1 - i), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int z = -2; z <= 2; z++) {
				for (int x = -2; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						int x2 = x;

						if (x > 0)
							x2--;

						if (Math.abs(x2) <= leafMod - Math.abs(z) - y)
							placeBlock(world, branchPos.add(x, branchLength - 2 + y, -branchLength + -z), leaves);
					}
				}
			}
		}
	}

	private void buildEastWestBranch(IWorld world, BlockPos branchPos, Random rand, boolean east) {
		BlockState log = AoABlocks.LUCALUS_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().getDefaultState();
		int branchLength = 3 + rand.nextInt(2);

		if (east) {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(world, branchPos.add(2 + i, i, 0), log);
				placeBlock(world, branchPos.add(2 + i, i, 1), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 3; z++) {
					for (int y = 1; y <= 3; y++) {
						int z2 = z;

						if (z > 0)
							z2--;

						if (Math.abs(z2) <= leafMod - Math.abs(x) - y)
							placeBlock(world, branchPos.add(branchLength + 1 + x, branchLength - 2 + y, z), leaves);
					}
				}
			}
		}
		else {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(world, branchPos.add(-1 - i, i, 0), log);
				placeBlock(world, branchPos.add(-1 - i, i, 1), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 3; z++) {
					for (int y = 1; y <= 3; y++) {
						int z2 = z;

						if (z > 0)
							z2--;

						if (Math.abs(z2) <= leafMod - Math.abs(x) - y)
							placeBlock(world, branchPos.add(-branchLength + -x, branchLength - 2 + y, z), leaves);
					}
				}
			}
		}
	}
}
