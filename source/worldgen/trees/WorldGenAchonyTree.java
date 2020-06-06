package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenAchonyTree extends WorldGenTree {
	public WorldGenAchonyTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 20 + rand.nextInt(10);
		int coreHeight = 4 + rand.nextInt(3);

		if (!checkSafeHeight(world, pos, trunkHeight + (int)(coreHeight * 1.5f), 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.ACHONY_LOG.getDefaultState();
		boolean lastRowRinged = false;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);

			if (i >= 3 && rand.nextFloat() < 0.4f && !lastRowRinged) {
				buildLeafRing(world, movablePos.toImmutable());

				lastRowRinged = true;
			}
			else {
				lastRowRinged = false;
			}
		}

		buildCrown(world, movablePos.toImmutable(), coreHeight);

		return true;
	}

	private void buildCrown(World world, BlockPos pos, int coreHeight) {
		IBlockState achonyLeaves = BlockRegister.ACHONY_LEAVES.getDefaultState();
		IBlockState lelyetianLeaves = BlockRegister.LELYETIAN_LEAVES.getDefaultState();

		buildLeafRing(world, pos.down(1));

		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				if (x != 0 || z != 0)
					placeBlock(world, pos.add(x, 0, z), achonyLeaves);
			}
		}

		for (int x = -3; x <= 3; x++) {
			placeBlock(world, pos.add(x, 1, -3), achonyLeaves);
			placeBlock(world, pos.add(x, 1, 3), achonyLeaves);
		}

		for (int z = -2; z <= 2; z++) {
			placeBlock(world, pos.add(3, 1, z), achonyLeaves);
			placeBlock(world, pos.add(-3, 1, z), achonyLeaves);
		}

		placeBlock(world, pos.add(3, 2, 2), achonyLeaves);
		placeBlock(world, pos.add(3, 2, 3), achonyLeaves);
		placeBlock(world, pos.add(3, 2, -2), achonyLeaves);
		placeBlock(world, pos.add(3, 2, -3), achonyLeaves);
		placeBlock(world, pos.add(-3, 2, 2), achonyLeaves);
		placeBlock(world, pos.add(-3, 2, 3), achonyLeaves);
		placeBlock(world, pos.add(-3, 2, -2), achonyLeaves);
		placeBlock(world, pos.add(-3, 2, -3), achonyLeaves);
		placeBlock(world, pos.add(2, 2, 3), achonyLeaves);
		placeBlock(world, pos.add(-2, 2, 3), achonyLeaves);
		placeBlock(world, pos.add(2, 2, -3), achonyLeaves);
		placeBlock(world, pos.add(-2, 2, -3), achonyLeaves);
		placeBlock(world, pos.add(3, 3, 3), achonyLeaves);
		placeBlock(world, pos.add(-3, 3, -3), achonyLeaves);
		placeBlock(world, pos.add(-3, 3, 3), achonyLeaves);
		placeBlock(world, pos.add(3, 3, -3), achonyLeaves);

		for (int x = -1; x <= 1; x++) {
			for (int y = 0; y <= coreHeight; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(world, pos.add(x, y, z), lelyetianLeaves);
				}
			}
		}

		for (int y = coreHeight; y <= (int)(coreHeight * 1.5); y++) {
			placeBlock(world, pos.add(0, y, 0), lelyetianLeaves);
		}
	}

	private void buildLeafRing(World world, BlockPos pos) {
		IBlockState achonyLeaves = BlockRegister.ACHONY_LEAVES.getDefaultState();

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (x != 0 || z != 0)
					placeBlock(world, pos.add(x, 0, z), achonyLeaves);
			}
		}
	}
}
