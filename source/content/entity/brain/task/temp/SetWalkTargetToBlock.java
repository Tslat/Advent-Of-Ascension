package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Path setting behaviour for walking to/near a block position.
 * @param <E> The entity
 */
public class SetWalkTargetToBlock<E extends PathfinderMob> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(SBLMemoryTypes.NEARBY_BLOCKS.get(), MemoryStatus.VALUE_PRESENT));

	protected BiPredicate<E, Pair<BlockPos, BlockState>> predicate = (entity, block) -> true;
	protected BiFunction<E, Pair<BlockPos, BlockState>, Float> speedMod = (owner, pos) -> 1f;
	protected BiFunction<E, Pair<BlockPos, BlockState>, Integer> closeEnoughDist = (entity, pos) -> 2;

	protected Pair<BlockPos, BlockState> target = null;

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the predicate to determine whether a given position/state should be the target path
	 * @param predicate The predicate
	 * @return this
	 */
	public SetWalkTargetToBlock<E> predicate(final BiPredicate<E, Pair<BlockPos, BlockState>> predicate) {
		this.predicate = predicate;

		return this;
	}

	/**
	 * Set the movespeed modifier for the entity when moving to the target.
	 * @param speedModifier The movespeed modifier/multiplier
	 * @return this
	 */
	public SetWalkTargetToBlock<E> speedMod(BiFunction<E, Pair<BlockPos, BlockState>, Float> speedModifier) {
		this.speedMod = speedModifier;

		return this;
	}

	/**
	 * Set the distance (in blocks) that is 'close enough' for the entity to be considered at the target position
	 * @param function The function
	 * @return this
	 */
	public SetWalkTargetToBlock<E> closeEnoughWhen(final BiFunction<E, Pair<BlockPos, BlockState>, Integer> function) {
		this.closeEnoughDist = function;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		for (Pair<BlockPos, BlockState> position : BrainUtils.getMemory(entity, SBLMemoryTypes.NEARBY_BLOCKS.get())) {
			if (this.predicate.test(entity, position)) {
				this.target = position;

				break;
			}
		}

		return this.target != null;
	}

	@Override
	protected void start(E entity) {
		BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(this.target.getFirst(), this.speedMod.apply(entity, this.target), this.closeEnoughDist.apply(entity, this.target)));
		BrainUtils.setMemory(entity, MemoryModuleType.LOOK_TARGET, new BlockPosTracker(this.target.getFirst()));
	}

	@Override
	protected void stop(E entity) {
		this.target = null;
	}
}
