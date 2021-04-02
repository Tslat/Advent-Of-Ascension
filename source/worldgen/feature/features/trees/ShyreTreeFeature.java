package net.tslat.aoa3.worldgen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public abstract class ShyreTreeFeature extends AoAVariableLeafTreeFeature {
	public ShyreTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen) {
		int trunkHeight = 5 + rand.nextInt(5);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.SHYRE_LOG.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		switch (rand.nextInt(4)) {
			case 0:
				for (int i = 0; i >= -2 - rand.nextInt(2); i--) {
					placeBlock(reader, movablePos.offset(-1, i, 0), leafBlock);
					placeBlock(reader, movablePos.offset(1, i, 0), leafBlock);
					placeBlock(reader, movablePos.offset(0, i, 1), leafBlock);
					placeBlock(reader, movablePos.offset(0, i, -1), leafBlock);
				}

				placeBlock(reader, movablePos.offset(0, 1, 0), leafBlock);
				break;
			case 1:
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
				break;
			case 2:
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
				break;
			case 3:
				for (int i = 0; i <= 3; i++) {
					if (i != 1) {
						placeBlock(reader, movablePos.offset(-1, i, 0), leafBlock);
						placeBlock(reader, movablePos.offset(1, i, 0), leafBlock);
						placeBlock(reader, movablePos.offset(0, i, 1), leafBlock);
						placeBlock(reader, movablePos.offset(0, i, -1), leafBlock);
					}
				}

				placeBlock(reader, movablePos.offset(0, 1, 0), leafBlock);
				break;
		}

		return true;
	}
}
