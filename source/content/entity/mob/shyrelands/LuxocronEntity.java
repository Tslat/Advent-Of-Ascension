package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class LuxocronEntity extends AoAMeleeMob {
    public LuxocronEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.90625f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_LUXOCRON_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_LUXOCRON_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_LUXOCRON_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        for (Player pl : level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(4), PlayerUtil::shouldPlayerBeAffected)) {
            EntityUtil.applyPotions(pl, new EffectBuilder(MobEffects.WEAKNESS, 30).level(3));
        }
    }
}
