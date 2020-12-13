package net.tslat.aoa3.entity.mob.voxponds;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class FischerEntity extends AoAMeleeMob {
    public FischerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.85f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 79;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_GADGETOID_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_GADGETOID_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_GADGETOID_HURT.get();
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (isInWater()) {
            Vec3d currentMotion = getMotion();
            double motionX = currentMotion.x;
            double motionY = currentMotion.y;
            double motionZ = currentMotion.z;

            if (getAttackTarget() != null && getAttackTarget().getPosY() > getPosY())
                motionY = 0.25;

            fallDistance = 0.5f;

            if (motionX > -1.100000023841858 && motionX < 1.100000023841858)
                motionX *= 1.100000023841858;

            if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858)
                motionZ *= 1.100000023841858;

            setMotion(motionX, motionY, motionZ);
        }
    }

    @Override
    protected int getMaxSpawnHeight() {
        return 30;
    }
}
