package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class RockbiterEntity extends AoAMeleeMob<RockbiterEntity> {
    public RockbiterEntity(EntityType<? extends RockbiterEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 13 / 16f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_ROCKBITER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_ROCKBITER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_ROCKBITER_HURT.get();
    }

    @Override
    protected int getAttackSwingDuration() {
        return 8;
    }

    @Override
    protected int getPreAttackTime() {
        return 3;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.genericWalkController(this),
                AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
        // TODO has block animation
    }
}
