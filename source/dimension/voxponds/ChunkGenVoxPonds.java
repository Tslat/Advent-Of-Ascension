package net.tslat.aoa3.dimension.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BiomeRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenVoxPonds implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = BiomeRegister.biomeVoxPonds;

	private int x;
	private int y;
	private int z;

	protected ChunkGenVoxPonds(World world) {
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
		IBlockState toxicStone = BlockRegister.stoneToxic.getDefaultState();
		IBlockState toxicWaste = BlockRegister.toxicWaste.getDefaultState();

		setAllBlocksBetweenYCoords(0, 15, toxicStone);
		setAllBlocksBetweenYCoords(16, 17, Blocks.WATER.getDefaultState());
		setAllBlocksBetweenYCoords(32, 35, toxicStone);

		if (rand.nextBoolean()) {
			setAllBlocksBetweenYCoords(36, 36,toxicStone);
			setAllBlocksBetweenYCoords(37, 37, toxicWaste);

			for (x = 0; x <= 15; x++) {
				for (z = 0; z <= 15; z++) {
					if (rand.nextBoolean())
						primer.setBlockState(x, 36, z, toxicWaste);
				}
			}
		}
		else {
			setAllBlocksBetweenYCoords(36, 37, BlockRegister.dirtToxic.getDefaultState());
			setAllBlocksBetweenYCoords(38, 38, BlockRegister.grassToxic.getDefaultState());

			for (x = 0; x <= 15; x++) {
				for (z = 0; z <= 15; z++) {
					if (rand.nextBoolean())
						primer.setBlockState(x, 36, z, toxicStone);
				}
			}
		}

		for (x = 0; x <= 15; x++) {
			for (y = 0; y <= 2; y++) {
				for (z = 0; z <= 15; z++) {
					primer.setBlockState(x, y, z, BlockRegister.dimensionalFabric.getDefaultState());
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

		if (rand.nextInt(6) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);

			if (rand.nextBoolean()) {
				StructuresHandler.generateStructure("HangingDegradedLampPost", world, rand, pos.setPos(x, 27, z));
			}
			else {
				StructuresHandler.generateStructure("DegradedLampPost", world, rand, pos.setPos(x, 16, z));
			}
		}

		if (ConfigurationUtil.StructureConfig.voxPonds.enigmaStationSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.enigmaStationSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 18;

			if (world.getBlockState(pos.setPos(x + 6, y, z + 6)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("EnigmaStation", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.controlTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.controlTowerSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ControlTower", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.cellTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.cellTowerSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("CellTower", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.observationTowerSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.observationTowerSpawnChance) == 0) {
			x = baseX + rand.nextInt(14);
			z = baseZ + rand.nextInt(10);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 11)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ObservationTower", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.voxBuildingSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.voxBuildingSpawnChance) == 0) {
			x = baseX;
			z = baseZ;
			y = 39;

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)) == biome.topBlock) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.nextInt(4)) {
					case 0:
						structure = StructuresHandler.getStructure("DestroyedStore1");
						break;
					case 1:
						structure = StructuresHandler.getStructure("DestroyedStore2");
						break;
					case 2:
						structure = StructuresHandler.getStructure("ExoidPlatform");
						break;
					case 3:
						structure = StructuresHandler.getStructure("VoxxulonBeacon");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand, pos.setPos(x, y, z));
			}
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.nightwingIslandSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.nightwingIslandSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 18;

			if (world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("NightwingIsland", world, rand, pos.setPos(x, y, z));
		}
		else if (ConfigurationUtil.StructureConfig.voxPonds.voxLottoOutpostSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.voxLottoOutpostSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("VoxLottoOutpost", world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.StructureConfig.voxPonds.poisonRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.voxPonds.poisonRuneShrineSpawnChance) == 0) {
			x = baseX + rand.nextInt(16);
			z = baseZ + rand.nextInt(16);
			y = 47;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("PoisonRuneShrine", world, rand, pos.setPos(x, y, z));
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
