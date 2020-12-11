package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class BloodtwisterTreeGenerator extends TreeGenerator {
	public BloodtwisterTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(5);
		int weaveHeight = 8 + rand.nextInt(8);

		if (!checkSafeHeight(world, pos, trunkHeight + weaveHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.BLOOD_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.BLOOD_LEAVES.get().getDefaultState();
		BlockState barkLog = AoABlocks.BLOOD_WOOD.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		placeBlock(world, pos.up(1 + rand.nextInt(3)), AoABlocks.EYEBALL_LOG.get().getDefaultState());
		placeBlock(world, movablePos.add(1, 0, 0), barkLog);
		placeBlock(world, movablePos.add(0, 0, 1), barkLog);
		placeBlock(world, movablePos, barkLog);
		placeBlock(world, movablePos.add(-1, 0, 0), barkLog);
		placeBlock(world, movablePos.add(0, 0, -1), barkLog);

		placeBlock(world, movablePos.add(2, 0, 0), leaves);
		placeBlock(world, movablePos.add(0, 0, 2), leaves);
		placeBlock(world, movablePos.add(-2, 0, 0), leaves);
		placeBlock(world, movablePos.add(0, 0, -2), leaves);
		placeBlock(world, movablePos.add(3, 0, 0), leaves);
		placeBlock(world, movablePos.add(0, 0, 3), leaves);
		placeBlock(world, movablePos.add(-3, 0, 0), leaves);
		placeBlock(world, movablePos.add(0, 0, -3), leaves);

		if (rand.nextBoolean()) {
			Direction[] directions = new Direction[] {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.toImmutable();

			movablePos.move(Direction.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(world, movablePos.move(directions[direction]), leaves);
				placeBlock(world, movablePos.move(Direction.UP), leaves);

				if (i == weaveHeight && curBaseDirection < 3) {
					movablePos.setPos(basePos).move(directions[curBaseDirection], 3);
					curBaseDirection++;
					direction = curBaseDirection;
					i = 1;
				}

				if (i % turnMod == 0) {
					direction++;

					if (direction > 3)
						direction = 0;
				}
			}
		}
		else {
			Direction[] directions = new Direction[] {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.toImmutable();

			movablePos.move(Direction.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(world, movablePos.move(directions[direction]), leaves);
				placeBlock(world, movablePos.move(Direction.UP), leaves);

				if (i == weaveHeight && curBaseDirection < 3) {
					movablePos.setPos(basePos).move(directions[curBaseDirection], 3);
					curBaseDirection++;
					direction = curBaseDirection;
					i = 1;
				}

				if (i % turnMod == 0) {
					direction++;

					if (direction > 3)
						direction = 0;
				}
			}
		}

		return true;
	}
}
