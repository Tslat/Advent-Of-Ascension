package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class Stranglewood extends LogBlock {
	public static final VoxelShape BARK_AABB = VoxelShapes.create(new AxisAlignedBB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D));

	public Stranglewood() {
		super(MaterialColor.COLOR_BROWN, MaterialColor.TERRACOTTA_BROWN);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return BARK_AABB;
	}

	@Override
	public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
		return true;
	}
}
