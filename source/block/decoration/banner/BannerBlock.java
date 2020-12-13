package net.tslat.aoa3.block.decoration.banner;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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
		super(BlockUtil.generateBlockProperties(Material.MISCELLANEOUS, MaterialColor.IRON, 0.5f, 1f, SoundType.CLOTH).notSolid().doesNotBlockMovement());

		setDefaultState(getDefaultState().with(TYPE, BannerType.MOUNTED).with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (!world.isRemote()) {
			if (state.get(TYPE) == BannerType.MOUNTED) {
				world.setBlockState(pos.down(), AoABlocks.BANNER_EXTENSION.get().getDefaultState().with(TYPE, BannerType.MOUNTED).with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING)).with(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.down()).getFluid() == Fluids.WATER));
			}
			else {
				world.setBlockState(pos.up(), AoABlocks.BANNER_EXTENSION.get().getDefaultState().with(TYPE, BannerType.STANDING).with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING)).with(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.up()).getFluid() == Fluids.WATER));
			}
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (state.get(TYPE) == BannerType.MOUNTED) {
			world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
		}
		else {
			world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
		}

		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		if (state.get(TYPE) == BannerType.MOUNTED) {
			Direction mountedFace = state.get(HorizontalBlock.HORIZONTAL_FACING);

			return hasEnoughSolidSide(world, pos.offset(mountedFace), mountedFace.getOpposite()) && world.getBlockState(pos.down()).getMaterial().isReplaceable();
		}
		else {
			return world.getBlockState(pos.up()).getMaterial().isReplaceable();
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		switch (context.getFace()) {
			case SOUTH:
			case NORTH:
			case WEST:
			case EAST:
				return getDefaultState().with(TYPE, BannerType.MOUNTED).with(HorizontalBlock.HORIZONTAL_FACING, context.getFace().getOpposite()).with(BlockStateProperties.WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
			case UP:
			case DOWN:
			default:
				return getDefaultState().with(TYPE, BannerType.STANDING).with(HorizontalBlock.HORIZONTAL_FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true)).with(BlockStateProperties.WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean mounted = state.get(TYPE) == BannerType.MOUNTED;

		switch (state.get(HorizontalBlock.HORIZONTAL_FACING)) {
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
		if (state.get(TYPE) == BannerType.MOUNTED) {
			if (!world.getBlockState(pos.offset(state.get(HorizontalBlock.HORIZONTAL_FACING))).getMaterial().isSolid())
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		else {
			if (!world.getBlockState(pos.down()).getMaterial().isSolid())
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TYPE, HorizontalBlock.HORIZONTAL_FACING, WATERLOGGED);
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

	public enum BannerType implements IStringSerializable {
		MOUNTED("mounted"),
		STANDING("standing");

		private final String name;

		BannerType(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
	}
}
