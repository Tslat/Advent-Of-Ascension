package net.tslat.aoa3.entity.projectile.mob;

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
import net.tslat.aoa3.entity.base.AoARangedAttacker;

public abstract class BaseMobProjectile extends ThrowableEntity {
	private Type projectileType;
	private AoARangedAttacker shooter;

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, double posX, double posY, double posZ, Type projectileType) {
		super(entityType, world);

		setPosition(posX, posY, posZ);
		setMotion(rand.nextGaussian() / 33 + 0.03, -2, rand.nextGaussian() / 33 + 0.03);

		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, Entity target, Type projectileType) {
		this(entityType, world, shooter, target.getPosX(), target.getPosY() + 25, target.getPosZ(), projectileType);
	}

	public BaseMobProjectile(EntityType<? extends ThrowableEntity> entityType, World world, AoARangedAttacker shooter, Type projectileType) {
		super(entityType, world);

		if (shooter instanceof LivingEntity) {
			this.owner = (LivingEntity)shooter;
			setPosition(owner.getPosX(), owner.getPosY() + owner.getEyeHeight() - 0.10000000149011612D, owner.getPosZ());
		}

		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public Type getProjectileType() {
		return projectileType;
	}

	@Override
	public float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	protected void registerData() {}

	@Override
	public void tick() {
		super.tick();

		if (ticksExisted > 500)
			remove();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.getType() != RayTraceResult.Type.BLOCK || world.getBlockState(new BlockPos(result.getHitVec())).getMaterial().blocksMovement()) {
			if (!world.isRemote) {
				if (result.getType() == RayTraceResult.Type.ENTITY) {
					if (result.hitInfo == shooter || shooter == null)
						return;

					shooter.doProjectileEntityImpact(this, ((EntityRayTraceResult)result).getEntity());
				}
				else if (result.getType() == RayTraceResult.Type.BLOCK) {
					if (shooter != null)
						shooter.doProjectileBlockImpact(this, world.getBlockState(new BlockPos(result.getHitVec())), new BlockPos(result.getHitVec()), ((BlockRayTraceResult)result).getFace());
				}

				remove();
			}
		}
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	public enum Type {
		MAGIC,
		PHYSICAL,
		GUN,
		OTHER
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
