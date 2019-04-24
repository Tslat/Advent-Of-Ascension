package net.tslat.aoa3.dimension.lelyetia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenLelyetia implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = DimensionRegister.biomeLelyetia;

	private int x;
	private int y;
	private int z;

	protected ChunkGenLelyetia(World world) {
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
		if (rand.nextInt(11) > 7) {
			switch (rand.nextInt(7)) {
				case 0:
					genLandStrip(0, 55, 0, 15, 59, 0);
					genLandStrip(0, 55, 15, 15, 59, 15);
					genLandStrip(15, 55, 0, 15, 59, 15);
					genLandStrip(0, 55, 0, 0, 59, 15);
					primer.setBlockState(1, 59, 1, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(14, 59, 14, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(1, 59, 14, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(14, 59, 1, BlockRegister.grassLelyetia.getDefaultState());

					genLandStrip(1, 55, 1, 14, 58, 1);
					genLandStrip(1, 55, 14, 14, 58, 14);
					genLandStrip(14, 55, 1, 14, 58, 14);
					genLandStrip(1, 55, 1, 1, 58, 14);
					primer.setBlockState(2, 58, 2, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(13, 58, 13, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(2, 58, 13, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(13, 58, 2, BlockRegister.grassLelyetia.getDefaultState());

					genLandStrip(2, 55, 2, 13, 57, 2);
					genLandStrip(2, 55, 13, 13, 57, 13);
					genLandStrip(13, 55, 2, 13, 57, 13);
					genLandStrip(2, 55, 2, 2, 57, 13);
					primer.setBlockState(3, 57, 3, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(12, 57, 12, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(3, 57, 12, BlockRegister.grassLelyetia.getDefaultState());
					primer.setBlockState(12, 57, 3, BlockRegister.grassLelyetia.getDefaultState());

					genLandStrip(3, 55, 3, 12, 56, 3);
					genLandStrip(3, 55, 12, 12, 56, 12);
					genLandStrip(12, 55, 3, 12, 56, 12);
					genLandStrip(3, 55, 3, 3, 56, 12);
					break;
				case 1:
					genLandStrip(0, 55, 0, 15, 59, 1);
					genLandStrip(0, 55, 14, 15, 59, 15);
					genLandStrip(0, 55, 2, 1, 59, 13);
					genLandStrip(14, 55, 2, 15, 59, 13);

					genLandStrip(4, 55, 4, 11, 59, 5);
					genLandStrip(4, 55, 10, 11, 59, 11);
					genLandStrip(4, 55, 6, 5, 59, 9);
					genLandStrip(10, 55, 6, 11, 59, 9);
					break;
				case 2:
					genLandStrip(0, 55, 0, 15, 59, 0);
					genLandStrip(0, 55, 15, 15, 59, 15);
					genLandStrip(15, 55, 0, 15, 59, 15);
					genLandStrip(0, 55, 0, 0, 59, 15);

					genLandStrip(1, 55, 1, 6, 59, 6);
					genLandStrip(9, 55, 1, 14, 59, 6);
					genLandStrip(1, 55, 9, 6, 59, 14);
					genLandStrip(9, 55, 9, 14, 59, 14);
					break;
				case 3:
					genLandStrip(0, 55, 0, 15, 59, 1);
					genLandStrip(0, 55, 14, 15, 59, 15);
					genLandStrip(0, 55, 2, 1, 59, 13);
					genLandStrip(14, 55, 2, 15, 59, 13);

					genLandStrip(2, 55, 2, 13, 57, 3);
					genLandStrip(2, 55, 12, 13, 57, 13);
					genLandStrip(2, 55, 2, 3, 57, 11);
					genLandStrip(12, 55, 2, 13, 57, 11);

					genLandStrip(4, 55, 4, 11, 56, 5);
					genLandStrip(4, 55, 10, 11, 56, 11);
					genLandStrip(4, 55, 4, 5, 56, 9);
					genLandStrip(10, 55, 4, 11, 56, 9);
					break;
				case 4:
					genLandStrip(0, 55, 0, 15, 59, 4);
					genLandStrip(0, 55, 11, 15, 59, 15);
					genLandStrip(0, 55, 5, 4, 59, 10);
					genLandStrip(11, 55, 5, 15, 59, 10);

					genLandStrip(5, 55, 10, 6, 59, 10);
					genLandStrip(5, 55, 9, 5, 59, 9);

					genLandStrip(9, 55, 10, 10, 59, 10);
					genLandStrip(10, 55, 9, 10, 59, 9);

					genLandStrip(5, 55, 5, 5, 59, 6);
					genLandStrip(6, 55, 5, 6, 59, 5);

					genLandStrip(10, 55, 5, 10, 59, 6);
					genLandStrip(9, 55, 5, 9, 59, 5);
					break;
				case 5:
					genLandStrip(0, 55, 0, 15, 59, 1);
					genLandStrip(0, 55, 14, 15, 59, 15);
					genLandStrip(0, 55, 2, 1, 59, 13);
					genLandStrip(14, 55, 2, 15, 59, 13);

					setAllBlocksInRegion(7, 58, 2, 8, 58, 13, BlockRegister.stoneLelyetia.getDefaultState());
					setAllBlocksInRegion(2, 58, 7, 6, 58, 8, BlockRegister.stoneLelyetia.getDefaultState());
					setAllBlocksInRegion(9, 58, 7, 13, 58, 8, BlockRegister.stoneLelyetia.getDefaultState());
					setAllBlocksInRegion(7, 59, 2, 8, 59, 13, BlockRegister.grassLelyetia.getDefaultState());
					setAllBlocksInRegion(2, 59, 7, 6, 59, 8, BlockRegister.grassLelyetia.getDefaultState());
					setAllBlocksInRegion(9, 59, 7, 13, 59, 8, BlockRegister.grassLelyetia.getDefaultState());
					break;
				case 6:
					genLandStrip(0, 55, 0, 15, 59, 0);
					genLandStrip(0, 55, 15, 15, 59, 15);
					genLandStrip(15, 55, 0, 15, 59, 15);
					genLandStrip(0, 55, 0, 0, 59, 15);

					genLandStrip(7, 55, 1, 8, 59, 14);
					genLandStrip(1, 55, 7, 6, 59, 8);
					genLandStrip(9, 55, 7, 14, 59, 8);

					genLandStrip(1, 55, 1, 6, 58, 1);
					genLandStrip(1, 55, 6, 6, 58, 6);
					genLandStrip(1, 55, 2, 1, 58, 5);
					genLandStrip(6, 55, 2, 6, 58, 5);
					genLandStrip(2, 55, 2, 5, 57, 2);
					genLandStrip(2, 55, 5, 5, 57, 5);
					genLandStrip(2, 55, 3, 2, 57, 4);
					genLandStrip(5, 55, 3, 5, 57, 4);

					genLandStrip(1, 55, 9, 6, 58, 9);
					genLandStrip(1, 55, 14, 6, 58, 14);
					genLandStrip(1, 55, 10, 1, 58, 13);
					genLandStrip(6, 55, 10, 6, 58, 13);
					genLandStrip(2, 55, 10, 5, 57, 10);
					genLandStrip(2, 55, 13, 5, 57, 13);
					genLandStrip(2, 55, 11, 2, 57, 12);
					genLandStrip(5, 55, 11, 5, 57, 12);

					genLandStrip(9, 55, 1, 14, 58, 1);
					genLandStrip(9, 55, 6, 14, 58, 6);
					genLandStrip(9, 55, 2, 9, 58, 5);
					genLandStrip(14, 55, 2, 14, 58, 5);
					genLandStrip(10, 55, 2, 13, 57, 2);
					genLandStrip(10, 55, 5, 13, 57, 5);
					genLandStrip(10, 55, 3, 10, 57, 4);
					genLandStrip(13, 55, 3, 13, 57, 4);

					genLandStrip(9, 55, 9, 14, 58, 9);
					genLandStrip(9, 55, 14, 14, 58, 14);
					genLandStrip(9, 55, 10, 9, 58, 13);
					genLandStrip(14, 55, 10, 14, 58, 13);
					genLandStrip(10, 55, 10, 13, 57, 10);
					genLandStrip(10, 55, 13, 13, 57, 13);
					genLandStrip(10, 55, 11, 10, 57, 12);
					genLandStrip(13, 55, 11, 13, 57, 12);
					break;
			}
		}
		else {
			genLandStrip(0, 55, 0, 15, 59, 15);
		}
	}

	private void genLandStrip(final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ) {
		setAllBlocksInRegion(lowerX, lowerY, lowerZ, upperX, lowerY, upperZ, BlockRegister.grassLelyetiaDown.getDefaultState());
		setAllBlocksInRegion(lowerX, lowerY + 1, lowerZ, upperX, upperY - 1, upperZ, BlockRegister.stoneLelyetia.getDefaultState());
		setAllBlocksInRegion(lowerX, upperY, lowerZ, upperX, upperY, upperZ, BlockRegister.grassLelyetia.getDefaultState());
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

		if (ConfigurationUtil.spawnChanceLelyetianTower > 0 && rand.nextInt(ConfigurationUtil.spawnChanceLelyetianTower) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 60;

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)) == biome.topBlock && world.getBlockState(pos.setPos(x + 7, y, z + 7)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LelyetianTower", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceZhinxEnclave > 0 && rand.nextInt(ConfigurationUtil.spawnChanceZhinxEnclave) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 15;

			if (world.getBlockState(pos.setPos(x + 4, 55, z + 6)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("ZhinxEnclave", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceParaviteHive > 0 && rand.nextInt(ConfigurationUtil.spawnChanceParaviteHive) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 36;

			if (world.getBlockState(pos.setPos(x + 6, 55, z + 6)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("ParaviteHive", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceBoneyDungeon > 0 && rand.nextInt(ConfigurationUtil.spawnChanceBoneyDungeon) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 31;

			StructuresHandler.generateStructure("BoneyDungeon", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.spawnChanceGrawPillar > 0 && rand.nextInt(ConfigurationUtil.spawnChanceGrawPillar) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 60;

			if (world.getBlockState(pos.setPos(x + 2, y - 1, z + 2)) == biome.topBlock && world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("GrawPillar", world, rand, pos.setPos(x, y, z));
		}

		this.rand.setSeed(chunkX * a + chunkZ * b ^ this.world.getSeed());
		biome.decorate(world, rand, basePos);
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
