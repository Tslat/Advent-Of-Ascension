package net.tslat.aoa3.content.world.genold.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.genold.feature.features.config.MiscStateAndVariablesConfig;

public class BlockPileFeature extends Feature<MiscStateAndVariablesConfig> {
	public BlockPileFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<MiscStateAndVariablesConfig> context) {
		BlockPos pos = context.origin();
		MiscStateAndVariablesConfig config = context.config();
		RandomSource rand = context.random();
		WorldGenLevel reader = context.level();
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
