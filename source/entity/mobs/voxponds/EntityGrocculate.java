package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
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
        return 1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 160;
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
        return SoundsRegister.mobGrocculateLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobGrocculateHit;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobGrocculateHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.veryHeavyStep;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.cannonVoxCannon, 1);

        if (rand.nextInt(5) == 0)
            dropItem(ItemRegister.toxicLump, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensVoxPonds, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (super.attackEntityAsMob(target)) {
            if (target instanceof EntityLivingBase) {
                double resist = 1;
                IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

                if (attrib != null)
                    resist -= attrib.getAttributeValue();

                ((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, true));
                target.addVelocity(motionX * 10.5 * resist, motionY * resist, motionZ * 10.5 * resist);
                target.velocityChanged = true;
            }

            return true;
        }

        return false;
    }
}
