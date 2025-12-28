package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NonPhysicalWeaponProjectile extends Projectile implements WeaponProjectile {
    private WeaponFiringContext context = null;

    protected int piercedCount = 0;
    protected Entity lastPiercedTarget = null;

    public NonPhysicalWeaponProjectile(EntityType<? extends NonPhysicalWeaponProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public NonPhysicalWeaponProjectile(EntityType<? extends NonPhysicalWeaponProjectile> entityType, Level level, WeaponFiringContext context) {
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
    protected double getDefaultGravity() {
        return 0d;
    }

    @Nullable
    @Override
    public ItemStack getWeaponItem() {
        return getShotContext().weaponStack();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {}

    protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {}
    protected void doEntityImpactFx(EntityHitResult rayTrace, Entity hitEntity) {}
    protected void doBlockImpact(BlockHitResult rayTrace, BlockState impactedBlock) {}
    protected void doBlockImpactFx(BlockHitResult rayTrace, BlockState block) {}

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }

    @Override
    public Vec3 getMovementToShoot(double angleX, double angleY, double angleZ, float velocity, float variationDegrees) {
        float inaccuracy = Mth.DEG_TO_RAD * variationDegrees / (Math.max(3, velocity) / 3f);

        return new Vec3(angleX, angleY, angleZ).normalize()
                .add(this.random.triangle(0, inaccuracy), this.random.triangle(0, inaccuracy), this.random.triangle(0, inaccuracy))
                .scale(velocity);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double boundsSize = getBoundingBox().getSize() * 4d;

        if (Double.isNaN(boundsSize))
            boundsSize = 4d;

        boundsSize *= 64d;

        return distance < boundsSize * boundsSize;
    }

    @Override
    public boolean canUsePortal(boolean allowPassengers) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount > getShotContext().projectileLifespan()) {
            discard();

            return;
        }

        HitResult rayTrace = checkCollisionOnMove(this, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, this::canHitEntity);

        if (rayTrace.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, rayTrace))
            onHit(rayTrace);

        checkInsideBlocks();

        Vec3 velocity = getDeltaMovement();
        double nextX = getX() + velocity.x;
        double nextY = getY() + velocity.y;
        double nextZ = getZ() + velocity.z;

        updateRotation();

        float drag = 0.99f;

        if (isInWater()) {
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.BUBBLE, nextX - velocity.x * (double)0.25F, nextY - velocity.y * (double)0.25F, nextZ - velocity.z * (double)0.25F, velocity.x, velocity.y, velocity.z);
            }

            drag = 0.8f;
        }

        setDeltaMovement(velocity.scale(drag));
        applyGravity();
        setPos(nextX, nextY, nextZ);
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type hitType = result.getType();

        if (hitType == HitResult.Type.ENTITY) {
            onHitEntity((EntityHitResult)result);
            level().gameEvent(GameEvent.PROJECTILE_LAND, result.getLocation(), GameEvent.Context.of(this, null));
        }
        else if (hitType == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)result;
            BlockPos hitPos = blockHitResult.getBlockPos();

            onHitBlock(blockHitResult);
            level().gameEvent(GameEvent.PROJECTILE_LAND, hitPos, GameEvent.Context.of(this, level().getBlockState(hitPos)));
        }
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
        Entity selfOrParent = EntityUtil.getPartOrPartOwner(hitEntity);

        doEntityImpactFx(result, hitEntity);

        if (selfOrParent != this.lastPiercedTarget) {
            if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
                projectileFiringWeapon.doEntityImpact(level(), this, RayTrace.wrap(position(), result), hitEntity);

            doEntityImpact(result, hitEntity);
        }

        if (this.piercedCount++ < getShotContext().piercingValue()) {
            this.lastPiercedTarget = selfOrParent;

            return;
        }

        discard();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        this.context = loadContextFromNbt(compound.contains("shot_context", Tag.TAG_COMPOUND) ? compound.getCompound("shot_context") : null, level());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
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
        return WeaponFiringContext.Builder.of(AoAWeapons.ILLUSION_SMG.toStack(), getOwner()).build();
    }
}
