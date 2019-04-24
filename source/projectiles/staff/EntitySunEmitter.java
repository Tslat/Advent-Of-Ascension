package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.fx.auxillary.OrangeSun;
import net.nevermine.fx.auxillary.YellowSun;

public class EntitySunEmitter extends EntityThrowable {
	private float damage;
	private int age;
	private EntityPlayer play;

	public EntitySunEmitter(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntitySunEmitter(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		play = par3EntityPlayer;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntitySunEmitter(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (!worldObj.isRemote) {
			setDead();
		}
	}

	public void onUpdate() {
		super.onUpdate();
		motionX *= 0.2;
		motionY *= 0.2;
		motionZ *= 0.2;
		if ((age == 15 || age == 30 || age == 45 || age == 60 || age == 75 || age == 90 || age == 105 || age == 120 || age == 135 || age == 140 || age == 145 || age == 150 || age == 155 || age == 159 || age == 23 || age == 37 || age == 53 || age == 67 || age == 83 || age == 97 || age == 113 || age == 127 || age == 143 || age == 147 || age == 153 || age == 158) && play != null && !worldObj.isRemote) {
			final EntitySunShot var2 = new EntitySunShot(worldObj, play, damage);
			var2.setPosition(posX, posY, posZ);
			var2.setThrowableHeading((double)(rand.nextInt(1000000) - rand.nextInt(2000000)), (double)(10 / (rand.nextInt(9) + 6)), (double)(rand.nextInt(1000000) - rand.nextInt(2000000)), 1.6f, 12.0f);
			if (rand.nextInt(2) == 1) {
				final EntitySunShot entitySunShot = var2;
				entitySunShot.motionX *= -1.0;
			}
			if (rand.nextInt(2) == 1) {
				final EntitySunShot entitySunShot2 = var2;
				entitySunShot2.motionZ *= -1.0;
			}
			worldObj.spawnEntityInWorld(var2);
			worldObj.playSoundAtEntity(this, "nevermine:SunShoot", 1.0f, 1.0f);
		}
		if (age == 160 && !worldObj.isRemote) {
			setDead();
		}
		++age;
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		for (int var3 = 0; var3 < 1; ++var3) {
			final YellowSun var4 = new YellowSun(worldObj, posX, posY, posZ, 5.0, 5.0, 5.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			final OrangeSun var5 = new OrangeSun(worldObj, posX, posY, posZ, 5.0, 5.0, 5.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
		}
	}
}
