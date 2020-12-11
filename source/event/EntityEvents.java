package net.tslat.aoa3.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.misc.HeartStoneEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.HunterUtil;
import net.tslat.aoa3.util.skill.InnervationUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class EntityEvents {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent ev) {
		LivingEntity entity = ev.getEntityLiving();

		if (!entity.world.isRemote && !(entity instanceof PlayerEntity)) {
			Entity killer = ev.getSource().getTrueSource();

			if (killer != null) {
				PlayerEntity killerPlayer = null;

				if (killer instanceof TameableEntity) {
					LivingEntity owner = ((TameableEntity)killer).getOwner();

					if (owner instanceof PlayerEntity)
						killerPlayer = (PlayerEntity)owner;
				}
				else if (killer instanceof PlayerEntity) {
					killerPlayer = (PlayerEntity)killer;
				}

				if (killerPlayer instanceof ServerPlayerEntity) {
					if (entity.getMaxHealth() > 1 && RandomUtil.oneInNChance(EntityUtil.isHostileMob(entity) ? 8 : 24) && InnervationUtil.canEntitySpawnHeartstone(entity)) {
						HeartStoneEntity heartStone = new HeartStoneEntity(entity.world, entity.getPosition());

						entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), AoASounds.HEART_STONE_SPAWN.get(), SoundCategory.MASTER, 1.0f, 1.0f);
						entity.world.addEntity(heartStone);
					}

					if (entity.world.getDimension().getType() == DimensionType.THE_NETHER && ev.getSource().damageType.contains("explosion") && RandomUtil.oneInNChance(4))
						entity.entityDropItem(new ItemStack(AoAItems.EXPLOSIVE_IDOL.get()), 0);

					float hunterXp = HunterUtil.getHunterXp(entity);

					if (hunterXp > 0)
						PlayerUtil.giveXpToPlayer((ServerPlayerEntity)killerPlayer, Skills.HUNTER, hunterXp);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingEvent.LivingUpdateEvent ev) {
		if (ev.getEntityLiving().world.isRemote && AoAConfig.CLIENT.partyDeaths.get() && ev.getEntityLiving().deathTime >= 19) {
			AxisAlignedBB boundingBox = ev.getEntity().getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ev.getEntityLiving().world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.03f, 3, 0), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
			}
		}
	}

	@SubscribeEvent
	public static void onSpawnerSpawn(LivingSpawnEvent.SpecialSpawn ev) {
		if (ev.getSpawner() != null && HunterUtil.isHunterCreature(ev.getEntityLiving()))
			ev.getEntityLiving().getPersistentData().putBoolean("IsHunterSpawnerMob", true);
	}

	@SubscribeEvent
	public static void onLivingTarget(LivingSetAttackTargetEvent ev) {
		if (!ev.getEntityLiving().world.isRemote && ev.getEntityLiving() instanceof MobEntity && (ev.getTarget() instanceof PlayerEntity || ev.getTarget() instanceof TameableEntity)) {
			int hunterLvl = HunterUtil.getHunterLevel(ev.getEntityLiving());
			PlayerEntity pl;

			if (hunterLvl > 1 && !ev.getEntityLiving().getPersistentData().getBoolean("IsHunterSpawnerMob")) {
				pl = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getTarget());

				if (pl instanceof ServerPlayerEntity && !PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)pl, Skills.HUNTER, hunterLvl))
					((MobEntity)ev.getEntityLiving()).setAttackTarget(null);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent ev) {
		if (!HunterUtil.canAttackTarget(ev.getEntityLiving(), ev.getSource().getTrueSource(), true))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent ev) {
		if (!ev.getWorld().isRemote) {
			if (ev.getEntity() instanceof FishingBobberEntity) {
				FishingBobberEntity hook = (FishingBobberEntity)ev.getEntity();
				ServerPlayerEntity fisher = (ServerPlayerEntity)hook.getAngler();

				if (fisher != null && PlayerUtil.isWearingFullSet(fisher, AdventArmour.Type.HAULING)) {
					ItemStack stack = fisher.getHeldItem(Hand.MAIN_HAND);

					if (!(stack.getItem() instanceof FishingRodItem))
						stack = fisher.getHeldItem(Hand.OFF_HAND);

					if (stack.getItem() instanceof FishingRodItem)
						hook.lureSpeed = Math.min(5, 2 + EnchantmentHelper.getFishingSpeedBonus(stack));
				}
			}
			else if (ev.getEntity() instanceof WitherEntity) {
				if (ev.getWorld().getDimension().getType() == DimensionType.THE_NETHER) {
					for (PlayerEntity pl : ev.getWorld().getEntitiesWithinAABB(PlayerEntity.class, ev.getEntity().getBoundingBox().grow(50))) {
						if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.ABYSS_REALMSTONE.get()));
					}
				}
			}

		}
	}

	@SubscribeEvent
	public static void onLootDrops(LivingDropsEvent ev) {
		if (!ev.getEntityLiving().world.isRemote) {
			if (ev.getEntityLiving().world.getDimension().getType() == AoADimensions.ANCIENT_CAVERN.type() && ev.getEntityLiving().isNonBoss()) {
				ev.getDrops().clear();
				ev.setCanceled(true);

				return;
			}

			if (HunterUtil.isHunterCreature(ev.getEntityLiving())) {
				if (!ev.isRecentlyHit() || !(ev.getSource().getTrueSource() instanceof PlayerEntity) || (!HunterUtil.canAttackTarget(ev.getEntityLiving(), ev.getSource().getTrueSource(), false))) {
					ev.getDrops().clear();
					ev.setCanceled(true);
				}
			}
		}
	}
}
