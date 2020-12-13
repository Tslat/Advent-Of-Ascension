package net.tslat.aoa3.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.HunterUtil;

import javax.annotation.Nullable;

public abstract class DamageUtil {
	public static void doScaledKnockback(LivingEntity target, Entity attacker, float strength, double xRatio, double zRatio) {
		if (target instanceof PlayerEntity && !PlayerUtil.shouldPlayerBeAffected((PlayerEntity)target))
			return;

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, attacker, strength, xRatio, zRatio);

		if(event.isCanceled())
			return;

		strength = event.getStrength() * (float)(1 - target.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getValue());
		xRatio = event.getRatioX();
		zRatio = event.getRatioZ();
		target.isAirBorne = true;
		target.velocityChanged = true;
		float vec = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
		Vec3d targetMotion = target.getMotion();
		double motionX = targetMotion.getX() / 2d - xRatio / (double)vec * (double)strength;
		double motionZ = targetMotion.getZ() / 2d - zRatio / (double)vec * (double)strength;
		double motionY = targetMotion.getY();

		if (target.onGround) {
			motionY /= 2.0D;
			motionY += strength;

			if (motionY > 0.4000000059604645D)
				motionY = 0.4000000059604645D;
		}

		target.setMotion(new Vec3d(motionX, motionY, motionZ));
		target.velocityChanged = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof PlayerEntity && !PlayerUtil.shouldPlayerBeAffected((PlayerEntity)target))
			return;

		Vec3d attackerVelocity = attacker.getMotion();
		double xVelocity = attackerVelocity.getX() * xModifier;
		double yVelocity = attackerVelocity.getY() * yModifier;
		double zVelocity = attackerVelocity.getZ() * zModifier;
		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, attacker, (float)NumberUtil.average(xVelocity, yVelocity, zVelocity), xVelocity, zVelocity);

		if(event.isCanceled())
			return;

		double resist = 1;
		IAttributeInstance attrib = target.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

		if (attrib != null)
			resist -= attrib.getValue();

		target.addVelocity(event.getRatioX() * resist, yVelocity * resist, event.getRatioZ() * resist);
		target.velocityChanged = true;
	}

	public static void killEntityCleanly(Entity entity) {
		if (!(entity instanceof LivingEntity)) {
			entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
			entity.remove();

			return;
		}

		LivingEntity target = (LivingEntity)entity;

		target.attackEntityFrom(new DamageSource("magic").setDamageBypassesArmor().setDamageIsAbsolute().setMagicDamage(), ((LivingEntity)entity).getHealth());

		if (target.getHealth() > 0)
			target.setHealth(0);
	}

	public static boolean dealAoeDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new IndirectEntityDamageSource("aoe", indirectSource, attacker).setMagicDamage();
		}
		else {
			damageSource = attacker instanceof PlayerEntity ? DamageSource.causePlayerDamage((PlayerEntity)attacker) : DamageSource.causeMobDamage(attacker);
		}

		if (bypassProtections) {
			damageSource.setDamageBypassesArmor();
			damageSource.setDamageIsAbsolute();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof LivingEntity && !HunterUtil.canAttackTarget((LivingEntity)target, attacker, false))
			return false;

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(damageSource, dmg);
	}

	public static boolean dealBlasterDamage(LivingEntity attacker, Entity target, Entity shot, float dmg, boolean bypassProtections) {
		DamageSource damageSource = new IndirectEntityDamageSource("blaster", shot, attacker);

		damageSource.setMagicDamage();

		if (!(target instanceof PlayerEntity))
			damageSource.setDamageBypassesArmor();

		if (bypassProtections) {
			damageSource.setDamageIsAbsolute();
			damageSource.setDamageBypassesArmor();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.attackEntityFrom(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.hurtResistantTime = 0;
		Vec3d targetMotion = target.getMotion();
		boolean success = target.attackEntityFrom(damageSource, dmg);
		target.setMotion(targetMotion);
		target.velocityChanged = false;
		return success;
	}

	public static boolean dealMeleeDamage(LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = attacker instanceof PlayerEntity ? DamageSource.causePlayerDamage((PlayerEntity)attacker) : DamageSource.causeMobDamage(attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (bypassProtections) {
			damageSource.setDamageBypassesArmor();
			damageSource.setDamageIsAbsolute();
		}

		if (target instanceof LivingEntity && !HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.hurtResistantTime = 0;

		boolean success = target.attackEntityFrom(damageSource, dmg);

		target.hurtResistantTime = 0;
		return success;
	}

	public static boolean dealMagicDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new IndirectEntityDamageSource("magic", indirectSource, attacker);
		}
		else {
			damageSource = attacker instanceof PlayerEntity ? DamageSource.causePlayerDamage((PlayerEntity)attacker) : DamageSource.causeMobDamage(attacker);
		}

		damageSource.setMagicDamage();

		if (!(target instanceof PlayerEntity))
			damageSource.setDamageBypassesArmor();

		if (bypassProtections)
			damageSource.setDamageIsAbsolute();

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.attackEntityFrom(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(damageSource, dmg);
	}

	public static void dealSelfHarmDamage(LivingEntity target, float dmg) {
		if (target instanceof PlayerEntity && !PlayerUtil.shouldPlayerBeAffected((PlayerEntity)target))
			return;

		if (target.getHealth() - dmg > 0.0f) {
			target.setHealth(target.getHealth() - dmg);
		}
		else {
			target.hurtResistantTime = 0;
			target.attackEntityFrom(new DamageSource("selfharm").setDamageIsAbsolute().setDamageBypassesArmor(), dmg);
		}
	}

	public static boolean dealGunDamage(Entity target, LivingEntity attacker, ThrowableEntity bullet, float dmg) {
		DamageSource source = new IndirectEntityDamageSource("gun", bullet, attacker).setProjectile();

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.attackEntityFrom(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.hurtResistantTime = 0;
		boolean success;

		if (RandomUtil.percentChance(0.6f)) {
			Vec3d targetMotion = target.getMotion();
			success = target.attackEntityFrom(source, dmg);
			target.setMotion(targetMotion);
			target.velocityChanged = false;
		}
		else {
			success = target.attackEntityFrom(source, dmg);
			target.velocityChanged = true;
		}

		return success;
	}

	public static boolean dealVulcaneDamage(LivingEntity target, PlayerEntity attacker, float dmg) {
		DamageSource source = DamageSource.causePlayerDamage(attacker).setDamageIsAbsolute().setDamageBypassesArmor();

		if (target.isInvulnerableTo(source))
			return false;

		if (!HunterUtil.canAttackTarget(target, attacker, false))
			return false;

		target.hurtResistantTime = 0;
		return target.attackEntityFrom(source, dmg);
	}

	public static boolean dealRangedDamage(Entity target, LivingEntity attacker, Entity projectile, float dmg) {
		DamageSource source = DamageSource.causeThrownDamage(projectile, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.attackEntityFrom(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.hurtResistantTime = 0;
		target.velocityChanged = true;
		return target.attackEntityFrom(source, dmg);
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getTrueSource() != null && !source.isProjectile() && !source.getDamageType().equals("thrown") && !source.isMagicDamage() && !source.isExplosion() && !source.isFireDamage() && source.getImmediateSource() == source.getTrueSource();
	}

	public static boolean isBlasterDamage(DamageSource source) {
		return source.getDamageType().equals("blaster") && source.isMagicDamage();
	}

	public static boolean isMagicDamage(DamageSource source, Entity target, float dmg) {
		return source.isMagicDamage() && !source.getDamageType().equals("blaster") && !source.getDamageType().equals("thorns") && !(target instanceof LivingEntity && isPoisonDamage(source, target, dmg));
	}

	public static boolean isRangedDamage(DamageSource source, Entity target, float dmg) {
		return ((source.isProjectile() && !source.getDamageType().equals("gun")) || source.getDamageType().equals("thrown")) && !isMagicDamage(source, target, dmg);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.getDamageType().equals("gun") && source.isProjectile();
	}

	public static boolean isPoisonDamage(DamageSource source, Entity target, float dmg) {
		return source.isMagicDamage() && source.getTrueSource() == null && !source.getDamageType().equals("thorns") && target instanceof LivingEntity && ((LivingEntity)target).isPotionActive(Effects.POISON) && dmg == 1.0f;
	}

	public static boolean isPhysicalDamage(DamageSource source, Entity target, float dmg) {
		return !isPoisonDamage(source, target, dmg) && !source.isMagicDamage() && !source.isExplosion() && source != DamageSource.WITHER && source != DamageSource.OUT_OF_WORLD && !source.isFireDamage() && source != DamageSource.STARVE;
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		if (source.getTrueSource() != null || source.isExplosion())
			return false;

		switch (source.getDamageType()) {
			case "onFire":
			case "inFire":
			case "cactus":
			case "acid":
			case "lightningBolt":
			case "lava":
			case "cramming":
			case "inWall":
			case "fallingBlock":
			case "starve":
			case "anvil":
			case "outOfWorld":
				return true;
			default:
				return false;
		}
	}

	public static boolean isPlayerEnvironmentallyProtected(ServerPlayerEntity player) {
		return PlayerUtil.isWearingFullSet(player, AdventArmour.Type.HAZMAT) || player.inventory.armorInventory.get(EquipmentSlotType.HEAD.getIndex()).getItem() == AoAArmour.FACE_MASK.get();
	}
}
