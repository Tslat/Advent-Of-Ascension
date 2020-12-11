package net.tslat.aoa3.entity.mob.dustopia;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class DustStriderEntity extends AoAMeleeMob {
    public DustStriderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    public DustStriderEntity(DustonEntity duston) {
        this(AoAEntities.Mobs.DUST_STRIDER.get(), duston.world);

        setLocationAndAngles(duston.getPosX(), duston.getPosY() + 0.5, duston.getPosZ(), duston.rotationYaw, duston.rotationPitch);
        fallDistance = -10;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.8125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 110;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 11.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
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
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayerEntity)
            AoAPackets.messagePlayer((ServerPlayerEntity)target, new ScreenOverlayPacket(ScreenOverlayPacket.Type.DARKNESS, 60));
    }
}
