package net.nevermine.projectiles.staff;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.OrangeTrail;

public class EntityStaffMecha extends EntityThrowable {
	private float damage;
	float f4;
	private boolean heal;
	private float gravity;
	private int age;

	public EntityStaffMecha(final World par1World) {
		super(par1World);
	}

	public EntityStaffMecha(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg, final boolean knock, final float grav) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		heal = knock;
		gravity = grav;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityStaffMecha(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return gravity;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			if (!heal) {
				EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			}
			else if (movingobjectposition.entityHit instanceof EntityLivingBase) {
				((EntityLivingBase)movingobjectposition.entityHit).heal(damage);

				if (getThrower() instanceof EntityPlayer && !((EntityPlayer)getThrower()).capabilities.isCreativeMode) {
					if (getThrower().getHealth() - 4.0f > 0.0f) {
						getThrower().setHealth(getThrower().getHealth() - 4.0f);
					}
					else {
						getThrower().attackEntityFrom(DamageSource.generic, 4.0f);
					}
				}
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
			final OrangeTrail var4 = new OrangeTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 3);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
