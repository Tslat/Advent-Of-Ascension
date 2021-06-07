package net.tslat.aoa3.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
import net.tslat.aoa3.world.gen.feature.placement.config.IntRangeConfig;

import java.util.Random;
import java.util.stream.Stream;

public class AboveHeightmapPlacement extends HeightmapBasedPlacement<IntRangeConfig> {
	public AboveHeightmapPlacement(Codec<IntRangeConfig> codec) {
		super(codec);
	}

	@Override
	protected Heightmap.Type type(IntRangeConfig config) {
		return Heightmap.Type.WORLD_SURFACE_WG;
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, IntRangeConfig config, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = helper.getHeight(type(config), x, z);

		return y > 0 ? Stream.of(new BlockPos(x, Math.min(y + config.getValue(rand), helper.getGenDepth()), z)) : Stream.empty();
	}
}
