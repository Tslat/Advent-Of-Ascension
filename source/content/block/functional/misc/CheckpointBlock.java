package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.library.object.PositionAndRotation;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class CheckpointBlock extends Block {
	public CheckpointBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getPlayer() != null && useContext.getPlayer().isCreative();
	}

	@Override
	public boolean canBeReplaced(BlockState state, Fluid fluid) {
		return false;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		return ItemStack.EMPTY;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity.tickCount % 5 == 0 && !level.isClientSide() && EntityPredicate.SURVIVAL_PLAYER.test(entity)) {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);
			PositionAndRotation checkpoint = plData.getCheckpoint();

			if (checkpoint == null || checkpoint.asBlockPos().distSqr(pos) > 9) {
				plData.setCheckpoint(PositionAndRotation.from(pos, entity));
				plData.player().sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.checkpoint.set", ChatFormatting.GREEN), true);
				new SoundBuilder(AoASounds.CHECKPOINT).notInWorld().execute();
			}
		}
	}

	public static boolean isValidCheckpoint(Level world, PositionAndRotation checkpoint) {
		ChunkAccess chunk = world.getChunkSource().getChunk(SectionPos.blockToSectionCoord(checkpoint.x()), SectionPos.blockToSectionCoord(checkpoint.z()), true);

		return chunk != null && chunk.getBlockState(checkpoint.asBlockPos()).getBlock() == AoABlocks.CHECKPOINT.get();
	}
}
