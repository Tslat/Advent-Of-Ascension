package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class WingedCreeperEntity extends AoACreeponiaCreeper {
    public WingedCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        PlayerEntity pl = level.getNearestPlayer(getX(), getY(), getZ(), 15, false);

        if (pl == null || pl.isCreative())
            return;

        if (pl.getY() > getY() && tickCount % 3 == 0) {
            Vector3d motion = getDeltaMovement();

            setDeltaMovement(motion.x() + MathHelper.clamp(pl.getX() - getX(), -0.05, 0.05), motion.y() + 0.3, motion.z() + MathHelper.clamp(pl.getZ() - getZ(), -0.05, 0.05));
        }
    }
}
