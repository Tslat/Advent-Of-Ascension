package net.tslat.aoa3.entity.mob.deeplands;

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
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class DoublerEntity extends AoAMeleeMob {
    public DoublerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2f;
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

        PlayerEntity closestPlayer = level.getNearestPlayer(getX(), getY(), getZ(), 10, pl -> PlayerUtil.shouldPlayerBeAffected((PlayerEntity)pl));

        if (closestPlayer != null)
            EntityUtil.applyPotions(closestPlayer, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 30));
    }
}
