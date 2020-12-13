package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
    protected double getBaseKnockbackResistance() {
        return 0.15d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 55d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.29d;
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
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        PlayerEntity pl = world.getClosestPlayer(getPosX(), getPosY(), getPosZ(), 15, false);

        if (pl == null || pl.isCreative())
            return;

        if (pl.getPosY() > getPosY() && ticksExisted % 3 == 0) {
            Vec3d motion = getMotion();

            setMotion(motion.getX() + MathHelper.clamp(pl.getPosX() - getPosX(), -0.05, 0.05), motion.getY() + 0.3, motion.getZ() + MathHelper.clamp(pl.getPosZ() - getPosZ(), -0.05, 0.05));
        }
    }
}
