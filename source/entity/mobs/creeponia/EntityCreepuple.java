package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityCreepuple extends EntityCreeper {
    public static final float entityWidth = 0.6f;

    public EntityCreepuple(World world) {
        super(world);

        setSize(entityWidth, 1.5625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.34375f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.329);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCreepoidLiving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsRegister.mobCreepoidHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 50 && super.getCanSpawnHere();
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }
}
