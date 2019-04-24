package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.BlackTrail;
import net.nevermine.fx.trail.RedTrail;

public class EntityShowerShot extends EntityThrowable {
	private int distance;
	private int age = 0;
	private static float explosionRadius = 2.5f;

	public EntityShowerShot(final World par1World) {
		super(par1World);
	}

	public EntityShowerShot(final World par1World, final EntityPlayer par3EntityPlayer, final int range) {
		super(par1World, par3EntityPlayer);
		distance = range;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityShowerShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		if (distance == 1) {
			return 0.1f;
		}
		if (distance == 2) {
			return 0.2f;
		}
		return 0.05f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityShowerShot.explosionRadius, false);

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			if (age >= 30) {
				setDead();
			}
			else {
				++age;
			}

			for (int var3 = 0; var3 < 8; ++var3) {
				final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final BlackTrail var5 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
