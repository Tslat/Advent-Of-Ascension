package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.CyanTrail;
import net.nevermine.mob.placement.HardProjectile;

import java.awt.*;

public class EntityCraexxeusBomb extends EntityThrowable implements HardProjectile {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityCraexxeusBomb(World par1World) {
		super(par1World);
		motionX = (rand.nextGaussian() * 0.05D);
		motionZ = (rand.nextGaussian() * 0.05D);
		motionY = -0.85D;
	}

	public EntityCraexxeusBomb(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = (rand.nextGaussian() * 0.05D);
		motionZ = (rand.nextGaussian() * 0.05D);
		motionY = -0.85D;
	}

	public EntityCraexxeusBomb(World par1World, double par2, double par4, double par6, EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		motionX = (rand.nextGaussian() * 0.05D);
		motionZ = (rand.nextGaussian() * 0.05D);
		motionY = -0.85D;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		worldObj.createExplosion(thrower, posX, posY, posZ, 2.0F, false);
		if (par1MovingObjectPosition.entityHit != null) {
			worldObj.createExplosion(thrower, posX, posY, posZ, 2.0F, false);
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 3.0F);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; var3++) {
				CyanTrail var21 = new CyanTrail(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var21);
			}
		}
	}
}
