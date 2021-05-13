package net.tslat.aoa3.worldgen.worlds.lunalus;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.LuniciaTreeGenerator;
import net.tslat.aoa3.worldgen.trees.LunossoTreeGenerator;

import java.util.function.BiConsumer;

public class LunalusBiome extends AoABiome {
	public LunalusBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState()))
				.temperature(-1)
				.downfall(0)
				.waterColor(NumberUtil.RGB(25, 25, 25))
				.waterFogColor(NumberUtil.RGB(25, 25, 25))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID, BiomeDictionary.Type.SPARSE};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 175, 175);
	}

	@Override
	public void generateStructuredChunk(IWorld world, ChunkPrimer chunk, SharedSeedRandom rand, BiConsumer<BlockPos, BlockState> blockPlacer) {}
	
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
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 36; y <= 60; y++) {
					Block bl = world.getBlockState(pos.setPos(basePos.getX() + x, y - 1, basePos.getZ() + z)).getBlock();

					if (rand.oneInNChance(10) && (bl == AoABlocks.LUNASOLE_GRASS.get() || bl == AoABlocks.LUNALYTE_GRASS.get()) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
						if (rand.fiftyFifty()) {
							world.setBlockState(pos.up(), AoABlocks.LUNTAR.get().getDefaultState(), 2);
						}
						else {
							world.setBlockState(pos.up(), AoABlocks.LUNALIP.get().getDefaultState(), 2);
						}
					}
				}
			}
		}

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 61; y <= 85; y++) {
					Block bl = world.getBlockState(pos.setPos(basePos.getX() + x, y - 1, basePos.getZ() + z)).getBlock();

					if (rand.oneInNChance(10) && (bl == AoABlocks.LUNASOLE_GRASS.get() || bl == AoABlocks.LUNALYTE_GRASS.get()) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
						if (rand.fiftyFifty()) {
							world.setBlockState(pos.up(), AoABlocks.LUNTAR.get().getDefaultState(), 2);
						}
						else {
							world.setBlockState(pos.up(), AoABlocks.LUNALIP.get().getDefaultState(), 2);
						}
					}
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int count = 0;

		LowTrees:
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 36; y <= 60; y++) {
					if (rand.oneInNChance(25)) {
						Block bl;

						switch (rand.randomNumberUpTo(4)) {
							case 0:
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 1, y - 1, basePos.getZ() + z + 1)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
									StructuresHandler.generateStructure("LunossoTree1", world, rand.source(), pos.add(-1, 1, -1));
									count++;
								}
								break;
							case 1:
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
									StructuresHandler.generateStructure("LunossoTree2", world, rand.source(), pos.add(-2, 1, -2));
									count++;
								}
								break;
							case 2:
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
									StructuresHandler.generateStructure("LuniciaTree1", world, rand.source(), pos.add(-2, 1, -2));
									count++;
								}
								break;
							case 3:
								if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
									StructuresHandler.generateStructure("LuniciaTree2", world, rand.source(), pos.add(-2, 1, -2));
									count++;
								}
								break;
						}

						if (count == 2)
							break LowTrees;
					}
				}
			}
		}

		count = 0;

		HighTrees:
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 61; y <= 85; y++) {
					if (rand.oneInNChance(25)) {
						Block bl;

						if (rand.fiftyFifty()) {
							if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
								new LunossoTreeGenerator(null).generate(world, rand.source(), pos.up());
								count++;
							}
						}
						else {
							if ((bl = world.getBlockState(pos.setPos(basePos.getX() + x + 2, y - 1, basePos.getZ() + z + 2)).getBlock()) == AoABlocks.LUNALYTE_GRASS.get() || bl == AoABlocks.LUNASOLE_GRASS.get()) {
								new LuniciaTreeGenerator(null).generate(world, rand.source(), pos.up());
								count++;
							}
						}

						if (count == 2)
							break HighTrees;
					}
				}
			}
		}
	}
	
	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.fiftyFifty()) {
			x = basePos.getX() + rand.randomNumberUpTo(4);
			z = basePos.getZ() + rand.randomNumberUpTo(4);
			y = rand.randomNumberBetween(36, 45);

			if (rand.fiftyFifty()) {
				StructuresHandler.generateStructure("LunarIsland1", world, rand.source(), new BlockPos(x, y, z));
			}
			else {
				StructuresHandler.generateStructure("LunarIsland2", world, rand.source(), new BlockPos(x, y, z));
			}
		}

		if (rand.fiftyFifty()) {
			x = basePos.getX() + rand.randomNumberUpTo(4);
			z = basePos.getZ() + rand.randomNumberUpTo(4);
			y = rand.randomNumberBetween(61, 70);

			if (rand.fiftyFifty()) {
				StructuresHandler.generateStructure("LunarIsland1", world, rand.source(), new BlockPos(x, y, z));
			}
			else {
				StructuresHandler.generateStructure("LunarIsland2", world, rand.source(), new BlockPos(x, y, z));
			}
		}


		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarVillageSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = rand.randomNumberBetween(4, 8);

			StructuresHandler.generateStructure("LunarAtom", world, rand.source(), pos.setPos(x + 60, y, z + 14));
			StructuresHandler.generateStructure("LunarLottoPlatform", world, rand.source(), pos.setPos(x + 85, y, z + 29));
			StructuresHandler.generateStructure("LunarFoodMarket", world, rand.source(), pos.setPos(x + 35, y, z));
			StructuresHandler.generateStructure("LunarHerbalHouse", world, rand.source(), pos.setPos(x + 15, y, z + 5));
			StructuresHandler.generateStructure("SpellbinderHouse", world, rand.source(), pos.setPos(x + 47, y + 22, z + 22));
			StructuresHandler.generateStructure("LunaradeStand", world, rand.source(), pos.setPos(x + 28, y + 22,  z + 22));
			StructuresHandler.generateStructure("LunarBank", world, rand.source(), pos.setPos(x, y + 5, z + 12));
			StructuresHandler.generateStructure("LunarFountain", world, rand.source(), pos.setPos(x + 30, y, z + 14));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.spaceArenaSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(9);
			z = basePos.getZ() + rand.randomNumberUpTo(8);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 10, y + 4, z + 11)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("SpaceArena", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarCreationPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 4, y + 2, z + 4)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarCreationPlatform", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.zargPlanetoidSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(7);
			z = basePos.getZ() + rand.randomNumberUpTo(5);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 11, y + 12, z + 12)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ZargPlanetoid", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarMazeSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(12);
			z = basePos.getZ() + rand.randomNumberUpTo(12);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 9, y + 7, z + 9)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarMaze", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarPrisonSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ() + rand.randomNumberUpTo(9);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 18, y + 5, z + 10)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarPrison", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarGardenSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(13);
			z = basePos.getZ() + rand.randomNumberUpTo(13);
			y = rand.randomNumberBetween(15, 18);

			if (world.getBlockState(pos.setPos(x + 8, y + 7, z + 8)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarGarden", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.observersEyeSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(90, 100);

			StructuresHandler.generateStructure("ObserversEye", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lunarRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.fiftyFifty() ? rand.randomNumberBetween(70, 90) : rand.randomNumberBetween(6, 21);

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LunarRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
