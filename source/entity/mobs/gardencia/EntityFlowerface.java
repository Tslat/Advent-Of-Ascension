package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;

public class EntityFlowerface extends AoAMeleeMob {
    public static final float entityWidth = 0.5f;
    private boolean candiedWater = false;

    public EntityFlowerface(World world) {
        super(world, entityWidth, 1.5f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 88;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 9;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.plantThump;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.plantThump;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityFlowerface;
    }

    @Override
    protected void onInsideBlock(IBlockState state) {
        if (state.getBlock() == BlockRegister.candiedWater) {
            if (!candiedWater) {
                EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.GARDENCIA_CANDIED_WATER_BUFF);
                setHealth(getHealth() * 1.5f);

                candiedWater = true;
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (candiedWater)
            compound.setBoolean("AoACandiedWater", true);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("AoACandiedWater"))
            candiedWater = true;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (isEntityAlive() && getHealth() < getMaxHealth()) {
            if (isInWater()) {
                heal(0.2f);
            }
            else if (world.isRainingAt(getPosition())) {
                heal(0.1f);
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote) {
            if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern) {
                Entity source = cause.getTrueSource();
                EntityPlayer killer = null;

                if (source != null) {
                    if (source instanceof EntityPlayer) {
                        killer = (EntityPlayer)source;
                    }
                    else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
                        killer = (EntityPlayer)((EntityTameable)source).getOwner();
                    }
                }

                if (killer != null)
                    PlayerUtil.addTributeToPlayer(killer, Enums.Deities.SELYAN, 8);
            }

            if (candiedWater && cause.getTrueSource() instanceof EntityPlayer && ItemUtil.consumeItem((EntityPlayer)cause.getTrueSource(), new ItemStack(ItemRegister.realmstoneBlank)))
                ItemUtil.givePlayerItemOrDrop((EntityPlayer)cause.getTrueSource(), new ItemStack(ItemRegister.realmstoneBorean));
        }
    }
}
