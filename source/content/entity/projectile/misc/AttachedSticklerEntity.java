package net.tslat.aoa3.content.entity.projectile.misc;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;
import org.joml.Vector3f;

public class AttachedSticklerEntity extends PhysicalWeaponProjectile {
    public static final EntityDataAccessor<Integer> STUCK_TO = SynchedEntityData.defineId(AttachedSticklerEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Vector3f> STUCK_OFFSET = SynchedEntityData.defineId(AttachedSticklerEntity.class, EntityDataSerializers.VECTOR3);
    private LivingEntity stuckTo;

    public AttachedSticklerEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level world) {
        super(entityType, world);
    }

    public AttachedSticklerEntity(EntityType<? extends AttachedSticklerEntity> entityType, Level level, Entity shooter, LivingEntity target, Vec3 stuckOffset, WeaponFiringContext context) {
        super(entityType, level);

        this.stuckTo = target;
        getEntityData().set(STUCK_TO, target.getId());
        getEntityData().set(STUCK_OFFSET, stuckOffset.toVector3f());
        updatePosition();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(STUCK_TO, -1);
        builder.define(STUCK_OFFSET, new Vector3f(0, 0, 0));
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public double getDefaultGravity() {
        return 0;
    }

    @Override
    public boolean canHitEntity(Entity target) {
        return false;
    }

    protected void updatePosition() {
        if (this.stuckTo != null) {
            Vector3f offset = getEntityData().get(STUCK_OFFSET);

            setPos(this.stuckTo.position().add(offset.x, offset.y, offset.z));
        }
    }

    protected void explode() {
        if (level() instanceof ServerLevel level) {
            AoAExplosionBuilder.at(this, AoAExplosions.STICKLER, StandardExplosion::new).explode();
            discard();
        }
    }

    protected boolean isExpired() {
        return this.stuckTo == null || !this.stuckTo.isAlive() || this.tickCount > 100;
    }

    @Override
    public void tick() {
        this.leftOwner = true;

        updatePosition();

        if (isExpired())
            explode();

        level().getProfiler().push("entityBaseTick");

        this.walkDistO = this.walkDist = 0;
        this.xRotO = getXRot();
        this.yRotO = getYRot();

        handlePortal();

        this.isInPowderSnow = this.wasInPowderSnow = false;

        if (level().isClientSide)
            clearFire();

        checkBelowWorld();

        this.firstTick = false;

        level().getProfiler().pop();
    }
}