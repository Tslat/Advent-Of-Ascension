package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.tileentity.BossAltarTileEntity;
import net.tslat.aoa3.data.server.AoANowhereBossArenaListener;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;

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
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE.key))
			return InteractionResult.PASS;

		ItemStack heldItem = player.getItemInHand(hand);

		//if (!(heldItem.getItem() instanceof BossSpawningItem<?> bossItem)) {
		//	if (hand == InteractionHand.OFF_HAND)
		//		player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.badItem"));
// TODO temp disabled for giants testing
		//	return InteractionResult.FAIL;
		//}

		BiFunction<ServerLevel, Vec3, Entity> bossFunction = getBossFunction(heldItem.getItem());

		if (bossFunction == null) {
			if (hand == InteractionHand.OFF_HAND)
				player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.nowhere.boss.tooFar", ChatFormatting.RED));

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

					bossAltar.updateEntity(AoAMobs.ICE_GIANT.get());
					AoAScheduler.scheduleSyncronisedTask(() -> bossAltar.updateEntity(null), 95);
				}

				arena.placePlayersAndBoss(serverLevel, players, pl -> pl.getLevel() == level && pl.isAlive() && teleportBounds.contains(pl.position()), bossFunction/*bossItem::spawnBoss*/);
			}
		}

		return InteractionResult.SUCCESS;
	}

	// TODO Remove
	@Nullable
	private BiFunction<ServerLevel, Vec3, Entity> getBossFunction(Item item) {
		if (item == Items.ICE) {
			return (level, pos) -> EntitySpawningUtil.spawnEntity(level, AoAMobs.ICE_GIANT.get(), pos, MobSpawnType.EVENT);
		}
		else if (item == Items.SAND) {
			return (level, pos) -> EntitySpawningUtil.spawnEntity(level, AoAMobs.SAND_GIANT.get(), pos, MobSpawnType.EVENT);
		}
		else if (TagUtil.isTaggedAs(item, ItemTags.LEAVES)) {
			return (level, pos) -> EntitySpawningUtil.spawnEntity(level, AoAMobs.LEAFY_GIANT.get(), pos, MobSpawnType.EVENT);
		}
		else if (item == Items.STONE) {
			return (level, pos) -> EntitySpawningUtil.spawnEntity(level, AoAMobs.STONE_GIANT.get(), pos, MobSpawnType.EVENT);
		}
		else if (TagUtil.isTaggedAs(item, ItemTags.LOGS)) {
			return (level, pos) -> EntitySpawningUtil.spawnEntity(level, AoAMobs.WOOD_GIANT.get(), pos, MobSpawnType.EVENT);
		}

		return null;
	}
}
