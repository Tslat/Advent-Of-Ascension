package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.content.world.gen.feature.features.config.MiscStateAndVariablesConfig;

import java.util.Random;

public class BendyColumnFeature extends Feature<MiscStateAndVariablesConfig> {
	public BendyColumnFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MiscStateAndVariablesConfig config) {
		boolean placed = false;
		BlockPos.Mutable placementPos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		BlockStateProvider blockProvider = config.stateProvider;
		int height = config.count.getValue(rand);
		int count = 0;
		Direction moveDir = config.inverted ? Direction.DOWN : Direction.UP;
		Direction lastDir = null;

		while (count < height) {
			for (int i = 0; i < height - count && i < 1 + rand.nextInt(height); i++) {
				if (config.inverted ? placementPos.getY() < 0 : placementPos.getY() >= reader.dimensionType().logicalHeight())
					return placed;

				if (reader.isEmptyBlock(placementPos)) {
					reader.setBlock(placementPos, blockProvider.getState(rand, placementPos), 2);
					placementPos.move(moveDir);
					placed = true;
					count++;
				}
				else {
					return placed;
				}
			}

			Direction turn = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

			if (turn != lastDir) {
				placementPos.move(turn);
				placementPos.move(moveDir.getOpposite());
				lastDir = turn.getOpposite();
			}
		}

		return placed;
	}
}
