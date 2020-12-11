package net.tslat.aoa3.block.generation.grass;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.Random;
import java.util.function.Supplier;

public class GrassBlock extends net.minecraft.block.GrassBlock {
	protected final Supplier<Block> soilBlock;
	protected final boolean growsInDark;

	public GrassBlock(MaterialColor mapColour, Supplier<Block> soilBlock, boolean growsInDark) {
		super(generateBlockProperties(mapColour));

		this.soilBlock = soilBlock;
		this.growsInDark = growsInDark;
	}

	private static Properties generateBlockProperties(MaterialColor mapColour) {
		Properties blockProperties = Properties.create(Material.ORGANIC, mapColour);

		blockProperties.hardnessAndResistance(0.6f);
		blockProperties.sound(SoundType.PLANT);
		blockProperties.tickRandomly();
		blockProperties.harvestTool(ToolType.SHOVEL);
		blockProperties.harvestLevel(0);

		return blockProperties;
	}

	public Supplier<Block> getSoilBlock() {
		return soilBlock;
	}

	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {}

	public boolean hasSufficientLight(BlockState grassState, IWorldReader world, BlockPos grassPos) {
		BlockPos topPos = grassPos.up();
		BlockState topBlock = world.getBlockState(topPos);

		if (topBlock.getBlock() == Blocks.SNOW && topBlock.get(SnowBlock.LAYERS) == 1)
			return true;

		return LightEngine.func_215613_a(world, grassState, grassPos, topBlock, topPos, Direction.UP, topBlock.getOpacity(world, topPos)) < world.getMaxLightLevel();
	}

	public boolean canStayGrass(BlockState grassState, IWorldReader world, BlockPos grassPos) {
		return hasSufficientLight(grassState, world, grassPos) && !world.getFluidState(grassPos.up()).isTagged(FluidTags.WATER);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!hasSufficientLight(state, world, pos)) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			world.setBlockState(pos, soilBlock.get().getDefaultState());
		}
		else if (growsInDark == (world.getLight(pos.up()) < 9)) {
			BlockState grassState = this.getDefaultState();

			for (int i = 0; i < 4; i++) {
				BlockPos growPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

				if (world.getBlockState(growPos).getBlock() == soilBlock.get() && canStayGrass(grassState, world, growPos))
					world.setBlockState(growPos, grassState.with(SNOWY, world.getBlockState(growPos.up()).getBlock() == Blocks.SNOW));
			}
		}
	}
}
