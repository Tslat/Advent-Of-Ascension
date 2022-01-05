package net.tslat.aoa3.object.entity.mob.greckon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.client.ClientOperations;

import javax.annotation.Nullable;

public class SilencerEntity extends AoAMeleeMob {
    public static boolean isClientNearby = false;
    public static float previousGain = 1;

    public SilencerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.84375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return null;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (level.isClientSide() && !isNoAi())
            ClientOperations.doSilencerSilence(this);
    }
}
