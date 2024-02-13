package net.tslat.aoa3.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.event.custom.events.*;
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
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.PlayerTickEvent.class, AoAPlayerEvents::onPlayerTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, AoAPlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, AoAPlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDeathEvent.class, AoAPlayerEvents::onEntityDeath);
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
		forgeBus.addListener(EventPriority.NORMAL, false, ItemCraftingEvent.class, AoAPlayerEvents::onItemCrafting);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.ItemCraftedEvent.class, AoAPlayerEvents::onItemCrafted);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemSmeltingEvent.class, AoAPlayerEvents::onItemSmelting);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.ItemSmeltedEvent.class, AoAPlayerEvents::onItemSmelted);
		forgeBus.addListener(EventPriority.NORMAL, false, GrindstoneResultEvent.class, AoAPlayerEvents::onGrindstoneModifying);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, AoAPlayerEvents::onItemFished);
		forgeBus.addListener(EventPriority.NORMAL, false, HaulingRodPullEntityEvent.class, AoAPlayerEvents::onHaulingRodPullEntity);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerInteractEvent.EntityInteractSpecific.class, AoAPlayerEvents::onEntityInteract);
		forgeBus.addListener(EventPriority.NORMAL, false, MobEffectEvent.Added.class, AoAPlayerEvents::onPotionApplied);
		forgeBus.addListener(EventPriority.NORMAL, false, CriticalHitEvent.class, AoAPlayerEvents::onCriticalHit);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingChangeTargetEvent.class, AoAPlayerEvents::onEntityTargeted);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingAttackEvent.class, AoAPlayerEvents::onPreAttack);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingHurtEvent.class, AoAPlayerEvents::onAttack);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDamageEvent.class, AoAPlayerEvents::onPostAttack);
	}

	public static void issueEvent(ServerPlayer pl, AoAPlayerEventListener.ListenerType listener, Consumer<? super AoAPlayerEventListener> eventConsumer) {
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

	public static void issueEvents(ServerPlayer pl, Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>>... listeners) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		for (Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>> listener : listeners) {
			plData.getListeners(listener.getFirst()).forEach(evListener -> {
				if (evListener.getListenerState() == ACTIVE)
					listener.getSecond().accept(evListener);
			});
		}
	}

	private static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END && ev.player instanceof ServerPlayer pl) {
			PlayerUtil.getAdventPlayer(pl).doPlayerTick();

			issueEvent(pl, PLAYER_TICK, listener -> listener.handlePlayerTick(ev));
		}
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, PLAYER_JUMP, listener -> listener.handlePlayerJump(ev));
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, PLAYER_FALL, listener -> listener.handlePlayerFall(ev));
	}

	private static void onEntityDeath(final LivingDeathEvent ev) {
		LivingEntity target = ev.getEntity();

		if (target instanceof ServerPlayer pl)
			issueEvent(pl, PLAYER_DEATH, listener -> listener.handlePlayerDeath(ev));

		for (Entity attacker : EntityUtil.getAttackersForMob(target, entity -> entity != target && entity instanceof ServerPlayer)) {
			issueEvent((ServerPlayer)attacker, ENTITY_KILL, listener -> listener.handleEntityKill(ev));
		}
	}

	private static void onAnimalBreed(final BabyEntitySpawnEvent ev) {
		if (ev.getCausedByPlayer() instanceof ServerPlayer pl)
			issueEvent(pl, ANIMAL_BREED, listener -> listener.handleAnimalBreed(ev));
	}

	private static void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		if (!ev.isEndConquered() && ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, PLAYER_RESPAWN, listener -> listener.handlePlayerRespawn(ev));
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvents(pl,
					Pair.of(PLAYER_LOGIN, listener -> listener.handlePlayerLogin(ev)),
					Pair.of(ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer(pl))));
	}

	private static void onPlayerLogout(final PlayerEvent.PlayerLoggedOutEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, PLAYER_LOGOUT, listener -> listener.handlePlayerLogout(ev));
	}

	private static void onPlayerDataClone(final PlayerEvent.Clone ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, PLAYER_CLONE, listener -> listener.handlePlayerDataClone(ev));
			issueEvent(pl, ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer(pl)));
		}
	}

	private static void onPlayerEquipmentChange(final LivingEquipmentChangeEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, EQUIPMENT_CHANGE, listener -> listener.handleArmourChange(ev));
	}

	private static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, DIMENSION_CHANGE, listener -> listener.handleDimensionChange(ev));
	}

	private static void onGamemodeChange(final PlayerEvent.PlayerChangeGameModeEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, GAMEMODE_CHANGE, listener -> listener.handleGamemodeChange(ev));
	}

	private static void onAttemptBlockHarvest(final PlayerEvent.HarvestCheck ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, BLOCK_HARVEST_ATTEMPT, listener -> listener.handleBlockHarvestAttempt(ev));
	}

	private static void onBlockHarvestSpeed(final PlayerEvent.BreakSpeed ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, BLOCK_BREAK_SPEED, listener -> listener.handleHarvestSpeedCheck(ev));
		}
		else {
			issueClientEvent(BLOCK_BREAK_SPEED, listener -> listener.handleHarvestSpeedCheck(ev));
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayer pl)
			issueEvent(pl, BLOCK_BREAK, listener -> listener.handleBlockBreak(ev));
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, BLOCK_PLACE, listener -> listener.handleBlockPlacement(ev));
	}

	private static void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, BLOCK_INTERACT, listener -> listener.handleBlockInteraction(ev));
	}

	private static void onItemThrow(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayer pl)
			issueEvent(pl, ITEM_THROW, listener -> listener.handleItemToss(ev));
	}

	private static void onLevelChange(final PlayerLevelChangeEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, LEVEL_CHANGE, listener -> listener.handleLevelChange(ev));
	}

	private static void onXpGain(final PlayerChangeXpEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, GAIN_SKILL_XP, listener -> listener.handleSkillXpGain(ev));
	}

	private static void onVanillaXpGain(final PlayerXpEvent.XpChange ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, GAIN_VANILLA_XP, listener -> listener.handleVanillaXpGain(ev));
	}

	private static void onItemCrafting(final ItemCraftingEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, ITEM_CRAFTING, listener -> listener.handleItemCrafting(ev));
	}

	private static void onItemCrafted(final PlayerEvent.ItemCraftedEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, ITEM_CRAFTED, listener -> listener.handleItemCrafted(ev));
		}
		else {
			issueClientEvent(ITEM_CRAFTED, listener -> listener.handleItemCrafted(ev));
		}
	}

	private static void onItemSmelting(final ItemSmeltingEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, ITEM_SMELTING, listener -> listener.handleItemSmelting(ev));
		}
		else {
			issueClientEvent(ITEM_SMELTING, listener -> listener.handleItemSmelting(ev));
		}
	}

	private static void onItemSmelted(final PlayerEvent.ItemSmeltedEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, ITEM_SMELTED, listener -> listener.handleItemSmelted(ev));
		}
		else {
			issueClientEvent(ITEM_SMELTED, listener -> listener.handleItemSmelted(ev));
		}
	}

	private static void onGrindstoneModifying(final GrindstoneResultEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, ITEM_GRINDSTONING, listener -> listener.handleGrindstoneModifying(ev));
		}
		else {
			issueClientEvent(ITEM_GRINDSTONING, listener -> listener.handleGrindstoneModifying(ev));
		}
	}

	private static void onItemFished(final ItemFishedEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, FISHED_ITEM, listener -> listener.handleItemFished(ev, ev instanceof HaulingItemFishedEvent));
	}

	private static void onHaulingRodPullEntity(final HaulingRodPullEntityEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, HAULING_ROD_PULL_ENTITY, listener -> listener.handleHaulingRodPullEntity(ev));
	}

	private static void onEntityInteract(final PlayerInteractEvent.EntityInteractSpecific ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, ENTITY_INTERACT, listener -> listener.handleEntityInteraction(ev));
	}

	private static void onPotionApplied(final MobEffectEvent.Added ev) {
		if (ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, POTION_APPLIED, listener -> listener.handleAppliedPotion(ev));
	}

	private static void onCriticalHit(final CriticalHitEvent ev) {
		if (ev.isVanillaCritical() && ev.getEntity() instanceof ServerPlayer pl)
			issueEvent(pl, CRITICAL_HIT, listener -> listener.handleCriticalHit(ev));
	}

	private static void onEntityTargeted(final LivingChangeTargetEvent ev) {
		if (ev.getNewTarget() instanceof ServerPlayer pl)
			issueEvent(pl, ENTITY_TARGET, listener -> listener.handleEntityTarget(ev));
	}

	private static void onPreAttack(final LivingAttackEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, INCOMING_ATTACK_BEFORE, listener -> listener.handlePreIncomingAttack(ev));
		}
		else if (ev.getSource().getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, OUTGOING_ATTACK_BEFORE, listener -> listener.handlePreOutgoingAttack(ev));
		}
	}

	private static void onAttack(final LivingHurtEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, INCOMING_ATTACK_DURING, listener -> listener.handleIncomingAttack(ev));
		}
		else if (ev.getSource().getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, OUTGOING_ATTACK_DURING, listener -> listener.handleOutgoingAttack(ev));
		}
	}

	private static void onPostAttack(final LivingDamageEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, INCOMING_ATTACK_AFTER, listener -> listener.handlePostIncomingAttack(ev));
		}
		else if (ev.getSource().getEntity() instanceof ServerPlayer pl) {
			issueEvent(pl, OUTGOING_ATTACK_AFTER, listener -> listener.handlePostOutgoingAttack(ev));
		}
	}

	public static void onKeyPress(final ServerPlayer player, final List<String> abilityListeners) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		for (String str : abilityListeners) {
			AoAAbility.Instance ability = plData.getAbility(str);

			if (ability != null)
				ability.handleKeyInput();
		}
	}

	public static void handleCustomInteraction(final ServerPlayer player, final String interactionType, final Object data) {
		issueEvent(player, CUSTOM, listener -> listener.handleCustomInteraction(interactionType, data));
	}
}
