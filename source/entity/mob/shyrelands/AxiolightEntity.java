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
import java.util.List;

public class AxiolightEntity extends AoAMeleeMob {
    public AxiolightEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.85f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_AXIOLIGHT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_AXIOLIGHT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_AXIOLIGHT_HURT.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        List<PlayerEntity> playerList = level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(12), PlayerUtil::shouldPlayerBeAffected);

        if (playerList.isEmpty())
            EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY).hideParticles());
    }
}
