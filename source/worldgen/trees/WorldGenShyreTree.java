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

public class WorldGenShyreTree extends WorldGenTree {
	private final IBlockState leavesBlock;

	public WorldGenShyreTree(@Nullable SaplingBlock sapling, @Nullable LeavesBlock leaves, Random rand) {
		super(sapling);

		if (leaves == null) {
			if (rand.nextBoolean()) {
				leavesBlock = BlockRegister.BRIGHT_SHYRE_LEAVES.getDefaultState();
			}
			else {
				leavesBlock = BlockRegister.SHYRE_LEAVES.getDefaultState();
			}
		}
		else {
			leavesBlock = leaves.getDefaultState();
		}
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + 3, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.SHYRE_LOG.getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);
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
