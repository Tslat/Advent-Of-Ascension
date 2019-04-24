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
import net.nevermine.fx.trail.GreenTrail;

public class EntityCreepBomb extends EntityThrowable {
	private float damage;
	float f4;

	public EntityCreepBomb(final World par1World) {
		super(par1World);
	}

	public EntityCreepBomb(final World par1World, final EntityCreep par3EntityPlayer, final float dmg, final float velo) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, (double)velo, motionZ, 3.0f, 1.0f);
	}

	public EntityCreepBomb(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
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

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final GreenTrail var4 = new GreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
