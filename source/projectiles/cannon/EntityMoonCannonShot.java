package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.PinkTrail;
import net.nevermine.fx.trail.WhiteTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityMoonCannonShot extends EntityThrowable implements HardProjectile {
	private float damage;
	private float shot1;
	private float shot2;
	private int switch1;
	private int switch2;
	private int age;

	public EntityMoonCannonShot(final World par1World) {
		super(par1World);
		age = 0;
		shot1 = 0.0f;
		shot2 = 0.0f;
		switch1 = 0;
		switch2 = 1;
	}

	public EntityMoonCannonShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityMoonCannonShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	private void onTickStart() {
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 40) {
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
			if (switch1 == 1) {
				shot1 += 0.25;
				if (shot1 == 3.0f) {
					switch1 = 0;
				}
			}
			if (switch1 == 0) {
				shot1 -= 0.25;
				if (shot1 == -3.0f) {
					switch1 = 1;
				}
			}
			if (switch2 == 1) {
				shot2 += 0.25;
				if (shot2 == 3.0f) {
					switch2 = 0;
				}
			}
			if (switch2 == 0) {
				shot2 -= 0.25;
				if (shot2 == -3.0f) {
					switch2 = 1;
				}
			}
			final PinkTrail var4 = new PinkTrail(worldObj, posX, posY + shot2, posZ, 0.0, 0.0, 0.0, 25);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final WhiteTrail var5 = new WhiteTrail(worldObj, posX, posY + shot1, posZ, 0.0, 0.0, 0.0, 25);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
		}
	}
}
