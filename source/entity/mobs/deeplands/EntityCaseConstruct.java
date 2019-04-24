package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityCaseConstruct extends AoAMeleeMob {
    public static final float entityWidth = 1.2f;

    public EntityCaseConstruct(World world) {
        super(world, entityWidth, 2.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.96875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 200;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 20;
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
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 3;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneCrystevia, 1);
        dropItem(Item.getItemFromBlock(BlockRegister.deepCase), 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
