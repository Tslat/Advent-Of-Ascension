package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class HopAtTargetGoal extends Goal {
	private final MobEntity taskHost;
	private LivingEntity hopTarget;
	private final float hopVelocity;

	public HopAtTargetGoal(MobEntity entity, float hopVelocity) {
		this.taskHost = entity;
		this.hopVelocity = hopVelocity;

		setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if ((hopTarget = taskHost.getTarget()) == null)
			return false;

		return taskHost.onGround && taskHost.getRandom().nextInt(5) == 0 && taskHost.distanceToSqr(hopTarget) <= 32;
	}

	@Override
	public boolean canContinueToUse() {
		return !taskHost.onGround;
	}

	@Override
	public void start() {
		double distanceX = hopTarget.getX() - taskHost.getX();
		double distanceZ = hopTarget.getZ() - taskHost.getZ();
		float hypot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);

		Vector3d motion = taskHost.getDeltaMovement();
		double motionX = motion.x() + distanceX / hypot * 0.4 * 0.200000011920929 + motion.x() * 0.10000000298023223;
		double motionZ = motion.z() + distanceZ / hypot * 0.4 * 0.200000011920929 + motion.z() * 0.10000000298023223;

		taskHost.setDeltaMovement(new Vector3d(motionX, hopVelocity, motionZ));
	}
}
