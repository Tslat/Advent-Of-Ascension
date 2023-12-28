package net.tslat.aoa3.content.entity.mob.iromine;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import org.jetbrains.annotations.Nullable;


public class MechamatonEntity extends AoAMeleeMob<MechamatonEntity> {
    public MechamatonEntity(EntityType<? extends MechamatonEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.90625f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GOLEM_STEP.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity)
            DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 5, 1.3f, 5);
    }
}
