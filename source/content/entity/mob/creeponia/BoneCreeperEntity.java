package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class BoneCreeperEntity extends AoACreeponiaCreeper {
    public BoneCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.4375f;
    }

    @Override
	public float getExplosionStrength() {
        return 2.75f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (random.nextBoolean()) {
            return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
        }
        else {
            return SoundEvents.SKELETON_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SKELETON_HURT;
    }
}
