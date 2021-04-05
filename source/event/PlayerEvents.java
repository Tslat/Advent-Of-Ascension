package net.tslat.aoa3.event;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ResourceDataPacket;
import net.tslat.aoa3.common.packet.packets.SkillDataPacket;
import net.tslat.aoa3.common.packet.packets.TributeDataPacket;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.misc.AnimaStoneEntity;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dimension.LelyetiaEvents;
import net.tslat.aoa3.event.dimension.LunalusEvents;
import net.tslat.aoa3.event.dimension.ShyrelandsEvents;
import net.tslat.aoa3.event.dimension.VoxPondsEvents;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.misc.ReservedItem;
import net.tslat.aoa3.item.misc.summoning.BossSpawningItem;
import net.tslat.aoa3.item.tool.misc.ExpFlask;
import net.tslat.aoa3.library.misc.AoAHalos;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.ButcheryUtil;
import net.tslat.aoa3.util.skill.ExpeditionUtil;
import net.tslat.aoa3.util.skill.InnervationUtil;
import org.apache.logging.log4j.Level;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class PlayerEvents {
	@SubscribeEvent
	public static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (ev.player instanceof ServerPlayerEntity) {
				PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.player).tickPlayer();

				if (!ev.player.isCreative() && ev.player.onGround && !ev.player.isVehicle() && ev.player.getVehicle() == null)
					ExpeditionUtil.handleRunningTick(ev, (ServerPlayerEntity)ev.player);
			}

			if (WorldUtil.isWorld(ev.player.level, AoADimensions.SHYRELANDS.key)) {
				if (!ev.player.level.isClientSide())
					ShyrelandsEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level, AoADimensions.LELYETIA.key)) {
				LelyetiaEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level, AoADimensions.VOX_PONDS.key)) {
				if (!ev.player.level.isClientSide())
					VoxPondsEvents.doPlayerTick(ev.player);
			}
			else if (WorldUtil.isWorld(ev.player.level, AoADimensions.LUNALUS.key)) {
				LunalusEvents.doPlayerTick(ev.player);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerJump(final LivingEvent.LivingJumpEvent ev) {
		if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.LUNALUS.key) && ev.getEntity() instanceof PlayerEntity) {
			LunalusEvents.doPlayerJump((PlayerEntity)ev.getEntity());
		}
	}

	@SubscribeEvent
	public static void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving());

			plData.handleIncomingAttack(ev);

			if (ev.getEntityLiving().getHealth() - ev.getAmount() <= 0 && ev.getEntityLiving().level.getLevelData().isHardcore())
				ReservedItem.handlePlayerDeath((ServerPlayerEntity)ev.getEntityLiving());
		}
	}

	@SubscribeEvent
	public static void onEnderPearl(final EnderTeleportEvent ev) {
		if (!ev.getEntityLiving().level.isClientSide() && ev.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity pl = (PlayerEntity)ev.getEntityLiving();

			if (pl.level.dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID) && ev.getTargetY() >= pl.level.dimensionType().logicalHeight())
				ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onPlayerLevelUp(final PlayerLevelChangeEvent ev) {
		if (ev.getSkill() == Skills.INNERVATION) {
			double healthBuff = InnervationUtil.getHealthBuff(ev.getNewLevel());

			if (healthBuff != InnervationUtil.getHealthBuff(ev.getOldLevel())) {
				EntityUtil.removeAttributeModifier(ev.getEntityLiving(), Attributes.MAX_HEALTH, InnervationUtil.INNERVATION_HEALTH_BUFF);

				if (healthBuff > 0)
					EntityUtil.applyAttributeModifierSafely(ev.getEntityLiving(), Attributes.MAX_HEALTH, InnervationUtil.getHealthModifier(ev.getNewLevel()));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerDamaged(final LivingDamageEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving());

			if (ev.getEntityLiving().getHealth() > 0.0f) {
				plData.handleDamageTriggers(ev);

				if (ev.getSource().getEntity() instanceof LivingEntity && RandomUtil.oneInNChance(15))
					plData.enableRevenge((LivingEntity)ev.getSource().getEntity());
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHurt(final LivingHurtEvent ev) {
		if (!ev.getEntityLiving().level.isClientSide) {
			if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
				ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntityLiving();

				PlayerUtil.getAdventPlayer(pl).handleIncomingDamage(ev);
				Entity creeper = ev.getSource().getDirectEntity();

				if (pl.getHealth() > 0 && ev.getSource().isExplosion() && creeper instanceof CreeperEntity) {
					if ((!pl.level.getEntitiesOfClass(TNTEntity.class, creeper.getBoundingBox().inflate(3)).isEmpty() || !pl.level.getEntitiesOfClass(TNTEntity.class, pl.getBoundingBox().inflate(3)).isEmpty()) && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
						ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.CREEPONIA_REALMSTONE.get()));
				}
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getSource().getEntity());

				plData.handleOutgoingDamage(ev);

				if (DamageUtil.isMeleeDamage(ev.getSource()) && !ev.getSource().isBypassMagic()) {
					ButcheryUtil.tryCritical(ev, plData);
					ButcheryUtil.tryBloodlustSpawn(plData.player(), ev.getEntityLiving());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && ItemUtil.findInventoryItem((ServerPlayerEntity)ev.getEntity(), new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop((ServerPlayerEntity)ev.getEntity(), new ItemStack(AoAItems.LELYETIA_REALMSTONE.get()));

			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntity());

			plData.handlePlayerFalling(ev);
			ExpeditionUtil.handleFallEvent(ev, plData);

			if (WorldUtil.isWorld(plData.player().level, AoADimensions.LUNALUS.key)) {
				LunalusEvents.doPlayerLanding(plData.player(), ev);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().level.isClientSide) {
			if (ev.getEntity() instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntity());

				plData.stats().resetAllTribute();
				plData.handlePlayerDeath(ev);
				ReservedItem.handlePlayerDeath(plData.player());
			}
			else if (ev.getSource().getEntity() instanceof ServerPlayerEntity) {
				if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.OVERWORLD.key)) {
					if (ev.getEntity().level.isDay()) {
						PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getSource().getEntity());

						plData.stats().addTribute(Deities.EREBON, 8);
						plData.stats().addTribute(Deities.LUXON, -8);
					}
				}
				else if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.DEEPLANDS.key)) {
					if (ev.getEntityLiving() instanceof FlyingEntity)
						ev.getEntityLiving().spawnAtLocation(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerChangeEquipment(final LivingEquipmentChangeEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity)
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving()).equipment().markDirty();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent ev) {
		if (!ev.isEndConquered() && ev.getEntityLiving() instanceof ServerPlayerEntity)
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving()).handlePlayerRespawn(ev);
	}

	@SubscribeEvent
	public static void onPlayerClone(final PlayerEvent.Clone ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity)
			PlayerUtil.clonePlayerData((ServerPlayerEntity)ev.getOriginal(), (ServerPlayerEntity)ev.getEntityLiving());
	}

	@SubscribeEvent
	public static void onWorldChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity)
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving()).stats().resetAllTribute();

		if (ev.getFrom() == AoADimensions.LELYETIA.key)
			ev.getEntityLiving().setNoGravity(false);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		PlayerEntity pl = ev.getPlayer();
		World world = pl.level;
		BlockPos pos = ev.getPos();

		if (pl.isCreative())
			return;

		if (pl instanceof ServerPlayerEntity) {
			BlockState block = world.getBlockState(pos);
			boolean crop = block.is(BlockTags.CROPS);
			boolean leaves = block.is(BlockTags.LEAVES);

			if (crop || block.is(BlockTags.FLOWERS) || block.getBlock() instanceof VineBlock || leaves) {
				if (!(block.getBlock() instanceof CropsBlock) || ((CropsBlock)block.getBlock()).isMaxAge(ev.getState())) {
					if (crop && world.isDay()) {
						PlayerUtil.getAdventPlayer((ServerPlayerEntity)pl).stats().addTribute(Deities.SELYAN, 2);

						if (RandomUtil.oneInNChance(2000))
							pl.spawnAtLocation(new ItemStack(AoAWeapons.GARDENER.get()), 0);
					}

					if (leaves ? RandomUtil.oneInNChance(35) : RandomUtil.oneInNChance(crop ? 6 : 8)) {
						AnimaStoneEntity animaStone = new AnimaStoneEntity(world, pos);

						ev.getWorld().playSound(null, pos, AoASounds.HEART_STONE_SPAWN.get(), SoundCategory.MASTER, 1.0f, 1.0f);
						ev.getWorld().addFreshEntity(animaStone);
					}
				}
			}

			if (block.is(Tags.Blocks.ORES) && pos.getY() <= 5 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
				ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.DEEPLANDS_REALMSTONE.get()));
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (ev.getEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntity();

			if (PlayerUtil.isWearingFullSet(pl, AdventArmour.Type.HYDRANGIC)) {
				if (ev.getPlacedBlock().getBlock() instanceof IGrowable && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getEntity().level, ev.getPos(), pl)) {
					ev.getWorld().levelEvent(2005, ev.getPos(), 0);
					pl.inventory.hurtArmor(DamageSource.GENERIC, 16);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerWakeup(final PlayerWakeUpEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity && WorldUtil.isWorld(ev.getEntityLiving().level, AoADimensions.OVERWORLD.key))
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving()).stats().resetAllTribute();
	}

	@SubscribeEvent
	public static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntityLiving();
			UUID uuid = pl.getGameProfile().getId();
			String msg = null;

			if (uuid.toString().equals("2a459b511-ca45-43d8-808d-f0eb30a63be4")) {
				msg = TextFormatting.DARK_RED + "It begins...Is this the end?";

				for (int i = 0; i < 16; i++) {
					pl.level.addParticle(ParticleTypes.LARGE_SMOKE, true, pl.getX(), pl.getY() + 0.2d, pl.getZ(), RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d);
				}
			}
			else if (AoAHalos.isCrazyDonator(uuid)) {
				msg = TextFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				pl.getServer().getPlayerList().broadcastMessage(new StringTextComponent(msg), ChatType.GAME_INFO, Util.NIL_UUID);

			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			PlayerDataManager.PlayerStats stats = plData.stats();

			if (AoAConfig.COMMON.skillsEnabled.get()) {
				for (Skills sk : Skills.values()) {
					AoAPackets.messagePlayer(pl, new SkillDataPacket(sk.id, stats.getLevelForDisplay(sk), stats.getExp(sk), stats.getSkillData(Skills.EXPEDITION)));
				}

				EntityUtil.applyAttributeModifierSafely(pl, Attributes.MAX_HEALTH, InnervationUtil.getHealthModifier(stats.getLevel(Skills.INNERVATION)));
			}

			if (AoAConfig.COMMON.resourcesEnabled.get()) {
				AoAPackets.messagePlayer(pl, new TributeDataPacket(stats.getTribute(Deities.EREBON), stats.getTribute(Deities.LUXON), stats.getTribute(Deities.PLUTON), stats.getTribute(Deities.SELYAN)));
				AoAPackets.messagePlayer(pl, new ResourceDataPacket(stats.getResourceValue(Resources.CREATION), stats.getResourceValue(Resources.ENERGY), stats.getResourceValue(Resources.RAGE), stats.getResourceValue(Resources.SOUL), plData.isRevengeActive()));
			}

			AoAHalos.syncWithNewClient(pl);

			PlayerAdvancements plAdvancements = pl.getAdvancements();
			Advancement rootAdv = AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/root"));

			if (rootAdv == null) {
				Logging.logMessage(Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things.");

				if (AoAConfig.COMMON.doVerboseDebugging.get()) {
					Logging.logStatusMessage("Printing out current advancements list...");
					pl.getServer().getAdvancements().getAllAdvancements().forEach(advancement -> Logging.logMessage(Level.INFO, advancement.getId().toString()));
				}
			}
			else if (!plAdvancements.getOrStartProgress(rootAdv).isDone()) {
				plAdvancements.award(AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books")), "legitimate");
				plAdvancements.award(rootAdv, "playerjoin");
			}
		}
	}

	@SubscribeEvent
	public static void onItemToss(final ItemTossEvent ev) {
		World world = ev.getPlayer().getCommandSenderWorld();

		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			ItemEntity entityItem = ev.getEntityItem();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get()) {
				if (ev.getPlayer().isInLava()) {
					ItemUtil.givePlayerItemOrDrop(ev.getPlayer(), new ItemStack(AoAItems.NETHER_REALMSTONE.get()));
					ev.getEntityItem().remove();
				}
			}
			else if (item instanceof ReservedItem) {
				ReservedItem.handlePlayerToss(ev);
			}
			else if (item instanceof BossSpawningItem) {
				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer()).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");
					return;
				}

				ev.setCanceled(true);
				world.addFreshEntity(BossSpawningItem.newBossEntityItemFromExisting(entityItem, ev.getPlayer()));

				BossSpawningItem bossItem = (BossSpawningItem)item;

				if (bossItem.getThrowingSound() != null)
					world.playSound(null, entityItem.getX(), entityItem.getX(), entityItem.getZ(), bossItem.getThrowingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerPickupXp(final PlayerXpEvent.PickupXp ev) {
		PlayerEntity pl = ev.getPlayer();

		if (!pl.level.isClientSide && ev.getOrb().value > 0) {
			ItemStack stack = ItemUtil.getStackFromInventory(pl, AoAItems.EXP_FLASK.get());

			if (stack != null) {
				ExpFlask.addExp(stack, ev.getOrb().value);
				ev.setCanceled(true);
				ev.getOrb().value = 0;
				ev.getOrb().remove();
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerFishing(final ItemFishedEvent ev) {
		/*if (!ev.getPlayer().world.isRemote() && RandomUtil.fiftyFifty()) {
			World world = ev.getPlayer().world;
			LootTableManager lootTableManager = world.getServer().getLootTableManager();
			LootTable lootTable;

			if (RandomUtil.oneInNChance(20)) {
				lootTable = lootTableManager.getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/hauling_treasure"));
			}
			else {
				lootTable = lootTableManager.getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/hauling_fish"));
			}

			List<ItemStack> loot = lootTable.generate(new LootContext.Builder((ServerWorld)world).withParameter(LootParameters.ORIGIN, ev.getHookEntity().getPositionVec()).withParameter(LootParameters.THIS_ENTITY, ev.getPlayer()).build(LootParameterSets.GIFT));

			if (!loot.isEmpty()) {
				FishingBobberEntity hook = ev.getHookEntity();
				LivingEntity fisher = ev.getEntityLiving();

				ItemEntity drop = new ItemEntity(fisher.world, hook.getPosX(), hook.getPosY(), hook.getPosZ(), loot.get(0));
				double velocityX = fisher.getPosX() - hook.getPosX();
				double velocityY = fisher.getPosY() - hook.getPosY();
				double velocityZ = fisher.getPosZ() - hook.getPosZ();
				double velocity = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);

				drop.setMotion(velocityX * 0.1D, velocityY * 0.1D + (double)MathHelper.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
				fisher.world.addEntity(drop);
				loot.remove(0);
				ev.setCanceled(true);
			}

			if (!loot.isEmpty()) {
				for (ItemStack item : loot) {
					ev.getHookEntity().entityDropItem(item);
				}
			}
		}*/

		if (WorldUtil.isWorld(ev.getEntityLiving().level, AoADimensions.LBOREAN.key) && RandomUtil.oneInNChance(10)) {
			FishingBobberEntity hook = ev.getHookEntity();
			LivingEntity fisher = ev.getEntityLiving();

			ItemEntity drop = new ItemEntity(fisher.level, hook.getX(), hook.getY(), hook.getZ(), new ItemStack(AoAItems.CALL_OF_THE_DRAKE.get()));
			double velocityX = fisher.getX() - hook.getX();
			double velocityY = fisher.getY() - hook.getY();
			double velocityZ = fisher.getZ() - hook.getZ();
			double velocity = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);

			drop.setDeltaMovement(velocityX * 0.1D, velocityY * 0.1D + (double)MathHelper.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
			fisher.level.addFreshEntity(drop);
		}
	}
}
