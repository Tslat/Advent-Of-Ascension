package net.tslat.aoa3.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.tslat.aoa3.world.gen.feature.placement.config.IntRangeConfig;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NTimesPlacement extends Placement<IntRangeConfig> {
	public NTimesPlacement(Codec<IntRangeConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, IntRangeConfig config, BlockPos pos) {
		int amount = config.getValue(rand);

		return IntStream.range(0, amount).mapToObj(count -> pos);
	}
}
