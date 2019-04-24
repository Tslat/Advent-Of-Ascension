package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityConstructStrength extends AoAMeleeMob {
    public static final float entityWidth = 1f;

    public EntityConstructStrength(World world) {
        super(world, entityWidth, 2.375f);
    }

    @Override
    public float getEyeHeight() {
        return 2.125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 30;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 12;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCrystalConstructLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCrystalConstructDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCrystalConstructHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(3) == 0)
            dropItem(ItemRegister.tokensCrystevia, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextBoolean())
            dropItem(ItemRegister.gemstonesRed, 3);

        if (rand.nextInt(6) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerCrystal), 1);
    }
}
