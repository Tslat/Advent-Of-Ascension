package net.tslat.aoa3.dimension.immortallis;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BiomeRegister;
import net.tslat.aoa3.structure.immortallis.ImmortallisDungeon;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenImmortallis implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = BiomeRegister.biomeImmortallis;

	protected ChunkGenImmortallis(World world) {
		this.world = world;
		this.world.setSeaLevel(0);
		this.rand = new Random(world.getSeed());
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.primer = new ChunkPrimer();
		this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

		Chunk chunk = new Chunk(world, primer, chunkX, chunkZ);
		byte[] biomeArray = chunk.getBiomeArray();
		byte biomeByte = (byte)Biome.getIdForBiome(biome);

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = biomeByte;
		}

		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		if (x != 0 || z != 0)
			return;

		new ImmortallisDungeon().generate(world, rand, new BlockPos(-15, 17, -40));
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
		return null;
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {}

	@Override
	public boolean isInsideStructure(World world, String name, BlockPos pos) {
		return false;
	}
}
