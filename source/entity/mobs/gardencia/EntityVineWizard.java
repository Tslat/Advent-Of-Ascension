package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityVineWizardShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityVineWizard extends AoARangedMob {
    public static final float entityWidth = 0.6f;

    public EntityVineWizard(World world) {
        super(world, entityWidth, 2.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60;
    }

    @Override
    protected double getBaseProjectileDamage() {
        return 15;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.shotVineWizardFire;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(50 - lootingMod) == 0)
            dropItem(WeaponRegister.cannonFlowerCannon, 1);

        if (rand.nextInt(40 - lootingMod) == 0)
            dropItem(ItemRegister.smallPetalGreen, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float bowDamageFactor) {
        world.spawnEntity(new EntityVineWizardShot(this, target, Enums.MobProjectileType.ENERGY));
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (isInWater() && getHealth() > 0)
            heal(0.6f);
    }
}
