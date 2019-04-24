package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.OrangeTrail;

public class EntityFireflyStaff extends EntityThrowable {
	private float damage;
	float f4;
	private int age;

	public EntityFireflyStaff(final World par1World) {
		super(par1World);
	}

	public EntityFireflyStaff(final World par1World, final EntityLivingBase par3EntityPlayer, final float dmg, final float xChange, final float yChange, final float zChange, final int invertX, final int invertY, final int invertZ) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		if (motionX < 0.5 && motionZ < 0.5) {
			if (rand.nextBoolean()) {
				motionX = 1.0f;
			}
			else {
				motionZ = 1.0f;
			}
		}

		setCustomThrowableHeading((motionX + xChange) * invertX, (motionY + yChange) * invertY, (motionZ + zChange) * invertZ, 3.0f, 1.0f);
	}

	public EntityFireflyStaff(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}
	
	private void setCustomThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float accuracy) {
		float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		motionX /= (double)f2;
		motionY /= (double)f2;
		motionZ /= (double)f2;
		motionX += rand.nextGaussian() * 0.007499999832361937D * (double)accuracy;
		motionY += rand.nextGaussian() * 0.007499999832361937D * (double)accuracy;
		motionZ += rand.nextGaussian() * 0.007499999832361937D * (double)accuracy;
		motionX *= (double)velocity;
		motionY *= (double)velocity;
		motionZ *= (double)velocity;
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		this.prevRotationYaw = rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		this.prevRotationPitch = rotationPitch = (float)(Math.atan2(motionY, (double)f3) * 180.0D / Math.PI);
	}

	protected float getGravityVelocity() {
		return 0.05f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				(movingobjectposition.entityHit).setFire(5);
			}
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 14; ++var3) {
			final OrangeTrail var4 = new OrangeTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
