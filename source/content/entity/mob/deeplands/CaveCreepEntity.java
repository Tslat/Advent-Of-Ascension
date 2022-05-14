package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
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
    private static final AnimationBuilder LIFT_HOLD_ANIM = new AnimationBuilder().addAnimation("misc.lift.hold", true);
    private static final AnimationBuilder LIFT_ANIM = new AnimationBuilder().addAnimation("misc.lift").addAnimation("misc.lift.hold", true);
    private static final AnimationBuilder DROP_ANIM = new AnimationBuilder().addAnimation("misc.drop");
    private static final AnimationBuilder TRANSITION_WALK_ANIM = new AnimationBuilder().addAnimation("move.walk.transition", true);
    private static final AnimationBuilder LIFTED_WALK_ANIM = new AnimationBuilder().addAnimation("move.walk.lifted", true);
    private static final EntityDataAccessor<Integer> LAST_AGGRO_TIME = SynchedEntityData.defineId(CaveCreepEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_AGGRO = SynchedEntityData.defineId(CaveCreepEntity.class, EntityDataSerializers.BOOLEAN);

    private int lastAggroChange = -1;
    private boolean isAggro = false;

    public CaveCreepEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1f;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        getEntityData().define(LAST_AGGRO_TIME, -1);
        getEntityData().define(IS_AGGRO, false);
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
    public void setTarget(@Nullable LivingEntity target) {
        LivingEntity oldTarget = getTarget();

        super.setTarget(target);

        if (target != oldTarget) {
            this.lastAggroChange = this.tickCount;

            getEntityData().set(LAST_AGGRO_TIME, this.lastAggroChange);
            getEntityData().set(IS_AGGRO, getTarget() != null);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (key.equals(LAST_AGGRO_TIME)) {
            this.lastAggroChange = getEntityData().get(LAST_AGGRO_TIME);
        }
        else if (key.equals(IS_AGGRO)) {
            this.isAggro = getEntityData().get(IS_AGGRO);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (lastAggroChange >= 0 && tickCount - lastAggroChange > 10)
            lastAggroChange = -1;
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
        animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
        animationData.addAnimationController(new AnimationController<>(this, "movement", 0, event -> {
            if (event.isMoving()) {
                if (lastAggroChange >= 0) {
                    event.getController().setAnimation(TRANSITION_WALK_ANIM);
                }
                else if (isAggro) {
                    event.getController().setAnimation(LIFTED_WALK_ANIM);
                }
                else {
                    event.getController().setAnimation(AoAAnimations.WALK);
                }

                return PlayState.CONTINUE;
            }
            else if (isAggro) {
                if (lastAggroChange == -1)
                    event.getController().setAnimation(LIFT_HOLD_ANIM);

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
        animationData.addAnimationController(new AnimationController<>(this, "lifts", 0, event -> {
            if (lastAggroChange >= 0) {
                if (isAggro) {
                    event.getController().setAnimation(LIFT_ANIM);
                }
                else {
                    event.getController().setAnimation(DROP_ANIM);
                }

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
    }
}
