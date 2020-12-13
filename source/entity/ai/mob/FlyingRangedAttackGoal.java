package net.tslat.aoa3.entity.ai.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;

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
	public boolean shouldExecute() {
		return this.taskOwner.getAttackTarget() != null;
	}

	@Override
	public void startExecuting() {
		this.attackCooldownTimer = 0;
	}

	@Override
	public void tick() {
		LivingEntity target = this.taskOwner.getAttackTarget();

		if (target.getDistanceSq(this.taskOwner) < 4096 && this.taskOwner.canEntityBeSeen(target)) {
			this.attackCooldownTimer++;

			if (this.attackCooldownTimer >= attackCooldownMin) {
				taskOwner.attackEntityWithRangedAttack(target, 0);
				attackCooldownTimer = -taskOwner.getRNG().nextInt(attackCooldownMax - attackCooldownMin);
			}
		}
		else if (this.attackCooldownTimer > 0) {
			this.attackCooldownTimer--;
		}
	}
}
