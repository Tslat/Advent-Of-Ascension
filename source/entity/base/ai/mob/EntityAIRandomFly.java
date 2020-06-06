package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

import java.util.Random;

public class EntityAIRandomFly extends EntityAIBase {
	private final EntityLiving taskOwner;
	private final boolean maintainTarget;

	public EntityAIRandomFly(EntityLiving creature, boolean maintainTarget) {
		this.taskOwner = creature;
		this.maintainTarget = maintainTarget;

		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		EntityMoveHelper moveHelper = this.taskOwner.getMoveHelper();

		if (!moveHelper.isUpdating()) {
			return true;
		}
		else {
			double distanceX = moveHelper.getX() - this.taskOwner.posX;
			double distanceY = moveHelper.getY() - this.taskOwner.posY;
			double distanceZ = moveHelper.getZ() - this.taskOwner.posZ;
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
		double targetX = this.taskOwner.posX + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		double targetY = this.taskOwner.posY + (double)((rand.nextFloat() * 2.0F - 1.5F) * 16.0F);
		double targetZ = this.taskOwner.posZ + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);

		if (!maintainTarget || (taskOwner.getAttackTarget() == null || taskOwner.getAttackTarget().getDistance(targetX, targetY, targetZ) < taskOwner.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue()))
			this.taskOwner.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 1.0d);
	}
}
