package net.tslat.aoa3.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.content.item.armour.AdventArmour;

import javax.annotation.Nullable;

public final class DamageUtil {
	public static void doScaledKnockback(LivingEntity target, Entity attacker, float strength, double xRatio, double zRatio) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, strength, xRatio, zRatio);

		if(event.isCanceled())
			return;

		strength = event.getStrength() * (float)(1 - target.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue());
		xRatio = event.getRatioX();
		zRatio = event.getRatioZ();
		target.hasImpulse = true;
		target.hurtMarked = true;
		double vec = Math.sqrt((xRatio * xRatio + zRatio * zRatio));
		Vec3 targetMotion = target.getDeltaMovement();
		double motionX = targetMotion.x() / 2d - xRatio / (double)vec * (double)strength;
		double motionZ = targetMotion.z() / 2d - zRatio / (double)vec * (double)strength;
		double motionY = targetMotion.y();

		if (target.isOnGround()) {
			motionY /= 2.0D;
			motionY += strength;

			if (motionY > 0.4000000059604645D)
				motionY = 0.4000000059604645D;
		}

		target.setDeltaMovement(new Vec3(motionX, motionY, motionZ));
		target.hurtMarked = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		Vec3 attackerVelocity = attacker.getDeltaMovement();
		double xVelocity = attackerVelocity.x() * xModifier;
		double yVelocity = attackerVelocity.y() * yModifier;
		double zVelocity = attackerVelocity.z() * zModifier;
		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, (float)NumberUtil.average(xVelocity, yVelocity, zVelocity), xVelocity, zVelocity);

		if(event.isCanceled())
			return;

		double resist = 1;
		AttributeInstance attrib = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

		if (attrib != null)
			resist -= attrib.getValue();

		target.push(event.getRatioX() * resist, yVelocity * resist, event.getRatioZ() * resist);
		target.hurtMarked = true;
	}

	public static void killEntityCleanly(Entity entity) {
		if (!(entity instanceof LivingEntity)) {
			entity.hurt(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
			entity.discard();

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
			damageSource = attacker instanceof Player ? DamageSource.playerAttack((Player)attacker) : DamageSource.mobAttack(attacker);
		}

		if (bypassProtections) {
			damageSource.bypassArmor();
			damageSource.bypassMagic();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(damageSource, dmg);
	}

	public static boolean dealBlasterDamage(LivingEntity attacker, Entity target, Entity shot, float dmg, boolean bypassProtections) {
		DamageSource damageSource = new IndirectEntityDamageSource("blaster", shot, attacker);

		damageSource.setMagic();

		if (!(target instanceof Player))
			damageSource.bypassArmor();

		if (bypassProtections) {
			damageSource.bypassMagic();
			damageSource.bypassArmor();
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		Vec3 targetMotion = target.getDeltaMovement();
		boolean success = target.hurt(damageSource, dmg);
		target.setDeltaMovement(targetMotion);
		target.hurtMarked = false;
		return success;
	}

	public static boolean dealMeleeDamage(LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = attacker instanceof Player ? DamageSource.playerAttack((Player)attacker) : DamageSource.mobAttack(attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (bypassProtections) {
			damageSource.bypassArmor();
			damageSource.bypassMagic();
		}

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
			damageSource = attacker instanceof Player ? DamageSource.playerAttack((Player)attacker) : DamageSource.mobAttack(attacker);
		}

		damageSource.setMagic();

		if (!(target instanceof Player))
			damageSource.bypassArmor();

		if (bypassProtections)
			damageSource.bypassMagic();

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(damageSource, dmg);
	}

	public static void dealSelfHarmDamage(LivingEntity target, float dmg) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		if (target.getHealth() - dmg > 0.0f) {
			target.setHealth(target.getHealth() - dmg);
		}
		else {
			target.invulnerableTime = 0;
			target.hurt(new DamageSource("selfharm").bypassMagic().bypassArmor(), dmg);
		}
	}

	public static boolean dealGunDamage(Entity target, LivingEntity attacker, ThrowableProjectile bullet, float dmg) {
		DamageSource source = new IndirectEntityDamageSource("gun", bullet, attacker).setProjectile();

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		boolean success;

		if (RandomUtil.percentChance(0.6f)) {
			Vec3 targetMotion = target.getDeltaMovement();
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

	public static boolean dealVulcaneDamage(LivingEntity target, Player attacker, float dmg) {
		DamageSource source = new EntityDamageSource("vulcane", attacker) {
			@Override
			public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
				ItemStack itemstack = this.entity instanceof LivingEntity ? ((LivingEntity)this.entity).getMainHandItem() : ItemStack.EMPTY;
				String prefix = "death.attack.player";

				return !itemstack.isEmpty() && itemstack.hasCustomHoverName() ? new TranslatableComponent(prefix + ".item", entity.getDisplayName(), this.entity.getDisplayName(), itemstack.getDisplayName()) : new TranslatableComponent(prefix, entity.getDisplayName(), this.entity.getDisplayName());
			}
		}.bypassMagic().bypassArmor();

		if (target.isInvulnerableTo(source))
			return false;

		target.invulnerableTime = 0;
		return target.hurt(source, dmg);
	}

	public static boolean dealRangedDamage(Entity target, LivingEntity attacker, Entity projectile, float dmg) {
		DamageSource source = DamageSource.thrown(projectile, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		target.hurtMarked = true;
		return target.hurt(source, dmg);
	}

	public static boolean dealHaulingDamage(Player player, HaulingFishingBobberEntity bobber, Entity target, float dmg) {
		DamageSource damageSource = new IndirectEntityDamageSource("hauling", bobber, player).bypassArmor().bypassMagic().bypassInvul();

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		return target instanceof LivingEntity && target.hurt(damageSource, dmg);
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
		return source.isMagic() && source.getEntity() == null && !source.getMsgId().equals("thorns") && target instanceof LivingEntity && ((LivingEntity)target).hasEffect(MobEffects.POISON) && dmg == 1.0f;
	}

	public static boolean isPhysicalDamage(DamageSource source, Entity target, float dmg) {
		return !isPoisonDamage(source, target, dmg) && !source.isMagic() && !source.isExplosion() && source != DamageSource.WITHER && source != DamageSource.OUT_OF_WORLD && !source.isFire() && source != DamageSource.STARVE;
	}

	public static boolean isVulcaneDamage(DamageSource source) {
		return source.getMsgId().equals("vulcane");
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		if (source.getEntity() != null || source.isExplosion())
			return false;

		return switch (source.getMsgId()) {
			case "onFire", "inFire", "cactus", "acid", "lightningBolt", "lava", "cramming", "inWall", "fallingBlock", "starve", "anvil", "outOfWorld" -> true;
			default -> false;
		};
	}

	public static boolean isPlayerEnvironmentallyProtected(ServerPlayer player) {
		Item helmet = player.getInventory().armor.get(EquipmentSlot.HEAD.getIndex()).getItem();

		return helmet instanceof AdventArmour && ((AdventArmour)helmet).isHelmetAirTight(player);
	}
}
