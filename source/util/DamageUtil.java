package net.tslat.aoa3.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
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
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class DamageUtil {
	public static <T extends Entity> boolean isAttackable(T entity) {
		return entity.isAlive() && (!(entity instanceof Player pl) || !pl.getAbilities().invulnerable);
	}

	public static DamageSource miscDamage(ResourceKey<DamageType> damageType, Level level) {
		return new DamageSource(level.damageSources().damageTypes.getHolderOrThrow(damageType));
	}

	public static DamageSource miscPositionedDamage(ResourceKey<DamageType> damageType, Level level, Vec3 position) {
		return new DamageSource(level.damageSources().damageTypes.getHolderOrThrow(damageType), position);
	}

	public static DamageSource entityDamage(ResourceKey<DamageType> damageType, @NotNull Entity attacker) {
		return new DamageSource(attacker.level().damageSources().damageTypes.getHolderOrThrow(damageType), attacker);
	}

	public static DamageSource positionedEntityDamage(ResourceKey<DamageType> damageType, @Nullable Entity attacker, Vec3 position) {
		return new DamageSource(RegistryUtil.getDataDrivenRegistry(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType), attacker, attacker, position);
	}

	public static DamageSource indirectEntityDamage(ResourceKey<DamageType> damageType, @Nullable Entity attacker, @Nullable Entity projectile) {
		return new DamageSource(RegistryUtil.getDataDrivenRegistry(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType), projectile, attacker);
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

	public static boolean doGunAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, ToFloatFunction<DamageSource> damage) {
		final DamageSource source = indirectEntityDamage(AoADamageTypes.GUN, attacker, projectile);

		return safelyDealDamage(source, target, damage.apply(source));
	}

	public static boolean doHeavyGunAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, ToFloatFunction<DamageSource> damage) {
		final DamageSource source = indirectEntityDamage(AoADamageTypes.HEAVY_GUN, attacker, projectile);

		return safelyDealDamage(source, target, damage.apply(source));
	}

	public static boolean doEnergyProjectileAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, ToFloatFunction<DamageSource> damage) {
		final DamageSource source = indirectEntityDamage(AoADamageTypes.ENERGY_PROJECTILE, attacker, projectile);

		return safelyDealDamage(source, target, damage.apply(source));
	}

	public static boolean doMagicProjectileAttack(@Nullable Entity attacker, @Nullable Entity projectile, Entity target, ToFloatFunction<DamageSource> damage) {
		final DamageSource source = indirectEntityDamage(AoADamageTypes.MAGIC_PROJECTILE, attacker, projectile);

		return safelyDealDamage(source, target, damage.apply(source));
	}

	public static boolean doRecoilAttack(Entity target, float dmg) {
		return safelyDealDamage(miscDamage(AoADamageTypes.RECOIL, target.level()), target, dmg);
	}

	public static boolean doMiscMagicAttack(@Nullable Entity attacker, Entity target, float dmg, @Nullable Vec3 position) {
		return safelyDealDamage(position == null ? attacker == null ?
												   miscDamage(AoADamageTypes.MAGIC_ATTACK, target.level()) :
												   entityDamage(AoADamageTypes.MAGIC_ATTACK, attacker) :
								positionedEntityDamage(AoADamageTypes.MAGIC_ATTACK, attacker, position), target, dmg);
	}

	public static boolean doMiscEnergyAttack(@Nullable Entity attacker, Entity target, float dmg, @Nullable Vec3 position) {
		return safelyDealDamage(position == null ? attacker == null ?
												   miscDamage(AoADamageTypes.ENERGY_ATTACK, target.level()) :
												   entityDamage(AoADamageTypes.ENERGY_ATTACK, attacker) :
								positionedEntityDamage(AoADamageTypes.ENERGY_ATTACK, attacker, position), target, dmg);
	}

	public static void doIndirectKnockback(LivingEntity target, float strength, Vec3 angle) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		LivingKnockBackEvent event = CommonHooks.onLivingKnockBack(target, strength, angle.x, angle.z);

		if (event.isCanceled())
			return;

		strength = event.getStrength();
		AttributeInstance knockbackResist = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

		if (knockbackResist != null)
			strength *= Math.max(0, 1 - knockbackResist.getValue());

		if (target.onGround() && angle.y < 0.1f)
			angle = angle.add(0, 0.25f, 0);

		target.setDeltaMovement(angle.scale(strength));
		target.hasImpulse = true;
		target.hurtMarked = true;
	}

	public static void doScaledKnockback(LivingEntity target, LivingEntity attacker, float strength, double xRatio, double yRatio, double zRatio) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		LivingKnockBackEvent event = CommonHooks.onLivingKnockBack(target, strength, xRatio, zRatio);

		if (event.isCanceled())
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

		if (target.onGround() && attacker.getY() == target.getY())
			vec = vec.add(0, 0.25f, 0);

		target.setDeltaMovement(vec);
		target.hasImpulse = true;
		target.hurtMarked = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		Vec3 attackerVelocity = attacker.getDeltaMovement().multiply(xModifier, yModifier, zModifier);
		double xVelocity = attackerVelocity.x() * xModifier;
		double yVelocity = attackerVelocity.y() * yModifier;
		double zVelocity = attackerVelocity.z() * zModifier;
		LivingKnockBackEvent event = CommonHooks.onLivingKnockBack(target, (float)NumberUtil.average(xVelocity, yVelocity, zVelocity), xVelocity, zVelocity);

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
			entity.hurt(entity.level().damageSources().genericKill(), Float.MAX_VALUE);
			entity.discard();

			return;
		}

		safelyDealDamage(livingEntity.level().damageSources().genericKill(), livingEntity, livingEntity.getHealth());

		if (livingEntity.getHealth() > 0)
			livingEntity.setHealth(0);
	}

	public static boolean safelyDealDamage(DamageSource damageSource, Entity target, float dmg) {
		return safelyDealDamage(damageSource, target, dmg, true);
	}

	public static boolean safelyDealDamage(DamageSource damageSource, Entity target, float dmg, boolean ignoreMiscEntities) {
		if (!ignoreMiscEntities && !(target instanceof LivingEntity) && !(target instanceof PartEntity<?>) && !(target instanceof EndCrystal))
			return false;

		if (target.hurt(damageSource, dmg)) {
			if (damageSource.getEntity() instanceof LivingEntity attacker)
				attacker.setLastHurtMob(target);

			return true;
		}

		return false;
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getEntity() != null && source.getDirectEntity() == source.getEntity() && isPhysicalDamage(source) && !isEnvironmentalDamage(source);
	}

	public static boolean isEnergyDamage(DamageSource source) {
		return source.is(AoATags.DamageTypes.ENERGY);
	}

	public static boolean isMagicDamage(DamageSource source) {
		return source.is(Tags.DamageTypes.IS_MAGIC) && !isPoisonDamage(source);
	}

	public static boolean isRangedDamage(DamageSource source) {
		return source.is(DamageTypeTags.IS_PROJECTILE) && !isMagicDamage(source) && !isEnergyDamage(source) && !source.is(AoATags.DamageTypes.GUN);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.is(AoATags.DamageTypes.GUN);
	}

	public static boolean isPoisonDamage(DamageSource source) {
		return source.is(Tags.DamageTypes.IS_POISON);
	}

	public static boolean isPhysicalDamage(DamageSource source) {
		return source.is(Tags.DamageTypes.IS_PHYSICAL);
	}

	public static boolean isVulcaneDamage(DamageSource source) {
		return source.is(AoADamageTypes.VULCANE);
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		return source.getEntity() == null && source.is(Tags.DamageTypes.IS_ENVIRONMENT);
	}

	public static boolean isPlayerEnvironmentallyProtected(Player player) {
		Item helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

		if (!(helmet instanceof AdventArmour adventArmour))
			return player.getItemBySlot(EquipmentSlot.HEAD).is(AoATags.Items.AIRTIGHT);

		return adventArmour.isHelmetAirTight(player);
	}

	public static float percentDamageReduction(DamageContainer container, float existingReduction, float percentReduction) {
		return existingReduction + (container.getNewDamage() - existingReduction) * percentReduction;
	}
}
