package net.tslat.aoa3.object.block.generation.grass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class WaterGrassBlock extends GrassBlock {
	public WaterGrassBlock(MaterialColor mapColour, Supplier<Block> soilBlock, boolean growsInDark) {
		super(mapColour, soilBlock, growsInDark);
	}

	@Override
	public boolean canStayGrass(BlockState grassState, World world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos);
	}

	@Override
	public boolean hasSufficientLight(BlockState grassState, World world, BlockPos grassPos) {
		BlockPos topPos = grassPos.above();
		BlockState topBlock = world.getBlockState(topPos);

		if (topBlock.getBlock() == Blocks.SNOW && topBlock.getValue(SnowBlock.LAYERS) == 1)
			return true;

		if (topBlock.getFluidState().getAmount() == 8)
			return true;

		return LightEngine.getLightBlockInto(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getLightBlock(world, topPos)) < world.getMaxLightLevel();
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
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
	protected boolean couldBeSnowy(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos upPos = pos.above();
		BlockState topBlock = worldReader.getBlockState(upPos);

		if (topBlock.is(Blocks.SNOW) && topBlock.getValue(SnowBlock.LAYERS) == 1) {
			return true;
		}
		else if (topBlock.getFluidState().getAmount() == 8) {
			return true;
		}
		else {
			int i = LightEngine.getLightBlockInto(worldReader, state, pos, topBlock, upPos, Direction.UP, topBlock.getLightBlock(worldReader, upPos));

			return i < worldReader.getMaxLightLevel();
		}
	}
}
