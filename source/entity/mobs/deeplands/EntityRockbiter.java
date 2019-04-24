package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityRockbiter extends AoAMeleeMob {
    public static final float entityWidth = 1f;

    public EntityRockbiter(World world) {
        super(world, entityWidth, 1.125f);
    }

    @Override
    public float getEyeHeight() {
        return 0.96875f;
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
        return 7;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobRockbiterLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobRockbiterDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobRockbiterHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(4) == 0)
            dropItem(ItemRegister.rockBones, 1);

        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitPredator, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensDeeplands, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerDeep), 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }
}
