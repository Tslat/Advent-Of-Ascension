package net.tslat.aoa3.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.tslat.aoa3.world.gen.feature.placement.config.OffsetPlacementConfig;

import java.util.Random;
import java.util.stream.Stream;

public class OffsetPlacement extends Placement<OffsetPlacementConfig> {
	public OffsetPlacement(Codec<OffsetPlacementConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, OffsetPlacementConfig config, BlockPos pos) {
		return Stream.of(pos.relative(config.direction, config.amount));
	}
}
