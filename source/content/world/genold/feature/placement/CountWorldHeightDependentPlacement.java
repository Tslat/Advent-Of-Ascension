/*
package net.tslat.aoa3.content.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.tslat.aoa3.content.world.gen.feature.placement.config.CountDividedRangeConfig;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountWorldHeightDependentPlacement extends HeightmapPlacement<CountDividedRangeConfig> {
	public CountWorldHeightDependentPlacement(Codec<CountDividedRangeConfig> codec) {
		super(codec);
	}

	@Override
	protected Heightmap.Type type(CountDividedRangeConfig config) {
		return Heightmap.Type.WORLD_SURFACE_WG;
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, CountDividedRangeConfig config, BlockPos pos) {
		int amount = config.getValue(helper.getHeight(type(config), pos.getX(), pos.getZ()));

		if (amount == 0)
			return Stream.empty();

		return IntStream.range(0, amount).mapToObj(count -> pos);
	}
}
*/
