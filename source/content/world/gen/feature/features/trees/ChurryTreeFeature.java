package net.tslat.aoa3.content.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class ChurryTreeFeature extends AoATreeFeature {
	public ChurryTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		int trunkHeight = 25 + rand.nextInt(15);

		if (!checkSafeHeight(reader, pos, trunkHeight + 3, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockPos.Mutable movablePos = new BlockPos.Mutable().set(pos.below());
		BlockState log = AoABlocks.CHURRY_LOG.get().defaultBlockState();
		BlockState lelyetianLeaves = AoABlocks.LELYETIAN_LEAVES.get().defaultBlockState();
		int leafGap = 0;

		for (int i = 0; i < trunkHeight; i++) {
			placeBlock(reader, movablePos.move(Direction.UP), log);
			leafGap++;

			if (i > 6 && leafGap > 6 && (leafGap > 11 || rand.nextInt(5) == 0)) {
				leafGap = 0;

				buildLeafBlob(reader, movablePos.immutable(), rand);
			}
		}

		placeBlock(reader, movablePos.move(Direction.UP), log);

		for (int x = -1; x <= 1; x++) {
			for (int y = 1; y <= 3; y++) {
				for (int z = -1; z <= 1; z++) {
					placeBlock(reader, movablePos.offset(x, y, z), lelyetianLeaves);
				}
			}
		}

		return true;
	}

	private void buildLeafBlob(ISeedReader reader, BlockPos pos, Random rand) {
		BlockState leaves = AoABlocks.CHURRY_LEAVES.get().defaultBlockState();

		placeBlock(reader, pos.north(), leaves);
		placeBlock(reader, pos.south(), leaves);
		placeBlock(reader, pos.east(), leaves);
		placeBlock(reader, pos.west(), leaves);

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (x != 0 || z != 0)
					placeBlock(reader, pos.offset(x, -1, z), leaves);
			}
		}

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y >= -3; y--) {
				for (int z = -2; z <= 2; z++) {
					if (!((x == 2 || x == -2) && (z == 2 || z == -2)))
						placeBlock(reader, pos.offset(x, y, z), leaves);
				}
			}
		}

		pos = pos.below(3);

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
