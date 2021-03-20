package net.tslat.aoa3.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
import net.tslat.aoa3.worldgen.feature.placement.config.CountDividedRangeConfig;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountWorldHeightDependentPlacement extends HeightmapBasedPlacement<CountDividedRangeConfig> {
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
