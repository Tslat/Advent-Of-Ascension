package net.tslat.aoa3.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.FakePlayer;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.HunterUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class EntityUtil {
	public static final AttributeModifier SPRINTING_SPEED_BOOST = (new AttributeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"), "Sprinting speed boost", 0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
	public static final AttributeModifier SLOW_FALLING = new AttributeModifier(UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA"), "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION).setSaved(false);

	public static boolean isVulnerableEntity(Entity entity, @Nullable DamageSource source) {
		if (!(entity instanceof LivingEntity) || !entity.isAlive())
			return false;

		if (source == null ? entity.isInvulnerable() : entity.isInvulnerableTo(source))
			return false;

		if (!(entity instanceof PlayerEntity))
			return true;

		PlayerEntity player = (PlayerEntity)entity;

		return PlayerUtil.shouldPlayerBeAffected(player);
	}

	public static boolean isHostileMob(Entity entity) {
		return entity instanceof IMob;
	}

	public static class Predicates {
		public static final Predicate<LivingEntity> HOSTILE_MOB = entity -> entity instanceof IMob;
	}

	public static void healEntity(LivingEntity entity, float amount) {
		if (amount > 0 && entity.isAlive() && entity.getHealth() > 0 && entity.getHealth() < entity.getMaxHealth())
			entity.heal(amount);
	}

	public static float getCurrentHealthPercent(LivingEntity entity) {
		return entity.getHealth() / entity.getMaxHealth();
	}

	public static boolean checkAboveHealthPercentThreshold(LivingEntity entity, float thresholdPercent) {
		if (entity.getHealth() <= 0)
			return false;

		return getCurrentHealthPercent(entity) >= thresholdPercent;
	}

	public static boolean checkBelowHealthPercentThreshold(LivingEntity entity, float thresholdPercent) {
		if (entity.getHealth() <= 0)
			return false;

		return getCurrentHealthPercent(entity) < thresholdPercent;
	}

	public static boolean isImmuneToSpecialAttacks(Entity target, LivingEntity attacker) {
		if (target instanceof LivingEntity && !HunterUtil.canAttackTarget((LivingEntity)target, attacker, false))
			return true;

		return target instanceof PlayerEntity || target instanceof WitherEntity || target instanceof EnderDragonEntity || !target.isNonBoss() || target.isInvulnerable() || (target instanceof LivingEntity && ((LivingEntity)target).getMaxHealth() > 500);
	}

	public static float getAttackCooldown(LivingEntity entity) {
		if (entity instanceof PlayerEntity)
			return ((PlayerEntity)entity).getCooledAttackStrength(0);

		return 1f;
	}

	public static boolean isFlyingCreature(Entity entity) {
		return entity instanceof LivingEntity && (entity instanceof FlyingEntity || entity instanceof IFlyingAnimal);
	}

	public static void reapplyAttributeModifier(LivingEntity entity, IAttribute attribute, AttributeModifier modifier) {
		IAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null) {
			if (instance.getModifier(modifier.getID()) != null)
				instance.removeModifier(modifier.getID());

			instance.applyModifier(modifier);
		}
	}

	public static void applyAttributeModifierSafely(LivingEntity entity, IAttribute attribute, AttributeModifier modifier) {
		IAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null && !instance.hasModifier(modifier))
			instance.applyModifier(modifier);
	}

	public static void removeAttributeModifier(LivingEntity entity, IAttribute attribute, AttributeModifier modifier) {
		IAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null && instance.hasModifier(modifier))
			instance.removeModifier(modifier);
	}

	public static void removeAttributeModifier(LivingEntity entity, IAttribute attribute, UUID modifierId) {
		IAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null) {
			AttributeModifier modifier = instance.getModifier(modifierId);

			if (modifier != null)
				instance.removeModifier(modifier);
		}
	}

	public static void pushEntityAway(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength) {
		Vec3d targetMotion = targetEntity.getMotion();

		targetEntity.setMotion((targetEntity.getPosX() - centralEntity.getPosX()) * strength + targetMotion.getX(),
				(targetEntity.getPosY() - centralEntity.getPosY()) * strength + targetMotion.getY(),
				(targetEntity.getPosZ() - centralEntity.getPosZ()) * strength + targetMotion.getZ());
		targetEntity.velocityChanged = true;
	}

	public static void pullEntityIn(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength) {
		Vec3d targetMotion = targetEntity.getMotion();

		targetEntity.setMotion((centralEntity.getPosX() - targetEntity.getPosX()) * strength + targetMotion.getX(),
				(centralEntity.getPosY() - targetEntity.getPosY()) * strength + targetMotion.getY(),
				(centralEntity.getPosZ() - targetEntity.getPosZ()) * strength + targetMotion.getZ());

		targetEntity.velocityChanged = true;
	}

	public static void applyPotions(Collection<? extends Entity> entities, PotionUtil.EffectBuilder... effects) {
		for (Entity entity : entities) {
			applyPotions(entity, effects);
		}
	}

	public static void applyPotions(Entity entity, PotionUtil.EffectBuilder... effects) {
		if (!(entity instanceof LivingEntity) || !entity.isAlive() || entity.isSpectator() || entity instanceof FakePlayer)
			return;

		boolean onlyBeneficial = entity instanceof PlayerEntity && ((PlayerEntity)entity).isCreative();

		for (PotionUtil.EffectBuilder builder : effects) {
			if (!onlyBeneficial || builder.getEffect().isBeneficial())
				((LivingEntity)entity).addPotionEffect(builder.build());
		}
	}

	public static void removePotions(LivingEntity entity, Effect... effects) {
		for (Effect effect : effects) {
			if (entity.isPotionActive(effect))
				entity.removePotionEffect(effect);
		}
	}

	public static boolean isPlayerLookingAtEntity(PlayerEntity pl, Entity target) {
		return isPlayerLookingAt(pl, target.getPosX(), target.getBoundingBox().minY + target.getHeight() / 2D, target.getPosZ()) && pl.canEntityBeSeen(target);
	}

	public static boolean isPlayerLookingAt(PlayerEntity pl, double posX, double posY, double posZ) {
		Vec3d playerLookVec = pl.getLookVec().normalize();
		Vec3d requiredLookVec = new Vec3d(posX - pl.getPosX(), posY - (pl.getPosY() + pl.getEyeHeight()),posZ - pl.getPosZ());
		double requiredLookVecLength = requiredLookVec.length();
		requiredLookVec = requiredLookVec.normalize();
		double vecDotProduct = playerLookVec.dotProduct(requiredLookVec);

		return vecDotProduct > 1.0 - 0.025d / requiredLookVecLength;
	}

	public static boolean isNaturalSpawnReason(SpawnReason reason) {
		return reason == SpawnReason.CHUNK_GENERATION || reason == SpawnReason.NATURAL;
	}

	@Nullable
	public static Vec3d preciseEntityInterceptCalculation(Entity impactedEntity, Entity impactingEntity, int granularity) {
		Vec3d vecVelocity = impactingEntity.getMotion();
		final double velocityX = vecVelocity.getX();
		final double velocityY = vecVelocity.getY();
		final double velocityZ = vecVelocity.getZ();

		for (int i = 0; i < granularity; i++) {
			double projectionX = velocityX * (1 / (float)granularity) * i;
			double projectionY = velocityY * (1 / (float)granularity) * i;
			double projectionZ = velocityZ * (1 / (float)granularity) * i;
			Vec3d initialVec = new Vec3d(impactingEntity.getPosX(), impactingEntity.getPosY(), impactingEntity.getPosZ());
			Vec3d projectedVec = initialVec.add(projectionX, projectionY, projectionZ);

			List<Entity> entityList = impactingEntity.world.getEntitiesWithinAABBExcludingEntity(impactingEntity, impactingEntity.getBoundingBox().grow(projectionX, projectionY, projectionZ));

			for (Entity entity : entityList) {
				if (entity != impactedEntity)
					continue;

				Optional<Vec3d> intercept = entity.getBoundingBox().rayTrace(initialVec, projectedVec);

				if (intercept.isPresent())
					return intercept.get();
			}
		}

		return null;
	}

	public static boolean canPvp(PlayerEntity attacker, PlayerEntity target) {
		return attacker.world.getServer().isPVPEnabled() && attacker != target && !attacker.isOnSameTeam(target);
	}

	public static Direction getDirectionFacing(Entity entity, boolean lateralOnly) {
		if (!lateralOnly) {
			if (entity.rotationPitch < -50)
				return Direction.DOWN;

			if (entity.rotationPitch > 50)
				return Direction.UP;
		}

		int vec = MathHelper.floor(entity.rotationYaw * 4 / 360 + 0.5) & 0x3;

		switch (++vec % 4) {
			case 0:
				return Direction.EAST;
			case 1:
				return Direction.SOUTH;
			case 2:
				return Direction.WEST;
			case 3:
			default:
				return Direction.NORTH;
		}
	}
}
