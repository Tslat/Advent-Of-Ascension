package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenStranglewoodTree extends WorldGenTree {
	public WorldGenStranglewoodTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		pos = findMultiSaplingPosition(world, rand, pos, 2);

		if (pos == null)
			return false;

		int trunkHeight = 10 + rand.nextInt(26);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 2))
			return false;

		if (!checkAndPrepSoil(world, pos, 2))
			return false;

		IBlockState log = BlockRegister.STRANGLEWOOD_LOG.getDefaultState();
		IBlockState leaves = BlockRegister.STRANGLEWOOD_LEAVES.getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			for (int x = 0; x <= 1; x++) {
				for (int z = 0; z <= 1; z++) {
					placeBlock(world, pos.add(x, i, z), log);
				}
			}
		}

		if (trunkHeight > 15) {
			buildTallCrown(world, pos.up(trunkHeight -1 ), rand);
		}
		else {
			buildShortCrown(world, pos.up(trunkHeight - 1), rand);

			for (int x = -1; x <= 2; x++) {
				for (int z = -1; z <= 2; z++) {
					if (x != -1 && x != 2 || z != -1 && z != 2)
						placeBlock(world, pos.add(x, trunkHeight - 3, z), leaves);
				}
			}
		}

		return true;
	}

	private void buildTallCrown(World world, BlockPos trunkTopPos, Random rand) {
		IBlockState log = BlockRegister.STRANGLEWOOD_LOG.getDefaultState();

		for (int x = 0; x < 5; x++) {
			placeBlock(world, trunkTopPos.add(2 + x, 0, 0), log);
			placeBlock(world, trunkTopPos.add(2 + x, 0, 1), log);
			placeBlock(world, trunkTopPos.add(-x - 1, 0, 0), log);
			placeBlock(world, trunkTopPos.add(-x - 1, 0, 1), log);
		}

		for (int z = 0; z < 5; z++) {
			placeBlock(world, trunkTopPos.add(0, 0, 2 + z), log);
			placeBlock(world, trunkTopPos.add(1, 0, 2 + z), log);
			placeBlock(world, trunkTopPos.add(0, 0, -z - 1), log);
			placeBlock(world, trunkTopPos.add(1, 0, -z - 1), log);
		}

		buildTallNorthSouthLeaves(world, trunkTopPos.add(0, 0, 6), rand);
		buildTallNorthSouthLeaves(world, trunkTopPos.add(0, 0, -5), rand);
		buildTallEastWestLeaves(world, trunkTopPos.add(-5, 0, 0), rand);
		buildTallEastWestLeaves(world, trunkTopPos.add(6, 0, 0), rand);
	}

	private void buildTallNorthSouthLeaves(World world, BlockPos centralPos, Random rand) {
		IBlockState leaves = BlockRegister.STRANGLEWOOD_LEAVES.getDefaultState();

		for (int x = -1; x <= 2; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -2; z <= 2; z++) {
					placeBlock(world, centralPos.add(x, y, z), leaves);

					if (y == 1 && z != -2 && z != 2) {
						placeBlock(world, centralPos.add(x, 2, z), leaves);
						placeBlock(world, centralPos.add(x, -2, z), leaves);
					}

					if (x == 0 && z != -2 && z != 2) {
						placeBlock(world, centralPos.add(-2, y, z), leaves);
						placeBlock(world, centralPos.add(3, y, z), leaves);
					}
				}
			}
		}
	}

	private void buildTallEastWestLeaves(World world, BlockPos centralPos, Random rand) {
		IBlockState leaves = BlockRegister.STRANGLEWOOD_LEAVES.getDefaultState();

		for (int x = -2; x <= 2; x++) {
			for (int y = -1; y <= 1; y++ ) {
				for (int z = -1; z <= 2; z++) {
					placeBlock(world, centralPos.add(x, y, z), leaves);

					if (y == 1 && x != -2 && x != 2) {
						placeBlock(world, centralPos.add(x, 2, z), leaves);
						placeBlock(world, centralPos.add(x, -2, z), leaves);
					}

					if (z == 0 && x != -2 && x != 2) {
						placeBlock(world, centralPos.add(x, y, -2), leaves);
						placeBlock(world, centralPos.add(x, y, 3), leaves);
					}
				}
			}
		}
	}

	private void buildShortCrown(World world, BlockPos trunkTopPos, Random rand) {
		IBlockState log = BlockRegister.STRANGLEWOOD_LOG.getDefaultState();
		IBlockState leaves = BlockRegister.STRANGLEWOOD_LEAVES.getDefaultState();

		for (int i = 0; i < 4; i++) {
			placeBlock(world, trunkTopPos.add(i + 2, -4 + i, 0), log);
			placeBlock(world, trunkTopPos.add(i + 2, -4 + i, 1), log);
			placeBlock(world, trunkTopPos.add(0, -4 + i, i + 2), log);
			placeBlock(world, trunkTopPos.add(1, -4 + i, i + 2), log);
			placeBlock(world, trunkTopPos.add(-1 + -i, -4 + i, 0), log);
			placeBlock(world, trunkTopPos.add(-1 + -i, -4 + i, 1), log);
			placeBlock(world, trunkTopPos.add(0, -4 + i, -1 + -i), log);
			placeBlock(world, trunkTopPos.add(1, -4 + i, -1 + -i), log);
		}

		for (int i = 5; i > 2; i--) {
			for (int x2 = -i; x2 <= i + 1; x2++) {
				for (int z2 = -i; z2 <= i + 1; z2++) {
					int x3 = x2;
					int z3 = z2;

					if (x3 < 0)
						x3 -= 1;

					if (z3 < 0)
						z3 -= 1;

					if (Math.abs(x3) <= i - (Math.abs(z3) - 2))
						placeBlock(world, trunkTopPos.add(x2, (7 - i) - 3, z2), leaves);
				}
			}
		}
	}
}
