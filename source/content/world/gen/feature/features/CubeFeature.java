package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.content.world.gen.feature.features.config.MiscStateAndVariablesConfig;

import java.util.Random;

public class CubeFeature extends Feature<MiscStateAndVariablesConfig> {
	public CubeFeature(Codec<MiscStateAndVariablesConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MiscStateAndVariablesConfig config) {
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
