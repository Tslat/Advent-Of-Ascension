package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityGadgetoid extends AoAMeleeMob {
    public static final float entityWidth = 1f;

    public EntityGadgetoid(World world) {
        super(world, entityWidth, 1.8125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
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
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitApoco, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensVoxPonds, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
