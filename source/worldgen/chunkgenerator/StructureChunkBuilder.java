package net.tslat.aoa3.worldgen.chunkgenerator;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.tslat.aoa3.worldgen.AoABiome;

public class StructureChunkBuilder extends ChunkGenerator<GenerationSettings> {
	public StructureChunkBuilder(IWorld world, BiomeProvider provider, GenerationSettings genSettings) {
		super(world, provider, genSettings);
	}

	@Override
	public void makeBase(IWorld world, IChunk chunk) {}

	@Override
	public void generateSurface(WorldGenRegion world, IChunk chunk) {
		int posX = world.getMainChunkX() * 16;
		int posZ = world.getMainChunkZ() * 16;
		Biome biome = world.getBiome(new BlockPos(posX, 0, posZ));
		SharedSeedRandom rand = new SharedSeedRandom();

		rand.setBaseChunkSeed(chunk.getPos().x, chunk.getPos().z);

		if (biome instanceof AoABiome)
			((AoABiome)biome).generateStructuredChunk(world, rand, chunk, posX, posZ);
	}

	@Override
	public void spawnMobs(WorldGenRegion region) {
		int chunkX = region.getMainChunkX();
		int chunkZ = region.getMainChunkZ();
		Biome biome = region.getBiome((new ChunkPos(chunkX, chunkZ)).asBlockPos());
		SharedSeedRandom seededRandom = new SharedSeedRandom();

		seededRandom.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, chunkX, chunkZ, seededRandom);
	}

	@Override
	public void generateStructures(BiomeManager biomeManager, IChunk chunk, ChunkGenerator<?> chunkGenerator, TemplateManager templateManager) {
		super.generateStructures(biomeManager, chunk, chunkGenerator, templateManager);

		Biome centerBiome = biomeManager.getBiome(new BlockPos(chunk.getPos().getXStart() + 8, 0, chunk.getPos().getZStart() + 8));

		if (centerBiome instanceof AoABiome)
			((AoABiome)centerBiome).generateStructures(world, biomeManager, chunk, chunkGenerator, templateManager);
	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return world.getChunk(x >> 4, z >> 4, ChunkStatus.SURFACE).getTopBlockY(heightmapType, x & 15, z & 15) + 1;
	}

	@Override
	public int getGroundHeight() {
		return 0;
	}
}
