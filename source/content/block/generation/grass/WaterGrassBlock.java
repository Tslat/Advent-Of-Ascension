package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class WaterGrassBlock extends GrassBlock {
	public WaterGrassBlock(MaterialColor mapColour, Supplier<Block> soilBlock, boolean growsInDark) {
		super(mapColour, soilBlock, growsInDark);
	}

	@Override
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

		return LayerLightEngine.getLightBlockInto(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getLightBlock(world, topPos)) < world.getMaxLightLevel();
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!couldBeSnowy(state, world, pos)) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			world.setBlockAndUpdate(pos, soilBlock.get().defaultBlockState());
		}
		else {
			if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
				BlockState defaultState = defaultBlockState();

				for(int i = 0; i < 4; ++i) {
					BlockPos spreadPos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

					if (world.getBlockState(spreadPos).is(Blocks.DIRT) && isSnowyAndNotUnderwater(defaultState, world, spreadPos))
						world.setBlockAndUpdate(spreadPos, defaultState.setValue(SNOWY, Boolean.valueOf(world.getBlockState(spreadPos.above()).is(Blocks.SNOW))));
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
			int i = LayerLightEngine.getLightBlockInto(worldReader, state, pos, topBlock, upPos, Direction.UP, topBlock.getLightBlock(worldReader, upPos));

			return i < worldReader.getMaxLightLevel();
		}
	}
}
