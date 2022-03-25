package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.Random;
import java.util.function.Supplier;

public class UpsideDownGrassBlock extends GrassBlock {
	public UpsideDownGrassBlock(MaterialColor mapColour, Supplier<Block> dirtBlock, boolean growsInDark, boolean isStoneBased) {
		super(mapColour, dirtBlock, growsInDark, isStoneBased);
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, World world, BlockPos grassPos) {
		if (world.dimension() == RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(AdventOfAscension.MOD_ID, "lelyetia")))
			return true;

		BlockPos bottomPos = grassPos.below();
		BlockState bottomBlock = world.getBlockState(bottomPos);

		return LightEngine.getLightBlockInto(world, grassState, grassPos, bottomBlock, bottomPos, Direction.DOWN, bottomBlock.getLightBlock(world, bottomPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, World world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.below()).is(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
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
	protected boolean couldBeSnowy(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos downPos = pos.below();
		BlockState bottomBlock = worldReader.getBlockState(downPos);

		if (bottomBlock.is(Blocks.SNOW) && bottomBlock.getValue(SnowBlock.LAYERS) == 1) {
			return true;
		}
		else if (bottomBlock.getFluidState().getAmount() == 8) {
			return false;
		}
		else {
			int i = LightEngine.getLightBlockInto(worldReader, state, pos, bottomBlock, downPos, Direction.DOWN, bottomBlock.getLightBlock(worldReader, downPos));

			return i < worldReader.getMaxLightLevel();
		}
	}
}
