package net.nevermine.projectiles.sticky;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.auxillary.BlueSticky;
import net.nevermine.fx.auxillary.PurpleSticky;

public class EntityBlastBomb extends EntityThrowable {
	private float damage;
	float f4;
	private int knockbackStrength;
	private int age;

	public EntityBlastBomb(final World par1World) {
		super(par1World);
	}

	public EntityBlastBomb(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		knockbackStrength = knock;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityBlastBomb(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				worldObj.createExplosion(getThrower(), posX, posY, posZ, 1.5f, false);
			}

			setDead();
		}

		if (movingobjectposition.entityHit == null) {
			motionX = 0.0;
			motionY = 0.0;
			motionZ = 0.0;
			setLocationAndAngles((double)movingobjectposition.blockX, (double)movingobjectposition.blockY, (double)movingobjectposition.blockZ, 0.0f, 360.0f);
		}
	}

	public void onUpdate() {
		super.onUpdate();
		++age;
		if (age == 80) {
			worldObj.createExplosion(getThrower(), posX, posY, posZ, 2.0f, false);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final BlueSticky var4 = new BlueSticky(worldObj, posX, posY - 0.25, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final PurpleSticky var5 = new PurpleSticky(worldObj, posX, posY + 0.25, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
