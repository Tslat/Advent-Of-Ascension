package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.function.Predicate;

public class BlockProjectileOrStunlockTask<E extends Mob> extends Behavior<E> {
	public BlockProjectileOrStunlockTask() {
		this(LivingEntity::isAlive, false);
	}

	public BlockProjectileOrStunlockTask(Predicate<E> canRetaliatePredicate, boolean overrideCurrentTarget) {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT));
	}

	@Override
	protected void start(ServerLevel pLevel, E entity, long pGameTime) {
		entity.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, entity.getBrain().getMemory(MemoryModuleType.HURT_BY_ENTITY));
	}
}
