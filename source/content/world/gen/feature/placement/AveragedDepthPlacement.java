package net.tslat.aoa3.content.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.stream.Stream;

public class AveragedDepthPlacement extends SimplePlacement<DepthAverageConfig> {
	public AveragedDepthPlacement(Codec<DepthAverageConfig> codec) {
		super(codec);
	}

	@Override
	protected Stream<BlockPos> place(Random random, DepthAverageConfig config, BlockPos pos) {
		int range = config.spread;
		int k = pos.getX();
		int l = pos.getZ();
		int i1 = random.nextInt(range + 1) + random.nextInt(range + 1) - range + config.baseline;
		return Stream.of(new BlockPos(k, i1, l));
	}
}
