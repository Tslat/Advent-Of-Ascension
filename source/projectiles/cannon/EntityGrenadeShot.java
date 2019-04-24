package net.nevermine.projectiles.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityGrenadeShot extends EntityThrowable implements HardProjectile {
	private int effect;
	private float damage;
	private static float explosionRadius;

	public EntityGrenadeShot(final World par1World) {
		super(par1World);
	}

	public EntityGrenadeShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityGrenadeShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.075f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityGrenadeShot.explosionRadius, false);

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	static {
		EntityGrenadeShot.explosionRadius = 1.5f;
	}
}
