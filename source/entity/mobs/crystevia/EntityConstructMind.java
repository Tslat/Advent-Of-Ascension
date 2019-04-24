package net.tslat.aoa3.entity.mobs.crystevia;

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

public class EntityConstructMind extends AoAMeleeMob {
    public static final float entityWidth = 2f;

    public EntityConstructMind(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.75f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 200;
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
    protected int getSpawnChanceFactor() {
        return 5;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneCrystevia, 1);

        if (rand.nextInt(3) == 0)
            dropItem(ItemRegister.tokensCrystevia, 1+ rand.nextInt(2 + lootingMod));

        if (rand.nextBoolean())
            dropItem(WeaponRegister.blasterOrbocron, 1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(16), PredicateUtil.IS_VULNERABLE_PLAYER)) {
            pl.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 65, 1, true, false));
            pl.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 65, 5, true, false));
        }
    }
}
