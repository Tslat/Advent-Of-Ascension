package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EntityLivingBase;
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
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityStalker extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityStalker(World world) {
        super(world, entityWidth, 2.5625f);
    }

    @Override
    public float getEyeHeight() {
        return 2.375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 138;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 13.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_STALKER_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_STALKER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_STALKER_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityStalker;
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

                if (posY > 0)
                    attemptTeleport(posX, posY, posZ);
            }

            return true;
        }

        return false;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }
}
