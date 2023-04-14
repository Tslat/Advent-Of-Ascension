package net.tslat.aoa3.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.item.armour.AdventArmour;

import javax.annotation.Nullable;

public final class DamageUtil {
	public static DamageSource miscDamage(ResourceKey<DamageType> damageType, Level level) {
		return new DamageSource(level.damageSources().damageTypes.getHolderOrThrow(damageType));
	}

	public static DamageSource miscPositionedDamage(ResourceKey<DamageType> damageType, Level level, Vec3 position) {
		return new DamageSource(level.damageSources().damageTypes.getHolderOrThrow(damageType), position);
	}

	public static DamageSource entityDamage(ResourceKey<DamageType> damageType, Entity attacker) {
		return new DamageSource(attacker.level.damageSources().damageTypes.getHolderOrThrow(damageType), attacker);
	}

	public static DamageSource positionedEntityDamage(ResourceKey<DamageType> damageType, Entity attacker, Vec3 position) {
		return new DamageSource(attacker.level.damageSources().damageTypes.getHolderOrThrow(damageType), attacker, null, position);
	}

	public static DamageSource indirectEntityDamage(ResourceKey<DamageType> damageType, Entity attacker, Entity projectile) {
		return new DamageSource(projectile.level.damageSources().damageTypes.getHolderOrThrow(damageType), projectile, attacker);
	}

	public static boolean doMobMeleeAttack(Entity attacker, Entity target, float dmg) {
		return safelyDealDamage(entityDamage(AoADamageTypes.MOB_MELEE_ATTACK, attacker), target, dmg);
	}

