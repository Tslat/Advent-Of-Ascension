package net.tslat.aoa3.worldgen.worlds.mysterium;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.WorldGenMinable;
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.util.function.BiConsumer;

public class MysteriumBiome extends AoABiome {
	public MysteriumBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(AoABlocks.FUNGAL_GRASS.get().getDefaultState(), AoABlocks.FUNGAL_DIRT.get().getDefaultState(), AoABlocks.FUNGAL_ROCK.get().getDefaultState()))
				.temperature(0.4f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(88, 51, 142))
				.waterFogColor(NumberUtil.RGB(88, 51, 142))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MUSHROOM};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 0, 0);
	}

	@Override
	public void generateStructuredChunk(IWorld world, ChunkPrimer chunk, SharedSeedRandom rand, BiConsumer<BlockPos, BlockState> blockPlacer) {
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);
		BlockPos.Mutable pos = new BlockPos.Mutable();
		BlockState stone = AoABlocks.FUNGAL_ROCK.get().getDefaultState();
		BlockState dirt = AoABlocks.FUNGAL_DIRT.get().getDefaultState();
		BlockState grass = AoABlocks.FUNGAL_GRASS.get().getDefaultState();

		if (random.oneInNChance(3)) {
			setAllBlocksInRegion(blockPlacer, 0, 1, 0, 15, 10, 15, stone);
			setAllBlocksInRegion(blockPlacer, 0, 11, 0, 15, 12, 15, Blocks.WATER.getDefaultState());
		}
		else {
			setAllBlocksInRegion(blockPlacer, 0, 1, 0, 15, 20, 15, stone);
			setAllBlocksInRegion(blockPlacer, 0, 21, 0, 15, 21, 15, dirt);

			for (int x = 0; x <= 15; x++) {
				for (int z = 0; z <= 15; z++) {
					if (x == 0 || x == 15 || z == 0 || z == 15) {
						blockPlacer.accept(pos.setPos(x, 22, z), grass);
					}
					else {
						blockPlacer.accept(pos.setPos(x, 22, z), dirt);
					}
				}
			}

			for (int x = 1; x <= 14; x++) {
				for (int z = 1; z <= 14; z++) {
					if (x == 1 || x == 14 || z == 1 || z == 14) {
						blockPlacer.accept(pos.setPos(x, 23, z), grass);
					}
					else {
						blockPlacer.accept(pos.setPos(x, 23, z), dirt);
					}
				}
			}

			setAllBlocksInRegion(blockPlacer, 2, 24, 2, 13, 24, 13, grass);
		}

		setAllBlocksInRegion(blockPlacer, 0, 0, 0, 15, 1, 15, AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState());
	}
	
	private void setAllBlocksInRegion(BiConsumer<BlockPos, BlockState> blockPlacer, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ, final BlockState block) {
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		int x;
		int y;
		int z;

		for (x = lowerX; x <= upperX; x++) {
			for (y = lowerY; y <= upperY; y++) {
				for (z = lowerZ; z <= upperZ; z++) {
					blockPlacer.accept(mutablePos.setPos(x, y, z), block);
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
		else if (stage == GenerationStage.Decoration.UNDERGROUND_ORES) {
			doOreGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int i = 0; i < AoAConfig.SERVER.ORES.mystiteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.MYSTITE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.mystiteMinOresPerVein.get(), AoAConfig.SERVER.ORES.mystiteMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.FUNGAL_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(2, 22), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		if (world.getBlockState(basePos).getBlock() != Blocks.WATER) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					if (rand.fiftyFifty()) {
						posX = basePos.getX() + x;
						posZ = basePos.getZ() + z;
						posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

						if (world.getBlockState(pos.setPos(posX, posY -1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
							switch (rand.randomNumberUpTo(5)) {
								case 0:
									world.setBlockState(pos.up(), AoABlocks.MYSTIC_BUSH.get().getDefaultState(), 2);
									break;
								case 1:
									world.setBlockState(pos.up(), AoABlocks.MYSTIC_FERNS.get().getDefaultState(), 2);
									break;
								case 2:
									world.setBlockState(pos.up(), AoABlocks.RAINBOW_GRASS.get().getDefaultState(), 2);
									break;
								case 3:
									world.setBlockState(pos.up(), AoABlocks.RAINBOW_GRASS2.get().getDefaultState(), 2);
									break;
								case 4:
									world.setBlockState(pos.up(), AoABlocks.RAINBOW_GRASS3.get().getDefaultState(), 2);
									break;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.randomNumberUpTo(4)) {
				case 0:
					structure = StructuresHandler.getStructure("TinyBlueMushroom");
					break;
				case 1:
					structure = StructuresHandler.getStructure("TinyGreenMushroom");
					break;
				case 2:
					structure = StructuresHandler.getStructure("TinyOrangeMushroom");
					break;
				case 3:
					structure = StructuresHandler.getStructure("TinyYellowMushroom");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(posX, posY, posZ));
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 3; i++) {
			switch (rand.randomNumberUpTo(6)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(4);
					posZ = basePos.getZ() + rand.randomNumberUpTo(4);
					posY = chunkGenerator.getHeight(posX + 6, posZ + 6, Heightmap.Type.WORLD_SURFACE);

					if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("BlueMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(4);
					posZ = basePos.getZ() + rand.randomNumberUpTo(4);
					posY = chunkGenerator.getHeight(posX + 6, posZ + 6, Heightmap.Type.WORLD_SURFACE);

					if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("GreenMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(8);
					posZ = basePos.getZ() + rand.randomNumberUpTo(8);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("OrangeMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(8);
					posZ = basePos.getZ() + rand.randomNumberUpTo(8);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE);

					if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("PurpleMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(9);
					posZ = basePos.getZ() + rand.randomNumberUpTo(9);
					posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE);

					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("YellowMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.randomNumberUpTo(10);
					posZ = basePos.getZ() + rand.randomNumberUpTo(10);
					posY = chunkGenerator.getHeight(posX + 2, posZ + 2, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() != Blocks.AIR)
						StructuresHandler.generateStructure("MiniBlueMushroomTree", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.hauntedCastleSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = chunkGenerator.getHeight(x + 19, z + 14, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 19, y -1, z + 14)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("HauntedCastle", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.fungshroomSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(13);
			z = basePos.getZ() + rand.randomNumberUpTo(13);
			y = chunkGenerator.getHeight(x + 8, z + 8, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("Fungshroom", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.gorbVillageSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = chunkGenerator.getHeight(x + 22, z + 10, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 22, y - 1, z + 10)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("GorbVillage", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.mysticLottoShroomSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("MysticLottoShroom", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.mushroomSpiderCaveSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(15);
			y = chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("MushroomSpiderCave", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.mysticPortalPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 6, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("MysticPortalPlatform", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.runicArenaSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("RunicArena", world, rand.source(), pos.setPos(x, y , z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.distortionRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 15;

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("DistortionRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
