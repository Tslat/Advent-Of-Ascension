package net.tslat.aoa3.event;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.function.Consumer;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerState.ACTIVE;
import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.*;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public final class AoAPlayerEvents {
	public static void issueEvent(ServerPlayerEntity pl, AoAPlayerEventListener.ListenerType listener, Consumer<? super AoAPlayerEventListener> eventConsumer) {
		PlayerUtil.getAdventPlayer(pl).getListeners(listener).forEach(evListener -> {
			if (evListener.getListenerState() == ACTIVE)
				eventConsumer.accept(evListener);
		});
	}

	public static void issueEvents(ServerPlayerEntity pl, Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>>... listeners) {
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		for (Pair<AoAPlayerEventListener.ListenerType, Consumer<? super AoAPlayerEventListener>> listener : listeners) {
			plData.getListeners(listener.getFirst()).forEach(evListener -> {
				if (evListener.getListenerState() == ACTIVE)
					listener.getSecond().accept(evListener);
			});
		}
	}

	@SubscribeEvent
	static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END && ev.player instanceof ServerPlayerEntity) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.player).doPlayerTick();

			issueEvent((ServerPlayerEntity)ev.player, PLAYER_TICK, listener -> listener.handlePlayerTick(ev));
		}
	}

	@SubscribeEvent
	static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_JUMP, listener -> listener.handlePlayerJump(ev));
	}

	@SubscribeEvent
	static void onPlayerFall(final LivingFallEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_FALL, listener -> listener.handlePlayerFall(ev));
	}

	@SubscribeEvent
	static void onPlayerDeath(final LivingDeathEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, PLAYER_DEATH, listener -> listener.handlePlayerDeath(ev));

		for (Entity pl : EntityUtil.getAttackersForMob(ev.getEntityLiving(), entity -> entity != player && entity instanceof ServerPlayerEntity)) {
			issueEvent((ServerPlayerEntity)pl, ENTITY_KILL, listener -> listener.handleEntityKill(ev));
		}
	}

	@SubscribeEvent
	static void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		if (!ev.isEndConquered() && ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_RESPAWN, listener -> listener.handlePlayerRespawn(ev));
	}

	@SubscribeEvent
	static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvents((ServerPlayerEntity)ev.getPlayer(),
					Pair.of(PLAYER_LOGIN, listener -> listener.handlePlayerLogin(ev)),
					Pair.of(ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer()))));
	}

	@SubscribeEvent
	static void onPlayerLogout(final PlayerEvent.PlayerLoggedOutEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_LOGOUT, listener -> listener.handlePlayerLogout(ev));
	}

	@SubscribeEvent
	static void onPlayerDataClone(final PlayerEvent.Clone ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)ev.getPlayer(), PLAYER_CLONE, listener -> listener.handlePlayerDataClone(ev));
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ATTRIBUTE_MODIFIERS, listener -> listener.applyAttributeModifiers(PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer())));
		}
	}

	@SubscribeEvent
	static void onPlayerEquipmentChange(final LivingEquipmentChangeEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, EQUIPMENT_CHANGE, listener -> listener.handleArmourChange(ev));
	}

	@SubscribeEvent
	static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), DIMENSION_CHANGE, listener -> listener.handleDimensionChange(ev));
	}

	@SubscribeEvent
	static void onGamemodeChange(final PlayerEvent.PlayerChangeGameModeEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAMEMODE_CHANGE, listener -> listener.handleGamemodeChange(ev));
	}

	@SubscribeEvent
	static void onAttemptBlockHarvest(final PlayerEvent.HarvestCheck ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_HARVEST_ATTEMPT, listener -> listener.handleBlockHarvestAttempt(ev));
	}

	@SubscribeEvent
	static void onBlockHarvestSpeed(final PlayerEvent.BreakSpeed ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_BREAK_SPEED, listener -> listener.handleHarvestSpeedCheck(ev));
	}

	@SubscribeEvent
	static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), BLOCK_BREAK, listener -> listener.handleBlockBreak(ev));
	}

	@SubscribeEvent
	static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

		if (player instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)player, BLOCK_PLACE, listener -> listener.handleBlockPlacement(ev));
	}

	@SubscribeEvent
	static void onItemThrow(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_THROW, listener -> listener.handleItemToss(ev));
	}

	@SubscribeEvent
	static void onLevelChange(final PlayerLevelChangeEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), LEVEL_CHANGE, listener -> listener.handleLevelChange(ev));
	}

	@SubscribeEvent
	static void onXpGain(final PlayerChangeXpEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAIN_SKILL_XP, listener -> listener.handleSkillXpGain(ev));
	}

	@SubscribeEvent
	static void onVanillaXpGain(final PlayerXpEvent.XpChange ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), GAIN_VANILLA_XP, listener -> listener.handleVanillaXpGain(ev));
	}

	@SubscribeEvent
	static void onItemCraft(final PlayerEvent.ItemCraftedEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_CRAFT, listener -> listener.handleItemCraft(ev));
	}

	@SubscribeEvent
	static void onItemSmelt(final PlayerEvent.ItemSmeltedEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), ITEM_SMELT, listener -> listener.handleItemSmelt(ev));
	}

	@SubscribeEvent
	static void onPotionApplied(final PotionEvent.PotionAddedEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getEntityLiving(), POTION_APPLIED, listener -> listener.handleAppliedPotion(ev));
	}

	@SubscribeEvent
	static void onCriticalHit(final CriticalHitEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayerEntity)
			issueEvent((ServerPlayerEntity)ev.getPlayer(), CRITICAL_HIT, listener -> listener.handleCriticalHit(ev));
	}

	@SubscribeEvent
	static void onPreAttack(final LivingAttackEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_BEFORE, listener -> listener.handlePreIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_BEFORE, listener -> listener.handlePreOutgoingAttack(ev));
		}
	}

	@SubscribeEvent
	static void onAttack(final LivingHurtEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_DURING, listener -> listener.handleIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_DURING, listener -> listener.handleOutgoingAttack(ev));
		}
	}

	@SubscribeEvent
	static void onPostAttack(final LivingDamageEvent ev) {
		PlayerEntity player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntityLiving());

		if (player instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, INCOMING_ATTACK_AFTER, listener -> listener.handlePostIncomingAttack(ev));
		}
		else if ((player = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity())) instanceof ServerPlayerEntity) {
			issueEvent((ServerPlayerEntity)player, OUTGOING_ATTACK_AFTER, listener -> listener.handlePostOutgoingAttack(ev));
		}
	}

	public static void onKeyPress(final ServerPlayerEntity player, final List<String> abilityListeners) {
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		for (String str : abilityListeners) {
			AoAAbility.Instance ability = plData.getAbility(str);

			if (ability != null)
				ability.handleKeyInput();
		}
	}
}
