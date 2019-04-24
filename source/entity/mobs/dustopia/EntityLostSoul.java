package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityLostSoul extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityLostSoul(World world) {
        super(world, entityWidth, 2.0f);
    }

    @Override
    public float getEyeHeight() {
        return 1.78125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
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
        return SoundsRegister.mobLostSoulLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobLostSoulDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobLostSoulHit;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensDustopia, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(200 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitDarkly, 1);

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerDustopian), 1);

        if (rand.nextInt(40 - lootingMod) == 0)
            dropItem(ItemRegister.realmstoneShyrelands, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();


        if (!world.isRemote && getAttackTarget() instanceof EntityPlayer) {
            if (EntityUtil.isPlayerLookingAtEntity((EntityPlayer)getAttackTarget(), this) && canEntityBeSeen(getAttackTarget()))
                heal(3.0f);
        }
    }
}
