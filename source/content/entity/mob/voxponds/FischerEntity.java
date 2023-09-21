package net.tslat.aoa3.content.entity.mob.voxponds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAWaterMeleeMobOld;

import javax.annotation.Nullable;

public class FischerEntity extends AoAWaterMeleeMobOld {
    public FischerEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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
            Vec3 currentMotion = getDeltaMovement();
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
