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
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.OrangeTrail;
import net.nevermine.fx.trail.RedTrail;
import net.nevermine.fx.trail.YellowTrail;

import java.awt.*;

public class EntityMeteorFall extends EntityThrowable {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityMeteorFall(final World par1World) {
		super(par1World);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.6;
	}

	public EntityMeteorFall(final World par1World, final EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.6;
	}

	public EntityMeteorFall(final World par1World, final double par2, final double par4, final double par6, final EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.6;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, 1.0f, false);

		if (par1MovingObjectPosition.entityHit != null) {
			if (par1MovingObjectPosition.entityHit instanceof EntityPlayer) {
				if (((EntityPlayer)par1MovingObjectPosition.entityHit).capabilities.isCreativeMode) {
					if (!(par1MovingObjectPosition.entityHit instanceof EntityPlayer && ((EntityPlayer)par1MovingObjectPosition.entityHit).capabilities.isCreativeMode))
						((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 4));
					par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float)25);
				}
			}
			else {
				EntityUtil.shootEntity(par1MovingObjectPosition.entityHit, getThrower(), this, 25);
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
				final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final YellowTrail var5 = new YellowTrail(worldObj, posX, posY - 0.6, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				final OrangeTrail var6 = new OrangeTrail(worldObj, posX, posY - 0.3, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
			}
		}
	}
}
