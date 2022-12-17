package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.ai.ExtendedGoal;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.EnumSet;

public class ExtendedMeleeAttackGoal<T extends Mob> extends ExtendedGoal<T> {
	protected double speedModifier = 1d;
	protected boolean ignoreLineOfSight = false;
	protected double attackReach;
	protected IntProvider attackInterval = ConstantInt.of(20);

	protected Path currentPath;
	protected Vec3 targetLocation;
	protected int newPathCooldown;
	protected int attackCooldown;

	protected long goalTimeoutCounter;

	public ExtendedMeleeAttackGoal(T entity) {
		super(entity);

		this.attackReach = entity.getBbWidth() * 1.75d + (entity.getEyeHeight() / 3.6d * 0.25d);

		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public ExtendedMeleeAttackGoal<T> speedModifier(double modifier) {
		this.speedModifier = modifier;

		return this;
	}

	public ExtendedMeleeAttackGoal<T> ignoreLineOfSight() {
		this.ignoreLineOfSight = true;

		return this;
	}

	public ExtendedMeleeAttackGoal<T> attackReach(double reach) {
		this.attackReach = reach;

		return this;
	}

	public ExtendedMeleeAttackGoal<T> attackInterval(IntProvider tickValueProvider) {
		this.attackInterval = tickValueProvider;

		return this;
	}

	@Override
	public boolean canUse() {
		if (!super.canUse())
			return false;

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
		super.start();

		this.entity.getNavigation().moveTo(this.currentPath, this.speedModifier);
		this.entity.setAggressive(true);
		this.newPathCooldown = 0;
		this.attackCooldown = 0;
	}

	@Override
	public boolean canContinueToUse() {
		if (!super.canContinueToUse())
			return false;

		LivingEntity target = this.entity.getTarget();

		if (target == null || !target.isAlive())
			return false;

		if (target.isSpectator() || (target instanceof Player && ((Player)target).isCreative()))
			return false;

		if (isTelegraphingAction())
			return true;

		if (!ignoreLineOfSight)
			return !this.entity.getNavigation().isDone();

		return entity.isWithinRestriction(target.blockPosition());
	}

	@Override
	public void tick() {
		super.tick();

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
		super.stop();

		this.entity.setAggressive(false);
		this.entity.getNavigation().stop();
	}

	protected void checkAndPerformAttack(LivingEntity target, double sqrDistToTarget) {
		if (sqrDistToTarget <= getAttackReachSqr(target)) {
			if (attackCooldown <= 0) {
				this.attackCooldown = this.attackInterval.sample(RandomUtil.RANDOM);

				startTelegraphingNextAction();
				this.entity.swing(InteractionHand.MAIN_HAND);

				return;
			}

			if (hasActionTelegraphFinished()) {
				this.entity.doHurtTarget(target);
				resetActionTelegraph();
			}
		}
		else {
			resetActionTelegraph();
		}
	}

	protected double getAttackReachSqr(LivingEntity target) {
		double targetBBOffset = target.getBbWidth() * 0.5d;

		return this.attackReach * this.attackReach + targetBBOffset * targetBBOffset;
	}
}
