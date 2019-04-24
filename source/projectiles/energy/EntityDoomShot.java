package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.BlackTrail;
import net.nevermine.fx.trail.BlueTrail;

public class EntityDoomShot extends EntityThrowable {
	private int age;
	private static float explosionRadius;

	public EntityDoomShot(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityDoomShot(final World par1World, final EntityPlayer par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		age = 0;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityDoomShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.03f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityDoomShot.explosionRadius, false);
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 60) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; ++var3) {
			final BlackTrail var4 = new BlackTrail(worldObj, posX, posY - 0.25, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final BlueTrail var5 = new BlueTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
		}
	}

	static {
		EntityDoomShot.explosionRadius = 2.5f;
	}
}
