package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.tslat.aoa3.common.registration.AoABlocks;

public class Stranglewood extends LogBlock {
	public static final VoxelShape BARK_AABB = VoxelShapes.create(new AxisAlignedBB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D));

	public Stranglewood() {
		super(MaterialColor.COLOR_BROWN, AoABlocks.STRIPPED_STRANGLEWOOD);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return BARK_AABB;
	}
}
