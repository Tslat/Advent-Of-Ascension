package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ThrownItemProjectile extends ThrowableItemProjectile implements ItemSupplier, WeaponProjectile {
    private WeaponFiringContext context = null;

    protected int piercedCount = 0;
    protected Entity lastPiercedTarget = null;

    public ThrownItemProjectile(EntityType<? extends ThrownItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownItemProjectile(EntityType<? extends ThrownItemProjectile> entityType, Level level, WeaponFiringContext context) {
        super(entityType, level);

        this.context = context;

        setOwner(context.getShooter());
    }

    @Override
    public WeaponFiringContext getShotContext() {
        if (this.context == null)
            this.context = getDefaultShotContext();

        return this.context;
    }

    @Nullable
    @Override
    public Entity getShooter() {
        return getOwner();
    }

    @Override
    protected Item getDefaultItem() {
        return getShotContext().weaponStack().getItem();
    }

    protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {}
    protected void doEntityImpactFx(EntityHitResult rayTrace, Entity hitEntity) {}
    protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {}
    protected void doBlockImpactFx(BlockHitResult rayTrace, BlockState block) {}

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return explosion.getDirectSourceEntity() != null && explosion.getDirectSourceEntity() == getOwner();
    }

    @Override
    public Vec3 getMovementToShoot(double angleX, double angleY, double angleZ, float velocity, float variationDegrees) {
        float inaccuracy = Mth.DEG_TO_RAD * variationDegrees / (Math.max(3, velocity) / 3f);

        return new Vec3(angleX, angleY, angleZ).normalize()
                .add(this.random.triangle(0, inaccuracy), this.random.triangle(0, inaccuracy), this.random.triangle(0, inaccuracy))
                .scale(velocity);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        final BlockState impactedBlock = level().getBlockState(result.getBlockPos());

        doBlockImpactFx(result, impactedBlock);

        if (!impactedBlock.blocksMotion())
            return;

        if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
            projectileFiringWeapon.doBlockImpact(level(), this, RayTrace.wrap(position(), result), impactedBlock);

        doBlockImpact(result, impactedBlock);
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity hitEntity = result.getEntity();

        doEntityImpactFx(result, hitEntity);

        if (hitEntity != this.lastPiercedTarget) {
            if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
                projectileFiringWeapon.doEntityImpact(level(), this, RayTrace.wrap(position(), result), hitEntity);

            doEntityImpact(result, hitEntity);
        }

        if (this.piercedCount++ < getShotContext().piercingValue()) {
            this.lastPiercedTarget = hitEntity;

            return;
        }

        discard();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        this.context = loadContextFromNbt(compound.contains("shot_context", Tag.TAG_COMPOUND) ? compound.getCompound("shot_context") : null, level());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.put("shot_context", getShotContext().toNbt(level().registryAccess()));
    }

    @NotNull
    protected WeaponFiringContext loadContextFromNbt(@Nullable CompoundTag nbt, Level level) {
        if (nbt == null)
            return getDefaultShotContext();

        return WeaponFiringContext.Instance.fromNbt(nbt, level);
    }

    @Override
    public WeaponFiringContext getDefaultShotContext() {
        return WeaponFiringContext.Builder.of(AoAWeapons.SLICE_STAR.toStack(), getOwner()).build();
    }
}
