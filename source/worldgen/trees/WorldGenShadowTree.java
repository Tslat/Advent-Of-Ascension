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

public class WorldGenShadowTree extends WorldGenTree {
	public WorldGenShadowTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight + 1, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.SHADOW_LOG.getDefaultState();
		IBlockState barkLog = log.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		IBlockState leaves = BlockRegister.SHADOW_LEAVES.getDefaultState();
		int branchWidth = 1 + rand.nextInt(4);
		boolean additionalLeaves = rand.nextBoolean();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);
		}

		placeBlock(world, movablePos.move(EnumFacing.UP), barkLog);

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
