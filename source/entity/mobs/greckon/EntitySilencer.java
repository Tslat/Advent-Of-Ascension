package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntitySilencer extends AoAMeleeMob {
    public static final float entityWidth = 0.5625f;

    public EntitySilencer(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.84375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.7;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 50;
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
        return SoundsRegister.mobSilencerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSilencerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSilencerHit;
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 5;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextBoolean())
            dropItem(WeaponRegister.staffGhoul, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!world.isRemote) {
            EntityPlayer pl = world.getClosestPlayer(posX, posY, posZ, 64, false);

            if (pl != null && !pl.capabilities.isCreativeMode) {
                if (pl.getHeldItem(EnumHand.MAIN_HAND) != ItemStack.EMPTY) {
                    if (EntityUtil.isPlayerLookingAtEntity(pl, this) && canEntityBeSeen(pl)) {
                        ItemStack heldStack = pl.getHeldItem(EnumHand.MAIN_HAND).copy();

                        pl.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
                        pl.entityDropItem(heldStack, 0.5f);
                        pl.inventoryContainer.detectAndSendChanges();
                    }
                }
                else if (pl.getHeldItem(EnumHand.OFF_HAND) != ItemStack.EMPTY) {
                    if (EntityUtil.isPlayerLookingAtEntity(pl, this) && canEntityBeSeen(pl)) {
                        ItemStack heldStack = pl.getHeldItem(EnumHand.OFF_HAND).copy();

                        pl.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
                        pl.entityDropItem(heldStack, 0.5f);
                        pl.inventoryContainer.detectAndSendChanges();
                    }
                }
            }
        }
    }
}
