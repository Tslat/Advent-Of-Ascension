package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.object.entity.base.AoARangedAttacker;

public abstract class BaseMobProjectile extends ThrowableEntity {
	private Type projectileType;
	private AoARangedAttacker shooter;

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, double posX, double posY, double posZ, Type projectileType) {
		super(entityType, world);

		setPos(posX, posY, posZ);
		setDeltaMovement(random.nextGaussian() / 33 + 0.03, -2, random.nextGaussian() / 33 + 0.03);

		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, Entity target, Type projectileType) {
		this(entityType, world, shooter, target.getX(), target.getY() + 25, target.getZ(), projectileType);
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, Type projectileType) {
		super(entityType, world);

		if (shooter instanceof LivingEntity) {
			LivingEntity owner = (LivingEntity)shooter;
			setOwner(owner);
			setPos(owner.getX(), owner.getY() + owner.getEyeHeight() - 0.10000000149011612D, owner.getZ());
		}

		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public Type getProjectileType() {
		return projectileType;
	}

	@Override
	public float getGravity() {
		return 0.0f;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void tick() {
		super.tick();

		if (tickCount > 500)
			remove();
	}

	@Override
	protected void onHit(RayTraceResult result) {
 		if (result.getType() != RayTraceResult.Type.BLOCK || level.getBlockState(new BlockPos(result.getLocation())).getMaterial().blocksMotion()) {
			if (!level.isClientSide) {
				if (result.getType() == RayTraceResult.Type.ENTITY) {
					if (result.hitInfo == shooter || shooter == null)
						return;

					shooter.doProjectileEntityImpact(this, ((EntityRayTraceResult)result).getEntity());
				}
				else if (result.getType() == RayTraceResult.Type.BLOCK) {
					if (shooter != null) {
						shooter.doProjectileBlockImpact(this, level.getBlockState(new BlockPos(result.getLocation())), new BlockPos(result.getLocation()), ((BlockRayTraceResult)result).getDirection());
					}
				}

				remove();
			}
		}
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	public enum Type {
		MAGIC,
		PHYSICAL,
		GUN,
		OTHER
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
