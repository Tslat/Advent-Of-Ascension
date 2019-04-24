package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.BlueTrail;
import net.nevermine.fx.trail.PurpleTrail;
import net.nevermine.fx.trail.YellowTrail;

public class EntityAncientDischarger extends EntityThrowable {
	private int age;
	private int effect;
	private static float explosionRadius = 3.0f;
	private EntityLivingBase shooter;

	public EntityAncientDischarger(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityAncientDischarger(final World par1World, final EntityPlayer par3EntityPlayer, final int ability) {
		super(par1World, par3EntityPlayer);
		shooter = par3EntityPlayer;
		age = 0;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityAncientDischarger(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		worldObj.createExplosion(shooter, posX, posY, posZ, EntityAncientDischarger.explosionRadius, false);

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
			final PurpleTrail var4 = new PurpleTrail(worldObj, posX, posY - 0.25, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final BlueTrail var5 = new BlueTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			final YellowTrail var6 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
		}
	}
}
