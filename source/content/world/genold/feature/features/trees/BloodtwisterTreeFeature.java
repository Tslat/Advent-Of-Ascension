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

public class BloodtwisterTreeFeature extends AoATreeFeature {
	public BloodtwisterTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<AoASaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 5 + rand.nextInt(5);
		int weaveHeight = 8 + rand.nextInt(8);

		if (!checkSafeHeight(reader, pos, trunkHeight + weaveHeight, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.BLOOD_LOG.log().defaultBlockState();
		BlockState leaves = AoABlocks.BLOOD_LEAVES.get().defaultBlockState();
		BlockState barkLog = AoABlocks.BLOOD_LOG.bark().defaultBlockState();

		placeBlock(reader, pos.above(1 + rand.nextInt(3)), AoABlocks.EYEBALL_LOG.log().defaultBlockState());

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		placeBlock(reader, movablePos.offset(1, 0, 0), barkLog);
		placeBlock(reader, movablePos.offset(0, 0, 1), barkLog);
		placeBlock(reader, movablePos, barkLog);
		placeBlock(reader, movablePos.offset(-1, 0, 0), barkLog);
		placeBlock(reader, movablePos.offset(0, 0, -1), barkLog);

		placeBlock(reader, movablePos.offset(2, 0, 0), leaves);
		placeBlock(reader, movablePos.offset(0, 0, 2), leaves);
		placeBlock(reader, movablePos.offset(-2, 0, 0), leaves);
		placeBlock(reader, movablePos.offset(0, 0, -2), leaves);
		placeBlock(reader, movablePos.offset(3, 0, 0), leaves);
		placeBlock(reader, movablePos.offset(0, 0, 3), leaves);
		placeBlock(reader, movablePos.offset(-3, 0, 0), leaves);
		placeBlock(reader, movablePos.offset(0, 0, -3), leaves);

		if (rand.nextBoolean()) {
			Direction[] directions = new Direction[] {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.immutable();

			movablePos.move(Direction.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(reader, movablePos.move(directions[direction]), leaves);
				placeBlock(reader, movablePos.move(Direction.UP), leaves);

				if (i == weaveHeight && curBaseDirection < 3) {
					movablePos.set(basePos).move(directions[curBaseDirection], 3);
					curBaseDirection++;
					direction = curBaseDirection;
					i = 1;
				}

				if (i % turnMod == 0) {
					direction++;

					if (direction > 3)
						direction = 0;
				}
			}
		}
		else {
			Direction[] directions = new Direction[] {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};

			int turnMod = rand.nextBoolean() ? 3 : 4;
			int direction = 0;
			int curBaseDirection = 0;
			BlockPos basePos = movablePos.immutable();

			movablePos.move(Direction.WEST, 3);

			for (int i = 1; i <= weaveHeight; i++) {
				placeBlock(reader, movablePos.move(directions[direction]), leaves);
				placeBlock(reader, movablePos.move(Direction.UP), leaves);

				if (i == weaveHeight && curBaseDirection < 3) {
					movablePos.set(basePos).move(directions[curBaseDirection], 3);
					curBaseDirection++;
					direction = curBaseDirection;
					i = 1;
				}

				if (i % turnMod == 0) {
					direction++;

					if (direction > 3)
						direction = 0;
				}
			}
		}

		return true;
	}
}
