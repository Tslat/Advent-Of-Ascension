package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySquasher extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntitySquasher(World world) {
        super(world, entityWidth, 1.6f);
    }

    @Override
    public float getEyeHeight() {
        return 1.75f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 8;
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
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(50 - lootingMod) == 0)
            dropItem(WeaponRegister.gunGardener, 1);

        if (rand.nextInt(20 - lootingMod) == 0)
            dropItem(ItemRegister.realmstoneVoxPonds, 1);

        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitFloro, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensGardencia, 1 + rand.nextInt(1 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRaining() && getHealth() > 0)
            heal(0.2f);

        if (rand.nextInt(250) == 0)
            motionY = 0.8;
    }
}
