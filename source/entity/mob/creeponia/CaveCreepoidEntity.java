package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CaveCreepoidEntity extends AoACreeponiaCreeper {
    public CaveCreepoidEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4f;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 65;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.27;
    }

    @Override
	public float getExplosionStrength() {
        return 3.2f;
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
    protected int getMaxSpawnHeight() {
        return 30;
    }

    @Override
    protected void onHit(DamageSource source, float amount) {
        if (!world.isRemote && !source.isFireDamage() && RandomUtil.oneInNChance(3))
            WorldUtil.createExplosion(this, world, 2f);
    }
}
