package net.tslat.aoa3.content.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.scores.Team;

import javax.annotation.Nullable;

public abstract class ExtendedTargetGoal<T extends Mob> extends ExtendedGoal<T> {
	protected boolean requireLineOfSight = false;
	protected boolean mustBePathable = false;
	protected int occludedTargetMemoryTime = 60;

	protected int nextPathCheck;
	protected boolean canPathTo = false;
	protected int targetOccludedTime;
	@Nullable
	protected LivingEntity target = null;

	protected ExtendedTargetGoal(T entity) {
		super(entity);
	}

	public ExtendedTargetGoal<T> requireLineOfSight() {
		this.requireLineOfSight = true;

		return this;
	}

	public ExtendedTargetGoal<T> onlyPathableTargets() {
		this.mustBePathable = true;

		return this;
	}

	public ExtendedTargetGoal<T> rememberLostTargetsFor(int ticks) {
		this.occludedTargetMemoryTime = ticks;

		return this;
	}

	@Override
	public boolean canContinueToUse() {
		if (!super.canContinueToUse())
			return false;

		if (this.target == null && (this.target = this.entity.getTarget()) == null)
			return false;

		if (!this.entity.canAttack(this.target))
			return false;

		Team team = this.entity.getTeam();

		if (team != null && team == this.target.getTeam())
			return false;

		double followRange = getTargetingRange();

		if (this.entity.distanceToSqr(this.target) > followRange * followRange)
			return false;

		if (this.requireLineOfSight) {
			if (this.entity.getSensing().hasLineOfSight(this.target)) {
				this.targetOccludedTime = 0;
			}
			else if (this.targetOccludedTime++ > reducedTickDelay(this.occludedTargetMemoryTime)) {
				return false;
			}
		}

		this.entity.setTarget(this.target);

		return true;
	}

	@Override
	public void start() {
		super.start();

		this.targetOccludedTime = 0;
		this.nextPathCheck = 0;
	}

	@Override
	public void stop() {
		super.stop();

		this.entity.setTarget(null);
		this.target = null;
	}

	protected boolean isAttackable(LivingEntity target, TargetingConditions predicate) {
		if (!predicate.test(this.entity, target))
			return false;

		if (!this.entity.isWithinRestriction(target.blockPosition()))
			return false;

		if (this.mustBePathable) {
			if (this.nextPathCheck-- <= 0)
				this.canPathTo = canPathTo(target);

			return this.canPathTo;
		}

		return true;
	}

	protected boolean canPathTo(LivingEntity entity) {
		this.nextPathCheck = reducedTickDelay(10 + this.entity.getRandom().nextInt(5));
		Path path = this.entity.getNavigation().createPath(target, 0);

		if (path == null)
			return false;

		Node pathEnd = path.getEndNode();

		if (pathEnd == null)
			return false;

		return (Math.pow(pathEnd.x - target.getBlockX(), 2) + Math.pow(pathEnd.z - target.getBlockZ(), 2)) <= 2.25d;
	}

	protected double getTargetingRange() {
		return this.entity.getAttributeValue(Attributes.FOLLOW_RANGE);
	}
}
