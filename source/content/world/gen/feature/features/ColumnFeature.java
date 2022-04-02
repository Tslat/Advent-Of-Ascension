package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.gen.feature.features.config.ColumnConfig;

import java.util.Random;

public class ColumnFeature extends Feature<ColumnConfig> {
	public ColumnFeature(Codec<ColumnConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<ColumnConfig> context) {
		Random rand = context.random();
		WorldGenLevel reader = context.level();
		ColumnConfig config = context.config();
		BlockPos pos = context.origin();
		boolean placed = false;
		BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
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
