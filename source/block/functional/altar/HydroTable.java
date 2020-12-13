package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.HydroliskEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;

public class HydroTable extends BossAltarBlock implements IWaterLoggable {
	private static final VoxelShape BOTTOM_SHAPE = makeCuboidShape(0, 0, 0, 16, 1, 16);
	private static final VoxelShape MID_SHAPE = makeCuboidShape(4, 1, 4, 12, 13, 12);
	private static final VoxelShape TOP_SHAPE = makeCuboidShape(0, 13, 0, 16, 16, 16);
	private static final VoxelShape SHAPE = VoxelShapes.or(BOTTOM_SHAPE, MID_SHAPE, TOP_SHAPE);

	public HydroTable() {
		super(MaterialColor.CYAN_TERRACOTTA);

		setDefaultState(getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		HydroliskEntity hydrolisk = new HydroliskEntity(AoAEntities.Mobs.HYDROLISK.get(), player.world);

		hydrolisk.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(hydrolisk);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.hydrolisk.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.PURE_WATER_STONE.get();
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
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}
}
