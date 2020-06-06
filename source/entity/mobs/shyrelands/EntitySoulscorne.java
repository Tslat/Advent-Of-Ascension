package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntitySoulscorne extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntitySoulscorne(World world) {
        super(world, entityWidth, 1.8125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.59375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 140d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_SOULSCORNE_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_SOULSCORNE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_SOULSCORNE_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entitySoulscorne;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)target;
            ArrayList<PotionEffect> positiveEffects = new ArrayList<PotionEffect>(entity.getActivePotionEffects().size());

            for (PotionEffect effect : entity.getActivePotionEffects()) {
                if (!effect.getPotion().isBadEffect())
                    positiveEffects.add(effect);
            }

            for (PotionEffect effect : positiveEffects) {
                entity.removePotionEffect(effect.getPotion());
            }
        }
    }
}
