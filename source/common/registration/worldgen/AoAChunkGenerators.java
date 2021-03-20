package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.worldgen.chunkgenerator.AsteroidsChunkGenerator;
import net.tslat.aoa3.worldgen.chunkgenerator.CavernsChunkGenerator;

public final class AoAChunkGenerators {
	public static void postInit() {
		Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(AdventOfAscension.MOD_ID, "caverns"), CavernsChunkGenerator.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(AdventOfAscension.MOD_ID, "asteroids"), AsteroidsChunkGenerator.CODEC);
	}
}
