package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.function.Supplier;

public class LucalusTreeFeature extends AoATreeFeature {
	public LucalusTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		pos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);

		if (pos == null)
			return false;

		int trunkHeight = 16 + rand.nextInt(16);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 2, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 2, isWorldGen))
			return false;

		BlockState baseSoil = reader.getBlockState(pos.below());

		for (int x = 0; x < 2; x++) {
			for (int z = 0; z < 2; z++) {
				BlockPos testPos = pos.offset(x, -1, z);

				if (reader.getBlockState(testPos).is(Blocks.AIR))
					reader.setBlock(testPos, baseSoil, 2);
			}
		}

		BlockState log = AoABlocks.LUCALUS_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().defaultBlockState();
		int northBranchGap = 0;
		int southBranchGap = 0;
		int eastBranchGap = 0;
		int westBranchGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);

					if (i < trunkHeight - 2) {
						if (northBranchGap > 4 && rand.nextInt(4) == 0) {
							northBranchGap = 0;

							buildNorthSouthBranch(reader, pos.offset(0, i, 0), rand, true);
						}

						if (eastBranchGap > 4 && rand.nextInt(4) == 0) {
							eastBranchGap = 0;

							buildEastWestBranch(reader, pos.offset(0, i, 0), rand, true);
						}

						if (southBranchGap > 4 && rand.nextInt(4) == 0) {
							southBranchGap = 0;

							buildNorthSouthBranch(reader, pos.offset(0, i, 0), rand, false);
						}

						if (westBranchGap > 4 && rand.nextInt(4) == 0) {
							westBranchGap = 0;

							buildEastWestBranch(reader, pos.offset(0, i, 0), rand, false);
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
					placeBlock(reader, pos.offset(x, trunkHeight - 1, z), leaves);
			}
		}

		for (int x = -2; x <= 3; x++) {
			for (int z = -2; z <= 3; z++) {
				if (x != -2 && x != 3 || z != -2 && z != 3)
					placeBlock(reader, pos.offset(x, trunkHeight, z), leaves);
			}
		}

		placeBlock(reader, pos.offset(0, trunkHeight + 1, 0), leaves);
		placeBlock(reader, pos.offset(1, trunkHeight + 1, 0), leaves);
		placeBlock(reader, pos.offset(0, trunkHeight + 1, 1), leaves);
		placeBlock(reader, pos.offset(1, trunkHeight + 1, 1), leaves);

		return true;
	}

	private void buildNorthSouthBranch(WorldGenLevel reader, BlockPos branchPos, Random rand, boolean north) {
		BlockState log = AoABlocks.LUCALUS_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().defaultBlockState();
		int branchLength = 3 + rand.nextInt(2);

		if (north) {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(reader, branchPos.offset(0, i, 2 + i), log);
				placeBlock(reader, branchPos.offset(1, i, 2 + i), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int z = -2; z <= 2; z++) {
				for (int x = -2; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						int x2 = x;

						if (x > 0)
							x2--;

						if (Math.abs(x2) <= leafMod - Math.abs(z) - y)
							placeBlock(reader, branchPos.offset(x, branchLength - 2 + y, branchLength + 1 + z), leaves);
					}
				}
			}
		}
		else {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(reader, branchPos.offset(0, i, -1 - i), log);
				placeBlock(reader, branchPos.offset(1, i, -1 - i), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int z = -2; z <= 2; z++) {
				for (int x = -2; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						int x2 = x;

						if (x > 0)
							x2--;

						if (Math.abs(x2) <= leafMod - Math.abs(z) - y)
							placeBlock(reader, branchPos.offset(x, branchLength - 2 + y, -branchLength + -z), leaves);
					}
				}
			}
		}
	}

	private void buildEastWestBranch(WorldGenLevel reader, BlockPos branchPos, Random rand, boolean east) {
		BlockState log = AoABlocks.LUCALUS_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.LUCALUS_LEAVES.get().defaultBlockState();
		int branchLength = 3 + rand.nextInt(2);

		if (east) {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(reader, branchPos.offset(2 + i, i, 0), log);
				placeBlock(reader, branchPos.offset(2 + i, i, 1), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 3; z++) {
					for (int y = 1; y <= 3; y++) {
						int z2 = z;

						if (z > 0)
							z2--;

						if (Math.abs(z2) <= leafMod - Math.abs(x) - y)
							placeBlock(reader, branchPos.offset(branchLength + 1 + x, branchLength - 2 + y, z), leaves);
					}
				}
			}
		}
		else {
			for (int i = 0; i < branchLength; i++) {
				placeBlock(reader, branchPos.offset(-1 - i, i, 0), log);
				placeBlock(reader, branchPos.offset(-1 - i, i, 1), log);
			}

			int leafMod = 2 + rand.nextInt(3);

			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 3; z++) {
					for (int y = 1; y <= 3; y++) {
						int z2 = z;

						if (z > 0)
							z2--;

						if (Math.abs(z2) <= leafMod - Math.abs(x) - y)
							placeBlock(reader, branchPos.offset(-branchLength + -x, branchLength - 2 + y, z), leaves);
					}
				}
			}
		}
	}
}
