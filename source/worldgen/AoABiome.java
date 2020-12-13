package net.tslat.aoa3.worldgen;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;

public abstract class AoABiome extends Biome {
	protected AoABiome(Builder biomeBuilder) {
		super(biomeBuilder);
	}

	public abstract BiomeDictionary.Type[] getBiomeTypes();

	public void generateStructures(IWorld world, BiomeManager biomeManager, IChunk chunk, ChunkGenerator<?> chunkGenerator, TemplateManager templateManager) {}

	public void generateStructuredChunk(WorldGenRegion world, SharedSeedRandom rand, IChunk chunk, int startX, int startZ) {}
}
