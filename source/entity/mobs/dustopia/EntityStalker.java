package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.ItemRegister;
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

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!world.isRemote && getAttackTarget() instanceof EntityPlayerMP) {
            if (EntityUtil.isPlayerLookingAtEntity((EntityPlayerMP)getAttackTarget(), this) && canEntityBeSeen(getAttackTarget()))
                PacketUtil.network.sendTo(new PacketScreenOverlay(30, Enums.ScreenOverlays.STATIC), (EntityPlayerMP) getAttackTarget());
        }
    }
}
