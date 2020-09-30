package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.saplings.SaplingBlock;
import net.tslat.aoa3.common.registration.BlockRegister;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldGenCreepTree extends WorldGenTree {
	private static final IBlockState leaves = BlockRegister.CREEP_LEAVES.getDefaultState();
	private static final IBlockState vines = BlockRegister.CREEP_VINES.getDefaultState();

	public WorldGenCreepTree(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int trunkHeight = 6 + rand.nextInt(10);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos(pos.down());
		IBlockState log = BlockRegister.CREEP_LOG.getDefaultState();
		int ringLocation = 1 + rand.nextInt(trunkHeight - 3);

		if (ringLocation < trunkHeight / 2)
			ringLocation = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(EnumFacing.UP), log);

			if (i == ringLocation) {
				int ringWidth = 1 + rand.nextInt(2);

				for (int y = 0; y <= rand.nextInt(2); y++) {
					for (int x = -ringWidth; x <= ringWidth; x++) {
						for (int z = -ringWidth; z <= ringWidth; z++) {
							placeLeafBlock(world, rand, movablePos.add(x, y, z));
						}
					}
				}
			}
		}

		if (rand.nextBoolean()) {
			int width = 1 + rand.nextInt(2);

			for (int y = 0; y <= 1 + rand.nextInt(2); y++) {
				for (int x = -width; x <= width; x++) {
					for (int z = -width; z <= width; z++) {
						placeLeafBlock(world, rand, movablePos.add(x, y, z));
					}
				}
			}
		}
		else {
			for (int y = 0; y > -rand.nextInt(3); y--) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						placeLeafBlock(world, rand, movablePos.add(x, y, z));
					}
				}
			}

			if (rand.nextBoolean()) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (x * x + z * z <= 4)
							placeLeafBlock(world, rand, movablePos.add(x, 1, z));
					}
				}
			}
			else {
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						placeLeafBlock(world, rand, movablePos.add(x, 1, z));
					}
				}
			}
		}

		return true;
	}

	private void placeLeafBlock(World world, Random rand, BlockPos leafPos) {
		placeBlock(world, leafPos, leaves);

		for (EnumFacing dir : EnumFacing.HORIZONTALS) {
			BlockPos vinePos;

			if (rand.nextFloat() < 0.65f && world.isAirBlock(vinePos = leafPos.offset(dir))) {
				placeBlock(world, vinePos, vines.withProperty(BlockVine.getPropertyFor(dir.getOpposite()), true));

				if (world.isAirBlock(vinePos.down())) {
					placeBlock(world, vinePos.down(), vines.withProperty(BlockVine.getPropertyFor(dir.getOpposite()), true));

					if (world.isAirBlock(vinePos.down(2)))
						placeBlock(world, vinePos.down(2), vines.withProperty(BlockVine.getPropertyFor(dir.getOpposite()), true));
				}
			}
		}
	}
}
