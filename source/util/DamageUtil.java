package net.tslat.aoa3.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
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
import net.minecraft.util.math.vector.Vector3d;
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

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, strength, xRatio, zRatio);

		if(event.isCanceled())
			return;

		strength = event.getStrength() * (float)(1 - target.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue());
		xRatio = event.getRatioX();
		zRatio = event.getRatioZ();
		target.hasImpulse = true;
		target.hurtMarked = true;
		float vec = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
		Vector3d targetMotion = target.getDeltaMovement();
		double motionX = targetMotion.x() / 2d - xRatio / (double)vec * (double)strength;
		double motionZ = targetMotion.z() / 2d - zRatio / (double)vec * (double)strength;
		double motionY = targetMotion.y();

		if (target.onGround) {
			motionY /= 2.0D;
			motionY += strength;

			if (motionY > 0.4000000059604645D)
				motionY = 0.4000000059604645D;
		}

		target.setDeltaMovement(new Vector3d(motionX, motionY, motionZ));
		target.hurtMarked = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof PlayerEntity && !PlayerUtil.shouldPlayerBeAffected((PlayerEntity)target))
			return;

		Vector3d attackerVelocity = attacker.getDeltaMovement();
		double xVelocity = attackerVelocity.x() * xModifier;
		double yVelocity = attackerVelocity.y() * yModifier;
		double zVelocity = attackerVelocity.z() * zModifier;
		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, (float)NumberUtil.average(xVelocity, yVelocity, zVelocity), xVelocity, zVelocity);

		if(event.isCanceled())
			return;

		double resist = 1;
		ModifiableAttributeInstance attrib = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

		if (attrib != null)
			resist -= attrib.getValue();

		target.push(event.getRatioX() * resist, yVelocity * resist, event.getRatioZ() * resist);
		target.hurtMarked = true;
	}

	public static void killEntityCleanly(Entity entity) {
		if (!(entity instanceof LivingEntity)) {
			entity.hurt(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
			entity.remove();

			return;
		}

		LivingEntity target = (LivingEntity)entity;

		target.hurt(new DamageSource("magic").bypassArmor().bypassMagic().setMagic(), ((LivingEntity)entity).getHealth());

		if (target.getHealth() > 0)
			target.setHealth(0);
	}

	public static boolean dealAoeDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new IndirectEntityDamageSource("aoe", indirectSource, attacker).setMagic();
		}
		else {
			damageSource = attacker instanceof PlayerEntity ? DamageSource.playerAttack((PlayerEntity)attacker) : DamageSource.mobAttack(attacker);
		}

		if (bypassProtections) {
			damageSource.bypassArmor();
			damageSource.bypassMagic();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof LivingEntity && !HunterUtil.canAttackTarget((LivingEntity)target, attacker, false))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(damageSource, dmg);
	}

	public static boolean dealBlasterDamage(LivingEntity attacker, Entity target, Entity shot, float dmg, boolean bypassProtections) {
		DamageSource damageSource = new IndirectEntityDamageSource("blaster", shot, attacker);

		damageSource.setMagic();

		if (!(target instanceof PlayerEntity))
			damageSource.bypassArmor();

		if (bypassProtections) {
			damageSource.bypassMagic();
			damageSource.bypassArmor();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.invulnerableTime = 0;
		Vector3d targetMotion = target.getDeltaMovement();
		boolean success = target.hurt(damageSource, dmg);
		target.setDeltaMovement(targetMotion);
		target.hurtMarked = false;
		return success;
	}

	public static boolean dealMeleeDamage(LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = attacker instanceof PlayerEntity ? DamageSource.playerAttack((PlayerEntity)attacker) : DamageSource.mobAttack(attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (bypassProtections) {
			damageSource.bypassArmor();
			damageSource.bypassMagic();
		}

		if (target instanceof LivingEntity && !HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.invulnerableTime = 0;

		boolean success = target.hurt(damageSource, dmg);

		target.invulnerableTime = 0;
		return success;
	}

	public static boolean dealMagicDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = new IndirectEntityDamageSource("magic", indirectSource, attacker);
		}
		else {
			damageSource = attacker instanceof PlayerEntity ? DamageSource.playerAttack((PlayerEntity)attacker) : DamageSource.mobAttack(attacker);
		}

		damageSource.setMagic();

		if (!(target instanceof PlayerEntity))
			damageSource.bypassArmor();

		if (bypassProtections)
			damageSource.bypassMagic();

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(damageSource, dmg);
	}

	public static void dealSelfHarmDamage(LivingEntity target, float dmg) {
		if (target instanceof PlayerEntity && !PlayerUtil.shouldPlayerBeAffected((PlayerEntity)target))
			return;

		if (target.getHealth() - dmg > 0.0f) {
			target.setHealth(target.getHealth() - dmg);
		}
		else {
			target.invulnerableTime = 0;
			target.hurt(new DamageSource("selfharm").bypassMagic().bypassArmor(), dmg);
		}
	}

	public static boolean dealGunDamage(Entity target, LivingEntity attacker, ThrowableEntity bullet, float dmg) {
		DamageSource source = new IndirectEntityDamageSource("gun", bullet, attacker).setProjectile();

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.invulnerableTime = 0;
		boolean success;

		if (RandomUtil.percentChance(0.6f)) {
			Vector3d targetMotion = target.getDeltaMovement();
			success = target.hurt(source, dmg);
			target.setDeltaMovement(targetMotion);
			target.hurtMarked = false;
		}
		else {
			success = target.hurt(source, dmg);
			target.hurtMarked = true;
		}

		return success;
	}

	public static boolean dealVulcaneDamage(LivingEntity target, PlayerEntity attacker, float dmg) {
		DamageSource source = DamageSource.playerAttack(attacker).bypassMagic().bypassArmor();

		if (target.isInvulnerableTo(source))
			return false;

		if (!HunterUtil.canAttackTarget(target, attacker, false))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(source, dmg);
	}

	public static boolean dealRangedDamage(Entity target, LivingEntity attacker, Entity projectile, float dmg) {
		DamageSource source = DamageSource.thrown(projectile, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPartEntity || target instanceof EnderCrystalEntity)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		if (!HunterUtil.canAttackTarget((LivingEntity)target, attacker, true))
			return false;

		target.invulnerableTime = 0;
		target.hurtMarked = true;
		return target.hurt(source, dmg);
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getEntity() != null && !source.isProjectile() && !source.getMsgId().equals("thrown") && !source.isMagic() && !source.isExplosion() && !source.isFire() && source.getDirectEntity() == source.getEntity();
	}

	public static boolean isBlasterDamage(DamageSource source) {
		return source.getMsgId().equals("blaster") && source.isMagic();
	}

	public static boolean isMagicDamage(DamageSource source, Entity target, float dmg) {
		return source.isMagic() && !source.getMsgId().equals("blaster") && !source.getMsgId().equals("thorns") && !(target instanceof LivingEntity && isPoisonDamage(source, target, dmg));
	}

	public static boolean isRangedDamage(DamageSource source, Entity target, float dmg) {
		return ((source.isProjectile() && !source.getMsgId().equals("gun")) || source.getMsgId().equals("thrown")) && !isMagicDamage(source, target, dmg);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.getMsgId().equals("gun") && source.isProjectile();
	}

	public static boolean isPoisonDamage(DamageSource source, Entity target, float dmg) {
		return source.isMagic() && source.getEntity() == null && !source.getMsgId().equals("thorns") && target instanceof LivingEntity && ((LivingEntity)target).hasEffect(Effects.POISON) && dmg == 1.0f;
	}

	public static boolean isPhysicalDamage(DamageSource source, Entity target, float dmg) {
		return !isPoisonDamage(source, target, dmg) && !source.isMagic() && !source.isExplosion() && source != DamageSource.WITHER && source != DamageSource.OUT_OF_WORLD && !source.isFire() && source != DamageSource.STARVE;
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		if (source.getEntity() != null || source.isExplosion())
			return false;

		switch (source.getMsgId()) {
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
		return PlayerUtil.isWearingFullSet(player, AdventArmour.Type.HAZMAT) || player.inventory.armor.get(EquipmentSlotType.HEAD.getIndex()).getItem() == AoAArmour.FACE_MASK.get();
	}
}
