package net.nevermine.mob.ai;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAIFlying extends EntityMob {
	public EntityAIFlying(final World par1World) {
		super(par1World);
	}

	protected void fall(final float par1) {
	}

	protected void updateFallState(final double par1, final boolean par3) {
	}

	public void moveEntityWithHeading(final float par1, final float par2) {
		if (isInWater()) {
			moveFlying(par1, par2, 0.02f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.800000011920929;
			motionY *= 0.800000011920929;
			motionZ *= 0.800000011920929;
		}
		else if (handleLavaMovement()) {
			moveFlying(par1, par2, 0.02f);
			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.5;
			motionY *= 0.5;
			motionZ *= 0.5;
		}
		else {
			float f2 = 0.91f;
			if (onGround) {
				f2 = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)).slipperiness * 0.91f;
			}
			final float f3 = 0.16277136f / (f2 * f2 * f2);
			moveFlying(par1, par2, onGround ? (0.1f * f3) : 0.02f);
			f2 = 0.91f;
			if (onGround) {
				f2 = worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ)).slipperiness * 0.91f;
			}
			moveEntity(motionX, motionY, motionZ);
			motionX *= f2;
			motionY *= f2;
			motionZ *= f2;
		}
		prevLimbSwingAmount = limbSwingAmount;
		final double d1 = posX - prevPosX;
		final double d2 = posZ - prevPosZ;
		float f4 = MathHelper.sqrt_double(d1 * d1 + d2 * d2) * 4.0f;
		if (f4 > 1.0f) {
			f4 = 1.0f;
		}
		limbSwingAmount += (f4 - limbSwingAmount) * 0.4f;
		limbSwing += limbSwingAmount;
	}

	public boolean isOnLadder() {
		return false;
	}
}
