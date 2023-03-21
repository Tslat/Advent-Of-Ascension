package net.tslat.aoa3.common.registration.entity;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;

import java.util.Optional;

public final class AoABrainMemories {
	public static final RegistryObject<MemoryModuleType<LivingEntity>> SECOND_ATTACK_TARGET = registerMemoryType("second_attack_target");
	public static final RegistryObject<MemoryModuleType<LivingEntity>> THIRD_ATTACK_TARGET = registerMemoryType("third_attack_target");

	public static void init() {}

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id, Codec<T> codec) {
		return AoARegistries.BRAIN_MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.of(codec)));
	}

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id) {
		return AoARegistries.BRAIN_MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.empty()));
	}
}
