package net.nevermine.boss;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAILeap extends EntityAIBase {
	EntityLivingBase leaper;
	EntityLivingBase leapTarget;
	float leapMotionY;

	public EntityAILeap(final EntityLiving par1EntityLiving, final float par2) {
		leaper = par1EntityLiving;
		leapMotionY = par2;
		setMutexBits(3);
	}

	public boolean shouldExecute() {
		leapTarget = leaper.getAITarget();
		if (leapTarget == null) {
			return false;
		}
		final double var1 = leaper.getDistanceSqToEntity(leapTarget);
		return var1 <= 32.0 && leaper.onGround && leaper.getRNG().nextInt(5) == 0;
	}

	public boolean continueExecuting() {
		return !leaper.onGround;
	}

	public void startExecuting() {
		final double var1 = leapTarget.posX - leaper.posX;
		final double var2 = leapTarget.posZ - leaper.posZ;
		final float var3 = MathHelper.sqrt_double(var1 * var1 + var2 * var2);
		final EntityLivingBase leaper = this.leaper;
		leaper.motionX += var1 / var3 * 0.5 * 0.800000011920929 + this.leaper.motionX * 0.20000000298023224;
		final EntityLivingBase leaper2 = this.leaper;
		leaper2.motionZ += var2 / var3 * 0.5 * 0.800000011920929 + this.leaper.motionZ * 0.20000000298023224;
		this.leaper.motionY = leapMotionY;
	}
}
