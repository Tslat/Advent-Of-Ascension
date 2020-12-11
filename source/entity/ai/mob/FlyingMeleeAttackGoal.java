package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.Hand;

public class FlyingMeleeAttackGoal extends Goal {
	private final FlyingEntity taskOwner;
	private final float chargingSpeed;
	private int attackCooldown = 0;
	private Path path;
	private int delayTicks;
	private double targetX;
	private double targetY;
	private double targetZ;
	private boolean retainTarget;

	public FlyingMeleeAttackGoal(FlyingEntity creature, float speed, boolean longMemory) {
		this.taskOwner = creature;
		this.chargingSpeed = speed;
		this.retainTarget = longMemory;
	}

	@Override
	public boolean shouldExecute() {
		LivingEntity target = this.taskOwner.getAttackTarget();

		if (target == null || !target.isAlive())
			return false;

		this.path = this.taskOwner.getNavigator().getPathToEntity(target, 0);

		if (this.path != null) {
			return true;
		}
		else {
			return getAttackReach(target) >= this.taskOwner.getDistanceSq(target.getPosX(), target.getBoundingBox().minY, target.getPosZ());
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		LivingEntity target = this.taskOwner.getAttackTarget();

		if (target == null || !target.isAlive())
			return false;

		if (!this.retainTarget)
			return !this.taskOwner.getNavigator().noPath();

		return !(target instanceof PlayerEntity) || !target.isSpectator() && !((PlayerEntity)target).isCreative();
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setPath(this.path, this.chargingSpeed);
		this.delayTicks = 0;
	}

	@Override
	public void resetTask() {
		LivingEntity target = this.taskOwner.getAttackTarget();

		if (target instanceof PlayerEntity && (target.isSpectator() || ((PlayerEntity)target).isCreative()))
			this.taskOwner.setAttackTarget(null);

		this.taskOwner.getNavigator().clearPath();
	}

	@Override
	public void tick() {
		LivingEntity target = this.taskOwner.getAttackTarget();

		if (target == null) {
			resetTask();

			return;
		}

		double attackDistance = this.taskOwner.getDistanceSq(target.getPosX(), target.getBoundingBox().minY, target.getPosZ());

		this.taskOwner.getLookController().setLookPositionWithEntity(target, 30, 30);
		this.delayTicks--;

		if ((this.retainTarget || this.taskOwner.getEntitySenses().canSee(target)) &&
				this.delayTicks <= 0 &&
				(this.targetX == 0 && this.targetY == 0 && this.targetZ == 0 || target.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1 ||
						this.taskOwner.getRNG().nextFloat() < 0.05f)) {
			this.targetX = target.getPosX();
			this.targetY = target.getBoundingBox().minY;
			this.targetZ = target.getPosZ();
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

	private void checkAndAttack(LivingEntity target, double attackDistance) {
		double reach = getAttackReach(target);

		if (attackDistance <= reach & this.attackCooldown <= 0) {
			this.attackCooldown = 20;
			this.taskOwner.swingArm(Hand.MAIN_HAND);
			this.taskOwner.attackEntityAsMob(target);
		}
	}

	private double getAttackReach(LivingEntity target) {
		return this.taskOwner.getWidth() * 2D * this.taskOwner.getWidth() * 2D + target.getWidth();
	}
}
