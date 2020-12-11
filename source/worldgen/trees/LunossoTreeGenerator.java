package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class LunossoTreeGenerator extends TreeGenerator {
	public LunossoTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		switch (rand.nextInt(3)) {
			case 0:
				return generateTree1(world, rand, pos);
			case 1:
				return generateTree2(world, rand, pos);
			case 2:
			default:
				return generateTree3(world, rand, pos);
		}
	}

	private boolean generateTree1(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 3 + rand.nextInt(5);
		int leafLoopWidth = 1 + rand.nextInt(2);

		if (!checkSafeHeight(world, pos, trunkHeight + leafLoopWidth + 3, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.LUNIDE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		movablePos.move(Direction.UP, 2);

		int leafLoop = rand.nextInt(3);

		if (leafLoop == 2 || leafLoop == 0) {
			for (int x = -leafLoopWidth; x <= leafLoopWidth; x++) {
				placeBlock(world, movablePos.add(x, 0, 0), leaves);
				placeBlock(world, movablePos.add(x, leafLoopWidth * 2, 0), leaves);

				if (Math.abs(x) == leafLoopWidth) {
					for (int y = 1; y <= leafLoopWidth + 1; y++) {
						placeBlock(world, movablePos.add(x, y, 0), leaves);
					}
				}
			}
		}

		if (leafLoop == 2 || leafLoop == 1) {
			for (int z = -leafLoopWidth; z <= leafLoopWidth; z++) {
				placeBlock(world, movablePos.add(0, 0, z), leaves);
				placeBlock(world, movablePos.add(0, leafLoopWidth * 2, z), leaves);

				if (Math.abs(z) == leafLoopWidth) {
					for (int y = 1; y <= leafLoopWidth + 1; y++) {
						placeBlock(world, movablePos.add(0, y, z), leaves);
					}
				}
			}
		}

		return true;
	}

	private boolean generateTree2(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 3 + rand.nextInt(5);
		int leafRings = 1 + rand.nextInt(3);

		if (!checkSafeHeight(world, pos, trunkHeight + leafRings * 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.LUNIDE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		for (int y = 2; y <= leafRings * 2; y += 2) {
			int ringWidth = 1 + rand.nextInt(2);

			for (int x = -ringWidth; x <= ringWidth; x++) {
				for (int z = -ringWidth; z <= ringWidth; z++) {
					if (Math.abs(x) == ringWidth || Math.abs(z) == ringWidth)
						placeBlock(world, movablePos.add(x, y, z), leaves);
				}
			}
		}

		return true;
	}

	private boolean generateTree3(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 3 + rand.nextInt(5);

		if (!checkSafeHeight(world, pos, trunkHeight + 8, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.LUNIDE_LOG.get().getDefaultState();
		BlockState leaves = AoABlocks.LUNOSSO_LEAVES.get().getDefaultState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		movablePos.move(Direction.UP, 5);

		for (int x = -3; x <= 3; x++) {
			for (int y = -3; y <= 3; y++) {
				for (int z = -3; z <= 3; z++) {
					if (x * x + y * y + z * z == 9)
						placeBlock(world, movablePos.add(x, y, z), leaves);
				}
			}
		}

		return true;
	}
}
