package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class NoContextBlockFeature extends Feature<BlockStateFeatureConfig> {
	public NoContextBlockFeature(Codec<BlockStateFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
		reader.setBlock(pos, config.state, 2);

		return false;
	}
}
