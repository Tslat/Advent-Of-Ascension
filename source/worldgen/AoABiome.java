package net.tslat.aoa3.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;

import java.util.function.BiConsumer;

public abstract class AoABiome extends Biome {
	protected AoABiome(Builder biomeBuilder) {
		super(biomeBuilder);
	}

	public abstract BiomeDictionary.Type[] getBiomeTypes();

	public void generateStructures(IWorld world, BiomeManager biomeManager, IChunk chunk, ChunkGenerator<?> chunkGenerator, TemplateManager templateManager) {}

	public void generateStructuredChunk(IWorld world, ChunkPrimer chunk, SharedSeedRandom rand, BiConsumer<BlockPos, BlockState> blockPlacer) {}
}
