package net.tslat.aoa3.dimension;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.tslat.aoa3.dimension.nether.NetherGen;
import net.tslat.aoa3.dimension.overworld.OverworldGen;

import java.util.Random;

public class WorldGen implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();

		if (dim == 0) {
			OverworldGen.generate(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
		}
		else if (dim == -1) {
			NetherGen.generate(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
		}
	}
}
