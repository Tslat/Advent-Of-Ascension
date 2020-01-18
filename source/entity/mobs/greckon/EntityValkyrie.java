package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityValkyrieShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityValkyrie extends AoAFlyingRangedMob {
    public static final float entityWidth = 0.75f;

    public EntityValkyrie(World world) {
        super(world, entityWidth, 1.125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.03125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 115;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 13.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.1;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobValkyrieLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobValkyrieDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobValkyrieHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityValkyrie;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return null;
    }

    @Override
    protected double getSpawnChanceFactor() {
        return ConfigurationUtil.EntityConfig.mobSpawnFrequencyModifier / 5d;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityValkyrieShot(this, Enums.MobProjectileType.OTHER);
    }
}
