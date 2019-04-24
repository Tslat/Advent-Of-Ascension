package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;

import javax.annotation.Nullable;

public class EntityBasilisk extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityBasilisk(World world) {
        super(world, entityWidth, 1.375f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobBasiliskLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobBasiliskDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobBasiliskHit;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(25 - lootingMod) == 0)
            dropItem(WeaponRegister.sniperDuster, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensDustopia, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(200 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitDarkly, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
