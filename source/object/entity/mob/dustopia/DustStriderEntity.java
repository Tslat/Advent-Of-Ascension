package net.tslat.aoa3.object.entity.mob.dustopia;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class DustStriderEntity extends AoAMeleeMob {
    public DustStriderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    public DustStriderEntity(DustonEntity duston) {
        this(AoAEntities.Mobs.DUST_STRIDER.get(), duston.level);

        moveTo(duston.getX(), duston.getY() + 0.5, duston.getZ(), duston.yRot, duston.xRot);
        fallDistance = -10;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayerEntity)
            AoAPackets.messagePlayer((ServerPlayerEntity)target, new ScreenOverlayPacket(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/darkness.png"), 60));
    }
}
