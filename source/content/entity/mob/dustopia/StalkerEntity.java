package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class StalkerEntity extends AoAMeleeMob {
    public StalkerEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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

        if (getTarget() instanceof ServerPlayer && hasLineOfSight(getTarget()) && EntityUtil.isPlayerLookingAtEntity((Player)getTarget(), this)) {
            setDeltaMovement(0, getDeltaMovement().y(), 0);

            if (getTarget().distanceToSqr(this) <= 2 * 2)
                AoAPackets.messagePlayer((ServerPlayer)getTarget(), new ScreenOverlayPacket(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/static.png"), 30));

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
