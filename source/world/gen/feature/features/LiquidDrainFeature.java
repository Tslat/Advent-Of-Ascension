package net.tslat.aoa3.world.gen.feature.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.tslat.aoa3.world.gen.feature.features.config.LiquidDrainConfig;

import java.util.Random;

public class LiquidDrainFeature extends Feature<LiquidDrainConfig> {
	public LiquidDrainFeature(Codec<LiquidDrainConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, LiquidDrainConfig config) {
		BlockPos.Mutable placementPos = new BlockPos.Mutable().set(pos);

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
			reader.getLiquidTicks().scheduleTick(placementPos.move(config.inverseDirection ? Direction.DOWN : Direction.UP), config.fluid.getType(), 0);
		}
		else {
			reader.getLiquidTicks().scheduleTick(pos, config.fluid.getType(), 0);
		}

		return true;
	}
}
