package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface WeaponProjectile {
    WeaponFiringContext getShotContext();
    WeaponFiringContext getDefaultShotContext();
    @Nullable
    default Entity getShooter() {
        return getShotContext().getShooter();
    }
    @Nullable
    default LivingEntity getLivingShooter() {
        return getShooter() instanceof LivingEntity shooter ? shooter : null;
    }

    default <T extends Projectile & WeaponProjectile> T fromPos(Vec3 pos) {
        T self = asEntity();

        self.moveTo(pos.x, pos.y, pos.z, self.getYRot(), self.getXRot());

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T fromEyePos() {
        T self = asEntity();
        Entity owner = self.getOwner();

        if (owner != null)
            self.moveTo(owner.getEyePosition(), owner.getYRot(), owner.getXRot());

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T fromArmPos() {
        return fromArmPosWithOffset(0, 0);
    }

    default <T extends Projectile & WeaponProjectile> T fromArmPosWithOffset(float rightOffset, float verticalOffset) {
        T self = asEntity();

        if (!(self.getOwner() instanceof LivingEntity shooter) || getShotContext() == null)
            return fromEyePos();

        ProjectileFiringWeapon weapon = getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon weapon2 ? weapon2 : ProjectileFiringWeapon.DEFAULT;
        rightOffset = (rightOffset + weapon.getRightOffset(weapon, shooter)) * (shooter.getBbWidth() / EntityType.PLAYER.getWidth()) * (shooter.getMainArm() == HumanoidArm.RIGHT ? 1 : -1);
        float forwardOffset = weapon.getForwardOffset(weapon, shooter) * (shooter.getBbWidth() / EntityType.PLAYER.getWidth());
        verticalOffset = (verticalOffset + weapon.getEyeHeightOffset(weapon, shooter)) * (shooter.getBbHeight() / EntityType.PLAYER.getHeight());

        self.moveTo(shooter.getEyePosition()
                            .add(shooter.getLookAngle().scale(forwardOffset))
                            .add(0, verticalOffset, 0)
                            .add(shooter.calculateViewVector(shooter.getXRot(), shooter.getYRot() + 90).scale(rightOffset)),
                    shooter.getYRot(), shooter.getXRot());

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T shootingStraight(float velocity, float inaccuracy) {
        T self = asEntity();
        Vec3 facing = self.getLookAngle();

        self.shoot(facing.x, facing.y, facing.z, velocity, inaccuracy);

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T withVelocity(Vec3 velocity) {
        T self = asEntity();

        self.setDeltaMovement(velocity);
        self.hasImpulse = true;

        self.setYRot((float)(Mth.atan2(velocity.x, velocity.z) * Mth.RAD_TO_DEG));
        self.setXRot((float)(Mth.atan2(velocity.y, velocity.horizontalDistance()) * Mth.RAD_TO_DEG));
        self.yRotO = self.getYRot();
        self.xRotO = self.getXRot();

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T shootingTowards(Vec3 angle, float velocity, float inaccuracy) {
        T self = asEntity();

        self.shoot(angle.x, angle.y, angle.z, velocity, inaccuracy);

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T shootingAtTarget(float velocity, float inaccuracy) {
        T self = asEntity();

        if (!(self.getOwner() instanceof LivingEntity shooter))
            return shootingStraight(velocity, inaccuracy);

        Vec3 eyePos = shooter.getEyePosition();
        Vec3 targetPos = eyePos.add(shooter.getLookAngle().scale(velocity * (getShotContext() == null ? 120 : getShotContext().projectileLifespan())));

        if (!AoAConfigs.SERVER.fastProjectileCalculations.getAsBoolean())
            targetPos = RayTrace.createWithSource(shooter).between(eyePos, targetPos).filter(self::canHitEntity).run().hitPos();

        Vec3 angle = self.position().vectorTo(targetPos);

        if (angle.normalize().dot(shooter.getLookAngle()) < 0.5f)
            angle = eyePos.vectorTo(targetPos);

        self.shoot(angle.x, angle.y, angle.z, velocity, inaccuracy);

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T offsetVelocity(Vec3 offset) {
        T self = asEntity();

        self.addDeltaMovement(offset);

        return self;
    }

    default <T extends Projectile & WeaponProjectile> T asEntity() {
        return (T)this;
    }

    default HitResult checkCollisionOnMove(Projectile projectile, ClipContext.Block blockRule, ClipContext.Fluid fluidRule, Predicate<Entity> filter) {
        Vec3 velocity = projectile.getDeltaMovement();
        Level level = projectile.level();
        Vec3 position = projectile.position();

        Vec3 nextPosition = position.add(velocity);
        HitResult hitResult = level.clip(new ClipContext(position, nextPosition, blockRule, fluidRule, projectile));

        if (hitResult.getType() != HitResult.Type.MISS)
            nextPosition = hitResult.getLocation();

        float collisionFuzz = (float)Math.clamp(projectile.tickCount * 0.05f * (velocity.lengthSqr() / (3f * 3f)), 0f, 0.3f);
        HitResult entityHitResult = ProjectileUtil.getEntityHitResult(level, projectile, position, nextPosition, projectile.getBoundingBox().expandTowards(velocity).inflate(1d), filter, collisionFuzz);

        if (entityHitResult != null)
            hitResult = entityHitResult;

        return hitResult;
    }
}
