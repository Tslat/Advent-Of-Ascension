package net.tslat.aoa3.entity.mobs.deeplands;

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

import javax.annotation.Nullable;

public class EntityDoubler extends AoAMeleeMob {
    public static final float entityWidth = 1.3f;


    public EntityDoubler(World world) {
        super(world, entityWidth, 2.25f);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 100;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 21;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobDoublerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobDoublerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobDoublerHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 5;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.realmstoneDeeplands, 1);

        if (rand.nextBoolean())
            dropItem(WeaponRegister.archergunSkeletal, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer closestPlayer = world.getClosestPlayer(posX, posY, posZ, 10, player -> !((EntityPlayer)player).capabilities.isCreativeMode);

        if (closestPlayer != null)
            closestPlayer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30, 1, true, false));
    }
}
