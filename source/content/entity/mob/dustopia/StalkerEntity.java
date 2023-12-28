package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;


public class StalkerEntity extends AoAMeleeMob<StalkerEntity> {
    public StalkerEntity(EntityType<? extends StalkerEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 2.375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_STALKER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_STALKER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_STALKER_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (getTarget() instanceof ServerPlayer && hasLineOfSight(getTarget()) && EntityUtil.isPlayerLookingAtEntity((Player)getTarget(), this))
            setDeltaMovement(0, getDeltaMovement().y(), 0);
    }

    @Override
    protected void onHurt(DamageSource source, float amount) {
        if (!level().isClientSide && getTarget() != null && getTarget().distanceToSqr(this) <= 2 * 2) {
            BlockPos teleportPos = RandomUtil.getRandomPositionWithinRange(blockPosition(), 64, 0, 64, true, level());
            randomTeleport(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ(), false);
        }
    }
}
