package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.OrangeTrail;
import net.nevermine.fx.trail.WhiteTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityRPGShot extends EntityThrowable implements HardProjectile {
	private int effect;
	private float damage;
	private int age;
	private static float explosionRadius = 3.0f;

	public EntityRPGShot(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityRPGShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityRPGShot(final World par1World, final double par2, final double par4, final double par6) {
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

		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityRPGShot.explosionRadius, false);

		if (!worldObj.isRemote) {
			setDead();
		}
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
		for (int var3 = 0; var3 < 8; ++var3) {
			final OrangeTrail var4 = new OrangeTrail(worldObj, posX, posY + 0.3, posZ, 0.0, 0.0, 0.0, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final WhiteTrail var5 = new WhiteTrail(worldObj, posX, posY - 0.3, posZ, 0.0, 0.0, 0.0, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
		}
	}
}
