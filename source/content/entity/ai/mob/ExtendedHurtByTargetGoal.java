package net.tslat.aoa3.content.entity.ai.mob;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.GameRules;
import net.tslat.aoa3.content.entity.ai.ExtendedTargetGoal;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import java.util.function.Predicate;

public class ExtendedHurtByTargetGoal<T extends Mob> extends ExtendedTargetGoal<T> {
	private static final TargetingConditions HURT_BY_TARGETING = TargetingConditions.forCombat().ignoreLineOfSight().ignoreInvisibilityTesting();
	private final Predicate<Entity> DEFAULT_ALLY_PREDICATE = entity -> entity instanceof Mob mob && mob.getTarget() == null && !mob.isAlliedTo(this.entity.getLastHurtByMob()) && (!(this.entity instanceof TamableAnimal animal) || (mob instanceof TamableAnimal tamableMob && animal.getOwner() == tamableMob.getOwner()));

	private int lastHurtByTimestamp;
	private Predicate<Entity> targetableAttackerPredicate = entity -> true;
	private Predicate<Entity> alertableAllyPredicate = DEFAULT_ALLY_PREDICATE;
	private int alertRadius = -1;

	public ExtendedHurtByTargetGoal(T entity) {
		super(entity);
	}

	public ExtendedHurtByTargetGoal<T> onlyTargetWhen(Predicate<Entity> predicate) {
		this.targetableAttackerPredicate = predicate;

		return this;
	}

	public ExtendedHurtByTargetGoal<T> alertNearbyEntities() {
		return alertNearbyEntities(new EntityPredicate<>().is(this.entity.getType()));
	}

	public ExtendedHurtByTargetGoal<T> alertNearbyEntities(Predicate<Entity> predicate) {
		return alertNearbyEntities((int)entity.getAttributeValue(Attributes.FOLLOW_RANGE), predicate);
	}

	public ExtendedHurtByTargetGoal<T> alertNearbyEntities(int radius) {
		return alertNearbyEntities(radius, new EntityPredicate<>().is(this.entity.getType()));
	}

	public ExtendedHurtByTargetGoal<T> alertNearbyEntities(int radius, Predicate<Entity> predicate) {
		this.alertableAllyPredicate = predicate.and(DEFAULT_ALLY_PREDICATE);
		this.alertRadius = radius;

		return this;
	}

	@Override
	public boolean canUse() {
		if (!super.canUse())
			return false;

		LivingEntity lastAttacker = this.entity.getLastHurtByMob();

		if (this.entity.getLastHurtByMobTimestamp() == this.lastHurtByTimestamp || lastAttacker == null)
			return false;

		if (lastAttacker.getType() == EntityType.PLAYER && this.entity.level().getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER))
			return false;

		if (!this.targetableAttackerPredicate.test(lastAttacker))
			return false;

		return isAttackable(lastAttacker, HURT_BY_TARGETING);
	}

	@Override
	public void start() {
		super.start();

		this.entity.setTarget(this.entity.getLastHurtByMob());
		this.target = this.entity.getTarget();
		this.lastHurtByTimestamp = this.entity.getLastHurtByMobTimestamp();
		this.targetOccludedTime = 300;

		if (alertRadius > 0)
			alertAllies();
	}

	protected void alertAllies() {
		for (Mob mob : EntityRetrievalUtil.<Mob>getEntities(this.entity, this.alertRadius, this.alertableAllyPredicate)) {
			mob.setTarget(this.entity.getLastHurtByMob());
		}
	}
}
