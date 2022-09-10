package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.tslat.aoa3.content.entity.ai.ExtendedTargetGoal;
import net.tslat.smartbrainlib.api.util.EntityRetrievalUtil;

import java.util.EnumSet;
import java.util.function.Predicate;

public class ExtendedFindNearbyTargetGoal<T extends Mob> extends ExtendedTargetGoal<T> {
	protected final Predicate targetPredicate;
	protected final boolean onlyPlayers;

	protected double radius;

	public ExtendedFindNearbyTargetGoal(T entity, boolean onlyPlayers, Predicate<? extends Entity> targetPredicate) {
		super(entity);

		this.targetPredicate = targetPredicate;
		this.onlyPlayers = onlyPlayers;
		this.radius = getTargetingRange();

		setFlags(EnumSet.of(Flag.TARGET));
	}

	public ExtendedFindNearbyTargetGoal<T> searchRadius(double radius) {
		this.radius = radius;

		return this;
	}

	@Override
	public boolean canUse() {
		if (!super.canUse())
			return false;

		this.target = findTarget();

		return this.target != null;
	}

	@Override
	public void start() {
		super.start();

		this.entity.setTarget(this.target);
	}

	protected LivingEntity findTarget() {
		if (this.onlyPlayers)
			return EntityRetrievalUtil.getNearestPlayer(this.entity, this.radius, this.targetPredicate);

		return (LivingEntity)EntityRetrievalUtil.getNearestEntity(this.entity, this.radius, entity -> entity instanceof LivingEntity && this.targetPredicate.test(entity));
	}
}
