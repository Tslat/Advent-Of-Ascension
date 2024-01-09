package net.tslat.aoa3.content.block.decoration.banner;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public class BannerExtension extends Block implements SimpleWaterloggedBlock {
	public BannerExtension(BlockBehaviour.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(BannerBlock.TYPE, BannerBlock.BannerType.MOUNTED).setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		BlockPos bannerPos = state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? pos.above() : pos.below();
		BlockState banner = world.getBlockState(bannerPos);

		world.setBlock(bannerPos, Blocks.AIR.defaultBlockState(), 35);
		world.levelEvent(player, 2001, bannerPos, Block.getId(banner));

		if (!world.isClientSide() && !player.isCreative()) {
			dropResources(state, world, pos, null, player, player.getMainHandItem());
			dropResources(banner, world, bannerPos, null, player, player.getMainHandItem());
		}

		return state;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
		BlockState banner;

		return state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? (banner = world.getBlockState(pos.above())).getBlock().getLightEmission(banner, world, pos.above()) : (banner = world.getBlockState(pos.below())).getBlock().getLightEmission(banner, world, pos.below());
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			if (!(world.getBlockState(pos.above()).getBlock() instanceof BannerBlock))
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
		else {
			if (!(world.getBlockState(pos.below()).getBlock() instanceof BannerBlock))
				world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return world.getBlockState(pos.above()).getBlock() instanceof BannerBlock || world.getBlockState(pos.below()).getBlock() instanceof BannerBlock;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			return world.getBlockState(pos.above()).getShape(world, pos.below());
		}
		else {
			return world.getBlockState(pos.below()).getShape(world, pos.above());
		}
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BannerBlock.TYPE, HorizontalDirectionalBlock.FACING, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED))
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		if (state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			BlockPos bannerPos = pos.below();
			BlockState banner = world.getBlockState(bannerPos);

			return banner.getBlock().getCloneItemStack(world, bannerPos, banner);
		}
		else {
			BlockPos bannerPos = pos.above();
			BlockState banner = world.getBlockState(bannerPos);

			return banner.getBlock().getCloneItemStack(world, bannerPos, banner);
		}
	}
}
