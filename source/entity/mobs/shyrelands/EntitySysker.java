package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntitySysker extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntitySysker(World world) {
        super(world, entityWidth, 1.40625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.15625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 75;
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
        return SoundsRegister.mobSyskerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSyskerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSyskerHit;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.tokensShyrelands, 3 + rand.nextInt(5 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerLight), 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(1 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        List<EntityPlayer> nearbyPlayers = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(8), PredicateUtil.IS_VULNERABLE_PLAYER);

        if (!nearbyPlayers.isEmpty()) {
            for (EntityPlayer pl : nearbyPlayers) {
                if (!pl.isSneaking()) {
                    pl.addVelocity(motionX * 0.05, 0.0425, motionZ * 0.05);
                    pl.addVelocity(Math.signum(posX - pl.posX) * 0.029, 0, Math.signum(posZ - pl.posZ) * 0.029);
                    pl.fallDistance = 0;
                }
            }

            if (rand.nextInt(150) == 0) {
                for (EntityPlayer pl : nearbyPlayers) {
                    if (!pl.isSneaking()) {
                        pl.addVelocity(motionX * 0.05, -1, motionZ * 0.05);
                        pl.fallDistance = 0;
                    }
                }
            }
        }
    }
}