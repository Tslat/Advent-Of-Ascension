package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.PurpleTrail;

import java.awt.*;

public class EntityNightmareFall extends EntityThrowable {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityNightmareFall(final World par1World) {
		super(par1World);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityNightmareFall(final World par1World, final EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityNightmareFall(final World par1World, final double par2, final double par4, final double par6, final EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			EntityUtil.shootEntity(par1MovingObjectPosition.entityHit, getThrower(), this, 25);

			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
				if (!(par1MovingObjectPosition.entityHit instanceof EntityPlayer && ((EntityPlayer)par1MovingObjectPosition.entityHit).capabilities.isCreativeMode)) {
					((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 4));
				}
			}
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final PurpleTrail var4 = new PurpleTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
