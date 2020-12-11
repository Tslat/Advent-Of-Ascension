package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class RunicTreeGenerator extends TreeGenerator {
	public RunicTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		BlockPos multiSaplingPos = findMultiSaplingPosition(world, rand, pos, 2);

		switch (rand.nextInt(multiSaplingPos != null ? 5 : 4)) {
			case 0:
				return generateTree1(world, rand, multiSaplingPos == null ? pos : multiSaplingPos);
			case 1:
				return generateTree2(world, rand, multiSaplingPos == null ? pos : multiSaplingPos);
			case 2:
				return generateTree3(world, rand, multiSaplingPos == null ? pos : multiSaplingPos);
			case 3:
				return generateTree4(world, rand, multiSaplingPos == null ? pos : multiSaplingPos);
			case 4:
				return generateTree5(world, rand, multiSaplingPos == null ? pos : multiSaplingPos);
		}

		return false;
	}

	private boolean generateTree1(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 4 + rand.nextInt(5);
		int leafHeight = 2 + rand.nextInt(3);

		if (!checkSafeHeight(world, pos, trunkHeight + leafHeight - 1, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.RUNIC_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		placeBlock(world, movablePos.add(1, 0, 0), log);
		placeBlock(world, movablePos.add(0, 0, 1), log);
		placeBlock(world, movablePos.add(0, 0, -1), log);
		placeBlock(world, movablePos.add(-1, 0, 0), log);
		placeBlock(world, movablePos.add(1, 1, 0), log);
		placeBlock(world, movablePos.add(0, 1, 1), log);
		placeBlock(world, movablePos.add(0, 1, -1), log);
		placeBlock(world, movablePos.add(-1, 1, 0), log);

		for (int i = 0; i < leafHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), leaves);
		}

		return true;
	}

	private boolean generateTree2(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 4 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight + 3, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.RUNIC_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		for (int i = -2; i < 3; i++) {
			if (i != 0) {
				placeBlock(world, movablePos.add(i, 0, 0), log);
				placeBlock(world, movablePos.add(0, 0, i), log);

				if (Math.abs(i) == 2) {
					placeBlock(world, movablePos.add(i, 1, 0), log);
					placeBlock(world, movablePos.add(0, 1, i), log);
				}
			}
		}

		int leafHeight = 2 + rand.nextInt(3);

		for (int x = -1; x <= 1; x++) {
			for (int y = 1; y <= leafHeight; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(world, movablePos.add(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree3(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 7 + rand.nextInt(6);
		int bulbWidth = 3 + rand.nextInt(2);

		if (!checkSafeHeight(world, pos, trunkHeight + bulbWidth * 2 - 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.RUNIC_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		for (int i = -bulbWidth; i <= bulbWidth; i++) {
			if (i != 0) {
				placeBlock(world, movablePos.add(i, 0, 0), log);
				placeBlock(world, movablePos.add(0, 0, i), log);

				if (Math.abs(i) == bulbWidth) {
					placeBlock(world, movablePos.add(i, 1, 0), log);
					placeBlock(world, movablePos.add(i, 2, 0), log);
					placeBlock(world, movablePos.add(0, 1, i), log);
					placeBlock(world, movablePos.add(0, 2, i), log);
				}
			}
		}

		for (int x = -bulbWidth + 1; x <= bulbWidth - 1; x++) {
			for (int z = -bulbWidth + 1; z <= bulbWidth - 1; z++) {
				for (int y = 1; y <= bulbWidth * 2 - 2; y++) {
					if ((y != 1 && y != bulbWidth * 2 - 2) || (Math.abs(x) != bulbWidth - 1 && Math.abs(z) != bulbWidth - 1))
						placeBlock(world, movablePos.add(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree4(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 9 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.RUNIC_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		int branchHeight = 2 + rand.nextInt(3);

		for (int i = -4; i <= 4; i++) {
			if (i != 0) {
				placeBlock(world, movablePos.add(i, 0, 0), log);
				placeBlock(world, movablePos.add(0, 0, i), log);

				if (Math.abs(i) == 4) {
					for (int j = 1; j <= branchHeight; j++) {
						placeBlock(world, movablePos.add(i, -j, 0), log);
						placeBlock(world, movablePos.add(0, -j, i), log);
					}
				}
			}
		}

		for (int y = -1; y <= 1; y++) {
			int width = 3 - Math.abs(y);

			for (int x = -width; x <= width; x++) {
				for (int z = -width; z <= width; z++) {
					if (x * x + z * z <= width * width) {
						placeBlock(world, movablePos.add(x, y - 2, z), leaves);
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree5(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 4 + rand.nextInt(5);
		int leafHeight = 2 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + leafHeight - 1, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		BlockState log = AoABlocks.RUNIC_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.RUNIC_LEAVES.get().getDefaultState();

		for (int x = 0; x <= 1; x++) {
			for (int y = 0; y <= trunkHeight; y++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, y, z), log);
				}
			}
		}

		for (int x = -1; x <= 2; x++) {
			for (int y = 0; y <= 1; y++) {
				for (int z = -1; z <= 2; z++) {
					if (x != -1 && x != 2 || z != -1 && z != 2)
						placeBlock(world, pos.add(x, trunkHeight + y, z), log);
				}
			}
		}

		for (int x = 0; x <= 1; x++) {
			for (int y = 1; y <= leafHeight; y++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, trunkHeight + y, z), leaves);
				}
			}
		}

		return true;
	}
}
