package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityArchvine extends AoAMeleeMob {
    public static final float entityWidth = 1.5f;

    public EntityArchvine(World world) {
        super(world, entityWidth, 1.4375f);
    }

    @Override
    public float getEyeHeight() {
        return 1.1875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 140;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 4;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobArchvineLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobArchvineDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobArchvineHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY > 66 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitFloro, 1);

        if (rand.nextInt(40 - lootingMod) == 0)
            dropItem(ItemRegister.smallPetalBlue, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));

        if (rand.nextInt(3) == 0)
            dropItem(ItemRegister.seedsFloracle, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getHealth() > 0) {
            if (world.isRaining())
                heal(0.2f);

            heal((getMaxHealth() - getHealth()) / 100);
        }
    }
}
