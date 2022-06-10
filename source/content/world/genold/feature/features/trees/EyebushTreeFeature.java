package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public class EyebushTreeFeature extends AoATreeFeature {
	public EyebushTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
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

	private void buildLeafRing(WorldGenLevel reader, BlockPos pos, int radius) {
		BlockState leaves = AoABlocks.VEIN_LEAVES.get().defaultBlockState();

		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				if (!((x == radius || x == -radius) && (z == radius || z == -radius)))
					placeBlock(reader, pos.offset(x, 0, z), leaves);
			}
		}
	}
}
