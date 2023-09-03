package net.tslat.aoa3.util;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.entity.PartEntity;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public final class EntityUtil {
	public static boolean isVulnerableEntity(Entity entity, @Nullable DamageSource source) {
		if (!(entity instanceof LivingEntity) || !entity.isAlive())
			return false;

		if (source == null ? entity.isInvulnerable() : entity.isInvulnerableTo(source))
			return false;

		return !(entity instanceof Player player) || PlayerUtil.shouldPlayerBeAffected(player);
	}

	public static boolean isHostileMob(Entity entity) {
		return entity instanceof Enemy;
	}

	public static class Predicates {
		public static final Predicate<LivingEntity> HOSTILE_MOB = entity -> entity instanceof Enemy;
		public static final Predicate<Entity> SURVIVAL_PLAYER = entity -> entity instanceof Player && !((Player)entity).isCreative() && !entity.isSpectator();
		public static final Predicate<Entity> ATTACKABLE_ENTITY = entity -> entity.isAlive() && SURVIVAL_PLAYER.test(entity);
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
		return target instanceof Player || !target.canChangeDimensions() || target.isInvulnerable() || target.getType().is(Tags.EntityTypes.BOSSES) || (target instanceof LivingEntity && ((LivingEntity)target).getMaxHealth() > 500);
	}

	public static float getAttackCooldown(LivingEntity entity) {
		if (entity instanceof Player)
			return ((Player)entity).getAttackStrengthScale(0);

		return 1f;
	}

	public static boolean isFlyingCreature(Entity entity) {
		return entity instanceof LivingEntity && (entity instanceof FlyingMob || entity instanceof FlyingAnimal);
	}

	public static boolean hasAttributeModifier(final LivingEntity entity, final Attribute attribute, final AttributeModifier modifier) {
		final AttributeInstance instance = entity.getAttribute(attribute);

		if (instance != null)
			return instance.hasModifier(modifier);

		return false;
	}

	public static void reapplyAttributeModifier(LivingEntity entity, Attribute attribute, AttributeModifier modifier, boolean permanentModifier) {
		AttributeInstance instance = entity.getAttribute(attribute);

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
		AttributeInstance instance = entity.getAttribute(attribute);

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
		AttributeInstance instance = entity.getAttribute(attribute);

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
		AttributeMap attributes = entity.getAttributes();

		return attributes.hasAttribute(attribute) ? attributes.getValue(attribute) : 0;
	}

	public static void pushEntityAway(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength) {
		double knockbackResist = targetEntity instanceof LivingEntity target ? Math.min(safelyGetAttributeValue(target, Attributes.KNOCKBACK_RESISTANCE), 1) : 0;

		if (knockbackResist >= 1)
			return;

		targetEntity.setDeltaMovement(targetEntity.getDeltaMovement().add(centralEntity.position().vectorTo(targetEntity.position()).scale((1 - knockbackResist) * strength)));
		targetEntity.hurtMarked = true;
	}

	public static void pullEntityIn(@Nonnull Entity centralEntity, @Nonnull Entity targetEntity, float strength, boolean normalised) {
		Vec3 targetMotion = targetEntity.getDeltaMovement();
		Vec3 velocity = new Vec3((centralEntity.getX() - targetEntity.getX()) + targetMotion.x(),
				(centralEntity.getY() - targetEntity.getY()) + targetMotion.y(),
				(centralEntity.getZ() - targetEntity.getZ()) + targetMotion.z());

		if (normalised)
			velocity = velocity.normalize();

		velocity = velocity.scale(strength);
		targetEntity.setDeltaMovement(velocity);

		targetEntity.hurtMarked = true;
	}

	public static void applyPotions(Collection<? extends Entity> entities, EffectBuilder... effects) {
		for (Entity entity : entities) {
			applyPotions(entity, effects);
		}
	}

	public static void applyPotions(Entity entity, EffectBuilder... effects) {
		if (!(entity instanceof LivingEntity) || !entity.isAlive() || entity.isSpectator() || entity instanceof FakePlayer)
			return;

		boolean onlyBeneficial = entity instanceof Player && ((Player)entity).isCreative();

		for (EffectBuilder builder : effects) {
			if (!onlyBeneficial || builder.getEffect().isBeneficial())
				((LivingEntity)entity).addEffect(builder.build());
		}
	}

	public static void removePotions(LivingEntity entity, MobEffect... effects) {
		for (MobEffect effect : effects) {
			if (entity.hasEffect(effect))
				entity.removeEffect(effect);
		}
	}

	public static boolean isPlayerLookingAtEntity(Player pl, Entity target) {
		return isPlayerLookingAt(pl, target.getX(), target.getBoundingBox().minY + target.getBbHeight() / 2D, target.getZ()) && pl.hasLineOfSight(target);
	}

	public static boolean isPlayerLookingAt(Player pl, double posX, double posY, double posZ) {
		Vec3 playerLookVec = pl.getLookAngle().normalize();
		Vec3 requiredLookVec = new Vec3(posX - pl.getX(), posY - (pl.getY() + pl.getEyeHeight()),posZ - pl.getZ());
		double requiredLookVecLength = requiredLookVec.length();
		requiredLookVec = requiredLookVec.normalize();
		double vecDotProduct = playerLookVec.dot(requiredLookVec);

		return vecDotProduct > 1.0 - 0.025d / requiredLookVecLength;
	}

	public static boolean isNaturalSpawnReason(MobSpawnType reason) {
		return reason == MobSpawnType.CHUNK_GENERATION || reason == MobSpawnType.NATURAL;
	}

	@Nullable
	public static Vec3 preciseEntityInterceptCalculation(Entity impactedEntity, Entity impactingEntity, int granularity) {
		Vec3 vecVelocity = impactingEntity.getDeltaMovement();
		final double velocityX = vecVelocity.x();
		final double velocityY = vecVelocity.y();
		final double velocityZ = vecVelocity.z();

		for (int i = 0; i < granularity; i++) {
			double projectionX = velocityX * (1 / (float)granularity) * i;
			double projectionY = velocityY * (1 / (float)granularity) * i;
			double projectionZ = velocityZ * (1 / (float)granularity) * i;
			Vec3 initialVec = new Vec3(impactingEntity.getX(), impactingEntity.getY(), impactingEntity.getZ());
			Vec3 projectedVec = initialVec.add(projectionX, projectionY, projectionZ);

			List<Entity> entityList = impactingEntity.level().getEntities(impactingEntity, impactingEntity.getBoundingBox().inflate(projectionX, projectionY, projectionZ));

			for (Entity entity : entityList) {
				if (entity != impactedEntity)
					continue;

				Optional<Vec3> intercept = entity.getBoundingBox().clip(initialVec, projectedVec);

				if (intercept.isPresent())
					return intercept.get();
			}
		}

		return null;
	}

	public static boolean canPvp(Player attacker, Player target) {
		return attacker.level().getServer().isPvpAllowed() && attacker != target && !attacker.isAlliedTo(target);
	}

	public static Direction getDirectionFacing(Entity entity, boolean lateralOnly) {
		if (!lateralOnly) {
			if (entity.getXRot() < -50)
				return Direction.DOWN;

			if (entity.getXRot() > 50)
				return Direction.UP;
		}

		int vec = Mth.floor(entity.getYRot() * 4 / 360 + 0.5) & 0x3;

		return switch (++vec % 4) {
			case 0 -> Direction.EAST;
			case 1 -> Direction.SOUTH;
			case 2 -> Direction.WEST;
			default -> Direction.NORTH;
		};
	}

	public static double getEntityJumpVelocity(LivingEntity entity) {
		float jumpVelocity = entity.getJumpPower();

		if (entity.hasEffect(MobEffects.JUMP))
			jumpVelocity += 0.1f * entity.getEffect(MobEffects.JUMP).getAmplifier() + 1;

		return jumpVelocity;
	}

	@Nonnull
	public static Set<Entity> getAttackersForMob(LivingEntity entity, @Nullable Predicate<Entity> filter) {
		CombatTracker tracker = entity.getCombatTracker();

		if (tracker.entries.isEmpty()) {
			if (entity.getLastDamageSource() != null) {
				Entity attacker = entity.getLastDamageSource().getEntity();

				if (attacker != null && (filter == null || filter.test(attacker)))
					return Set.of(entity.getLastDamageSource().getEntity());
			}

			return Collections.emptySet();
		}

		HashSet<Entity> killers = new HashSet<>(tracker.entries.size());

		for (CombatEntry entry : tracker.entries) {
			if (entry.source().getEntity() instanceof LivingEntity attacker && (filter == null || filter.test(attacker)))
				killers.add(attacker);
		}

		return killers;
	}

	public static Vec3 getDirectionForFacing(Entity entity) {
		return new Vec3(
				-Mth.sin(entity.getYRot() * (float)Math.PI / 180f),
				-Mth.sin(entity.getXRot() * (float)Math.PI / 180f),
				Mth.cos(entity.getYRot() * (float)Math.PI / 180f)
		);
	}

	public static Vec3 getVelocityVectorForFacing(Entity entity) {
		return getVelocityVectorForFacing(entity, 1f);
	}

	public static Vec3 getVelocityVectorForFacing(Entity entity, float velocityMod) {
		return new Vec3(
				-Mth.sin(entity.getYRot() * (float)Math.PI / 180f) * Mth.cos(entity.getXRot() * (float)Math.PI / 180.0F) * velocityMod,
				-Mth.sin(entity.getXRot() * (float)Math.PI / 180f) * velocityMod,
				Mth.cos(entity.getYRot() * (float)Math.PI / 180f) * Mth.cos(entity.getXRot() * (float)Math.PI / 180f) * velocityMod);
	}

	public static boolean isEntityMoving(Entity entity) {
		Vec3 velocity = entity.getDeltaMovement();

		return velocity.x() != 0 || velocity.z() != 0 || velocity.y() > -0.07d || velocity.y() < -0.08d;
	}

	public static Vec3 getEntityCenter(Entity entity) {
		return new Vec3(entity.getX(0.5f), entity.getY(0.5f), entity.getZ(0.5f));
	}

	@Nullable
	public static <T extends Entity> EntityHitResult getEntityCollisionWithPrecision(Level level, Entity projectile, Vec3 startVec, Vec3 endVec, AABB bounds, Predicate<T> filter, float tolerance) {
		double closestDist = Double.MAX_VALUE;
		Entity impactEntity = null;
		Vec3 position = null;

		for(T target : EntityRetrievalUtil.<T>getEntities(level, bounds, entity -> entity != projectile && filter.test((T)entity))) {
			AABB targetBounds = target.getBoundingBox().inflate(tolerance);
			Optional<Vec3> boundsClip = targetBounds.clip(startVec, endVec);

			if (boundsClip.isPresent()) {
				Vec3 pos = boundsClip.get();

				double dist = startVec.distanceToSqr(pos);

				if (dist < closestDist) {
					impactEntity = target;
					closestDist = dist;
					position = pos;
				}
			}

			if (target.isMultipartEntity()) {
				for (Entity part : target.getParts()) {
					targetBounds = part.getBoundingBox().inflate(tolerance);
					boundsClip = targetBounds.clip(startVec, endVec);

					if (boundsClip.isPresent()) {
						Vec3 pos = boundsClip.get();

						double dist = startVec.distanceToSqr(pos);

						if (dist < closestDist) {
							impactEntity = target;
							closestDist = dist;
							position = pos;
						}
					}
				}
			}
		}

		return impactEntity == null ? null : new EntityHitResult(impactEntity, position);
	}

	@org.jetbrains.annotations.Nullable
	public static LivingEntity getLivingEntityFromSelfOrPart(Entity entity) {
		if (entity instanceof LivingEntity livingEntity)
			return livingEntity;

		return entity instanceof PartEntity<?> part && part.getParent() instanceof LivingEntity livingEntity ? livingEntity : null;
	}
}
