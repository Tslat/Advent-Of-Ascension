package net.tslat.aoa3.event;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.generation.stone.StoneBlock;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.misc.EntityBloodlust;
import net.tslat.aoa3.event.dimension.*;
import net.tslat.aoa3.item.armour.HaulingArmour;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.*;
import net.tslat.aoa3.utils.skills.*;

import java.util.Random;

public class PlayerEvents {
	@SubscribeEvent
	public void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.player);

			cap.tickPlayer();

			if (!ev.player.world.isRemote && !ev.player.capabilities.isCreativeMode && ev.player.onGround && !ev.player.isRiding())
				ExpeditionUtil.handleRunningTick(ev, cap);

			if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.shyrelands) {
				ShyrelandsEvents.doPlayerTick(cap);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.lelyetia) {
				LelyetiaEvents.doPlayerTick(cap);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.voxPonds) {
				VoxPondsEvents.doPlayerTick(cap);
			}
			else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.candyland) {
				CandylandEvents.doPlayerTick(cap);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHit(final LivingAttackEvent ev) {
		if (ev.getEntity() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntity());

			if (!ev.getEntity().world.isRemote && EntityUtil.isPhysicalDamage(ev.getSource(), ev.getEntity(), ev.getAmount()))
				InnervationUtil.tryDodge(ev);

			if (cap.getArmourSet() != null)
				cap.getArmourSet().handleAttackImmunities(ev, cap);
		}
	}

	@SubscribeEvent
	public void onPlayerDamaged(final LivingDamageEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntityLiving());

			if (ev.getEntityLiving().getHealth() > 0.0f) {
				if (cap.getArmourSet() != null) {
					cap.getArmourSet().handleDamageTriggers(ev, cap);
				}

				if (ev.getSource().getTrueSource() instanceof EntityLivingBase && AdventOfAscension.rand.nextInt(10) == 0)
					cap.enableRevenge((EntityLivingBase)ev.getSource().getTrueSource());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHurt(final LivingHurtEvent ev) {
		if (ev.getEntityLiving() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getEntityLiving());

			if (cap.getArmourSet() != null)
				cap.getArmourSet().handleDamageReductions(ev, cap);
		}
		else if (ev.getSource().getTrueSource() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getSource().getTrueSource());

			if (cap.getArmourSet() != null)
				cap.getArmourSet().handleAttackBuffs(ev, cap);

			if (EntityUtil.isMeleeDamage(ev.getSource()) && !ev.getSource().isDamageAbsolute()) {
				ButcheryUtil.tryCritical(ev, cap);

				if (AdventOfAscension.rand.nextInt(30) == 0)
					ev.getEntity().world.spawnEntity(new EntityBloodlust(ev.getEntity().world, ev.getEntity().getPosition()));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerFall(final LivingFallEvent ev) {
		if (ev.getEntity() instanceof EntityPlayer) {
			final EntityPlayer pl = (EntityPlayer)ev.getEntity();

			if (!pl.world.isRemote) {
				AdventPlayerCapability cap = (AdventPlayerCapability)pl.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

				switch (cap.getArmourSetType()) {
					case ALACRITY:
					case AMETHIND:
					case HUNTER:
					case LUNAR:
					case ROCKBONE:
						ev.setCanceled(true);
						break;
					default:
						ExpeditionUtil.handleFallEvent(ev, cap);
						break;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDeath(final LivingDeathEvent ev) {
		if (ev.getEntity() instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)ev.getEntity();
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(pl);

			cap.resetAllTribute();

			if (!pl.world.isRemote) {
				if (!pl.world.getGameRules().getBoolean("keepInventory")) {
					for (int i = 0; i < pl.inventory.getSizeInventory(); i++) {
						ItemStack stack = pl.inventory.getStackInSlot(i);

						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.intervention, stack) > 0) {
							cap.interventionStacks.add(ItemUtil.removeEnchantment(stack, EnchantmentsRegister.intervention));
							pl.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
						}
					}

					for (int i = 0; i < 4; i++) {
						ItemStack stack = pl.inventory.armorInventory.get(i);

						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.intervention, stack) > 0) {
							cap.interventionStacks.add(ItemUtil.removeEnchantment(stack, EnchantmentsRegister.intervention));
							pl.inventory.armorInventory.set(i, ItemStack.EMPTY);
						}
					}
				}

				if (pl instanceof EntityPlayerMP) {
					if (ev.getSource().damageType.equals("vox_ponds"))
						ModUtil.completeAdvancement((EntityPlayerMP)pl, "voxponds/oops", "atmosphere_death");
				}
			}


			if (!ev.getEntity().isDead && ev.getSource().getTrueSource() instanceof EntityLivingBase)
				ev.getSource().getTrueSource().onKillEntity((EntityPlayer)ev.getEntity());
		}
		else if (ev.getEntity().world.provider.getDimension() == 0 && ev.getEntity().world.isDaytime() && ev.getSource().getTrueSource() instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)ev.getSource().getTrueSource());

			cap.addTribute(Enums.Deities.EREBON, 8);
			cap.addTribute(Enums.Deities.LUXON, -8);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent ev) {
		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.player);

		if (!cap.interventionStacks.isEmpty()) {
			for (ItemStack stack : cap.interventionStacks) {
				ItemUtil.givePlayerItemOrDrop(ev.player, stack);
			}

			cap.interventionStacks.clear();
		}
	}

	@SubscribeEvent
	public void onPlayerClone(final PlayerEvent.Clone ev) {
		AdventPlayerCapability oldCap = (AdventPlayerCapability)ev.getOriginal().getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);
		NBTTagCompound oldTag = oldCap.saveNBTData();
		AdventPlayerCapability newCap = (AdventPlayerCapability)ev.getEntityPlayer().getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);
		newCap.loadNBTData(oldTag);

		newCap.interventionStacks = oldCap.interventionStacks;
	}

	@SubscribeEvent
	public void onWorldChange(final net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent ev) {
		if (ev.fromDim != 0) {
			PlayerUtil.getAdventPlayer(ev.player).resetAllTribute();
		}
		else {
			World world = DimensionManager.getWorld(ev.fromDim);

			if (!world.isDaytime()) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.player);
				long timeRemaining = 24000L - world.getWorldTime();

				if (cap.getTribute(Enums.Deities.LUXON) == 200)
					ev.player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, (int)timeRemaining, 0, true, false));

				if (cap.getTribute(Enums.Deities.EREBON) == 200)
					ev.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (int)timeRemaining, 0, true, false));

				if (cap.getTribute(Enums.Deities.PLUTON) == 200)
					ev.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, (int)timeRemaining, 1, true, false));

				if (cap.getTribute(Enums.Deities.SELYAN) == 200)
					ev.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, (int)timeRemaining, 0, true, false));
			}
		}
	}

	@SubscribeEvent
	public void onBlockHarvest(final BlockEvent.HarvestDropsEvent ev) {
		if (ev.getWorld().isRemote || ev.getHarvester() == null || ev.getHarvester() instanceof FakePlayer)
			return;

		Item tool = ev.getHarvester().getHeldItem(EnumHand.MAIN_HAND).getItem();

		if (tool instanceof SpecialHarvestTool)
			((SpecialHarvestTool)tool).doHarvestEffect(ev);

		Block bl = ev.getState().getBlock();

		if (bl instanceof BlockCrops || bl instanceof BlockFlower || bl instanceof BlockVine || bl instanceof BlockLeaves) {
			if (bl instanceof BlockCrops)
				PlayerUtil.getAdventPlayer(ev.getHarvester()).addTribute(Enums.Deities.SELYAN, 2);

			if (bl instanceof BlockLeaves ? AdventOfAscension.rand.nextInt(35) == 0 : bl instanceof BlockCrops ? (((BlockCrops)bl).isMaxAge(ev.getState()) && AdventOfAscension.rand.nextInt(8) == 0) : AdventOfAscension.rand.nextInt(8) == 0) {
				EntityItem animaDrop = new EntityItem(ev.getWorld(), ev.getPos().getX(), ev.getPos().getY(), ev.getPos().getZ(), new ItemStack(ItemRegister.animaStone));

				ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.heartStoneSpawn, SoundCategory.MASTER, 1.0f, 1.0f);
				ev.getWorld().spawnEntity(animaDrop);
			}
		}
		else if (bl == Blocks.STONE || bl == Blocks.NETHERRACK || bl instanceof StoneBlock || bl == Blocks.END_STONE) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.getHarvester());
			int lvl = cap.getLevel(Enums.Skills.FORAGING);

			if (bl != Blocks.NETHERRACK || AdventOfAscension.rand.nextBoolean()) {
				if (ForagingUtil.shouldGetLoot(lvl)) {
					ForagingUtil.ForagingDrop loot = ForagingUtil.getLoot(lvl);
					EntityItem entityItem = new EntityItem(ev.getWorld());
					ItemStack lootDrop = loot.getLootStack();

					if (cap.getArmourSetType() == Enums.ArmourSets.FORAGING)
						lootDrop.setCount(lootDrop.getCount() * 2);

					entityItem.setItem(lootDrop);
					entityItem.setPosition(ev.getPos().getX() + 0.5, ev.getPos().getY(), ev.getPos().getZ() + 0.5);
					ev.getWorld().spawnEntity(entityItem);
					cap.addXp(Enums.Skills.FORAGING, loot.xp, false);
					ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.foragingLoot, SoundCategory.MASTER, 1.0f, 1.0f);

					if (ev.getWorld().provider.getDimension() == 0)
						cap.addTribute(Enums.Deities.PLUTON, 11 - lvl / 10);
				}
			}
		}
		else if (bl instanceof BlockLog) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.getHarvester());
			int lvl = cap.getLevel(Enums.Skills.LOGGING);

			if (LoggingUtil.shouldGetLoot(lvl)) {
				if (AdventOfAscension.rand.nextBoolean()) {
					LoggingUtil.LoggingDrop loot = LoggingUtil.getLoot(lvl);
					EntityItem entityItem = new EntityItem(ev.getWorld());
					ItemStack lootDrop = loot.getLootStack();

					if (cap.getArmourSetType() == Enums.ArmourSets.LOGGING)
						lootDrop.setCount(lootDrop.getCount() * 2);

					entityItem.setItem(lootDrop);
					entityItem.setPosition(ev.getPos().getX() + 0.5, ev.getPos().getY(), ev.getPos().getZ() + 0.5);
					ev.getWorld().spawnEntity(entityItem);
					cap.addXp(Enums.Skills.LOGGING, loot.xp, false);
				}
				else {

					for (ItemStack stack : ev.getDrops()) {
						if (stack.getItem() == Item.getItemFromBlock(bl)) {
							stack = stack.copy();

							stack.setCount(lvl > 50 ? 2 : 1);

							EntityItem item = new EntityItem(ev.getWorld(), ev.getPos().getX(), ev.getPos().getY(), ev.getPos().getZ(), stack);

							ev.getWorld().spawnEntity(item);
							cap.addXp(Enums.Skills.LOGGING, (float)Math.pow(lvl, 1.65D) * 3, false);
							break;
						}
					}

					if (ev.getWorld().provider.getDimension() == 0)
						cap.addTribute(Enums.Deities.PLUTON, 11 - lvl / 10);

					ev.getWorld().playSound(null, ev.getPos(), SoundsRegister.foragingLoot, SoundCategory.MASTER, 1.0f, 1.0f);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(final BlockEvent.BreakEvent ev) {
		if (!ev.getPlayer().capabilities.isCreativeMode && WorldUtil.isBlockProtectedWorld(ev.getWorld().provider.getDimension()))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public void onBlockPlace(final BlockEvent.PlaceEvent ev) {
		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.getPlayer());

		if (!ev.getPlayer().capabilities.isCreativeMode && WorldUtil.isBlockProtectedWorld(ev.getWorld().provider.getDimension())) {
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onBlockInteract(final PlayerInteractEvent.RightClickBlock ev) {
		if (!ev.getEntityPlayer().capabilities.isCreativeMode && WorldUtil.isBlockProtectedWorld(ev.getWorld().provider.getDimension())) {
			Item item = ev.getItemStack().getItem();

			if (item instanceof ItemBlock || item instanceof ItemBucket || item instanceof ItemArmorStand || item instanceof ItemHangingEntity) {
				ev.setCancellationResult(EnumActionResult.SUCCESS);
				ev.setUseItem(Event.Result.DENY);
				ev.setResult(Event.Result.DENY);
				ev.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onEmptyBucketUse(final FillBucketEvent ev) {
		if (!ev.getEntityPlayer().capabilities.isCreativeMode && WorldUtil.isBlockProtectedWorld(ev.getWorld().provider.getDimension())) {
			ev.setCanceled(true);
			ev.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public void onPlayerPickup(final net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent ev) {
		if (!ev.player.world.isRemote) {
			if (ev.getStack().getItem() == ItemRegister.animaStone) {
				AnimaUtil.doAnimaStonePickup(ev);
			}
			else if (ev.getStack().getItem() == ItemRegister.heartStone) {
				InnervationUtil.doHeartStonePickup(ev);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerFish(final ItemFishedEvent ev) {
		if (!ev.getDrops().isEmpty() && ev.getDrops().get(0).getItem() instanceof ItemFishFood) {
			if (AdventOfAscension.rand.nextInt(3) == 0 && !(ev.getEntityPlayer() instanceof FakePlayer)) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(ev.getEntityPlayer());
				int lvl = cap.getLevel(Enums.Skills.HAULING);
				HaulingUtil.HaulingDrop loot = HaulingUtil.getLoot(lvl);

				if (cap.getArmourSetType() == Enums.ArmourSets.HAULING)
					loot = HaulingArmour.doLootBonusCheck(cap, loot);

				EntityFishHook lure = ev.getHookEntity();
				BlockPos lurePos = lure.getPosition();
				BlockPos playerPos = ev.getEntityPlayer().getPosition();
				EntityItem lootEntity = new EntityItem(ev.getHookEntity().world, lurePos.getX(), lurePos.getY(), lurePos.getZ(), loot.getLootStack());
				int distX = playerPos.getX() - lurePos.getX();
				int distY = playerPos.getY() - lurePos.getY();
				int distZ = playerPos.getZ() - lurePos.getZ();
				double hyp = MathHelper.sqrt(distX * distX + distY * distY + distZ * distZ);
				lootEntity.motionX = distX * 0.1D;
				lootEntity.motionY = distY * 0.1D + (double)MathHelper.sqrt(hyp) * 0.12D;
				lootEntity.motionZ = distZ * 0.1D;

				lure.world.spawnEntity(lootEntity);
				lure.world.spawnEntity(new EntityXPOrb(lure.world, playerPos.getX(), playerPos.getY() + 0.5D, playerPos.getZ(), AdventOfAscension.rand.nextInt(10) + 2));
				cap.addXp(Enums.Skills.HAULING, loot.xp, false);
				ev.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerWakeup(final PlayerWakeUpEvent ev) {
		EntityPlayer pl = ev.getEntityPlayer();

		if (!pl.world.isRemote && pl.world.provider.getDimension() == 0) {
			if (pl.world instanceof WorldServer) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(pl);

				cap.resetAllTribute();
				OverworldEvents.doWorldStartCheck(pl.world);
			}
		}
	}
}
