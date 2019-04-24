package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySoulvyre extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntitySoulvyre(World world) {
        super(world, entityWidth, 2.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.9375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 95;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobSoulvyreLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSoulvyreDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSoulvyreHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.tokensShyrelands, 1 + rand.nextInt(3 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerShyre), 1);
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (isPotionActive(MobEffects.RESISTANCE))
            addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 200, 0, true, true));

        if (isPotionActive(MobEffects.STRENGTH))
            addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 200, 1, true, true));

        addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 1, true, true));
    }
}
