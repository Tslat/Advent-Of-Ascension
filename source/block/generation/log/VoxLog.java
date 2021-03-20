package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
	private static final VoxelShape X_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(0, 2, 2, 16, 14, 14), IBooleanFunction.NOT_SAME);
	private static final VoxelShape Z_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(2, 2, 0, 14, 14, 16), IBooleanFunction.NOT_SAME);
	private static final VoxelShape Y_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(2, 0, 2, 14, 16, 14), IBooleanFunction.NOT_SAME);

	public VoxLog() {
		super(MaterialColor.COLOR_GREEN, MaterialColor.COLOR_GREEN);

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.getValue(AXIS)) {
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
		return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER).setValue(AXIS, context.getClickedFace().getAxis());
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(BlockStateProperties.WATERLOGGED))
			world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED, AXIS);
	}
}
