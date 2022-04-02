package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class DustStriderEntity extends AoAMeleeMob {
    public DustStriderEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public DustStriderEntity(DustonEntity duston) {
        this(AoAMobs.DUST_STRIDER.get(), duston.level);

        moveTo(duston.getX(), duston.getY() + 0.5, duston.getZ(), duston.getYRot(), duston.getXRot());
        fallDistance = -10;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.8125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_DUST_STRIDER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_DUST_STRIDER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_DUST_STRIDER_HURT.get();
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayer)
            AoAPackets.messagePlayer((ServerPlayer)target, new ScreenOverlayPacket(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/darkness.png"), 60));
    }
}
