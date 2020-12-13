package net.tslat.aoa3.entity.mob.crystevia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class ConstructOfSpeedEntity extends AoAMeleeMob {
    public ConstructOfSpeedEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);

        isSlipperyMovement = true;
        setAIMoveSpeed(1.6f);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 58;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.31;
    }

    @Override
    protected double getBaseArmour() {
        return 3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
    }

    @Override
    public boolean addPotionEffect(EffectInstance effect) {
        if (effect.getPotion() == Effects.SPEED)
            PotionUtil.amplifyEffect(effect, (effect.getAmplifier() + 1) * 2 - effect.getAmplifier());

        return super.addPotionEffect(effect);
    }

    @Override
    public void tick() {
        super.tick();

        if (isAlive() && getHealth() < getMaxHealth())
            heal(0.1f);
    }
}
