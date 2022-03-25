package net.tslat.aoa3.content.block.decoration.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;

public class SteelPlateBlock extends Block {
	private static final NonNullList<VoxelShape> SHAPE_MAP = generateShapeMap();

	public SteelPlateBlock() {
		super(new BlockUtil.CompactProperties(Material.HEAVY_METAL, MaterialColor.METAL)
						.stats(7f, 20f)
						.tool(ToolType.PICKAXE)
						.harvestTool(ToolType.PICKAXE).get());

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
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		if (useContext.getItemInHand().getItem() != this.asItem())
			return false;

		if (state == defaultBlockState())
			return false;

		return !state.getValue(SixWayBlock.PROPERTY_BY_DIRECTION.get((useContext.replacingClickedOnBlock() ? useContext.getClickedFace() : useContext.getClickedFace().getOpposite())));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState replacingState = context.getLevel().getBlockState(context.getClickedPos());

		if (replacingState.getBlock() == this) {
			if (!context.replacingClickedOnBlock()) {
				return replacingState.setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(context.getClickedFace().getOpposite()), true);
			}
			else {
				for (Direction direction : context.getNearestLookingDirections()) {
					BooleanProperty property = SixWayBlock.PROPERTY_BY_DIRECTION.get(direction);

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
				return defaultBlockState().setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(context.getClickedFace().getOpposite()), true).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);

			for (Direction direction : context.getNearestLookingDirections()) {
				return defaultBlockState().setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(direction), true).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
			}

			return null;
		}
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
		builder.add(BlockStateProperties.UP, BlockStateProperties.DOWN, BlockStateProperties.NORTH, BlockStateProperties.SOUTH, BlockStateProperties.EAST, BlockStateProperties.WEST, BlockStateProperties.WATERLOGGED);
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
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
				VoxelShapes.box(0, 15 / 16d, 0, 1, 1, 1),
				VoxelShapes.box(0, 0, 0, 1, 1 / 16d, 1),
				VoxelShapes.box(0, 0, 0, 1, 1, 1 / 16d),
				VoxelShapes.box(0, 0, 15 / 16d, 1, 1, 1),
				VoxelShapes.box(15 / 16d, 0, 0, 1, 1, 1),
				VoxelShapes.box(0, 0, 0, 1 / 16d, 1, 1)};

		shapeList[0] = VoxelShapes.block();

		for (int i = 1; i < 64; i++) {
			int shapeIndex = 0;
			VoxelShape[] shapes = new VoxelShape[Integer.bitCount(i)];

			for (int j = 0; j <= 5; j++) {
				if ((i >> j & 1) == 1)
					shapes[shapeIndex++] = definedShapes[j];
			}

			shapeList[i] = VoxelShapes.or(VoxelShapes.empty(), shapes);
		}

		return NonNullList.of(VoxelShapes.empty(), shapeList);
	}
}
