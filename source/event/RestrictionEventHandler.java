package net.tslat.aoa3.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.object.entity.base.AoATrader;
import net.tslat.aoa3.object.item.tablet.TabletItem;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class RestrictionEventHandler {
	public static void preInit() {
		handleEventIf(PlayerInteractEvent.RightClickBlock.class, RestrictionEventHandler::handleRightClickBlock, ev -> EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getPlayer()));
		cancelEventIf(EntityTeleportEvent.EnderPearl.class, ev -> ev.getTargetY() >= ev.getPlayer().level.dimensionType().logicalHeight());
		cancelEventIf(LivingConversionEvent.Pre.class, ev -> ev.getEntityLiving() instanceof AoATrader);
		cancelEventIf(PlayerEvent.BreakSpeed.class, ev -> WorldUtil.isWorld(ev.getPlayer().level, AoADimensions.NOWHERE.key));
		cancelEventIf(BlockEvent.FluidPlaceBlockEvent.class, ev -> WorldUtil.isWorld((World)ev.getWorld(), AoADimensions.NOWHERE.key));
		cancelEventIf(EntityMobGriefingEvent.class, ev -> ev.getEntity() != null && WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key));
		cancelEventIf(LivingDropsEvent.class, ev -> WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key));
		cancelEventIf(FillBucketEvent.class, ev -> WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getPlayer()));
		cancelEventIf(BlockEvent.BreakEvent.class, ev -> EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getPlayer()) && (WorldUtil.isWorld((World)ev.getWorld(), AoADimensions.NOWHERE.key) || !AoASkillReqReloadListener.canBreakBlock(PlayerUtil.getAdventPlayer(ev.getPlayer()), ev.getState().getBlock())));
		cancelEventIf(BlockEvent.EntityPlaceEvent.class, ev -> EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()) && (WorldUtil.isWorld((World)ev.getWorld(), AoADimensions.NOWHERE.key) || (!AoASkillReqReloadListener.canPlaceBlock(PlayerUtil.getAdventPlayer((PlayerEntity)ev.getEntity()), ev.getState().getBlock()))));

		handleEventIf(ExplosionEvent.Detonate.class,
				ev -> ev.getAffectedBlocks().clear(),
				ev -> WorldUtil.isWorld(ev.getWorld(), AoADimensions.NOWHERE.key));

		handleEventIf(EntityTeleportEvent.ChorusFruit.class, ev -> {
			cancelEvent(ev);
			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.chorusFruit"));
			}, ev -> WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));

		handleEventIf(EntityTeleportEvent.EnderPearl.class, ev -> {
			cancelEvent(ev);
			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.enderPearl"));
			}, ev -> WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));

		handleEventIf(EntityTeleportEvent.TeleportCommand.class, ev -> {
			cancelEvent(ev);
			PlayerUtil.notifyPlayer((PlayerEntity)ev.getEntity(), new TranslationTextComponent("message.feedback.nowhere.teleport"));
			}, ev -> WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));

		handleEventIf(PlayerInteractEvent.RightClickBlock.class,
				RestrictionEventHandler::handleNowhereRightClickBlock,
				ev -> WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
	}

	private static void handleNowhereRightClickBlock(PlayerInteractEvent.RightClickBlock ev) {
		BlockState block = ev.getWorld().getBlockState(ev.getPos());
		Item heldItem = ev.getPlayer().getItemInHand(ev.getHand()).getItem();

		if (block.getBlock() == Blocks.JUKEBOX) {
			if (heldItem == Items.AIR || heldItem instanceof MusicDiscItem) {
				ev.setUseItem(Event.Result.ALLOW);
				ev.setUseBlock(Event.Result.ALLOW);
				ev.getPlayer().abilities.mayBuild = true;
			}
		}
		else if (block.getBlock() == Blocks.SOUL_CAMPFIRE || block.getBlock() == Blocks.ENDER_CHEST) {
			ev.setUseItem(Event.Result.DENY);
		}
		else if (heldItem instanceof TabletItem || heldItem == AoAItems.LOTTO_TOTEM.get()) {
			ev.setUseItem(Event.Result.ALLOW);
			ev.setUseBlock(Event.Result.DENY);
			ev.getPlayer().abilities.mayBuild = true;
		}
		else {
			ev.setCanceled(true);
		}
	}

	private static void handleRightClickBlock(final PlayerInteractEvent.RightClickBlock ev) {
		World world = ev.getWorld();

		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			ev.setCanceled(true);

			return;
		}

		PlayerDataManager plData = PlayerUtil.getAdventPlayer(ev.getPlayer());

		if (!AoASkillReqReloadListener.canInteractWith(plData, world.getBlockState(ev.getPos()).getBlock()))
			ev.setUseBlock(Event.Result.DENY);

		Item item = ev.getItemStack().getItem();

		if (item instanceof BlockItem && !AoASkillReqReloadListener.canPlaceBlock(plData, ((BlockItem)item).getBlock()))
			ev.setUseItem(Event.Result.DENY);
	}

	private static <T extends Event> void handleEvent(Class<T> eventClass, Consumer<T> handler) {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, false, eventClass, handler);
	}

	private static <T extends Event> void handleEventIf(Class<T> eventClass, Consumer<T> handler, Predicate<T> basePredicate, Predicate<T>... predicates) {
		handleEvent(eventClass, wrapEventConditionally(or(basePredicate, predicates), handler));
	}

	private static <T extends Event> void cancelEventIf(Class<T> eventClass, Predicate<T> basePredicate, Predicate<T>... predicates) {
		handleEvent(eventClass, wrapEventConditionally(or(basePredicate, predicates), RestrictionEventHandler::cancelEvent));
	}

	private static <T extends Event> void cancelEvent(T ev) {
		if (ev.isCancelable())
			ev.setCanceled(true);

		if (ev.hasResult())
			ev.setResult(Event.Result.DENY);
	}

	private static <T extends Event> Predicate<T> and(Predicate<T> basePredicate, Predicate<T>... predicates) {
		for (Predicate<T> predicate : predicates) {
			basePredicate = basePredicate.and(predicate);
		}

		return basePredicate;
	}

	private static <T extends Event> Predicate<T> or(Predicate<T> basePredicate, Predicate<T>... predicates) {
		for (Predicate<T> predicate : predicates) {
			basePredicate = basePredicate.or(predicate);
		}

		return basePredicate;
	}

	private static <T extends Event> Consumer<T> wrapEventConditionally(Predicate<T> predicate, Consumer<T> handler) {
		return t -> {
			if (predicate.test(t))
				handler.accept(t);
		};
	}
}
