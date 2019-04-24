package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

public class EntityArcFlower extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;
    public EntityArcFlower(World world) {
        super(world, entityWidth, 0.9375f);
    }

    @Override
    public float getEyeHeight() {
        return 0.05f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 5;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
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
