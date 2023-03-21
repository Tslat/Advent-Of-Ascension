package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.player.Player;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.object.TriPredicate;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Special-case behaviour for setting secondary, tertiary, etc attack targets.<br>
 * This is useful for entities that concurrently target multiple entities, and use additional memory modules to store the additional targets.<br>
 * Uses {@link MemoryModuleType#NEAREST_PLAYERS} and {@link MemoryModuleType#NEAREST_VISIBLE_LIVING_ENTITIES} for its retrieval of additional targets.<br>
 * This behaviour will skip the usual pathing and alerting functionality as it is assumed they will be handled under the primary target.<br>
 * <br>
 * Defaults:<br>
 * <ul>
 *     <li>Will target any not-invulnerable player</li>
 *     <li>Avoids setting memories if a previous memory in the list is already set to the same target, and including {@link MemoryModuleType#ATTACK_TARGET}</li>
 * </ul>
 */
public class SetAdditionalAttackTargets<E extends Mob> extends ExtendedBehaviour<E> {
	private final List<MemoryModuleType<? extends LivingEntity>> targetingMemories = new ObjectArrayList<>();

	protected TriPredicate<E, MemoryModuleType<? extends LivingEntity>, LivingEntity> canAttackPredicate = (owner, memory, target) -> target.isAlive() && target instanceof Player player && !player.getAbilities().invulnerable;
	protected TriConsumer<E, MemoryModuleType<? extends LivingEntity>, LivingEntity> targetCallback = (owner, memory, target) -> {};
	protected boolean avoidDuplicateTargets = true;

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return List.of();
	}

	/**
	 * Set the predicate to determine whether a given entity should be targeted or not.
	 * @param predicate The predicate
	 * @return this
	 */
	public SetAdditionalAttackTargets<E> attackablePredicate(TriPredicate<E, MemoryModuleType<? extends LivingEntity>, LivingEntity> predicate) {
		this.canAttackPredicate = predicate;

		return this;
	}

	/**
	 * Sets the callback for when a target is being successfully set to a memory.
	 */
	public SetAdditionalAttackTargets<E> whenTargeting(TriConsumer<E, MemoryModuleType<? extends LivingEntity>, LivingEntity> callback) {
		this.targetCallback = callback;

		return this;
	}

	/**
	 * Add {@link MemoryModuleType memories} to the list of tertiary memories to set targets for.<br>
	 * This appends to any existing memories already added to this behaviour, and the functionality of this behaviour is order-dependent.
	 */
	public SetAdditionalAttackTargets<E> withMemories(MemoryModuleType<? extends LivingEntity>... targetMemories) {
		this.targetingMemories.addAll(List.of(targetMemories));

		return this;
	}

	/**
	 * Allow for the tertiary target memories to be set to the same as the previous modules if no new target is available
	 */
	public SetAdditionalAttackTargets<E> allowDuplicateTargeting() {
		this.avoidDuplicateTargets = false;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		Brain<?> brain = entity.getBrain();

		for (MemoryModuleType<?> memory : this.targetingMemories) {
			if (!BrainUtils.hasMemory(brain, memory))
				return true;
		}

		return BrainUtils.hasMemory(brain, MemoryModuleType.NEAREST_PLAYERS) || BrainUtils.hasMemory(brain, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES);
	}

	@Override
	protected void start(E entity) {
		Brain<?> brain = entity.getBrain();
		Set<LivingEntity> targetPool = new ObjectOpenHashSet<>();

		BrainUtils.withMemory(brain, MemoryModuleType.NEAREST_PLAYERS, targetPool::addAll);
		BrainUtils.withMemory(brain, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, memory -> memory.findAll(target -> true).forEach(targetPool::add));

		if (targetPool.isEmpty())
			return;

		for (MemoryModuleType<? extends LivingEntity> memory : this.targetingMemories) {
			LivingEntity target = BrainUtils.getMemory(brain, memory);

			if (target == null) {
				LivingEntity newTarget = null;

				for (Iterator<LivingEntity> iterator = targetPool.iterator(); iterator.hasNext(); newTarget = null) {
					newTarget = iterator.next();

					if (this.canAttackPredicate.test(entity, memory, newTarget)) {
						BrainUtils.setMemory(brain, (MemoryModuleType)memory, newTarget);
						this.targetCallback.accept(entity, memory, newTarget);
						iterator.remove();

						break;
					}
				}

				if (newTarget != null && !this.avoidDuplicateTargets)
					targetPool.add(newTarget);
			}
		}
	}
}
