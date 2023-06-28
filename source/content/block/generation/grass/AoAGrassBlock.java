package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

import java.util.function.Supplier;

public class AoAGrassBlock extends net.minecraft.world.level.block.GrassBlock {
	protected final Supplier<? extends Block> soilBlock;
	protected final boolean growsInDark;

	public AoAGrassBlock(BlockBehaviour.Properties properties, Supplier<? extends Block> soilBlock, boolean growsInDark) {
		super(properties);

		this.soilBlock = soilBlock;
		this.growsInDark = growsInDark;
	}

	public Supplier<? extends Block> getSoilBlock() {
		return soilBlock;
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, Level world, BlockPos grassPos) {
		BlockPos topPos = grassPos.above();
		BlockState topBlock = world.getBlockState(topPos);

		if (topBlock.getBlock() == Blocks.SNOW && topBlock.getValue(SnowLayerBlock.LAYERS) == 1)
			return true;

		return LightEngine.getLightBlockInto(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getLightBlock(world, topPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, Level world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.above()).is(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!hasSufficientLight(state, world, pos)) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			world.setBlockAndUpdate(pos, soilBlock.get().defaultBlockState());
		}
		else if (growsInDark == (world.getMaxLocalRawBrightness(pos.above()) < 9)) {
			BlockState grassState = this.defaultBlockState();

			for (int i = 0; i < 4; i++) {
				BlockPos growPos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

				if (world.getBlockState(growPos).getBlock() == soilBlock.get() && canStayGrass(grassState, world, growPos))
					world.setBlockAndUpdate(growPos, grassState.setValue(SNOWY, world.getBlockState(growPos.above()).getBlock() == Blocks.SNOW));
			}
		}
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

					if (world.getBlockState(spreadPos).getBlock() == soilBlock.get() && isSnowyAndNotUnderwater(defaultState, world, spreadPos))
						world.setBlockAndUpdate(spreadPos, defaultState.setValue(SNOWY, Boolean.valueOf(world.getBlockState(spreadPos.above()).is(Blocks.SNOW))));
				}
			}
		}
	}

	protected boolean couldBeSnowy(BlockState state, LevelAccessor worldReader, BlockPos pos) {
		BlockPos upPos = pos.above();
		BlockState topBlock = worldReader.getBlockState(upPos);

		if (topBlock.is(Blocks.SNOW) && topBlock.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		}
		else if (topBlock.getFluidState().getAmount() == 8) {
			return false;
		}
		else {
			int i = LightEngine.getLightBlockInto(worldReader, state, pos, topBlock, upPos, Direction.UP, topBlock.getLightBlock(worldReader, upPos));

			return i < worldReader.getMaxLightLevel();
		}
	}

	protected boolean isSnowyAndNotUnderwater(BlockState state, LevelAccessor worldReader, BlockPos pos) {
		return couldBeSnowy(state, worldReader, pos) && !worldReader.getFluidState(pos.above()).is(FluidTags.WATER);
	}
}