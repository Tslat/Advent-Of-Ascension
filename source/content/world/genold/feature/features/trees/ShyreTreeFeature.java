package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.AoASaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public abstract class ShyreTreeFeature extends AoAVariableLeafTreeFeature {
	public ShyreTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<? extends AoASaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 5 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.SHYRE_LOG.log().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		switch (rand.nextInt(4)) {
			case 0 -> {
				for (int i = 0; i >= -2 - rand.nextInt(2); i--) {
					placeBlock(reader, movablePos.offset(-1, i, 0), leafBlock);
					placeBlock(reader, movablePos.offset(1, i, 0), leafBlock);
					placeBlock(reader, movablePos.offset(0, i, 1), leafBlock);
					placeBlock(reader, movablePos.offset(0, i, -1), leafBlock);
				}
				placeBlock(reader, movablePos.offset(0, 1, 0), leafBlock);
			}
			case 1 -> {
				for (int y = 0; y >= -1 - rand.nextInt(2); y--) {
					for (int x = -1; x <= 1; x++) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(reader, movablePos.offset(x, y, z), leafBlock);
						}
					}
				}
				placeBlock(reader, movablePos.offset(0, 1, 0), leafBlock);
				placeBlock(reader, movablePos.offset(1, 1, 0), leafBlock);
				placeBlock(reader, movablePos.offset(0, 1, 1), leafBlock);
				placeBlock(reader, movablePos.offset(-1, 1, 0), leafBlock);
				placeBlock(reader, movablePos.offset(0, 1, -1), leafBlock);
			}
			case 2 -> {
				for (int x = -1; x <= 1; x++) {
					for (int y = 1; y <= 3; y += 2) {
						for (int z = -1; z <= 1; z++) {
							placeBlock(reader, movablePos.offset(x, y, z), leafBlock);
						}
					}
				}
				placeBlock(reader, movablePos.offset(-1, 2, -1), leafBlock);
				placeBlock(reader, movablePos.offset(1, 2, 1), leafBlock);
				placeBlock(reader, movablePos.offset(-1, 2, 1), leafBlock);
				placeBlock(reader, movablePos.offset(1, 2, -1), leafBlock);
			}
			case 3 -> {
				for (int i = 0; i <= 3; i++) {
					if (i != 1) {
						placeBlock(reader, movablePos.offset(-1, i, 0), leafBlock);
						placeBlock(reader, movablePos.offset(1, i, 0), leafBlock);
						placeBlock(reader, movablePos.offset(0, i, 1), leafBlock);
						placeBlock(reader, movablePos.offset(0, i, -1), leafBlock);
					}
				}
				placeBlock(reader, movablePos.offset(0, 1, 0), leafBlock);
			}
		}

		return true;
	}
}
