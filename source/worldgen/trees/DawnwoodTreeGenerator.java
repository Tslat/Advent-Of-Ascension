package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class DawnwoodTreeGenerator extends TreeGenerator {
	public DawnwoodTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 15 + rand.nextInt(10);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.DAWN_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.DAWN_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);

			if (rand.nextInt(4) == 0)
				placeBlock(world, movablePos.north(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(world, movablePos.south(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(world, movablePos.east(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(world, movablePos.west(), leaves);
		}

		if (rand.nextInt(3) == 0) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(world, movablePos.add(x, -1, z), leaves);
					placeBlock(world, movablePos.add(x, 1, z), leaves);
				}
			}

			int leafWidth = 2 + rand.nextInt(2);

			for (int x = -leafWidth; x <= leafWidth; x++) {
				for (int z = -leafWidth; z <= leafWidth; z++) {
					placeBlock(world, movablePos.add(x, 0, z), leaves);
				}
			}

			placeBlock(world, movablePos.add(0, 2, 0), leaves);
			placeBlock(world, movablePos.add(1, -2, 0), leaves);
			placeBlock(world, movablePos.add(-1, -2, 0), leaves);
			placeBlock(world, movablePos.add(0, -2, 1), leaves);
			placeBlock(world, movablePos.add(0, -2, -1), leaves);
		}
		else if (rand.nextBoolean()) {
			placeBlock(world, movablePos.move(Direction.UP), leaves);
		}

		return true;
	}
}
