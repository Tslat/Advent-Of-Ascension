package net.tslat.aoa3.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.tslat.aoa3.worldgen.feature.placement.config.UndergroundSurfaceConfig;

import java.util.Random;
import java.util.stream.Stream;

public class UndergroundSurfacePlacement extends Placement<UndergroundSurfaceConfig> {
	public UndergroundSurfacePlacement(Codec<UndergroundSurfaceConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, UndergroundSurfaceConfig config, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = config.getRandomValue(rand);
		BlockPos.Mutable checkPos = new BlockPos.Mutable(x, y, z);

		for (int i = 0; i < 5; i++) {
			if (isEmptyPosition(helper, checkPos))
				break;

			checkPos.set(x, config.getRandomValue(rand), z);
		}

		if (!isEmptyPosition(helper, checkPos))
			return Stream.empty();

		if (config.onCeiling)
			return Stream.of(findRoofPosition(helper, checkPos, config, x, z).immutable());

		return Stream.of(findFloorPosition(helper, checkPos, config, x, z).immutable());
	}

	private BlockPos findRoofPosition(WorldDecoratingHelper helper, BlockPos.Mutable startPos, UndergroundSurfaceConfig config, int x, int z) {
		int roofPos = helper.getGenDepth();

		if (!isEmptyPosition(helper, startPos)) {
			startPos.set(x, roofPos, z);

			while (startPos.getY() >= 0 && !isEmptyPosition(helper, startPos)) {
				startPos.move(Direction.DOWN);
			}

			if (startPos.getY() < 0)
				return new BlockPos(x, roofPos - 1, z);

			return startPos.immutable();
		}

		while (startPos.getY() < roofPos && isEmptyPosition(helper, startPos)) {
			startPos.move(Direction.UP);
		}

		if (config.onSurface)
			startPos.move(Direction.DOWN);

		return startPos.immutable();
	}

	private BlockPos findFloorPosition(WorldDecoratingHelper helper, BlockPos.Mutable startPos, UndergroundSurfaceConfig config, int x, int z) {
		int roofPos = helper.getGenDepth();

		if (!isEmptyPosition(helper, startPos)) {
			startPos.set(x, 0, z);

			while (startPos.getY() < roofPos && !isEmptyPosition(helper, startPos)) {
				startPos.move(Direction.UP);
			}

			if (startPos.getY() >= roofPos)
				return new BlockPos(x, 1, z);

			return startPos.immutable();
		}

		while (startPos.getY() > 0 && isEmptyPosition(helper, startPos)) {
			startPos.move(Direction.DOWN);
		}

		if (config.onSurface)
			startPos.move(Direction.UP);

		return startPos.immutable();
	}

	private boolean isEmptyPosition(WorldDecoratingHelper helper, BlockPos pos) {
		Material material = helper.getBlockState(pos).getMaterial();

		return material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}
}
