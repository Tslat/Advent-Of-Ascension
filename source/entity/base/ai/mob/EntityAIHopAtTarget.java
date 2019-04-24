package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIHopAtTarget extends EntityAIBase {
	private final EntityLiving taskHost;
	private EntityLivingBase hopTarget;
	private final float hopVelocity;

	public EntityAIHopAtTarget(EntityLiving entity, float hopVelocity) {
		this.taskHost = entity;
		this.hopVelocity = hopVelocity;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if ((hopTarget = taskHost.getAttackTarget()) == null)
			return false;

		return taskHost.onGround && taskHost.getRNG().nextInt(5) == 0 && taskHost.getDistanceSq(hopTarget) <= 32;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !taskHost.onGround;
	}

	@Override
	public void startExecuting() {
		double distanceX = hopTarget.posX - taskHost.posX;
		double distanceZ = hopTarget.posZ - taskHost.posZ;
		float hypot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);

		taskHost.motionX += distanceX / hypot * 0.4 * 0.200000011920929 + taskHost.motionX * 0.10000000298023223;
		taskHost.motionZ += distanceZ / hypot * 0.4 * 0.200000011920929 + taskHost.motionZ * 0.10000000298023223;
		taskHost.motionY = hopVelocity;
	}
}
