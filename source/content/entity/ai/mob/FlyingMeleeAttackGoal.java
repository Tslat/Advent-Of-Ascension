package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;

import java.util.EnumSet;

public class FlyingMeleeAttackGoal extends Goal {
	private final AoAFlyingMeleeMob taskOwner;
	private final float speedModifier;
	private final boolean retainTarget;

	private int attackCooldown = 0;
	private Path path;
	private int delayTicks;
	private double targetX;
	private double targetY;
	private double targetZ;

	public FlyingMeleeAttackGoal(AoAFlyingMeleeMob creature, float speed, boolean longMemory) {
		this.taskOwner = creature;
		this.speedModifier = speed;
		this.retainTarget = longMemory;

		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target == null || !target.isAlive())
			return false;

		this.path = this.taskOwner.getNavigation().createPath(target, 0);

		if (this.path != null) {
			return true;
		}
		else {
			return getAttackReach(target) >= this.taskOwner.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ());
		}
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target == null || !target.isAlive())
			return false;

		if (!this.retainTarget)
			return !this.taskOwner.getNavigation().isDone();

		return !(target instanceof Player) || !target.isSpectator() && !((Player)target).isCreative();
	}

	@Override
	public void start() {
		this.taskOwner.getNavigation().moveTo(this.path, this.speedModifier);
		this.delayTicks = 0;
	}

	@Override
	public void stop() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target instanceof Player && (target.isSpectator() || ((Player)target).isCreative()))
			this.taskOwner.setTarget(null);

		this.taskOwner.getNavigation().stop();
	}

	@Override
	public void tick() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target == null) {
			stop();

			return;
		}

		double attackDistance = this.taskOwner.distanceToSqr(target.getX(), target.getBoundingBox().minY, target.getZ());

		this.taskOwner.getLookControl().setLookAt(target, 30, 30);
		this.delayTicks--;

		if ((this.retainTarget || this.taskOwner.getSensing().hasLineOfSight(target)) &&
				this.delayTicks <= 0 &&
				(this.targetX == 0 && this.targetY == 0 && this.targetZ == 0 || target.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1 ||
						this.taskOwner.getRandom().nextFloat() < 0.05f)) {
			this.targetX = target.getX();
			this.targetY = target.getBoundingBox().minY;
			this.targetZ = target.getZ();
			this.delayTicks = 4 + this.taskOwner.getRandom().nextInt(7);

			if (attackDistance > 1024) {
				this.delayTicks += 10;
			}
			else if (attackDistance < 256) {
				this.delayTicks += 5;
			}

			if (!this.taskOwner.getNavigation().moveTo(target, this.speedModifier)) {
				this.delayTicks += 15;
			}
			else {
				this.taskOwner.getMoveControl().setWantedPosition(this.targetX, this.targetY, this.targetZ, this.speedModifier);
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
			this.taskOwner.swing(InteractionHand.MAIN_HAND);
			this.taskOwner.doHurtTarget(target);
		}
	}

	private double getAttackReach(LivingEntity target) {
		return this.taskOwner.getBbWidth() * 2D * this.taskOwner.getBbWidth() * 2D + target.getBbWidth();
	}
}
