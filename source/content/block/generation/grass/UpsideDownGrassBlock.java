package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Supplier;

public class UpsideDownGrassBlock extends GrassBlock {
	public UpsideDownGrassBlock(MaterialColor mapColour, Supplier<Block> dirtBlock, boolean growsInDark, boolean isStoneBased) {
		super(mapColour, dirtBlock, growsInDark, isStoneBased);
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, Level world, BlockPos grassPos) {
		if (world.dimension() == ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, "lelyetia")))
			return true;

		BlockPos bottomPos = grassPos.below();
		BlockState bottomBlock = world.getBlockState(bottomPos);

		return LayerLightEngine.getLightBlockInto(world, grassState, grassPos, bottomBlock, bottomPos, Direction.DOWN, bottomBlock.getLightBlock(world, bottomPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, Level world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.below()).is(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!hasSufficientLight(state, world, pos)) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			world.setBlockAndUpdate(pos, soilBlock.get().defaultBlockState());
		}
		else if (growsInDark == (world.getMaxLocalRawBrightness(pos.below()) < 9)) {
			BlockState grassState = this.defaultBlockState();

			for (int i = 0; i < 4; i++) {
				BlockPos growPos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

				if (world.getBlockState(growPos).getBlock() == soilBlock.get() && canStayGrass(grassState, world, growPos))
					world.setBlockAndUpdate(growPos, grassState.setValue(SNOWY, world.getBlockState(growPos.below()).getBlock() == Blocks.SNOW));
			}
		}
	}

	@Override
	protected boolean couldBeSnowy(BlockState state, LevelAccessor worldReader, BlockPos pos) {
		BlockPos downPos = pos.below();
		BlockState bottomBlock = worldReader.getBlockState(downPos);

		if (bottomBlock.is(Blocks.SNOW) && bottomBlock.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		}
		else if (bottomBlock.getFluidState().getAmount() == 8) {
			return false;
		}
		else {
			int i = LayerLightEngine.getLightBlockInto(worldReader, state, pos, bottomBlock, downPos, Direction.DOWN, bottomBlock.getLightBlock(worldReader, downPos));

			return i < worldReader.getMaxLightLevel();
		}
	}
}
