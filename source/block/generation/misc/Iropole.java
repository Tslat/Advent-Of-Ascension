package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;

public class Iropole extends RotatedPillarBlock {
	private static final VoxelShape Y_SHAPE = makeCuboidShape(3, 0, 3, 13, 16, 13);
	private static final VoxelShape X_SHAPE = makeCuboidShape(0, 3, 3, 16, 13, 13);
	private static final VoxelShape Z_SHAPE = makeCuboidShape(3, 3, 0, 13, 13, 16);

	public Iropole() {
		super(BlockUtil.generateBlockProperties(Material.IRON, MaterialColor.BLACK, 1.5f, 10f, SoundType.METAL));
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
		return getDefaultState().with(RotatedPillarBlock.AXIS, context.getFace().getAxis()).with(BlockStateProperties.WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
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
		builder.add(RotatedPillarBlock.AXIS, BlockStateProperties.WATERLOGGED);
	}
}
