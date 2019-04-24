package net.nevermine.dimension.candyland;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.nevermine.dimension.DimensionOrganizer;

public class GenLayerBiomesCandyland extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {DimensionOrganizer.Candyland, DimensionOrganizer.CandylandMarshmallow, DimensionOrganizer.CandylandChocolate, DimensionOrganizer.CandylandRock,};

	public GenLayerBiomesCandyland(long seed) {
		super(seed);
	}

	public GenLayerBiomesCandyland(long seed, GenLayer genlayer) {
		super(seed);
		this.parent = genlayer;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}