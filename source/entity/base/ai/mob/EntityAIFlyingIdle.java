package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFlyingIdle extends EntityAIBase {
	private final EntityFlying taskOwner;
	private double lookX;
	private double lookY;
	private double lookZ;
	private int idleTime;

	public EntityAIFlyingIdle(EntityFlying creature) {
		this.taskOwner = creature;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		return this.taskOwner.getRNG().nextFloat() < 0.02f;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.idleTime >= 0;
	}

	@Override
	public void startExecuting() {
		double randomLookVec = (Math.PI * 2D) * this.taskOwner.getRNG().nextDouble();
		this.lookX = Math.cos(randomLookVec);
		this.lookY = Math.tan(randomLookVec);
		this.lookZ = Math.sin(randomLookVec);
		this.idleTime = 20 + this.taskOwner.getRNG().nextInt(20);
	}

	@Override
	public void updateTask() {
		this.idleTime--;
		this.taskOwner.getLookHelper().setLookPosition(this.taskOwner.posX + this.lookX, this.taskOwner.posY + this.lookY, this.taskOwner.posZ + this.lookZ, this.taskOwner.getHorizontalFaceSpeed(), this.taskOwner.getVerticalFaceSpeed());
	}
}
