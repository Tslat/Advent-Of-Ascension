package net.tslat.aoa3.content.block.functional.utility;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.InteractionResults;
import org.jetbrains.annotations.Nullable;

public class TinkerersTable extends HorizontalDirectionalBlock {
	private static final VoxelShape BOTTOM_SHAPE = box(1, 0, 1, 15, 9, 15);
	private static final VoxelShape SURFACE_SHAPE = box(0, 9, 0, 16, 12, 16);
	private static final VoxelShape NORTH_SHAPE = Shapes.or(BOTTOM_SHAPE, SURFACE_SHAPE, box(0, 12, 10, 16, 16, 15));
	private static final VoxelShape SOUTH_SHAPE = Shapes.or(BOTTOM_SHAPE, SURFACE_SHAPE, box(0, 12, 0, 16, 16, 5));
	private static final VoxelShape WEST_SHAPE = Shapes.or(BOTTOM_SHAPE, SURFACE_SHAPE, box(10, 12, 0, 15, 16, 16));
	private static final VoxelShape EAST_SHAPE = Shapes.or(BOTTOM_SHAPE, SURFACE_SHAPE, box(0, 12, 0, 5, 16, 16));

	public TinkerersTable(Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return simpleCodec(TinkerersTable::new);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case EAST -> EAST_SHAPE;
			case SOUTH -> SOUTH_SHAPE;
			case WEST -> WEST_SHAPE;
			default -> NORTH_SHAPE;
		};
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true).getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HorizontalDirectionalBlock.FACING);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		//if (player instanceof ServerPlayer pl)
		//	TinkerersTableMenu.openContainer(pl, pos);

		return InteractionResults.BlockUseWithoutItem.succeedAndSwingArmBothSides(level.isClientSide);
	}
}