package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Functional equivalent of the goal system's {@link net.minecraft.world.entity.ai.goal.PanicGoal panic goal}.<br>
 * Rapidly sets a runaway position based on its last damage.<br>
 * Defaults:
 * <ul>
 *     <li>1.25x Speed modifier when panicking</li>
 *     <li>Panics if freezing, on fire, or was recently hurt by a living entity</li>
 *     <li>Runs to a nearby location within 5x4 blocks radius</li>
 *     <li>Panics for a minimum of 5-6 seconds</li>
 * </ul>
 * @param <E> The entity
 */
public class Panic<E extends PathfinderMob> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.HURT_BY, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.REGISTERED));

	protected BiPredicate<E, DamageSource> shouldPanicPredicate = (entity, damageSource) -> entity.isFreezing() || entity.isOnFire() || damageSource.getEntity() instanceof LivingEntity;
	protected Object2FloatFunction<E> speedMod = entity -> 1.25f;
	protected SquareRadius radius = new SquareRadius(5, 4);
	protected BiFunction<E, DamageSource, Integer> panicFor = (entity, damageSource) -> entity.getRandom().nextInt(100, 120);

	protected Vec3 targetPos = null;
	protected int panicEndTime = 0;

	/**
	 * Set a custom predicate for if the entity should panic based on its current conditions.
	 * @param predicate The predicate
	 * @return this
	 */
	public Panic<E> panicIf(final BiPredicate<E, DamageSource> predicate) {
		this.shouldPanicPredicate = predicate;

		return this;
	}

	/**
	 * Determine the length of time (in ticks) that the entity should panic for once starting
	 * @param function The predicate
	 * @return this
	 */
	public Panic<E> panicFor(final BiFunction<E, DamageSource, Integer> function) {
		this.panicFor = function;

		return this;
	}

	/**
	 * Set the movespeed modifier for the entity when panicking.
	 * @param speedModifier The movespeed modifier/multiplier
	 * @return this
	 */
	public Panic<E> speedMod(final Object2FloatFunction<E> speedModifier) {
		this.speedMod = speedModifier;

		return this;
	}

	/**
	 * Set the radius in which to look for walk positions.
	 * @param radius The coordinate radius, in blocks
	 * @return this
	 */
	public Panic<E> setRadius(double radius) {
		return setRadius(radius, radius);
	}

	/**
	 * Set the radius in which to look for walk positions.
	 * @param xz The X/Z coordinate radius, in blocks
	 * @param y The Y coordinate radius, in blocks
	 * @return this
	 */
	public Panic<E> setRadius(double xz, double y) {
		this.radius = new SquareRadius(xz, y);

		return this;
	}

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		if (!this.shouldPanicPredicate.test(entity, BrainUtils.getMemory(entity, MemoryModuleType.HURT_BY)))
			return false;

		setPanicTarget(entity);

		return this.targetPos != null;
	}

	@Override
	protected boolean timedOut(long gameTime) {
		return false;
	}

	@Override
	protected boolean shouldKeepRunning(E entity) {
		return entity.tickCount < this.panicEndTime;
	}

	@Override
	protected void start(E entity) {
		BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(this.targetPos, this.speedMod.apply(entity), 0));
		BrainUtils.setMemory(entity, MemoryModuleType.IS_PANICKING, true);

		this.panicEndTime = entity.tickCount + this.panicFor.apply(entity, BrainUtils.getMemory(entity, MemoryModuleType.HURT_BY));
	}

	@Override
	protected void tick(E entity) {
		if (entity.getNavigation().isDone()) {
			this.targetPos = null;
			setPanicTarget(entity);

			if (this.targetPos != null) {
				BrainUtils.clearMemory(entity, MemoryModuleType.PATH);
				BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(this.targetPos, this.speedMod.apply(entity), 1));
			}
		}
	}

	@Override
	protected void stop(E entity) {
		this.targetPos = null;
		this.panicEndTime = 0;

		BrainUtils.setMemory(entity, MemoryModuleType.IS_PANICKING, false);
	}

	@Nullable
	protected Vec3 findNearbyWater(E entity) {
		final BlockPos pos = entity.blockPosition();
		final Level level = entity.level();

		return !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty() ? null : BlockPos.findClosestMatch(entity.blockPosition(), (int)this.radius.xzRadius(), (int)this.radius.yRadius(), checkPos -> level.getFluidState(checkPos).is(FluidTags.WATER)).map(Vec3::atBottomCenterOf).orElse(null);
	}

	protected void setPanicTarget(E entity) {
		if (entity.isOnFire())
			this.targetPos = findNearbyWater(entity);

		if (this.targetPos == null) {
			final DamageSource lastDamage = BrainUtils.getMemory(entity, MemoryModuleType.HURT_BY);

			if (lastDamage != null && lastDamage.getEntity() instanceof LivingEntity attacker)
				this.targetPos = DefaultRandomPos.getPosAway(entity, (int)this.radius.xzRadius(), (int)this.radius.yRadius(), attacker.position());

			if (this.targetPos == null)
				this.targetPos = DefaultRandomPos.getPos(entity, (int)this.radius.xzRadius(), (int)this.radius.yRadius());
		}
	}
}
