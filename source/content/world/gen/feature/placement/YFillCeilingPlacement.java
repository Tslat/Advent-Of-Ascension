package net.tslat.aoa3.content.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class YFillCeilingPlacement extends Placement<TopSolidRangeConfig> {
	public YFillCeilingPlacement(Codec<TopSolidRangeConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, TopSolidRangeConfig config, BlockPos pos) {
		int worldHeight = helper.getGenDepth();

		return IntStream.range(worldHeight - config.bottomOffset - config.topOffset, worldHeight - config.topOffset).mapToObj(y -> new BlockPos(pos.getX(), y, pos.getZ()));
	}
}
