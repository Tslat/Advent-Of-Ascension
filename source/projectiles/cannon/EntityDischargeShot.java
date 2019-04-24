package net.nevermine.projectiles.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.projectiles.HardProjectile;

public class EntityDischargeShot extends EntityThrowable implements HardProjectile {
	private int effect;
	private static float explosionRadius;

	public EntityDischargeShot(final World par1World) {
		super(par1World);
	}

	public EntityDischargeShot(final World par1World, final EntityPlayer par3EntityPlayer, final int ability) {
		super(par1World, par3EntityPlayer);
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityDischargeShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityDischargeShot.explosionRadius, false);
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	static {
		EntityDischargeShot.explosionRadius = 2.5f;
	}
}
