package net.tslat.aoa3.entity.mob.voxponds;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ExoidEntity extends AoAMeleeMob {
    private int tier = 1;

    public ExoidEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExoidEntity(World world, int tier) {
        this(AoAEntities.Mobs.EXOID.get(), world);
        this.tier = tier;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.15;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 100;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 11.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_GADGETOID_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_GADGETOID_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_GADGETOID_HURT.get();
    }

    @Override
    public boolean preventDespawn() {
        return true;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!world.isRemote && ticksExisted >= 900) {
            WorldUtil.createExplosion(this, world, 3f);
            remove();
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote && tier < 5) {
            ExoidEntity exoid = new ExoidEntity(world, tier + 1);
            BlockPos newPos = RandomUtil.getRandomPositionWithinRange(getPosition(), 45, 0, 45, true, world);

            exoid.setLocationAndAngles(newPos.getX(), newPos.getY(), newPos.getZ(), rotationYaw, rotationPitch);
            world.addEntity(exoid);
        }
    }
}