	public static boolean doProjectileAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, float dmg) {
		return safelyDealDamage(indirectEntityDamage(AoADamageTypes.RANGED_ATTACK, attacker, projectile), target, dmg);
	}

	public static boolean doVulcaneAttack(@Nullable Entity attacker, Entity target, float dmg) {
		return safelyDealDamage(positionedEntityDamage(AoADamageTypes.VULCANE, attacker, target.position()), target, dmg);
	}

	public static boolean doGunAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, float dmg) {
		return safelyDealDamage(indirectEntityDamage(AoADamageTypes.GUN, attacker, projectile), target, dmg);
	}

	public static boolean doHeavyGunAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, float dmg) {
		return safelyDealDamage(indirectEntityDamage(AoADamageTypes.HEAVY_GUN, attacker, projectile), target, dmg);
	}

	public static boolean doEnergyProjectileAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, float dmg) {
		return safelyDealDamage(indirectEntityDamage(AoADamageTypes.ENERGY_PROJECTILE, attacker, projectile), target, dmg);
	}

	public static boolean doMagicProjectileAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, float dmg) {
		return safelyDealDamage(indirectEntityDamage(AoADamageTypes.MAGIC_PROJECTILE, attacker, projectile), target, dmg);
	}

	public static boolean doRecoilAttack(Entity target, float dmg) {
		return safelyDealDamage(miscDamage(AoADamageTypes.RECOIL, target.level), target, dmg);
	}

	public static boolean doMiscMagicAttack(Entity attacker, Entity target, float dmg, @Nullable Vec3 position) {
		return safelyDealDamage(position == null ? entityDamage(AoADamageTypes.MAGIC_ATTACK, attacker) : positionedEntityDamage(AoADamageTypes.MAGIC_ATTACK, attacker, position), target, dmg);
	}

	public static boolean doMiscEnergyAttack(Entity attacker, Entity target, float dmg, @Nullable Vec3 position) {
		return safelyDealDamage(position == null ? entityDamage(AoADamageTypes.ENERGY_ATTACK, attacker) : positionedEntityDamage(AoADamageTypes.ENERGY_ATTACK, attacker, position), target, dmg);
	}

	public static void doScaledKnockback(LivingEntity target, LivingEntity attacker, float strength, double xRatio, double yRatio, double zRatio) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, strength, xRatio, zRatio);

		if(event.isCanceled())
			return;

		strength = event.getStrength();
		AttributeInstance knockbackResist = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		AttributeInstance knockbackStrength = attacker.getAttribute(Attributes.ATTACK_KNOCKBACK);

		if (knockbackStrength != null)
			strength *= Math.max(0, 1 + knockbackStrength.getValue());

		if (knockbackResist != null)
			strength *= Math.max(0, 1 - knockbackResist.getValue());

		Vec3 vec = target.position().subtract(attacker.position());

		if (vec.y == 0 && (yRatio != xRatio || yRatio != zRatio))
			vec = vec.add(0, 1, 0);

		vec = vec.normalize()
				.multiply(event.getRatioX(), yRatio, event.getRatioZ())
				.add(attacker.getDeltaMovement().scale(0.5f))
				.multiply(strength, strength, strength);

		if (target.isOnGround())
			vec.multiply(0.5f, 0.75f, 0.5f);

		target.setDeltaMovement(vec);
		target.hasImpulse = true;
		target.hurtMarked = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		Vec3 attackerVelocity = attacker.getDeltaMovement()
				.multiply(xModifier, yModifier, zModifier);
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
		if (!(entity instanceof LivingEntity livingEntity)) {
			entity.hurt(entity.level.damageSources().outOfWorld(), Float.MAX_VALUE);
			entity.discard();

			return;
		}

		safelyDealDamage(livingEntity.level.damageSources().outOfWorld(), livingEntity, livingEntity.getHealth());

		if (livingEntity.getHealth() > 0)
			livingEntity.setHealth(0);
	}

	public static boolean safelyDealDamage(DamageSource damageSource, Entity target, float dmg) {
		return safelyDealDamage(damageSource, target, dmg, true);
	}

	public static boolean safelyDealDamage(DamageSource damageSource, Entity target, float dmg, boolean ignoreMiscEntities) {
		if (!ignoreMiscEntities && !(target instanceof LivingEntity) && !(target instanceof PartEntity<?>) && !(target instanceof EndCrystal))
			return false;

		return target.hurt(damageSource, dmg);
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getEntity() != null && source.getDirectEntity() == source.getEntity() && isPhysicalDamage(source);
	}

	public static boolean isEnergyDamage(DamageSource source) {
		return source.is(AoATags.DamageTypes.ENERGY);
	}

	public static boolean isMagicDamage(DamageSource source, Entity target, float dmg) {
		return source.is(AoATags.DamageTypes.MAGIC) && !isPoisonDamage(source, target, dmg);
	}

	public static boolean isRangedDamage(DamageSource source, Entity target, float dmg) {
		return source.is(DamageTypeTags.IS_PROJECTILE) && !isMagicDamage(source, target, dmg) && !source.is(AoATags.DamageTypes.GUN);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.is(AoATags.DamageTypes.GUN);
	}

	public static boolean isPoisonDamage(DamageSource source, Entity target, float dmg) {
		return source.is(DamageTypes.MAGIC) && source.getEntity() == null && target instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffects.POISON) && dmg == 1;
	}

	public static boolean isPhysicalDamage(DamageSource source) {
		return !source.is(DamageTypeTags.BYPASSES_ARMOR) && !source.is(DamageTypeTags.IS_EXPLOSION) && !isEnvironmentalDamage(source);
	}

	public static boolean isVulcaneDamage(DamageSource source) {
		return source.is(AoADamageTypes.VULCANE);
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		if (source.getEntity() != null || source.is(DamageTypeTags.IS_EXPLOSION))
			return false;

		return source.is(AoATags.DamageTypes.ENVIRONMENTAL);
	}

	public static boolean isPlayerEnvironmentallyProtected(ServerPlayer player) {
		Item helmet = player.getInventory().armor.get(EquipmentSlot.HEAD.getIndex()).getItem();

		return helmet instanceof AdventArmour && ((AdventArmour)helmet).isHelmetAirTight(player);
	}
}
