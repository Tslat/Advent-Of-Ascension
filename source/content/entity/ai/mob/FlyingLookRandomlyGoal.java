package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FlyingLookRandomlyGoal extends Goal {
	private final Mob taskOwner;

	public FlyingLookRandomlyGoal(Mob creature) {
		this.taskOwner = creature;

		setFlags(EnumSet.of(Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public void tick() {
		if (taskOwner.getTarget() == null) {
			taskOwner.setYRot(-((float)Mth.atan2(taskOwner.getDeltaMovement().x(), taskOwner.getDeltaMovement().z())) * (180F / (float)Math.PI));
			taskOwner.yBodyRot = taskOwner.getYRot();
		}
		else {
			LivingEntity target = this.taskOwner.getTarget();

			if (target.distanceToSqr(this.taskOwner) < 4096.0D) {
				double vecX = target.getX() - this.taskOwner.getX();
				double vecZ = target.getZ() - this.taskOwner.getZ();

				this.taskOwner.setYRot(-((float)Mth.atan2(vecX, vecZ)) * (180F / (float)Math.PI));
				this.taskOwner.yBodyRot = this.taskOwner.getYRot();
			}
		}
	}
}
