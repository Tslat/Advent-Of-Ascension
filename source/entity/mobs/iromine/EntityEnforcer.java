package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityEnforcer extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityEnforcer(World world) {
        super(world, entityWidth, 2.25f);
    }

    @Override
    public float getEyeHeight() {
        return 2.0625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 30;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobEnforcerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobEnforcerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobEnforcerHit;
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
