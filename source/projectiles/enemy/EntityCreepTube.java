package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.boss.creep.EntityCreep;
import net.nevermine.fx.trail.BlackTrail;

public class EntityCreepTube extends EntityThrowable {
	private float damage;
	float f4;
	private int knockbackStrength;
	private int age;

	public EntityCreepTube(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityCreepTube(final World par1World, final EntityCreep par3EntityPlayer, final float dmg, final int knock) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		knockbackStrength = knock;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityCreepTube(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.075f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit instanceof EntityLivingBase) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
			worldObj.createExplosion(this, posX, posY, posZ, 1.2f, false);
		}
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 4 || age == 8 || age == 12 || age == 16 || age == 20 || age == 24 || age == 28) {
			worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
		}
		++age;
		if (age == 30 && !worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 14; ++var3) {
			final BlackTrail var4 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
