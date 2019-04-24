package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.PinkTrail;

public class EntityMoonDestroyer extends EntityThrowable {
	private int age;
	private static float explosionRadius;
	private EntityLivingBase shooter;

	public EntityMoonDestroyer(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityMoonDestroyer(final World par1World, final EntityPlayer par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		shooter = par3EntityPlayer;
		age = 0;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityMoonDestroyer(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.15f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(shooter, posX, posY, posZ, EntityMoonDestroyer.explosionRadius, false);
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 40) {
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
			final PinkTrail var4 = new PinkTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}

	static {
		EntityMoonDestroyer.explosionRadius = 4.5f;
	}
}
