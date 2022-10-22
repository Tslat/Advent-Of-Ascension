package net.tslat.aoa3.content.entity.mob.shyrelands;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class SoulvyreEntity extends AoAMeleeMob<SoulvyreEntity> {
    public SoulvyreEntity(EntityType<? extends SoulvyreEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.9375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_SOULVYRE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_SOULVYRE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_SOULVYRE_HURT.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayer && PlayerUtil.consumeResource((ServerPlayer)target, AoAResources.SPIRIT.get(), 10, true))
            heal(10);
    }
}
