package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

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

		setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return taskOwner.getLastHurtByMob() != null && getRandomPosition();
	}

	@Override
	public boolean isInterruptable() {
		return false;
	}

	protected boolean getRandomPosition() {
		Vector3d targetPos = RandomPositionGenerator.getPos(this.taskOwner, 20, 4);

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
	public void stop() {
		panicTimer = 0;
	}

	@Override
	public void start() {
		this.taskOwner.getNavigation().moveTo(this.randomTargetX, this.randomTargetY, this.randomTargetZ, this.speed);
	}

	@Override
	public boolean canContinueToUse() {
		return panicTimer < timeToPanic;
	}

	@Override
	public void tick() {
		panicTimer++;

		if (taskOwner.getNavigation().isDone() && getRandomPosition())
			taskOwner.getNavigation().moveTo(randomTargetX, randomTargetY, randomTargetZ, speed);
	}
}
