package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;

public class EntityArcFlower extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;
    public EntityArcFlower(World world) {
        super(world, entityWidth, 0.9375f);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, AoAMinion.class, 10, true, false, EntityTameable::isTamed));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public float getEyeHeight() {
        return 0.05f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 1;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 14;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected void jump() {}

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer nearestPlayer = world.getClosestPlayerToEntity(this, 64);

        if (nearestPlayer == null)
            return;

        if (nearestPlayer.canEntityBeSeen(this)) {
            motionX = 0;
            motionZ = 0;
        }
    }

    @Override
    public void addVelocity(double x, double y, double z) {}

    @Override
    public void applyEntityCollision(Entity entityIn) {}

    @Override
    protected boolean canDropLoot() {
        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote) {
            EntityArcworm arcworm = new EntityArcworm(world);

            arcworm.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            world.spawnEntity(arcworm);
            setDead();
        }
    }
}
