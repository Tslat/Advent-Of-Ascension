package net.tslat.aoa3.dimension.runandor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BiomeRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenRunandor implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = BiomeRegister.RUNANDOR;

	private int x;
	private int y;
	private int z;
	private int count;

	protected ChunkGenRunandor(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);

		count = rand.nextInt(26);
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		if (count > 25)
			count = 0;

		setBlocksInChunk();

		Chunk chunk = new Chunk(world, primer, chunkX, chunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		count++;
		return chunk;
	}

	private void setBlocksInChunk() {
		setAllBlocksBetweenYCoords(0, 11 + count, BlockRegister.RUNIC_STONE.getDefaultState());
		setAllBlocksBetweenYCoords(11 + count, 11 + count, 11 + count <= 14 ? BlockRegister.RUNIC_STONE.getDefaultState() : BlockRegister.RUNIC_GRASS.getDefaultState());

		if (count < 4) {
			for (x = 0; x <= 15; x++) {
				for (y = 12 + count; y <= 15; y++) {
					for (z = 0; z <= 15; z++) {
						primer.setBlockState(x, y, z, Blocks.WATER.getDefaultState());
					}
				}
			}
		}

		for (int x = 0; x <= 15; x++) {
			for (int y = 0; y <= 5; y++) {
				for (int z = 0; z <= 15; z++) {
					if (y <= 2)
						primer.setBlockState(x, y, z, BlockRegister.DIMENSIONAL_FABRIC.getDefaultState());
				}
			}
		}
	}

	private void setAllBlocksBetweenYCoords(final int lowerY, final int upperY, IBlockState block) {
		setAllBlocksInRegion(0, lowerY, 0, 15, upperY, 15, block);
	}

	private void setAllBlocksInRegion(final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ, final IBlockState block) {
		for (x = lowerX; x <= upperX; x++) {
			for (y = lowerY; y <= upperY; y++) {
				for (z = lowerZ; z <= upperZ; z++) {
					primer.setBlockState(x, y, z, block);
				}
			}
		}
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		this.rand.setSeed(world.getSeed());
		long a = this.rand.nextLong() / 2L * 2L + 1L;
		long b = this.rand.nextLong() / 2L * 2L + 1L;
		final int baseX = chunkX * 16 + 1;
		final int baseZ = chunkZ * 16 + 1;
		int x;
		int z;
		int y;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		BlockPos basePos = new BlockPos(baseX, 0, baseZ);
		Biome biome = world.getBiome(basePos);

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());

		if (ConfigurationUtil.StructureConfig.runandor.clunkheadArenaSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.runandor.clunkheadArenaSpawnChance) == 0) {
			x = baseX + rand.nextInt(6);
			z = baseZ + rand.nextInt(10);
			y = world.getHeight(x + 12, z + 10);

			if (world.getBlockState(pos.setPos(x + 12, y - 1, z + 10)) == biome.topBlock)
				StructuresHandler.generateStructure("ClunkheadArena", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.runandor.runicTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.runandor.runicTowerSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 5, z + 5);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)) == biome.topBlock)
				StructuresHandler.generateStructure("RunicTower", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.runandor.spectralCageSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.runandor.spectralCageSpawnChance) == 0) {
			x = baseX + rand.nextInt(13);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 8, z + 7);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 7)) == biome.topBlock)
				StructuresHandler.generateStructure("SpectralCage", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.runandor.runeRandomisationStationSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.runandor.runeRandomisationStationSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 7, z + 7);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)) == biome.topBlock)
				StructuresHandler.generateStructure("RuneRandomisationStation", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.runandor.runeTemplarBunkerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.runandor.runeTemplarBunkerSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 3;

			if (world.getHeight(x + 7, z + 7) > 11)
				StructuresHandler.generateStructure("RuneTemplarBunker", world, rand, pos.setPos(x, y, z));
		}

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());
		biome.decorate(world, rand, basePos);
		WorldEntitySpawner.performWorldGenSpawning(world, biome, baseX + 8, baseZ + 8, 16, 16, rand);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return this.world.getBiome(pos).getSpawnableList(creatureType);
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}
