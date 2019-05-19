package net.tslat.aoa3.dimension.lunalus;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenLunalus implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = DimensionRegister.biomeLunalus;

	private int x;
	private int y;
	private int z;

	protected ChunkGenLunalus(World world) {
		this.world = world;
		this.rand = new Random(world.getSeed());
		this.world.setSeaLevel(0);
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		this.primer = new ChunkPrimer();

		generateIslands();

		Chunk chunk = new Chunk(world, primer, chunkX, chunkZ);
		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateIslands() {

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

		if (rand.nextBoolean()) {
			x = baseX + rand.nextInt(4);
			z = baseZ + rand.nextInt(4);
			y = 36 + rand.nextInt(9);

			if (rand.nextBoolean()) {
				StructuresHandler.generateStructure("LunarIsland1", world, rand, new BlockPos(x, y, z));
			}
			else {
				StructuresHandler.generateStructure("LunarIsland2", world, rand, new BlockPos(x, y, z));
			}
		}

		if (rand.nextBoolean()) {
			x = baseX + rand.nextInt(4);
			z = baseZ + rand.nextInt(4);
			y = 61 + rand.nextInt(9);

			if (rand.nextBoolean()) {
				StructuresHandler.generateStructure("LunarIsland1", world, rand, new BlockPos(x, y, z));
			}
			else {
				StructuresHandler.generateStructure("LunarIsland2", world, rand, new BlockPos(x, y, z));
			}
		}


		if (ConfigurationUtil.StructureConfig.lunalus.lunarVillageSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarVillageSpawnChance) == 0) {
			x = baseX;
			z = baseZ;
			y = rand.nextInt(4) + 4;

			StructuresHandler.generateStructure("LunarAtom", world, rand, pos.setPos(x + 60, y, z + 14));
			StructuresHandler.generateStructure("LunarLottoPlatform", world, rand, pos.setPos(x + 85, y, z + 29));
			StructuresHandler.generateStructure("LunarFoodMarket", world, rand, pos.setPos(x + 35, y, z));
			StructuresHandler.generateStructure("LunarHerbalHouse", world, rand, pos.setPos(x + 15, y, z + 5));
			StructuresHandler.generateStructure("SpellbinderHouse", world, rand, pos.setPos(x + 47, y + 22, z + 22));
			StructuresHandler.generateStructure("LunaradeStand", world, rand, pos.setPos(x + 28, y + 22,  z + 22));
			StructuresHandler.generateStructure("LunarBank", world, rand, pos.setPos(x, y + 5, z + 12));
			StructuresHandler.generateStructure("LunarFountain", world, rand, pos.setPos(x + 30, y, z + 14));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.spaceArenaSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.spaceArenaSpawnChance) == 0) {
			x = baseX + rand.nextInt(9);
			z = baseZ + rand.nextInt(8);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 10, y + 4, z + 11)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("SpaceArena", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.lunarCreationPlatformSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarCreationPlatformSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 4, y + 2, z + 4)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarCreationPlatform", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.zargPlanetoidSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.zargPlanetoidSpawnChance) == 0) {
			x = baseX + rand.nextInt(7);
			z = baseZ + rand.nextInt(5);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 11, y + 12, z + 12)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ZargPlanetoid", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.lunarMazeSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarMazeSpawnChance) == 0) {
			x = baseX + rand.nextInt(12);
			z = baseZ + rand.nextInt(12);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 9, y + 7, z + 9)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarMaze", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.lunarPrisonSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarPrisonSpawnChance) == 0) {
			x = baseX;
			z = baseZ + rand.nextInt(9);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 18, y + 5, z + 10)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarPrison", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.lunarGardenSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarGardenSpawnChance) == 0) {
			x = baseX + rand.nextInt(13);
			z = baseZ + rand.nextInt(13);
			y = rand.nextInt(3) + 15;

			if (world.getBlockState(pos.setPos(x + 8, y + 7, z + 8)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarGarden", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.lunalus.observersEyeSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarGardenSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 90 + rand.nextInt(10);

			StructuresHandler.generateStructure("ObserversEye", world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.StructureConfig.lunalus.lunarRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.lunalus.lunarRuneShrineSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = rand.nextBoolean() ? rand.nextInt(20) + 70 : rand.nextInt(15) + 6;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarRuneShrine", world, rand, pos.setPos(x, y, z));
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
