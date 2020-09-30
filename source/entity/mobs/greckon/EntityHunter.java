package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityHunter extends AoAMeleeMob {
    public static final float entityWidth = 1.3f;

    public EntityHunter(World world) {
        super(world, entityWidth, 1.3f);
        setSlipperyMovement();
        setAIMoveSpeed(2.7f);
    }

    @Override
    public float getEyeHeight() {
        return 0.875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.25;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 123;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_HUNTER_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_HUNTER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_HUNTER_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityHunter;
    }
}
