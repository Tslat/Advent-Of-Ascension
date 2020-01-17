package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityFischer extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityFischer(World world) {
        super(world, entityWidth, 1.0f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 79;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobGadgetoidLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobGadgetoidDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobGadgetoidHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityFischer;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (isInWater()) {
            if (getAttackTarget() != null && getAttackTarget().posY > posY)
                motionY = 0.25;

            fallDistance = 0.5f;

            if (motionX > -1.100000023841858 && motionX < 1.100000023841858)
                motionX *= 1.100000023841858;

            if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858)
                motionZ *= 1.100000023841858;
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 30 && super.getCanSpawnHere();
    }
}
