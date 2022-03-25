package net.tslat.aoa3.content.block.generation.grass;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;
import java.util.function.Supplier;

public class GrassBlock extends net.minecraft.block.GrassBlock {
	protected final Supplier<Block> soilBlock;
	protected final boolean growsInDark;

	public GrassBlock(MaterialColor mapColour, Supplier<Block> soilBlock, boolean growsInDark) {
		this(mapColour, soilBlock, growsInDark, false);
	}

	public GrassBlock(MaterialColor mapColour, Supplier<Block> soilBlock, boolean growsInDark, boolean isStoneBased) {
		super(new BlockUtil.CompactProperties(Material.GRASS, mapColour).stats(0.6f).sound(isStoneBased ? SoundType.NYLIUM : SoundType.GRASS).randomTicks().tool(ToolType.SHOVEL).get());

		this.soilBlock = soilBlock;
		this.growsInDark = growsInDark;
	}

	public Supplier<Block> getSoilBlock() {
		return soilBlock;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, World world, BlockPos grassPos) {
		BlockPos topPos = grassPos.above();
		BlockState topBlock = world.getBlockState(topPos);

		if (topBlock.getBlock() == Blocks.SNOW && topBlock.getValue(SnowBlock.LAYERS) == 1)
			return true;

		return LightEngine.getLightBlockInto(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getLightBlock(world, topPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, World world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.above()).is(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
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

					if (world.getBlockState(spreadPos).getBlock() == soilBlock.get() && isSnowyAndNotUnderwater(defaultState, world, spreadPos))
						world.setBlockAndUpdate(spreadPos, defaultState.setValue(SNOWY, Boolean.valueOf(world.getBlockState(spreadPos.above()).is(Blocks.SNOW))));
				}
			}
		}
	}

	protected boolean couldBeSnowy(BlockState state, IWorldReader worldReader, BlockPos pos) {
		BlockPos upPos = pos.above();
		BlockState topBlock = worldReader.getBlockState(upPos);

		if (topBlock.is(Blocks.SNOW) && topBlock.getValue(SnowBlock.LAYERS) == 1) {
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

	protected boolean isSnowyAndNotUnderwater(BlockState state, IWorldReader worldReader, BlockPos pos) {
		return couldBeSnowy(state, worldReader, pos) && !worldReader.getFluidState(pos.above()).is(FluidTags.WATER);
	}
}
