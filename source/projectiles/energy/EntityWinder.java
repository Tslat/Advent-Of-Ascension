package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.RedTrail;
import net.nevermine.fx.trail.YellowTrail;

public class EntityWinder extends EntityThrowable {
	private int age;
	private float damage;
	float f4;
	private float gravity;
	private int knockbackStrength;

	public EntityWinder(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityWinder(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final int knock, final float grav) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		knockbackStrength = knock;
		gravity = grav;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityWinder(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 15) {
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		else {
			++age;
		}
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			f4 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			movingobjectposition.entityHit.addVelocity(motionX * knockbackStrength * 0.4000000238418579 / f4, 0.1, motionZ * knockbackStrength * 0.4000000238418579 / f4);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (rand.nextBoolean()) {
			for (int var3 = 0; var3 < 3; ++var3) {
				final RedTrail var4 = new RedTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
		else {
			for (int var3 = 0; var3 < 3; ++var3) {
				final YellowTrail var4 = new YellowTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
