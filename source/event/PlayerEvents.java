package net.tslat.aoa3.event;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ResourceDataPacket;
import net.tslat.aoa3.common.packet.packets.SkillDataPacket;
import net.tslat.aoa3.common.packet.packets.TributeDataPacket;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.misc.AnimaStoneEntity;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dimension.LelyetiaEvents;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.event.dimension.ShyrelandsEvents;
import net.tslat.aoa3.event.dimension.VoxPondsEvents;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.misc.BlankRealmstone;
import net.tslat.aoa3.item.misc.ReservedItem;
import net.tslat.aoa3.item.misc.summoning.BossSpawningItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.item.tool.misc.ExpFlask;
import net.tslat.aoa3.library.misc.AoAHalos;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.*;
import net.tslat.aoa3.worldgen.trees.CreepTreeGenerator;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class PlayerEvents {
	@SubscribeEvent
	public static void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			if (ev.player instanceof ServerPlayerEntity) {
				PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.player).tickPlayer();

				if (!ev.player.isCreative() && ev.player.onGround && !ev.player.isBeingRidden() && ev.player.getRidingEntity() == null)
					ExpeditionUtil.handleRunningTick(ev, (ServerPlayerEntity)ev.player);
			}

			if (ev.player.dimension == AoADimensions.SHYRELANDS.type()) {
				if (!ev.player.world.isRemote)
					ShyrelandsEvents.doPlayerTick(ev.player);
			}
			else if (ev.player.dimension == AoADimensions.LELYETIA.type()) {
				LelyetiaEvents.doPlayerTick(ev.player);
			}
			else if (ev.player.dimension == AoADimensions.VOX_PONDS.type()) {
				if (!ev.player.world.isRemote)
					VoxPondsEvents.doPlayerTick(ev.player);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving());

			plData.handleIncomingAttack(ev);

			if (ev.getEntityLiving().getHealth() - ev.getAmount() <= 0 && ev.getEntityLiving().world.getWorldInfo().isHardcore())
				ReservedItem.handlePlayerDeath((ServerPlayerEntity)ev.getEntityLiving());
		}
	}

	@SubscribeEvent
	public static void onPlayerLevelUp(final PlayerLevelChangeEvent ev) {
		if (ev.getSkill() == Skills.INNERVATION) {
			double healthBuff = InnervationUtil.getHealthBuff(ev.getNewLevel());

			if (healthBuff != InnervationUtil.getHealthBuff(ev.getOldLevel())) {
				EntityUtil.removeAttributeModifier(ev.getEntityLiving(), SharedMonsterAttributes.MAX_HEALTH, InnervationUtil.INNERVATION_HEALTH_BUFF);

				if (healthBuff > 0)
					EntityUtil.applyAttributeModifierSafely(ev.getEntityLiving(), SharedMonsterAttributes.MAX_HEALTH, InnervationUtil.getHealthModifier(ev.getNewLevel()));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerDamaged(final LivingDamageEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving());

			if (ev.getEntityLiving().getHealth() > 0.0f) {
				plData.handleDamageTriggers(ev);

				if (ev.getSource().getTrueSource() instanceof LivingEntity && RandomUtil.oneInNChance(15))
					plData.enableRevenge((LivingEntity)ev.getSource().getTrueSource());
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHurt(final LivingHurtEvent ev) {
		if (!ev.getEntityLiving().world.isRemote) {
			if (ev.getEntityLiving() instanceof ServerPlayerEntity) {
				ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntityLiving();

				PlayerUtil.getAdventPlayer(pl).handleIncomingDamage(ev);
				Entity creeper = ev.getSource().getImmediateSource();

				if (pl.getHealth() > 0 && ev.getSource().isExplosion() && creeper instanceof CreeperEntity) {
					if ((!pl.world.getEntitiesWithinAABB(TNTEntity.class, creeper.getBoundingBox().grow(3)).isEmpty() || !pl.world.getEntitiesWithinAABB(TNTEntity.class, pl.getBoundingBox().grow(3)).isEmpty()) && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
						ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.CREEPONIA_REALMSTONE.get()));
				}
			}
			else if (ev.getSource().getTrueSource() instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getSource().getTrueSource());

				plData.handleOutgoingDamage(ev);

				if (DamageUtil.isMeleeDamage(ev.getSource()) && !ev.getSource().isDamageAbsolute()) {
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
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().world.isRemote) {
			if (ev.getEntity() instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntity());

				plData.stats().resetAllTribute();
				plData.handlePlayerDeath(ev);
				ReservedItem.handlePlayerDeath(plData.player());

				// Patching broken forge/vanilla code
				if (ev.getEntity().isAlive() && ev.getSource().getTrueSource() instanceof LivingEntity)
					ev.getSource().getTrueSource().onKillEntity((LivingEntity)ev.getEntity());
			}
			else if (ev.getSource().getTrueSource() instanceof ServerPlayerEntity) {
				if (ev.getEntity().world.getDimension().getType() == DimensionType.OVERWORLD) {
					if (ev.getEntity().world.isDaytime()) {
						PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getSource().getTrueSource());

						plData.stats().addTribute(Deities.EREBON, 8);
						plData.stats().addTribute(Deities.LUXON, -8);
					}
				}
				else if (ev.getEntity().world.getDimension().getType() == AoADimensions.DEEPLANDS.type()) {
					if (ev.getEntityLiving() instanceof FlyingEntity)
						ev.getEntityLiving().entityDropItem(new ItemStack(AoAItems.MUSIC_DISC_CAVERNS.get()), 0.5f);
				}
				else if (ev.getEntity().world.getDimension().getType() == AoADimensions.CRYSTEVIA.type()) {
					if (ev.getEntity().getClass().toString().contains("Construct")) {
						ItemStack blankRealmstoneStack = ItemUtil.getStackFromInventory((ServerPlayerEntity)ev.getSource().getTrueSource(), AoAItems.BLANK_REALMSTONE.get());

						if (blankRealmstoneStack != null)
							BlankRealmstone.handleAncientCavernTask(blankRealmstoneStack, ev.getEntityLiving(), (ServerPlayerEntity)ev.getSource().getTrueSource());
					}
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

		if (ev.getFrom() == AoADimensions.LELYETIA.type())
			ev.getEntityLiving().setNoGravity(false);
	}

	public static void onBlockHarvest(final BlockEvent.HarvestDropsEvent ev) {
		ServerPlayerEntity pl = (ServerPlayerEntity)ev.getHarvester();
		Item tool = pl.getHeldItem(Hand.MAIN_HAND).getItem();

		if (tool instanceof SpecialHarvestTool)
			((SpecialHarvestTool)tool).doHarvestEffect(ev);

		if (!(ev.getHarvester() instanceof ServerPlayerEntity))
			return;

		Block bl = ev.getState().getBlock();
		boolean crop = bl.isIn(BlockTags.CROPS);
		boolean leaves = bl.isIn(BlockTags.LEAVES);

		if (crop || bl.isIn(BlockTags.FLOWERS) || bl instanceof VineBlock || leaves) {
			if (!(bl instanceof CropsBlock) || ((CropsBlock)bl).isMaxAge(ev.getState())) {
				if (crop && ev.getWorld().getWorld().isDaytime()) {
					PlayerUtil.getAdventPlayer(pl).stats().addTribute(Deities.SELYAN, 2);

					if (RandomUtil.oneInNChance(2000))
						pl.entityDropItem(new ItemStack(AoAWeapons.GARDENER.get()), 0);
				}

				if (leaves ? RandomUtil.oneInNChance(35) : RandomUtil.oneInNChance(crop ? 6 : 8)) {
					AnimaStoneEntity animaStone = new AnimaStoneEntity(ev.getWorld().getWorld(), ev.getPos());

					ev.getWorld().playSound(null, ev.getPos(), AoASounds.HEART_STONE_SPAWN.get(), SoundCategory.MASTER, 1.0f, 1.0f);
					ev.getWorld().addEntity(animaStone);
				}
			}
		}
		else if (ev.getState().getMaterial() == Material.ROCK && ev.getState().isOpaqueCube(ev.getWorld(), ev.getPos())) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			int lvl = plData.stats().getLevel(Skills.FORAGING);

			if (ev.getState().getBlockHardness(ev.getWorld(), ev.getPos()) > 1 || RandomUtil.fiftyFifty()) {
				if (ForagingUtil.shouldGetLoot(lvl)) {
					ev.getDrops().addAll(ForagingUtil.getLoot(pl, ev.getPos()));

					if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.FORAGING)
						ev.getDrops().addAll(ForagingUtil.getLoot(pl, ev.getPos()));

					ev.getWorld().playSound(null, ev.getPos(), AoASounds.SKILL_LOOT.get(), SoundCategory.MASTER, 1.0f, 1.0f);

					if (ev.getWorld().getDimension().getType() == DimensionType.OVERWORLD && ev.getWorld().getWorld().isDaytime())
						plData.stats().addTribute(Deities.PLUTON, 11 - lvl / 10);
				}
			}
		}
		else if (bl.isIn(BlockTags.LOGS)) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			int lvl = plData.stats().getLevel(Skills.LOGGING);

			if (LoggingUtil.shouldGetLoot(lvl)) {
				if (RandomUtil.fiftyFifty()) {
					ev.getDrops().addAll(LoggingUtil.getLoot(pl));

					if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.LOGGING)
						ev.getDrops().addAll(LoggingUtil.getLoot(pl));
				}
				else {
					ItemStack duplicateStack = ItemStack.EMPTY;

					for (ItemStack stack : ev.getDrops()) {
						if (stack.getItem() == Item.getItemFromBlock(bl)) {
							duplicateStack = stack.copy();

							duplicateStack.setCount(lvl > 50 ? 2 : 1);
							plData.stats().addXp(Skills.LOGGING, (float)Math.pow(lvl, 1.65D) * 3, false, false);

							break;
						}
					}

					if (!duplicateStack.isEmpty())
						ev.getDrops().add(duplicateStack);
				}

				if (ev.getWorld().getDimension().getType() == DimensionType.OVERWORLD)
					plData.stats().addTribute(Deities.PLUTON, 11 - lvl / 10);

				ev.getWorld().playSound(null, ev.getPos(), AoASounds.SKILL_LOOT.get(), SoundCategory.MASTER, 1.0f, 1.0f);
			}
		}

		if (bl.isIn(Tags.Blocks.ORES) && ev.getPos().getY() <= 5 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
			ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.DEEPLANDS_REALMSTONE.get()));
	}

	@SubscribeEvent
	public static void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (!WorldUtil.canModifyBlock(ev.getWorld(), ev.getPos(), ev.getPlayer()))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void onBlockPlace(final BlockEvent.EntityPlaceEvent ev) {
		if (!ev.getWorld().isRemote() && ev.getPlacedBlock().getBlock() == AoABlocks.CREEP_LEAVES.get()) {
			new CreepTreeGenerator(null).generate(ev.getWorld(), new Random(), ev.getPos());
		}

		if (!WorldUtil.canModifyBlock(ev.getWorld(), ev.getPos(), ev.getEntity())) {
			ev.setCanceled(true);

			return;
		}

		if (ev.getEntity() instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)ev.getEntity();

			if (PlayerUtil.isWearingFullSet(pl, AdventArmour.Type.HYDRANGIC)) {
				if (ev.getPlacedBlock().getBlock() instanceof IGrowable && BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), ev.getWorld().getWorld(), ev.getPos(), pl)) {
					ev.getWorld().playEvent(2005, ev.getPos(), 0);
					pl.inventory.damageArmor(4);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		DimensionType dimType = ev.getWorld().getDimension().getType();

		if (dimType == AoADimensions.ANCIENT_CAVERN.type() || dimType == AoADimensions.IMMORTALLIS.type()) {
			PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

			if (relevantPlayer == null || !relevantPlayer.isCreative())
				ev.setUseItem(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onEmptyBucketUse(final FillBucketEvent ev) {
		DimensionType dimType = ev.getWorld().getDimension().getType();

		if (dimType == AoADimensions.ANCIENT_CAVERN.type() || dimType == AoADimensions.IMMORTALLIS.type()) {
			PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getEntity());

			if (relevantPlayer == null || !relevantPlayer.isCreative()) {
				ev.setCanceled(true);
				ev.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerWakeup(final PlayerWakeUpEvent ev) {
		if (ev.getEntityLiving() instanceof ServerPlayerEntity && ev.getEntityLiving().world.getDimension().getType() == DimensionType.OVERWORLD) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getEntityLiving()).stats().resetAllTribute();
			OverworldEvents.doWorldStartCheck(ev.getEntityLiving().world);
		}
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
					pl.world.addParticle(ParticleTypes.LARGE_SMOKE, true, pl.getPosX(), pl.getPosY() + 0.2d, pl.getPosZ(), RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d, RandomUtil.randomValueUpTo(0.1f) - 0.05d);
				}
			}
			else if (AoAHalos.isCrazyDonator(uuid)) {
				msg = TextFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				pl.getServer().getPlayerList().sendMessage(new StringTextComponent(msg));

			OverworldEvents.alertNewPlayer(pl.world, pl);

			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			PlayerDataManager.PlayerStats stats = plData.stats();

			if (AoAConfig.COMMON.skillsEnabled.get()) {
				for (Skills sk : Skills.values()) {
					AoAPackets.messagePlayer(pl, new SkillDataPacket(sk.id, stats.getLevelForDisplay(sk), stats.getExp(sk), stats.getSkillData(Skills.EXPEDITION)));
				}

				EntityUtil.applyAttributeModifierSafely(pl, SharedMonsterAttributes.MAX_HEALTH, InnervationUtil.getHealthModifier(stats.getLevel(Skills.INNERVATION)));
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
					pl.getServer().getAdvancementManager().getAllAdvancements().forEach(advancement -> Logging.logMessage(Level.INFO, advancement.getId().toString()));
				}
			}
			else if (!plAdvancements.getProgress(rootAdv).isDone()) {
				plAdvancements.grantCriterion(AdvancementUtil.getAdvancement(new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/by_the_books")), "legitimate");
				plAdvancements.grantCriterion(rootAdv, "playerjoin");
			}
		}
	}

	@SubscribeEvent
	public static void onItemToss(final ItemTossEvent ev) {
		World world = ev.getPlayer().getEntityWorld();

		if (ev.getPlayer() instanceof ServerPlayerEntity) {
			ItemEntity entityItem = ev.getEntityItem();
			Item item = entityItem.getItem().getItem();

			if (item == AoAItems.BLANK_REALMSTONE.get()) {
				if (ev.getPlayer().isInLava()) {
					ItemUtil.givePlayerItemOrDrop(ev.getPlayer(), new ItemStack(AoAItems.NETHER_REALMSTONE.get()));
					ev.getEntityItem().remove();
				}
			}
			else if (item instanceof BossSpawningItem) {
				if (world.getDifficulty() == Difficulty.PEACEFUL) {
					PlayerUtil.getAdventPlayer((ServerPlayerEntity)ev.getPlayer()).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");
					return;
				}

				ev.setCanceled(true);
				world.addEntity(BossSpawningItem.newBossEntityItemFromExisting(entityItem, ev.getPlayer()));

				BossSpawningItem bossItem = (BossSpawningItem)item;

				if (bossItem.getThrowingSound() != null)
					world.playSound(null, entityItem.getPosX(), entityItem.getPosX(), entityItem.getPosZ(), bossItem.getThrowingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerPickupXp(final PlayerXpEvent.PickupXp ev) {
		PlayerEntity pl = ev.getPlayer();

		if (!pl.world.isRemote && ev.getOrb().xpValue > 0) {
			ItemStack stack = ItemUtil.getStackFromInventory(pl, AoAItems.EXP_FLASK.get());

			if (stack != null) {
				ExpFlask.addExp(stack, ev.getOrb().xpValue);
				ev.setCanceled(true);
				ev.getOrb().xpValue = 0;
				ev.getOrb().remove();
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerFishing(final ItemFishedEvent ev) {
		if (!ev.getPlayer().world.isRemote() && RandomUtil.fiftyFifty()) {
			World world = ev.getPlayer().world;
			LootTableManager lootTableManager = world.getServer().getLootTableManager();
			LootTable lootTable;

			if (RandomUtil.oneInNChance(20)) {
				lootTable = lootTableManager.getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/hauling_treasure"));
			}
			else {
				lootTable = lootTableManager.getLootTableFromLocation(new ResourceLocation(AdventOfAscension.MOD_ID, "skills/hauling_fish"));
			}

			List<ItemStack> loot = lootTable.generate(new LootContext.Builder((ServerWorld)world).withParameter(LootParameters.POSITION, ev.getHookEntity().getPosition()).withParameter(LootParameters.THIS_ENTITY, ev.getPlayer()).build(LootParameterSets.GIFT));

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
		}

		if (ev.getEntityLiving().world.getDimension().getType() == AoADimensions.LBOREAN.type() && RandomUtil.oneInNChance(10)) {
			FishingBobberEntity hook = ev.getHookEntity();
			LivingEntity fisher = ev.getEntityLiving();

			ItemEntity drop = new ItemEntity(fisher.world, hook.getPosX(), hook.getPosY(), hook.getPosZ(), new ItemStack(AoAItems.CALL_OF_THE_DRAKE.get()));
			double velocityX = fisher.getPosX() - hook.getPosX();
			double velocityY = fisher.getPosY() - hook.getPosY();
			double velocityZ = fisher.getPosZ() - hook.getPosZ();
			double velocity = MathHelper.sqrt(velocityX * velocityX + velocityY * velocityY + velocityZ * velocityZ);

			drop.setMotion(velocityX * 0.1D, velocityY * 0.1D + (double)MathHelper.sqrt(velocity) * 0.08D, velocityZ * 0.1D);
			fisher.world.addEntity(drop);
		}
	}
}
