package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class BoneCreeperEntity extends AoACreeponiaCreeper {
    public BoneCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.4375f;
    }

    @Override
	public float getExplosionStrength() {
        return 2.75f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (random.nextBoolean()) {
            return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
        }
        else {
            return SoundEvents.SKELETON_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SKELETON_HURT;
    }
}
