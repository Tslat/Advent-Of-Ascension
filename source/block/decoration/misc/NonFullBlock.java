package net.tslat.aoa3.block.decoration.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NonFullBlock extends Block {
	private final VoxelShape SHAPE;

	public NonFullBlock(VoxelShape shape, Properties properties) {
		super(properties);

		this.SHAPE = shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
