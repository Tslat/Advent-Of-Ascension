package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class CaveCreepEntity extends AoAMeleeMob {
    private static final AnimationBuilder LIFT = new AnimationBuilder().addAnimation("misc.lift", true);
    private static final AnimationBuilder LOWER = new AnimationBuilder().addAnimation("misc.lower", true);

    private boolean aggro = false;

    public CaveCreepEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CAVE_CREEP_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CAVE_CREEP_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CAVE_CREEP_HURT.get();
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected int getAttackSwingDuration() {
        return 11;
    }

    @Override
    protected int getPreAttackTime() {
        return 6;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "movement", 0, event -> {
            if (event.isMoving()) {
                event.getController().setAnimation(aggro ? AoAAnimations.RUN : AoAAnimations.WALK);

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
        animationData.addAnimationController(new AnimationController<>(this, "lifting", 0, event -> {
            //event.getController().setAnimation(aggro ? LIFT : LOWER);

            return PlayState.CONTINUE;
        }));
        animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE)); // TODO this will probably need some smoothing out
    }
}
