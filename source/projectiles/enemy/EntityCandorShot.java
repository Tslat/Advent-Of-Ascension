package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.boss.cottoncandor.EntityCottonCandor;
import net.nevermine.fx.circular.CyanCircular;
import net.nevermine.fx.trail.PinkTrail;

public class EntityCandorShot extends EntityThrowable {
	private float damage;
	private static float explosionRadius;

	public EntityCandorShot(final World par1World) {
		super(par1World);
		damage = 35.0f;
	}

	public EntityCandorShot(final World par1World, final EntityCottonCandor par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		damage = 35.0f;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityCandorShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		damage = 35.0f;
	}

	protected float getGravityVelocity() {
		return 0.015f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		worldObj.createExplosion(this, posX, posY, posZ, EntityCandorShot.explosionRadius, false);
		setDead();
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final CyanCircular var4 = new CyanCircular(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final PinkTrail var5 = new PinkTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 25);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}

	static {
		EntityCandorShot.explosionRadius = 5.0f;
	}
}
