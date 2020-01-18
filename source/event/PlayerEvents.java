package net.tslat.aoa3.event;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.generation.stone.StoneBlock;
import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.common.packet.PacketResourceData;
import net.tslat.aoa3.common.packet.PacketSkillData;
import net.tslat.aoa3.common.packet.PacketTributeData;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.dimension.AoAWorldProvider;
import net.tslat.aoa3.entity.misc.EntityAnimaStone;
import net.tslat.aoa3.entity.misc.EntityBloodlust;
import net.tslat.aoa3.event.custom.PlayerLevelChangeEvent;
import net.tslat.aoa3.event.dimension.*;
import net.tslat.aoa3.item.misc.BlankRealmstone;
import net.tslat.aoa3.item.misc.ReservedItem;
import net.tslat.aoa3.item.misc.summon.BossSpawningItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.item.tool.misc.ExpFlask;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.*;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.*;
import org.apache.logging.log4j.Level;

import java.util.UUID;

public class PlayerEvents {
	@SubscribeEvent
	public void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END && !ev.player.world.isRemote) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(ev.player);

			plData.tickPlayer();

			if (!ev.player.world.isRemote && !ev.player.capabilities.isCreativeMode && ev.player.onGround && !ev.player.isRiding())
				ExpeditionUtil.handleRunningTick(ev, plData);

			if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.shyrelands) {
				ShyrelandsEvents.doPlayerTick(plData);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.lelyetia) {
				LelyetiaEvents.doPlayerTick(plData);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.voxPonds) {
				VoxPondsEvents.doPlayerTick(plData);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.candyland) {
				CandylandEvents.doPlayerTick(plData);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHit(final LivingAttackEvent ev) {
		if (!ev.getEntity().world.isRemote && ev.getEntity() instanceof EntityPlayer) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntity());

			plData.handleIncomingAttack(ev);
		}
	}

	@SubscribeEvent
	public void onPlayerLevelUp(final PlayerLevelChangeEvent ev) {
		if (ev.getSkill() == Enums.Skills.INNERVATION) {
			double healthBuff = InnervationUtil.getHealthBuff(ev.getNewLevel());

			if (healthBuff != InnervationUtil.getHealthBuff(ev.getOldLevel())) {
				EntityUtil.removeAttributeModifier(ev.getEntityPlayer(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.INNERVATION_HEALTH_BUFF);

				if (healthBuff > 0)
					EntityUtil.applyAttributeModifierSafely(ev.getEntityPlayer(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.innervationHealthBuff(healthBuff));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerDamaged(final LivingDamageEvent ev) {
		if (!ev.getEntityLiving().world.isRemote && ev.getEntityLiving() instanceof EntityPlayer) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntityLiving());

			if (ev.getEntityLiving().getHealth() > 0.0f) {
				plData.handleDamageTriggers(ev);

				if (ev.getSource().getTrueSource() instanceof EntityLivingBase && AdventOfAscension.rand.nextInt(15) == 0)
					plData.enableRevenge((EntityLivingBase)ev.getSource().getTrueSource());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHurt(final LivingHurtEvent ev) {
		if (!ev.getEntityLiving().world.isRemote) {
			if (ev.getEntityLiving() instanceof EntityPlayer) {
				EntityPlayer pl = (EntityPlayer)ev.getEntityLiving();

				PlayerUtil.getAdventPlayer(pl).handleIncomingDamage(ev);

				if (pl.getHealth() > 0 && ev.getSource().isExplosion() && ev.getSource().getImmediateSource() instanceof EntityCreeper && !pl.world.getEntitiesWithinAABB(EntityTNTPrimed.class, ev.getSource().getImmediateSource().getEntityBoundingBox().grow(3)).isEmpty() && ItemUtil.consumeItem(pl, new ItemStack(ItemRegister.realmstoneBlank)))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.realmstoneCreeponia));
			}
			else if (ev.getSource().getTrueSource() instanceof EntityPlayer) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getSource().getTrueSource());

				plData.handleOutgoingDamage(ev);

				if (EntityUtil.isMeleeDamage(ev.getSource()) && !ev.getSource().isDamageAbsolute()) {
					ButcheryUtil.tryCritical(ev, plData);

					if (AdventOfAscension.rand.nextInt(30) == 0)
						ev.getEntity().world.spawnEntity(new EntityBloodlust(ev.getEntity().world, ev.getEntity().getPosition()));
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerFall(final LivingFallEvent ev) {
		if (!ev.getEntity().world.isRemote && ev.getEntity() instanceof EntityPlayer) {
			if (ev.getDistance() > 25 && ev.getDamageMultiplier() > 0 && ItemUtil.consumeItem((EntityPlayer)ev.getEntity(), new ItemStack(ItemRegister.realmstoneBlank)))
				ItemUtil.givePlayerItemOrDrop((EntityPlayer)ev.getEntity(), new ItemStack(ItemRegister.realmstoneLelyetia));

			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntity());

			plData.handlePlayerFalling(ev);
			ExpeditionUtil.handleFallEvent(ev, plData);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEntityDeath(final LivingDeathEvent ev) {
		if (!ev.getEntity().world.isRemote) {
			if (ev.getEntity() instanceof EntityPlayer) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntity());

				plData.stats().resetAllTribute();
				plData.handlePlayerDeath(ev);
				ReservedItem.handlePlayerDeath(plData.player());

				// Patching broken forge/vanilla code
				if (!ev.getEntity().isDead && ev.getSource().getTrueSource() instanceof EntityLivingBase)
					ev.getSource().getTrueSource().onKillEntity((EntityLivingBase)ev.getEntity());
			}
			else if (ev.getSource().getTrueSource() instanceof EntityPlayer) {
				if (ev.getEntity().world.provider.getDimension() == 0) {
					if (ev.getEntity().world.isDaytime()) {
						PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getSource().getTrueSource());

						plData.stats().addTribute(Enums.Deities.EREBON, 8);
						plData.stats().addTribute(Enums.Deities.LUXON, -8);
					}
				}
				else if (ev.getEntity().world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.crystevia && ev.getEntity().getClass().toString().contains("Construct")) {
					ItemStack blankRealmstoneStack = ItemUtil.getStackFromInventory((EntityPlayer)ev.getSource().getTrueSource(), ItemRegister.realmstoneBlank);

					if (blankRealmstoneStack != null)
						BlankRealmstone.handleAncientCavernTask(blankRealmstoneStack, ev.getEntityLiving(), (EntityPlayer)ev.getSource().getTrueSource());
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPlayerChangeEquipment(final LivingEquipmentChangeEvent ev) {
		if (!ev.getEntityLiving().world.isRemote && ev.getEntityLiving() instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntityLiving()).equipment().markDirty();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent ev) {
		if (!ev.player.world.isRemote)
			PlayerUtil.getAdventPlayer(ev.player).handlePlayerRespawn(ev);
	}

	@SubscribeEvent
	public void onPlayerClone(final PlayerEvent.Clone ev) {
		if (!ev.getEntityPlayer().world.isRemote)
			PlayerUtil.clonePlayerData(ev.getOriginal(), ev.getEntityPlayer());
	}

	@SubscribeEvent
	public void onWorldChange(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (!ev.player.world.isRemote)
			PlayerUtil.getAdventPlayer(ev.player).stats().resetAllTribute();

		if (ev.fromDim == ConfigurationUtil.MainConfig.dimensionIds.lelyetia && ev.player.hasNoGravity())
			ev.player.setNoGravity(false);
	}

	@SubscribeEvent
	public void onBlockHarvest(final BlockEvent.HarvestDropsEvent ev) {
		if (ev.getHarvester() == null || ev.getHarvester() instanceof FakePlayer)
			return;

		EntityPlayer pl = ev.getHarvester();
		Item tool = pl.getHeldItem(EnumHand.MAIN_HAND).getItem();

		if (tool instanceof SpecialHarvestTool)
			((SpecialHarvestTool)tool).doHarvestEffect(ev);

		if (ev.getWorld().isRemote)
			return;

		Block bl = ev.getState().getBlock();

		if (bl instanceof BlockCrops || bl instanceof BlockFlower || bl instanceof BlockVine || bl instanceof BlockLeaves) {
			if (!(bl instanceof BlockCrops) || ((BlockCrops)bl).isMaxAge(ev.getState())) {
				if (bl instanceof BlockCrops) {
					PlayerUtil.getAdventPlayer(pl).stats().addTribute(Enums.Deities.SELYAN, 2);

					if (AdventOfAscension.rand.nextInt(2000) == 0)
						pl.entityDropItem(new ItemStack(WeaponRegister.gunGardener), 0);
				}

				if (bl instanceof BlockLeaves ? AdventOfAscension.rand.nextInt(35) == 0 : bl instanceof BlockCrops ? AdventOfAscension.rand.nextInt(6) == 0 : AdventOfAscension.rand.nextInt(8) == 0) {
					EntityAnimaStone animaStone = new EntityAnimaStone(ev.getWorld(), ev.getPos());

					ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.heartStoneSpawn, SoundCategory.MASTER, 1.0f, 1.0f);
					ev.getWorld().spawnEntity(animaStone);
				}
			}
		}
		else if (bl == Blocks.STONE || bl == Blocks.NETHERRACK || bl instanceof StoneBlock || bl == Blocks.END_STONE) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			int lvl = plData.stats().getLevel(Enums.Skills.FORAGING);

			if (bl != Blocks.NETHERRACK || AdventOfAscension.rand.nextBoolean()) {
				if (ForagingUtil.shouldGetLoot(lvl)) {
					ev.getDrops().addAll(ForagingUtil.getLoot(pl));

					if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.FORAGING)
						ev.getDrops().addAll(ForagingUtil.getLoot(pl));

					ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.foragingLoot, SoundCategory.MASTER, 1.0f, 1.0f);

					if (ev.getWorld().provider.getDimension() == 0)
						plData.stats().addTribute(Enums.Deities.PLUTON, 11 - lvl / 10);
				}
			}
		}
		else if (bl instanceof BlockLog) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
			int lvl = plData.stats().getLevel(Enums.Skills.LOGGING);

			if (LoggingUtil.shouldGetLoot(lvl)) {
				if (AdventOfAscension.rand.nextBoolean()) {
					ev.getDrops().addAll(LoggingUtil.getLoot(pl));

					if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.LOGGING)
						ev.getDrops().addAll(LoggingUtil.getLoot(pl));
				}
				else {
					ItemStack duplicateStack = ItemStack.EMPTY;

					for (ItemStack stack : ev.getDrops()) {
						if (stack.getItem() == Item.getItemFromBlock(bl)) {
							duplicateStack = stack.copy();

							duplicateStack.setCount(lvl > 50 ? 2 : 1);
							plData.stats().addXp(Enums.Skills.LOGGING, (float)Math.pow(lvl, 1.65D) * 3, false);

							break;
						}
					}

					if (!duplicateStack.isEmpty())
						ev.getDrops().add(duplicateStack);
				}

				if (ev.getWorld().provider.getDimension() == 0)
					plData.stats().addTribute(Enums.Deities.PLUTON, 11 - lvl / 10);

				ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.foragingLoot, SoundCategory.MASTER, 1.0f, 1.0f);
			}
		}
		else if (bl instanceof BlockOre && ev.getPos().getY() <= 5 && ItemUtil.consumeItem(pl, new ItemStack(ItemRegister.realmstoneBlank))) {
			ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.realmstoneDeeplands));
		}
	}

	@SubscribeEvent
	public void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (!ev.getPlayer().capabilities.isCreativeMode && !ev.getWorld().isBlockModifiable(ev.getPlayer(), ev.getPos()))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public void onBlockPlace(final BlockEvent.PlaceEvent ev) {
		if (!ev.getPlayer().capabilities.isCreativeMode && ev.getWorld().provider instanceof AoAWorldProvider && !((AoAWorldProvider)ev.getWorld().provider).canPlaceBlock(ev.getPlayer(), ev.getPos(), ev.getPlacedBlock())) {
			ev.setCanceled(true);

			return;
		}

		if (!ev.getWorld().isRemote && PlayerUtil.isWearingFullSet(ev.getPlayer(), Enums.ArmourSets.HYDRANGIC)) {
			if (ev.getPlacedBlock().getBlock() instanceof IGrowable && ItemDye.applyBonemeal(new ItemStack(Items.DYE, 1, 15), ev.getWorld(), ev.getPos())) {
				ev.getWorld().playEvent(2005, ev.getPos(), 0);
				ev.getPlayer().inventory.damageArmor(4);
			}
		}
	}

	@SubscribeEvent
	public void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		if (!ev.getEntityPlayer().capabilities.isCreativeMode && ev.getWorld().provider instanceof AoAWorldProvider && !((AoAWorldProvider)ev.getWorld().provider).canInteractWith(ev.getEntityPlayer(), ev.getPos(), null, ev.getItemStack())) {
			ev.setCancellationResult(EnumActionResult.SUCCESS);
			ev.setUseItem(Event.Result.DENY);
			ev.setResult(Event.Result.DENY);
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEmptyBucketUse(final FillBucketEvent ev) {
		if (!ev.getEntityPlayer().capabilities.isCreativeMode && ev.getWorld().provider instanceof AoAWorldProvider && !((AoAWorldProvider)ev.getWorld().provider).canInteractWith(ev.getEntityPlayer(), ev.getTarget() == null ? null  : ev.getTarget().getBlockPos(), null, ev.getEmptyBucket())) {
			ev.setCanceled(true);
			ev.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public void onPlayerWakeup(final PlayerWakeUpEvent ev) {
		EntityPlayer pl = ev.getEntityPlayer();

		if (!pl.world.isRemote && pl.world.provider.getDimension() == 0) {
			PlayerUtil.getAdventPlayer(pl).stats().resetAllTribute();

			if (pl.world instanceof WorldServer)
				OverworldEvents.doWorldStartCheck(pl.world);
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent ev) {
		if (ev.player instanceof EntityPlayerMP && !ev.player.world.isRemote) {
			UUID uuid = ev.player.getGameProfile().getId();
			String msg = null;

			if (AdventOfAscension.instance().isTslat(uuid)) {
				msg = TextFormatting.DARK_RED + "It begins...Is this the end?";

				((WorldServer)ev.player.world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, ev.player.posX, ev.player.posY + 0.2, ev.player.posZ, 16, 0.5, 0.5, 0.5, 0.1);
			}
			else if (uuid.equals(UUID.fromString("010318ef-28fc-4c7c-8940-2f0d62eabfa6"))) {
				msg = TextFormatting.LIGHT_PURPLE + "Xolova creeps in to watch you suffer. Feel free to die now.";
			}
			else if (PlayerCrownHandler.isCrazyDonator(uuid)) {
				msg = TextFormatting.LIGHT_PURPLE + "They approach. Tremble before them.";
			}

			if (msg != null)
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TextComponentString(msg));

			OverworldEvents.alertNewPlayer(ev.player.world, ev.player);

			PlayerDataManager plData = PlayerUtil.getAdventPlayer(ev.player);
			PlayerDataManager.PlayerStats stats = plData.stats();

			for (Enums.Skills sk : Enums.Skills.values()) {
				PacketUtil.network.sendTo(new PacketSkillData(sk.id, stats.getLevelForDisplay(sk), stats.getExp(sk), stats.getSkillData(Enums.Skills.EXPEDITION)), (EntityPlayerMP)ev.player);
			}

			EntityUtil.applyAttributeModifierSafely(ev.player, SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.innervationHealthBuff(InnervationUtil.getHealthBuff(stats.getLevel(Enums.Skills.INNERVATION))));
			PacketUtil.network.sendTo(new PacketTributeData(stats.getTribute(Enums.Deities.EREBON), stats.getTribute(Enums.Deities.LUXON), stats.getTribute(Enums.Deities.PLUTON), stats.getTribute(Enums.Deities.SELYAN)), (EntityPlayerMP)ev.player);
			PacketUtil.network.sendTo(new PacketResourceData(stats.getResourceValue(Enums.Resources.CREATION), stats.getResourceValue(Enums.Resources.ENERGY), stats.getResourceValue(Enums.Resources.RAGE), stats.getResourceValue(Enums.Resources.SOUL), plData.isRevengeActive()), (EntityPlayerMP)ev.player);
			PlayerCrownHandler.syncWithNewClient((EntityPlayerMP)ev.player);

			Advancement rootAdv = ModUtil.getAdvancement("overworld/root");

			if (rootAdv != null) {
				PlayerAdvancements plAdvancements = ((EntityPlayerMP)ev.player).getAdvancements();

				if (!plAdvancements.getProgress(rootAdv).isDone()) {
					plAdvancements.grantCriterion(ModUtil.getAdvancement("overworld/by_the_books"), "legitimate");
					plAdvancements.grantCriterion(rootAdv, "playerjoin");
				}
			}
			else {
				AdventOfAscension.logMessage(Level.WARN, "Unable to find inbuilt advancements, another mod is breaking things. This may cause issues");

				if (ConfigurationUtil.MainConfig.doVerboseDebugging) {
					AdventOfAscension.logOptionalMessage("Printing out current advancements list...");
					FMLCommonHandler.instance().getMinecraftServerInstance().getAdvancementManager().getAdvancements().forEach(advancement -> AdventOfAscension.logOptionalMessage(advancement.getId().toString()));
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(final ItemTossEvent ev) {
		World world = ev.getPlayer().getEntityWorld();

		if (!world.isRemote) {
			EntityItem entityItem = ev.getEntityItem();
			Item item = entityItem.getItem().getItem();

			if (item == ItemRegister.realmstoneBlank) {
				if (entityItem.isInLava())
					ItemUtil.givePlayerItemOrDrop(ev.getPlayer(), new ItemStack(ItemRegister.realmstoneNether));
			}
			else if (item instanceof BossSpawningItem) {
				if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
					PlayerUtil.getAdventPlayer(ev.getPlayer()).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");
					return;
				}

				ev.setCanceled(true);
				world.spawnEntity(EntityUtil.newBossEntityItemFromExisting(entityItem, ev.getPlayer()));

				BossSpawningItem bossItem = (BossSpawningItem)item;

				if (bossItem.getThrowingSound() != null)
					world.playSound(null, entityItem.posX, entityItem.posY, entityItem.posZ, bossItem.getThrowingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerPickupXp(final PlayerPickupXpEvent ev) {
		EntityPlayer pl = ev.getEntityPlayer();

		if (!pl.world.isRemote && ev.getOrb().xpValue > 0) {
			int i = ItemUtil.findItemInInventory(pl, ItemRegister.expFlask);

			if (i < 0)
				return;

			ItemStack stack = pl.inventory.getStackInSlot(i);

			ExpFlask.addExp((EntityPlayerMP)ev.getEntityPlayer(), i, stack, ev.getOrb().xpValue);
			ev.setCanceled(true);
			ev.getOrb().xpValue = 0;
			ev.getOrb().setDead();
		}
	}

	@SubscribeEvent
	public void onPlayerFishing(final ItemFishedEvent ev) {
		if (ev.getEntityPlayer().world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.lborean && ev.getEntityPlayer().getRNG().nextInt(10) == 0)
			ev.getDrops().add(new ItemStack(ItemRegister.callOfTheDrake));
	}
}
