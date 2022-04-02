package net.tslat.aoa3.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;

public final class BrainUtils {
	public static <T> T memoryOrDefault(LivingEntity entity, MemoryModuleType<T> memory, @Nullable T fallback) {
		return memoryOrDefault(entity.getBrain(), memory, fallback);
	}

	public static <T> T memoryOrDefault(Brain<?> brain, MemoryModuleType<T> memory, @Nullable T fallback) {
		return brain.getMemory(memory).orElse(fallback);
	}

	public static <T> Optional<T> getOptionalMemory(LivingEntity entity, MemoryModuleType<T> memory) {
		return getOptionalMemory(entity, memory, Optional.empty());
	}

	public static <T> Optional<T> getOptionalMemory(Brain<?> brain, MemoryModuleType<T> memory) {
		return getOptionalMemory(brain, memory, Optional.empty());
	}

	public static <T> Optional<T> getOptionalMemory(LivingEntity entity, MemoryModuleType<T> memory, Optional<T> fallback) {
		return getOptionalMemory(entity.getBrain(), memory, fallback);
	}

	public static <T> Optional<T> getOptionalMemory(Brain<?> brain, MemoryModuleType<T> memory, Optional<T> fallback) {
		Optional<T> storedMemory = brain.getMemory(memory);

		return storedMemory.isPresent() ? storedMemory : fallback;
	}

	@Nullable
	public static <T> T getMemory(LivingEntity entity, MemoryModuleType<T> memory) {
		return getMemory(entity.getBrain(), memory);
	}

	@Nullable
	public static <T> T getMemory(Brain<?> brain, MemoryModuleType<T> memory) {
		return memoryOrDefault(brain, memory, null);
	}

	public static <T> void withMemory(LivingEntity entity, MemoryModuleType<T> memory, Consumer<T> consumer) {
		withMemory(entity.getBrain(), memory, consumer);
	}

	public static <T> void withMemory(Brain<?> brain, MemoryModuleType<T> memory, Consumer<T> consumer) {
		brain.getMemory(memory).ifPresent(consumer);
	}

	public static boolean hasMemory(LivingEntity entity, MemoryModuleType<?> memory) {
		return hasMemory(entity.getBrain(), memory);
	}

	public static boolean hasMemory(Brain<?> brain, MemoryModuleType<?> memory) {
		return brain.hasMemoryValue(memory);
	}

	public static <T> void setMemory(LivingEntity entity, MemoryModuleType<T> memoryType, T memory) {
		setMemory(entity.getBrain(), memoryType, memory);
	}

	public static <T> void setMemory(Brain<?> brain, MemoryModuleType<T> memoryType, T memory) {
		brain.setMemory(memoryType, memory);
	}

	public static void clearMemory(LivingEntity entity, MemoryModuleType<?> memory) {
		clearMemory(entity.getBrain(), memory);
	}

	public static void clearMemory(Brain<?> brain, MemoryModuleType<?> memory) {
		brain.eraseMemory(memory);
	}

	@Nullable
	public static LivingEntity getTargetOfEntity(LivingEntity entity) {
		return getTargetOfEntity(entity, null);
	}

	@Nullable
	public static LivingEntity getTargetOfEntity(LivingEntity entity, @Nullable LivingEntity fallback) {
		return memoryOrDefault(entity.getBrain(), MemoryModuleType.ATTACK_TARGET, fallback);
	}

	@Nullable
	public static LivingEntity getLastAttacker(LivingEntity entity) {
		return memoryOrDefault(entity, MemoryModuleType.HURT_BY_ENTITY, null);
	}

	public static void setTargetOfEntity(LivingEntity entity, LivingEntity target) {
		if (entity instanceof Mob mob)
			mob.setTarget(target);


		setMemory(entity, MemoryModuleType.ATTACK_TARGET, target);

	}
}
