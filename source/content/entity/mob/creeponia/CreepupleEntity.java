package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepupleEntity extends AoACreeponiaCreeper {
    public CreepupleEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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
                WorldUtil.createExplosion(this, level, getX() + (rand().nextDouble() * 3) - 1, getY() + (rand().nextDouble() * 3) - 1, getZ() + (rand().nextDouble() * 2) - 1, (getExplosionStrength() / 1.25f) * (isCharged() ? 2f : 1f), ForgeEventFactory.getMobGriefingEvent(level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);
            }

            level.explode(this, getX(), getY(), getZ(), getExplosionStrength() * (isCharged() ? 2f : 1f), ForgeEventFactory.getMobGriefingEvent(level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);
            discard();
            spawnLingeringCloud();
        }
    }
}
