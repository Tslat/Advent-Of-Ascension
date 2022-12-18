package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;

public class DoublerEntity extends AoAMeleeMob<DoublerEntity> {
    public DoublerEntity(EntityType<? extends DoublerEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 4f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_DOUBLER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_DOUBLER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_DOUBLER_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        Player closestPlayer = level.getNearestPlayer(getX(), getY(), getZ(), 10, pl -> PlayerUtil.shouldPlayerBeAffected((Player)pl));

        if (closestPlayer != null)
            EntityUtil.applyPotions(closestPlayer, new EffectBuilder(MobEffects.BLINDNESS, 30));
    }

    @Override
    protected int getAttackSwingDuration() {
        return 41;
    }

    @Override
    protected int getPreAttackTime() {
        return 23;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.genericWalkController(this),
                AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM));
    }
}
