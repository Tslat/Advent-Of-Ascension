package net.tslat.aoa3.object.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Hand;
import net.tslat.aoa3.object.entity.base.AoAFlyingRangedMob;

public class FlyingRangedAttackGoal extends Goal {
	private final AoAFlyingRangedMob taskOwner;
	private final int attackCooldownMin;
	private final int attackCooldownMax;
	public int attackCooldownTimer;

	public FlyingRangedAttackGoal(AoAFlyingRangedMob creature, int attackCooldownMin, int attackCooldownMax) {
		this.taskOwner = creature;
		this.attackCooldownMin = attackCooldownMin;
		this.attackCooldownMax = Math.max(attackCooldownMin + 1, attackCooldownMax);
	}

	@Override
	public boolean canUse() {
		return this.taskOwner.getTarget() != null;
	}

	@Override
	public void start() {
		this.attackCooldownTimer = 0;
	}

	@Override
	public void tick() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target.distanceToSqr(this.taskOwner) < 4096 && this.taskOwner.canSee(target)) {
			this.attackCooldownTimer++;

			if (this.attackCooldownTimer == attackCooldownMin - taskOwner.getPreAttackTime())
				this.taskOwner.swing(Hand.MAIN_HAND);

			if (this.attackCooldownTimer >= attackCooldownMin) {
				taskOwner.performRangedAttack(target, 0);
				attackCooldownTimer = -taskOwner.getRandom().nextInt(attackCooldownMax - attackCooldownMin);
			}
		}
		else if (this.attackCooldownTimer > 0) {
			this.attackCooldownTimer--;
		}
	}
}
