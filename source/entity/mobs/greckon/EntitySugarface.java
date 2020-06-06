package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySugarface extends AoAMeleeMob {
    public static final float entityWidth = 0.5625f;

    public EntitySugarface(World world) {
        super(world, entityWidth, 2.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.2;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 124;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_SUGARFACE_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_SUGARFACE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_SUGARFACE_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entitySugarface;
    }
}
