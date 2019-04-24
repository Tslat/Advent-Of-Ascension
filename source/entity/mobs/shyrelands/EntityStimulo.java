package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityStimulo extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityStimulo(World world) {
        super(world, entityWidth, 1.875f);
    }

    @Override
    public float getEyeHeight() {
        return 1.78125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 85;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 4;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobStimuloLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobStimuloDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobStimuloHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.tokensShyrelands, 1 + rand.nextInt(3 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerShyre), 1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (ticksExisted % 100 == 0)
            heal(getHealth());
    }
}
