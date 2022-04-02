package net.tslat.aoa3.content.world.gen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.SaplingBlock;
import net.tslat.aoa3.content.world.gen.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public class TurquoiseHavenTreeFeature extends HavenTreeFeature {
	public TurquoiseHavenTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.TURQUOISE_HAVEN_LEAVES.get().defaultBlockState();
	}
}
