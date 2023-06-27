package net.tslat.aoa3.content.block.decoration.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SteelPlateBlock extends Block {
	private static final NonNullList<VoxelShape> SHAPE_MAP = generateShapeMap();

	public SteelPlateBlock(BlockBehaviour.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState()
				.setValue(BlockStateProperties.UP, false)
				.setValue(BlockStateProperties.DOWN, false)
				.setValue(BlockStateProperties.NORTH, false)
				.setValue(BlockStateProperties.SOUTH, false)
				.setValue(BlockStateProperties.EAST, false)
				.setValue(BlockStateProperties.WEST, false)
				.setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		if (useContext.getItemInHand().getItem() != this.asItem())
			return false;

		if (state == defaultBlockState())
			return false;

		return !state.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get((useContext.replacingClickedOnBlock() ? useContext.getClickedFace() : useContext.getClickedFace().getOpposite())));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState replacingState = context.getLevel().getBlockState(context.getClickedPos());

		if (replacingState.getBlock() == this) {
			if (!context.replacingClickedOnBlock()) {
				return replacingState.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(context.getClickedFace().getOpposite()), true);
			}
			else {
				for (Direction direction : context.getNearestLookingDirections()) {
					BooleanProperty property = PipeBlock.PROPERTY_BY_DIRECTION.get(direction);

					if (!replacingState.getValue(property))
						return replacingState.setValue(property, true).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
				}

				return null;
			}
		}
		else {
			if (context.getPlayer() != null && context.getPlayer().isCrouching())
				return defaultBlockState();

			if (!context.replacingClickedOnBlock())
				return defaultBlockState().setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(context.getClickedFace().getOpposite()), true).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);

			for (Direction direction : context.getNearestLookingDirections()) {
				return defaultBlockState().setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), true).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
			}

			return null;
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(BlockStateProperties.WATERLOGGED))
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.UP, BlockStateProperties.DOWN, BlockStateProperties.NORTH, BlockStateProperties.SOUTH, BlockStateProperties.EAST, BlockStateProperties.WEST, BlockStateProperties.WATERLOGGED);
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(stateToShapeIndex(state));
	}

	private static int stateToShapeIndex(BlockState state) {
		int lookup = 0;

		if (state.getValue(BlockStateProperties.UP))
			lookup |= 1;

		if (state.getValue(BlockStateProperties.DOWN))
			lookup |= 2;

		if (state.getValue(BlockStateProperties.NORTH))
			lookup |= 4;

		if (state.getValue(BlockStateProperties.SOUTH))
			lookup |= 8;

		if (state.getValue(BlockStateProperties.EAST))
			lookup |= 16;

		if (state.getValue(BlockStateProperties.WEST))
			lookup |= 32;

		return lookup;
	}

	private static NonNullList<VoxelShape> generateShapeMap() {
		VoxelShape[] shapeList = new VoxelShape[64];
		VoxelShape[] definedShapes = new VoxelShape[] {
				Shapes.box(0, 15 / 16d, 0, 1, 1, 1),
				Shapes.box(0, 0, 0, 1, 1 / 16d, 1),
				Shapes.box(0, 0, 0, 1, 1, 1 / 16d),
				Shapes.box(0, 0, 15 / 16d, 1, 1, 1),
				Shapes.box(15 / 16d, 0, 0, 1, 1, 1),
				Shapes.box(0, 0, 0, 1 / 16d, 1, 1)};

		shapeList[0] = Shapes.block();

		for (int i = 1; i < 64; i++) {
			int shapeIndex = 0;
			VoxelShape[] shapes = new VoxelShape[Integer.bitCount(i)];

			for (int j = 0; j <= 5; j++) {
				if ((i >> j & 1) == 1)
					shapes[shapeIndex++] = definedShapes[j];
			}

			shapeList[i] = Shapes.or(Shapes.empty(), shapes);
		}

		return NonNullList.of(Shapes.empty(), shapeList);
	}
}
