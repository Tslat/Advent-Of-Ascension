package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class WingedCreeperEntity extends AoACreeponiaCreeper {
    public WingedCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.40625f;
    }

    @Override
    public float getExplosionStrength() {
        return 3.1f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AoASounds.ENTITY_CREEPOID_HURT.get();
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        Player pl = level.getNearestPlayer(getX(), getY(), getZ(), 15, false);

        if (pl == null || pl.isCreative())
            return;

        if (pl.getY() > getY() && tickCount % 3 == 0) {
            Vec3 motion = getDeltaMovement();

            setDeltaMovement(motion.x() + Mth.clamp(pl.getX() - getX(), -0.05, 0.05), motion.y() + 0.3, motion.z() + Mth.clamp(pl.getZ() - getZ(), -0.05, 0.05));
        }
    }
}
