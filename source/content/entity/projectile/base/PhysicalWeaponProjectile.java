package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhysicalWeaponProjectile extends ThrowableProjectile implements WeaponProjectile {
    private WeaponFiringContext context = null;

    protected int piercedCount = 0;
    protected Entity lastPiercedTarget = null;

    public PhysicalWeaponProjectile(EntityType<? extends PhysicalWeaponProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public PhysicalWeaponProjectile(EntityType<? extends PhysicalWeaponProjectile> entityType, Level level, WeaponFiringContext context) {
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
        return 0;
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
    protected void doBlockImpactFx(BlockHitResult rayTrace, BlockState block) {
        if (level() instanceof ServerLevel level) {
            SoundBuilder.at(block.getSoundType(level, rayTrace.getBlockPos(), getShooter()).getHitSound(), level, rayTrace.getBlockPos()).volume(0.5f).varyPitch(0.25f).play();
            ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, block).setPos(rayTrace.getBlockPos()), rayTrace.getLocation())
                    .spawnNTimes(5)
                    .cutoffDistance(64)
                    .addBulkProperty(builder -> builder.velocity(RandomUtil.gaussianOffset(getDeltaMovement().scale(-0.1f), 0.05f, 0.05f, 0.05f)))
                    .addBulkProperty(builder -> builder.scaleMod(RandomUtil.valueBetween(0.5f, 0.75f)))
                    .sendToAllPlayersTrackingBlock(level, rayTrace.getBlockPos());
        }
    }

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
    public void tick() {
        if (!this.hasBeenShot) {
            gameEvent(GameEvent.PROJECTILE_SHOOT, getOwner());

            this.hasBeenShot = true;
        }

        if (!this.leftOwner)
            this.leftOwner = checkLeftOwner();

        baseTick();

        HitResult rayTrace = checkCollisionOnMove(this, AoAGameRules.checkDestructiveWeaponPhysics(level()) ? ClipContext.Block.OUTLINE : ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this::canHitEntity);

        if (rayTrace.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, rayTrace))
            hitTargetOrDeflectSelf(rayTrace);

        checkInsideBlocks();

        Vec3 velocity = getDeltaMovement();
        Vec3 newPos = position().add(velocity);

        updateRotation();

        if (isInWater()) {
            Vec3 bubblePos = newPos.subtract(velocity.scale(0.25f));

            for (int i = 0; i < 4; i++) {
                level().addParticle(ParticleTypes.BUBBLE, bubblePos.x, bubblePos.y, bubblePos.z, velocity.x, velocity.y, velocity.z);
            }

            velocity = velocity.scale(0.8f);
        }
        else {
            velocity = velocity.scale(0.99f);
        }

        setDeltaMovement(velocity);
        applyGravity();
        setPos(newPos);

        if (this.tickCount > getShotContext().projectileLifespan())
            discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        final BlockState impactedBlock = level().getBlockState(result.getBlockPos());

        doBlockImpactFx(result, impactedBlock);

        if (doBlockDestruction(result, impactedBlock))
            return;

        if (!impactedBlock.blocksMotion())
            return;

        if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
            projectileFiringWeapon.doBlockImpact(level(), this, RayTrace.wrap(position(), result), impactedBlock);

        doBlockImpact(result, impactedBlock);
        discard();
    }

    protected boolean doBlockDestruction(BlockHitResult rayTrace, BlockState block) {
        if (AoAGameRules.checkDestructiveWeaponPhysics(level())) {
            BlockPos blockPos = rayTrace.getBlockPos();
            float hardness = block.getDestroySpeed(level(), blockPos);

            if (hardness < 0)
                return false;

            float penetrationMod = 0.3f * Math.max(getBbWidth(), getBbHeight()) / 0.25f;

            if (hardness <= penetrationMod) {
                if (!level().isClientSide) {
                    if (this.random.nextBoolean()) {
                        level().destroyBlock(blockPos, true);
                    }
                    else {
                        level().setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                    }
                }

                this.random.setSeed((long)Math.pow(getId(), Math.pow(block.getSeed(blockPos), (level().getGameTime() / 3f))));

                return this.random.nextFloat() > hardness / ((1.5f * 0.3f) / penetrationMod);
            }
        }

        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity hitEntity = result.getEntity();
        Entity selfOrParent = EntityUtil.getPartOrPartOwner(hitEntity);

        doEntityImpactFx(result, hitEntity);

        if (selfOrParent != this.lastPiercedTarget) {
            if (getShotContext().weaponStack().getItem() instanceof ProjectileFiringWeapon projectileFiringWeapon)
                projectileFiringWeapon.doEntityImpact(level(), this, RayTrace.wrap(position(), result), hitEntity);

            if (isOnFire())
                hitEntity.igniteForSeconds(5);

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
        return WeaponFiringContext.Builder.of(AoAWeapons.SQUAD_GUN.toStack(), getOwner()).build();
    }
}
