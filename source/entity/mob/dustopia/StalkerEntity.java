package net.tslat.aoa3.entity.mob.dustopia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class StalkerEntity extends AoAMeleeMob {
    public StalkerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2.375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 138;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3;
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

        if (getAttackTarget() instanceof ServerPlayerEntity && canEntityBeSeen(getAttackTarget()) && EntityUtil.isPlayerLookingAtEntity((PlayerEntity)getAttackTarget(), this)) {
            setMotion(0, getMotion().getY(), 0);

            if (getAttackTarget().getDistanceSq(this) <= 2 * 2)
                AoAPackets.messagePlayer((ServerPlayerEntity)getAttackTarget(), new ScreenOverlayPacket(ScreenOverlayPacket.Type.STATIC, 30));

        }
    }

    @Override
    protected void onHit(DamageSource source, float amount) {
        if (!world.isRemote && getAttackTarget() != null && getAttackTarget().getDistanceSq(this) <= 2 * 2) {
            BlockPos teleportPos = RandomUtil.getRandomPositionWithinRange(getPosition(), 64, 0, 64, true, world);
            attemptTeleport(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ(), false);
        }
    }
}
