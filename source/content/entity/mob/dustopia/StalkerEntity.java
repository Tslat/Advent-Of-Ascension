package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
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

        if (getTarget() instanceof ServerPlayerEntity && canSee(getTarget()) && EntityUtil.isPlayerLookingAtEntity((PlayerEntity)getTarget(), this)) {
            setDeltaMovement(0, getDeltaMovement().y(), 0);

            if (getTarget().distanceToSqr(this) <= 2 * 2)
                AoAPackets.messagePlayer((ServerPlayerEntity)getTarget(), new ScreenOverlayPacket(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/static.png"), 30));

        }
    }

    @Override
    protected void onHit(DamageSource source, float amount) {
        if (!level.isClientSide && getTarget() != null && getTarget().distanceToSqr(this) <= 2 * 2) {
            BlockPos teleportPos = RandomUtil.getRandomPositionWithinRange(blockPosition(), 64, 0, 64, true, level);
            randomTeleport(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ(), false);
        }
    }
}
