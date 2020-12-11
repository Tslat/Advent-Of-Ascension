package net.tslat.aoa3.block.decoration.banner;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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
		super(BlockUtil.generateBlockProperties(Material.MISCELLANEOUS, MaterialColor.IRON, 0.5f, 1f, SoundType.CLOTH).notSolid().doesNotBlockMovement());

		setDefaultState(getDefaultState().with(BannerBlock.TYPE, BannerBlock.BannerType.MOUNTED).with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos bannerPos = state.get(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? pos.up() : pos.down();
		BlockState banner = world.getBlockState(bannerPos);

		world.setBlockState(bannerPos, Blocks.AIR.getDefaultState(), 35);
		world.playEvent(player, 2001, bannerPos, Block.getStateId(banner));

		if (!world.isRemote() && !player.isCreative()) {
			spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
			spawnDrops(banner, world, bannerPos, null, player, player.getHeldItemMainhand());
		}
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		BlockState banner;

		return state.get(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED ? (banner = world.getBlockState(pos.up())).getBlock().getLightValue(banner, world, pos.up()) : (banner = world.getBlockState(pos.down())).getBlock().getLightValue(banner, world, pos.down());
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (state.get(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			if (!(world.getBlockState(pos.up()).getBlock() instanceof BannerBlock))
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		else {
			if (!(world.getBlockState(pos.down()).getBlock() instanceof BannerBlock))
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		return world.getBlockState(pos.up()).getBlock() instanceof BannerBlock || world.getBlockState(pos.down()).getBlock() instanceof BannerBlock;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		if (state.get(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			return world.getBlockState(pos.up()).getShape(world, pos.down());
		}
		else {
			return world.getBlockState(pos.down()).getShape(world, pos.up());
		}
	}

	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BannerBlock.TYPE, HorizontalBlock.HORIZONTAL_FACING, WATERLOGGED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(BlockStateProperties.WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		if (state.get(BannerBlock.TYPE) == BannerBlock.BannerType.MOUNTED) {
			BlockPos bannerPos = pos.down();
			BlockState banner = world.getBlockState(bannerPos);

			return banner.getBlock().getItem(world, bannerPos, banner);
		}
		else {
			BlockPos bannerPos = pos.up();
			BlockState banner = world.getBlockState(bannerPos);

			return banner.getBlock().getItem(world, bannerPos, banner);
		}
	}
}
