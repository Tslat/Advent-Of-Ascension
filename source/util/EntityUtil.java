package net.tslat.aoa3.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.util.FakePlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public final class EntityUtil {
	public static final AttributeModifier SPRINTING_SPEED_BOOST = new AttributeModifier(UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"), "Sprinting speed boost", 0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final AttributeModifier SLOW_FALLING = new AttributeModifier(UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA"), "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION);

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
		public static final Predicate<Entity> ATTACKABLE_ENTITY = entity -> entity.isAlive() && EntityPredicates.ATTACK_ALLOWED.test(entity);
		public static final Predicate<Entity> SURVIVAL_PLAYER = entity -> entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative() && !entity.isSpectator();
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
		return target instanceof PlayerEntity || target instanceof WitherEntity || target instanceof EnderDragonEntity || !target.canChangeDimensions() || target.isInvulnerable() || (target instanceof LivingEntity && ((LivingEntity)target).getMaxHealth() > 500);
	}

	public static float getAttackCooldown(LivingEntity entity) {
		if (entity instanceof PlayerEntity)
			return ((PlayerEntity)entity).getAttackStrengthScale(0);

		return 1f;
	}

	public static boolean isFlyingCreature(Entity entity) {
		return entity instanceof LivingEntity && (entity instanceof FlyingEntity || entity instanceof IFlyingAnimal);
	}

	public static void reapplyAttributeModifier(LivingEntity entity, Attribute attribute, AttributeModifier modifier, boolean permanentModifier) {
		ModifiableAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null) {
			if (instance.getModifier(modifier.getId()) != null)
				instance.removeModifier(modifier.getId());

			if (permanentModifier) {
				instance.addPermanentModifier(modifier);
			}
			else {
				instance.addTransientModifier(modifier);
			}

			if (attribute == Attributes.MAX_HEALTH && entity.getHealth() > entity.getMaxHealth())
				entity.setHealth(entity.getMaxHealth());
		}
	}

	public static void applyAttributeModifierSafely(LivingEntity entity, Attribute attribute, AttributeModifier modifier, boolean permanentModifier) {
		ModifiableAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null && !instance.hasModifier(modifier)) {
			if (permanentModifier) {
				instance.addPermanentModifier(modifier);
			}
			else {
				instance.addTransientModifier(modifier);
			}
		}
	}

	public static void removeAttributeModifier(LivingEntity entity, Attribute attribute, AttributeModifier modifier) {
		removeAttributeModifier(entity, attribute, modifier.getId());
	}

	public static void removeAttributeModifier(LivingEntity entity, Attribute attribute, UUID modifierId) {
		ModifiableAttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null) {
			AttributeModifier modifier = instance.getModifier(modifierId);

			if (modifier != null) {
				instance.removeModifier(modifier);

				if (attribute == Attributes.MAX_HEALTH && entity.getHealth() > entity.getMaxHealth())
					entity.setHealth(entity.getMaxHealth());
			}
		}
	}

	public static double safelyGetAttributeValue(LivingEntity entity, Attribute attribute) {
		AttributeModifierManager attributes = entity.getAttributes();

		return attributes.hasAttribute(attribute) ? attributes.getValue(attribute) : 0;
	}

	public static void pushEntityAway(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength) {
		Vector3d targetMotion = targetEntity.getDeltaMovement();

		targetEntity.setDeltaMovement((targetEntity.getX() - centralEntity.getX()) * strength + targetMotion.x(),
				(targetEntity.getY() - centralEntity.getY()) * strength + targetMotion.y(),
				(targetEntity.getZ() - centralEntity.getZ()) * strength + targetMotion.z());
		targetEntity.hurtMarked = true;
	}

	public static void pullEntityIn(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength, boolean normalised) {
		Vector3d targetMotion = targetEntity.getDeltaMovement();
		Vector3d velocity = new Vector3d((centralEntity.getX() - targetEntity.getX()) + targetMotion.x(),
				(centralEntity.getY() - targetEntity.getY()) + targetMotion.y(),
				(centralEntity.getZ() - targetEntity.getZ()) + targetMotion.z());

		if (normalised)
			velocity = velocity.normalize();

		velocity = velocity.scale(strength);
		targetEntity.setDeltaMovement(velocity);

		targetEntity.hurtMarked = true;
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
				((LivingEntity)entity).addEffect(builder.build());
		}
	}

	public static void removePotions(LivingEntity entity, Effect... effects) {
		for (Effect effect : effects) {
			if (entity.hasEffect(effect))
				entity.removeEffect(effect);
		}
	}

	public static boolean isPlayerLookingAtEntity(PlayerEntity pl, Entity target) {
		return isPlayerLookingAt(pl, target.getX(), target.getBoundingBox().minY + target.getBbHeight() / 2D, target.getZ()) && pl.canSee(target);
	}

	public static boolean isPlayerLookingAt(PlayerEntity pl, double posX, double posY, double posZ) {
		Vector3d playerLookVec = pl.getLookAngle().normalize();
		Vector3d requiredLookVec = new Vector3d(posX - pl.getX(), posY - (pl.getY() + pl.getEyeHeight()),posZ - pl.getZ());
		double requiredLookVecLength = requiredLookVec.length();
		requiredLookVec = requiredLookVec.normalize();
		double vecDotProduct = playerLookVec.dot(requiredLookVec);

		return vecDotProduct > 1.0 - 0.025d / requiredLookVecLength;
	}

	public static boolean isNaturalSpawnReason(SpawnReason reason) {
		return reason == SpawnReason.CHUNK_GENERATION || reason == SpawnReason.NATURAL;
	}

	@Nullable
	public static Vector3d preciseEntityInterceptCalculation(Entity impactedEntity, Entity impactingEntity, int granularity) {
		Vector3d vecVelocity = impactingEntity.getDeltaMovement();
		final double velocityX = vecVelocity.x();
		final double velocityY = vecVelocity.y();
		final double velocityZ = vecVelocity.z();

		for (int i = 0; i < granularity; i++) {
			double projectionX = velocityX * (1 / (float)granularity) * i;
			double projectionY = velocityY * (1 / (float)granularity) * i;
			double projectionZ = velocityZ * (1 / (float)granularity) * i;
			Vector3d initialVec = new Vector3d(impactingEntity.getX(), impactingEntity.getY(), impactingEntity.getZ());
			Vector3d projectedVec = initialVec.add(projectionX, projectionY, projectionZ);

			List<Entity> entityList = impactingEntity.level.getEntities(impactingEntity, impactingEntity.getBoundingBox().inflate(projectionX, projectionY, projectionZ));

			for (Entity entity : entityList) {
				if (entity != impactedEntity)
					continue;

				Optional<Vector3d> intercept = entity.getBoundingBox().clip(initialVec, projectedVec);

				if (intercept.isPresent())
					return intercept.get();
			}
		}

		return null;
	}

	public static boolean canPvp(PlayerEntity attacker, PlayerEntity target) {
		return attacker.level.getServer().isPvpAllowed() && attacker != target && !attacker.isAlliedTo(target);
	}

	public static Direction getDirectionFacing(Entity entity, boolean lateralOnly) {
		if (!lateralOnly) {
			if (entity.xRot < -50)
				return Direction.DOWN;

			if (entity.xRot > 50)
				return Direction.UP;
		}

		int vec = MathHelper.floor(entity.yRot * 4 / 360 + 0.5) & 0x3;

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

	public static double getEntityJumpVelocity(LivingEntity entity) {
		float jumpVelocity = entity.getJumpPower();

		if (entity.hasEffect(Effects.JUMP))
			jumpVelocity += 0.1f * entity.getEffect(Effects.JUMP).getAmplifier() + 1;

		return jumpVelocity;
	}

	@Nonnull
	public static Set<Entity> getAttackersForMob(LivingEntity entity, @Nullable Predicate<Entity> filter) {
		CombatTracker tracker = entity.getCombatTracker();

		if (tracker.entries.isEmpty() || !tracker.inCombat)
			return Collections.emptySet();

		HashSet<Entity> killers = new HashSet<Entity>(tracker.entries.size());

		for (CombatEntry entry : tracker.entries) {
			if (entry.isCombatRelated() && (filter == null || filter.test(entry.getSource().getEntity())))
				killers.add(entry.getSource().getEntity());
		}

		return killers;
	}

	public static Vector3d getDirectionForFacing(Entity entity) {
		return new Vector3d(
				-MathHelper.sin(entity.yRot * (float)Math.PI / 180f),
				-MathHelper.sin(entity.xRot * (float)Math.PI / 180f),
				MathHelper.cos(entity.yRot * (float)Math.PI / 180f)
		);
	}

	public static Vector3d getVelocityVectorForFacing(Entity entity) {
		return getVelocityVectorForFacing(entity, 1f);
	}

	public static Vector3d getVelocityVectorForFacing(Entity entity, float velocityMod) {
		return new Vector3d(
				-MathHelper.sin(entity.yRot * (float)Math.PI / 180f) * MathHelper.cos(entity.xRot * (float)Math.PI / 180.0F) * velocityMod,
				-MathHelper.sin(entity.xRot * (float)Math.PI / 180f) * velocityMod,
				MathHelper.cos(entity.yRot * (float)Math.PI / 180f) * MathHelper.cos(entity.xRot * (float)Math.PI / 180f) * velocityMod);
	}

	public static boolean isEntityMoving(Entity entity) {
		Vector3d velocity = entity.getDeltaMovement();

		return velocity.x() != 0 || velocity.z() != 0 || velocity.y() > -0.07d || velocity.y() < -0.08d;
	}
}
