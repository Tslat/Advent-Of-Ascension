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

public class ShyreTreeGenerator extends TreeGenerator {
	private final BlockState leavesBlock;

	public ShyreTreeGenerator(@Nullable SaplingBlock sapling, @Nullable LeavesBlock leaves, Random rand) {
		super(sapling);

		if (leaves == null) {
			if (rand.nextBoolean()) {
				leavesBlock = AoABlocks.BRIGHT_SHYRE_LEAVES.get().getDefaultState();
			}
			else {
				leavesBlock = AoABlocks.SHYRE_LEAVES.get().getDefaultState();
			}
		}
		else {
			leavesBlock = leaves.getDefaultState();
		}
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + 3, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.SHYRE_LOG.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		switch (rand.nextInt(4)) {
			case 0:
				for (int i = 0; i >= -2 - rand.nextInt(2); i--) {
					placeBlock(world, movablePos.add(-1, i, 0), leavesBlock);
					placeBlock(world, movablePos.add(1, i, 0), leavesBlock);
					placeBlock(world, movablePos.add(0, i, 1), leavesBlock);
					placeBlock(world, movablePos.add(0, i, -1), leavesBlock);
				}

				placeBlock(world, movablePos.add(0, 1, 0), leavesBlock);
				break;
			case 1:
				for (int y = 0; y >= -1 - rand.nextInt(2); y--) {
					for (int x = -1; x <= 1; x++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(world, movablePos.add(x, y, z), leavesBlock);
						}
					}
				}

				placeBlock(world, movablePos.add(0, 1, 0), leavesBlock);
				placeBlock(world, movablePos.add(1, 1, 0), leavesBlock);
				placeBlock(world, movablePos.add(0, 1, 1), leavesBlock);
				placeBlock(world, movablePos.add(-1, 1, 0), leavesBlock);
				placeBlock(world, movablePos.add(0, 1, -1), leavesBlock);
				break;
			case 2:
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 3; y += 2) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(world, movablePos.add(x, y, z), leavesBlock);
						}
					}
				}

				placeBlock(world, movablePos.add(-1, 2, -1), leavesBlock);
				placeBlock(world, movablePos.add(1, 2, 1), leavesBlock);
				placeBlock(world, movablePos.add(-1, 2, 1), leavesBlock);
				placeBlock(world, movablePos.add(1, 2, -1), leavesBlock);
				break;
			case 3:
				for (int i = 0; i <= 3; i++) {
					if (i != 1) {
						placeBlock(world, movablePos.add(-1, i, 0), leavesBlock);
						placeBlock(world, movablePos.add(1, i, 0), leavesBlock);
						placeBlock(world, movablePos.add(0, i, 1), leavesBlock);
						placeBlock(world, movablePos.add(0, i, -1), leavesBlock);
					}
				}

				placeBlock(world, movablePos.add(0, 1, 0), leavesBlock);
				break;
		}

		return true;
	}
}
