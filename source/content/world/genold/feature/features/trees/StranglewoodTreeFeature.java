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

public class StranglewoodTreeFeature extends AoATreeFeature {
	public StranglewoodTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		pos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);

		if (pos == null)
			return false;

		int trunkHeight = 10 + rand.nextInt(26);

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

		BlockState log = AoABlocks.STRANGLEWOOD_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.STRANGLEWOOD_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
				}
			}
		}

		if (trunkHeight > 15) {
			buildTallCrown(reader, pos.above(trunkHeight -1 ), rand);
		}
		else {
			buildShortCrown(reader, pos.above(trunkHeight - 1), rand);

			for (int x = -1; x <= 2; x++) {
				for (int z = -1; z <= 2; z++) {
					if (x != -1 && x != 2 || z != -1 && z != 2)
						placeBlock(reader, pos.offset(x, trunkHeight - 3, z), leaves);
				}
			}
		}

		return true;
	}

	private void buildTallCrown(WorldGenLevel reader, BlockPos trunkTopPos, Random rand) {
		BlockState log = AoABlocks.STRANGLEWOOD_LOG.get().defaultBlockState();

		for (int x = 0; x < 5; x++) {
			placeBlock(reader, trunkTopPos.offset(2 + x, 0, 0), log);
			placeBlock(reader, trunkTopPos.offset(2 + x, 0, 1), log);
			placeBlock(reader, trunkTopPos.offset(-x - 1, 0, 0), log);
			placeBlock(reader, trunkTopPos.offset(-x - 1, 0, 1), log);
		}

		for (int z = 0; z < 5; z++) {
			placeBlock(reader, trunkTopPos.offset(0, 0, 2 + z), log);
			placeBlock(reader, trunkTopPos.offset(1, 0, 2 + z), log);
			placeBlock(reader, trunkTopPos.offset(0, 0, -z - 1), log);
			placeBlock(reader, trunkTopPos.offset(1, 0, -z - 1), log);
		}

		buildTallNorthSouthLeaves(reader, trunkTopPos.offset(0, 0, 6), rand);
		buildTallNorthSouthLeaves(reader, trunkTopPos.offset(0, 0, -5), rand);
		buildTallEastWestLeaves(reader, trunkTopPos.offset(-5, 0, 0), rand);
		buildTallEastWestLeaves(reader, trunkTopPos.offset(6, 0, 0), rand);
	}

	private void buildTallNorthSouthLeaves(WorldGenLevel reader, BlockPos centralPos, Random rand) {
		BlockState leaves = AoABlocks.STRANGLEWOOD_LEAVES.get().defaultBlockState();

		for (int x = -1; x <= 2; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -2; z <= 2; z++) {
					placeBlock(reader, centralPos.offset(x, y, z), leaves);

					if (y == 1 && z != -2 && z != 2) {
						placeBlock(reader, centralPos.offset(x, 2, z), leaves);
						placeBlock(reader, centralPos.offset(x, -2, z), leaves);
					}

					if (x == 0 && z != -2 && z != 2) {
						placeBlock(reader, centralPos.offset(-2, y, z), leaves);
						placeBlock(reader, centralPos.offset(3, y, z), leaves);
					}
				}
			}
		}
	}

	private void buildTallEastWestLeaves(WorldGenLevel reader, BlockPos centralPos, Random rand) {
		BlockState leaves = AoABlocks.STRANGLEWOOD_LEAVES.get().defaultBlockState();

		for (int x = -2; x <= 2; x++) {
			for (int y = -1; y <= 1; y++ ) {
				for (int z = -1; z <= 2; z++) {
					placeBlock(reader, centralPos.offset(x, y, z), leaves);

					if (y == 1 && x != -2 && x != 2) {
						placeBlock(reader, centralPos.offset(x, 2, z), leaves);
						placeBlock(reader, centralPos.offset(x, -2, z), leaves);
					}

					if (z == 0 && x != -2 && x != 2) {
						placeBlock(reader, centralPos.offset(x, y, -2), leaves);
						placeBlock(reader, centralPos.offset(x, y, 3), leaves);
					}
				}
			}
		}
	}

	private void buildShortCrown(WorldGenLevel reader, BlockPos trunkTopPos, Random rand) {
		BlockState log = AoABlocks.STRANGLEWOOD_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.STRANGLEWOOD_LEAVES.get().defaultBlockState();

		for (int i = 0; i < 4; i++) {
			placeBlock(reader, trunkTopPos.offset(i + 2, -4 + i, 0), log);
			placeBlock(reader, trunkTopPos.offset(i + 2, -4 + i, 1), log);
			placeBlock(reader, trunkTopPos.offset(0, -4 + i, i + 2), log);
			placeBlock(reader, trunkTopPos.offset(1, -4 + i, i + 2), log);
			placeBlock(reader, trunkTopPos.offset(-1 + -i, -4 + i, 0), log);
			placeBlock(reader, trunkTopPos.offset(-1 + -i, -4 + i, 1), log);
			placeBlock(reader, trunkTopPos.offset(0, -4 + i, -1 + -i), log);
			placeBlock(reader, trunkTopPos.offset(1, -4 + i, -1 + -i), log);
		}

		for (int i = 5; i > 2; i--) {
			for (int x2 = -i; x2 <= i + 1; x2++) {
				for (int z2 = -i; z2 <= i + 1; z2++) {
					int x3 = x2;
					int z3 = z2;

					if (x3 < 0)
						x3 -= 1;

					if (z3 < 0)
						z3 -= 1;

					if (Math.abs(x3) <= i - (Math.abs(z3) - 2))
						placeBlock(reader, trunkTopPos.offset(x2, (7 - i) - 3, z2), leaves);
				}
			}
		}
	}
}
