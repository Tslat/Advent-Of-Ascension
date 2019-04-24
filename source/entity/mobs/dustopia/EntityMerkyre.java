package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityMerkyre extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 0.5625f;

    public EntityMerkyre(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 75;
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
        return SoundsRegister.mobMerkyreLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobMerkyreDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobMerkyreHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(5) == 0)
            dropItem(ItemRegister.primordialSkull, 1);
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase)
            ((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150, 30, true, true));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public int getHunterReq() {
        return 79;
    }

    @Override
    public float getHunterXp() {
        return 1500;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
