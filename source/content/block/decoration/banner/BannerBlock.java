package net.tslat.aoa3.content.block.decoration.banner;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class BannerBlock extends Block implements IWaterLoggable {
	public static final EnumProperty<BannerType> TYPE = EnumProperty.<BannerType>create("type", BannerType.class);

	private static final VoxelShape MOUNTED_NORTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0, 1, 1, 0.15625));
	private static final VoxelShape MOUNTED_SOUTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0.84375, 1, 1, 1));
	private static final VoxelShape MOUNTED_EAST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.84375, 0, 0, 1, 1, 1));
	private static final VoxelShape MOUNTED_WEST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0, 0.15625, 1, 1));

	private static final VoxelShape STANDING_NORTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.1875, 0, 0.453125, 0.8125, 1, 0.609375));
	private static final VoxelShape STANDING_SOUTH_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.1875, 0, 0.390625, 0.8125, 1, 0.546875));
	private static final VoxelShape STANDING_EAST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.390625, 0, 0.1875, 0.546875, 1, 0.8125));
	private static final VoxelShape STANDING_WEST_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.453125, 0, 0.1875, 0.609375, 1, 0.8125));

	public BannerBlock() {
		super(new BlockUtil.CompactProperties(Material.DECORATION, MaterialColor.METAL).stats(0.5f, 1f).sound(SoundType.WOOL).noClip().noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalBlock.FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (!world.isClientSide()) {
			if (state.getValue(TYPE) == BannerType.MOUNTED) {
				world.setBlockAndUpdate(pos.below(), AoABlocks.BANNER_EXTENSION.get().defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalBlock.FACING, state.getValue(HorizontalBlock.FACING)).setValue(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.below()).getType() == Fluids.WATER));
			}
			else {
				world.setBlockAndUpdate(pos.above(), AoABlocks.BANNER_EXTENSION.get().defaultBlockState().setValue(TYPE, BannerType.STANDING).setValue(HorizontalBlock.FACING, state.getValue(HorizontalBlock.FACING)).setValue(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.above()).getType() == Fluids.WATER));
			}
		}
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			world.setBlockAndUpdate(pos.below(), Blocks.AIR.defaultBlockState());
		}
		else {
			world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	public float getShadeBrightness(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			Direction mountedFace = state.getValue(HorizontalBlock.FACING);

			return canSupportCenter(world, pos.relative(mountedFace), mountedFace.getOpposite()) && world.getBlockState(pos.below()).getMaterial().isReplaceable();
		}
		else {
			return world.getBlockState(pos.above()).getMaterial().isReplaceable();
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		switch (context.getClickedFace()) {
			case SOUTH:
			case NORTH:
			case WEST:
			case EAST:
				return defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalBlock.FACING, context.getClickedFace().getOpposite()).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
			case UP:
			case DOWN:
			default:
				return defaultBlockState().setValue(TYPE, BannerType.STANDING).setValue(HorizontalBlock.FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true)).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean mounted = state.getValue(TYPE) == BannerType.MOUNTED;

		switch (state.getValue(HorizontalBlock.FACING)) {
			case SOUTH:
				return mounted ? MOUNTED_SOUTH_SHAPE : STANDING_SOUTH_SHAPE;
			case EAST:
				return mounted ? MOUNTED_EAST_SHAPE : STANDING_EAST_SHAPE;
			case WEST:
				return mounted ? MOUNTED_WEST_SHAPE : STANDING_WEST_SHAPE;
			case NORTH:
			default:
				return mounted ? MOUNTED_NORTH_SHAPE : STANDING_NORTH_SHAPE;
		}
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			if (!world.getBlockState(pos.relative(state.getValue(HorizontalBlock.FACING))).getMaterial().isSolid())
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
		else {
			if (!world.getBlockState(pos.below()).getMaterial().isSolid())
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TYPE, HorizontalBlock.FACING, WATERLOGGED);
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

	public enum BannerType implements IStringSerializable {
		MOUNTED("mounted"),
		STANDING("standing");

		private final String name;

		BannerType(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
