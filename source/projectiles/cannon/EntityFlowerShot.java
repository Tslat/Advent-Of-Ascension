package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.GreenTrail;
import net.nevermine.fx.trail.YellowTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityFlowerShot extends EntityThrowable implements HardProjectile {
	private float damage;
	float f4;
	private float gravity;

	public EntityFlowerShot(final World par1World) {
		super(par1World);
	}

	public EntityFlowerShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final float grav) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		gravity = grav;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityFlowerShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return gravity;
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
				if (rand.nextInt(2) == 1) {
					final GreenTrail var4 = new GreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				}
				else {
					final YellowTrail var5 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				}
			}
		}
	}
}
