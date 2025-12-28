package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.entity.PartEntity;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.tme.api.object.builder.EffectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public final class EntityUtil {
	// TODO Check for usages to convert to areProbablyEnemies
	public static boolean isHostileMob(Entity entity) {
		return entity instanceof Enemy || (entity instanceof NeutralMob neutralMob && neutralMob.isAngry());
	}

	public static boolean areProbablyEnemies(Entity target, @Nullable Entity attacker) {
		if (attacker == target)
			return false;

		if (attacker != null) {
			if (attacker.isAlliedTo(target))
				return false;

			if (target instanceof OwnableEntity ownable && attacker.getUUID().equals(ownable.getOwnerUUID()))
				return false;

			if (attacker instanceof OwnableEntity ownable && target.getUUID().equals(ownable.getOwnerUUID()))
				return false;

			if (EntityUtil.isHostileMob(attacker)) {
				if (target instanceof Player)
					return true;

				if (target instanceof OwnableEntity ownable) {
					if (ownable.getOwner() instanceof Player)
						return true;

					if (target instanceof LivingEntity livingTarget) {
						AttributeMap attributes = livingTarget.getAttributes();

						if (attributes.hasAttribute(Attributes.ATTACK_DAMAGE) || attributes.hasAttribute(AoAAttributes.RANGED_ATTACK_DAMAGE))
							return true;

						if (target instanceof Mob mob && mob.getTarget() == attacker)
							return true;
					}
				}
			}
        }

		boolean isHostileTarget = EntityUtil.isHostileMob(target);

		if (attacker == null || attacker instanceof Player || !EntityUtil.isHostileMob(attacker))
			return isHostileTarget;

		return !isHostileTarget;
	}

	public static void healEntity(LivingEntity entity, float amount) {
		if (amount > 0 && entity.isAlive() && entity.getHealth() > 0 && entity.getHealth() < entity.getMaxHealth())
			entity.heal(amount);
	}

	public static float getHealthPercent(LivingEntity entity) {
		return entity.getHealth() / entity.getMaxHealth();
	}

	public static boolean isImmuneToSpecialAttacks(Entity target) {
		return target instanceof Player || target.isInvulnerable() || target.getType().is(Tags.EntityTypes.BOSSES) || (target instanceof LivingEntity livingTarget && livingTarget.getMaxHealth() > 500);
	}

	public static float getAttackCooldown(LivingEntity entity) {
		if (entity instanceof Player pl)
			return pl.getAttackStrengthScale(0);

		return entity.swinging ? entity.swingTime / (float)entity.getCurrentSwingDuration() : 1;
	}

	public static boolean isFlyingCreature(Entity entity) {
		if (!(entity instanceof LivingEntity))
			return false;

		if (entity instanceof FlyingMob || entity instanceof FlyingAnimal)
			return true;

		if (!(entity instanceof Mob mob))
			return false;

		return mob.getNavigation() instanceof FlyingPathNavigation || mob.getMoveControl() instanceof FlyingMoveControl;
	}

	public static void pushEntityAway(@NotNull Entity centralEntity, @NotNull Entity targetEntity, float strength) {
		double knockbackResist = targetEntity instanceof LivingEntity target ? Math.min(AttributeUtil.getAttributeValue(target, Attributes.KNOCKBACK_RESISTANCE), 1) : 0;

		if (knockbackResist >= 1)
			return;

		targetEntity.addDeltaMovement(targetEntity.getDeltaMovement().add(centralEntity.position().vectorTo(targetEntity.position()).scale((1 - knockbackResist) * strength)));
		targetEntity.hasImpulse = true;
		targetEntity.hurtMarked = true;
	}

	public static void pullEntityIn(@NotNull Entity centralEntity, @NotNull Entity targetEntity, float strength, boolean normalised) {
		Vec3 targetMotion = targetEntity.getDeltaMovement();
		Vec3 velocity = new Vec3((centralEntity.getX() - targetEntity.getX()) + targetMotion.x(),
				(centralEntity.getY() - targetEntity.getY()) + targetMotion.y(),
				(centralEntity.getZ() - targetEntity.getZ()) + targetMotion.z());

		if (normalised)
			velocity = velocity.normalize();

		velocity = velocity.scale(strength);
		targetEntity.addDeltaMovement(velocity);

		targetEntity.hurtMarked = true;
	}

	public static void applyPotions(Collection<? extends Entity> entities, Entity source, EffectBuilder... effects) {
		for (Entity entity : entities) {
			applyPotions(entity, source, effects);
		}
	}

	public static void applyPotions(Entity entity, Entity source, EffectBuilder... effects) {
		entity = getPartOrPartOwner(entity);

		if (!(entity instanceof LivingEntity target) || !entity.isAlive() || entity.isSpectator() || entity instanceof FakePlayer)
			return;

		boolean onlyBeneficial = entity instanceof Player pl && pl.getAbilities().invulnerable;

		for (EffectBuilder builder : effects) {
			if (!onlyBeneficial || builder.getEffect().value().isBeneficial())
				target.addEffect(builder.build(), source);
		}
	}

	public static void removePotions(LivingEntity entity, Holder<MobEffect>... effects) {
		for (Holder<MobEffect> effect : effects) {
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

	@NotNull
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

		Set<Entity> killers = new ObjectOpenHashSet<>(tracker.entries.size());

		for (CombatEntry entry : tracker.entries) {
			if (entry.source().getEntity() instanceof LivingEntity attacker && (filter == null || filter.test(attacker)))
				killers.add(attacker);
		}

		return killers;
	}

	public static Vec3 getDirectionForFacing(Entity entity) {
		return new Vec3(
				-Mth.sin(entity.getYRot() * Mth.DEG_TO_RAD),
				-Mth.sin(entity.getXRot() * Mth.DEG_TO_RAD),
				Mth.cos(entity.getYRot() * Mth.DEG_TO_RAD)
		);
	}

	public static Vec3 getVelocityVectorForFacing(Entity entity) {
		return getVelocityVectorForFacing(entity, 1f);
	}

	public static Vec3 getVelocityVectorForFacing(Entity entity, float velocityMod) {
		return new Vec3(
				-Mth.sin(entity.getYRot() * Mth.DEG_TO_RAD) * Mth.cos(entity.getXRot() * Mth.DEG_TO_RAD) * velocityMod,
				-Mth.sin(entity.getXRot() * Mth.DEG_TO_RAD) * velocityMod,
				Mth.cos(entity.getYRot() * Mth.DEG_TO_RAD) * Mth.cos(entity.getXRot() * Mth.DEG_TO_RAD) * velocityMod);
	}

	public static boolean isEntityMoving(Entity entity) {
		Vec3 velocity = entity.getDeltaMovement();

		return velocity.x() != 0 || velocity.z() != 0 || velocity.y() > -0.07d || velocity.y() < -0.08d;
	}

	public static Vec3 getEntityCenter(Entity entity) {
		return new Vec3(entity.getX(0.5f), entity.getY(0.5f), entity.getZ(0.5f));
	}

	public static Entity getPartOrPartOwner(Entity entity) {
		if (entity instanceof PartEntity<?> part)
			return part.getParent();

		return entity;
	}
}
