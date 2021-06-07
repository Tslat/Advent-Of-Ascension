package net.tslat.aoa3.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AllXPlacement extends SimplePlacement<NoPlacementConfig> {
	public AllXPlacement(Codec<NoPlacementConfig> codec) {
		super(codec);
	}

	@Override
	protected Stream<BlockPos> place(Random random, NoPlacementConfig config, BlockPos pos) {
		return IntStream.range(0, 16).mapToObj(x -> new BlockPos(x + pos.getX(), pos.getY(), pos.getZ()));
	}
}
