package net.nevermine.projectiles.throwable;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.RedTrail;

public class EntityHellFireProjectile extends EntityThrowable {
	private int age = 0;

	public EntityHellFireProjectile(final World par1World) {
		super(par1World);
	}

	public EntityHellFireProjectile(final World world, EntityThrowable thrown, final double x, final double y, final double z) {
		super(world, thrown.posX, thrown.posY, thrown.posZ);
		motionX = x - thrown.posX;
		motionZ = z - thrown.posZ;
		motionY = y - thrown.posY;
		setThrowableHeading(motionX, motionY + 0.5, motionZ, 1.5f, 1.0f);
	}

	@Override
	protected float getGravityVelocity() {
		if (age >= 5)
			return 1.0f;

		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (!worldObj.isRemote)
			setDead();
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 5) {
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
		for (int var3 = 0; var3 < 5; ++var3) {
			final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
