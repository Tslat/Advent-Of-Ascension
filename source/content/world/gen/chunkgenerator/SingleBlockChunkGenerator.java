package net.tslat.aoa3.content.world.gen.chunkgenerator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SingleBlockChunkGenerator extends ChunkGenerator {
	public static final Codec<SingleBlockChunkGenerator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BlockState.CODEC.fieldOf("block").forGetter(instance -> instance.block),
			Biome.CODEC.fieldOf("biome").forGetter(instance -> instance.biome)
	).apply(codec, SingleBlockChunkGenerator::new));

	private final BlockState block;
	private final Holder<Biome> biome;

	public SingleBlockChunkGenerator(BlockState block, Holder<Biome> biome) {
		super(new FixedBiomeSource(biome));

		this.block = block;
		this.biome = biome;
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}

	@Override
	public void applyCarvers(WorldGenRegion region, long seed, RandomState random, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carvingStep) {}

	@Override
	public void buildSurface(WorldGenRegion region, StructureManager structureManager, RandomState random, ChunkAccess chunkAccess) {}

	@Override
	public void spawnOriginalMobs(WorldGenRegion region) {}

	public int getMinY() {
		return 0;
	}

	public int getGenDepth() {
		return 384;
	}

	public int getSeaLevel() {
		return -63;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState random, StructureManager structureManager, ChunkAccess chunkAccess) {
		if (this.block.getBlock() != Blocks.AIR) {
			Heightmap oceanFloorHeightmap = chunkAccess.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
			Heightmap worldgenHeightMap = chunkAccess.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					for (int y = chunkAccess.getMinBuildHeight(); y <= chunkAccess.getMaxBuildHeight(); y++) {
						chunkAccess.setBlockState(pos.set(x, y, z), this.block, false);
						oceanFloorHeightmap.update(x, y, z, this.block);
						worldgenHeightMap.update(x, y, z, this.block);
					}
				}
			}
		}

		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getBaseHeight(int posX, int posZ, Heightmap.Types heightmapType, LevelHeightAccessor heightAccessor, RandomState random) {
		return this.block.isAir() ? 0 : getGenDepth() - getMinY();
	}

	@Override
	public NoiseColumn getBaseColumn(int posX, int posZ, LevelHeightAccessor heightAccessor, RandomState random) {
		BlockState[] columnData = new BlockState[getGenDepth()];

		Arrays.fill(columnData, this.block);

		return new NoiseColumn(getMinY(), columnData);
	}

	@Override
	public void addDebugScreenInfo(List<String> debugLines, RandomState random, BlockPos pos) {}
}
