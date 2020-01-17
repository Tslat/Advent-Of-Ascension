package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityWitherBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityWitherWizard extends AoARangedMob {
    public static final float entityWidth = 0.6f;

    public EntityWitherWizard(World world) {
        super(world, entityWidth, 2.5f);
    }

    @Override
    public float getEyeHeight() {
        return 1.71875f;
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
    public double getBaseProjectileDamage() {
        return 6.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.23;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobWitherWizardLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobWitherWizardHit;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobWitherWizardHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityWitherWizard;
    }

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        if (target instanceof EntityLivingBase)
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20, 60, true, true));
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.shotWizardBlast;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityWitherBall(this, Enums.MobProjectileType.ENERGY);
    }

    @Override
    protected float getSpawnChanceFactor() {
        return 0.5f;
    }
}
