package net.tslat.aoa3.entity.mob.nether;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class InfernalEntity extends AoAMeleeMob {
    public InfernalEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
    protected void onHit(DamageSource source, float amount) {
        if (DamageUtil.isMeleeDamage(source) && (!(source.getEntity() instanceof PlayerEntity) || PlayerUtil.shouldPlayerBeAffected((PlayerEntity)source.getEntity())))
            source.getEntity().setSecondsOnFire(5);
    }
}
