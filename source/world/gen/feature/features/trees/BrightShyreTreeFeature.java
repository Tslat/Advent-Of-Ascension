package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.function.Supplier;

public class BrightShyreTreeFeature extends ShyreTreeFeature {
	public BrightShyreTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.BRIGHT_SHYRE_LEAVES.get().defaultBlockState();
	}
}
