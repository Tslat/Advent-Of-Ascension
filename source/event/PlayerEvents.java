package net.tslat.aoa3.event;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.misc.CheckpointBlock;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.content.item.misc.ReservedItem;
import net.tslat.aoa3.content.item.tool.misc.ExpFlask;
import net.tslat.aoa3.content.item.weapon.sword.BaseSword;
import net.tslat.aoa3.event.dimension.LelyetiaEvents;
import net.tslat.aoa3.event.dimension.LunalusEvents;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.event.dimension.VoxPondsEvents;
import net.tslat.aoa3.library.object.PositionAndRotation;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.apache.logging.log4j.Level;

import java.util.UUID;

public class PlayerEvents {
	public static void preInit() {
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.PlayerTickEvent.class, PlayerEvents::onPlayerTick);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, PlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingAttackEvent.class, PlayerEvents::onPlayerHit);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingHurtEvent.class, PlayerEvents::onPlayerHurt);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDamageEvent.class, PlayerEvents::onPlayerDamaged);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, PlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDeathEvent.class, PlayerEvents::onEntityDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.BreakEvent.class, PlayerEvents::onBlockBreak);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.EntityPlaceEvent.class, PlayerEvents::onBlockPlace);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, PlayerEvents::onPlayerLogin);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTossEvent.class, PlayerEvents::onItemToss);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerXpEvent.PickupXp.class, PlayerEvents::onPlayerPickupXp);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, PlayerEvents::onPlayerFishing);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangedDimensionEvent.class, PlayerEvents::onDimensionChange);
	}

	private static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (WorldUtil.isWorld(ev.player.level(), AoADimensions.LELYETIA.key)) {
				LelyetiaEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level(), AoADimensions.VOX_PONDS.key)) {
				VoxPondsEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level(), AoADimensions.LUNALUS.key)) {
				LunalusEvents.doPlayerTick(ev.player);
			}
		}

		if (WorldUtil.isWorld(ev.player.level(), AoADimensions.NOWHERE.key))
			NowhereEvents.doPlayerTick(ev);
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LUNALUS.key) && ev.getEntity() instanceof Player)
			LunalusEvents.doPlayerJump((Player)ev.getEntity());
	}

	private static void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer && ev.getEntity().getHealth() - ev.getAmount() <= 0 && ev.getEntity().level().getLevelData().isHardcore())
			ReservedItem.handlePlayerDeath((ServerPlayer)ev.getEntity());
	}

	private static void onPlayerHurt(final LivingHurtEvent ev) {
		Entity attacker = ev.getSource().getEntity();

		if (DamageUtil.isMeleeDamage(ev.getSource()) && attacker instanceof LivingEntity) {
			ItemStack weapon = ((LivingEntity)attacker).getItemInHand(InteractionHand.MAIN_HAND);

			if (weapon.getItem() instanceof BaseSword baseSword)
				ev.setAmount(baseSword.getDamageForAttack(ev.getEntity(), (LivingEntity)attacker, weapon, ev.getAmount()));
		}

		if (ev.getEntity() instanceof ServerPlayer pl) {
			if (pl.getHealth() > 0 && ev.getSource().is(DamageTypeTags.IS_EXPLOSION) && ev.getSource().getDirectEntity() instanceof Creeper) {
				if ((!pl.level().getEntitiesOfClass(PrimedTnt.class, ev.getSource().getDirectEntity().getBoundingBox().inflate(3)).isEmpty() || !pl.level().getEntitiesOfClass(PrimedTnt.class, pl.getBoundingBox().inflate(3)).isEmpty()) && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.CREEPONIA_REALMSTONE.get()));
			}
		}
	}

	private static void onPlayerDamaged(final LivingDamageEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			if (pl.getHealth() <= ev.getAmount()) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
				PositionAndRotation checkpoint = plData.getCheckpoint();

				if (checkpoint != null) {
					if (CheckpointBlock.isValidCheckpoint(pl.level(), checkpoint)) {
						AoAScheduler.scheduleSyncronisedTask(() -> {
							if (NowhereEvents.isInBossRegion(pl.blockPosition()))
								ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));

							PlayerUtil.resetToDefaultStatus(pl);
							pl.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
							pl.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.respawn"), ChatFormatting.GREEN), true);
							checkpoint.applyToEntity(pl);
						}, 1);

						ev.setCanceled(true);

						return;
					}
					else {
						plData.clearCheckpoint();
						pl.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.invalid"), ChatFormatting.RED));
					}
				}

				if (ev.getEntity().level().dimension() == AoADimensions.NOWHERE.key)
					NowhereEvents.doDeathPrevention(ev, plData);
			}
		}
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer player) {
			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.LELYETIA_REALMSTONE.get()));

			if (WorldUtil.isWorld(player.level(), AoADimensions.LUNALUS.key))
				LunalusEvents.doPlayerLanding(player, ev);
		}
	}

	private static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().level().isClientSide) {
			if (ev.getEntity() instanceof ServerPlayer) {
				ReservedItem.handlePlayerDeath((ServerPlayer)ev.getEntity());
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayer) {
				if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.DEEPLANDS.key)) {
					if (ev.getEntity() instanceof FlyingMob)
						ev.getEntity().spawnAtLocation(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
			}
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		Player pl = ev.getPlayer();

		if (pl instanceof ServerPlayer) {
			BlockPos pos = ev.getPos();

			if (!pl.isCreative() && ev.getState().is(Tags.Blocks.ORES) && pos.getY() <= pl.level().getMinBuildHeight() + 5 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.DEEPLANDS_REALMSTONE.get()));
		}
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (!(ev.getEntity() instanceof ServerPlayer player))
			return;

		if (PlayerUtil.isWearingFullSet(player, AdventArmour.Type.HYDRANGIC)) {
			if (ev.getPlacedBlock().getBlock() instanceof BonemealableBlock && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getEntity().level(), ev.getPos(), player)) {
				ev.getLevel().levelEvent(LevelEvent.PARTICLES_PLANT_GROWTH, ev.getPos(), 0);
				player.hurtArmor(player.level().damageSources().generic(), 16);
			}
		}
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer player) {
			UUID uuid = player.getGameProfile().getId();
			String msg = null;

			if (uuid.compareTo(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4")) == 0) {
				msg = ChatFormatting.DARK_RED + "It begins...Is this the end?";

				((ServerLevel)player.level()).sendParticles(ParticleTypes.LARGE_SMOKE, player.getX(), player.getY() + 0.2d, player.getZ(), 16, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, 1);
			}

			if (msg != null)
				player.getServer().getPlayerList().broadcastSystemMessage(Component.literal(msg), false);

			ServerPlayerDataManager.syncNewPlayer(player);

			PlayerAdvancements plAdvancements = player.getAdvancements();
			AdvancementHolder rootAdv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "completionist/root"));

			if (rootAdv == null) {
				Logging.logMessage(Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things.");
			}
			else if (!plAdvancements.getOrStartProgress(rootAdv).isDone()) {
				plAdvancements.award(AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "completionist/by_the_books")), "legitimate");
				plAdvancements.award(rootAdv, "playerjoin");
			}
		}
	}

	private static void onItemToss(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayer player) {
			ItemEntity entityItem = ev.getEntity();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get() && entityItem.getOwner() == player) {
				if (player.isEyeInFluidType(NeoForgeMod.LAVA_TYPE.value())) {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.NETHER_REALMSTONE.get()));
					ev.getEntity().discard();
				}
			}
			else if (item instanceof ReservedItem) {
				ReservedItem.handlePlayerToss(ev);
			}
		}
	}

	private static void onPlayerPickupXp(final PlayerXpEvent.PickupXp ev) {
		if (!ev.getEntity().level().isClientSide && ev.getOrb().value > 0) {
			ItemStack stack = ItemUtil.getStackFromInventory(ev.getEntity(), AoATools.EXP_FLASK.get());

			if (stack != null && stack.getItem() instanceof ExpFlask flask) {
				flask.addCharge(stack, ev.getOrb().value);
				ev.setCanceled(true);
				ev.getOrb().value = 0;
				ev.getOrb().discard();
			}
		}
	}

	private static void onPlayerFishing(final ItemFishedEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LBOREAN.key) && RandomUtil.oneInNChance(10)) {
			FishingHook hook = ev.getHookEntity();
			LivingEntity fisher = ev.getEntity();

			ItemEntity drop = new ItemEntity(fisher.level(), hook.getX(), hook.getY(), hook.getZ(), new ItemStack(AoAItems.CALL_OF_THE_DRAKE.get()));
			double velocityX = fisher.getX() - hook.getX();
			double velocityY = fisher.getY() - hook.getY();
			double velocityZ = fisher.getZ() - hook.getZ();
			double velocity = Math.sqrt((velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ));

			drop.setDeltaMovement(velocityX * 0.1D, velocityY * 0.1D + (double)Math.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
			fisher.level().addFreshEntity(drop);
		}
	}

	private static void onDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getFrom() == AoADimensions.NOWHERE.key)
			NowhereEvents.doDimensionChange(ev);

		if (ev.getEntity() instanceof ServerPlayer pl)
			PlayerUtil.getAdventPlayer(pl).clearCheckpoint();
	}
}
