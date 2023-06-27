package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.EnumSet;

public class RandomFlyingGoal extends Goal {
	private final Mob taskOwner;
	private final boolean maintainTarget;

	public RandomFlyingGoal(Mob creature, boolean maintainTarget) {
		this.taskOwner = creature;
		this.maintainTarget = maintainTarget;

		setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		MoveControl moveHelper = this.taskOwner.getMoveControl();

		if (!moveHelper.hasWanted()) {
			return true;
		}
		else if (maintainTarget && taskOwner.getTarget() != null) {
			return false;
		}
		else if (taskOwner.onGround()) {
			return true;
		}
		else {
			double distanceX = moveHelper.getWantedX() - this.taskOwner.getX();
			double distanceY = moveHelper.getWantedY() - this.taskOwner.getY();
			double distanceZ = moveHelper.getWantedZ() - this.taskOwner.getZ();
			double distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

			return distanceSquared < 1.0D || distanceSquared > 3600.0D;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !this.taskOwner.getNavigation().isDone() && !this.taskOwner.isVehicle();
	}

	@Override
	public void start() {
		RandomSource rand = this.taskOwner.getRandom();
		float heightMod = (float)(taskOwner.getY() + 1) / (float)(taskOwner.level().getHeight(Heightmap.Types.MOTION_BLOCKING, (int)taskOwner.getX(), (int)taskOwner.getZ()) + 10);
		double targetX = this.taskOwner.getX() + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		double targetY = this.taskOwner.getY() + (double)((rand.nextFloat() * 2.0F - heightMod) * 16.0F);
		double targetZ = this.taskOwner.getZ() + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);

		this.taskOwner.getMoveControl().setWantedPosition(targetX, targetY, targetZ, 1.0d);
	}
}
