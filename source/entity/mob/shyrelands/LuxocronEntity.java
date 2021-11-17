package net.tslat.aoa3.entity.mob.shyrelands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class LuxocronEntity extends AoAMeleeMob {
    public LuxocronEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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

        for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(4), PlayerUtil::shouldPlayerBeAffected)) {
            EntityUtil.applyPotions(pl, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 30).level(3));
        }
    }
}
