package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public abstract class CelevusTreeFeature extends AoAVariableLeafTreeFeature {
	public CelevusTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(reader, rand, pos, 2, isWorldGen);
		boolean success;

		if (multiSaplingPos == null || (isWorldGen && rand.nextInt(6) != 0)) {
			success = genSmallTree(reader, rand, pos, leafBlock, isWorldGen);
		}
		else {
			success = genBigTree(reader, rand, multiSaplingPos, leafBlock, isWorldGen);
		}

		return success;
	}

	public boolean genBigTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 15 + rand.nextInt(10);

		if (!checkSafeHeight(reader, pos, trunkHeight + 5, 2, isWorldGen))
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

		BlockState log = AoABlocks.CELEVE_STEM.get().defaultBlockState();
		boolean clockwise = rand.nextBoolean();
		boolean thickTwist = rand.nextBoolean();
		int twistOffset = 1 + rand.nextInt(3);

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(reader, pos.offset(x, i, z), log);
				}
			}

			switch ((i + twistOffset) % 4) {
				case 0:
					placeBlock(reader, pos.offset(0, i, 0), leafBlock, true);

					if (thickTwist)
						placeBlock(reader, pos.offset(0, i - 1, 0), leafBlock, true);

					break;
				case 1:
					if (clockwise) {
						placeBlock(reader, pos.offset(0, i, 1), leafBlock, true);

						if (thickTwist && i > 0)
							placeBlock(reader, pos.offset(0, i - 1, 1), leafBlock, true);
					}
					else {
						placeBlock(reader, pos.offset(1, i, 0), leafBlock, true);

						if (thickTwist && i > 0)
							placeBlock(reader, pos.offset(1, i - 1, 0), leafBlock, true);
					}
					break;
				case 2:
					placeBlock(reader, pos.offset(1, i, 1), leafBlock, true);

					if (thickTwist && i > 0)
						placeBlock(reader, pos.offset(1, i - 1, 1), leafBlock, true);

					break;
				case 3:
					if (clockwise) {
						placeBlock(reader, pos.offset(1, i, 0), leafBlock, true);

						if (thickTwist && i > 0)
							placeBlock(reader, pos.offset(1, i - 1, 0), leafBlock, true);
					}
					else {
						placeBlock(reader, pos.offset(0, i, 1), leafBlock, true);

						if (thickTwist && i > 0)
							placeBlock(reader, pos.offset(0, i - 1, 1), leafBlock, true);
					}
					break;
			}
		}

		pos = pos.above(trunkHeight);

		for (int x = -1; x <= 2; x++) {
			for (int z = -1; z <= 2; z++) {
				placeBlock(reader, pos.offset(x, 0, z), leafBlock);
				placeBlock(reader, pos.offset(x, 5, z), leafBlock);
			}
		}

		for (int x = -2; x <= 3; x++) {
			for (int y = 1; y <= 4; y++) {
				for (int z = -2; z <= 3; z++) {
					placeBlock(reader, pos.offset(x, y, z), leafBlock);
				}
			}
		}

		return true;
	}

	public boolean genSmallTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(7);

		if (!checkSafeHeight(reader, pos, trunkHeight + 5, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.CELEVE_STEM.get().defaultBlockState();
		BlockState whiteLeaves = AoABlocks.WHITE_CELEVUS_LEAVES.get().defaultBlockState();
		int leafRingGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);

			if (leafRingGap > 1 && i < trunkHeight - 1 && rand.nextBoolean()) {
				leafRingGap = 0;

				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						placeBlock(reader, movablePos.offset(x, 0, z), leafBlock);
					}
				}
			}

			leafRingGap++;
		}

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				placeBlock(reader, movablePos.offset(x, 0, z), whiteLeaves);
				placeBlock(reader, movablePos.offset(x, 5, z), leafBlock);
			}
		}

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				placeBlock(reader, movablePos.offset(x, 1, z), leafBlock);
				placeBlock(reader, movablePos.offset(x, 2, z), whiteLeaves);
				placeBlock(reader, movablePos.offset(x, 3, z), leafBlock);
				placeBlock(reader, movablePos.offset(x, 4, z), whiteLeaves);
			}
		}

		return true;
	}
}
