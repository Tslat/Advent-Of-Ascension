package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.trail.BlackTrail;

public class EntityWitherPierce extends EntityThrowable {
	private float damage;
	private int age;
	private int numPen;

	public EntityWitherPierce(final World par1World) {
		super(par1World);
		age = 0;
	}

	public EntityWitherPierce(final World par1World, final EntityLivingBase par3EntityPlayer, final float dmg, final int numThru) {
		super(par1World, par3EntityPlayer);
		age = 0;
		damage = dmg;
		numPen = numThru;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntityWitherPierce(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
		age = 0;
	}

	protected float getGravityVelocity() {
		return 0.0f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);

			if (movingobjectposition.entityHit instanceof EntityLiving) {
				((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
			}
		}

		if (numPen == 0 && !worldObj.isRemote) {
			setDead();
		}
		--numPen;
	}

	public void onUpdate() {
		super.onUpdate();
		if (age == 20) {
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
			final BlackTrail var4 = new BlackTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
		}
	}
}
