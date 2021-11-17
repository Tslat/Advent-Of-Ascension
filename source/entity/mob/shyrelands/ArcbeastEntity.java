package net.tslat.aoa3.entity.mob.shyrelands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class ArcbeastEntity extends AoAMeleeMob {
    public ArcbeastEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.9375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_ARCBEAST_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_ARCBEAST_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_ARCBEAST_HURT.get();
    }

    @Override
    protected int getAttackSwingDuration() {
        return 11;
    }

    @Override
    protected int getPreAttackTime() {
        return 7;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(AoAAnimations.genericWalkIdleController(this));
        animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
    }
}
