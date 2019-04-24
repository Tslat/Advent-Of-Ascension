package net.nevermine.projectiles.energy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;
import net.nevermine.fx.circular.CyanCircular;
import net.nevermine.fx.circular.GreenCircular;

import java.util.List;

public class EntitySeaocronShot extends EntityThrowable {
	private float damage;

	public EntitySeaocronShot(final World par1World) {
		super(par1World);
	}

	public EntitySeaocronShot(final World par1World, final EntityPlayer par3EntityPlayer, final float dmg) {
		super(par1World, par3EntityPlayer);
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 3.0f, 1.0f);
	}

	public EntitySeaocronShot(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.03f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			for (final EntityLiving e : (List<EntityLiving>)worldObj.getEntitiesWithinAABB(EntityLiving.class, movingobjectposition.entityHit.boundingBox.expand(15.0, 15.0, 15.0))) {
				e.addVelocity(Math.signum(movingobjectposition.entityHit.posX - e.posX) * 1.629, 0.0, Math.signum(movingobjectposition.entityHit.posZ - e.posZ) * 1.629);
			}

			EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 4; ++var3) {
				final CyanCircular var4 = new CyanCircular(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final GreenCircular var5 = new GreenCircular(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 8);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
			}
		}
	}
}
