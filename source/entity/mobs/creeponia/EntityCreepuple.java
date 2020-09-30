package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityCreepuple extends EntityCreeponiaCreeper {
    public static final float entityWidth = 0.6f;

    public EntityCreepuple(World world) {
        super(world, entityWidth, 1.5625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.34375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.28d;
    }

    @Override
	public float getExplosionStrength() {
        return 2.4f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_CREEPOID_LIVING;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_CREEPOID_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_CREEPOID_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityCreepuple;
    }

    @Override
    protected void explode() {
        if (!world.isRemote) {
            for (int i = 0; i < 3; i++) {
                WorldUtil.createExplosion(this, world, posX + (rand.nextDouble() * 3) - 1, posY + (rand.nextDouble() * 3) - 1, posZ + (rand.nextDouble() * 2) - 1, (getExplosionStrength() / 1.25f) * (getPowered() ? 2f : 1f), WorldUtil.checkGameRule(world, "doStrongerMobGriefing"));
            }

            world.createExplosion(this, posX, posY, posZ, getExplosionStrength() * (getPowered() ? 2f : 1f), WorldUtil.checkGameRule(world, "doStrongerMobGriefing"));
            setDead();
            spawnLingeringCloud();
        }
    }
}
