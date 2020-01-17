package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIFullPanic extends EntityAIBase {
	private final EntityCreature taskOwner;

	private final int timeToPanic;
	private int panicTimer;

	private final double speed;
	private double randomTargetX;
	private double randomTargetY;
	private double randomTargetZ;

	public EntityAIFullPanic(EntityCreature creature, int timeToPanic, double speed) {
		this.taskOwner = creature;
		this.timeToPanic = timeToPanic;
		this.speed = speed;

		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		return taskOwner.getRevengeTarget() != null && getRandomPosition();
	}

	@Override
	public boolean isInterruptible() {
		return false;
	}

	private boolean getRandomPosition() {
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
		panicTimer++;

		if (panicTimer < timeToPanic) {
			if (taskOwner.getNavigator().noPath() && getRandomPosition())
				taskOwner.getNavigator().tryMoveToXYZ(randomTargetX, randomTargetY, randomTargetZ, speed);

			return true;
		}

		return false;
	}
}
