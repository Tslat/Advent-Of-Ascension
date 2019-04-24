package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class DawnwoodBars extends BidirectionalPlantStackable {
	public DawnwoodBars() {
		super("DawnwoodBars", "dawnwood_bars");
		setHardness(1.0f);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1D, 0.625D);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1D, 0.625D);
	}
}
