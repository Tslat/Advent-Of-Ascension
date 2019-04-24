package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlueTrail;

public class EntityAtomizerShot extends EntityThrowable {
	private float damage;
	float f4;
	private int age = 0;
	private EntityPlayer play;

	public EntityAtomizerShot(final World par1World) {
		super(par1World);
	}

	public EntityAtomizerShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		play = par3EntityPlayer;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityAtomizerShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		if (!worldObj.isRemote && play != null) {
			final EntityAtomizerBounce var2 = new EntityAtomizerBounce(worldObj, play, 7.0f, 1.3f);
			var2.setPosition(posX, posY, posZ);
			var2.setThrowableHeading(0.0, 1.2999999523162842, 0.0, 1.6f, 12.0f);
			worldObj.spawnEntityInWorld(var2);
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			if (age >= 30) {
				setDead();
			}
			else {
				++age;
			}

			for (int var3 = 0; var3 < 11; ++var3) {
				final BlueTrail var4 = new BlueTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
