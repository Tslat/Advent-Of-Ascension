package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityMechachron extends AoAMeleeMob {
    public static final float entityWidth = 2f;

    public EntityMechachron(World world) {
        super(world, entityWidth, 1.875f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.7;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 114;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 10.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.295;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_MECHACHRON_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_MECHACHRON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_MECHACHRON_HIT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.VERY_HEAVY_STEP;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityMechachron;
    }
}
