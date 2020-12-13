package net.tslat.aoa3.entity.mob.shyrelands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.ShyreBeamEntity;

import javax.annotation.Nullable;

public class ArcWizardEntity extends AoARangedMob {
    public ArcWizardEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.85f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 148d;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 16.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_ARC_WIZARD_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_ARC_WIZARD_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_ARC_WIZARD_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return AoASounds.ENTITY_ARC_WIZARD_SHOOT.get();
    }

    @Override
    protected int getMaxSpawnHeight() {
        return 35;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
       return new ShyreBeamEntity(this, BaseMobProjectile.Type.MAGIC);
    }
}
