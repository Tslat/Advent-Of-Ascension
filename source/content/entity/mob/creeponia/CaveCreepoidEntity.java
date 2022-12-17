package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;

public class CaveCreepoidEntity extends AoACreeponiaCreeper {
    public CaveCreepoidEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.40625f;
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
    protected void onHurt(DamageSource source, float amount) {
        if (!level.isClientSide && !source.isFire() && RandomUtil.oneInNChance(3))
            WorldUtil.createExplosion(this, level, 2f);
    }
}
