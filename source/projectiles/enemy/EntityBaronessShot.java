package net.nevermine.projectiles.enemy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.boss.baroness.EntityBaroness;
import net.nevermine.fx.trail.thin.ThinRedTrail;

public class EntityBaronessShot extends EntityThrowable {
	private int age = 0;
	private float damage;
	private EntityBaroness b;

	public EntityBaronessShot(World par1World) {
		super(par1World);

	}

	public EntityBaronessShot(World par1World, EntityBaroness par3, float dmg) {
		super(par1World, par3);
		damage = dmg;
		b = par3;
		setThrowableHeading(motionX, motionY, motionZ, 3.0F, 1.0F);
	}

	public EntityBaronessShot(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.075F;
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {

		if (movingobjectposition.entityHit != null) {
			if (b != null)
				b.setInv(40);
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);

		}

		if (!worldObj.isRemote) {
			setDead();
		}

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (age == 40) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			age += 1;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 8; ++var3) {

			ThinRedTrail var26 = new ThinRedTrail(worldObj, posX, posY, posZ, 0.0D, 0.0D, 0.0D, 5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var26);

		}
	}

}