package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenEyebushTree extends WorldGenTree {
	public WorldGenEyebushTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		boolean doubleTrunk = rand.nextBoolean();

		if (!checkSafeHeight(world, pos, (doubleTrunk ? 2 : 1) + 5, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		IBlockState log = BlockRegister.EYEBALL_LOG.getDefaultState();

		placeBlock(world, pos, log);

		pos = pos.up();

		if (doubleTrunk) {
			placeBlock(world, pos, log);

			pos = pos.up();
		}

		placeBlock(world, pos.north(), log);
		placeBlock(world, pos.south(), log);
		placeBlock(world, pos.east(), log);
		placeBlock(world, pos.west(), log);
		placeBlock(world, pos.up(), log);
		placeBlock(world, pos.up(2), log);

		buildLeafRing(world, pos, 2);
		buildLeafRing(world, pos.up(), 3);
		buildLeafRing(world, pos.up(2), 2);
		buildLeafRing(world, pos.up(3), 1);

		placeBlock(world, pos.up(4), BlockRegister.VEIN_LEAVES.getDefaultState());

		return true;
	}

	private void buildLeafRing(World world, BlockPos pos, int radius) {
		IBlockState leaves = BlockRegister.VEIN_LEAVES.getDefaultState();

		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				if (!((x == radius || x == -radius) && (z == radius || z == -radius)))
					placeBlock(world, pos.add(x, 0, z), leaves);
			}
		}
	}
}
