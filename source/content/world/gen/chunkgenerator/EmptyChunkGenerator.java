package net.tslat.aoa3.content.world.gen.chunkgenerator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
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

public class EmptyChunkGenerator extends ChunkGenerator {
	public static final Codec<EmptyChunkGenerator> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			BiomeSource.CODEC.fieldOf("biome_source").forGetter(p_255584_ -> p_255584_.biomeSource)
	).apply(codec, EmptyChunkGenerator::new));
	private static final BlockState AIR = Blocks.AIR.defaultBlockState();

	public EmptyChunkGenerator(BiomeSource biomeSource) {
		super(biomeSource);
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
		return 0;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState random, StructureManager structureManager, ChunkAccess chunkAccess) {
		return CompletableFuture.completedFuture(chunkAccess);
	}

	@Override
	public int getBaseHeight(int posX, int posZ, Heightmap.Types heightmapType, LevelHeightAccessor heightAccessor, RandomState random) {
		return heightAccessor.getMinBuildHeight();
	}

	@Override
	public NoiseColumn getBaseColumn(int posX, int posZ, LevelHeightAccessor heightAccessor, RandomState random) {
		final BlockState[] columnData = new BlockState[heightAccessor.getHeight()];

		Arrays.fill(columnData, AIR);

		return new NoiseColumn(heightAccessor.getMinBuildHeight(), columnData);
	}

	@Override
	public void addDebugScreenInfo(List<String> debugLines, RandomState random, BlockPos pos) {}
}
