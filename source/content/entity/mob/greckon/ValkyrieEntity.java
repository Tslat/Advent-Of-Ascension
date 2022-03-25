package net.tslat.aoa3.content.entity.mob.greckon;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.ValkyrieShotEntity;

import javax.annotation.Nullable;

public class ValkyrieEntity extends AoAFlyingRangedMob {
    public ValkyrieEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.03125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_VALKYRIE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_VALKYRIE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_VALKYRIE_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return null;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new ValkyrieShotEntity(this, BaseMobProjectile.Type.OTHER);
    }
}
