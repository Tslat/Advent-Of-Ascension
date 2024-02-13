package net.tslat.aoa3.library.builder;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public final class EntityPredicate<T extends Entity> implements Predicate<T> {
	public static final Immutable<Entity> TARGETABLE_HOSTILE_MOB = new EntityPredicate<>().isAlive().isHostileMob().immutable();
	public static final Immutable<Entity> SURVIVAL_PLAYER = new EntityPredicate<Entity>().isAlive().isSurvival().immutable();
	public static final Immutable<Entity> DAMAGEABLE_ENTITIES = new EntityPredicate<>().isAlive().isDamageable().immutable();
	public static final Immutable<Entity> TARGETABLE_ENTITIES = new EntityPredicate<>().isAlive().and(entity -> !(entity instanceof Player pl) || (!pl.isCreative() && !pl.isSpectator())).immutable();

	private Predicate<T> predicate;

	public EntityPredicate() {
		this.predicate = Objects::nonNull;
	}

	public EntityPredicate(Entity excluding) {
		this.predicate = checkingEntity -> checkingEntity != excluding;
	}

	public EntityPredicate(Predicate<T> basePredicate) {
		this.predicate = basePredicate;
	}

	@Override
	public boolean test(T t) {
		return this.predicate.test(t);
	}

	@NotNull
	@Override
	public EntityPredicate<T> and(@NotNull Predicate<? super T> other) {
		Predicate<T> predicate = this.predicate;

		this.predicate = entity -> predicate.test(entity) && other.test(entity);

		return this;
	}

	@NotNull
	@Override
	public EntityPredicate<T> or(@NotNull Predicate<? super T> other) {
		Predicate<T> predicate = this.predicate;

		this.predicate = entity -> predicate.test(entity) || other.test(entity);

		return this;
	}

	public EntityPredicate<T> isSubtypeOf(Class<? extends Entity> clazz) {
		return and(clazz::isInstance);
	}

	public EntityPredicate<T> isNotSubtypeOf(Class<? extends Entity> clazz) {
		return and(entity -> !clazz.isInstance(entity));
	}

	public EntityPredicate<T> isAlive() {
		return and(Entity::isAlive);
	}

	public EntityPredicate<T> isDead() {
		return and(entity -> !entity.isAlive());
	}

	public EntityPredicate<T> isCreative() {
		return and(entity -> entity instanceof Player pl && pl.isCreative());
	}

	public EntityPredicate<T> isSpectator() {
		return and(Entity::isSpectator);
	}

	public EntityPredicate<T> isSurvival() {
		return and(entity -> entity instanceof Player pl && !pl.isSpectator() && !pl.isCreative());
	}

	public EntityPredicate<T> isHostileMob() {
		return and(Enemy.class::isInstance);
	}

	public EntityPredicate<T> isFriendlyMob() {
		return and(entity -> !(entity instanceof Enemy));
	}

	public EntityPredicate<T> isPlayer() {
		return isSubtypeOf(Player.class);
	}

	public EntityPredicate<T> isNonPlayer() {
		return and(entity -> !(entity instanceof Player));
	}

	public EntityPredicate<T> isDamageable() {
		return and(entity -> !entity.isInvulnerable() && (!(entity instanceof Player pl) || (!pl.isCreative() && !pl.isSpectator())));
	}

	public EntityPredicate<T> is(EntityType<?> entityType) {
		return and(entity -> entity.getType() == entityType);
	}

	public EntityPredicate<T> isNot(EntityType<?> entityType) {
		return and(entity -> entity.getType() != entityType);
	}

	public EntityPredicate<T> isInCollection(Collection<T> collection) {
		return and(collection::contains);
	}

	public EntityPredicate<T> notInCollection(Collection<T> collection) {
		return and(entity -> !collection.contains(entity));
	}

	private Immutable<T> immutable() {
		return new Immutable<>(this);
	}

	public static class Immutable<T extends Entity> implements Predicate<T> {
		private final Predicate<T> predicate;

		Immutable(EntityPredicate<T> predicate) {
			this.predicate = predicate;
		}

		@Override
		public boolean test(T entity) {
			return this.predicate.test(entity);
		}

		@NotNull
		@Override
		public EntityPredicate<T> and(@NotNull Predicate<? super T> other) {
			return new EntityPredicate<T>(this.predicate).and(other);
		}

		@NotNull
		@Override
		public EntityPredicate<T> or(@NotNull Predicate<? super T> other) {
			return new EntityPredicate<T>(this.predicate).or(other);
		}
	}
}
