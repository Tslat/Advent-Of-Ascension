package net.tslat.aoa3.content.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.tslat.aoa3.common.registration.worldgen.AoAPlacementModifiers;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class ChunkHighPoint extends PlacementModifier {
	public static final Codec<ChunkHighPoint> CODEC = Codec.unit(ChunkHighPoint::new);

	private ChunkHighPoint() {}

	public static ChunkHighPoint instance() {
		return new ChunkHighPoint();
	}

	@Override
	public PlacementModifierType<ChunkHighPoint> type() {
		return AoAPlacementModifiers.CHUNK_HIGH_POINT.get();
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
		final WorldGenLevel level = context.getLevel();
		final int minX = pos.getX() - 12;
		final int minZ = pos.getZ() - 12;
		final int maxX = pos.getX() + 12;
		final int maxZ = pos.getZ() + 12;
		final AtomicReference<BlockPos> highestRingPos = new AtomicReference<>(new BlockPos(pos.getX(), Integer.MIN_VALUE, pos.getZ()));

		return BlockPos.betweenClosedStream(new BlockPos(minX, pos.getY(), minZ), new BlockPos(maxX, pos.getY(), maxZ)).map(pos1 -> {
			pos1 = pos1.immutable();

			while ((pos1 = pos1.above()).getY() < level.getMaxBuildHeight() && !level.getBlockState(pos1).isAir()) {
				;
			}

			if (pos1.getY() > highestRingPos.get().getY() && (pos1.getX() == minX || pos1.getX() == maxX || pos1.getZ() == minZ || pos1.getZ() == maxZ))
				highestRingPos.set(pos1.below());

			return pos1.below();
		})
				.max(Comparator.comparingInt(BlockPos::getY))
				.filter(pos1 -> pos1.getY() > highestRingPos.get().getY())
				.stream()
				.map(BlockPos::below);
	}
}
