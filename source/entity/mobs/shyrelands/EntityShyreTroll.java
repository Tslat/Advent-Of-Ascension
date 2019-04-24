package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityShyreBeam;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityShyreTroll extends AoARangedMob {
    public static final float entityWidth = 0.6f;

    public EntityShyreTroll(World world) {
        super(world, entityWidth, 1.8f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 20;
    }

    @Override
    protected double getBaseProjectileDamage() {
        return 5.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobGoblinLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobGoblinDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobGoblinHit;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.shotShyreTrollFire;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerShiny), 1);

        if (rand.nextInt(50 - lootingMod) == 0)
            dropItem(WeaponRegister.staffShyre, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.runeWind, 4 + rand.nextInt(3 + lootingMod));
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityShyreBeam(this, Enums.MobProjectileType.ENERGY);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getHealth() > 0)
            heal(0.2f);
    }
}
