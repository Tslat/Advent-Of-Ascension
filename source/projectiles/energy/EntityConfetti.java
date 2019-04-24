package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.RainbowGlitter;

public class EntityConfetti extends EntityThrowable {
	private boolean cluster;
	private int age;

	public EntityConfetti(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityConfetti(final World par1World, final EntityPlayer player, final boolean clustr) {
		super(par1World, player);
		age = 0;
		cluster = clustr;
		posY -= 1;
		if (cluster) {
			motionX = rand.nextGaussian() * 0.1;
			motionZ = rand.nextGaussian() * 0.1;
		}
		else {
			motionX = rand.nextGaussian() * 0.05;
			motionZ = rand.nextGaussian() * 0.05;
		}
		motionY = 0;
	}

	public EntityConfetti(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
		posY -= 1;
		motionX = rand.nextGaussian() * 0.05;
		motionZ = rand.nextGaussian() * 0.05;
		motionY = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 10) {
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
		if (!cluster) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final RainbowGlitter var4 = new RainbowGlitter(worldObj, posX, posY, posZ, 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian());
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
		else {
			for (int var3 = 0; var3 < 8; ++var3) {
				final RainbowGlitter var4 = new RainbowGlitter(worldObj, posX, posY, posZ, 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian());
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
			for (int var3 = 0; var3 < 8; ++var3) {
				final RainbowGlitter var4 = new RainbowGlitter(worldObj, posX, posY - 1, posZ, 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian(), 0.25 * rand.nextGaussian());
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
