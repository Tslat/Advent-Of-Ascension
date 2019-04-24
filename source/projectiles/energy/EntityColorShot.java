package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.lasting.*;
import net.nevermine.projectiles.HardProjectile;

public class EntityColorShot extends EntityThrowable implements HardProjectile {
	private float damage;

	public EntityColorShot(final World par1World) {
		super(par1World);
	}

	public EntityColorShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityColorShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
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
				final LastingPurpleTrail var4 = new LastingPurpleTrail(worldObj, posX, posY - 1.5, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final LastingGreenTrail var5 = new LastingGreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				final LastingRedTrail var6 = new LastingRedTrail(worldObj, posX, posY + 1.5, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
				final LastingYellowTrail var7 = new LastingYellowTrail(worldObj, posX, posY + 0.5, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var7);
				final LastingCyanTrail var8 = new LastingCyanTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var8);
				final LastingOrangeTrail var9 = new LastingOrangeTrail(worldObj, posX, posY + 1.0, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var9);
				final LastingBlueTrail var10 = new LastingBlueTrail(worldObj, posX, posY - 1.0, posZ, 0.0, 0.0, 0.0, 16);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var10);
			}
		}
	}
}
