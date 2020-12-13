package net.tslat.aoa3.worldgen.worlds.voxponds;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

public class VoxPondsBiome extends AoABiome {
	public VoxPondsBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(AoABlocks.POLLUTED_GRASS.get().getDefaultState(), AoABlocks.POLLUTED_SOIL.get().getDefaultState(), AoABlocks.POLLUTED_STONE.get().getDefaultState()))
				.temperature(0)
				.downfall(0)
				.waterColor(NumberUtil.RGB(103, 147, 0))
				.waterFogColor(NumberUtil.RGB(103, 147, 0))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 255, 0);
	}

	@Override
	public void generateStructuredChunk(WorldGenRegion world, SharedSeedRandom rand, IChunk chunk, int startX, int startZ) {
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState toxicStone = AoABlocks.POLLUTED_STONE.get().getDefaultState();
		BlockState toxicWaste = AoABlocks.TOXIC_WASTE.get().getDefaultState();

		setAllBlocksInRegion(chunk, 0, 0, 0, 15, 15, 15, toxicStone);
		setAllBlocksInRegion(chunk, 0, 16, 0, 15, 17, 15, Blocks.WATER.getDefaultState());
		setAllBlocksInRegion(chunk, 0, 32, 0, 15, 35, 15, toxicStone);

		if (rand.nextBoolean()) {
			setAllBlocksInRegion(chunk, 0, 36, 0, 15, 36, 15, toxicStone);
			setAllBlocksInRegion(chunk, 0, 37, 0, 15, 37, 15, toxicWaste);

			for (int x = 0; x <= 15; x++) {
				for (int z = 0; z <= 15; z++) {
					if (rand.nextBoolean())
						chunk.setBlockState(pos.setPos(x, 36, z), toxicWaste, false);
				}
			}
		}
		else {
			setAllBlocksInRegion(chunk, 0, 36, 0, 15, 37, 15, AoABlocks.POLLUTED_SOIL.get().getDefaultState());
			setAllBlocksInRegion(chunk, 0, 38, 0, 15, 38, 15, AoABlocks.POLLUTED_GRASS.get().getDefaultState());

			for (int x = 0; x <= 15; x++) {
				for (int z = 0; z <= 15; z++) {
					if (rand.nextBoolean())
						chunk.setBlockState(pos.setPos(x, 36, z), toxicStone, false);
				}
			}
		}

		setAllBlocksInRegion(chunk, 0, 0, 0, 15, 1, 15, AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState());
	}

	private void setAllBlocksInRegion(IChunk chunk, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ, final BlockState block) {
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		int x;
		int y;
		int z;

		for (x = lowerX; x <= upperX; x++) {
			for (y = lowerY; y <= upperY; y++) {
				for (z = lowerZ; z <= upperZ; z++) {
					chunk.setBlockState(mutablePos.setPos(x, y, z), block, false);
				}
			}
		}
	}

	@Override
	public void decorate(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld world, long seed, SharedSeedRandom rand, BlockPos pos) {
		super.decorate(stage, chunkGenerator, world, seed, rand, pos);

		Biome biome = world.getBiomeManager().getBiome(pos);
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);

		if (stage == GenerationStage.Decoration.VEGETAL_DECORATION) {
			doPlantGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doTreeGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doMiscGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				if (rand.fiftyFifty() && world.getBlockState(pos.setPos(basePos.getX() + x, 38, basePos.getZ() + z)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
					world.setBlockState(pos.up(), AoABlocks.DEAD_GRASS.get().getDefaultState(), 2);
			}
		}

		for (int i = 0; i < 3; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 39;

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				world.setBlockState(pos.up(), AoABlocks.VOX_SHROOM.get().getDefaultState(), 2);
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < rand.randomNumberUpTo(3); i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 39;

			switch (rand.randomNumberUpTo(3)) {
				case 0:
					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTree1", world, rand.source(), pos.setPos(posX, posY, posZ));

					break;
				case 1:
					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTree2", world, rand.source(), pos.setPos(posX, posY, posZ));

					break;
				case 2:
					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTree3", world, rand.source(), pos.setPos(posX, posY, posZ));

					break;
			}
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 12; i++) {
			switch (rand.randomNumberUpTo(5))  {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = 39;

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTentacle1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = 39;

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTentacle2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = 39;

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTentacle3", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = 39;

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTentacle4", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = 39;

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("ToxicTentacle5", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
			}
		}

		for (int i = 0; i < 6; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 39;

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
				if (rand.fiftyFifty()) {
					StructuresHandler.generateStructure("VoxBranch1", world, rand.source(), pos.up());
				}
				else {
					StructuresHandler.generateStructure("VoxBranch2", world, rand.source(), pos.up());
				}
			}
		}

		for (int i = 0; i < 8; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 38;

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == AoABlocks.TOXIC_WASTE.get())
				StructuresHandler.generateStructure("MiniTentacles", world, rand.source(), pos.up());
		}

		if (rand.randomNumberUpTo(16) == 0) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 16;

			if (rand.fiftyFifty()) {
				StructuresHandler.generateStructure("DegradedSupportBeam1", world, rand.source(), pos.setPos(posX, posY, posZ));
			}
			else {
				StructuresHandler.generateStructure("DegradedSupportBeam2", world, rand.source(), pos.setPos(posX, posY, posZ));
			}
		}

		for (int i = 0; i < rand.randomNumberUpTo(6) + 2; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 28;
			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.randomNumberUpTo(4)) {
				case 0:
					structure = StructuresHandler.getStructure("ToxicStem1");
					break;
				case 1:
					structure = StructuresHandler.getStructure("ToxicStem2");
					break;
				case 2:
					structure = StructuresHandler.getStructure("ToxicStem3");
					break;
				case 3:
					structure = StructuresHandler.getStructure("ToxicStem4");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(posX, posY, posZ));
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.oneInNChance(6)) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);

			if (rand.fiftyFifty()) {
				StructuresHandler.generateStructure("HangingDegradedLampPost", world, rand.source(), pos.setPos(x, 27, z));
			}
			else {
				StructuresHandler.generateStructure("DegradedLampPost", world, rand.source(), pos.setPos(x, 16, z));
			}
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.enigmaStationSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 18;

			if (world.getBlockState(pos.setPos(x + 6, y, z + 6)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("EnigmaStation", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.controlTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ControlTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.cellTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("CellTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.observationTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(14);
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 11)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ObservationTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.voxBuildingSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = 39;

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)).getBlock() == surfaceBlock) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.randomNumberUpTo(4)) {
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

				StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(x, y, z));
			}
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.nightwingIslandSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 18;

			if (world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("NightwingIsland", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.voxLottoOutpostSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 39;

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("VoxLottoOutpost", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.poisonRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 47;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("PoisonRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
