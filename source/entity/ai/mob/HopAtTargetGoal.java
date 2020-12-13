package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class HopAtTargetGoal extends Goal {
	private final MobEntity taskHost;
	private LivingEntity hopTarget;
	private final float hopVelocity;

	public HopAtTargetGoal(MobEntity entity, float hopVelocity) {
		this.taskHost = entity;
		this.hopVelocity = hopVelocity;

		setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
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
		double distanceX = hopTarget.getPosX() - taskHost.getPosX();
		double distanceZ = hopTarget.getPosZ() - taskHost.getPosZ();
		float hypot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);

		Vec3d motion = taskHost.getMotion();
		double motionX = motion.getX() + distanceX / hypot * 0.4 * 0.200000011920929 + motion.getX() * 0.10000000298023223;
		double motionZ = motion.getZ() + distanceZ / hypot * 0.4 * 0.200000011920929 + motion.getZ() * 0.10000000298023223;

		taskHost.setMotion(new Vec3d(motionX, hopVelocity, motionZ));
	}
}
