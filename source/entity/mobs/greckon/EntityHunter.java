package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
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
        return 0.5;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 25;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.329;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobHunterLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobHunterDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobHunterHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensGreckon, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(20 - lootingMod) == 0)
            dropItem(ItemRegister.realmstoneDustopia, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
