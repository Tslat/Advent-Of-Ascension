package net.tslat.aoa3.entity.mob.shyrelands;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SoulscorneEntity extends AoAMeleeMob {
    public SoulscorneEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.59375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 140d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
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
    protected int getMaxSpawnHeight() {
        return 35;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity)target;
            ArrayList<EffectInstance> positiveEffects = new ArrayList<EffectInstance>(entity.getActivePotionEffects().size());

            for (EffectInstance effect : entity.getActivePotionEffects()) {
                if (effect.getPotion().isBeneficial())
                    positiveEffects.add(effect);
            }

            for (EffectInstance effect : positiveEffects) {
                entity.removePotionEffect(effect.getPotion());
            }
        }
    }
}
