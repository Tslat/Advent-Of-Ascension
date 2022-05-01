package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.function.Supplier;

public class DawnwoodTreeFeature extends AoATreeFeature {
	public DawnwoodTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 15 + rand.nextInt(10);

		if (!checkSafeHeight(reader, pos, trunkHeight + 2, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.below());
		BlockState log = AoABlocks.DAWN_LOG.get().defaultBlockState();
		BlockState leaves = AoABlocks.DAWN_LEAVES.get().defaultBlockState();

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);

			if (rand.nextInt(4) == 0)
				placeBlock(reader, movablePos.north(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(reader, movablePos.south(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(reader, movablePos.east(), leaves);

			if (rand.nextInt(4) == 0)
				placeBlock(reader, movablePos.west(), leaves);
		}

		if (rand.nextInt(3) == 0) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, movablePos.offset(x, -1, z), leaves);
					placeBlock(reader, movablePos.offset(x, 1, z), leaves);
				}
			}

			int leafWidth = 2 + rand.nextInt(2);

			for (int x = -leafWidth; x <= leafWidth; x++) {
				for (int z = -leafWidth; z <= leafWidth; z++) {
					placeBlock(reader, movablePos.offset(x, 0, z), leaves);
				}
			}

			placeBlock(reader, movablePos.offset(0, 2, 0), leaves);
			placeBlock(reader, movablePos.offset(1, -2, 0), leaves);
			placeBlock(reader, movablePos.offset(-1, -2, 0), leaves);
			placeBlock(reader, movablePos.offset(0, -2, 1), leaves);
			placeBlock(reader, movablePos.offset(0, -2, -1), leaves);
		}
		else if (rand.nextBoolean()) {
			placeBlock(reader, movablePos.move(Direction.UP), leaves);
		}

		return true;
	}
}
