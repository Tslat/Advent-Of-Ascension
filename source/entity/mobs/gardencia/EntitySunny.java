package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySunny extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntitySunny(World world) {
        super(world, entityWidth, 2.375f);
    }

    @Override
    public float getEyeHeight() {
        return 2.125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.9;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 90;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.plantThump;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.plantThump;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY > 66 && super.getCanSpawnHere();
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 9;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneGardencia, 1);

        if (rand.nextBoolean())
            dropItem(WeaponRegister.gunFlowersFury, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getHealth() > 0) {
            if (world.isRaining()) {
                heal(0.2f);
            }
            else {
                heal(0.8f);
            }
        }
    }
}
