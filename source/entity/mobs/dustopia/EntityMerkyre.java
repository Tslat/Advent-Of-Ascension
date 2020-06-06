package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityMerkyre extends AoAMeleeMob {
    public static final float entityWidth = 0.5625f;

    public EntityMerkyre(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 119;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.26d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_MERKYRE_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_MERKYRE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_MERKYRE_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityMerkyre;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount) && !EntityUtil.isEnvironmentalDamage(source)) {
            EntityAreaEffectCloud effectCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);

            effectCloud.setDuration(30);
            effectCloud.setRadius(1.5f);
            effectCloud.setOwner(this);
            effectCloud.setWaitTime(0);
            effectCloud.setColor(Enums.RGBIntegers.BLACK);
            effectCloud.addEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0, false, true));
            effectCloud.setRadiusPerTick(-(effectCloud.getRadius() - 0.5f) / (float)effectCloud.getDuration());

            world.spawnEntity(effectCloud);

            return true;
        }

        return false;
    }
}
