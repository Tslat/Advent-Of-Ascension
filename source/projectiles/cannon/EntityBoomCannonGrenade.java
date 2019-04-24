package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlueTrail;
import net.nevermine.fx.trail.PurpleTrail;
import net.nevermine.fx.trail.YellowTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityBoomCannonGrenade extends EntityThrowable implements HardProjectile {
	private int age;
	private int effect;
	private float damage;
	private static float explosionRadius;

	public EntityBoomCannonGrenade(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityBoomCannonGrenade(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int ability) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		effect = ability;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityBoomCannonGrenade(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.1f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityBoomCannonGrenade.explosionRadius, false);

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
			final PurpleTrail var4 = new PurpleTrail(worldObj, posX, posY - 0.25, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final BlueTrail var5 = new BlueTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			final YellowTrail var6 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
		}
	}

	static {
		EntityBoomCannonGrenade.explosionRadius = 1.5f;
	}
}
