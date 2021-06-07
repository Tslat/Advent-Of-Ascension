package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.world.gen.feature.features.config.StructureFeatureConfig;

import java.util.Random;

public class StructurePieceGenFeature extends Feature<StructureFeatureConfig> {
	public StructurePieceGenFeature(Codec<StructureFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, StructureFeatureConfig config) {
		if (!config.requireGround || !reader.getBlockState(pos.below()).getMaterial().isReplaceable())
			config.getTemplate(rand).placeInWorld(reader, pos, pos, config.getPlacementSettings(rand), rand, 2);

		return true;
	}
}
