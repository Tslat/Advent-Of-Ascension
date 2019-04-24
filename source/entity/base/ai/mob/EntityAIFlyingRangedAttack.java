package net.tslat.aoa3.entity.base.ai.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;

public class EntityAIFlyingRangedAttack extends EntityAIBase {
	private final AoAFlyingRangedMob taskOwner;
	private final int attackCooldownMin;
	private final int attackCooldownMax;
	public int attackCooldownTimer;

	public EntityAIFlyingRangedAttack(AoAFlyingRangedMob creature, int attackCooldownMin, int attackCooldownMax) {
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
	public void updateTask() {
		EntityLivingBase target = this.taskOwner.getAttackTarget();

		if (target.getDistanceSq(this.taskOwner) < 4096 && this.taskOwner.canEntityBeSeen(target)) {
			this.attackCooldownTimer++;

			if (this.attackCooldownTimer >= attackCooldownMin) {
				taskOwner.attackEntityWithRangedAttack(target, 0);
				attackCooldownTimer = 0 - taskOwner.getRNG().nextInt(attackCooldownMax - attackCooldownMin);
			}
		}
		else if (this.attackCooldownTimer > 0) {
			this.attackCooldownTimer--;
		}
	}
}
