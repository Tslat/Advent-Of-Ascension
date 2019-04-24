package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlackTrail;
import net.nevermine.fx.trail.PurpleTrail;

public class EntityShadowlordShot extends EntityFireball {
	private int switch1;
	private int shot1;

	public EntityShadowlordShot(final World par1World) {
		super(par1World);
		switch1 = 1;
		shot1 = 0;
		setSize(0.3125f, 0.3125f);
	}

	public EntityShadowlordShot(final World par1World, final EntityLivingBase par2EntityLivingBase, final double par3, final double par5, final double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
		switch1 = 1;
		shot1 = 0;
		setSize(0.3125f, 0.3125f);
	}

	protected float getMotionFactor() {
		return isInvulnerable() ? 0.73f : super.getMotionFactor();
	}

	public EntityShadowlordShot(final World par1World, final double par2, final double par4, final double par6, final double par8, final double par10, final double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		switch1 = 1;
		shot1 = 0;
		setSize(0.3125f, 0.3125f);
	}

	public boolean isBurning() {
		return false;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), 20.0f);

			final int healthPercent = EntityUtil.getPercentageOfMaxHealth((EntityLivingBase)movingobjectposition.entityHit);

			if (healthPercent > 80) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 7));
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 2));
			}
			else if (healthPercent > 55) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 5));
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 4));
			}
			else if (healthPercent > 35) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 3));
			}
			else {
				if (healthPercent > 25) {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 6));
				}
				else {
					((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 8));
				}

				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
			}

			((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
		}

		worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);

		if (!worldObj.isRemote) {
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

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				if (switch1 == 1) {
					shot1 += (int)1.0;
					if (shot1 == 3) {
						switch1 = 0;
					}
				}
				if (switch1 == 0) {
					shot1 -= (int)1.0;
					if (shot1 == -3) {
						switch1 = 1;
					}
				}
				if (switch1 == 0) {
					final PurpleTrail var4 = new PurpleTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				}
				if (switch1 == 1) {
					final BlackTrail var5 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				}
			}
		}
	}
}
