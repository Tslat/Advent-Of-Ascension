package net.tslat.aoa3.object.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Predicate;

public class BlockProjectileOrStunlockTask<E extends MobEntity> extends Task<E> {
	public BlockProjectileOrStunlockTask() {
		this(LivingEntity::isAlive, false);
	}

	public BlockProjectileOrStunlockTask(Predicate<E> canRetaliatePredicate, boolean overrideCurrentTarget) {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleStatus.VALUE_PRESENT));
	}

	@Override
	protected void start(ServerWorld pLevel, E entity, long pGameTime) {
		entity.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, entity.getBrain().getMemory(MemoryModuleType.HURT_BY_ENTITY));
	}
}
