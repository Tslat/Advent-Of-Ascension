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
import net.nevermine.fx.trail.OrangeTrail;
import net.nevermine.projectiles.HardProjectile;

import java.awt.*;

public class EntityMechFall extends EntityThrowable implements HardProjectile {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityMechFall(World par1World) {
		super(par1World);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
	}

	public EntityMechFall(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
	}

	public EntityMechFall(World par1World, double par2, double par4, double par6, EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
		if (par1MovingObjectPosition.entityHit != null) {
			worldObj.createExplosion(this, posX, posY, posZ, 1.0f, false);
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 5.0f);
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		for (int var3 = 0; var3 < 8; ++var3) {
			OrangeTrail var21 = new OrangeTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var21);
		}
	}
}

