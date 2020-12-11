package net.tslat.aoa3.entity.mob.greckon;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class HunterEntity extends AoAMeleeMob {
    public HunterEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);

        isSlipperyMovement = true;

        setAIMoveSpeed(2.7f);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.25;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 123;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_HUNTER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_HUNTER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_HUNTER_HURT.get();
    }
}
