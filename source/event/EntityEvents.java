package net.tslat.aoa3.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.misc.EntityHeartStone;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;
import net.tslat.aoa3.utils.skills.InnervationUtil;

import java.util.Random;

public class EntityEvents {
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent ev) {
		EntityLivingBase entity = ev.getEntityLiving();

		if (!entity.world.isRemote && !(entity instanceof EntityPlayer)) {
			Entity killer = ev.getSource().getTrueSource();

			if (killer != null) {
				EntityPlayer killerPlayer = null;

				if (killer instanceof EntityTameable) {
					EntityLivingBase owner = ((EntityTameable)killer).getOwner();

					if (owner instanceof EntityPlayer)
						killerPlayer = (EntityPlayer)owner;
				}
				else if (killer instanceof EntityPlayer) {
					killerPlayer = (EntityPlayer)killer;
				}

				if (killerPlayer != null) {
					if (entity.getMaxHealth() > 1 && AdventOfAscension.rand.nextInt(8 * (entity instanceof IMob ? 1 : 3)) == 0 && InnervationUtil.canEntitySpawnHeartstone(entity)) {
						EntityHeartStone heartStone = new EntityHeartStone(entity.world, entity.getPosition());

						entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundsRegister.HEART_STONE_SPAWN, SoundCategory.MASTER, 1.0f, 1.0f);
						entity.world.spawnEntity(heartStone);
					}

					if (entity.world.provider.getDimension() == -1 && ev.getSource().damageType.contains("explosion") && AdventOfAscension.rand.nextInt(4) == 0)
						entity.entityDropItem(new ItemStack(ItemRegister.EXPLOSIVE_IDOL), 0);

					float hunterXp = HunterUtil.getHunterXp(entity);

					if (hunterXp > 0)
						PlayerUtil.giveXpToPlayer(killerPlayer, Enums.Skills.HUNTER, hunterXp);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingEvent.LivingUpdateEvent ev) {
		if (ev.getEntityLiving().world.isRemote && ConfigurationUtil.MainConfig.funOptions.partyDeaths && ev.getEntityLiving().deathTime >= 19) {
			AxisAlignedBB boundingBox = ev.getEntity().getEntityBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;
			Random rand = AdventOfAscension.rand;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				AdventOfAscension.proxy.spawnParticle(4, ev.getEntity().world, boundingBox.minX + rand.nextDouble() * width, boundingBox.minY + rand.nextDouble() * height, boundingBox.minZ + rand.nextDouble() * depth, rand.nextGaussian() * 0.05, 0, rand.nextGaussian() * 0.05, 3, 0.3f);
			}
		}
	}

	@SubscribeEvent
	public void onSpawnerSpawn(LivingSpawnEvent.SpecialSpawn ev) {
		if (ev.getSpawner() != null && HunterUtil.isHunterCreature(ev.getEntityLiving()))
			ev.getEntityLiving().getEntityData().setBoolean("IsHunterSpawnerMob", true);
	}

	@SubscribeEvent
	public void onLivingTarget(LivingSetAttackTargetEvent ev) {
		if (!ev.getEntityLiving().world.isRemote && ev.getEntityLiving() instanceof EntityLiving && (ev.getTarget() instanceof EntityPlayer || ev.getTarget() instanceof EntityTameable)) {
			int hunterLvl = HunterUtil.getHunterLevel(ev.getEntityLiving());
			EntityPlayer pl;

			if (hunterLvl > 1 && !ev.getEntityLiving().getEntityData().getBoolean("IsHunterSpawnerMob")) {
				pl = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getTarget());

				if (pl != null && PlayerUtil.getAdventPlayer(pl).stats().getLevel(Enums.Skills.HUNTER) < hunterLvl) {
					((EntityLiving)ev.getEntityLiving()).setAttackTarget(null);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent ev) {
		if (!HunterUtil.canAttackTarget(ev.getEntityLiving(), ev.getSource().getTrueSource(), true))
			ev.setCanceled(true);
	}

	// Patch in block protection for Wither & EnderDragon to fix vanilla shortfalls
	@SubscribeEvent
	public void onEntityDestroyBlock(LivingDestroyBlockEvent ev) {
		if (ev.getEntity() instanceof EntityFireball) {
			if (ev.getState().getBlock().getExplosionResistance(ev.getEntity()) >= 1000000)
				ev.setCanceled(true);
		}
		else if (ev.getState().getBlockHardness(ev.getEntity().getEntityWorld(), ev.getPos()) < 0) {
			ev.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent ev) {
		if (!ev.getWorld().isRemote) {
			if (ev.getEntity() instanceof EntityFishHook) {
				EntityFishHook hook = (EntityFishHook)ev.getEntity();
				EntityPlayerMP fisher = (EntityPlayerMP)hook.getAngler();

				if (fisher != null && PlayerUtil.isWearingFullSet(fisher, Enums.ArmourSets.HAULING)) {
					ItemStack stack = fisher.getHeldItem(EnumHand.MAIN_HAND);

					if (!(stack.getItem() instanceof ItemFishingRod))
						stack = fisher.getHeldItem(EnumHand.OFF_HAND);

					if (stack.getItem() instanceof ItemFishingRod)
						hook.setLureSpeed(Math.min(5, 2 + EnchantmentHelper.getFishingSpeedBonus(stack)));
				}
			}
			else if (ev.getEntity() instanceof EntityWither) {
				if (ev.getWorld().provider.getDimension() == -1) {
					for (EntityPlayer pl : ev.getWorld().getEntitiesWithinAABB(EntityPlayer.class, ev.getEntity().getEntityBoundingBox().grow(50))) {
						if (ItemUtil.findInventoryItem(pl, new ItemStack(ItemRegister.BLANK_REALMSTONE), true, 1))
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.ABYSS_REALMSTONE));
					}
				}
			}

		}
	}

	@SubscribeEvent
	public void onLootDrops(LivingDropsEvent ev) {
		if (!ev.getEntityLiving().world.isRemote) {
			if (ev.getEntityLiving().world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern && ev.getEntityLiving().isNonBoss()) {
				ev.getDrops().clear();
				ev.setCanceled(true);

				return;
			}

			if (ev.getEntityLiving() instanceof EntityWither) { // Patch in a loot table for Wither to fix vanilla shortfalls
				EntityWither wither = (EntityWither)ev.getEntityLiving();
				LootTable lootTable = wither.world.getLootTableManager().getLootTableFromLocation(LootSystemRegister.entityWither);
				LootContext.Builder builder = (new LootContext.Builder((WorldServer)wither.world)).withLootedEntity(wither).withDamageSource(ev.getSource());

				if (ev.isRecentlyHit() && wither.getAttackingEntity() instanceof EntityPlayer)
					builder = builder.withPlayer((EntityPlayer)wither.getAttackingEntity()).withLuck(((EntityPlayer)wither.getAttackingEntity()).getLuck());

				for (ItemStack stack : lootTable.generateLootForPools(wither.getRNG(), builder.build())) {
					wither.entityDropItem(stack, 0);
				}
			}

			if (HunterUtil.isHunterCreature(ev.getEntityLiving())) {
				if (!ev.isRecentlyHit() || !(ev.getSource().getTrueSource() instanceof EntityPlayer) || (!((EntityPlayer)ev.getSource().getTrueSource()).capabilities.isCreativeMode && !HunterUtil.doesPlayerMeetHunterReq(ev.getEntityLiving(), (EntityPlayer)ev.getSource().getTrueSource()))) {
					ev.getDrops().clear();
					ev.setCanceled(true);
				}
			}
		}
	}
}
