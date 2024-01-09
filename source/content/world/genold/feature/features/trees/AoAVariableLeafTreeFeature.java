package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.content.block.functional.plant.AoASaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public abstract class AoAVariableLeafTreeFeature extends AoATreeFeature {
	public AoAVariableLeafTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<? extends AoASaplingBlock> sapling) {
		super(codec, sapling);
	}

	@Override
	protected boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, boolean isWorldGen) {
		return generateTree(reader, rand, pos, getLeaf(), isWorldGen);
	}

	protected abstract boolean generateTree(WorldGenLevel reader, RandomSource rand, BlockPos pos, BlockState leafBlock, boolean isWorldGen);

	protected abstract BlockState getLeaf();
}
