package net.nevermine.mob.entity.overworld;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityFakeTnt extends EntityTNTPrimed {
	public EntityFakeTnt(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.03999999910593033D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
			motionY *= -0.5D;
		}

		if (fuse-- <= 0) {
			setDead();

			if (!worldObj.isRemote)
				explode();
		}
		else {
			worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode() {
		worldObj.createExplosion(this, posX, posY, posZ, 4.0f, false);
	}
}
