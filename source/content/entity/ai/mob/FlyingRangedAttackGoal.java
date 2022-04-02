package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;

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

		if (target.distanceToSqr(this.taskOwner) < 4096 && this.taskOwner.hasLineOfSight(target)) {
			this.attackCooldownTimer++;

			if (this.attackCooldownTimer == attackCooldownMin - taskOwner.getPreAttackTime())
				this.taskOwner.swing(InteractionHand.MAIN_HAND);

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
