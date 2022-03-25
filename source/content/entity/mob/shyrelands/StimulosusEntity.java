package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class StimulosusEntity extends AoAMeleeMob {
    public StimulosusEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.78125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_STIMULOSUS_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_STIMULO_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_STIMULO_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!level.isClientSide) {
            float healthPercent = EntityUtil.getCurrentHealthPercent(this);
            EffectBuilder effect = null;

            if (healthPercent < 0.25) {
                effect = new EffectBuilder(Effects.DAMAGE_BOOST, -1).level(2);
            }
            else if (healthPercent < 0.50) {
                effect = new EffectBuilder(Effects.DAMAGE_BOOST, -1).level(3);
            }
            else if (healthPercent < 0.75) {
                effect = new EffectBuilder(Effects.DAMAGE_BOOST, -1).level(4);
            }

            if (effect != null)
                EntityUtil.applyPotions(this, effect);
        }
    }
}
