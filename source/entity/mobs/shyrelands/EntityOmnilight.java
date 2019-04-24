package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityOmnilightShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityOmnilight extends AoAFlyingRangedMob {
    public static final float entityWidth = 0.9f;

    public EntityOmnilight(World world) {
        super(world, entityWidth, 0.9f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 30;
    }

    @Override
    protected double getBaseProjectileDamage() {
        return 8;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.1;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobOmnilightLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobOmnilightDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobOmnilightHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityOmnilightShot(this, Enums.MobProjectileType.ENERGY);
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.tokensShyrelands, 2 + rand.nextInt(4 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerLight), 1);
    }
}
