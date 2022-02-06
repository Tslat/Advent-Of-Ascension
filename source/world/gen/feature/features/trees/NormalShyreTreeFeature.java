package net.tslat.aoa3.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.object.block.functional.plant.SaplingBlock;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.function.Supplier;

public class NormalShyreTreeFeature extends ShyreTreeFeature {
	public NormalShyreTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.SHYRE_LEAVES.get().defaultBlockState();
	}
}
