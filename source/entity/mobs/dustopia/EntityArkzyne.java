package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityArkzyne extends AoAMeleeMob {
    public static final float entityWidth = 0.5625f;

    public EntityArkzyne(World world) {
        super(world, entityWidth, 2.6875f);
    }

    @Override
    public float getEyeHeight() {
        return 2.5f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 135;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobArkzyneLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobArkzyneDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobArkzyneHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityArkzyne;
    }
}
