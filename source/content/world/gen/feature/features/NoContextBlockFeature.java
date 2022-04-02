package net.tslat.aoa3.content.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.gen.feature.placement.config.BlockStatePlacementConfig;

public class NoContextBlockFeature extends Feature<BlockStatePlacementConfig> {
	public NoContextBlockFeature(Codec<BlockStatePlacementConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStatePlacementConfig> context) {
		context.level().setBlock(context.origin(), context.config().block, Block.UPDATE_CLIENTS);

		return true;
	}
}
