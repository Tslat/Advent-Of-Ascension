package net.tslat.aoa3.content.block.decoration.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NonFullBlock extends Block {
	private final VoxelShape SHAPE;

	public NonFullBlock(VoxelShape shape, Properties properties) {
		super(properties);

		this.SHAPE = shape;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
