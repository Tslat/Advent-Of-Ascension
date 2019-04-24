package net.nevermine.projectiles.enemy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNethengeicWitherShot extends EntityFireball {
	private int extra;

	public EntityNethengeicWitherShot(final World par1World) {
		super(par1World);
		setSize(0.3125f, 0.3125f);
	}

	public EntityNethengeicWitherShot(final World par1World, final EntityLivingBase par2EntityLivingBase, final double par3, final double par5, final double par7, final int form) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
		setSize(0.3125f, 0.3125f);
		extra = form;
	}

	protected float getMotionFactor() {
		return isInvulnerable() ? 0.73f : super.getMotionFactor();
	}

	public EntityNethengeicWitherShot(final World par1World, final double par2, final double par4, final double par6, final double par8, final double par10, final double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		setSize(0.3125f, 0.3125f);
	}

	public boolean isBurning() {
		return false;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		if (!worldObj.isRemote) {
			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase && shootingEntity != null) {
				EntityLivingBase target = ((EntityLivingBase)par1MovingObjectPosition.entityHit);

				target.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), 20.0f);
				target.addPotionEffect(new PotionEffect(Potion.wither.id, 90, 1));

				if (extra == 3) {
					par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), 10.0f);
					target.addPotionEffect(new PotionEffect(Potion.wither.id, 70, 2));
				}
			}
			worldObj.newExplosion(this, posX, posY, posZ, 2.0f, false, false);
			setDead();
		}
	}

	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		return false;
	}

	protected void entityInit() {
		dataWatcher.addObject(10, (byte)0);
	}

	public boolean isInvulnerable() {
		return dataWatcher.getWatchableObjectByte(10) == 1;
	}

	public void setInvulnerable(final boolean par1) {
		dataWatcher.updateObject(10, (byte)(par1 ? 1 : 0));
	}
}
