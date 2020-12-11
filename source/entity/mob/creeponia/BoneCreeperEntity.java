package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class BoneCreeperEntity extends AoACreeponiaCreeper {
    public BoneCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.4375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 45d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3d;
    }

    @Override
	public float getExplosionStrength() {
        return 2.75f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (rand.nextBoolean()) {
            return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
        }
        else {
            return SoundEvents.ENTITY_SKELETON_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }
}
