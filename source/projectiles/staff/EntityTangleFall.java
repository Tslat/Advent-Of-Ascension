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
import net.nevermine.fx.trail.GreenTrail;

import java.awt.*;

public class EntityTangleFall extends EntityThrowable {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityTangleFall(final World par1World) {
		super(par1World);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityTangleFall(final World par1World, final EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityTangleFall(final World par1World, final double par2, final double par4, final double par6, final EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		thrower = p;
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.5;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
				if (!(par1MovingObjectPosition.entityHit instanceof EntityPlayer && ((EntityPlayer)par1MovingObjectPosition.entityHit).capabilities.isCreativeMode)) {
					((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 4));
				}

				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), (float)15);
			}
			else {
				EntityUtil.shootEntity(par1MovingObjectPosition.entityHit, getThrower(), this, 15);
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
				final GreenTrail var4 = new GreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
