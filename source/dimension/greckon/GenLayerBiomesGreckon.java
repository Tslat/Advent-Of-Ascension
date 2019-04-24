package net.nevermine.dimension.greckon;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.nevermine.dimension.DimensionOrganizer;

public class GenLayerBiomesGreckon extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {DimensionOrganizer.Greckon, DimensionOrganizer.GreckonSkull, DimensionOrganizer.GreckonForest,};

	public GenLayerBiomesGreckon(long seed) {
		super(seed);
	}

	public GenLayerBiomesGreckon(long seed, GenLayer genlayer) {
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