package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class HopAtTargetGoal extends Goal {
	private final Mob taskHost;
	private LivingEntity hopTarget;
	private final float hopVelocity;

	public HopAtTargetGoal(Mob entity, float hopVelocity) {
		this.taskHost = entity;
		this.hopVelocity = hopVelocity;

		setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if ((hopTarget = taskHost.getTarget()) == null)
			return false;

		return taskHost.isOnGround() && taskHost.getRandom().nextInt(5) == 0 && taskHost.distanceToSqr(hopTarget) <= 32;
	}

	@Override
	public boolean canContinueToUse() {
		return !taskHost.isOnGround();
	}

	@Override
	public void start() {
		double distanceX = hopTarget.getX() - taskHost.getX();
		double distanceZ = hopTarget.getZ() - taskHost.getZ();
		double hypot = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);

		Vec3 motion = taskHost.getDeltaMovement();
		double motionX = motion.x() + distanceX / hypot * 0.4 * 0.200000011920929 + motion.x() * 0.10000000298023223;
		double motionZ = motion.z() + distanceZ / hypot * 0.4 * 0.200000011920929 + motion.z() * 0.10000000298023223;

		taskHost.setDeltaMovement(new Vec3(motionX, hopVelocity, motionZ));
	}
}
