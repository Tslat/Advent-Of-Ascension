package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.List;
import java.util.Optional;

public final class AoABrainMemories {
	public static final DeferredRegister<MemoryModuleType<?>> MEMORIES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<MemoryModuleType<List<ProjectileEntity>>> INCOMING_PROJECTILES = registerMemoryType("incoming_projectiles");

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id, Codec<T> codec) {
		return MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.of(codec)));
	}

	private static <T> RegistryObject<MemoryModuleType<T>> registerMemoryType(String id) {
		return MEMORIES.register(id, () -> new MemoryModuleType<T>(Optional.empty()));
	}
}
