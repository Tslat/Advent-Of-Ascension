package net.tslat.aoa3.object.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;

public class LookAroundGoal extends Goal {
	private final MobEntity taskOwner;

	public LookAroundGoal(MobEntity creature) {
		this.taskOwner = creature;

		setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public void tick() {
		if (taskOwner.getTarget() == null) {
			taskOwner.yRot = -((float)MathHelper.atan2(taskOwner.getDeltaMovement().x(), taskOwner.getDeltaMovement().z())) * (180F / (float)Math.PI);
			taskOwner.yBodyRot = taskOwner.yRot;
		}
		else {
			LivingEntity target = this.taskOwner.getTarget();

			if (target.distanceToSqr(this.taskOwner) < 4096.0D) {
				double vecX = target.getX() - this.taskOwner.getX();
				double vecZ = target.getZ() - this.taskOwner.getZ();

				this.taskOwner.yRot = -((float)MathHelper.atan2(vecX, vecZ)) * (180F / (float)Math.PI);
				this.taskOwner.yBodyRot = this.taskOwner.yRot;
			}
		}
	}
}
