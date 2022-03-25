package net.tslat.aoa3.content.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
import net.tslat.aoa3.content.world.gen.feature.placement.config.IntRangeConfig;

import java.util.Random;
import java.util.stream.Stream;

public class UpToSurfacePlacement extends HeightmapBasedPlacement<IntRangeConfig> {
	public UpToSurfacePlacement(Codec<IntRangeConfig> codec) {
		super(codec);
	}

	@Override
	protected Heightmap.Type type(IntRangeConfig config) {
		return Heightmap.Type.MOTION_BLOCKING;
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, IntRangeConfig config, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = rand.nextInt(1 + helper.getHeight(type(config), x, z));

		if (y < Math.max(0, config.min) || y > config.max)
			return Stream.empty();

		return Stream.of(new BlockPos(x, y, z));
	}
}
