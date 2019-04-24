package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityLuxocron extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;

    public EntityLuxocron(World world) {
        super(world, entityWidth, 0.9375f);
    }

    @Override
    public float getEyeHeight() {
        return 0.90625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 80;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobLuxocronLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobLuxocronDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobLuxocronHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerShiny), 1);

        if (rand.nextInt(50 - lootingMod) == 0)
            dropItem(WeaponRegister.shotgunAmplifier, 1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(7), PredicateUtil.IS_VULNERABLE_PLAYER)) {
            pl.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30, 2, true, false));
        }
    }
}
