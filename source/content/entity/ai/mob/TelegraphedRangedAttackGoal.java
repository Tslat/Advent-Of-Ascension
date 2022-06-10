package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import java.util.EnumSet;

public class TelegraphedRangedAttackGoal<T extends Mob & RangedAttackMob> extends Goal {
	private final T taskOwner;
	private int attackCooldownMin = 20;
	private int attackCooldownMax = 40;
	private float speedModifier = 1;
	private float attackRadius = 32;
	private int preAttackTime = 0;

	public int attackCooldownTimer;
	private LivingEntity target = null;
	private int attackTime = 1;
	private int seeTime;


	public TelegraphedRangedAttackGoal(T taskOwner) {
		this.taskOwner = taskOwner;

		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public TelegraphedRangedAttackGoal<T> moveSpeedMod(float mod) {
		this.speedModifier = mod;

		return this;
	}

	public TelegraphedRangedAttackGoal<T> attackFrequency(int min, int max) {
		this.attackCooldownMin = min;
		this.attackCooldownMax = max;

		return this;
	}

	public TelegraphedRangedAttackGoal<T> windUpTime(int ticks) {
		this.preAttackTime = ticks;

		return this;
	}

	public TelegraphedRangedAttackGoal<T> targetRadius(float radius) {
		this.attackRadius = radius;

		return this;
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.taskOwner.getTarget();

		if (target == null || !target.isAlive())
			return false;

		this.target = target;

		return true;
	}

	@Override
	public void start() {
		this.attackCooldownTimer = 0;
	}

	@Override
	public boolean canContinueToUse() {
		return this.canUse() || !this.taskOwner.getNavigation().isDone();
	}

	@Override
	public void stop() {
		this.target = null;
		this.seeTime = 0;
		this.attackTime = 1;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		double targetDistance = this.taskOwner.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
		boolean canSeeTarget = this.taskOwner.getSensing().hasLineOfSight(this.target);

		if (canSeeTarget) {
			++this.seeTime;
		}
		else {
			this.seeTime = 0;
		}

		if (!(targetDistance > (double)this.attackRadius * this.attackRadius) && this.seeTime >= 5) {
			this.taskOwner.getNavigation().stop();
		}
		else {
			this.taskOwner.getNavigation().moveTo(this.target, this.speedModifier);
		}

		this.taskOwner.getLookControl().setLookAt(this.target, 30f, 30f);

		if (--this.attackTime <= 0 && this.attackTime >= -preAttackTime) {
			if (!canSeeTarget)
				return;

			if (this.attackTime == 0)
				this.taskOwner.swing(InteractionHand.MAIN_HAND);

			if (this.attackTime == -preAttackTime) {
				float distMod = (float)Math.sqrt(targetDistance) / this.attackRadius;
				float distFactor = Mth.clamp(distMod, 0.1f, 1);

				this.taskOwner.performRangedAttack(this.target, distFactor);
				this.attackTime = Mth.floor(distMod * (float)(this.attackCooldownMax - this.attackCooldownMin) + (float)this.attackCooldownMin);
			}
		}
		else if (this.attackTime < 0) {
			this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(targetDistance) / (double)this.attackRadius, this.attackCooldownMin, this.attackCooldownMax));
		}
	}
}
