package net.nevermine.projectiles.gun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityPenetrationBullet extends EntityThrowable implements HardProjectile {
	private float damage;
	private int age;
	private int numPen;
	private Entity lastTarget;

	public EntityPenetrationBullet(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityPenetrationBullet(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int numThru) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		numPen = numThru + 1;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityPenetrationBullet(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null && lastTarget != movingobjectposition.entityHit) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			lastTarget = movingobjectposition.entityHit;
			--numPen;
		}

		if (numPen == 0 && !worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 20) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}
}
