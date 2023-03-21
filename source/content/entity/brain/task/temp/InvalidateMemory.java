package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.apache.commons.lang3.function.ToBooleanBiFunction;

import java.util.List;

/**
 * Custom behaviour for conditionally invalidating/resetting existing memories.<br>
 * This allows for custom handling of stored memories, and clearing them at will.<br>
 * <br>
 * Invalidates the memory unconditionally once the behaviour runs. Use {@link InvalidateMemory#invalidateIf} and {@link ExtendedBehaviour#startCondition} to quantify its operating conditions
 * @param <E> The brain owner
 * @param <M> The data type of the memory
 */
public class InvalidateMemory<E extends LivingEntity, M> extends ExtendedBehaviour<E> {
	private List<Pair<MemoryModuleType<?>, MemoryStatus>> memoryRequirements;

	protected ToBooleanBiFunction<E, M> customPredicate = (entity, target) -> true;
	protected MemoryModuleType<M> memory;

	public InvalidateMemory(MemoryModuleType<M> memory) {
		super();

		this.memory = memory;
		this.memoryRequirements = List.of(Pair.of(this.memory, MemoryStatus.VALUE_PRESENT));
	}

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return this.memoryRequirements == null ? List.of() : this.memoryRequirements;
	}

	/**
	 * Sets the {@link MemoryModuleType memory} to check and invalidate.
	 */
	public InvalidateMemory<E, M> forMemory(MemoryModuleType<M> memory) {
		this.memory = memory;
		this.memoryRequirements = List.of(Pair.of(this.memory, MemoryStatus.VALUE_PRESENT));

		return this;
	}

	/**
	 * Sets a custom predicate to invalidate the memory if none of the previous checks invalidate it first.
	 */
	public InvalidateMemory<E, M> invalidateIf(ToBooleanBiFunction<E, M> predicate) {
		this.customPredicate = predicate;

		return this;
	}

	@Override
	protected void start(E entity) {
		M memory = BrainUtils.getMemory(entity, this.memory);

		if (memory != null && this.customPredicate.applyAsBoolean(entity, memory))
			BrainUtils.clearMemory(entity, this.memory);
	}
}
