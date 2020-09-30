package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityGrocculate extends AoAMeleeMob {
    public static final float entityWidth = 1.5f;

    public EntityGrocculate(World world) {
        super(world, entityWidth, 3.5f);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.9;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 112;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.29;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_GROCCULATE_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_GROCCULATE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_GROCCULATE_HIT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.VERY_HEAVY_STEP;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityGrocculate;
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (super.attackEntityAsMob(target)) {
            if (target instanceof EntityLivingBase) {
                double resist = 1;
                IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

                if (attrib != null)
                    resist -= attrib.getAttributeValue();

                target.addVelocity(motionX * 10.5 * resist, motionY * resist, motionZ * 10.5 * resist);
                target.velocityChanged = true;
            }

            return true;
        }

        return false;
    }
}
