package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.ai.animation.Animatable;
import net.tslat.aoa3.util.RandomUtil;

import java.util.EnumSet;

public class AnimatableMeleeAttackGoal<T extends Mob & Animatable> extends Goal {
	private final T entity;

	protected double speedModifier = 1d;
	protected boolean ignoreLineOfSight = false;
	protected String animKey = "ATTACK";
	protected double attackReach;
	protected int attackInterval = 20;
	protected int preAttackTime = 0;
	protected boolean stopAttackIfOutOfRange = false;

	protected long attackDamageTick = -1;
	protected Path currentPath;
	protected Vec3 targetLocation;
	protected int newPathCooldown;
	protected int attackCooldown;

	protected long goalTimeoutCounter;

	public AnimatableMeleeAttackGoal(T entity) {
		this.entity = entity;
		this.attackReach = entity.getBbWidth() * 1.75d + (entity.getEyeHeight() / 3.6d * 0.25d);

		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public AnimatableMeleeAttackGoal<T> speedModifier(double modifier) {
		this.speedModifier = modifier;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> ignoreLineOfSight() {
		this.ignoreLineOfSight = true;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> useAnimation(String animKey) {
		this.animKey = animKey;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> attackReach(double reach) {
		this.attackReach = reach;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> attackInterval(int ticks) {
		this.attackInterval = ticks;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> preAttackTime(int ticks) {
		this.preAttackTime = ticks;

		return this;
	}

	public AnimatableMeleeAttackGoal<T> cancelAttackIfOutOfRange() {
		this.stopAttackIfOutOfRange = true;

		return this;
	}

	@Override
	public boolean canUse() {
		long gameTime = this.entity.level.getGameTime();

		if (gameTime - this.goalTimeoutCounter < 20)
			return false;

		if (entity.level.getDifficulty() == Difficulty.PEACEFUL)
			return false;

		this.goalTimeoutCounter = gameTime;
		LivingEntity target = this.entity.getTarget();

		if (target == null || !target.isAlive())
			return false;

		this.currentPath = this.entity.getNavigation().createPath(target, 0);

		if (this.currentPath != null)
			return true;

		return this.getAttackReachSqr(target) >= this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
	}

	@Override
	public void start() {
		this.entity.getNavigation().moveTo(this.currentPath, this.speedModifier);
		this.entity.setAggressive(true);
		this.newPathCooldown = 0;
		this.attackCooldown = 0;
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity target = this.entity.getTarget();

		if (target == null || !target.isAlive())
			return false;

		if (target.isSpectator() || (target instanceof Player && ((Player)target).isCreative()))
			return false;

		if (attackDamageTick >= 0)
			return true;

		if (!ignoreLineOfSight)
			return !this.entity.getNavigation().isDone();

		return entity.isWithinRestriction(target.blockPosition());
	}

	@Override
	public void tick() {
		LivingEntity target = this.entity.getTarget();
		double targetDistance = this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());

		if (this.newPathCooldown > 0)
			this.newPathCooldown--;

		this.entity.getLookControl().setLookAt(target, 30, 30);

		if (this.newPathCooldown <= 0 && (this.ignoreLineOfSight || this.entity.getSensing().hasLineOfSight(target)) && (this.targetLocation == null || target.distanceToSqr(this.targetLocation) >= 1 || RandomUtil.oneInNChance(20))) {
			this.targetLocation = target.position();
			this.newPathCooldown = RandomUtil.randomNumberBetween(4, 11);

			if (targetDistance > 256) {
				this.newPathCooldown += 5;

				if (targetDistance > 1024)
					this.newPathCooldown += 5;
			}

			if (!this.entity.getNavigation().moveTo(target, this.speedModifier))
				this.newPathCooldown += 15;
		}

		if (this.attackCooldown > 0)
			this.attackCooldown--;

		this.checkAndPerformAttack(target, targetDistance);
	}

	@Override
	public void stop() {
		if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(this.entity.getTarget()))
			this.entity.setTarget(null);

		this.entity.setAggressive(false);
		this.entity.getNavigation().stop();
		this.entity.resetAnimationState(this.animKey);
	}

	protected void checkAndPerformAttack(LivingEntity target, double sqrDistToTarget) {
		int animState = this.entity.getAnimationState(animKey);

		if (animState > 0 && attackCooldown > 0)
			this.entity.setAnimationState(animKey, 0);

		if (sqrDistToTarget <= getAttackReachSqr(target) || (animState > 0 && !this.stopAttackIfOutOfRange)) {
			if (attackCooldown <= 0) {
				this.attackCooldown = this.attackInterval;
				this.entity.swing(InteractionHand.MAIN_HAND);
				this.entity.setAnimationState(animKey, 1);

				this.attackDamageTick = this.entity.level.getGameTime() + this.preAttackTime;
			}

			if (this.attackDamageTick >= 0 && this.entity.level.getGameTime() >= attackDamageTick) {
				this.entity.doHurtTarget(target);
				this.entity.setAnimationState(animKey, 2);

				this.attackDamageTick = -1;
			}
		}
		else {
			this.attackDamageTick = -1;
		}
	}

	protected double getAttackReachSqr(LivingEntity target) {
		double targetBBOffset = target.getBbWidth() * 0.5d;

		return this.attackReach * this.attackReach + targetBBOffset * targetBBOffset;
	}
}
