package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;

import javax.annotation.Nullable;
import java.util.List;

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
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 4.0f));
        tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 74;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
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

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityAlarmo;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRemote || isAIDisabled())
            return;

        List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(4), pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && canEntityBeSeen(pl));

        if (!playerList.isEmpty()) {
            List<EntityLivingBase> mobList = world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(30), mob -> mob instanceof IMob);

            addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, -1, 20, true, false));

            for (EntityLivingBase mob : mobList) {
                mob.setRevengeTarget(playerList.get(rand.nextInt(playerList.size())));
            }
        }
    }
}
