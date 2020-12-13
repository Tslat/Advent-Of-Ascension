package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class VoxLog extends LogBlock {
	private static final VoxelShape X_SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), makeCuboidShape(0, 2, 2, 16, 14, 14), IBooleanFunction.NOT_SAME);
	private static final VoxelShape Z_SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), makeCuboidShape(2, 2, 0, 14, 14, 16), IBooleanFunction.NOT_SAME);
	private static final VoxelShape Y_SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), makeCuboidShape(2, 0, 2, 14, 16, 14), IBooleanFunction.NOT_SAME);

	public VoxLog() {
		super(MaterialColor.GREEN, MaterialColor.GREEN);

		setDefaultState(getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(AXIS)) {
			case X:
				return X_SHAPE;
			case Y:
				return Y_SHAPE;
			case Z:
			default:
				return Z_SHAPE;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(BlockStateProperties.WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER).with(AXIS, context.getFace().getAxis());
	}

	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (state.get(BlockStateProperties.WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED, AXIS);
	}
}
