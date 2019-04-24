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
import net.nevermine.fx.trail.CyanTrail;

import java.awt.*;
import java.util.List;

public class EntityMoonFall extends EntityThrowable {
	private Color colour;
	private EntityLivingBase thrower;

	public EntityMoonFall(final World par1World) {
		super(par1World);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
	}

	public EntityMoonFall(final World par1World, final EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
	}

	public EntityMoonFall(final World par1World, final double par2, final double par4, final double par6, final EntityLivingBase p) {
		super(par1World, par2, par4, par6);
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = -0.85;
		thrower = p;
	}

	public EntityLivingBase getThrower() {
		return thrower;
	}

	protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
		worldObj.createExplosion(thrower, posX, posY, posZ, 3.0f, false);

		for (final EntityLivingBase e : (List<EntityLiving>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox.expand(2.0, 2.0, 2.0))) {
			if (thrower != null && e instanceof EntityPlayer && e.getUniqueID() == thrower.getUniqueID())
				continue;

			e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2));
		}

		if (par1MovingObjectPosition.entityHit != null) {
			EntityUtil.shootEntity(par1MovingObjectPosition.entityHit, getThrower(), this, 9);

			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
				if (!(par1MovingObjectPosition.entityHit instanceof EntityPlayer && ((EntityPlayer)par1MovingObjectPosition.entityHit).capabilities.isCreativeMode))
					((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 3));
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
				final CyanTrail var4 = new CyanTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
