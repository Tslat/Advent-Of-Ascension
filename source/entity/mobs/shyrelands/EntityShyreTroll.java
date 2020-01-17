package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
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
        return 165;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 17;
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

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityShyreTroll;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityShyreBeam(this, Enums.MobProjectileType.ENERGY);
    }
}
