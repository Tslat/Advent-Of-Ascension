package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityConstructFlight extends AoAFlyingMeleeMob {
    public static final float entityWidth = 0.7f;

    public EntityConstructFlight(World world) {
        super(world, entityWidth, 0.84375f);
    }

    @Override
    public float getEyeHeight() {
        return 0.5f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 40;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.1;
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
            dropItem(ItemRegister.gemstonesBlue, 3);

        if (rand.nextInt(6) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerCrystal), 1);
    }
}
