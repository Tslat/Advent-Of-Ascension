package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityNightwing extends AoAFlyingMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityNightwing(World world) {
        super(world, entityWidth, 0.9375f);
    }

    @Override
    public float getEyeHeight() {
        return 0.85f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 30;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.1;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobNightwingLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobNightwingDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobNightwingHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.blasterGasBlaster, 1);

        if (rand.nextInt(5) == 0)
            dropItem(ItemRegister.apocoStone, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
