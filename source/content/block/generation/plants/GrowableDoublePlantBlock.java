package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.function.Supplier;

public class GrowableDoublePlantBlock extends GrowablePlantBlock {
	private final Supplier<? extends DoublePlantBlock> grownPlant;

	public GrowableDoublePlantBlock(Properties properties, Supplier<? extends DoublePlantBlock> grownPlant) {
		super(properties);

		this.grownPlant = grownPlant;
	}

	@Override
	int getGrowHeight(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		return 2;
	}

	@Override
	void growPlant(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		level.setBlock(pos, mergeWaterlogging(level, getGrownPlantForHeight(level, random, pos, state, 0).setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), pos), Block.UPDATE_CLIENTS);
		level.setBlock(pos.above(), mergeWaterlogging(level, getGrownPlantForHeight(level, random, pos, state, 1).setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), pos), Block.UPDATE_CLIENTS);
	}

	@Override
	BlockState getGrownPlantForHeight(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, int heightStage) {
		return this.grownPlant.get().defaultBlockState();
	}
}
