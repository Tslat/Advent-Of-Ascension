package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class CompletePanicGoal extends Goal {
	protected final CreatureEntity taskOwner;

	protected final int timeToPanic;
	protected int panicTimer;

	protected final double speed;
	protected double randomTargetX;
	protected double randomTargetY;
	protected double randomTargetZ;

	public CompletePanicGoal(CreatureEntity creature, int timeToPanic, double speed) {
		this.taskOwner = creature;
		this.timeToPanic = timeToPanic;
		this.speed = speed;

		setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		return taskOwner.getRevengeTarget() != null && getRandomPosition();
	}

	@Override
	public boolean isPreemptible() {
		return false;
	}

	protected boolean getRandomPosition() {
		Vec3d targetPos = RandomPositionGenerator.findRandomTarget(this.taskOwner, 20, 4);

		if (targetPos != null) {
			this.randomTargetX = targetPos.x;
			this.randomTargetY = targetPos.y;
			this.randomTargetZ = targetPos.z;

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void resetTask() {
		panicTimer = 0;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().tryMoveToXYZ(this.randomTargetX, this.randomTargetY, this.randomTargetZ, this.speed);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return panicTimer < timeToPanic;
	}

	@Override
	public void tick() {
		panicTimer++;

		if (taskOwner.getNavigator().noPath() && getRandomPosition())
			taskOwner.getNavigator().tryMoveToXYZ(randomTargetX, randomTargetY, randomTargetZ, speed);
	}
}
