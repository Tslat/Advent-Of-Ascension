package net.tslat.aoa3.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.function.Consumer;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.ACTIVE;
import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.*;

public final class AoAPlayerEvents {
	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.PlayerTickEvent.class, AoAPlayerEvents::onPlayerTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, AoAPlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, AoAPlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, AoAPlayerEvents::onPlayerDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, BabyEntitySpawnEvent.class, AoAPlayerEvents::onAnimalBreed);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerRespawnEvent.class, AoAPlayerEvents::onPlayerRespawn);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, AoAPlayerEvents::onPlayerLogin);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedOutEvent.class, AoAPlayerEvents::onPlayerLogout);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.Clone.class, AoAPlayerEvents::onPlayerDataClone);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEquipmentChangeEvent.class, AoAPlayerEvents::onPlayerEquipmentChange);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangedDimensionEvent.class, AoAPlayerEvents::onDimensionChange);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangeGameModeEvent.class, AoAPlayerEvents::onGamemodeChange);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.HarvestCheck.class, AoAPlayerEvents::onAttemptBlockHarvest);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.BreakSpeed.class, AoAPlayerEvents::onBlockHarvestSpeed);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.BreakEvent.class, AoAPlayerEvents::onBlockBreak);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.EntityPlaceEvent.class, AoAPlayerEvents::onBlockPlace);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerInteractEvent.RightClickBlock.class, AoAPlayerEvents::onBlockInteract);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTossEvent.class, AoAPlayerEvents::onItemThrow);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerLevelChangeEvent.class, AoAPlayerEvents::onLevelChange);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerChangeXpEvent.class, AoAPlayerEvents::onXpGain);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerXpEvent.XpChange.class, AoAPlayerEvents::onVanillaXpGain);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.ItemCraftedEvent.class, AoAPlayerEvents::onItemCraft);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.ItemSmeltedEvent.class, AoAPlayerEvents::onItemSmelt);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, AoAPlayerEvents::onItemFished);
		forgeBus.addListener(EventPriority.NORMAL, false, HaulingRodPullEntityEvent.class, AoAPlayerEvents::onHaulingRodPullEntity);
		forgeBus.addListener(EventPriority.NORMAL, false, PotionEvent.PotionAddedEvent.class, AoAPlayerEvents::onPotionApplied);
		forgeBus.addListener(EventPriority.NORMAL, false, CriticalHitEvent.class, AoAPlayerEvents::onCriticalHit);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingSetAttackTargetEvent.class, AoAPlayerEvents::onEntityTargeted);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingAttackEvent.class, AoAPlayerEvents::onPreAttack);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingHurtEvent.class, AoAPlayerEvents::onAttack);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDamageEvent.class, AoAPlayerEvents::onPostAttack);
	}

	public static void issueEvent(ServerPlayerEntity pl, AoAPlayerEventListener.ListenerType listener, Consumer<? super AoAPlayerEventListener> eventConsumer) {
		PlayerUtil.getAdventPlayer(pl).getListeners(listener).forEach(evListener -> {
			if (evListener.getListenerState() == ACTIVE)
				eventConsumer.accept(evListener);
		});
	}

	public static void issueClientEvent(AoAPlayerEventListener.ListenerType listener, Consumer<AoAPlayerEventListener> eventConsumer) {
		ClientPlayerDataManager.get().getListeners(listener).forEach(evListener -> {
			if (evListener.getListenerState() == ACTIVE)
				eventConsumer.accept(evListener);
		});
	}

	public static void issueEvents(ServerPlayerEntity pl, Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>>... listeners) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		for (Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>> listener : listeners) {
			plData.getListeners(listener.getFirst()).forEach(evListener -> {
				if (evListener.getListenerState() == ACTIVE)
					listener.getSecond().accept(evListener);
			});
		}
	}

	private static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END && ev.player instanceof ServerPlayerEntity) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.player).doPlayerTick();

			issueEvent((ServerPlayerEntity)ev.player, PLAYER_TICK, listener -> listener.handlePlayerTick(ev));
		}
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_JUMP, listener -> listener.handlePlayerJump(ev));
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_FALL, listener -> listener.handlePlayerFall(ev));
	}

	private static void onPlayerDeath(final LivingDeathEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_DEATH, listener -> listener.handlePlayerDeath(ev));

		for (Entity pl : EntityUtil.getAttackersForMob(ev.getEntityLiving(), entity -> entity != player && entity instanceof ServerPlayerEntity)) {
			issueEvent((ServerPlayerEntity)pl, ENTITY_KILL, listener -> listener.handleEntityKill(ev));
		}
	}

	private static void onAnimalBreed(final BabyEntitySpawnEvent ev) {
		if (ev.getCausedByPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getCausedByPlayer(), ANIMAL_BREED, listener -> listener.handleAnimalBreed(ev));
	}

	private static void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		if (!ev.isEndConquered() && ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_RESPAWN, listener -> listener.handlePlayerRespawn(ev));
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvents((ServerPlayerEntity)ev.getPlayer(),
					Pair.of(PLAYER_LOGIN, listener -> listener.handlePlayerLogin(ev)),
					Pair.of(ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer()))));
	}

	private static void onPlayerLogout(final PlayerEvent.PlayerLoggedOutEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_LOGOUT, listener -> listener.handlePlayerLogout(ev));
	}

	private static void onPlayerDataClone(final PlayerEvent.Clone ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_CLONE, listener -> listener.handlePlayerDataClone(ev));
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer())));
		}
	}

	private static void onPlayerEquipmentChange(final LivingEquipmentChangeEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, EQUIPMENT_CHANGE, listener -> listener.handleArmourChange(ev));
	}

	private static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), DIMENSION_CHANGE, listener -> listener.handleDimensionChange(ev));
	}

	private static void onGamemodeChange(final PlayerEvent.PlayerChangeGameModeEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAMEMODE_CHANGE, listener -> listener.handleGamemodeChange(ev));
	}

	private static void onAttemptBlockHarvest(final PlayerEvent.HarvestCheck ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_HARVEST_ATTEMPT, listener -> listener.handleBlockHarvestAttempt(ev));
	}

	private static void onBlockHarvestSpeed(final PlayerEvent.BreakSpeed ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_BREAK_SPEED, listener -> listener.handleHarvestSpeedCheck(ev));
		}
		else {
			issueClientEvent(BLOCK_BREAK_SPEED, listener -> listener.handleHarvestSpeedCheck(ev));
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_BREAK, listener -> listener.handleBlockBreak(ev));
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, BLOCK_PLACE, listener -> listener.handleBlockPlacement(ev));
	}

	private static void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, BLOCK_INTERACT, listener -> listener.handleBlockInteraction(ev));
	}

	private static void onItemThrow(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_THROW, listener -> listener.handleItemToss(ev));
	}

	private static void onLevelChange(final PlayerLevelChangeEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), LEVEL_CHANGE, listener -> listener.handleLevelChange(ev));
	}

	private static void onXpGain(final PlayerChangeXpEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAIN_SKILL_XP, listener -> listener.handleSkillXpGain(ev));
	}

	private static void onVanillaXpGain(final PlayerXpEvent.XpChange ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAIN_VANILLA_XP, listener -> listener.handleVanillaXpGain(ev));
	}

	private static void onItemCraft(final PlayerEvent.ItemCraftedEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_CRAFT, listener -> listener.handleItemCraft(ev));
		}
		else {
			issueClientEvent(ITEM_CRAFT, listener -> listener.handleItemCraft(ev));
		}
	}

	private static void onItemSmelt(final PlayerEvent.ItemSmeltedEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_SMELT, listener -> listener.handleItemSmelt(ev));
		}
		else {
			issueClientEvent(ITEM_SMELT, listener -> listener.handleItemSmelt(ev));
		}
	}

	private static void onItemFished(final ItemFishedEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), FISHED_ITEM, listener -> listener.handleItemFished(ev, ev instanceof HaulingItemFishedEvent));
	}

	private static void onHaulingRodPullEntity(final HaulingRodPullEntityEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), HAULING_ROD_PULL_ENTITY, listener -> listener.handleHaulingRodPullEntity(ev));
	}

	private static void onPotionApplied(final PotionEvent.PotionAddedEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getEntityLiving(), POTION_APPLIED, listener -> listener.handleAppliedPotion(ev));
	}

	private static void onCriticalHit(final CriticalHitEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), CRITICAL_HIT, listener -> listener.handleCriticalHit(ev));
	}

	private static void onEntityTargeted(final LivingSetAttackTargetEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getTarget());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, ENTITY_TARGET, listener -> listener.handleEntityTarget(ev));
	}

	private static void onPreAttack(final LivingAttackEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_BEFORE, listener -> listener.handlePreIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_BEFORE, listener -> listener.handlePreOutgoingAttack(ev));
		}
	}

	private static void onAttack(final LivingHurtEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_DURING, listener -> listener.handleIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_DURING, listener -> listener.handleOutgoingAttack(ev));
		}
	}

	private static void onPostAttack(final LivingDamageEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_AFTER, listener -> listener.handlePostIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_AFTER, listener -> listener.handlePostOutgoingAttack(ev));
		}
	}

	public static void onKeyPress(final ServerPlayerEntity player, final List<String> abilityListeners) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		for (String str : abilityListeners) {
			AoAAbility.Instance ability = plData.getAbility(str);

			if (ability != null)
				ability.handleKeyInput();
		}
	}

	public static void handleCustomInteraction(final ServerPlayerEntity player, final String interactionType, final Object data) {
		issueEvent(player, CUSTOM, listener -> listener.handleCustomInteraction(interactionType, data));
	}
}
