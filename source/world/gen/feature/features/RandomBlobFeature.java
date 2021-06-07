package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.world.gen.feature.features.config.MiscStateAndVariablesConfig;

import java.util.Random;
import java.util.function.Predicate;

public class RandomBlobFeature extends Feature<MiscStateAndVariablesConfig> {
	public RandomBlobFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MiscStateAndVariablesConfig config) {
		BlockState state = config.stateProvider.getState(rand, pos);
		int sizeX = config.x.getValue(rand);
		int sizeY = config.y.getValue(rand);
		int sizeZ = config.z.getValue(rand);
		float chance = 1 / (float)config.count.getValue(rand);
		Predicate<Float> testPredicate = config.inverted ? val -> rand.nextFloat() > val : val -> rand.nextFloat() < val;

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y <= sizeY; y++) {
				for (int z = 0; z <= sizeZ; z++) {
					if (testPredicate.test(chance))
						reader.setBlock(pos.offset(x, y, z), state, 2);
				}
			}
		}

		return true;
	}
}
