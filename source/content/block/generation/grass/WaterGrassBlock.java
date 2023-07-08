package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class WaterGrassBlock extends AoAGrassBlock {
	public WaterGrassBlock(BlockBehaviour.Properties properties, Supplier<Block> soilBlock, boolean growsInDark) {
		super(properties, soilBlock);
	}

	/*@Override
	public boolean canStayGrass(BlockState grassState, Level world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos);
	}

	@Override
	public boolean hasSufficientLight(BlockState grassState, Level world, BlockPos grassPos) {
		BlockPos topPos = grassPos.above();
		BlockState topBlock = world.getBlockState(topPos);

		if (topBlock.getBlock() == Blocks.SNOW && topBlock.getValue(SnowLayerBlock.LAYERS) == 1)
			return true;

		if (topBlock.getFluidState().getAmount() == 8)
			return true;

		return LightEngine.getLightBlockInto(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getLightBlock(world, topPos)) < world.getMaxLightLevel();
	}*/

	/*@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!couldBeSnowy(state, level, pos)) {
			if (!level.isAreaLoaded(pos, 3))
				return;

			level.setBlockAndUpdate(pos, soilBlock.get().defaultBlockState());
		}
		else {
			if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
				BlockState defaultState = defaultBlockState();

				for(int i = 0; i < 4; ++i) {
					BlockPos spreadPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

					if (level.getBlockState(spreadPos).is(Blocks.DIRT) && isSnowyAndNotUnderwater(defaultState, level, spreadPos))
						level.setBlockAndUpdate(spreadPos, defaultState.setValue(SNOWY, Boolean.valueOf(level.getBlockState(spreadPos.above()).is(Blocks.SNOW))));
				}
			}
		}
	}

	@Override
	protected boolean couldBeSnowy(BlockState state, LevelAccessor worldReader, BlockPos pos) {
		BlockPos upPos = pos.above();
		BlockState topBlock = worldReader.getBlockState(upPos);

		if (topBlock.is(Blocks.SNOW) && topBlock.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		}
		else if (topBlock.getFluidState().getAmount() == 8) {
			return true;
		}
		else {
			int i = LightEngine.getLightBlockInto(worldReader, state, pos, topBlock, upPos, Direction.UP, topBlock.getLightBlock(worldReader, upPos));

			return i < worldReader.getMaxLightLevel();
		}
	}*/
}
