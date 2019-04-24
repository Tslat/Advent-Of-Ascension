package net.nevermine.dimension.gardencia;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.nevermine.dimension.DimensionOrganizer;

public class GenLayerBiomesGardencia extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {DimensionOrganizer.Gardencia, DimensionOrganizer.GardenciaMarsh, DimensionOrganizer.GardenciaFungal,};

	public GenLayerBiomesGardencia(long seed) {
		super(seed);
	}

	public GenLayerBiomesGardencia(long seed, GenLayer genlayer) {
		super(seed);
		parent = genlayer;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}