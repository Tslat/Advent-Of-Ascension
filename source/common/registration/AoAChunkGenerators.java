package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.world.gen.chunkgenerator.SingleBlockChunkGenerator;

public final class AoAChunkGenerators {
	public static void init() {}

	public static final RegistryObject<Codec<SingleBlockChunkGenerator>> SINGLE_BLOCK = register("single_block", SingleBlockChunkGenerator.CODEC);

	private static <T extends ChunkGenerator> RegistryObject<Codec<T>> register(String id, Codec<T> codec) {
		return AoARegistries.CHUNK_GENERATORS.register(id, () -> codec);
	}
}
