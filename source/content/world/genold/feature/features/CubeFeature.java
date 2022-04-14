package net.tslat.aoa3.content.world.genold.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.genold.feature.features.config.MiscStateAndVariablesConfig;

import java.util.Random;

public class CubeFeature extends Feature<MiscStateAndVariablesConfig> {
	public CubeFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<MiscStateAndVariablesConfig> context) {
		BlockPos pos = context.origin();
		MiscStateAndVariablesConfig config = context.config();
		Random rand = context.random();
		WorldGenLevel reader = context.level();
		BlockState state = config.stateProvider.getState(rand, pos);
		int sizeX = config.x.getValue(rand);
		int sizeY = config.y.getValue(rand);
		int sizeZ = config.z.getValue(rand);

		for (BlockPos placementPos : BlockPos.betweenClosed(pos, pos.offset(sizeX - 1, sizeY - 1, sizeZ - 1))) {
			reader.setBlock(placementPos, state, 2);
		}

		return true;
	}
}
