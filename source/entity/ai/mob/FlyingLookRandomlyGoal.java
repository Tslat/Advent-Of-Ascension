package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;

public class FlyingLookRandomlyGoal extends Goal {
	private final MobEntity taskOwner;

	public FlyingLookRandomlyGoal(MobEntity creature) {
		this.taskOwner = creature;

		setMutexFlags(EnumSet.of(Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void tick() {
		if (taskOwner.getAttackTarget() == null) {
			taskOwner.rotationYaw = -((float)MathHelper.atan2(taskOwner.getMotion().getX(), taskOwner.getMotion().getZ())) * (180F / (float)Math.PI);
			taskOwner.renderYawOffset = taskOwner.rotationYaw;
		}
		else {
			LivingEntity target = this.taskOwner.getAttackTarget();

			if (target.getDistanceSq(this.taskOwner) < 4096.0D) {
				double vecX = target.getPosX() - this.taskOwner.getPosX();
				double vecZ = target.getPosZ() - this.taskOwner.getPosZ();

				this.taskOwner.rotationYaw = -((float)MathHelper.atan2(vecX, vecZ)) * (180F / (float)Math.PI);
				this.taskOwner.renderYawOffset = this.taskOwner.rotationYaw;
			}
		}
	}
}
