package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFischer extends AoAMeleeMob implements HunterEntity {
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
        return 40;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3286;
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
    public int getHunterReq() {
        return 33;
    }

    @Override
    public float getHunterXp() {
        return 95;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 30 && world.getDifficulty() != EnumDifficulty.PEACEFUL && rand.nextInt(getSpawnChanceFactor()) == 0;
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 15;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
