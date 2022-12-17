package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.tileentity.BossAltarTileEntity;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.data.server.AoANowhereBossArenaListener;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BossAltar extends Block implements EntityBlock {
	private static final VoxelShape SHAPE = BlockUtil.pixelBasedCube(4, 0, 4, 12, 12, 12);

	public BossAltar() {
		super(new BlockUtil.CompactProperties(Material.HEAVY_METAL, MaterialColor.COLOR_BLACK).unbreakable().light(2).emissive().noOcclusion().get());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BossAltarTileEntity(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE.key) || level.getDifficulty() == Difficulty.PEACEFUL)
			return InteractionResult.PASS;

		ItemStack heldItem = player.getItemInHand(hand);
		BossTokenItem bossItem;
		EntityType<?> entityType;

		if ((bossItem = getEntityTypeFromStack(heldItem)) == null || (entityType = bossItem.getEntityType(heldItem)) == null) {
			if (hand == InteractionHand.OFF_HAND)
				player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.badItem"));

			return InteractionResult.FAIL;
		}

		if (level instanceof ServerLevel serverLevel) {
			AABB teleportBounds = new AABB(pos.getX() - 3, pos.getY(), pos.getZ() - 3, pos.getX() + 4, pos.getY() + 3, pos.getZ() + 3);
			List<Player> players = EntityRetrievalUtil.getPlayers(level, teleportBounds, LivingEntity::isAlive);

			if (players.isEmpty()) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.tooFar", ChatFormatting.RED));

				return InteractionResult.FAIL;
			}

			AoANowhereBossArenaListener.NowhereBossArena arena = AoANowhereBossArenaListener.getFreeArena(serverLevel);

			if (arena == null) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.full", ChatFormatting.RED));
			}
			else {
				BlockEntity blockEntity = level.getBlockEntity(pos);

				if (blockEntity instanceof BossAltarTileEntity bossAltar) {
					if (bossAltar.getCurrentEntity() != null) {
						player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.inUse", ChatFormatting.RED));

						return InteractionResult.FAIL;
					}

					bossAltar.updateEntity(entityType);
					AoAScheduler.scheduleSyncronisedTask(() -> bossAltar.updateEntity(null), 95);
				}

				arena.placePlayersAndBoss(serverLevel, players, pl -> pl.getLevel() == level && pl.isAlive() && teleportBounds.contains(pl.position()), heldItem, bossItem::spawnBoss);
			}
		}

		return InteractionResult.SUCCESS;
	}

	@Nullable
	private BossTokenItem getEntityTypeFromStack(ItemStack stack) {
		BossTokenItem token = null;

		if (stack.getItem() instanceof BossTokenItem tokenItem) {
			token = tokenItem;
		}
		else if (stack.getItem() instanceof BlockItem block && block.getBlock() instanceof BossTokenItem tokenItem) {
			token = tokenItem;
		}

		return token;
	}
}
