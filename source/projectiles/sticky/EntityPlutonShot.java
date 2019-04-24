package net.nevermine.projectiles.sticky;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.YellowTrail;

public class EntityPlutonShot extends EntityThrowable {
	private float damage;
	float f4;
	private int age;
	private EntityPlayer p;

	public EntityPlutonShot(final World par1World) {
		super(par1World);
	}

	public EntityPlutonShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		p = par3EntityPlayer;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityPlutonShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.015f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			if (EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage) && !worldObj.isRemote) {
				final EntityPlutonStickler var2 = new EntityPlutonStickler(worldObj, p, 0.0f, (EntityLivingBase)movingobjectposition.entityHit);

				var2.setPosition(movingobjectposition.entityHit.posX, movingobjectposition.entityHit.posY, movingobjectposition.entityHit.posZ);
				worldObj.spawnEntityInWorld(var2);
			}
		}

		if (!worldObj.isRemote)
			setDead();
	}

	public void onUpdate() {
		super.onUpdate();
		++age;

		if (age == 45 && !worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();

		for (int var3 = 0; var3 < 14; ++var3) {
			final YellowTrail var4 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 65);

			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
