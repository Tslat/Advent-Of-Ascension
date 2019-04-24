package net.nevermine.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.projectiles.HardProjectile;

public class EntityPopShot extends EntityThrowable implements HardProjectile {
	private float dmg;
	private int age;
	private int mod;
	private static float explosionRadius;

	public EntityPopShot(final World par1World) {
		super(par1World);
		age = 0;
		mod = 0;
	}

	public EntityPopShot(final World par1World, final EntityPlayer par3EntityPlayer, final float damage) {
		super(par1World, par3EntityPlayer);
		age = 0;
		mod = 0;
		dmg = damage;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityPopShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
		mod = 0;
	}

	protected float getGravityVelocity() {
		return 0.15f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(this, posX, posY, posZ, EntityPopShot.explosionRadius, false);
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), dmg + mod);
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		++age;
		if (age % 5 == 0) {
			++mod;
		}
	}

	static {
		EntityPopShot.explosionRadius = 1.0f;
	}
}
