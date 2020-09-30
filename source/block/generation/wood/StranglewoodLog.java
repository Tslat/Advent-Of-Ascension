package net.tslat.aoa3.block.generation.wood;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StranglewoodLog extends LogBlock {
	public static final AxisAlignedBB VERTICAL_AABB = new AxisAlignedBB(0.001D, 0.0D, 0.001D, 0.999D, 1.0D, 0.999D);
	public static final AxisAlignedBB NORTH_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.001D, 0.001D, 1.0D, 0.999D, 0.999D);
	public static final AxisAlignedBB EAST_WEST_AABB = new AxisAlignedBB(0.001D, 0.001D, 0.0D, 0.999D, 0.999D, 1.0D);
	public static final AxisAlignedBB BARK_AABB = new AxisAlignedBB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D);

	public StranglewoodLog() {
		super("StranglewoodLog", "stranglewood_log");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(LOG_AXIS)) {
			case X:
				return NORTH_SOUTH_AABB;
			case Z:
				return EAST_WEST_AABB;
			case NONE:
				return BARK_AABB;
			case Y:
			default:
				return VERTICAL_AABB;
		}
	}

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
		return true;
	}
}
