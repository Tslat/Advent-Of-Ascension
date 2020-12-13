package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class ShadowTreeGenerator extends TreeGenerator {
	public ShadowTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight + 1, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.SHADOW_LOG.get().getDefaultState();
		BlockState barkLog = AoABlocks.SHADOW_WOOD.get().getDefaultState();
		BlockState leaves = AoABlocks.SHADOW_LEAVES.get().getDefaultState();
		int branchWidth = 1 + rand.nextInt(4);
		boolean additionalLeaves = rand.nextBoolean();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		placeBlock(world, movablePos.move(Direction.UP), barkLog);

		for (int i = 1; i <= branchWidth; i++) {
			placeBlock(world, movablePos.north(i), barkLog);
			placeBlock(world, movablePos.south(i), barkLog);
			placeBlock(world, movablePos.east(i), barkLog);
			placeBlock(world, movablePos.west(i), barkLog);
		}

		for (int i = branchWidth + 1; i <= branchWidth + 3; i++) {
			placeBlock(world, movablePos.add(i, 0, 0), leaves);
			placeBlock(world, movablePos.add(i, -2, 1), leaves);
			placeBlock(world, movablePos.add(i, -2, -1), leaves);
			placeBlock(world, movablePos.add(-i, 0, 0), leaves);
			placeBlock(world, movablePos.add(-i, -2, 1), leaves);
			placeBlock(world, movablePos.add(-i, -2, -1), leaves);
			placeBlock(world, movablePos.add(0, 0, i), leaves);
			placeBlock(world, movablePos.add(1, -2, i), leaves);
			placeBlock(world, movablePos.add(-1, -2, i), leaves);
			placeBlock(world, movablePos.add(0, 0, -i), leaves);
			placeBlock(world, movablePos.add(1, -2, -i), leaves);
			placeBlock(world, movablePos.add(-1, -2, -i), leaves);

			if (i == branchWidth + 3) {
				placeBlock(world, movablePos.add(i, -1, 0), leaves);
				placeBlock(world, movablePos.add(-i, -1, 0), leaves);
				placeBlock(world, movablePos.add(0, -1, i), leaves);
				placeBlock(world, movablePos.add(0, -1, -i), leaves);

				placeBlock(world, movablePos.add(i, -3, 1), leaves);
				placeBlock(world, movablePos.add(i, -3, -1), leaves);
				placeBlock(world, movablePos.add(-i, -3, 1), leaves);
				placeBlock(world, movablePos.add(-i, -3, -1), leaves);
				placeBlock(world, movablePos.add(1, -3, i), leaves);
				placeBlock(world, movablePos.add(-1, -3, i), leaves);
				placeBlock(world, movablePos.add(1, -3, -i), leaves);
				placeBlock(world, movablePos.add(-1, -3, -i), leaves);
				placeBlock(world, movablePos.add(i, -4, 1), leaves);
				placeBlock(world, movablePos.add(i, -4, -1), leaves);
				placeBlock(world, movablePos.add(-i, -4, 1), leaves);
				placeBlock(world, movablePos.add(-i, -4, -1), leaves);
				placeBlock(world, movablePos.add(1, -4, i), leaves);
				placeBlock(world, movablePos.add(-1, -4, i), leaves);
				placeBlock(world, movablePos.add(1, -4, -i), leaves);
				placeBlock(world, movablePos.add(-1, -4, -i), leaves);
			}
			else {
				placeBlock(world, movablePos.add(i, -1, 0), barkLog);
				placeBlock(world, movablePos.add(-i, -1, 0), barkLog);
				placeBlock(world, movablePos.add(0, -1, i), barkLog);
				placeBlock(world, movablePos.add(0, -1, -i), barkLog);

				if (i == branchWidth + 1) {
					placeBlock(world, movablePos.add(i, -3, 1), leaves);
					placeBlock(world, movablePos.add(i, -3, -1), leaves);
					placeBlock(world, movablePos.add(-i, -3, 1), leaves);
					placeBlock(world, movablePos.add(-i, -3, -1), leaves);
					placeBlock(world, movablePos.add(1, -3, i), leaves);
					placeBlock(world, movablePos.add(-1, -3, i), leaves);
					placeBlock(world, movablePos.add(1, -3, -i), leaves);
					placeBlock(world, movablePos.add(-1, -3, -i), leaves);
					placeBlock(world, movablePos.add(i, -4, 1), leaves);
					placeBlock(world, movablePos.add(i, -4, -1), leaves);
					placeBlock(world, movablePos.add(-i, -4, 1), leaves);
					placeBlock(world, movablePos.add(-i, -4, -1), leaves);
					placeBlock(world, movablePos.add(1, -4, i), leaves);
					placeBlock(world, movablePos.add(-1, -4, i), leaves);
					placeBlock(world, movablePos.add(1, -4, -i), leaves);
					placeBlock(world, movablePos.add(-1, -4, -i), leaves);
				}

				if (additionalLeaves) {
					placeBlock(world, movablePos.add(i, -1, 1), leaves);
					placeBlock(world, movablePos.add(i, -1, -1), leaves);
					placeBlock(world, movablePos.add(-i, -1, 1), leaves);
					placeBlock(world, movablePos.add(-i, -1, -1), leaves);
					placeBlock(world, movablePos.add(1, -1, i), leaves);
					placeBlock(world, movablePos.add(-1, -1, i), leaves);
					placeBlock(world, movablePos.add(1, -1, -i), leaves);
					placeBlock(world, movablePos.add(-1, -1, -i), leaves);
				}
			}
		}



		return true;
	}
}
