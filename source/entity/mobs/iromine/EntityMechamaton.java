package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityMechamaton extends AoAMeleeMob {
    public static final float entityWidth = 1.125f;

    public EntityMechamaton(World world) {
        super(world, entityWidth, 2.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.90625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 160;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 11;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.329;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobAutomatonLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobAutomatonDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobAutomatonHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.mobGolemStep;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(50 - lootingMod) == 0)
            dropItem(WeaponRegister.staffPower, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        super.doMeleeEffect(target);
    }
}
