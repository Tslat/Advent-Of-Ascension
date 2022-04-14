package net.tslat.aoa3.content.world.genold.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.genold.feature.features.config.LiquidDrainConfig;

import java.util.Random;

public class LiquidDrainFeature extends Feature<LiquidDrainConfig> {
	public LiquidDrainFeature(Codec<LiquidDrainConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<LiquidDrainConfig> context) {
		BlockPos pos = context.origin();
		LiquidDrainConfig config = context.config();
		Random rand = context.random();
		WorldGenLevel reader = context.level();
		BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos().set(pos);

		if (config.stopBlocks.contains(reader.getBlockState(placementPos)))
			return false;

		BlockState fillState = config.fill ? config.fluid.createLegacyBlock() : Blocks.AIR.defaultBlockState();

		reader.setBlock(placementPos, config.fluid.createLegacyBlock(), 2);
		placementPos.move(config.inverseDirection ? Direction.UP : Direction.DOWN);

		while ((config.inverseDirection ? placementPos.getY() < reader.getMaxBuildHeight() : placementPos.getY() >= 0) && !config.stopBlocks.contains(reader.getBlockState(placementPos))) {
			reader.setBlock(placementPos, fillState, 2);
			placementPos.move(config.inverseDirection ? Direction.UP : Direction.DOWN);
		}

		if (config.fill) {
			reader.scheduleTick(placementPos.move(config.inverseDirection ? Direction.DOWN : Direction.UP), config.fluid.getType(), 0);
		}
		else {
			reader.scheduleTick(pos, config.fluid.getType(), 0);
		}

		return true;
	}
}
