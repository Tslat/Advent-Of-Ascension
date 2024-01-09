package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.content.world.gen.chunkgenerator.EmptyChunkGenerator;

public final class AoAChunkGenerators {
	public static void init() {}

	public static final DeferredHolder<Codec<? extends ChunkGenerator>, Codec<EmptyChunkGenerator>> EMPTY = register("empty", EmptyChunkGenerator.CODEC);

	private static <T extends ChunkGenerator> DeferredHolder<Codec<? extends ChunkGenerator>, Codec<T>> register(String id, Codec<T> codec) {
		return AoARegistries.CHUNK_GENERATORS.register(id, () -> codec);
	}
}
