package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.tslat.tme.api.particle.ParticleBuilder;

public class FakeTntEntity extends Entity {
    private static final EntityDataAccessor<Integer> FUSE_TIME = SynchedEntityData.defineId(FakeTntEntity.class, EntityDataSerializers.INT);

    private boolean dealsDamage = false;

    public FakeTntEntity(EntityType<? extends FakeTntEntity> entityType, Level level) {
        super(entityType, level);

        this.blocksBuilding = true;
    }

    public FakeTntEntity(EntityType<? extends FakeTntEntity> entityType, Level level, double x, double y, double z) {
        this(entityType, level);

        setPos(x, y, z);

        double pulseFrequency = level.random.nextDouble() * 6.2831854820251465;

        setDeltaMovement(-Math.sin(pulseFrequency) * 0.02, 0.20000000298023224, -Math.cos(pulseFrequency) * 0.02);
        setFuse(80);

        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public void setDealsDamage(boolean dealsDamage) {
        this.dealsDamage = dealsDamage;
    }

    public void setFuse(int life) {
        this.entityData.set(FUSE_TIME, life);
    }

    public int getFuse() {
        return this.entityData.get(FUSE_TIME);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FUSE_TIME, 80);
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    public boolean isPickable() {
        return !isRemoved();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.contains("DealsDamage", Tag.TAG_BYTE))
            this.dealsDamage = compoundTag.getBoolean("DealsDamage");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    protected double getDefaultGravity() {
        return 0.04f;
    }

    @Override
    public void tick() {
        if (!level().isClientSide && this.tickCount == 1)
            level().playSound(null, getX(), getY(), getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1, 1);

        handlePortal();
        applyGravity();
        move(MoverType.SELF, getDeltaMovement());
        setDeltaMovement(getDeltaMovement().scale(0.98f));

        if (onGround())
            setDeltaMovement(getDeltaMovement().multiply(0.7, -0.5, 0.7));

        int fuseTick = getFuse();

        setFuse(--fuseTick);

        if (fuseTick <= 0) {
            discard();

            if (!level().isClientSide)
                explode();
        }
        else {
            updateInWaterStateAndDoFluidPushing();

            if (level().isClientSide)
                level().addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.5, getZ(), 0.0, 0.0, 0.0);
        }
    }

    protected void explode() {
        if (this.dealsDamage) {
            level().explode(this, Explosion.getDefaultDamageSource(level(), this), null, getX(), getY(0.0625), getZ(), 4f, false, Level.ExplosionInteraction.NONE);
        }
        else if (level().isClientSide) {
            level().playLocalSound(getX(), getY(), getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 4f, (1f + (level().random.nextFloat() - level().random.nextFloat()) * 0.2F) * 0.7f, false);
            ParticleBuilder.forPosition(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ())
                    .spawnClientParticles(level());
        }
    }
}
