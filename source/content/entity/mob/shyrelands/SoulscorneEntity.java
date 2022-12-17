package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SoulscorneEntity extends AoAMeleeMob<SoulscorneEntity> {
    public SoulscorneEntity(EntityType<? extends SoulscorneEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.59375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_SOULSCORNE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_SOULSCORNE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_SOULSCORNE_HURT.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity entity) {
            ArrayList<MobEffectInstance> positiveEffects = new ArrayList<>(entity.getActiveEffects().size());

            for (MobEffectInstance effect : entity.getActiveEffects()) {
                if (effect.getEffect().isBeneficial())
                    positiveEffects.add(effect);
            }

            for (MobEffectInstance effect : positiveEffects) {
                entity.removeEffect(effect.getEffect());
            }
        }
    }
}
