package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIFullPanic extends EntityAIBase {
	private final EntityCreature taskOwner;
	private final double speed;
	private double randomTargetX;
	private double randomTargetY;
	private double randomTargetZ;

	public EntityAIFullPanic(EntityCreature creature, double speed) {
		this.taskOwner = creature;
		this.speed = speed;
	}

	@Override
	public boolean shouldExecute() {
		return getRandomPosition();
	}

	private boolean getRandomPosition() {
		Vec3d targetPos = RandomPositionGenerator.findRandomTarget(this.taskOwner, 5, 4);

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
	public void startExecuting() {
		this.taskOwner.getNavigator().tryMoveToXYZ(this.randomTargetX, this.randomTargetY, this.randomTargetZ, this.speed);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !this.taskOwner.getNavigator().noPath();
	}
}
