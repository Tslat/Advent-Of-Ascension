package net.tslat.aoa3.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.tslat.aoa3.world.gen.feature.placement.config.BlockStatePlacementConfig;

import java.util.Random;
import java.util.stream.Stream;

public class OnlyOnPlacement extends Placement<BlockStatePlacementConfig> {
	public OnlyOnPlacement(Codec<BlockStatePlacementConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, BlockStatePlacementConfig config, BlockPos pos) {
		return helper.getBlockState(pos.below()) == config.block ? Stream.of(pos) : Stream.empty();
	}
}
