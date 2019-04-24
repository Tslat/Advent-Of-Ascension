package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.GreenTrail;
import net.nevermine.minion.entity.EntityRosid;

public class EntityRosidianShot extends EntityThrowable {
	private float damage;
	float f4;
	private int knockbackStrength;
	private float gravity;
	private int age;

	public EntityRosidianShot(final World par1World) {
		super(par1World);
	}

	public EntityRosidianShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock, final float grav) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		knockbackStrength = knock;
		gravity = grav;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityRosidianShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return gravity;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (!worldObj.isRemote && rand.nextInt(5) == 0) {
				final EntityRosid var5 = new EntityRosid(worldObj);

				var5.setPosition(posX, posY + 1.0, posZ);
				worldObj.spawnEntityInWorld(var5);
			}
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		++age;
		if (age == 35 && !worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 3; ++var3) {
			final GreenTrail var4 = new GreenTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
