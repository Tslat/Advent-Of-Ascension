package net.nevermine.projectiles.cannon;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.projectiles.HardProjectile;

public class EntityRunicBomb extends EntityThrowable implements HardProjectile {
	private int effect;
	private float damage;
	private static float explosionRadius;

	public EntityRunicBomb(final World par1World) {
		super(par1World);
	}

	public EntityRunicBomb(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityRunicBomb(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(this, posX, posY, posZ, EntityRunicBomb.explosionRadius, false);
		if (movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityMob && getThrower() != null) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	static {
		EntityRunicBomb.explosionRadius = 1.5f;
	}
}
