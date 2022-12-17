package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class InfernalEntity extends AoAMeleeMob<InfernalEntity> {
    public InfernalEntity(EntityType<? extends InfernalEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.71875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_INFERNAL_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GENERIC_VERY_HEAVY_STEP.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof LivingEntity)
            DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 10.5f, 0.5f, 10.5f);
    }

    @Override
    protected void onHurt(DamageSource source, float amount) {
        if (DamageUtil.isMeleeDamage(source) && (!(source.getEntity() instanceof Player) || PlayerUtil.shouldPlayerBeAffected((Player)source.getEntity())))
            source.getEntity().setSecondsOnFire(5);
    }
}
