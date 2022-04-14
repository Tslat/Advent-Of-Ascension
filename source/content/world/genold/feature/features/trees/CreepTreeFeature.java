package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.block.generation.plants.VinesBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class CreepTreeFeature extends AoATreeFeature {
	private static final BlockState leaves = AoABlocks.CREEP_LEAVES.get().defaultBlockState();
	private static final BlockState vines = AoABlocks.CREEP_VINES.get().defaultBlockState();

	public CreepTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		ArrayList<BlockPos> leafPositions = new ArrayList<BlockPos>();
		int trunkHeight = 6 + rand.nextInt(10);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.CREEP_LOG.get().defaultBlockState();
		int ringLocation = 1 + rand.nextInt(trunkHeight - 3);

		if (ringLocation < trunkHeight / 2)
			ringLocation = -1;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);

			if (i == ringLocation) {
				int ringWidth = 1 + rand.nextInt(2);

				for (int y = 0; y <= rand.nextInt(2); y++) {
					for (int x = -ringWidth; x <= ringWidth; x++) {
						for (int z = -ringWidth; z <= ringWidth; z++) {
							placeLeafBlock(reader, movablePos.offset(x, y, z), leafPositions);
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
						placeLeafBlock(reader, movablePos.offset(x, y, z), leafPositions);
					}
				}
			}
		}
		else {
			for (int y = 0; y > -rand.nextInt(3); y--) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						placeLeafBlock(reader, movablePos.offset(x, y, z), leafPositions);
					}
				}
			}

			if (rand.nextBoolean()) {
				for (int x = -2; x <= 2; x++) {
					for (int z = -2; z <= 2; z++) {
						if (x * x + z * z <= 4)
							placeLeafBlock(reader, movablePos.offset(x, 1, z), leafPositions);
					}
				}
			}
			else {
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						placeLeafBlock(reader, movablePos.offset(x, 1, z), leafPositions);
					}
				}
			}
		}

		populateVines(reader, rand, leafPositions);

		return true;
	}

	private void placeLeafBlock(WorldGenLevel reader, BlockPos leafPos, ArrayList<BlockPos> leafPositions) {
		placeBlock(reader, leafPos, leaves);
		leafPositions.add(leafPos);
	}

	private void populateVines(WorldGenLevel reader, Random rand, ArrayList<BlockPos> leafPositions) {
		for (BlockPos pos : leafPositions) {
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				BlockPos vinePos;

				if (rand.nextFloat() < 0.65f && reader.isEmptyBlock(vinePos = pos.relative(dir))) {
					BlockState state = ((VinesBlock)vines.getBlock()).getStateForPosition(reader, vinePos);
					int count = 0;

					placeBlock(reader, vinePos, state);

					while (count < 2 && rand.nextFloat() < 0.65f && reader.isEmptyBlock(vinePos = vinePos.below())) {
						state = ((VinesBlock)vines.getBlock()).getStateForPosition(reader, vinePos);

						placeBlock(reader, vinePos, state);

						count++;
					}
				}
			}
		}
	}
}
