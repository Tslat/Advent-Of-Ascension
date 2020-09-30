package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.block.BlockLog.LOG_AXIS;

public class WorldGenBloodtwisterTree extends WorldGenTree {
	public WorldGenBloodtwisterTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(5);
		int weaveHeight = 8 + rand.nextInt(8);

		if (!checkSafeHeight(world, pos, trunkHeight + weaveHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.BLOOD_LOG.getDefaultState();
		IBlockState leaves = BlockRegister.BLOOD_LEAVES.getDefaultState();
		IBlockState barkLog = log.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);
		}

		placeBlock(world, pos.up(1 + rand.nextInt(3)), BlockRegister.EYEBALL_LOG.getDefaultState());
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
			EnumFacing[] directions = new EnumFacing[] {EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.toImmutable();

			movablePos.move(EnumFacing.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(world, movablePos.move(directions[direction]), leaves);
				placeBlock(world, movablePos.move(EnumFacing.UP), leaves);

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
			EnumFacing[] directions = new EnumFacing[] {EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.toImmutable();

			movablePos.move(EnumFacing.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(world, movablePos.move(directions[direction]), leaves);
				placeBlock(world, movablePos.move(EnumFacing.UP), leaves);

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
