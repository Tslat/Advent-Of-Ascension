package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.object.block.functional.sapling.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.Random;
import java.util.function.Supplier;

public class EyebushTreeFeature extends AoATreeFeature {
	public EyebushTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		boolean doubleTrunk = rand.nextBoolean();

		if (!checkSafeHeight(reader, pos, (doubleTrunk ? 2 : 1) + 5, 1, isWorldGen))
			return false;

		if (!checkAndPrepSoil(reader, pos, 1, isWorldGen))
			return false;

		BlockState log = AoABlocks.EYEBALL_LOG.get().defaultBlockState();

		placeBlock(reader, pos, log);

		pos = pos.above();

		if (doubleTrunk) {
			placeBlock(reader, pos, log);

			pos = pos.above();
		}

		placeBlock(reader, pos.north(), log);
		placeBlock(reader, pos.south(), log);
		placeBlock(reader, pos.east(), log);
		placeBlock(reader, pos.west(), log);
		placeBlock(reader, pos.above(), log);
		placeBlock(reader, pos.above(2), log);

		buildLeafRing(reader, pos, 2);
		buildLeafRing(reader, pos.above(), 3);
		buildLeafRing(reader, pos.above(2), 2);
		buildLeafRing(reader, pos.above(3), 1);

		placeBlock(reader, pos.above(4), AoABlocks.VEIN_LEAVES.get().defaultBlockState());

		return true;
	}

	private void buildLeafRing(ISeedReader reader, BlockPos pos, int radius) {
		BlockState leaves = AoABlocks.VEIN_LEAVES.get().defaultBlockState();

		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				if (!((x == radius || x == -radius) && (z == radius || z == -radius)))
					placeBlock(reader, pos.offset(x, 0, z), leaves);
			}
		}
	}
}
