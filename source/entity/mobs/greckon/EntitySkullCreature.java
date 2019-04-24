package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySkullCreature extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;

    public EntitySkullCreature(World world) {
        super(world, entityWidth, 2.3125f);
    }

    @Override
    public float getEyeHeight() {
        return 2.03125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 40;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobSkullCreatureLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSkullCreatureDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSkullCreatureHit;
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (getHealth() < 15) {
            if ((getHealth() > 10 || getHealth() < 5) && getHealth() > 0)
                heal(0.2f);
        }
    }

    @Override
    public boolean getIsInvulnerable() {
        return getHealth() < 15 && (getHealth() > 10 || getHealth() < 5) && getHealth() > 0;
    }
}
