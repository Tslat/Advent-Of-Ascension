package net.nevermine.projectiles.staff;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;

import java.awt.*;

public class EntityLunarFall extends EntityThrowable {
	private Color colour;
	private int age;
	private EntityLivingBase thrower;

	public EntityLunarFall(final World par1World) {
		super(par1World);
		age = 30;
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityLunarFall(final World par1World, final EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		age = 30;
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityLunarFall(final World par1World, final double par2, final double par4, final double par6, final EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		age = 30;
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null)
			EntityUtil.shootEntity(par1MovingObjectPosition.entityHit, getThrower(), this, 16);

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		--age;
		if (age == 1) {
			age = 30;
			worldObj.playSoundAtEntity(this, "nevermine:LunarFall", 1.0f, 1.0f);
		}
	}
}
