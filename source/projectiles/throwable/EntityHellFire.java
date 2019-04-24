package net.nevermine.projectiles.throwable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.nevermine.assist.EntityUtil;

import java.util.List;

public class EntityHellFire extends EntityThrowable {
	private int effect;
	private float damage;
	EntityPlayer pl;

	public EntityHellFire(final World par1World) {
		super(par1World);
	}

	public EntityHellFire(final World par1World, final EntityPlayer player, final float dmg) {
		super(par1World, player);
		pl = player;
		damage = dmg;
		setThrowableHeading(motionX, motionY, motionZ, 1.0f, 1.0f);
	}

	public EntityHellFire(final World par1World, final double par2, final double par4, final double par6) {
		super(par1World, par2, par4, par6);
	}

	protected float getGravityVelocity() {
		return 0.05f;
	}

	protected void onImpact(final MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.typeOfHit != MovingObjectPosition.MovingObjectType.MISS) {
			if (movingobjectposition.entityHit instanceof EntityLivingBase && !movingobjectposition.entityHit.getUniqueID().equals(getThrower().getUniqueID())) {
				EntityUtil.shootEntity(movingobjectposition.entityHit, getThrower(), this, damage);
			}

			for (final EntityLiving e : (List<EntityLiving>)worldObj.getEntitiesWithinAABB(EntityMob.class, this.boundingBox.expand(7.0, 7.0, 7.0))) {
				worldObj.spawnEntityInWorld(new EntityHellFireProjectile(worldObj, this, e.posX, e.posY, e.posZ));
				e.setFire(10);
			}

			worldObj.playSoundAtEntity(this, "nevermine:HellfireHit", 1.0f, 1.0F);
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}
}
