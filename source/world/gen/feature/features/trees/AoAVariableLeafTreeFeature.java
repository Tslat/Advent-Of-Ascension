package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.object.block.functional.plant.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public abstract class AoAVariableLeafTreeFeature extends AoATreeFeature {
	public AoAVariableLeafTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> sapling) {
		super(codec, sapling);
	}

	@Override
	protected boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, boolean isWorldGen) {
		return generateTree(reader, rand, pos, getLeaf(), isWorldGen);
	}

	protected abstract boolean generateTree(ISeedReader reader, Random rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen);

	protected abstract BlockState getLeaf();
}
