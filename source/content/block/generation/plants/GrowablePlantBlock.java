package net.tslat.aoa3.content.block.generation.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class GrowablePlantBlock extends TallGrassBlock {
	public GrowablePlantBlock(Properties properties) {
		super(properties);
	}

	abstract int getGrowHeight(ServerLevel level, RandomSource random, BlockPos pos, BlockState state);

	abstract void growPlant(ServerLevel level, RandomSource random, BlockPos pos, BlockState state);

	abstract BlockState getGrownPlantForHeight(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, int heightStage);

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		if (getGrownPlantForHeight(level, random, pos, state, 0).canSurvive(level, pos)) {
			int height = getGrowHeight(level, random, pos, state);
			BlockPos.MutableBlockPos checkPos = pos.mutable();

			for (int i = 0; i < height - 1; i++) {
				if (!level.isEmptyBlock(checkPos.setY(checkPos.getY() + 1)))
					return;
			}

			growPlant(level, random, pos, state);
		}
	}

	protected final BlockState mergeWaterlogging(Level level, BlockState state, BlockPos pos) {
		return state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, level.isWaterAt(pos)) : state;
	}
}
