package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.trail.thin.ThinYellowTrail;

public class EntityMechShot extends EntityThrowable {
	private int age = 0;
	private float damage;
	private static float explosionRadius = 1.0f;

	public EntityMechShot(World par1World) {
		super(par1World);
	}

	public EntityMechShot(World par1World, EntityMob par3EntityPlayer, float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityMechShot(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.075f;
	}

	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		worldObj.createExplosion(this, posX, posY, posZ, explosionRadius, false);
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

	@SideOnly(value = Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; ++var3) {
			ThinYellowTrail var26 = new ThinYellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var26);
		}
	}
}

