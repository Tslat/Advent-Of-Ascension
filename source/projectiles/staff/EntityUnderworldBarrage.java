package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.RedTrail;

public class EntityUnderworldBarrage extends EntityThrowable {
	private float damage;
	float f4;
	private EntityPlayer play;
	private int numOfBarrage;

	public EntityUnderworldBarrage(final World par1World) {
		super(par1World);
	}

	public EntityUnderworldBarrage(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int barnum) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		play = par3EntityPlayer;
		numOfBarrage = barnum;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityUnderworldBarrage(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				movingobjectposition.entityHit.setFire(5);
			}
		}

		if (!worldObj.isRemote) {
			if (play != null && numOfBarrage < 6) {
				final EntityUnderworldBarrage var2 = new EntityUnderworldBarrage(worldObj, play, 7.0f, numOfBarrage + 1);
				play.worldObj.playSoundAtEntity(play, "nevermine:BasicStaff", 1.0f, 1.0f);
				worldObj.spawnEntityInWorld(var2);
			}

			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 11; ++var3) {
				final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
