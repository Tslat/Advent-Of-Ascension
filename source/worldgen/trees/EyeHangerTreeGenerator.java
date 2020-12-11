package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class EyeHangerTreeGenerator extends TreeGenerator {
	public EyeHangerTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 5 + rand.nextInt(5);
		int weaveHeight = 8 + rand.nextInt(8);

		if (!checkSafeHeight(world, pos, trunkHeight + weaveHeight, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.SHADOW_LOG.get().getDefaultState();
		BlockState barkLog = AoABlocks.SHADOW_WOOD.get().getDefaultState();
		int branchWidth = 1 + rand.nextInt(3);

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);
		}

		for (int i = 1; i <= branchWidth; i++) {
			placeBlock(world, movablePos.add(i, 0, 0), barkLog);
			placeBlock(world, movablePos.add(-i, 0, 0), barkLog);
			placeBlock(world, movablePos.add(0, 0, i), barkLog);
			placeBlock(world, movablePos.add(0, 0, -i), barkLog);

			if (i == branchWidth && rand.nextBoolean()) {
				placeBlock(world, movablePos.add(i, 1, 0), log);
				placeBlock(world, movablePos.add(-i, 1, 0), log);
				placeBlock(world, movablePos.add(0, 1, i), log);
				placeBlock(world, movablePos.add(0, 1, -i), log);
			}
		}

		doLeaves(world, movablePos.up(), rand, branchWidth);

		return true;
	}

	private void doLeaves(IWorld world, BlockPos pos, Random rand, int branchWidth) {
		BlockState leaves = AoABlocks.SHADOWBLOOD_LEAVES.get().getDefaultState();

		for (int i = -branchWidth - 1; i <= branchWidth + 1; i++) {
			placeBlock(world, pos.add(-i, 0, 0), leaves);

			if (Math.abs(i) > branchWidth && rand.nextInt(7) == 0)
				doEyeHanger(world, pos.add(i, 0, 0), rand);

			for (int j = 0; j <= branchWidth; j++) {
				if (Math.abs(i) <= branchWidth - j + 1) {
					placeBlock(world, pos.add(i, 0, j + 1), leaves);
					placeBlock(world, pos.add(-i, 0, -j - 1), leaves);

					if (i != 0) {
						if (rand.nextInt(7) == 0) {
							doEyeHanger(world, pos.add(i, 0, -j - 1), rand);
						}

						if (rand.nextInt(7) == 0) {
							doEyeHanger(world, pos.add(i, 0, j + 1), rand);
						}
					}
				}
			}
		}

		for (int i = -branchWidth; i <= branchWidth; i++) {
			for (int j = 0; j <= branchWidth - Math.abs(i); j++) {
				placeBlock(world, pos.add(i, 1, j), leaves);
				placeBlock(world, pos.add(i, 1, -j), leaves);
			}
		}

		if (branchWidth == 3) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(world, pos.add(x, 2, z), leaves);
				}
			}
		}
	}

	private void doEyeHanger(IWorld world, BlockPos leafPos, Random rand) {
		BlockState bloodStrands = AoABlocks.BLOOD_STRANDS.get().getDefaultState();
		BlockState eye = AoABlocks.EYE_BLOCK.get().getDefaultState();
		int length = 2 + rand.nextInt(3);

		for (int i = 1; i <= length; i++) {
			placeBlock(world, leafPos.down(i), bloodStrands);
		}

		placeBlock(world, leafPos.down(length + 1), eye);
	}
}
