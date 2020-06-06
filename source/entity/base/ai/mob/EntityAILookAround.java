package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAILookAround extends EntityAIBase {
	private final EntityLiving taskOwner;

	public EntityAILookAround(EntityLiving creature) {
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
				double vecX = target.posX - this.taskOwner.posX;
				double vecZ = target.posZ - this.taskOwner.posZ;

				this.taskOwner.rotationYaw = -((float)MathHelper.atan2(vecX, vecZ)) * (180F / (float)Math.PI);
				this.taskOwner.renderYawOffset = this.taskOwner.rotationYaw;
			}
		}
	}
}
