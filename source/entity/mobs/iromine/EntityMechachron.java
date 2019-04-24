package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
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
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 180;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 9;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobMechachronLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobMechachronDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobMechachronHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.veryHeavyStep;
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 5;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneIromine, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.incompleteMechaStaff, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinGold, 1);
    }
}
