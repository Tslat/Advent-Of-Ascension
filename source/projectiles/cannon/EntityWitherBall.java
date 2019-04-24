package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlackTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityWitherBall extends EntityThrowable implements HardProjectile {
	private float damage;
	float f4;
	private int knockbackStrength;

	public EntityWitherBall(final World par1World) {
		super(par1World);
	}

	public EntityWitherBall(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		knockbackStrength = knock;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityWitherBall(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(20, 100, 2));
			}

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
				final BlackTrail var4 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
