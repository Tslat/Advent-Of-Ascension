package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIFullPanic extends EntityAIBase {
	protected final EntityCreature taskOwner;

	protected final int timeToPanic;
	protected int panicTimer;

	protected final double speed;
	protected double randomTargetX;
	protected double randomTargetY;
	protected double randomTargetZ;

	public EntityAIFullPanic(EntityCreature creature, int timeToPanic, double speed) {
		this.taskOwner = creature;
		this.timeToPanic = timeToPanic;
		this.speed = speed;

		this.setMutexBits(1 | 2);
	}

	@Override
	public boolean shouldExecute() {
		return taskOwner.getRevengeTarget() != null && getRandomPosition();
	}

	@Override
	public boolean isInterruptible() {
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
	public void updateTask() {
		panicTimer++;

		if (taskOwner.getNavigator().noPath() && getRandomPosition())
			taskOwner.getNavigator().tryMoveToXYZ(randomTargetX, randomTargetY, randomTargetZ, speed);
	}
}
