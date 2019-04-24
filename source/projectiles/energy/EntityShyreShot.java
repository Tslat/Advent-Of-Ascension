package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.CyanTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityShyreShot extends EntityThrowable implements HardProjectile {
	private float damage;
	private int knockbackStrength;
	private int age = 0;

	public EntityShyreShot(World par1World) {
		super(par1World);
	}

	public EntityShyreShot(World par1World, EntityPlayer par3EntityPlayer, float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0F, 1.0F);
	}

	public EntityShyreShot(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.075F;
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (getThrower() != null) {
			getThrower().setPositionAndUpdate(posX, posY, posZ);
		}

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
			age += 1;
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; var3++) {
			CyanTrail var21 = new CyanTrail(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var21);
		}
	}
}
