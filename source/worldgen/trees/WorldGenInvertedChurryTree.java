package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenInvertedChurryTree extends WorldGenTree {
	public WorldGenInvertedChurryTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	protected boolean checkSafeHeight(World world, BlockPos pos, int maxHeight, int trunkWidth) {
		return pos.getY() <= 256 && pos.getY() - maxHeight >= 1;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 25 + rand.nextInt(15);

		if (!checkSafeHeight(world, pos, trunkHeight + 3, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.up());
		IBlockState log = BlockRegister.CHURRY_LOG.getDefaultState();
		IBlockState lelyetianLeaves = BlockRegister.LELYETIAN_LEAVES.getDefaultState();
		int leafGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.DOWN), log);
			leafGap++;

			if (i > 6 && leafGap > 6 && (leafGap > 11 || rand.nextInt(5) == 0)) {
				leafGap = 0;

				buildLeafBlob(world, movablePos.toImmutable(), rand);
			}
		}

		placeBlock(world, movablePos.move(EnumFacing.DOWN), log);

		for (int x = -1; x <= 1; x++) {
			for (int y = 1; y <= 3; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(world, movablePos.add(x, -y, z), lelyetianLeaves);
				}
			}
		}

		return true;
	}

	private void buildLeafBlob(World world, BlockPos pos, Random rand) {
		IBlockState leaves = BlockRegister.CHURRY_LEAVES.getDefaultState();

		placeBlock(world, pos.north(), leaves);
		placeBlock(world, pos.south(), leaves);
		placeBlock(world, pos.east(), leaves);
		placeBlock(world, pos.west(), leaves);

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (x != 0 || z != 0)
					placeBlock(world, pos.add(x, 1, z), leaves);
			}
		}

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y >= -3; y--) {
				for (int z = -2; z <= 2; z++) {
					if (!((x == 2 || x == -2) && (z == 2 || z == -2)))
						placeBlock(world, pos.add(x, -y, z), leaves);
				}
			}
		}

		pos = pos.up(3);

		placeBlock(world, pos.north(3), leaves);
		placeBlock(world, pos.south(3), leaves);
		placeBlock(world, pos.east(3), leaves);
		placeBlock(world, pos.west(3), leaves);

		if (rand.nextBoolean()) {
			placeBlock(world, pos.north(4), leaves);
			placeBlock(world, pos.south(4), leaves);
			placeBlock(world, pos.east(4), leaves);
			placeBlock(world, pos.west(4), leaves);
		}

		placeBlock(world, pos.north(), leaves);
		placeBlock(world, pos.south(), leaves);
		placeBlock(world, pos.east(), leaves);
		placeBlock(world, pos.west(), leaves);
	}
}
