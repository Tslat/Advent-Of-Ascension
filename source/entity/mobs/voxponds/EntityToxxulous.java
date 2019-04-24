package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityToxxulous extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityToxxulous(World world) {
        super(world, entityWidth, 1.125f);
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
        return 60;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobToxxulousLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobToxxulousDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobToxxulousHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.blasterPoisonPlunger, 1);

        if (rand.nextInt(10) == 0)
            dropItem(ItemRegister.sludgeParasite, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(7), PredicateUtil.IS_VULNERABLE_PLAYER)) {
            pl.addPotionEffect(new PotionEffect(MobEffects.POISON, 30, 2, true, false));
        }
    }
}
