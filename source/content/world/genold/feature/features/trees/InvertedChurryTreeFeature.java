package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.function.Supplier;

public class InvertedChurryTreeFeature extends AoATreeFeature {
	public InvertedChurryTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean checkSafeHeight(WorldGenLevel reader, BlockPos pos, int maxHeight, int trunkWidth, boolean isWorldGen) {
		return pos.getY() <= 256 && pos.getY() - maxHeight >= 1;
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 25 + rand.nextInt(15);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		BlockPos.MutableBlockPos movablePos = new BlockPos.MutableBlockPos().set(pos.above());
		BlockState log = AoABlocks.CHURRY_LOG.get().defaultBlockState();
		BlockState lelyetianLeaves = AoABlocks.LELYETIAN_LEAVES.get().defaultBlockState();
		int leafGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.DOWN), log);
			leafGap++;

			if (i > 6 && leafGap > 6 && (leafGap > 11 || rand.nextInt(5) == 0)) {
				leafGap = 0;

				buildLeafBlob(reader, movablePos.immutable(), rand);
			}
		}

		placeBlock(reader, movablePos.move(Direction.DOWN), log);

		for (int x = -1; x <= 1; x++) {
			for (int y = 1; y <= 3; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, movablePos.offset(x, -y, z), lelyetianLeaves);
				}
			}
		}

		return true;
	}

	private void buildLeafBlob(WorldGenLevel reader, BlockPos pos, Random rand) {
		BlockState leaves = AoABlocks.CHURRY_LEAVES.get().defaultBlockState();

		placeBlock(reader, pos.north(), leaves);
		placeBlock(reader, pos.south(), leaves);
		placeBlock(reader, pos.east(), leaves);
		placeBlock(reader, pos.west(), leaves);

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (x != 0 || z != 0)
					placeBlock(reader, pos.offset(x, 1, z), leaves);
			}
		}

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y >= -3; y--) {
				for (int z = -2; z <= 2; z++) {
					if (!((x == 2 || x == -2) && (z == 2 || z == -2)))
						placeBlock(reader, pos.offset(x, -y, z), leaves);
				}
			}
		}

		pos = pos.above(3);

		placeBlock(reader, pos.north(3), leaves);
		placeBlock(reader, pos.south(3), leaves);
		placeBlock(reader, pos.east(3), leaves);
		placeBlock(reader, pos.west(3), leaves);

		if (rand.nextBoolean()) {
			placeBlock(reader, pos.north(4), leaves);
			placeBlock(reader, pos.south(4), leaves);
			placeBlock(reader, pos.east(4), leaves);
			placeBlock(reader, pos.west(4), leaves);
		}

		placeBlock(reader, pos.north(), leaves);
		placeBlock(reader, pos.south(), leaves);
		placeBlock(reader, pos.east(), leaves);
		placeBlock(reader, pos.west(), leaves);
	}
}
