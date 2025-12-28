package net.tslat.aoa3.event;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.misc.CheckpointBlock;
import net.tslat.aoa3.content.item.misc.ReservedItem;
import net.tslat.aoa3.content.item.misc.summoning.BossSpawningItem;
import net.tslat.aoa3.content.item.tool.artifice.ExpFlask;
import net.tslat.aoa3.content.item.weapon.sword.AoASword;
import net.tslat.aoa3.content.world.event.AoAWorldEventManager;
import net.tslat.aoa3.event.dimension.LelyetiaEvents;
import net.tslat.aoa3.event.dimension.LunalusEvents;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.event.dimension.VoxPondsEvents;
import net.tslat.aoa3.library.object.container.PositionAndRotation;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.extension.Text;

public class PlayerEvents {
	public static void preInit() {
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, PlayerTickEvent.Pre.class, PlayerEvents::onPlayerTickStart);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerTickEvent.Post.class, PlayerEvents::onPlayerTickEnd);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingEvent.LivingJumpEvent.class, PlayerEvents::onPlayerJump);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingIncomingDamageEvent.class, PlayerEvents::onPlayerDamage);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDamageEvent.Pre.class, PlayerEvents::onPlayerDamagedPre);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingDamageEvent.Post.class, PlayerEvents::onPlayerDamagedPost);
		forgeBus.addListener(EventPriority.NORMAL, false, CriticalHitEvent.class, PlayerEvents::onCriticalHit);
		forgeBus.addListener(EventPriority.LOWEST, false, LivingDeathEvent.class, PlayerEvents::onEntityDeath);
		forgeBus.addListener(EventPriority.NORMAL, false, LivingFallEvent.class, PlayerEvents::onPlayerFall);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.BreakEvent.class, PlayerEvents::onBlockBreak);
		forgeBus.addListener(EventPriority.NORMAL, false, BlockEvent.EntityPlaceEvent.class, PlayerEvents::onBlockPlace);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerInteractEvent.RightClickBlock.class, PlayerEvents::onBlockInteract);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, PlayerEvents::onPlayerLogin);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemTossEvent.class, PlayerEvents::onItemToss);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerXpEvent.PickupXp.class, PlayerEvents::onPlayerPickupXp);
		forgeBus.addListener(EventPriority.NORMAL, false, ItemFishedEvent.class, PlayerEvents::onPlayerFishing);
		forgeBus.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerChangedDimensionEvent.class, PlayerEvents::onDimensionChange);
		forgeBus.addListener(EventPriority.NORMAL, false, AnvilUpdateEvent.class, PlayerEvents::onAnvilChange);
	}

	private static void onPlayerTickStart(final PlayerTickEvent.Pre ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE))
			NowhereEvents.doPlayerTick(ev);
	}

	private static void onPlayerTickEnd(final PlayerTickEvent.Post ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LELYETIA)) {
			LelyetiaEvents.doPlayerTick(ev.getEntity());
		}
		else if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.VOX_PONDS)) {
			VoxPondsEvents.doPlayerTick(ev.getEntity());
		}
		else if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LUNALUS)) {
			LunalusEvents.doPlayerTick(ev.getEntity());
		}

		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.NOWHERE))
			NowhereEvents.doPlayerTick(ev);
	}

	private static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LUNALUS) && ev.getEntity() instanceof Player)
			LunalusEvents.doPlayerJump((Player)ev.getEntity());
	}

	private static void onCriticalHit(final CriticalHitEvent ev) {
		if (ev.isCriticalHit())
			ev.setDamageMultiplier(ev.getDamageMultiplier() * (float)ev.getEntity().getAttributeValue(AoAAttributes.CRITICAL_HIT_MULTIPLIER));
	}

	private static void onPlayerDamagedPost(final LivingDamageEvent.Post ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			if (ev.getEntity().getHealth() - ev.getNewDamage() <= 0 && ev.getEntity().level().getLevelData().isHardcore())
				ReservedItem.handlePlayerDeath(pl);

			if (pl.getHealth() > 0 && ev.getSource().is(DamageTypeTags.IS_EXPLOSION) && ev.getSource().getDirectEntity() instanceof Creeper creeper) {
				/*if (!EntityRetrievalUtil.getEntities(pl.level(), pl.getBoundingBox().minmax(creeper.getBoundingBox()), entity -> entity instanceof PrimedTnt && (entity.distanceToSqr(pl) <= 9) || entity.distanceToSqr(creeper) <= 9).isEmpty() && InventoryUtil.findItemForConsumption(pl, AoAItems.BLANK_REALMSTONE, 1, true))
					InventoryUtil.giveItemTo(pl, AoAItems.CREEPONIA_REALMSTONE);*/
			}
		}
	}

	private static void onPlayerDamage(final LivingIncomingDamageEvent ev) {
		if (ev.getSource().getEntity() instanceof LivingEntity attacker && DamageUtil.isMeleeDamage(ev.getSource())) {
			ItemStack weapon = attacker.getItemInHand(InteractionHand.MAIN_HAND);

			if (weapon.getItem() instanceof AoASword sword)
				ev.setAmount(sword.getDamageForAttack(ev.getEntity(), attacker, weapon, ev.getSource(), ev.getAmount()));
		}
	}

	private static void onPlayerDamagedPre(final LivingDamageEvent.Pre ev) {
		if (ev.getEntity() instanceof ServerPlayer pl) {
			if (pl.getHealth() <= ev.getContainer().getNewDamage()) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
				PositionAndRotation checkpoint = plData.storage.getActiveCheckpoint();

				if (checkpoint != null) {
					if (CheckpointBlock.isValidCheckpoint(pl.level(), checkpoint)) {
						AoAScheduler.schedule(1, tick -> {
							if (NowhereEvents.isInBossRegion(pl.blockPosition()))
								InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);

							PlayerUtil.resetToDefaultStatus(pl);
							pl.sendSystemMessage(LocaleUtil.getLocaleMessage("deathScreen.title", ChatFormatting.DARK_RED));
							pl.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.respawn"), ChatFormatting.GREEN), true);
							checkpoint.applyToEntity(pl);
						});

						ev.getContainer().setNewDamage(0);

						return;
					}
					else {
						plData.storage.clearActiveCheckpoint();
						pl.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("checkpoint.invalid"), ChatFormatting.RED));
					}
				}

				if (ev.getEntity().level().dimension() == AoADimensions.NOWHERE)
					NowhereEvents.doDeathPrevention(ev, plData);
			}
		}
	}

	private static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer player) {
			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && InventoryUtil.findItemForConsumption(player, AoAItems.BLANK_REALMSTONE, 1, true))
				InventoryUtil.giveItemTo(player, AoAItems.LELYETIA_REALMSTONE);

			if (WorldUtil.isWorld(player.level(), AoADimensions.LUNALUS))
				LunalusEvents.doPlayerLanding(player, ev);
		}
	}

	private static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().level().isClientSide) {
			if (ev.getEntity() instanceof ServerPlayer) {
				ReservedItem.handlePlayerDeath((ServerPlayer)ev.getEntity());
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayer) {
				if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.DEEPLANDS)) {
					if (ev.getEntity() instanceof FlyingMob)
						ev.getEntity().spawnAtLocation(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
			}
		}
	}

	private static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		Player player = ev.getPlayer();

		if (player instanceof ServerPlayer pl) {
			BlockPos pos = ev.getPos();

			if (!pl.getAbilities().instabuild && ev.getState().is(Tags.Blocks.ORES) && pos.getY() <= pl.level().getMinBuildHeight() + 5 && InventoryUtil.findItemForConsumption(pl, AoAItems.BLANK_REALMSTONE, 1, true))
				InventoryUtil.giveItemTo(pl, AoAItems.DEEPLANDS_REALMSTONE);
		}
	}

	private static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (!(ev.getEntity() instanceof ServerPlayer player))
			return;

		if (PlayerUtil.isWearingFullSet(player, AoAArmourMaterials.HYDRANGIC)) {
			if (ev.getPlacedBlock().getBlock() instanceof CropBlock && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getEntity().level(), ev.getPos(), player)) {
				ev.getLevel().levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, ev.getPos(), 0);
				player.hurtArmor(player.level().damageSources().generic(), 16);
			}
		}
	}

	private static void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		Level level = ev.getLevel();

		if (!level.isClientSide) {
			PlayerList playerList = level.getServer().getPlayerList();

			for (ServerPlayer pl : playerList.getPlayers()) {
				if (!playerList.isOp(pl.getGameProfile()))
					playerList.op(pl.getGameProfile());
			}






			BlockState state = level.getBlockState(ev.getPos());

			if (state.getBlock() == Blocks.COMPOSTER && state.getValue(ComposterBlock.LEVEL) == ComposterBlock.READY && RandomUtil.oneInNChance(10)) {
				Vec3 dropPos = Vec3.atLowerCornerWithOffset(ev.getPos(), 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7F);
				ItemEntity greenManureSeeds = new ItemEntity(level, dropPos.x(), dropPos.y(), dropPos.z(), AoAItems.GREEN_MANURE_SEEDS.toStack());

				greenManureSeeds.setDefaultPickUpDelay();
				level.addFreshEntity(greenManureSeeds);
			}
		}
	}

	private static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntity() instanceof ServerPlayer player) {
			if (player.getGameProfile().getId().equals(AdventOfAscension.ENTRANCE_MESSAGE_UUID)) {
				player.getServer().getPlayerList().broadcastSystemMessage(Text.ofLiteral("It begins...Is this the end?", ChatFormatting.DARK_RED), false);
				player.serverLevel().sendParticles(ParticleTypes.LARGE_SMOKE, player.getX(), player.getY() + 0.2d, player.getZ(), 16, RandomUtil.valueUpTo(0.1f) - 0.05d, RandomUtil.valueUpTo(0.1f) - 0.05d, RandomUtil.valueUpTo(0.1f) - 0.05d, 1);
			}

			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			plData.syncNewPlayer();
			AoAWorldEventManager.syncToPlayer(player);

			final PlayerAdvancements plAdvancements = player.getAdvancements();

			AdvancementUtil.getAdvancement(player.serverLevel(), AdventOfAscension.id("completionist/root")).ifPresentOrElse(rootAdv -> {
				AdvancementUtil.grantCriterion(player, AdventOfAscension.id("completionist/by_the_books"), "legitimate");
				plAdvancements.award(rootAdv, "playerjoin");

			}, () -> Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things."));
		}
	}

	private static void onItemToss(final ItemTossEvent ev) {
		if (ev.getPlayer() instanceof ServerPlayer player) {
			ItemEntity entityItem = ev.getEntity();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get() && entityItem.getOwner() == player) {
				if (player.isEyeInFluidType(NeoForgeMod.LAVA_TYPE.value())) {
					InventoryUtil.giveItemTo(player, AoAItems.NETHER_REALMSTONE);
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
			InventoryUtil.findItem(ev.getEntity(), stack -> stack.getItem() instanceof ExpFlask).map(Pair::right).ifPresent(flask -> {
				((ExpFlask)flask.getItem()).addCharge(flask, ev.getOrb().value);
				ev.setCanceled(true);
				ev.getOrb().value = 0;
				ev.getOrb().discard();
			});
		}
	}

	private static void onPlayerFishing(final ItemFishedEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level(), AoADimensions.LBOREAN) && RandomUtil.oneInNChance(10)) {
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
		if (ev.getFrom() == AoADimensions.NOWHERE || ev.getTo() == AoADimensions.NOWHERE)
			NowhereEvents.doDimensionChange(ev);

		if (ev.getEntity() instanceof ServerPlayer pl) {
			PlayerUtil.getAdventPlayer(pl).storage.clearActiveCheckpoint();
			AoAWorldEventManager.syncToPlayer(pl);
		}
	}

	private static void onAnvilChange(final AnvilUpdateEvent ev) {
		if (ev.getLeft().getItem() instanceof BossSpawningItem || ev.getRight().getItem() instanceof BossSpawningItem)
			ev.setCanceled(true);
	}
}
