package net.tslat.aoa3.content.block.functional.utility;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.content.block.blockentity.ImbuingChamberBlockEntity;
import net.tslat.aoa3.util.InteractionResults;
import org.jetbrains.annotations.Nullable;

public class ImbuingChamber extends HorizontalDirectionalBlock implements EntityBlock {
	public static final MapCodec<ImbuingChamber> CODEC = simpleCodec(ImbuingChamber::new);

	public ImbuingChamber(Properties properties) {
		super(properties);

		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ImbuingChamberBlockEntity(pos, state);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			if (level.getBlockEntity(pos) instanceof ImbuingChamberBlockEntity blockEntity)
				blockEntity.setCustomName(stack.getHoverName());
		}
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			if (level.getBlockEntity(pos) instanceof ImbuingChamberBlockEntity blockEntity)
				blockEntity.dropContents(level, pos);

			super.onRemove(state, level, pos, newState, isMoving);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player instanceof ServerPlayer pl)
			ImbuingChamberMenu.openContainer(pl, pos);

		return InteractionResults.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) instanceof ImbuingChamberBlockEntity blockEntity)
			return blockEntity;

		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
