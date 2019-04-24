package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nullable;

public class EntityFlowerface extends AoAMeleeMob {
    public static final float entityWidth = 0.5f;

    public EntityFlowerface(World world) {
        super(world, entityWidth, 1.5f);
    }

    @Override
    public float getEyeHeight() {
        return 1.5f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 100;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 3;
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

    @Override
    public boolean getCanSpawnHere() {
        return (posY > 66 || world.provider.getDimension() == ConfigurationUtil.dimAncientCavern) && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (world.provider.getDimension() != ConfigurationUtil.dimAncientCavern) {
            if (rand.nextInt(40 - lootingMod) == 0)
                dropItem(ItemRegister.smallPetalRed, 1);

            if (rand.nextInt(40 - lootingMod) == 0) {
                dropItem(ItemRegister.realmstoneAncientCavern, 1);
            }
            else if (rand.nextInt(20 - lootingMod) == 0) {
                dropItem(ItemRegister.realmstoneVoxPonds, 1);
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRaining() && getHealth() > 0)
            heal(0.2f);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.dimAncientCavern) {
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
    }
}
