package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class LuniciaTreeGenerator extends TreeGenerator {
	public LuniciaTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		if (rand.nextBoolean()) {
			return generateTree1(world, rand, pos);
		}
		else {
			return generateTree2(world, rand, pos);
		}
	}

	private boolean generateTree1(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 6 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.LUNIDE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUNICIA_LEAVES.get().getDefaultState();

		int leafGap = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);

			if (leafGap >= 2 && rand.nextFloat() < 0.65f) {
				leafGap = 0;

				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (Math.abs(x) == 2 || Math.abs(z) == 2)
							placeBlock(world, movablePos.add(x, 0, z), leaves);
					}
				}
			}

			leafGap++;
		}

		return true;
	}

	private boolean generateTree2(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 6 + rand.nextInt(6);

		if (!checkSafeHeight(world, pos, trunkHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.LUNIDE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUNICIA_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		int leafPillars = 2 + rand.nextInt(5);

		for (double i = 0; i < Math.PI * 2; i += Math.PI / leafPillars) {
			int x = (int)Math.round(Math.cos(i) * 2);
			int z = (int)Math.round(Math.sin(i) * 2);
			int initY = rand.nextInt(2);
			int endY = rand.nextInt(2);

			for (int y = initY; y <= trunkHeight - 3 - endY; y++) {
				placeBlock(world, movablePos.add(x, -y, z), leaves);
			}
		}

		return true;
	}
}
