package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Random;

public class HauntedTreeGenerator extends TreeGenerator {
	private final int hauntedLeafChance = 80;
	private final int hauntedLogChance = 25;
	private final int purplingLogChance = 20;

	public HauntedTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		switch (rand.nextInt(4)) {
			case 0:
				return generateTree1(world, rand, pos);
			case 1:
				return generateTree2(world, rand, pos);
			case 2:
				return generateTree3(world, rand, pos);
			case 3:
				return generateTree4(world, rand, pos);
		}

		return true;
	}

	private boolean generateTree1(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 24;

		if (!checkSafeHeight(world, pos, trunkHeight + 1, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.get().getDefaultState() : AoABlocks.HAUNTED_LOG.get().getDefaultState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.get().getDefaultState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().getDefaultState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(world, movablePos.move(Direction.UP), logBlock);
		}

		generateCrossBranch(world, rand, pos.up(5), log, 6);
		generateCrossBranch(world, rand, pos.up(10), log, 5);
		generateCrossBranch(world, rand, pos.up(15), log, 4);
		generateCrossBranch(world, rand, pos.up(19), log, 3);
		generateCrossBranch(world, rand, pos.up(23), log, 2);

		placeBlock(world, pos.add(0, 24, 0), rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves);

		return true;
	}

	private boolean generateTree2(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 12 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + 1, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.get().getDefaultState() : AoABlocks.HAUNTED_LOG.get().getDefaultState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.get().getDefaultState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().getDefaultState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(world, movablePos.move(Direction.UP), logBlock);
		}

		int coreHeight = 3 + rand.nextInt(3);
		int bottomCoreHeight = 3 + rand.nextInt(3);

		for (int i = 0; i < coreHeight; i++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(world, movablePos.add(x, -i, z), leafBlock);
				}
			}
		}

		for (int i = 0; i < bottomCoreHeight; i++) {
			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 2; z++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(world, movablePos.add(x, -coreHeight + -i, z), leafBlock);
				}
			}
		}

		for (int i = -3; i <= 3; i += 6) {
			for (int j = -1; j <= 1; j += 2) {
				for (int y = 0; y <= 2 + rand.nextInt(3); y++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(world, movablePos.add(i, -coreHeight + y, j), leafBlock);
				}

				for (int y = 0; y <= 2 + rand.nextInt(3); y++) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					placeBlock(world, movablePos.add(j, -coreHeight + y, i), leafBlock);
				}
			}
		}

		for (int i = 1; i <= 1 + rand.nextInt(3); i++) {
			BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

			placeBlock(world, movablePos.add(0, i, 0), leafBlock);
		}

		return true;
	}

	private boolean generateTree3(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 10 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		boolean purpling = rand.nextInt(purplingLogChance) == 0;
		BlockState log = purpling ? AoABlocks.HAUNTED_PURPLING_LOG.get().getDefaultState() : AoABlocks.HAUNTED_LOG.get().getDefaultState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.get().getDefaultState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().getDefaultState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().getDefaultState();

		HashMap<Direction, Integer> branchOffsetMap = new HashMap<Direction, Integer>(4);

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(world, movablePos.move(Direction.UP), logBlock);

			if (i > 3) {
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					if ((!branchOffsetMap.containsKey(dir) || i - branchOffsetMap.get(dir) > 3) && rand.nextInt(3) == 0) {
						branchOffsetMap.put(dir, i);

						for (int j = 1; j <= 4; j++) {
							BlockPos branchLocPos = movablePos.offset(dir, j);

							BlockState branchLogBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
							BlockState barkBlock = getBarkForLog(branchLogBlock);

							placeBlock(world, branchLocPos, barkBlock);

							if (j > 1) {
								if (j == 2 || j == 4) {
									BlockState branchLogBlock2 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

									placeBlock(world, branchLocPos.down(), branchLogBlock2);
								}

								Direction sidewaysDir = dir.rotateYCCW();
								branchLocPos = branchLocPos.down(2);

								for (int k = -1; k <= 1; k++) {
									BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

									placeBlock(world, branchLocPos.offset(sidewaysDir, k), leafBlock);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree4(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 7 + rand.nextInt(7);

		if (!checkSafeHeight(world, pos, trunkHeight + 3, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = rand.nextInt(purplingLogChance) == 0 ? AoABlocks.HAUNTED_PURPLING_LOG.get().getDefaultState() : AoABlocks.HAUNTED_LOG.get().getDefaultState();
		BlockState redEyesLog = AoABlocks.HAUNTED_EYES_LOG.get().getDefaultState();
		BlockState eyeLog = AoABlocks.HAUNTED_EYE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().getDefaultState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

			placeBlock(world, movablePos.move(Direction.UP), logBlock);
		}

		for (int i = -2; i <= 2; i++) {
			BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
			BlockState logBlock2 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
			BlockState barkBlock = getBarkForLog(logBlock);
			BlockState barkBlock2 = getBarkForLog(logBlock2);

			placeBlock(world, movablePos.add(i, 1, 0), barkBlock);
			placeBlock(world, movablePos.add(0, 1, i), barkBlock2);

			if (Math.abs(i) == 2) {
				BlockState logBlock3 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;
				BlockState logBlock4 = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeLog : redEyesLog : log;

				placeBlock(world, movablePos.add(i, 2, 0), logBlock3);
				placeBlock(world, movablePos.add(0, 2, i), logBlock4);
			}
		}

		for (int x = -4; x <= 4; x++) {
			for (int z = -4; z <= 4; z++) {
				if (x * x + z * z <= 16 - (3 - Math.abs(x) - Math.abs(z))) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;
					BlockPos leafPos = movablePos.add(x, 3, z);

					placeBlock(world, leafPos, leafBlock);

					if ((Math.abs(x) > 2 || Math.abs(z) > 2) && rand.nextInt(5) == 0) {
						for (int i = 1; i <= 3 + rand.nextInt(3); i++) {
							BlockState leafBlock2 = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

							placeBlock(world, leafPos.add(0, -i, 0), leafBlock2);
						}
					}
				}
			}
		}

		return true;
	}

	private void generateCrossBranch(IWorld world, Random rand, BlockPos branchPos, BlockState log, int length) {
		BlockState barkLog = getBarkForLog(log);
		BlockState redEyesBarkLog = AoABlocks.HAUNTED_EYES_WOOD.get().getDefaultState();
		BlockState eyeBarkLog = AoABlocks.HAUNTED_EYE_WOOD.get().getDefaultState();
		BlockState leaves = AoABlocks.HAUNTED_LEAVES.get().getDefaultState();
		BlockState eyeLeaves = AoABlocks.HAUNTED_EYES_LEAVES.get().getDefaultState();

		for (int i = 1; i <= length; i++) {
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				BlockState logBlock = rand.nextInt(hauntedLogChance) == 0 ? rand.nextBoolean() ? eyeBarkLog : redEyesBarkLog : barkLog;
				BlockPos branchLocPos = branchPos.offset(dir, i);

				placeBlock(world, branchPos.offset(dir, i), logBlock);

				for (Direction leafDir : Direction.values()) {
					BlockState leafBlock = rand.nextInt(hauntedLeafChance) == 0 ? eyeLeaves : leaves;

					if (leafDir != Direction.DOWN)
						placeBlock(world, branchLocPos.offset(leafDir), leafBlock);
				}
			}
		}
	}

	private BlockState getBarkForLog(BlockState state) {
		if (state.getBlock() == AoABlocks.HAUNTED_LOG.get())
			return AoABlocks.HAUNTED_WOOD.get().getDefaultState();

		if (state.getBlock() == AoABlocks.HAUNTED_PURPLING_LOG.get())
			return AoABlocks.HAUNTED_PURPLING_WOOD.get().getDefaultState();

		if (state.getBlock() == AoABlocks.HAUNTED_EYE_LOG.get())
			return AoABlocks.HAUNTED_EYES_LOG.get().getDefaultState();

		if (state.getBlock() == AoABlocks.HAUNTED_EYES_LOG.get())
			return AoABlocks.HAUNTED_EYES_WOOD.get().getDefaultState();

		return AoABlocks.HAUNTED_WOOD.get().getDefaultState();
	}
}
