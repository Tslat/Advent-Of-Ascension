package net.tslat.aoa3.content.entity.brain.task.custom;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.util.BrainUtils;

/**
 * Extension of MoveToWalkTarget, but auto-marking the sprinting flag depending on the movespeed.
 * This can be useful for using sprint animations on the client.
 * @param <E>
 */
public class WalkOrRunToWalkTarget<E extends PathfinderMob> extends MoveToWalkTarget<E> {
	@Override
	protected void startOnNewPath(E entity) {
		BrainUtils.setMemory(entity, MemoryModuleType.PATH, this.path);

		if (entity.getNavigation().moveTo(this.path, this.speedModifier))
			entity.setSharedFlag(3, this.speedModifier > 1);
	}

	@Override
	protected void stop(E entity) {
		super.stop(entity);
		entity.setSharedFlag(3, false);
	}
}
