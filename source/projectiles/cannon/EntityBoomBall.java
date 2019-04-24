package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.projectiles.HardProjectile;

public class EntityBoomBall extends EntityThrowable implements HardProjectile {
	private int age;
	private float damage;

	public EntityBoomBall(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityBoomBall(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityBoomBall(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.05f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 4 || age == 8 || age == 12 || age == 16 || age == 20 || age == 24 || age == 28) {
			worldObj.createExplosion(getThrower(), posX, posY, posZ, 2.0f, false);
		}
		++age;

		if (age == 30 && !worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		EntityExplodeFX fx = new EntityExplodeFX(worldObj, posX, posY, posZ, 0, 0, 0);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
	}
}
