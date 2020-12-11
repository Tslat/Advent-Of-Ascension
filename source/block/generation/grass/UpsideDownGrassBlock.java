package net.tslat.aoa3.block.generation.grass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class UpsideDownGrassBlock extends GrassBlock {
	public UpsideDownGrassBlock(MaterialColor mapColour, Supplier<Block> dirtBlock, boolean growsInDark) {
		super(mapColour, dirtBlock, growsInDark);
	}

	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, IWorldReader world, BlockPos grassPos) {
		BlockPos bottomPos = grassPos.down();
		BlockState bottomBlock = world.getBlockState(bottomPos);

		return LightEngine.func_215613_a(world, grassState, grassPos, bottomBlock, bottomPos, Direction.DOWN, bottomBlock.getOpacity(world, bottomPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, IWorldReader world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.down()).isTagged(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!hasSufficientLight(state, world, pos)) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			world.setBlockState(pos, soilBlock.get().getDefaultState());
		}
		else if (growsInDark == (world.getLight(pos.down()) < 9)) {
			BlockState grassState = this.getDefaultState();

			for (int i = 0; i < 4; i++) {
				BlockPos growPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

				if (world.getBlockState(growPos).getBlock() == soilBlock.get() && canStayGrass(grassState, world, growPos))
					world.setBlockState(growPos, grassState.with(SNOWY, world.getBlockState(growPos.down()).getBlock() == Blocks.SNOW));
			}
		}
	}
}
