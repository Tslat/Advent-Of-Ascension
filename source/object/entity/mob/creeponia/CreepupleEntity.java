package net.tslat.aoa3.object.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepupleEntity extends AoACreeponiaCreeper {
    public CreepupleEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.34375f;
    }

    @Override
	public float getExplosionStrength() {
        return 2.4f;
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
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CREEPOID_HURT.get();
    }

    @Override
    protected void explode() {
        if (!level.isClientSide) {
            for (int i = 0; i < 3; i++) {
                WorldUtil.createExplosion(this, level, getX() + (random.nextDouble() * 3) - 1, getY() + (random.nextDouble() * 3) - 1, getZ() + (random.nextDouble() * 2) - 1, (getExplosionStrength() / 1.25f) * (isCharged() ? 2f : 1f), AoAGameRules.checkStrongerMobGriefing(level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            }

            level.explode(this, getX(), getY(), getZ(), getExplosionStrength() * (isCharged() ? 2f : 1f), AoAGameRules.checkStrongerMobGriefing(level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            remove();
            spawnLingeringCloud();
        }
    }
}
