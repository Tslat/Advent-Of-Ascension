package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

public class ClearWater extends FlowingFluidBlock {
	public ClearWater(Supplier<? extends FlowingFluid> supplier, Properties properties) {
		super(supplier, properties);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
}
