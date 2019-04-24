package net.nevermine.projectiles.staff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDestructionBall extends EntityThrowable {
	private float damage;
	private int age;

	public EntityDestructionBall(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityDestructionBall(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityDestructionBall(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		worldObj.createExplosion(getThrower(), posX, posY, posZ, 2.6f, false);
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		motionX *= 0.3;
		motionY *= 0.3;
		motionZ *= 0.3;
		if (age == 60) {
			worldObj.createExplosion(getThrower(), posX, posY, posZ, 2.6f, false);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}
}
