package net.nevermine.projectiles.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityDischargeDamage extends EntityThrowable implements HardProjectile {
	private float dmg;
	private static float explosionRadius;

	public EntityDischargeDamage(final World par1World) {
		super(par1World);
	}

	public EntityDischargeDamage(final World par1World, final EntityPlayer par3EntityPlayer, final float damage) {
		super(par1World, par3EntityPlayer);
		dmg = damage;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityDischargeDamage(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityDischargeDamage.explosionRadius, false);

		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, dmg);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	static {
		EntityDischargeDamage.explosionRadius = 2.5f;
	}
}
