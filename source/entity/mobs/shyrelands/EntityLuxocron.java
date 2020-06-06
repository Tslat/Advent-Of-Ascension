package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityLuxocron extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;

    public EntityLuxocron(World world) {
        super(world, entityWidth, 0.9375f);
    }

    @Override
    public float getEyeHeight() {
        return 0.90625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 169;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 14;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_LUXOCRON_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_LUXOCRON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_LUXOCRON_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityLuxocron;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(4), PredicateUtil.IS_VULNERABLE_PLAYER)) {
            pl.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30, 2, true, false));
        }
    }
}
