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

public class ShadowTreeFeature extends AoATreeFeature {
	public ShadowTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<AoASaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 8 + rand.nextInt(6);

		if (!checkSafeHeight(reader, pos, trunkHeight + 1, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.SHADOW_LOG.log().defaultBlockState();
		BlockState barkLog = AoABlocks.SHADOW_LOG.bark().defaultBlockState();
		BlockState leaves = AoABlocks.SHADOW_LEAVES.get().defaultBlockState();
		int branchWidth = 1 + rand.nextInt(4);
		boolean additionalLeaves = rand.nextBoolean();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
		}

		placeBlock(reader, movablePos.move(Direction.UP), barkLog);

		for (int i = 1; i <= branchWidth; i++) {
			placeBlock(reader, movablePos.north(i), barkLog);
			placeBlock(reader, movablePos.south(i), barkLog);
			placeBlock(reader, movablePos.east(i), barkLog);
			placeBlock(reader, movablePos.west(i), barkLog);
		}

		for (int i = branchWidth + 1; i <= branchWidth + 3; i++) {
			placeBlock(reader, movablePos.offset(i, 0, 0), leaves);
			placeBlock(reader, movablePos.offset(i, -2, 1), leaves);
			placeBlock(reader, movablePos.offset(i, -2, -1), leaves);
			placeBlock(reader, movablePos.offset(-i, 0, 0), leaves);
			placeBlock(reader, movablePos.offset(-i, -2, 1), leaves);
			placeBlock(reader, movablePos.offset(-i, -2, -1), leaves);
			placeBlock(reader, movablePos.offset(0, 0, i), leaves);
			placeBlock(reader, movablePos.offset(1, -2, i), leaves);
			placeBlock(reader, movablePos.offset(-1, -2, i), leaves);
			placeBlock(reader, movablePos.offset(0, 0, -i), leaves);
			placeBlock(reader, movablePos.offset(1, -2, -i), leaves);
			placeBlock(reader, movablePos.offset(-1, -2, -i), leaves);

			if (i == branchWidth + 3) {
				placeBlock(reader, movablePos.offset(i, -1, 0), leaves);
				placeBlock(reader, movablePos.offset(-i, -1, 0), leaves);
				placeBlock(reader, movablePos.offset(0, -1, i), leaves);
				placeBlock(reader, movablePos.offset(0, -1, -i), leaves);

				placeBlock(reader, movablePos.offset(i, -3, 1), leaves);
				placeBlock(reader, movablePos.offset(i, -3, -1), leaves);
				placeBlock(reader, movablePos.offset(-i, -3, 1), leaves);
				placeBlock(reader, movablePos.offset(-i, -3, -1), leaves);
				placeBlock(reader, movablePos.offset(1, -3, i), leaves);
				placeBlock(reader, movablePos.offset(-1, -3, i), leaves);
				placeBlock(reader, movablePos.offset(1, -3, -i), leaves);
				placeBlock(reader, movablePos.offset(-1, -3, -i), leaves);
				placeBlock(reader, movablePos.offset(i, -4, 1), leaves);
				placeBlock(reader, movablePos.offset(i, -4, -1), leaves);
				placeBlock(reader, movablePos.offset(-i, -4, 1), leaves);
				placeBlock(reader, movablePos.offset(-i, -4, -1), leaves);
				placeBlock(reader, movablePos.offset(1, -4, i), leaves);
				placeBlock(reader, movablePos.offset(-1, -4, i), leaves);
				placeBlock(reader, movablePos.offset(1, -4, -i), leaves);
				placeBlock(reader, movablePos.offset(-1, -4, -i), leaves);
			}
			else {
				placeBlock(reader, movablePos.offset(i, -1, 0), barkLog);
				placeBlock(reader, movablePos.offset(-i, -1, 0), barkLog);
				placeBlock(reader, movablePos.offset(0, -1, i), barkLog);
				placeBlock(reader, movablePos.offset(0, -1, -i), barkLog);

				if (i == branchWidth + 1) {
					placeBlock(reader, movablePos.offset(i, -3, 1), leaves);
					placeBlock(reader, movablePos.offset(i, -3, -1), leaves);
					placeBlock(reader, movablePos.offset(-i, -3, 1), leaves);
					placeBlock(reader, movablePos.offset(-i, -3, -1), leaves);
					placeBlock(reader, movablePos.offset(1, -3, i), leaves);
					placeBlock(reader, movablePos.offset(-1, -3, i), leaves);
					placeBlock(reader, movablePos.offset(1, -3, -i), leaves);
					placeBlock(reader, movablePos.offset(-1, -3, -i), leaves);
					placeBlock(reader, movablePos.offset(i, -4, 1), leaves);
					placeBlock(reader, movablePos.offset(i, -4, -1), leaves);
					placeBlock(reader, movablePos.offset(-i, -4, 1), leaves);
					placeBlock(reader, movablePos.offset(-i, -4, -1), leaves);
					placeBlock(reader, movablePos.offset(1, -4, i), leaves);
					placeBlock(reader, movablePos.offset(-1, -4, i), leaves);
					placeBlock(reader, movablePos.offset(1, -4, -i), leaves);
					placeBlock(reader, movablePos.offset(-1, -4, -i), leaves);
				}

				if (additionalLeaves) {
					placeBlock(reader, movablePos.offset(i, -1, 1), leaves);
					placeBlock(reader, movablePos.offset(i, -1, -1), leaves);
					placeBlock(reader, movablePos.offset(-i, -1, 1), leaves);
					placeBlock(reader, movablePos.offset(-i, -1, -1), leaves);
					placeBlock(reader, movablePos.offset(1, -1, i), leaves);
					placeBlock(reader, movablePos.offset(-1, -1, i), leaves);
					placeBlock(reader, movablePos.offset(1, -1, -i), leaves);
					placeBlock(reader, movablePos.offset(-1, -1, -i), leaves);
				}
			}
		}



		return true;
	}
}
