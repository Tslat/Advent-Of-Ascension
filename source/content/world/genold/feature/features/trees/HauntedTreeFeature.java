package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.AoASaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.HashMap;
import java.util.function.Supplier;

public class HauntedTreeFeature extends AoATreeFeature {
	private final int hauntedLeafChance = 80;
	private final int hauntedLogChance = 25;
	private final int purplingLogChance = 20;

	public HauntedTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<? extends AoASaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		return switch (rand.nextInt(4)) {
			case 0 -> generateTree1(reader, rand, pos, isWorldGen);
			case 1 -> generateTree2(reader, rand, pos, isWorldGen);
			case 2 -> generateTree3(reader, rand, pos, isWorldGen);
			case 3 -> generateTree4(reader, rand, pos, isWorldGen);
			default -> true;
		};

	}

	private boolean generateTree1(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 24;

		if (!checkSafeHeight(reader, pos, trunkHeight + 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.log().defaultBlockState() : AoABlocks.HAUNTED_LOG.log().defaultBlockState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.log().defaultBlockState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.log().defaultBlockState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().defaultBlockState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(reader, movablePos.move(Direction.UP), logBlock);
		}

		generateCrossBranch(reader, rand, pos.above(5), log, 6);
		generateCrossBranch(reader, rand, pos.above(10), log, 5);
		generateCrossBranch(reader, rand, pos.above(15), log, 4);
		generateCrossBranch(reader, rand, pos.above(19), log, 3);
		generateCrossBranch(reader, rand, pos.above(23), log, 2);

		placeBlock(reader, pos.offset(0, 24, 0), rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves);

		return true;
	}

	private boolean generateTree2(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 12 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.log().defaultBlockState() : AoABlocks.HAUNTED_LOG.log().defaultBlockState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.log().defaultBlockState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.log().defaultBlockState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().defaultBlockState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(reader, movablePos.move(Direction.UP), logBlock);
		}

		int coreHeight = 3 + rand.nextInt(3);
		int bottomCoreHeight = 3 + rand.nextInt(3);

		for (int i = 0; i < coreHeight; i++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(reader, movablePos.offset(x, -i, z), leafBlock);
				}
			}
		}

		for (int i = 0; i < bottomCoreHeight; i++) {
			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 2; z++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(reader, movablePos.offset(x, -coreHeight + -i, z), leafBlock);
				}
			}
		}

		for (int i = -3; i <= 3; i += 6) {
			for (int j = -1; j <= 1; j += 2) {
				for (int y = 0; y <= 2 + rand.nextInt(3); y++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(reader, movablePos.offset(i, -coreHeight + y, j), leafBlock);
				}

				for (int y = 0; y <= 2 + rand.nextInt(3); y++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(reader, movablePos.offset(j, -coreHeight + y, i), leafBlock);
				}
			}
		}

		for (int i = 1; i <= 1 + rand.nextInt(3); i++) {
			BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

			placeBlock(reader, movablePos.offset(0, i, 0), leafBlock);
		}

		return true;
	}

	private boolean generateTree3(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 10 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		boolean purpling = rand.nextInt(purplingLogChance) == 0;
		BlockState log = purpling ? AoABlocks.HAUNTED_PURPLING_LOG.log().defaultBlockState() : AoABlocks.HAUNTED_LOG.log().defaultBlockState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.log().defaultBlockState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.log().defaultBlockState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().defaultBlockState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().defaultBlockState();

		HashMap<Direction, Integer> branchOffsetMap = new HashMap<Direction, Integer>(4);

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(reader, movablePos.move(Direction.UP), logBlock);

			if (i > 3) {
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					if ((!branchOffsetMap.containsKey(dir) || i - branchOffsetMap.get(dir) > 3) && rand.nextInt(3) == 0) {
						branchOffsetMap.put(dir, i);

						for (int j = 1; j <= 4; j++) {
							BlockPos branchLocPos = movablePos.relative(dir, j);

							BlockState branchLogBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
							BlockState barkBlock = getBarkForLog(branchLogBlock);

							placeBlock(reader, branchLocPos, barkBlock);

							if (j > 1) {
								if (j == 2 || j == 4) {
									BlockState branchLogBlock2 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

									placeBlock(reader, branchLocPos.below(), branchLogBlock2);
								}

								Direction sidewaysDir = dir.getCounterClockWise();
								branchLocPos = branchLocPos.below(2);

								for (int k = -1; k <= 1; k++) {
									BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;
									BlockPos newPos = branchLocPos.relative(sidewaysDir, k);

									placeBlock(reader, newPos, leafBlock);

									if (rand.nextInt(100) == 0 && reader.getBlockState(newPos = newPos.below()).isAir())
										placeBlock(reader, newPos, AoABlocks.MAGIC_MARANG_CROP.get().defaultBlockState());
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree4(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 7 + rand.nextInt(7);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.log().defaultBlockState() : AoABlocks.HAUNTED_LOG.log().defaultBlockState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.log().defaultBlockState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.log().defaultBlockState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().defaultBlockState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(reader, movablePos.move(Direction.UP), logBlock);
		}

		for (int i = -2; i <= 2; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
			BlockState logBlock2 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
			BlockState barkBlock = getBarkForLog(logBlock);
			BlockState barkBlock2 = getBarkForLog(logBlock2);

			placeBlock(reader, movablePos.offset(i, 1, 0), barkBlock);
			placeBlock(reader, movablePos.offset(0, 1, i), barkBlock2);

			if (Math.abs(i) == 2) {
				BlockState logBlock3 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
				BlockState logBlock4 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

				placeBlock(reader, movablePos.offset(i, 2, 0), logBlock3);
				placeBlock(reader, movablePos.offset(0, 2, i), logBlock4);
			}
		}

		for (int x = -4; x <= 4; x++) {
			for (int z = -4; z <= 4; z++) {
				if (x * x + z * z <= 16 - (3 - Math.abs(x) - Math.abs(z))) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;
					BlockPos leafPos = movablePos.offset(x, 3, z);

					placeBlock(reader, leafPos, leafBlock);

					if ((Math.abs(x) > 2 || Math.abs(z) > 2) && rand.nextInt(5) == 0) {
						for (int i = 1; i <= 3 + rand.nextInt(3); i++) {
							BlockState leafBlock2 = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

							placeBlock(reader, leafPos.offset(0, -i, 0), leafBlock2);
						}
					}
				}
			}
		}

		return true;
	}

	private void generateCrossBranch(WorldGenLevel reader, RandomSource rand, BlockPos branchPos, BlockState log, int length) {
		BlockState barkLog = getBarkForLog(log);
		BlockState redEyesBarkLog = AoABlocks.HAUNTED_EYES_LOG.bark().defaultBlockState();
		BlockState eyeBarkLog = AoABlocks.HAUNTED_EYE_LOG.bark().defaultBlockState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().defaultBlockState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().defaultBlockState();

		for (int i = 1; i <= length; i++) {
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeBarkLog : redEyesBarkLog : barkLog;
				BlockPos branchLocPos = branchPos.relative(dir, i);

				placeBlock(reader, branchPos.relative(dir, i), logBlock);

				for (Direction leafDir : Direction.values()) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					if (leafDir != Direction.DOWN)
						placeBlock(reader, branchLocPos.relative(leafDir), leafBlock);
				}
			}
		}
	}

	private BlockState getBarkForLog(BlockState state) {
		if (state.getBlock() == AoABlocks.HAUNTED_LOG.log())
			return AoABlocks.HAUNTED_LOG.bark().defaultBlockState();

		if (state.getBlock() == AoABlocks.HAUNTED_PURPLING_LOG.log())
			return AoABlocks.HAUNTED_PURPLING_LOG.bark().defaultBlockState();

		if (state.getBlock() == AoABlocks.HAUNTED_EYE_LOG.log())
			return AoABlocks.HAUNTED_EYES_LOG.log().defaultBlockState();

		if (state.getBlock() == AoABlocks.HAUNTED_EYES_LOG.log())
			return AoABlocks.HAUNTED_EYES_LOG.bark().defaultBlockState();

		return AoABlocks.HAUNTED_LOG.bark().defaultBlockState();
	}
}
