package net.tslat.aoa3.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.misc.HeartStoneEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.HunterUtil;
import net.tslat.aoa3.util.skill.InnervationUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class EntityEvents {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent ev) {
		LivingEntity entity = ev.getEntityLiving();

		if (!entity.level.isClientSide && !(entity instanceof PlayerEntity)) {
			Entity killer = ev.getSource().getEntity();

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
						HeartStoneEntity heartStone = new HeartStoneEntity(entity.level, entity.blockPosition());

						entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AoASounds.HEART_STONE_SPAWN.get(), SoundCategory.MASTER, 1.0f, 1.0f);
						entity.level.addFreshEntity(heartStone);
					}

					if (WorldUtil.isWorld(ev.getEntity().level, AoADimensions.NETHER.key) && ev.getSource().msgId.contains("explosion") && RandomUtil.oneInNChance(4))
						entity.spawnAtLocation(new ItemStack(AoAItems.EXPLOSIVE_IDOL.get()), 0);

					float hunterXp = HunterUtil.getHunterXp(entity);

					if (hunterXp == -1 && !PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)killerPlayer, Skills.HUNTER, 10))
						hunterXp = (float)RandomUtil.randomValueBetween(2, 10);

					if (hunterXp > 0)
						PlayerUtil.giveXpToPlayer((ServerPlayerEntity)killerPlayer, Skills.HUNTER, hunterXp);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityUpdate(LivingEvent.LivingUpdateEvent ev) {
		if (ev.getEntityLiving().level.isClientSide && AoAConfig.CLIENT.partyDeaths.get() && ev.getEntityLiving().deathTime >= 19) {
			AxisAlignedBB boundingBox = ev.getEntity().getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				ev.getEntityLiving().level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.03f, 3, 0), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
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
		if (!ev.getEntityLiving().level.isClientSide && ev.getEntityLiving() instanceof MobEntity && (ev.getTarget() instanceof PlayerEntity || ev.getTarget() instanceof TameableEntity)) {
			int hunterLvl = HunterUtil.getHunterLevel(ev.getEntityLiving());
			PlayerEntity pl;

			if (hunterLvl > 1 && !ev.getEntityLiving().getPersistentData().getBoolean("IsHunterSpawnerMob")) {
				pl = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getTarget());

				if (pl instanceof ServerPlayerEntity && !PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)pl, Skills.HUNTER, hunterLvl))
					((MobEntity)ev.getEntityLiving()).setTarget(null);
			}
		}
	}

	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent ev) {
		if (!HunterUtil.canAttackTarget(ev.getEntityLiving(), ev.getSource().getEntity(), true))
			ev.setCanceled(true);
	}

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent ev) {
		if (!ev.getWorld().isClientSide) {
			if (ev.getEntity() instanceof FishingBobberEntity) {
				FishingBobberEntity hook = (FishingBobberEntity)ev.getEntity();
				Entity fisher = hook.getOwner();

				if (fisher instanceof ServerPlayerEntity && PlayerUtil.isWearingFullSet((ServerPlayerEntity)fisher, AdventArmour.Type.HAULING)) {
					ItemStack stack = ((ServerPlayerEntity)fisher).getItemInHand(Hand.MAIN_HAND);

					if (!(stack.getItem() instanceof FishingRodItem))
						stack = ((ServerPlayerEntity)fisher).getItemInHand(Hand.OFF_HAND);

					if (stack.getItem() instanceof FishingRodItem)
						hook.lureSpeed = Math.min(5, 2 + EnchantmentHelper.getFishingSpeedBonus(stack));
				}
			}
			else if (ev.getEntity() instanceof WitherEntity && ((WitherEntity)ev.getEntity()).getInvulnerableTicks() == 220) {
				if (WorldUtil.isWorld(ev.getWorld(), AoADimensions.NETHER.key)) {
					for (PlayerEntity pl : ev.getWorld().getEntitiesOfClass(PlayerEntity.class, ev.getEntity().getBoundingBox().inflate(50))) {
						if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.ABYSS_REALMSTONE.get()));
					}
				}
			}

		}
	}

	@SubscribeEvent
	public static void onLootDrops(LivingDropsEvent ev) {
		if (!ev.getEntityLiving().level.isClientSide) {
			if (WorldUtil.isWorld(ev.getEntityLiving().level, AoADimensions.ANCIENT_CAVERN.key) && ev.getEntityLiving().canChangeDimensions()) {
				ev.getDrops().clear();
				ev.setCanceled(true);

				return;
			}

			if (HunterUtil.isHunterCreature(ev.getEntityLiving())) {
				if (!ev.isRecentlyHit() || !(ev.getSource().getEntity() instanceof PlayerEntity) || (!HunterUtil.canAttackTarget(ev.getEntityLiving(), ev.getSource().getEntity(), false))) {
					ev.getDrops().clear();
					ev.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onEntityExploded(ExplosionEvent.Detonate ev) {
		if (AoAConfig.SERVER.saveLootFromExplosions.get())
			ev.getAffectedEntities().removeIf(entity -> entity instanceof ItemEntity && entity.tickCount < 20);
	}
}
