package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityArocknid extends AoAMeleeMob {
    public static final float entityWidth = 1.7f;
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityArocknid.class, DataSerializers.BYTE);

    public EntityArocknid(World world) {
        super(world, entityWidth, 1.3125f);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, (byte)0);
    }

    @Override
    protected PathNavigate createNavigator(World world) {
        return new PathNavigateClimber(this, world);
    }

    @Override
    public float getEyeHeight() {
        return 0.59375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8d;
    }

    @Override
    protected double getBaseArmour() {
        return 2d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 75d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 8d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.295d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCaveBugLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCaveBugDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCaveBugHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SPIDER_STEP;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityArocknid;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote)
            setBesideClimbableBlock(this.collidedHorizontally);
    }

    @Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataManager.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte climbingBit = this.dataManager.get(CLIMBING);

        if (climbing) {
            climbingBit = (byte)(climbingBit | 1);
        }
        else {
            climbingBit = (byte)(climbingBit & -2);
        }

        this.dataManager.set(CLIMBING, climbingBit);
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase)
            ((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 0, false, true));
    }
}
