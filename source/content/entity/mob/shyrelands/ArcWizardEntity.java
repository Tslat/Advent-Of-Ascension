package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.ShyreBeamEntity;

import javax.annotation.Nullable;

public class ArcWizardEntity extends AoARangedMob<ArcWizardEntity> {
    public ArcWizardEntity(EntityType<? extends ArcWizardEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.85f;
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
    protected BaseMobProjectile getNewProjectileInstance() {
       return new ShyreBeamEntity(this, BaseMobProjectile.Type.MAGIC);
    }
}
