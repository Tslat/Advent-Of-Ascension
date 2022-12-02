package net.tslat.aoa3.event.dimension;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.block.functional.portal.NowhereActivityPortal;
import net.tslat.aoa3.content.block.functional.utility.TeaSink;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;

public final class NowhereEvents {
	public static boolean isInParkourRegion(BlockPos pos) {
		return pos.getX() > 1000 && pos.getZ() > 1000;
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

	public static void doPlayerTick(final TickEvent.PlayerTickEvent ev) {
		Player pl = ev.player;

		if (ev.phase == TickEvent.Phase.START) {
			if (pl instanceof ServerPlayer serverPl) {
				if (isInParkourRegion(serverPl.blockPosition())) {
					if (PlayerUtil.shouldPlayerBeAffected(serverPl))
						PlayerUtil.getAdventPlayer(serverPl).setInAbilityLockRegion();
				}
				else {
					PlayerUtil.getAdventPlayer(serverPl).leaveAbilityLockRegion();
				}
			}
		}
		else {
			pl.getAbilities().mayBuild = pl.isCreative();

			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				if (pl.getY() < pl.level.getMinBuildHeight()) {
					pl.fallDistance = -1;

					if (pl instanceof ServerPlayer)
						NowhereActivityPortal.Activity.RETURN.activate((ServerPlayer)pl);
				}

				if (pl.isFallFlying())
					pl.stopFallFlying();

				if (isInFoodFreeRegion(pl.blockPosition())) {
					FoodData foodData = pl.getFoodData();

					foodData.setFoodLevel(20);
					foodData.setExhaustion(0);
					foodData.setSaturation(20);
				}
			}
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)ev.getEntity());

		plData.returnItemStorage();
		plData.clearCheckpoint();

		ItemUtil.clearInventoryOfItems(ev.getEntity(), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		((ServerPlayer)ev.getEntity()).gameMode.getGameModeForPlayer().updatePlayerAbilities(ev.getEntity().getAbilities());
	}

	public static void doDeathPrevention(final LivingDamageEvent ev, ServerPlayerDataManager plData) {
		ServerPlayer player = plData.player();
		LivingEntity killer = player.getKillCredit();

		player.getScoreboard().forAllObjectives(ObjectiveCriteria.DEATH_COUNT, player.getScoreboardName(), Score::increment);

		if (killer != null) {
			player.awardStat(Stats.ENTITY_KILLED_BY.get(killer.getType()));
			killer.awardKillScore(player, 1, ev.getSource());
		}

		player.awardStat(Stats.DEATHS);
		player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_DEATH));
		player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

		if (isInBossRegion(player.blockPosition()) && !AdvancementUtil.isAdvancementCompleted(player, AdventOfAscension.id("nowhere/root"))) {
			AoAScheduler.scheduleSyncronisedTask(() -> {
				PlayerUtil.resetToDefaultStatus(player);
				player.connection.teleport(17.5d, 452.5d, 3.5d, 0, player.getXRot());
				ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
				PlayerUtil.getAdventPlayer(player).returnItemStorage();
			}, 1);
		}
		else {
			NowhereActivityPortal.Activity.RETURN.activate(plData.player());
		}

		player.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
		ev.setCanceled(true);
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
		Item heldItem = ev.getEntity().getItemInHand(ev.getHand()).getItem();

		if (block == Blocks.JUKEBOX) {
			if (heldItem == Items.AIR || heldItem instanceof RecordItem) {
				ev.setUseItem(Event.Result.ALLOW);
				ev.setUseBlock(Event.Result.ALLOW);
				ev.getEntity().getAbilities().mayBuild = true;
			}
		}
		else if (TagUtil.isTaggedAs(block, AoATags.Blocks.NOWHERE_SAFE_GUI_BLOCK)) {
			ev.setUseItem(Event.Result.DENY);
		}
		else if (block == Blocks.WATER_CAULDRON) {
			ev.setUseItem(Event.Result.DENY);
			AoAScheduler.scheduleSyncronisedTask(() -> ev.getLevel().setBlock(ev.getPos(), blockState.setValue(LayeredCauldronBlock.LEVEL, LayeredCauldronBlock.MAX_FILL_LEVEL), Block.UPDATE_CLIENTS), 1);
		}
		else if (block == AoABlocks.TEA_SINK.get()) {
			ev.setUseItem(Event.Result.DENY);
			AoAScheduler.scheduleSyncronisedTask(() -> ev.getLevel().setBlock(ev.getPos(), blockState.setValue(TeaSink.FILLED, true), Block.UPDATE_CLIENTS), 1);
		}
		else if (heldItem instanceof TabletItem || heldItem == AoAItems.LOTTO_TOTEM.get()) {
			ev.setUseItem(Event.Result.ALLOW);
			ev.setUseBlock(Event.Result.DENY);
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
