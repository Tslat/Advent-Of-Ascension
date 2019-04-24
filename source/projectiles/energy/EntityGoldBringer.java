package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.YellowTrail;

public class EntityGoldBringer extends EntityThrowable {
	private int age;
	private static float explosionRadius;

	public EntityGoldBringer(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityGoldBringer(final World par1World, final EntityPlayer par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		age = 0;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityGoldBringer(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.03f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityGoldBringer.explosionRadius, false);
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
			final YellowTrail var4 = new YellowTrail(worldObj, posX, posY - 0.25, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final YellowTrail var5 = new YellowTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
		}
	}

	static {
		EntityGoldBringer.explosionRadius = 2.5f;
	}
}
