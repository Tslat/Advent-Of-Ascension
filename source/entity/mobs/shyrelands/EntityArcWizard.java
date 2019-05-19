package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityShyreBeam;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityArcWizard extends AoARangedMob implements HunterEntity {
    public static final float entityWidth = 0.6f;

    public EntityArcWizard(World world) {
        super(world, entityWidth, 2.3f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 90;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 4;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobArcWizardLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobArcWizardDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobArcWizardHit;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.shotArcWizardFire;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.staffShyre, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 3 + rand.nextInt(4 + lootingMod));
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityShyreBeam(this, Enums.MobProjectileType.ENERGY);
    }

    @Override
    public int getHunterReq() {
        return 66;
    }

    @Override
    public float getHunterXp() {
        return 650;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
