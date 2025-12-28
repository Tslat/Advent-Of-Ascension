package net.tslat.aoa3.event.dimension;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.misc.CheckpointBlock;
import net.tslat.aoa3.content.block.functional.portal.NowhereActivityPortal;
import net.tslat.aoa3.content.block.functional.utility.TeaSink;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.library.object.container.PositionAndRotation;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public final class NowhereEvents {
	public static boolean isInParkourRegion(BlockPos pos) {
		return pos.getX() > 900 && pos.getZ() > 900;
	}

	public static boolean isInBossRegion(BlockPos pos) {
		return pos.getX() < -500 && pos.getZ() < -500;
	}

	public static boolean isInFoodFreeRegion(BlockPos pos) {
		return pos.getX() > 0 && pos.getZ() > 0;
	}

	public static boolean isInLobbyRegion(BlockPos pos) {
		return pos.getX() < 250 && pos.getZ() < 250 && pos.getX() > -250 && pos.getZ() > -250;
	}

	public static void doPlayerTick(final PlayerTickEvent ev) {
		Player pl = ev.getEntity();

		if (ev instanceof PlayerTickEvent.Pre) {
			if (pl instanceof ServerPlayer serverPl) {
				if (isInParkourRegion(serverPl.blockPosition())) {
					if (PlayerUtil.shouldPlayerBeAffected(serverPl))
						PlayerUtil.getAdventPlayer(serverPl).stats.setInAbilityLockRegion();
				}
				else {
					PlayerUtil.getAdventPlayer(serverPl).stats.leaveAbilityLockRegion();
				}
			}
			else if (isInBossRegion(pl.blockPosition())) {
				List<AoABoss> bosses = EntityRetrievalUtil.getEntities(pl, 80, AoABoss.class);

				if (!bosses.isEmpty()) {
					AoABoss boss = bosses.getFirst();

					if (boss.getMusic() != null)
						SoundBuilder.asMusic(boss.getMusic(), boss.level()).onlyFor(pl).play();
				}
			}
		}
		else {
			pl.getAbilities().mayBuild = pl.isCreative();

			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				if (pl.getY() < pl.level().getMinBuildHeight()) {
					pl.fallDistance = -1;

					if (pl instanceof ServerPlayer serverPlayer) {
						if (!AdvancementUtil.isAdvancementCompleted(serverPlayer, AdventOfAscension.id("nowhere/root"))) {
							AoAScheduler.schedule(1, tick -> {
								PlayerUtil.resetToDefaultStatus(serverPlayer);
								InventoryUtil.clearItems(serverPlayer, AoAItems.RETURN_CRYSTAL);
								serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
								serverPlayer.connection.teleport(17.5d, 452.5d, 3.5d, 0, serverPlayer.getXRot());
							});
						}
						else {
							ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(serverPlayer);
							PositionAndRotation checkpoint = plData.storage.getActiveCheckpoint();

							if (checkpoint != null) {
								if (CheckpointBlock.isValidCheckpoint(serverPlayer.level(), checkpoint)) {
									AoAScheduler.schedule(1, tick -> {
										if (NowhereEvents.isInBossRegion(serverPlayer.blockPosition()))
											InventoryUtil.clearItems(serverPlayer, AoAItems.RETURN_CRYSTAL);

										PlayerUtil.resetToDefaultStatus(serverPlayer);

										if (!NowhereEvents.isInParkourRegion(serverPlayer.blockPosition())) {
											serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
										}
										else if (!InventoryUtil.hasItem(serverPlayer, AoAItems.RETURN_CRYSTAL)) {
											InventoryUtil.giveItemTo(serverPlayer, AoAItems.RETURN_CRYSTAL);
										}

										serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.respawn"), ChatFormatting.GREEN), true);
										checkpoint.applyToEntity(serverPlayer);
									});

									return;
								}
								else {
									plData.storage.clearActiveCheckpoint();
									serverPlayer.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.invalid"), ChatFormatting.RED));
								}
							}
							else {
								NowhereActivityPortal.Activity.RETURN.activate((ServerPlayer)pl);
							}
						}
					}
				}

				if (pl.isFallFlying())
					pl.stopFallFlying();

				if (isInFoodFreeRegion(pl.blockPosition())) {
					FoodData foodData = pl.getFoodData();

					foodData.setExhaustion(-4);
					foodData.setFoodLevel(20);
					foodData.setSaturation(20);
				}
			}
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			if (ev.getFrom() == AoADimensions.NOWHERE) {
				plData.storage.restoreStats();
				plData.storage.returnStoredItems();
				plData.storage.clearActiveCheckpoint();

				InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);
				pl.gameMode.getGameModeForPlayer().updatePlayerAbilities(pl.getAbilities());
			}
			else {
				plData.storage.saveStats();
			}
		}
	}

	public static void doDeathPrevention(final LivingDamageEvent.Pre ev, ServerPlayerDataManager plData) {
		ServerPlayer player = plData.getPlayer();

		if (player.server.isHardcore() && NowhereEvents.isInBossRegion(player.blockPosition()))
			return;

		LivingEntity killer = player.getKillCredit();

		player.getScoreboard().forAllObjectives(ObjectiveCriteria.DEATH_COUNT, player, ScoreAccess::increment);

		if (killer != null) {
			player.awardStat(Stats.ENTITY_KILLED_BY.get(killer.getType()));
			killer.awardKillScore(player, 1, ev.getContainer().getSource());
		}

		player.awardStat(Stats.DEATHS);
		player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_DEATH));
		player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

		if (!AdvancementUtil.isAdvancementCompleted(player, AdventOfAscension.id("nowhere/root"))) {
			AoAScheduler.schedule(1, tick -> {
				PlayerUtil.resetToDefaultStatus(player);
				player.connection.teleport(17.5d, 452.5d, 3.5d, 0, player.getXRot());
				InventoryUtil.clearItems(player, AoAItems.RETURN_CRYSTAL);
				PlayerUtil.getAdventPlayer(player).storage.returnStoredItems();
			});
		}
		else {
			NowhereActivityPortal.Activity.RETURN.activate(plData.getPlayer());
		}

		player.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
		ev.getContainer().setNewDamage(0);
	}

	public static void handleNowhereRightClickItem(final PlayerInteractEvent.RightClickItem ev) {
		Item item = ev.getItemStack().getItem();

		if (item instanceof MinecartItem || item instanceof BoatItem)
			ev.setCanceled(true);
	}

	public static void handleNowhereRightClickEntity(final PlayerInteractEvent.EntityInteract ev) {
		if (ev.getTarget() instanceof ItemFrame)
			ev.setCanceled(true);
	}

	public static void handleNowhereLeftClickEntity(final AttackEntityEvent ev) {
		if (ev.getTarget() instanceof ItemFrame)
			ev.setCanceled(true);
	}

	public static void handleNowhereRightClickBlock(final PlayerInteractEvent.RightClickBlock ev) {
		BlockState blockState = ev.getLevel().getBlockState(ev.getPos());
		Block block = blockState.getBlock();
		ItemStack heldStack = ev.getEntity().getItemInHand(ev.getHand());
		Item heldItem = heldStack.getItem();

		if (block == Blocks.JUKEBOX) {
			if (heldItem == Items.AIR || heldStack.has(DataComponents.JUKEBOX_PLAYABLE)) {
				ev.setUseItem(TriState.TRUE);
				ev.setUseBlock(TriState.TRUE);
				ev.getEntity().getAbilities().mayBuild = true;
			}
		}
		else if (blockState.is(AoATags.Blocks.NOWHERE_SAFE_GUI_BLOCK)) {
			ev.setUseItem(TriState.FALSE);
		}
		else if (block == Blocks.WATER_CAULDRON) {
			ev.setUseItem(TriState.FALSE);
			AoAScheduler.schedule(1, tick -> ev.getLevel().setBlock(ev.getPos(), blockState.setValue(LayeredCauldronBlock.LEVEL, LayeredCauldronBlock.MAX_FILL_LEVEL), Block.UPDATE_CLIENTS));
		}
		else if (block == AoABlocks.TEA_SINK.get()) {
			ev.setUseItem(TriState.FALSE);
			AoAScheduler.schedule(1, tick -> ev.getLevel().setBlock(ev.getPos(), blockState.setValue(TeaSink.FILLED, true), Block.UPDATE_CLIENTS));
		}
		else if (heldItem == AoAItems.LOTTO_TOTEM.get()) {
			ev.setUseItem(TriState.TRUE);
			ev.setUseBlock(TriState.FALSE);
			ev.getEntity().getAbilities().mayBuild = true;
		}
		else {
			ev.setCanceled(true);
		}
	}

	public static void handleLoot(final LivingDropsEvent ev) {
		if (!(ev.getEntity() instanceof AoABoss) || ev.isCanceled()) {
			ev.setCanceled(true);

			return;
		}

		AoAConfigs.SERVER.bossDropsScheme.get().handleDrops(ev);
	}
}
