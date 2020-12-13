package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.gen.Heightmap;

import java.util.EnumSet;
import java.util.Random;

public class RandomFlyingGoal extends Goal {
	private final MobEntity taskOwner;
	private final boolean maintainTarget;

	public RandomFlyingGoal(MobEntity creature, boolean maintainTarget) {
		this.taskOwner = creature;
		this.maintainTarget = maintainTarget;

		setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		MovementController moveHelper = this.taskOwner.getMoveHelper();

		if (!moveHelper.isUpdating()) {
			return true;
		}
		else if (maintainTarget && taskOwner.getAttackTarget() != null) {
			return false;
		}
		else if (taskOwner.onGround) {
			return true;
		}
		else {
			double distanceX = moveHelper.getX() - this.taskOwner.getPosX();
			double distanceY = moveHelper.getY() - this.taskOwner.getPosY();
			double distanceZ = moveHelper.getZ() - this.taskOwner.getPosZ();
			double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			return distanceSquared < 1.0D || distanceSquared > 3600.0D;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		return false;
	}

	@Override
	public void startExecuting() {
		Random rand = this.taskOwner.getRNG();
		float heightMod = (float)(taskOwner.getPosY() + 1) / (float)(taskOwner.world.getHeight(Heightmap.Type.MOTION_BLOCKING, (int)taskOwner.getPosX(), (int)taskOwner.getPosZ()) + 10);
		double targetX = this.taskOwner.getPosX() + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		double targetY = this.taskOwner.getPosY() + (double)((rand.nextFloat() * 2.0F - heightMod) * 16.0F);
		double targetZ = this.taskOwner.getPosZ() + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);

		this.taskOwner.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 1.0d);
	}
}
