package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIFlyingLookAround extends EntityAIBase {
	private final EntityFlying taskOwner;

	public EntityAIFlyingLookAround(EntityFlying creature) {
		this.taskOwner = creature;
		this.setMutexBits(2);
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public void updateTask() {
		if (this.taskOwner.getAttackTarget() == null) {
			this.taskOwner.rotationYaw = -((float)MathHelper.atan2(this.taskOwner.motionX, this.taskOwner.motionZ)) * (180F / (float)Math.PI);
			this.taskOwner.renderYawOffset = this.taskOwner.rotationYaw;
		}
		else {
			EntityLivingBase target = this.taskOwner.getAttackTarget();

			if (target.getDistanceSq(this.taskOwner) < 4096.0D) {
				double d1 = target.posX - this.taskOwner.posX;
				double d2 = target.posZ - this.taskOwner.posZ;

				this.taskOwner.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
				this.taskOwner.renderYawOffset = this.taskOwner.rotationYaw;
			}
		}
	}
}
