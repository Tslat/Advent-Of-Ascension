package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityWitherBall;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityWitherWizard extends AoARangedMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.6f;

    public EntityWitherWizard(World world) {
        super(world, entityWidth, 2.5f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
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
        return 40;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 6;
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

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(15 - lootingMod) == 0)
            dropItem(WeaponRegister.staffEmber, 1);

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerNether), 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
        dropItem(ItemRegister.runeWither, 2 + rand.nextInt(3 + lootingMod));
    }

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        if (target instanceof EntityLivingBase)
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 20, 60, true, true));
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isFireDamage();
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

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
