package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.blockentity.BossAltarBlockEntity;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.content.world.nowhere.NowhereBossArena;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.InteractionResults;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BossAltar extends Block implements EntityBlock {
	private static final VoxelShape SHAPE = BlockUtil.pixelBasedCube(4, 0, 4, 12, 12, 12);

	public BossAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BossAltarBlockEntity(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE))
			return InteractionResults.BlockUseItemOn.noActionTaken();

		if (level.getDifficulty() == Difficulty.PEACEFUL) {
			if (!level.isClientSide && hand == InteractionHand.MAIN_HAND)
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.difficulty"), ChatFormatting.RED));

			return InteractionResults.BlockUseItemOn.noActionTaken();
		}

		ItemStack heldItem = player.getItemInHand(hand);
		BossTokenItem bossItem;
		EntityType<?> entityType;

		if ((bossItem = getEntityTypeFromStack(heldItem)) == null || (entityType = bossItem.getEntityType(heldItem)) == null) {
			if (hand == InteractionHand.OFF_HAND && !level.isClientSide())
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.badItem"), ChatFormatting.RED));

			return InteractionResults.BlockUseItemOn.noActionTaken();
		}

		if (level instanceof ServerLevel serverLevel) {
			AABB teleportBounds = new AABB(pos.getX() - 3, pos.getY(), pos.getZ() - 3, pos.getX() + 4, pos.getY() + 3, pos.getZ() + 3);
			List<Player> players = EntityRetrievalUtil.getPlayers(level, teleportBounds, LivingEntity::isAlive);

			if (players.isEmpty()) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.tooFar"), ChatFormatting.RED));

				return InteractionResults.BlockUseItemOn.noActionTaken();
			}

			NowhereBossArena arena = NowhereBossArena.getFreeArena(serverLevel);

			if (arena == null) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.full"), ChatFormatting.RED));
			}
			else {
				BlockEntity blockEntity = level.getBlockEntity(pos);

				if (blockEntity instanceof BossAltarBlockEntity bossAltar) {
					if (bossAltar.getCurrentEntity() != null) {
						player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.inUse"), ChatFormatting.RED));

						return InteractionResults.BlockUseItemOn.noActionTaken();
					}

					bossAltar.updateEntity(entityType);
					AoAScheduler.schedule(95, tick -> bossAltar.updateEntity(null));
				}

				arena.placePlayersAndBoss(serverLevel, players, pl -> pl.level() == level && pl.isAlive() && teleportBounds.contains(pl.position()), heldItem, entityType, bossItem::spawnBoss);
			}
		}

		return InteractionResults.BlockUseItemOn.succeedAndSwingArmBothSides(level.isClientSide);
	}

	@Nullable
	private BossTokenItem getEntityTypeFromStack(ItemStack stack) {
		return switch (stack.getItem()) {
			case BossTokenItem bossToken -> bossToken;
			case BlockItem blockItem when blockItem.getBlock() instanceof BossTokenItem token -> token;
			default -> null;
		};
	}
}
