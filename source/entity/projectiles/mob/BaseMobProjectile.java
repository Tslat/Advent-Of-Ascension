package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.library.Enums;

public abstract class
BaseMobProjectile extends EntityThrowable {
	private Enums.MobProjectileType projectileType;
	private AoARangedAttacker shooter;

	public BaseMobProjectile(World world) {
		super(world);
	}

	public BaseMobProjectile(World world, AoARangedAttacker shooter, double posX, double posY, double posZ, Enums.MobProjectileType projectileType) {
		super(world);

		setPosition(posX, posY, posZ);

		motionX = rand.nextGaussian() / 33 + 0.03;
		motionY = -2;
		motionZ = rand.nextGaussian() / 33 + 0.03;
		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public BaseMobProjectile(World world, AoARangedAttacker shooter, Entity target, Enums.MobProjectileType projectileType) {
		this(world, shooter, target.posX, target.posY + 25, target.posZ, projectileType);
	}

	public BaseMobProjectile(World world, AoARangedAttacker shooter, Enums.MobProjectileType projectileType) {
		super(world);

		if (shooter instanceof EntityLivingBase) {
			this.thrower = (EntityLivingBase)shooter;
			setPosition(thrower.posX, thrower.posY + thrower.getEyeHeight() - 0.10000000149011612D, thrower.posZ);
		}

		this.projectileType = projectileType;
		this.shooter = shooter;
	}

	public Enums.MobProjectileType getProjectileType() {
		return projectileType;
	}

	@Override
	public float getGravityVelocity() {
		return 0.0f;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (ticksExisted > 500)
			setDead();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.typeOfHit != RayTraceResult.Type.BLOCK || world.getBlockState(result.getBlockPos()).getMaterial().blocksMovement()) {
			if (!world.isRemote) {
				if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
					if (result.entityHit == shooter || shooter == null)
						return;

					shooter.doProjectileEntityImpact(this, result.entityHit);
				}
				else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
					if (shooter != null)
						shooter.doProjectileBlockImpact(this, world.getBlockState(result.getBlockPos()), result.getBlockPos(), result.sideHit);
				}

				setDead();
			}
		}
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
}
