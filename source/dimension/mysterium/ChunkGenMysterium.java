package net.tslat.aoa3.dimension.mysterium;

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

public class ChunkGenMysterium implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = BiomeRegister.biomeMysterium;

	private int x;
	private int y;
	private int z;

	protected ChunkGenMysterium(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		setBlocksInChunk();

		Chunk chunk = new Chunk(world, primer, chunkX, chunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void setBlocksInChunk() {
		boolean isHole = rand.nextInt(3) == 0;
		IBlockState stone = BlockRegister.stoneMysterium.getDefaultState();
		IBlockState dirt = BlockRegister.dirtMysterium.getDefaultState();
		IBlockState grass = BlockRegister.grassMysterium.getDefaultState();

		if (isHole) {
			setAllBlocksInRegion(0, 1, 0, 15, 10, 15, stone);
			setAllBlocksInRegion(0, 11, 0, 15, 12, 15, Blocks.WATER.getDefaultState());
		}
		else {
			setAllBlocksInRegion(0, 1, 0, 15, 20, 15, stone);
			setAllBlocksInRegion(0, 21, 0, 15, 21, 15, dirt);

			for (int x = 0; x <= 15; x++) {
				for (int z = 0; z <= 15; z++) {
					if (x == 0 || x == 15 || z == 0 || z == 15) {
						primer.setBlockState(x, 22, z, grass);
					}
					else {
						primer.setBlockState(x, 22, z, dirt);
					}
				}
			}

			for (int x = 1; x <= 14; x++) {
				for (int z = 1; z <= 14; z++) {
					if (x == 1 || x == 14 || z == 1 || z == 14) {
						primer.setBlockState(x, 23, z, grass);
					}
					else {
						primer.setBlockState(x, 23, z, dirt);
					}
				}
			}

			setAllBlocksInRegion(2, 24, 2, 13, 24, 13, grass);
		}

		for (int x = 0; x <= 15; x++) {
			for (int y = 0; y <= 5; y++) {
				for (int z = 0; z <= 15; z++) {
					if (y <= 2)
						primer.setBlockState(x, y, z, BlockRegister.dimensionalFabric.getDefaultState());
				}
			}
		}
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

		if (ConfigurationUtil.StructureConfig.mysterium.hauntedCastleSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.hauntedCastleSpawnChance) == 0) {
			x = baseX;
			z = baseZ;
			y = world.getHeight(x + 19, z + 14);

			if (world.getBlockState(pos.setPos(x + 19, y -1, z + 14)) == biome.topBlock)
				StructuresHandler.generateStructure("HauntedCastle", world, rand, pos.setPos(x, y - 1, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.fungshroomSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.fungshroomSpawnChance) == 0) {
			x = baseX + rand.nextInt(13);
			z = baseZ + rand.nextInt(13);
			y = world.getHeight(x + 8, z + 8);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)) == biome.topBlock)
				StructuresHandler.generateStructure("Fungshroom", world, rand, pos.setPos(x, y - 1, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.gorbVillageSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.gorbVillageSpawnChance) == 0) {
			x = baseX;
			z = baseZ + rand.nextInt(10);
			y = world.getHeight(x + 22, z + 10);

			if (world.getBlockState(pos.setPos(x + 22, y - 1, z + 10)) == biome.topBlock)
				StructuresHandler.generateStructure("GorbVillage", world, rand, pos.setPos(x, y - 1, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.mysticLottoShroomSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.mysticLottoShroomSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 4, z + 4);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)) == biome.topBlock)
				StructuresHandler.generateStructure("MysticLottoShroom", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.mushroomSpiderCaveSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.mushroomSpiderCaveSpawnChance) == 0) {
			x = baseX + rand.nextInt(15);
			z = baseZ + rand.nextInt(15);
			y = world.getHeight(x + 7, z + 7);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)) == biome.topBlock)
				StructuresHandler.generateStructure("MushroomSpiderCave", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.mysticPortalPlatformSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.mysticPortalPlatformSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 6, z + 4);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 4)) == biome.topBlock)
			StructuresHandler.generateStructure("MysticPortalPlatform", world, rand, pos.setPos(x, y - 1, z));
		}
		else if (ConfigurationUtil.StructureConfig.mysterium.runicArenaSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.runicArenaSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 7, z + 7);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)) == biome.topBlock)
				StructuresHandler.generateStructure("RunicArena", world, rand, pos.setPos(x, y , z));
		}

		if (ConfigurationUtil.StructureConfig.mysterium.distortionRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.mysterium.distortionRuneShrineSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = world.getHeight(x + 3, z + 3) + 15;

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("DistortionRuneShrine", world, rand, pos.setPos(x, y, z));
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
