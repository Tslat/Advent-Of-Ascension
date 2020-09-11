package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;

public class EntityAIFlyingMeleeAttack extends EntityAIBase {
	private final EntityFlying taskOwner;
	private final float chargingSpeed;
	private int attackCooldown = 0;
	private Path path;
	private int delayTicks;
	private double targetX;
	private double targetY;
	private double targetZ;
	private boolean retainTarget;

	public EntityAIFlyingMeleeAttack(EntityFlying creature, float speed, boolean longMemory) {
		this.taskOwner = creature;
		this.chargingSpeed = speed;
		this.retainTarget = longMemory;
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase target = this.taskOwner.getAttackTarget();

		if (target == null || !target.isEntityAlive())
			return false;

		this.path = this.taskOwner.getNavigator().getPathToEntityLiving(target);

		if (this.path != null) {
			return true;
		}
		else {
			return getAttackReach(target) >= this.taskOwner.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase target = this.taskOwner.getAttackTarget();

		if (target == null || !target.isEntityAlive())
			return false;

		if (!this.retainTarget)
			return !this.taskOwner.getNavigator().noPath();

		return !(target instanceof EntityPlayer) || !((EntityPlayer)target).isSpectator() && !((EntityPlayer)target).isCreative();
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setPath(this.path, this.chargingSpeed);
		this.delayTicks = 0;
	}

	@Override
	public void resetTask() {
		EntityLivingBase target = this.taskOwner.getAttackTarget();

		if (target instanceof EntityPlayer && (((EntityPlayer)target).isSpectator() || ((EntityPlayer)target).isCreative()))
			this.taskOwner.setAttackTarget(null);

		this.taskOwner.getNavigator().clearPath();
	}

	@Override
	public void updateTask() {
		EntityLivingBase target = this.taskOwner.getAttackTarget();

		if (target == null) {
			resetTask();

			return;
		}

		double attackDistance = this.taskOwner.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);

		this.taskOwner.getLookHelper().setLookPositionWithEntity(target, 30, 30);
		this.delayTicks--;

		if ((this.retainTarget || this.taskOwner.getEntitySenses().canSee(target)) &&
				this.delayTicks <= 0 &&
				(this.targetX == 0 && this.targetY == 0 && this.targetZ == 0 || target.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1 ||
						this.taskOwner.getRNG().nextFloat() < 0.05f)) {
			this.targetX = target.posX;
			this.targetY = target.getEntityBoundingBox().minY;
			this.targetZ = target.posZ;
			this.delayTicks = 4 + this.taskOwner.getRNG().nextInt(7);

			if (attackDistance > 1024) {
				this.delayTicks += 10;
			}
			else if (attackDistance < 256) {
				this.delayTicks += 5;
			}

			if (!this.taskOwner.getNavigator().tryMoveToEntityLiving(target, this.chargingSpeed)) {
				this.delayTicks += 15;
			}
			else {
				this.taskOwner.getMoveHelper().setMoveTo(this.targetX, this.targetY, this.targetZ, this.chargingSpeed);
			}

		}

		if (this.attackCooldown > 0)
			this.attackCooldown--;

		this.checkAndAttack(target, attackDistance);
	}

	private void checkAndAttack(EntityLivingBase target, double attackDistance) {
		double reach = getAttackReach(target);

		if (attackDistance <= reach & this.attackCooldown <= 0) {
			this.attackCooldown = 20;
			this.taskOwner.swingArm(EnumHand.MAIN_HAND);
			this.taskOwner.attackEntityAsMob(target);
		}
	}

	private double getAttackReach(EntityLivingBase target) {
		return this.taskOwner.width * 2D * this.taskOwner.width * 2D + target.width;
	}
}
