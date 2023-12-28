package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import org.jetbrains.annotations.Nullable;


public class MerkyreEntity extends AoAMeleeMob<MerkyreEntity> {
    public MerkyreEntity(EntityType<? extends MerkyreEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.5625f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_MERKYRE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_MERKYRE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_MERKYRE_HURT.get();
    }

    @Override
    protected void onHurt(DamageSource source, float amount) {
        if (!DamageUtil.isEnvironmentalDamage(source)) {
            AreaEffectCloud effectCloud = new AreaEffectCloud(level(), getX(), getY(), getZ());

            effectCloud.setDuration(30);
            effectCloud.setRadius(1.5f);
            effectCloud.setOwner(this);
            effectCloud.setWaitTime(0);
            effectCloud.setFixedColor(ColourUtil.BLACK);
            effectCloud.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0, false, true));
            effectCloud.setRadiusPerTick(-(effectCloud.getRadius() - 0.5f) / (float)effectCloud.getDuration());

            level().addFreshEntity(effectCloud);
        }
    }
}