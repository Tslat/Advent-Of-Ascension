package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.TreeSet;

public class EntitySoulscorne extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 0.6f;

    public EntitySoulscorne(World world) {
        super(world, entityWidth, 1.8125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.59375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 120;
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
        return SoundsRegister.mobSoulscorneLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSoulscorneDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSoulscorneHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.gunSublimus, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 2 + rand.nextInt(6 + lootingMod));
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase)target).removePotionEffect(MobEffects.ABSORPTION);
            ((EntityLivingBase)target).removePotionEffect(MobEffects.HEALTH_BOOST);
            ((EntityLivingBase) target).removePotionEffect(MobEffects.RESISTANCE);

            Iterator modIterator = ((EntityLivingBase)target).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifiers().iterator();

            while (modIterator.hasNext()) {
                modIterator.remove();
            }
        }
    }

    @Override
    public int getHunterReq() {
        return 75;
    }

    @Override
    public float getHunterXp() {
        return 120;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
