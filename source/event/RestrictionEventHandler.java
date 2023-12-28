package net.tslat.aoa3.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class RestrictionEventHandler {
	public static void preInit() {
		cancelEventIf(EntityTeleportEvent.EnderPearl.class, ev -> ev.getTargetY() >= ev.getEntity().level().dimensionType().logicalHeight());
		cancelEventIf(LivingConversionEvent.Pre.class, ev -> ev.getEntity() instanceof AoATrader);
		cancelEventIf(PlayerEvent.BreakSpeed.class, ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key));
		cancelEventIf(BlockEvent.FluidPlaceBlockEvent.class, ev -> WorldUtil.isWorld((Level)ev.getLevel(), AoADimensions.NOWHERE.key));
		cancelEventIf(EntityMobGriefingEvent.class, ev -> ev.getEntity() != null && WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key));
		cancelEventIf(FillBucketEvent.class, ev -> WorldUtil.isWorld(ev.getLevel(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
		cancelEventIf(BlockEvent.BreakEvent.class, ev -> EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getPlayer()) && (WorldUtil.isWorld((Level)ev.getLevel(), AoADimensions.NOWHERE.key) || !AoASkillReqReloadListener.canBreakBlock(PlayerUtil.getAdventPlayer(ev.getPlayer()), ev.getState().getBlock(), true)));
		cancelEventIf(BlockEvent.EntityPlaceEvent.class, ev -> EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()) && (WorldUtil.isWorld((Level)ev.getLevel(), AoADimensions.NOWHERE.key) || (!AoASkillReqReloadListener.canPlaceBlock(PlayerUtil.getAdventPlayer((Player)ev.getEntity()), ev.getState().getBlock(), true))));

		handleEventIf(PlayerInteractEvent.RightClickBlock.class,
				RestrictionEventHandler::handleRightClickBlock,
				ev -> !WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));

		handleEventIf(PlayerInteractEvent.RightClickBlock.class,
				NowhereEvents::handleNowhereRightClickBlock,
				ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
		handleEventIf(PlayerInteractEvent.EntityInteract.class,
				NowhereEvents::handleNowhereRightClickEntity,
				ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
		handleEventIf(AttackEntityEvent.class,
				NowhereEvents::handleNowhereLeftClickEntity,
				ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
		handleEventIf(PlayerInteractEvent.RightClickItem.class,
				NowhereEvents::handleNowhereRightClickItem,
				ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()));
		handleEventIf(EntityTeleportEvent.class, ev -> {
			cancelEvent(ev);
			PlayerUtil.notifyPlayer((Player)ev.getEntity(), Component.translatable(LocaleUtil.createFeedbackLocaleKey("nowhere.teleport")));
		}, ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key) && EntityUtil.Predicates.SURVIVAL_PLAYER.test(ev.getEntity()) && !(ev instanceof EntityTeleportEvent.TeleportCommand) && !(ev instanceof EntityTeleportEvent.SpreadPlayersCommand));
		handleEventIf(ExplosionEvent.Detonate.class,
				ev -> ev.getAffectedBlocks().clear(),
				ev -> WorldUtil.isWorld(ev.getLevel(), AoADimensions.NOWHERE.key));
		handleEventIf(LivingDropsEvent.class,
				NowhereEvents::handleLoot,
				ev -> WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE.key));
	}

	private static void handleRightClickBlock(final PlayerInteractEvent.RightClickBlock ev) {
		Level world = ev.getLevel();

		PlayerDataManager plData = PlayerUtil.getAdventPlayer(ev.getEntity());

		if (!AoASkillReqReloadListener.canInteractWith(plData, world.getBlockState(ev.getPos()).getBlock(), true))
			ev.setUseBlock(Event.Result.DENY);

		Item item = ev.getItemStack().getItem();

		if (item instanceof BlockItem && !AoASkillReqReloadListener.canPlaceBlock(plData, ((BlockItem)item).getBlock(), true))
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
