package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

public class CaveCreepEntity extends AoAMeleeMob<CaveCreepEntity> {
    private static final RawAnimation LIFT_HOLD_ANIM = RawAnimation.begin().thenPlay("misc.lift.hold");
    private static final RawAnimation LIFT_ANIM = RawAnimation.begin().thenPlay("misc.lift").thenPlay("misc.lift.hold");
    private static final RawAnimation DROP_ANIM = RawAnimation.begin().thenPlay("misc.drop");
    private static final RawAnimation TRANSITION_WALK_ANIM = RawAnimation.begin().thenPlay("move.walk.transition");
    private static final RawAnimation LIFTED_WALK_ANIM = RawAnimation.begin().thenPlay("move.walk.lifted");
    private static final EntityDataAccessor<Integer> LAST_AGGRO_TIME = SynchedEntityData.defineId(CaveCreepEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_AGGRO = SynchedEntityData.defineId(CaveCreepEntity.class, EntityDataSerializers.BOOLEAN);

    private int lastAggroChange = -1;
    private boolean isAggro = false;

    public CaveCreepEntity(EntityType<? extends CaveCreepEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (state.isMoving()) {
                if (lastAggroChange >= 0)
                    return state.setAndContinue(TRANSITION_WALK_ANIM);

                if (isAggro)
                    return state.setAndContinue(LIFTED_WALK_ANIM);

                return state.setAndContinue(DefaultAnimations.WALK);
            }
            else if (isAggro) {
                if (lastAggroChange == -1)
                    state.setAnimation(LIFT_HOLD_ANIM);

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
        controllers.add(new AnimationController<>(this, "lifts", 0, state -> {
            if (this.lastAggroChange >= 0)
                return state.setAndContinue(this.isAggro ? LIFT_ANIM : DROP_ANIM);

            return PlayState.STOP;
        }));
    }
}
