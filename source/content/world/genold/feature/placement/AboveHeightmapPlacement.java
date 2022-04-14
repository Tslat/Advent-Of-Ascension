/*
package net.tslat.aoa3.content.world.gen.feature.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.tslat.aoa3.content.world.gen.feature.placement.config.IntRangeConfig;

import java.util.Random;
import java.util.stream.Stream;

public class AboveHeightmapPlacement extends PlacementModifier {
	public static final Codec<AboveHeightmapPlacement> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					Codec.INT.optionalFieldOf("min", 0).forGetter(config -> config.min),
					Codec.INT.optionalFieldOf("max", 0).forGetter(config -> config.max),
					Heightmap.Types.CODEC.optionalFieldOf("heightmap", Heightmap.Types.WORLD_SURFACE_WG).forGetter(config -> config.heightmap))
			.apply(builder, AboveHeightmapPlacement::new));
	public final int min;
	public final int max;
	public final Heightmap.Types heightmap;

	public AboveHeightmapPlacement(int min, int max, Heightmap.Types heightmap) {
		this.min = min;
		this.max = max;
		this.heightmap = heightmap;
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, Random rand, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = context.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);

		return y > 0 ? Stream.of(new BlockPos(x, Math.min(y + getValue(rand), context.getGenDepth()), z)) : Stream.empty();
	}

	@Override
	public PlacementModifierType<?> type() {
		return AoAWorldgenPlacements.ABOVE_HEIGHTMAP;
	}

	public int getValue(Random rand) {
		if (min == max)
			return max;

		return rand.nextInt(1 + max - min) + min;
	}
}
*/
