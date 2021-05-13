package net.tslat.aoa3.worldgen.chunkgenerator;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.tslat.aoa3.worldgen.AoABiome;

public class StructureChunkBuilder extends ChunkGenerator<GenerationSettings> {
	public StructureChunkBuilder(IWorld world, BiomeProvider provider, GenerationSettings genSettings) {
		super(world, provider, genSettings);
	}

	@Override
	public void makeBase(IWorld world, IChunk chunk) {
		SharedSeedRandom random = new SharedSeedRandom(this.seed);
		ChunkPrimer primer = (ChunkPrimer)chunk;
		Biome biome = world.getBiome(chunk.getPos().asBlockPos());

		primer.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		random.setBaseChunkSeed(chunk.getPos().x, chunk.getPos().z);

		Heightmap groundHeightmap = primer.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

		for (int x = 0; x < 4; x++) {
			for (int z = 0; z < 4; z++) {
				primer.getSection(15);

				for (int i = 0; i < 256; i += 15) {
					primer.getSection(i >> 4);
				}
			}
		}

		if (biome instanceof AoABiome) {
			((AoABiome)biome).generateStructuredChunk(world, primer, random, (pos, state) -> {
				BlockPos globalPos = new BlockPos(primer.getPos().asBlockPos().add(pos));
				ChunkSection chunkSection = primer.getSection(pos.getY() >> 4);

				if (state.getLightValue(primer, globalPos) != 0)
					primer.addLightPosition(globalPos);

				chunkSection.setBlockState(pos.getX(), pos.getY() & 15, pos.getZ(), state, false);
				groundHeightmap.update(pos.getX(), pos.getY(), pos.getZ(), state);
			});
		}
	}

	@Override
	public void generateSurface(WorldGenRegion world, IChunk chunk) {}

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
		if (world.getChunkProvider() instanceof ServerChunkProvider) {
			ServerChunkProvider provider = (ServerChunkProvider)world.getChunkProvider();
			ChunkHolder holder = provider.func_217213_a(new ChunkPos(new BlockPos(x, 0, z)).asLong());

			if (holder != null && ChunkHolder.getChunkStatusFromLevel(holder.getChunkLevel()).isAtLeast(ChunkStatus.SURFACE))
				return world.getChunk(x >> 4, z >> 4, ChunkStatus.SURFACE).getTopBlockY(heightmapType, x & 15, z & 15) + 1;
		}

		return 0;
	}

	@Override
	public int getGroundHeight() {
		return 0;
	}
}
