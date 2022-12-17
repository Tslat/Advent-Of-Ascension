package net.tslat.aoa3.content.entity.mob.greckon;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;

public class ShifterEntity extends AoAMeleeMob<ShifterEntity> {
    private int cloakCooldown = 160;

    public ShifterEntity(EntityType<? extends ShifterEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.46875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_SHIFTER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_SHIFTER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_SHIFTER_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (cloakCooldown > 0)
            cloakCooldown--;

        if (cloakCooldown == 0 && getTarget() != null) {
            cloakCooldown = 160;

            EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 40));
            playSound(AoASounds.ENTITY_SHIFTER_AMBIENT.get(), 1.0f, 1.0f);
        }
    }
}
