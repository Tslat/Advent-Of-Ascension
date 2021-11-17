package net.tslat.aoa3.entity.mob.voxponds;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;

import javax.annotation.Nullable;

public class FischerEntity extends AoAWaterMeleeMob {
    public FischerEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.85f;
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
    public void aiStep() {
        super.aiStep();

        if (isInWater()) {
            Vector3d currentMotion = getDeltaMovement();
            double motionX = currentMotion.x;
            double motionY = currentMotion.y;
            double motionZ = currentMotion.z;

            if (getTarget() != null && getTarget().getY() > getY())
                motionY = 0.25;

            fallDistance = 0.5f;

            if (motionX > -1.100000023841858 && motionX < 1.100000023841858)
                motionX *= 1.100000023841858;

            if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858)
                motionZ *= 1.100000023841858;

            setDeltaMovement(motionX, motionY, motionZ);
        }
    }
}
