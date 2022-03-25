package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.content.world.gen.feature.features.config.CappedColumnConfig;

import java.util.Random;

public class CappedColumnFeature extends Feature<CappedColumnConfig> {
	public CappedColumnFeature(Codec<CappedColumnConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, CappedColumnConfig config) {
		boolean placed = false;
		BlockPos.Mutable placementPos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		int stemHeight = rand.nextInt(1 + Math.max(0, config.maxStemSize - config.minStemSize));

		if (!config.uniformHeightDistribution)
			stemHeight = 1 + rand.nextInt(1 + stemHeight);

		stemHeight += config.minStemSize;

		for (int i = 0; i < stemHeight; i++) {
			boolean capping = false;
			BlockState state;

			if (i == stemHeight - 1 || !reader.isEmptyBlock(config.upsideDown ? placementPos.below() : placementPos.above())) {
				state = config.capBlock.getState(rand, placementPos);
				capping = true;
			}
			else {
				state = config.stemBlock.getState(rand, placementPos);
			}

			if (reader.isEmptyBlock(placementPos) && state.canSurvive(reader, placementPos)) {
				reader.setBlock(placementPos, state, 2);
				placementPos.move(config.upsideDown ? Direction.DOWN : Direction.UP);

				placed = true;
			}
			else {
				break;
			}

			if (capping)
				break;
		}

		return placed;
	}
}
