package net.nevermine.projectiles.gun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityNetherSlug extends EntityThrowable implements HardProjectile {
	private float damage;
	float f4;
	private int knockbackStrength;
	private int effect;

	public EntityNetherSlug(final World par1World) {
		super(par1World);
	}

	public EntityNetherSlug(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock, final int ability) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		knockbackStrength = knock;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityNetherSlug(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase)
				movingobjectposition.entityHit.setFire(5);

			f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			movingobjectposition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / f4);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}
}
