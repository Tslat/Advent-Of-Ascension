package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.world.gen.feature.features.config.ColumnConfig;

import java.util.Random;

public class ColumnFeature extends Feature<ColumnConfig> {
	public ColumnFeature(Codec<ColumnConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ColumnConfig config) {
		boolean placed = false;
		BlockPos.Mutable placementPos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		int stemHeight = rand.nextInt(1 + Math.max(0, config.maxSize - config.minSize));

		if (!config.uniformHeightDistribution)
			stemHeight = 1 + rand.nextInt(1 + stemHeight);

		stemHeight += config.minSize;

		for (int i = 0; i < stemHeight; i++) {
			BlockState block = config.block.getState(rand, placementPos);

			if (reader.isEmptyBlock(placementPos) && block.canSurvive(reader, placementPos)) {
				reader.setBlock(placementPos, block, 2);
				placementPos.move(Direction.UP);

				placed = true;
			}
			else {
				break;
			}
		}

		return placed;
	}
}
