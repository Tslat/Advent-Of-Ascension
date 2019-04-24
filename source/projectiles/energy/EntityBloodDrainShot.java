package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.circular.RedCircular;

public class EntityBloodDrainShot extends EntityThrowable {
	private int age;
	private float damage;
	private float healing;
	private int knockbackStrength;

	public EntityBloodDrainShot(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityBloodDrainShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final float heal) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		healing = heal;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityBloodDrainShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (getThrower() != null)
				getThrower().heal(healing);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();

		if (age == 3) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 3; ++var3) {
			final RedCircular var4 = new RedCircular(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
