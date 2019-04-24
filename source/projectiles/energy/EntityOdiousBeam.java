package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.thin.ThinBlackTrail;
import net.nevermine.fx.trail.thin.ThinPinkTrail;

public class EntityOdiousBeam extends EntityThrowable {
	private float damage;
	private int color;
	private int age;
	private EntityPlayer play;

	public EntityOdiousBeam(final World par1World) {
		super(par1World);
	}

	public EntityOdiousBeam(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int clr) {
		super(par1World, par3EntityPlayer);
		color = clr;
		damage = dmg;
		play = par3EntityPlayer;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityOdiousBeam(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (play != null) {
				movingobjectposition.entityHit.addVelocity(Math.signum(play.posX - movingobjectposition.entityHit.posX) * 0.049, Math.signum(play.posY - movingobjectposition.entityHit.posY) * 0.049, Math.signum(play.posZ - movingobjectposition.entityHit.posZ) * 0.049);
			}
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 20) {
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
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final ThinBlackTrail var4 = new ThinBlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final ThinPinkTrail var5 = new ThinPinkTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
