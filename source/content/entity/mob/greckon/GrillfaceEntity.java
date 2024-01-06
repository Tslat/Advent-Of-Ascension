package net.tslat.aoa3.content.entity.mob.greckon;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import org.jetbrains.annotations.Nullable;


public class GrillfaceEntity extends AoAMeleeMob<GrillfaceEntity> {
    private int scareCooldown = 0;

    public GrillfaceEntity(EntityType<? extends GrillfaceEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 2f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_GRILLFACE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_GRILLFACE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_GRILLFACE_HURT.get();
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayer && getLastHurtByMob() == null && scareCooldown <= 0) {
            playSound(AoASounds.ENTITY_GRILLFACE_SCARE.get(), 1.0f, 1.0f);

            scareCooldown = 100;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (scareCooldown > 0)
            scareCooldown--;
    }
}
