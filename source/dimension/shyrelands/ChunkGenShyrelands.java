package net.tslat.aoa3.dimension.shyrelands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGenShyrelands implements IChunkGenerator {
	private final World world;

	private final Random rand;

	private ChunkPrimer primer;

	private final Biome biome = DimensionRegister.biomeShyrelands;

	private int x;
	private int y;
	private int z;
	
	private final IBlockState whiteBricks = BlockRegister.bricksShyreWhite.getDefaultState();
	private final IBlockState yellowBricks = BlockRegister.bricksShyreYellow.getDefaultState();
	private final IBlockState glass = BlockRegister.glassShyre.getDefaultState();
	private final IBlockState cloud = BlockRegister.shyreCloud.getDefaultState();

	protected ChunkGenShyrelands(World world) {
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

		correctCloudBlocks(chunk);

		byte[] biomeArray = chunk.getBiomeArray();

		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte)Biome.getIdForBiome(biome);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void setBlocksInChunk() {
		for (x = 0; x <= 15; x++) {
			for (z = 0; z <= 15; z++) {
				if (rand.nextInt(3) == 0)
					primer.setBlockState(x, 64, z, Blocks.BARRIER.getDefaultState());
			}
		}

		setAllBlocksInRegion(0, 0, 0, 15, 29, 15, BlockRegister.stoneShyrelands.getDefaultState());
		setAllBlocksInRegion(1, 30, 1, 14, 30, 14, BlockRegister.grassShyrelands.getDefaultState());

		setAllBlocksInRegion(0, 30, 0, 15, 30, 0, whiteBricks);
		setAllBlocksInRegion(0, 30, 15, 15, 30, 15, whiteBricks);
		setAllBlocksInRegion(0, 30, 1, 0, 30, 14, whiteBricks);
		setAllBlocksInRegion(15, 30, 1, 15, 30, 14, whiteBricks);

		setAllBlocksInRegion(0, 46, 0, 0, 54, 0, whiteBricks);
		setAllBlocksInRegion(0, 46, 15, 0, 54, 15, whiteBricks);
		setAllBlocksInRegion(15, 46, 0, 15, 54, 0, whiteBricks);
		setAllBlocksInRegion(15, 46, 15, 15, 54, 15, whiteBricks);

		setAllBlocksInRegion(0, 55, 0, 15, 55, 0, whiteBricks);
		setAllBlocksInRegion(0, 55, 15, 15, 55, 15, whiteBricks);
		setAllBlocksInRegion(0, 55, 1, 0, 55, 14, whiteBricks);
		setAllBlocksInRegion(15, 55, 1, 15, 55, 14, whiteBricks);

		setAllBlocksInRegion(1, 46, 0, 14, 54, 0, glass);
		setAllBlocksInRegion(1, 46, 15, 14, 54, 15, glass);
		setAllBlocksInRegion(0, 46, 1, 0, 54, 14, glass);
		setAllBlocksInRegion(15, 46, 1, 15, 54, 14, glass);

		setAllBlocksInRegion(0, 45, 0, 15, 45, 0, yellowBricks);
		setAllBlocksInRegion(0, 45, 15, 15, 45, 15, yellowBricks);
		setAllBlocksInRegion(0, 45, 1, 0, 45, 14, yellowBricks);
		setAllBlocksInRegion(15, 45, 1, 15, 45, 14, yellowBricks);

		setAllBlocksInRegion(0, 31, 0, 3, 44, 0, yellowBricks);
		setAllBlocksInRegion(12, 31, 0, 14, 44, 0, yellowBricks);
		setAllBlocksInRegion(0, 31, 15, 3, 44, 15, yellowBricks);
		setAllBlocksInRegion(12, 31, 15, 15, 44, 15, yellowBricks);
		setAllBlocksInRegion(0, 31, 0, 0, 44, 3, yellowBricks);
		setAllBlocksInRegion(0, 31, 12, 0, 44, 14, yellowBricks);
		setAllBlocksInRegion(15, 31, 0, 15, 44, 3, yellowBricks);
		setAllBlocksInRegion(15, 31, 12, 15, 44, 14, yellowBricks);

		setAllBlocksInRegion(4, 31, 0, 4, 44, 0, whiteBricks);
		setAllBlocksInRegion(11, 31, 0, 11, 44, 0, whiteBricks);
		setAllBlocksInRegion(4, 31, 15, 4, 44, 15, whiteBricks);
		setAllBlocksInRegion(11, 31, 15, 11, 44, 15, whiteBricks);
		setAllBlocksInRegion(0, 31, 4, 0, 44, 4, whiteBricks);
		setAllBlocksInRegion(0, 31, 11, 0, 44, 11, whiteBricks);
		setAllBlocksInRegion(15, 31, 4, 15, 44, 4, whiteBricks);
		setAllBlocksInRegion(15, 31, 11, 15, 44, 11, whiteBricks);
		setAllBlocksInRegion(5, 44, 0, 10, 44, 0, whiteBricks);
		setAllBlocksInRegion(5, 44, 15, 10, 44, 15, whiteBricks);
		setAllBlocksInRegion(0, 44, 5, 0, 44, 10, whiteBricks);
		setAllBlocksInRegion(15, 44, 5, 15, 44, 10, whiteBricks);

		buildRandomDoor(EnumFacing.NORTH);
		buildRandomDoor(EnumFacing.SOUTH);
		buildRandomDoor(EnumFacing.EAST);
		buildRandomDoor(EnumFacing.WEST);

		for (x = 0; x <= 15; x++) {
			for (y = 31; y <= 45; y++) {
				if (rand.nextInt(12) == 0 && primer.getBlockState(x, y, 0).getBlock() == BlockRegister.bricksShyreYellow)
					primer.setBlockState(x, y, 0, glass);

				if (rand.nextInt(12) == 0 && primer.getBlockState(x, y, 0).getBlock() == BlockRegister.bricksShyreYellow)
					primer.setBlockState(x, y, 15, glass);
			}
		}

		for (z = 0; z <= 15; z++) {
			for (y = 31; y <= 45; y++) {
				if (rand.nextInt(12) == 0 && primer.getBlockState(0, y, z).getBlock() == BlockRegister.bricksShyreYellow)
					primer.setBlockState(0, y, z, glass);

				if (rand.nextInt(12) == 0 && primer.getBlockState(15, y, z).getBlock() == BlockRegister.bricksShyreYellow)
					primer.setBlockState(15, y, z, glass);
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

	private void buildRandomDoor(EnumFacing side) {
		switch (side) {
			case NORTH:
				z = 0;
			case SOUTH:
				if (side == EnumFacing.SOUTH)
					z = 15;

				switch (rand.nextInt(5)) {
					case 0:
						primer.setBlockState(5, 43, z, glass);
						primer.setBlockState(6, 43, z, glass);
						primer.setBlockState(7, 43, z, whiteBricks);
						primer.setBlockState(8, 43, z, whiteBricks);
						primer.setBlockState(9, 43, z, glass);
						primer.setBlockState(10, 43, z, glass);
						primer.setBlockState(5, 42, z, glass);
						primer.setBlockState(6, 42, z, glass);
						primer.setBlockState(7, 42, z, whiteBricks);
						primer.setBlockState(8, 42, z, whiteBricks);
						primer.setBlockState(9, 42, z, glass);
						primer.setBlockState(10, 42, z, glass);
						primer.setBlockState(5, 41, z, glass);
						primer.setBlockState(6, 41, z, whiteBricks);
						primer.setBlockState(7, 41, z, whiteBricks);
						primer.setBlockState(8, 41, z, whiteBricks);
						primer.setBlockState(9, 41, z, whiteBricks);
						primer.setBlockState(10, 41, z, glass);
						primer.setBlockState(5, 40, z, whiteBricks);
						primer.setBlockState(6, 40, z, whiteBricks);
						primer.setBlockState(9, 40, z, whiteBricks);
						primer.setBlockState(10, 40, z, whiteBricks);
						break;
					case 1:
						primer.setBlockState(5, 43, z, glass);
						primer.setBlockState(6, 43, z, glass);
						primer.setBlockState(7, 43, z, whiteBricks);
						primer.setBlockState(8, 43, z, whiteBricks);
						primer.setBlockState(9, 43, z, glass);
						primer.setBlockState(10, 43, z, glass);
						primer.setBlockState(5, 42, z, whiteBricks);
						primer.setBlockState(6, 42, z, whiteBricks);
						primer.setBlockState(7, 42, z, whiteBricks);
						primer.setBlockState(8, 42, z, whiteBricks);
						primer.setBlockState(9, 42, z, whiteBricks);
						primer.setBlockState(10, 42, z, whiteBricks);
						primer.setBlockState(5, 41, z, glass);
						primer.setBlockState(6, 41, z, whiteBricks);
						primer.setBlockState(9, 41, z, whiteBricks);
						primer.setBlockState(10, 41, z, glass);
						primer.setBlockState(5, 40, z, whiteBricks);
						primer.setBlockState(6, 40, z, whiteBricks);
						primer.setBlockState(9, 40, z, whiteBricks);
						primer.setBlockState(10, 40, z, whiteBricks);
						break;
					case 2:
						primer.setBlockState(5, 43, z, glass);
						primer.setBlockState(6, 43, z, glass);
						primer.setBlockState(7, 43, z, glass);
						primer.setBlockState(8, 43, z, glass);
						primer.setBlockState(9, 43, z, glass);
						primer.setBlockState(10, 43, z, glass);
						primer.setBlockState(5, 42, z, glass);
						primer.setBlockState(6, 42, z, whiteBricks);
						primer.setBlockState(7, 42, z, whiteBricks);
						primer.setBlockState(8, 42, z, whiteBricks);
						primer.setBlockState(9, 42, z, whiteBricks);
						primer.setBlockState(10, 42, z, glass);
						primer.setBlockState(5, 41, z, glass);
						primer.setBlockState(6, 41, z, whiteBricks);
						primer.setBlockState(9, 41, z, whiteBricks);
						primer.setBlockState(10, 41, z, glass);
						primer.setBlockState(5, 40, z, whiteBricks);
						primer.setBlockState(6, 40, z, whiteBricks);
						primer.setBlockState(9, 40, z, whiteBricks);
						primer.setBlockState(10, 40, z, whiteBricks);
						break;
					case 3:
						primer.setBlockState(5, 43, z, glass);
						primer.setBlockState(6, 43, z, whiteBricks);
						primer.setBlockState(9, 43, z, whiteBricks);
						primer.setBlockState(10, 43, z, glass);
						primer.setBlockState(5, 42, z, glass);
						primer.setBlockState(6, 42, z, whiteBricks);
						primer.setBlockState(9, 42, z, whiteBricks);
						primer.setBlockState(10, 42, z, glass);
						primer.setBlockState(5, 41, z, whiteBricks);
						primer.setBlockState(6, 41, z, whiteBricks);
						primer.setBlockState(9, 41, z, whiteBricks);
						primer.setBlockState(10, 41, z, whiteBricks);
						break;
					case 4:
						primer.setBlockState(5, 43, z, glass);
						primer.setBlockState(6, 43, z, whiteBricks);
						primer.setBlockState(7, 43, z, glass);
						primer.setBlockState(8, 43, z, glass);
						primer.setBlockState(9, 43, z, whiteBricks);
						primer.setBlockState(10, 43, z, glass);
						primer.setBlockState(5, 42, z, glass);
						primer.setBlockState(6, 42, z, whiteBricks);
						primer.setBlockState(7, 42, z, whiteBricks);
						primer.setBlockState(8, 42, z, whiteBricks);
						primer.setBlockState(9, 42, z, whiteBricks);
						primer.setBlockState(10, 42, z, glass);
						primer.setBlockState(5, 41, z, whiteBricks);
						primer.setBlockState(6, 41, z, whiteBricks);
						primer.setBlockState(9, 41, z, whiteBricks);
						primer.setBlockState(10, 41, z, whiteBricks);
						break;
				}
				break;
			case WEST:
				x = 0;
			case EAST:
				if (side == EnumFacing.EAST)
					x = 15;

				switch (rand.nextInt(5)) {
					case 0:
						primer.setBlockState(x, 43, 5, glass);
						primer.setBlockState(x, 43, 6, glass);
						primer.setBlockState(x, 43, 7, whiteBricks);
						primer.setBlockState(x, 43, 8, whiteBricks);
						primer.setBlockState(x, 43, 9, glass);
						primer.setBlockState(x, 43, 10, glass);
						primer.setBlockState(x, 42, 5, glass);
						primer.setBlockState(x, 42, 6, glass);
						primer.setBlockState(x, 42, 7, whiteBricks);
						primer.setBlockState(x, 42, 8, whiteBricks);
						primer.setBlockState(x, 42, 9, glass);
						primer.setBlockState(x, 42, 10, glass);
						primer.setBlockState(x, 41, 5, glass);
						primer.setBlockState(x, 41, 6, whiteBricks);
						primer.setBlockState(x, 41, 7, whiteBricks);
						primer.setBlockState(x, 41, 8, whiteBricks);
						primer.setBlockState(x, 41, 9, whiteBricks);
						primer.setBlockState(x, 41, 10, glass);
						primer.setBlockState(x, 40, 5, whiteBricks);
						primer.setBlockState(x, 40, 6, whiteBricks);
						primer.setBlockState(x, 40, 7, whiteBricks);
						primer.setBlockState(x, 40, 8, whiteBricks);
						primer.setBlockState(x, 40, 9, whiteBricks);
						primer.setBlockState(x, 40, 10, whiteBricks);
						break;
					case 1:
						primer.setBlockState(x, 43, 5, glass);
						primer.setBlockState(x, 43, 6, glass);
						primer.setBlockState(x, 43, 7, whiteBricks);
						primer.setBlockState(x, 43, 8, whiteBricks);
						primer.setBlockState(x, 43, 9, glass);
						primer.setBlockState(x, 43, 10, glass);
						primer.setBlockState(x, 42, 5, whiteBricks);
						primer.setBlockState(x, 42, 6, whiteBricks);
						primer.setBlockState(x, 42, 7, whiteBricks);
						primer.setBlockState(x, 42, 8, whiteBricks);
						primer.setBlockState(x, 42, 9, whiteBricks);
						primer.setBlockState(x, 42, 10, whiteBricks);
						primer.setBlockState(x, 41, 5, glass);
						primer.setBlockState(x, 41, 6, whiteBricks);
						primer.setBlockState(x, 41, 9, whiteBricks);
						primer.setBlockState(x, 41, 10, glass);
						primer.setBlockState(x, 40, 5, whiteBricks);
						primer.setBlockState(x, 40, 6, whiteBricks);
						primer.setBlockState(x, 40, 9, whiteBricks);
						primer.setBlockState(x, 40, 10, whiteBricks);
						break;
					case 2:
						primer.setBlockState(x, 43, 5, glass);
						primer.setBlockState(x, 43, 6, glass);
						primer.setBlockState(x, 43, 7, glass);
						primer.setBlockState(x, 43, 8, glass);
						primer.setBlockState(x, 43, 9, glass);
						primer.setBlockState(x, 43, 10, glass);
						primer.setBlockState(x, 42, 5, glass);
						primer.setBlockState(x, 42, 6, whiteBricks);
						primer.setBlockState(x, 42, 7, whiteBricks);
						primer.setBlockState(x, 42, 8, whiteBricks);
						primer.setBlockState(x, 42, 9, whiteBricks);
						primer.setBlockState(x, 42, 10, glass);
						primer.setBlockState(x, 41, 5, glass);
						primer.setBlockState(x, 41, 6, whiteBricks);
						primer.setBlockState(x, 41, 9, whiteBricks);
						primer.setBlockState(x, 41, 10, glass);
						primer.setBlockState(x, 40, 5, whiteBricks);
						primer.setBlockState(x, 40, 6, whiteBricks);
						primer.setBlockState(x, 40, 9, whiteBricks);
						primer.setBlockState(x, 40, 10, whiteBricks);
						break;
					case 3:
						primer.setBlockState(x, 43, 5, glass);
						primer.setBlockState(x, 43, 6, whiteBricks);
						primer.setBlockState(x, 43, 9, whiteBricks);
						primer.setBlockState(x, 43, 10, glass);
						primer.setBlockState(x, 42, 5, glass);
						primer.setBlockState(x, 42, 6, whiteBricks);
						primer.setBlockState(x, 42, 9, whiteBricks);
						primer.setBlockState(x, 42, 10, glass);
						primer.setBlockState(x, 41, 5, whiteBricks);
						primer.setBlockState(x, 41, 6, whiteBricks);
						primer.setBlockState(x, 41, 9, whiteBricks);
						primer.setBlockState(x, 41, 10, whiteBricks);
						break;
					case 4:
						primer.setBlockState(x, 43, 5, glass);
						primer.setBlockState(x, 43, 6, whiteBricks);
						primer.setBlockState(x, 43, 7, glass);
						primer.setBlockState(x, 43, 8, glass);
						primer.setBlockState(x, 43, 9, whiteBricks);
						primer.setBlockState(x, 43, 10, glass);
						primer.setBlockState(x, 42, 5, glass);
						primer.setBlockState(x, 42, 6, whiteBricks);
						primer.setBlockState(x, 42, 7, whiteBricks);
						primer.setBlockState(x, 42, 8, whiteBricks);
						primer.setBlockState(x, 42, 9, whiteBricks);
						primer.setBlockState(x, 42, 10, glass);
						primer.setBlockState(x, 41, 5, whiteBricks);
						primer.setBlockState(x, 41, 6, whiteBricks);
						primer.setBlockState(x, 41, 9, whiteBricks);
						primer.setBlockState(x, 41, 10, whiteBricks);
						break;
				}
				break;
		}
	}

	private void correctCloudBlocks(Chunk chunk) {
		for (x = 0; x <= 15; x++) {
			for (z = 0; z <= 15; z++) {
				if (chunk.getBlockState(x, 64, z).getBlock() == Blocks.BARRIER)
					chunk.setBlockState(new BlockPos(x, 64, z), cloud);
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

		if (ConfigurationUtil.spawnChanceShyreDecoration > 0 && rand.nextInt(ConfigurationUtil.spawnChanceShyreDecoration) == 0) {
			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.nextInt(7)) {
				case 0:
					structure = StructuresHandler.getStructure("AncientShrine");
					break;
				case 1:
					structure = StructuresHandler.getStructure("ArcWizardCheckpoint");
					break;
				case 2:
					structure = StructuresHandler.getStructure("SoulscorneAmbush");
					break;
				case 3:
					structure = StructuresHandler.getStructure("StrangeShrine");
					break;
				case 4:
					structure = StructuresHandler.getStructure("ShyreDecoration1");
					break;
				case 5:
					structure = StructuresHandler.getStructure("ShyreDecoration2");
					break;
				case 6:
					structure = StructuresHandler.getStructure("ShyreDecoration3");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand, pos.setPos(baseX, 31, baseZ));
		}

		if (ConfigurationUtil.spawnChanceCraexxeusTower > 0 && rand.nextInt(ConfigurationUtil.spawnChanceCraexxeusTower) == 0) {
			x = baseX + 3;
			z = baseZ + 3;
			y = 31;

			StructuresHandler.generateStructure("CraexxeusTower", world, rand, pos.setPos(x, y, z));
		}

		if (ConfigurationUtil.spawnChanceShyreDungeon > 0 && rand.nextInt(ConfigurationUtil.spawnChanceShyreDungeon) == 0) {
			switch (rand.nextInt(3)) {
				case 0:
					x = baseX + rand.nextInt(10);
					z = baseZ + rand.nextInt(4);
					y = rand.nextInt(15) + 2;

					StructuresHandler.generateStructure("LightwalkerDungeon", world, rand, pos.setPos(x, y, z));
					break;
				case 1:
					x = baseX + rand.nextInt(15);
					z = baseZ + rand.nextInt(15);
					y = rand.nextInt(15) + 2;

					StructuresHandler.generateStructure("LuxocronDungeon", world, rand, pos.setPos(x, y, z));
					break;
				case 2:
					x = baseX;
					z = baseZ + rand.nextInt(15);
					y = rand.nextInt(15) + 2;

					StructuresHandler.generateStructure("ShyreTrollDungeon", world, rand, pos.setPos(x, y, z));
					break;
			}
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
