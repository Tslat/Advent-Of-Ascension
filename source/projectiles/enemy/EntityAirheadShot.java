package net.nevermine.projectiles.enemy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.mob.entity.candyland.EntityAirhead;

public class EntityAirheadShot extends EntityThrowable {
	private float damage;

	public EntityAirheadShot(final World par1World) {
		super(par1World);
		damage = 40.0f;
	}

	public EntityAirheadShot(final World par1World, final EntityAirhead par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		damage = 40.0f;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityAirheadShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		damage = 40.0f;
	}

	protected float getGravityVelocity() {
		return 0.015f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}

		setDead();
	}
}
