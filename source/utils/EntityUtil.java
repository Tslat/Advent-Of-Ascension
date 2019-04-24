package net.tslat.aoa3.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityUtil {

	public static int getCurrentHealthPercent(EntityLivingBase entity) {
		float percentage = (entity.getHealth() / entity.getMaxHealth()) * 100;
		return (int)percentage;
	}

	public static boolean checkAboveHealthPercentThreshold(EntityLivingBase entity, int thresholdPercent) {
		if (entity.getHealth() <= 0)
			return false;

		return getCurrentHealthPercent(entity) >= thresholdPercent;
	}

	public static boolean checkBelowHealthPercentThreshold(EntityLivingBase entity, int thresholdPercent) {
		if (entity.getHealth() <= 0)
			return false;

		return getCurrentHealthPercent(entity) < thresholdPercent;
	}

	public static void healEntity(EntityLivingBase entity, float healAmount) {
		if (entity.getHealth() > 0.0f)
			entity.heal(healAmount);
	}

	public static boolean dealAoeDamage(@Nullable Entity indirectSource, EntityLivingBase attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new EntityDamageSourceIndirect("aoe", indirectSource, attacker).setMagicDamage();
		}
		else {
			damageSource = attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)attacker) : DamageSource.causeMobDamage(attacker);
		}

		if (bypassProtections) {
			damageSource.setDamageBypassesArmor();
			damageSource.setDamageIsAbsolute();
		}

		if (target.isEntityInvulnerable(damageSource))
			return false;

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);


			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(damageSource, dmg);
	}

	public static boolean dealBlasterDamage(EntityLivingBase attacker, Entity target, Entity shot, float dmg, boolean bypassProtections) {
		DamageSource damageSource = new EntityDamageSourceIndirect("blaster", shot, attacker);

		damageSource.setMagicDamage();

		if (bypassProtections) {
			damageSource.setDamageIsAbsolute();
			damageSource.setDamageBypassesArmor();
		}

		if (target.isEntityInvulnerable(damageSource) || checkMobProperty(target, Enums.MobProperties.BLASTER_IMMUNE))
			return false;

		if (target instanceof MultiPartEntityPart || target instanceof EntityEnderCrystal)
			return target.attackEntityFrom(damageSource, dmg);

		if (!(target instanceof EntityLivingBase))
			return false;

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}


		target.hurtResistantTime = 0;
		double velocityX = target.motionX;
		double velocityY = target.motionY;
		double velocityZ = target.motionZ;
		boolean success = target.attackEntityFrom(damageSource, dmg);
		target.motionX = velocityX;
		target.motionY = velocityY;
		target.motionZ = velocityZ;
		target.velocityChanged = false;
		return success;
	}

	public static boolean dealMeleeDamage(EntityLivingBase attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)attacker) : DamageSource.causeMobDamage(attacker);

		if (target.isEntityInvulnerable(damageSource) || checkMobProperty(target, Enums.MobProperties.MELEE_IMMUNE))
			return false;

		if (bypassProtections) {
			damageSource.setDamageBypassesArmor();
			damageSource.setDamageIsAbsolute();
		}

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;

		boolean success = target.attackEntityFrom(damageSource, dmg);

		target.hurtResistantTime = 0;
		return success;
	}

	public static boolean dealMagicDamage(@Nullable Entity indirectSource, EntityLivingBase attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new EntityDamageSourceIndirect("magic", indirectSource, attacker);
		}
		else {
			damageSource = attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)attacker) : DamageSource.causeMobDamage(attacker);
		}

		damageSource.setMagicDamage().setDamageBypassesArmor();

		if (bypassProtections)
			damageSource.setDamageIsAbsolute();

		if (target.isEntityInvulnerable(damageSource) || checkMobProperty(target, Enums.MobProperties.MAGIC_IMMUNE))
			return false;

		if (target instanceof MultiPartEntityPart || target instanceof EntityEnderCrystal)
			return target.attackEntityFrom(damageSource, dmg);

		if (!(target instanceof EntityLivingBase))
			return false;

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(damageSource, dmg);
	}

	public static void dealSelfHarmDamage(EntityLivingBase target, float dmg) {
		if (target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)
			return;

		if (target.getHealth() - dmg > 0.0f) {
			target.setHealth(target.getHealth() - dmg);
		}
		else {
			target.hurtResistantTime = 0;
			target.attackEntityFrom(new DamageSource("selfharm").setDamageIsAbsolute().setDamageBypassesArmor(), dmg);
		}
	}

	public static boolean dealGunDamage(Entity target, EntityLivingBase attacker, EntityThrowable bullet, float dmg) {
		DamageSource source = new EntityDamageSourceIndirect("gun", bullet, attacker).setProjectile();

		if (target.isEntityInvulnerable(source) || checkMobProperty(target, Enums.MobProperties.GUN_IMMUNE))
			return false;

		if (target instanceof MultiPartEntityPart || target instanceof EntityEnderCrystal)
			return target.attackEntityFrom(source, dmg);

		if (!(target instanceof EntityLivingBase))
			return false;

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;
		boolean success;

		if (AdventOfAscension.rand.nextInt(10) < 6) {
			double velocityX = target.motionX;
			double velocityY = target.motionY;
			double velocityZ = target.motionZ;
			success = target.attackEntityFrom(source, dmg);
			target.motionX = velocityX;
			target.motionY = velocityY;
			target.motionZ = velocityZ;
			target.velocityChanged = false;
		}
		else {
			success = target.attackEntityFrom(source, dmg);
			target.velocityChanged = true;
		}

		return success;
	}

	public static boolean dealVulcaneDamage(EntityLivingBase target, EntityPlayer attacker, float dmg) {
		DamageSource source = DamageSource.causePlayerDamage(attacker).setDamageIsAbsolute().setDamageBypassesArmor();

		if (target.isEntityInvulnerable(source))
			return false;

		if (target instanceof HunterEntity) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!attacker.capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(source, dmg);
	}

	public static boolean dealRangedDamage(Entity target, EntityLivingBase attacker, Entity projectile, float dmg) {
		DamageSource source = DamageSource.causeThrownDamage(attacker, projectile);

		if (target.isEntityInvulnerable(source) || ((projectile instanceof EntityArrow || projectile instanceof EntityThrowable) && checkMobProperty(target, Enums.MobProperties.RANGED_IMMUNE)))
			return false;

		if (target instanceof MultiPartEntityPart || target instanceof EntityEnderCrystal)
			return target.attackEntityFrom(source, dmg);

		if (!(target instanceof EntityLivingBase))
			return false;

		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return false;
			}
		}

		target.hurtResistantTime = 0;
		target.velocityChanged = true;
		return target.attackEntityFrom(source, dmg);
	}

	public static boolean isSpecExempt(Entity target, EntityLivingBase attacker) {
		if (target instanceof HunterEntity && attacker instanceof EntityPlayer) {
			AdventPlayerCapability cap = (AdventPlayerCapability)attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (!((EntityPlayer)attacker).capabilities.isCreativeMode && cap.getLevel(Enums.Skills.HUNTER) < ((HunterEntity)target).getHunterReq()) {
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((HunterEntity)target).getHunterReq())));
				return true;
			}
		}

		return target instanceof EntityPlayer || target instanceof EntityWither || target instanceof EntityDragon || target instanceof BossEntity || target.getIsInvulnerable() || (target instanceof EntityLivingBase && ((EntityLivingBase)target).getMaxHealth() > 500);

	}

	public static void doScaledKnockback(EntityLivingBase target, Entity attacker, float strength, double xRatio, double zRatio) {
		if (target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.isCreativeMode)
			return;

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, attacker, strength, xRatio, zRatio);

		if(event.isCanceled())
			return;

		strength = event.getStrength() * (float)(1 - target.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue());
		xRatio = event.getRatioX();
		zRatio = event.getRatioZ();
		target.isAirBorne = true;
		target.velocityChanged = true;
		float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
		target.motionX /= 2.0D;
		target.motionZ /= 2.0D;
		target.motionX -= xRatio / (double)f * (double)strength;
		target.motionZ -= zRatio / (double)f * (double)strength;

		if (target.onGround) {
			target.motionY /= 2.0D;
			target.motionY += (double)strength;

			if (target.motionY > 0.4000000059604645D)
			{
				target.motionY = 0.4000000059604645D;
			}
		}
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getTrueSource() != null && !source.isProjectile() && !source.getDamageType().equals("thrown") && !source.isMagicDamage() && !source.isExplosion() && !source.isFireDamage() && source.getImmediateSource() == source.getTrueSource();
	}

	public static boolean isMagicDamage(DamageSource source, Entity target, float dmg) {
		return (source.isMagicDamage() && !source.getDamageType().equals("blaster")) && !source.getDamageType().equals("thorns") && !(target instanceof EntityLivingBase && isPoisonDamage(source, target, dmg));
	}

	public static boolean isRangedDamage(DamageSource source, Entity target, float dmg) {
		return ((source.isProjectile() && !source.getDamageType().equals("gun")) || source.getDamageType().equals("thrown")) && !isMagicDamage(source, target, dmg);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.getDamageType().equals("gun") && source.isProjectile();
	}

	public static boolean isPoisonDamage(DamageSource source, Entity target, float dmg) {
		return source.isMagicDamage() && source.getTrueSource() == null && !source.getDamageType().equals("thorns") && target instanceof EntityLivingBase && ((EntityLivingBase)target).isPotionActive(MobEffects.POISON) && dmg == 1.0f;
	}

	public static boolean isPhysicalDamage(DamageSource source, Entity target, float dmg) {
		return !isPoisonDamage(source, target, dmg) && !source.isMagicDamage() && !source.isExplosion() && source != DamageSource.WITHER && source != DamageSource.OUT_OF_WORLD && !source.isFireDamage() && source != DamageSource.STARVE;
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		return source.getTrueSource() == null && (source.isFireDamage() || source.isExplosion() || source.getDamageType().equals("cactus") || source.getDamageType().equals("acid_block") || source.getDamageType().equals("giant_snail_acid"));
	}

	public static BlockPos getBlockAimingAt(EntityPlayer pl, double distance) {
		Vec3d startVec = new Vec3d(pl.posX, pl.posY + (double)pl.getEyeHeight(), pl.posZ);
		float cosYaw = MathHelper.cos(-pl.rotationYaw * 0.017453292F - (float)Math.PI);
		float sinYaw = MathHelper.sin(-pl.rotationYaw * 0.017453292F - (float)Math.PI);
		float cosPitch = -MathHelper.cos(-pl.rotationPitch * 0.017453292F);
		float sinPitch = MathHelper.sin(-pl.rotationPitch * 0.017453292F);
		float angleX = sinYaw * cosPitch;
		float angleZ = cosYaw * cosPitch;
		Vec3d endVec = startVec.addVector((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
		RayTraceResult ray = pl.world.rayTraceBlocks(startVec, endVec, true, true, false);

		if (ray == null)
			return null;

		return ray.getBlockPos();
	}

	public static boolean isHostileMob(Entity mob) {
		return mob instanceof IMob;
	}

	public static EnumFacing getDirectionFacing(Entity entity, boolean lateralOnly) {
		if (!lateralOnly) {
			if (entity.rotationPitch < -50)
				return EnumFacing.DOWN;

			if (entity.rotationPitch > 50)
				return EnumFacing.UP;
		}

		int vec = MathHelper.floor(entity.rotationYaw * 4 / 360 + 0.5) & 0x3;

		switch (++vec % 4) {
			case 0:
				return EnumFacing.EAST;
			case 1:
				return EnumFacing.SOUTH;
			case 2:
				return EnumFacing.WEST;
			case 3:
				return EnumFacing.NORTH;
			default:
				return EnumFacing.NORTH;
		}
	}

	public static boolean checkMobProperty(Entity entity, Enums.MobProperties property) {
		return entity instanceof SpecialPropertyEntity && ((SpecialPropertyEntity)entity).getMobProperties().contains(property);
	}

	public static boolean applyAttributeModifier(EntityLivingBase entity, IAttribute attribute, AttributeModifier modifier) {
		IAttributeInstance att = entity.getEntityAttribute(attribute);

		if (att != null && !att.hasModifier(modifier)) {
			att.applyModifier(modifier);

			return true;
		}

		return false;
	}

	public static boolean removeAttributeModifier(EntityLivingBase entity, IAttribute attribute, AttributeModifier modifier) {
		IAttributeInstance att = entity.getEntityAttribute(attribute);

		if (att != null && att.hasModifier(modifier)) {
			att.removeModifier(modifier);

			return true;
		}

		return false;
	}

	public static boolean isPlayerLookingAtEntity(EntityPlayer pl, Entity target) {
		return isPlayerLookingAt(pl, target.posX, target.getEntityBoundingBox().minY + target.height / 2D, target.posZ) && pl.canEntityBeSeen(target);
	}

	public static boolean isPlayerLookingAt(EntityPlayer pl, double posX, double posY, double posZ) {
		Vec3d playerLookVec = pl.getLookVec().normalize();
		Vec3d requiredLookVec = new Vec3d(posX - pl.posX, posY - pl.posY + pl.getEyeHeight() / 2D,posZ - pl.posZ);
		double requiredLookVecLength = requiredLookVec.lengthVector();
		requiredLookVec = requiredLookVec.normalize();
		double vecDotProduct = playerLookVec.dotProduct(requiredLookVec);

		return vecDotProduct > 0.8 - 0.025 / requiredLookVecLength;
	}
}
