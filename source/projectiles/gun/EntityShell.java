package net.nevermine.projectiles.gun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityShell extends EntityThrowable implements HardProjectile {
	private float damage;
	private int knockbackStrength;
	private int age;
	private float direction;

	public EntityShell(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityShell(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final float dir) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		direction = dir;
		setThrowableHeading(motionX, motionY + direction, motionZ, 3.0f, 1.0f);
	}

	public EntityShell(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 60) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}
}
