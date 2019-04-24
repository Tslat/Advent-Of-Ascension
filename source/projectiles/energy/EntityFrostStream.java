package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.thin.ThinCyanTrail;
import net.nevermine.fx.trail.thin.ThinPinkTrail;

public class EntityFrostStream extends EntityThrowable {
	private float damage;
	private int color;

	public EntityFrostStream(final World par1World) {
		super(par1World);
	}

	public EntityFrostStream(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int clr) {
		super(par1World, par3EntityPlayer);
		color = clr;
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityFrostStream(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 1));
			}
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	protected float getGravityVelocity() {
		return 0.6f;
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 12; ++var3) {
				final ThinCyanTrail var4 = new ThinCyanTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final ThinPinkTrail var5 = new ThinPinkTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
