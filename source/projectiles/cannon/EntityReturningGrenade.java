package net.nevermine.projectiles.cannon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.thin.ThinYellowTrail;
import net.nevermine.projectiles.HardProjectile;

public class EntityReturningGrenade extends EntityThrowable implements HardProjectile {
	private int count;
	private int age;
	private float damage;
	private EntityPlayer play;
	private static float explosionRadius = 1.5f;

	public EntityReturningGrenade(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityReturningGrenade(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int number) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		play = par3EntityPlayer;
		count = number;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityReturningGrenade(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.075f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		worldObj.createExplosion(getThrower(), posX, posY, posZ, EntityReturningGrenade.explosionRadius, false);

		if (age < 10 && play != null && !worldObj.isRemote && count < 5) {
			final EntityReturningGrenade var2 = new EntityReturningGrenade(worldObj, play, 5.0f, count + 1);

			play.worldObj.playSoundAtEntity(play, "nevermine:MissileMaker", 1.0f, 1.0f);
			worldObj.spawnEntityInWorld(var2);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		++age;
		if (!worldObj.isRemote && count < 5 && play != null && age == 10) {
			final EntityReturningGrenade var2 = new EntityReturningGrenade(worldObj, play, 5.0f, count + 1);
			play.worldObj.playSoundAtEntity(play, "nevermine:MissileMaker", 1.0f, 1.0f);
			worldObj.spawnEntityInWorld(var2);
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final ThinYellowTrail var4 = new ThinYellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
