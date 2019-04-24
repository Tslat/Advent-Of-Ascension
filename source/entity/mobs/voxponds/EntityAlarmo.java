package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityAlarmo extends AoAMeleeMob {
    public static final float entityWidth = 0.625f;

    public EntityAlarmo(World world) {
        super(world, entityWidth, 1.375f);
    }

    @Override
    public float getEyeHeight() {
        return 1f;
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
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobAlarmoLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobAlarmoDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobAlarmoHit;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(20 - lootingMod) == 0)
            dropItem(WeaponRegister.staffDestruction, 1);

        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitApoco, 1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (isAIDisabled())
            return;

        for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(2), PredicateUtil.IS_VULNERABLE_PLAYER)) {
            if (canEntityBeSeen(pl)) {
                world.createExplosion(this, posX, posY, posZ, 3, false);

                if (!world.isRemote)
                    setDead();

                return;
            }
        }
    }
}
