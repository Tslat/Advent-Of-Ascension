package net.tslat.aoa3.worldgen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.worldgen.feature.features.config.MiscStateAndVariablesConfig;

import java.util.BitSet;
import java.util.Random;

public class StalagmiteFeature extends Feature<MiscStateAndVariablesConfig> {
	public StalagmiteFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MiscStateAndVariablesConfig config) {
		int height = config.count.getValue(rand);
		int width = config.x.getValue(rand);

		BlockState state = config.stateProvider.getState(rand, pos);
		BitSet placementPositions = new BitSet(width * width);

		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				for (int z = 0; z < width; z++) {
					if (rand.nextFloat() < width / (((float)height - (i + 1)) * width))
						placementPositions.set(x << (int)(Math.log(width) / Math.log(2) + 1) | z);

					if (!placementPositions.get(x << (int)(Math.log(width) / Math.log(2) + 1) | z)) {
						BlockPos placePos = pos.offset(x, i, z);

						reader.setBlock(placePos, state, 2);
					}
				}
			}
		}

		return true;
	}
}
