package net.nevermine.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityFireBullet extends EntityThrowable implements HardProjectile {
	private float damage;

	public EntityFireBullet(final World par1World) {
		super(par1World);
	}

	public EntityFireBullet(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityFireBullet(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			if (movingobjectposition.entityHit.isBurning()) {
				EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage * 2.0f);
			}
			else {
				EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			}

			if (movingobjectposition.entityHit instanceof EntityLivingBase)
				movingobjectposition.entityHit.setFire(5);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}
}
