package net.tslat.aoa3.object.block.decoration.banner;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class BannerExtension extends Block implements IWaterLoggable {
	public BannerExtension() {
		super(new BlockUtil.CompactProperties(Material.DECORATION, MaterialColor.METAL).stats(0.5f, 1f).sound(SoundType.WOOL).noClip().noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(BannerBlock.TYPE, BannerBlock.BannerType.MOUNTED).setValue(HorizontalBlock.FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos bannerPos = state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? pos.above() : pos.below();
		BlockState banner = world.getBlockState(bannerPos);

		world.setBlock(bannerPos, Blocks.AIR.defaultBlockState(), 35);
		world.levelEvent(player, 2001, bannerPos, Block.getId(banner));

		if (!world.isClientSide() && !player.isCreative()) {
			dropResources(state, world, pos, null, player, player.getMainHandItem());
			dropResources(banner, world, bannerPos, null, player, player.getMainHandItem());
		}
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		BlockState banner;

		return state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? (banner = world.getBlockState(pos.above())).getBlock().getLightValue(banner, world, pos.above()) : (banner = world.getBlockState(pos.below())).getBlock().getLightValue(banner, world, pos.below());
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
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
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		return world.getBlockState(pos.above()).getBlock() instanceof BannerBlock || world.getBlockState(pos.below()).getBlock() instanceof BannerBlock;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		if (state.getValue(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			return world.getBlockState(pos.above()).getShape(world, pos.below());
		}
		else {
			return world.getBlockState(pos.below()).getShape(world, pos.above());
		}
	}

	@Override
	public float getShadeBrightness(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BannerBlock.TYPE, HorizontalBlock.FACING, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
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
