package net.nevermine.projectiles.enemy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.mob.entity.haven.EntityVoliant;

public class EntityVoliantShot extends EntityThrowable {
	private float damage;
	private static float explosionRadius;

	public EntityVoliantShot(final World par1World) {
		super(par1World);
		damage = 20.0f;
	}

	public EntityVoliantShot(final World par1World, final EntityVoliant par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		damage = 20.0f;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityVoliantShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		damage = 20.0f;
	}

	protected float getGravityVelocity() {
		return 0.015f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		worldObj.createExplosion(this, posX, posY, posZ, EntityVoliantShot.explosionRadius, false);
		setDead();
	}

	static {
		EntityVoliantShot.explosionRadius = 4.0f;
	}
}
