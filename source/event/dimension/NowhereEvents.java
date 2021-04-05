package net.tslat.aoa3.event.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class NowhereEvents {
	@SubscribeEvent
	public static void onBreakAttempt(final PlayerEvent.BreakSpeed ev) {
		if (WorldUtil.isWorld(ev.getPlayer().level, AoADimensions.NOWHERE.key))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void playerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.START && WorldUtil.isWorld(ev.player.level, AoADimensions.NOWHERE.key))
			ev.player.abilities.mayBuild = ev.player.isCreative();
	}

	@SubscribeEvent
	public static void onEmptyBucketUse(final FillBucketEvent ev) {
		if (!WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

		if (relevantPlayer == null || !relevantPlayer.isCreative()) {
			ev.setCanceled(true);
			ev.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getFrom() == AoADimensions.NOWHERE.key) {
			ItemUtil.clearInventoryOfItems(ev.getPlayer(), new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		}
		else if (ev.getTo() == AoADimensions.NOWHERE.key) {
			GameRules gameRules = AoADimensions.NOWHERE.getWorld().getGameRules();

			gameRules.getRule(GameRules.RULE_DOFIRETICK).set(false, ServerLifecycleHooks.getCurrentServer());
			gameRules.getRule(GameRules.RULE_MOBGRIEFING).set(false, ServerLifecycleHooks.getCurrentServer());
			gameRules.getRule(AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS).set(false, ServerLifecycleHooks.getCurrentServer());
			gameRules.getRule(AoAGameRules.STRONGER_MOB_GRIEFING).set(false, ServerLifecycleHooks.getCurrentServer());
		}
	}

	@SubscribeEvent
	public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getPlayer().isCreative() || !WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key))
			return;

		BlockState block = ev.getWorld().getBlockState(ev.getPos());

		if (block.getBlock() == Blocks.JUKEBOX || block.getBlock() == Blocks.SOUL_CAMPFIRE || block.getBlock() == Blocks.ENDER_CHEST) {
			ev.setUseBlock(Event.Result.ALLOW);
			ev.setUseItem(Event.Result.DENY);
		}
		else {
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onLoot(final LivingDropsEvent ev) {
		if (!WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key))
			return;

		if (!(ev.getEntityLiving() instanceof PlayerEntity) && !(ev.getEntityLiving() instanceof TameableEntity)) {
			ev.getDrops().clear();
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void blockBreak(final BlockEvent.BreakEvent ev) {
		if (!WorldUtil.isWorld(ev.getPlayer().level, AoADimensions.NOWHERE.key))
			return;

		if (!ev.getPlayer().isCreative())
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void blockPlace(final BlockEvent.EntityPlaceEvent ev) {
		World world = null;

		if (ev.getWorld() instanceof World) {
			world = (World)ev.getWorld();
		}
		else if (ev.getEntity() != null) {
			world = ev.getEntity().level;
		}

		if (world == null || !WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return;

		if (!(ev.getEntity() instanceof PlayerEntity) || !((PlayerEntity)ev.getEntity()).isCreative())
			ev.setCanceled(true);
	}
}
