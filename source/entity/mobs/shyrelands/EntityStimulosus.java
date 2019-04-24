package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityStimulosus extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityStimulosus(World world) {
        super(world, entityWidth, 1.875f);
    }

    @Override
    public float getEyeHeight() {
        return 1.78125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 350;
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
        return SoundsRegister.mobStimulosusLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobStimuloDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobStimuloHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneShyrelands, 1);

        if (rand.nextBoolean())
            dropItem(WeaponRegister.cannonShyreBlaster, 1);
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (ticksExisted % 200 == 0)
            heal(getHealth());
    }
}
