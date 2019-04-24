package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.RedTrail;
import net.nevermine.mob.entity.night.EntityAnemia;
import net.nevermine.resource.rage.rageHelper;

public class EntityAnemiaShot extends EntityThrowable {
	private float damage;
	private static float explosionRadius;

	public EntityAnemiaShot(final World par1World) {
		super(par1World);
		damage = 20.0f;
	}

	public EntityAnemiaShot(final World par1World, final EntityAnemia par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		damage = 20.0f;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityAnemiaShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		damage = 20.0f;
	}

	protected float getGravityVelocity() {
		return 0.015f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
			if (movingobjectposition.entityHit instanceof EntityPlayer) {
				rageHelper.getProperties((EntityPlayer)movingobjectposition.entityHit).removeBarValue(20.0f);
			}
		}
		worldObj.createExplosion(this, posX, posY, posZ, EntityAnemiaShot.explosionRadius, false);
		setDead();
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}

	static {
		EntityAnemiaShot.explosionRadius = 2.0f;
	}
}
