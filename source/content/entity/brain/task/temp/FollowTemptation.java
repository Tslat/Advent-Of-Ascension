package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * {@link net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour ExtendedBehaviour} equivalent of vanilla's {@link net.minecraft.world.entity.ai.behavior.FollowTemptation FollowTemptation}.<br>
 * Has the entity follow a relevant temptation target (I.E. a player holding a tempting item).<br>
 * Will continue running for as long as the entity is being tempted.<br>
 * Defaults:
 * <ul>
 *     <li>Follows the temptation target indefinitely</li>
 *     <li>Will stop following if panicked or if it has an active breed target</li>
 *     <li>Will not follow a temptation target again for 5 seconds after stopping</li>
 *     <li>Considers 2.5 blocks 'close enough' for the purposes of following temptation</li>
 *     <li>1x speed modifier while following</li>
 * </ul>
 */
public class FollowTemptation<E extends PathfinderMob> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.IS_TEMPTED, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.BREED_TARGET, MemoryStatus.REGISTERED));

	protected BiFunction<E, Player, Float> speedMod = (entity, temptingPlayer) -> 1f;
	protected BiPredicate<E, Player> shouldFollow = (entity, temptingPlayer) -> (!(entity instanceof Animal animal) || animal.getAge() == 0) && !BrainUtils.hasMemory(entity, MemoryModuleType.IS_PANICKING);
	protected BiFunction<E, Player, Float> closeEnoughWhen = (owner, temptingPlayer) -> 2.5f;
	protected Object2IntFunction<E> temptationCooldown = entity -> 100;

	public FollowTemptation() {
		super();

		this.runFor(entity -> Integer.MAX_VALUE);
	}

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the movespeed modifier for the entity when following the tempting player.
	 * @param speedModifier The movespeed modifier/multiplier
	 * @return this
	 */
	public FollowTemptation<E> speedMod(final BiFunction<E, Player, Float> speedModifier) {
		this.speedMod = speedModifier;

		return this;
	}

	/**
	 * Determine whether the entity should follow the tempting player or not
	 * @param predicate The temptation predicate
	 * @return this
	 */
	public FollowTemptation<E> followIf(final BiPredicate<E, Player> predicate) {
		this.shouldFollow = predicate;

		return this;
	}

	/**
	 * Sets the amount (in blocks) that the mob can be considered 'close enough' to their temptation that they can stop pathfinding
	 * @param closeEnoughMod The distance modifier
	 * @return this
	 */
	public FollowTemptation<E> closeEnoughDist(final BiFunction<E, Player, Float> closeEnoughMod) {
		this.closeEnoughWhen = closeEnoughMod;

		return this;
	}

	/**
	 * Sets the length of time (in ticks) the entity should ignore temptation after having previously been tempted.<br>
	 * NOTE: This could be ignored if the {@link FollowTemptation#followIf} predicate has been overriden
	 * @param cooldownFunction The cooldown function
	 * @return this
	 */
	public FollowTemptation<E> temptationCooldown(final Object2IntFunction<E> cooldownFunction) {
		this.temptationCooldown = cooldownFunction;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		return this.shouldFollow.test(entity, BrainUtils.getMemory(entity, MemoryModuleType.TEMPTING_PLAYER));
	}

	@Override
	protected boolean shouldKeepRunning(E entity) {
		return BrainUtils.hasMemory(entity, MemoryModuleType.TEMPTING_PLAYER) &&
				!BrainUtils.hasMemory(entity, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS) &&
				!BrainUtils.hasMemory(entity, MemoryModuleType.BREED_TARGET) &&
				this.shouldFollow.test(entity, BrainUtils.getMemory(entity, MemoryModuleType.TEMPTING_PLAYER));
	}

	@Override
	protected void start(E entity) {
		BrainUtils.setMemory(entity, MemoryModuleType.IS_TEMPTED, true);
	}

	@Override
	protected void tick(E entity) {
		final Player temptingPlayer = BrainUtils.getMemory(entity, MemoryModuleType.TEMPTING_PLAYER);
		final float closeEnough = this.closeEnoughWhen.apply(entity, temptingPlayer);

		BrainUtils.setMemory(entity, MemoryModuleType.LOOK_TARGET, new EntityTracker(temptingPlayer, true));

		if (entity.distanceToSqr(temptingPlayer) < closeEnough * closeEnough) {
			BrainUtils.clearMemory(entity, MemoryModuleType.WALK_TARGET);
		}
		else {
			BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityTracker(temptingPlayer, false), this.speedMod.apply(entity, temptingPlayer), (int)closeEnough));
		}
	}

	@Override
	protected void stop(E entity) {
		final int cooldownTicks = this.temptationCooldown.apply(entity);

		BrainUtils.setForgettableMemory(entity, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, cooldownTicks, cooldownTicks);
		BrainUtils.setMemory(entity, MemoryModuleType.IS_TEMPTED, false);
		BrainUtils.clearMemories(entity, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET);
	}
}
