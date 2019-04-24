package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlackTrail;
import net.nevermine.fx.trail.YellowTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntitySmileBlaster extends EntityThrowable implements HardProjectile {
	private float damage;
	float f4;
	private int knockbackStrength;
	private float shot1;
	private int switch1;
	private float speed;

	public EntitySmileBlaster(final World par1World) {
		super(par1World);
		speed = 0.3f;
		shot1 = 0.0f;
		switch1 = 0;
	}

	public EntitySmileBlaster(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock) {
		super(par1World, par3EntityPlayer);
		speed = 0.3f;
		damage = dmg;
		knockbackStrength = knock;
		motionX *= speed;
		motionY *= speed;
		motionZ *= speed;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntitySmileBlaster(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		speed = 0.3f;
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			movingobjectposition.entityHit.addVelocity(motionX * knockbackStrength * 0.6000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.6000000238418579 / f4);
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
				if (switch1 == 1) {
					shot1 += 0.25;
					if (shot1 == 3.0f) {
						switch1 = 0;
					}
				}
				if (switch1 == 0) {
					shot1 -= 0.25;
					if (shot1 == -3.0f) {
						switch1 = 1;
					}
				}
				if (switch1 == 0) {
					final YellowTrail var4 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				}
				if (switch1 == 1) {
					final BlackTrail var5 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				}
			}
		}
	}
}
