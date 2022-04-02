package net.tslat.aoa3.content.block.decoration.banner;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class BannerBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<BannerType> TYPE = EnumProperty.create("type", BannerType.class);

	private static final VoxelShape MOUNTED_NORTH_SHAPE = Shapes.create(new AABB(0, 0, 0, 1, 1, 0.15625));
	private static final VoxelShape MOUNTED_SOUTH_SHAPE = Shapes.create(new AABB(0, 0, 0.84375, 1, 1, 1));
	private static final VoxelShape MOUNTED_EAST_SHAPE = Shapes.create(new AABB(0.84375, 0, 0, 1, 1, 1));
	private static final VoxelShape MOUNTED_WEST_SHAPE = Shapes.create(new AABB(0, 0, 0, 0.15625, 1, 1));

	private static final VoxelShape STANDING_NORTH_SHAPE = Shapes.create(new AABB(0.1875, 0, 0.453125, 0.8125, 1, 0.609375));
	private static final VoxelShape STANDING_SOUTH_SHAPE = Shapes.create(new AABB(0.1875, 0, 0.390625, 0.8125, 1, 0.546875));
	private static final VoxelShape STANDING_EAST_SHAPE = Shapes.create(new AABB(0.390625, 0, 0.1875, 0.546875, 1, 0.8125));
	private static final VoxelShape STANDING_WEST_SHAPE = Shapes.create(new AABB(0.453125, 0, 0.1875, 0.609375, 1, 0.8125));

	public BannerBlock() {
		super(new BlockUtil.CompactProperties(Material.DECORATION, MaterialColor.METAL).stats(0.5f, 1f).sound(SoundType.WOOL).noClip().noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH).setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (!world.isClientSide()) {
			if (state.getValue(TYPE) == BannerType.MOUNTED) {
				world.setBlockAndUpdate(pos.below(), AoABlocks.BANNER_EXTENSION.get().defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)).setValue(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.below()).getType() == Fluids.WATER));
			}
			else {
				world.setBlockAndUpdate(pos.above(), AoABlocks.BANNER_EXTENSION.get().defaultBlockState().setValue(TYPE, BannerType.STANDING).setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)).setValue(BlockStateProperties.WATERLOGGED, world.getFluidState(pos.above()).getType() == Fluids.WATER));
			}
		}
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			world.setBlockAndUpdate(pos.below(), Blocks.AIR.defaultBlockState());
		}
		else {
			world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			Direction mountedFace = state.getValue(HorizontalDirectionalBlock.FACING);

			return canSupportCenter(world, pos.relative(mountedFace), mountedFace.getOpposite()) && world.getBlockState(pos.below()).getMaterial().isReplaceable();
		}
		else {
			return world.getBlockState(pos.above()).getMaterial().isReplaceable();
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return switch (context.getClickedFace()) {
			case SOUTH, NORTH, WEST, EAST -> defaultBlockState().setValue(TYPE, BannerType.MOUNTED).setValue(HorizontalDirectionalBlock.FACING, context.getClickedFace().getOpposite()).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
			case UP, DOWN -> defaultBlockState().setValue(TYPE, BannerType.STANDING).setValue(HorizontalDirectionalBlock.FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true)).setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
		};
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean mounted = state.getValue(TYPE) == BannerType.MOUNTED;

		return switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
			case SOUTH -> mounted ? MOUNTED_SOUTH_SHAPE : STANDING_SOUTH_SHAPE;
			case EAST -> mounted ? MOUNTED_EAST_SHAPE : STANDING_EAST_SHAPE;
			case WEST -> mounted ? MOUNTED_WEST_SHAPE : STANDING_WEST_SHAPE;
			case NORTH, DOWN, UP -> mounted ? MOUNTED_NORTH_SHAPE : STANDING_NORTH_SHAPE;
		};
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			if (!world.getBlockState(pos.relative(state.getValue(HorizontalDirectionalBlock.FACING))).getMaterial().isSolid())
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
		else {
			if (!world.getBlockState(pos.below()).getMaterial().isSolid())
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, HorizontalDirectionalBlock.FACING, BlockStateProperties.WATERLOGGED);
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

	public enum BannerType implements StringRepresentable {
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
