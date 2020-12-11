package net.tslat.aoa3.worldgen.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.block.decoration.sapling.SaplingBlock;
import net.tslat.aoa3.block.generation.plants.VinesBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class CreepTreeGenerator extends TreeGenerator {
	private static final BlockState leaves = AoABlocks.CREEP_LEAVES.get().getDefaultState();
	private static final BlockState vines = AoABlocks.CREEP_VINES.get().getDefaultState();

	private final ArrayList<BlockPos> leafPositions = new ArrayList<BlockPos>();

	public CreepTreeGenerator(@Nullable SaplingBlock sapling) {
		super(sapling);
	}

	@Override
	public boolean generate(IWorld world, Random rand, BlockPos pos) {
		int trunkHeight = 6 + rand.nextInt(10);

		if (!checkSafeHeight(world, pos, trunkHeight + 2, 1))
			return false;

		if (!checkAndPrepSoil(world, pos, 1))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable(pos.down());
		BlockState log = AoABlocks.CREEP_LOG.get().getDefaultState();
		int ringLocation = 1 + rand.nextInt(trunkHeight - 3);

		if (ringLocation < trunkHeight / 2)
			ringLocation = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(world, movablePos.move(Direction.UP), log);

			if (i == ringLocation) {
				int ringWidth = 1 + rand.nextInt(2);

				for (int y = 0; y <= rand.nextInt(2); y++) {
					for (int x = -ringWidth; x <= ringWidth; x++) {
						for (int z = -ringWidth; z <= ringWidth; z++) {
							placeLeafBlock(world, movablePos.add(x, y, z));
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
						placeLeafBlock(world, movablePos.add(x, y, z));
					}
				}
			}
		}
		else {
			for (int y = 0; y > -rand.nextInt(3); y--) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						placeLeafBlock(world, movablePos.add(x, y, z));
					}
				}
			}

			if (rand.nextBoolean()) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (x * x + z * z <= 4)
							placeLeafBlock(world, movablePos.add(x, 1, z));
					}
				}
			}
			else {
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						placeLeafBlock(world, movablePos.add(x, 1, z));
					}
				}
			}
		}

		populateVines(world, rand);

		return true;
	}

	private void placeLeafBlock(IWorld world, BlockPos leafPos) {
		placeBlock(world, leafPos, leaves);
		leafPositions.add(leafPos);
	}

	private void populateVines(IWorld world, Random rand) {
		for (BlockPos pos : leafPositions) {
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				BlockPos vinePos;

				if (rand.nextFloat() < 0.65f && world.isAirBlock(vinePos = pos.offset(dir))) {
					BlockState state = ((VinesBlock)vines.getBlock()).getStateForPosition(world, vinePos);
					int count = 0;

					placeBlock(world, vinePos, state);

					while (count < 2 && rand.nextFloat() < 0.65f && world.isAirBlock(vinePos = vinePos.down())) {
						state = ((VinesBlock)vines.getBlock()).getStateForPosition(world, vinePos);

						placeBlock(world, vinePos, state);

						count++;
					}
				}
			}
		}
	}
}
