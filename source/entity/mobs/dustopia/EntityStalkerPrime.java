package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityStalkerPrime extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityStalkerPrime(World world) {
        super(world, entityWidth, 2.5f);
    }

    @Override
    public float getEyeHeight() {
        return 2.3125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.35;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 146;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 14;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobStalkerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobStalkerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobStalkerHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityStalkerPrime;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getAttackTarget() instanceof EntityPlayerMP && canEntityBeSeen(getAttackTarget()) && EntityUtil.isPlayerLookingAtEntity((EntityPlayer)getAttackTarget(), this)) {
            motionX = 0;
            motionZ = 0;

            if (getAttackTarget().getDistanceSq(this) <= 2 * 2)
                PacketUtil.network.sendTo(new PacketScreenOverlay(30, Enums.ScreenOverlays.STATIC), (EntityPlayerMP)getAttackTarget());
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount)) {
            if (!world.isRemote && getAttackTarget() != null && getAttackTarget().getDistanceSq(this) <= 2 * 2) {
                double posX = this.posX + rand.nextGaussian() * 64;
                double posZ = this.posZ + rand.nextGaussian() * 64;
                double posY = world.getHeight((int)posX, (int)posZ);

                attemptTeleport(posX, posY, posZ);
            }

            return true;
        }

        return false;
    }
}
