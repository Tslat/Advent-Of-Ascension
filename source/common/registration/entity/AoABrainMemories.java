package net.tslat.aoa3.common.registration.entity;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;

import java.util.List;
import java.util.Optional;

public final class AoABrainMemories {
	public static void init() {}

	public static final RegistryObject<MemoryModuleType<List<Projectile>>> INCOMING_PROJECTILES = registerMemoryType("incoming_projectiles");

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id, Codec<T> codec) {
		return AoARegistries.BRAIN_MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.of(codec)));
	}

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id) {
		return AoARegistries.BRAIN_MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.empty()));
	}
}
