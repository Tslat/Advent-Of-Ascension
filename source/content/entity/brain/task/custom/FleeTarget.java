package net.tslat.aoa3.content.entity.brain.task.custom;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.api.util.BrainUtils;

import java.util.List;

/**
 * Flee the current attack target. <br>
 * Defaults:
 * <ul>
 *     <li>20 block flee distance</li>
 *     <li>1x move speed modifier</li>
 * </ul>
 * @param <E> The entity
 */
public class FleeTarget<E extends PathfinderMob> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT));

	private int fleeDistance = 20;
	private float speedModifier = 1;

	private Path runPath = null;

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the maximum distance the entity should try to flee to
	 * @param blocks The distance, in blocks
	 * @return this
	 */
	public FleeTarget<E> fleeDistance(int blocks) {
		this.fleeDistance = blocks;

		return this;
	}

	/**
	 * Set the movespeed modifier for when the entity is running away.
	 * @param mod The speed multiplier modifier
	 * @return this
	 */
	public FleeTarget<E> speedModifier(float mod) {
		this.speedModifier = mod;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		LivingEntity target = BrainUtils.getTargetOfEntity(entity);
		double distToTarget = entity.distanceToSqr(target);
		Vec3 runPos = DefaultRandomPos.getPosAway(entity, this.fleeDistance, 10, target.position());

		if (runPos == null || target.distanceToSqr(runPos.x, runPos.y, runPos.z) < distToTarget)
			return false;

		this.runPath = entity.getNavigation().createPath(runPos.x, runPos.y, runPos.z, 0);

		return this.runPath != null;
	}

	@Override
	protected boolean canStillUse(ServerLevel level, E entity, long gameTime) {
		return entity.getNavigation().getPath() == this.runPath && !entity.getNavigation().isDone();
	}

	@Override
	protected boolean timedOut(long gameTime) {
		return false;
	}

	@Override
	protected void start(E entity) {
		entity.getNavigation().moveTo(this.runPath, this.speedModifier);
		BrainUtils.clearMemory(entity, MemoryModuleType.ATTACK_TARGET);
	}

	@Override
	protected void stop(E entity) {
		if (entity.getNavigation().getPath() == this.runPath)
			entity.getNavigation().setSpeedModifier(1);

		this.runPath = null;
	}
}
