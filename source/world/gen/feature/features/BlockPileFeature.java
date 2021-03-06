package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.world.gen.feature.features.config.MiscStateAndVariablesConfig;

import java.util.Random;

public class BlockPileFeature extends Feature<MiscStateAndVariablesConfig> {
	public BlockPileFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MiscStateAndVariablesConfig config) {
		int piles = config.count.getValue(rand);

		for(int i = 0; i < piles; ++i) {
			int x = rand.nextInt(1 + config.x.getValue(rand));
			int y = rand.nextInt(1 + config.y.getValue(rand));
			int z = rand.nextInt(1 + config.z.getValue(rand));
			float radius = (float)(x + y + z) * 0.333F + 0.5F;

			for (BlockPos placementPos : BlockPos.betweenClosed(pos.offset(-x, -y, -z), pos.offset(x, y, z))) {
				if (placementPos.distSqr(pos) <= (double)(radius * radius))
					reader.setBlock(placementPos, config.stateProvider.getState(rand, placementPos), 4);
			}

			pos = pos.offset(-1 + rand.nextInt(config.x.getValue(rand)), -rand.nextInt(config.y.getValue(rand)), -1 + rand.nextInt(config.z.getValue(rand)));
		}

		return true;
	}
}
