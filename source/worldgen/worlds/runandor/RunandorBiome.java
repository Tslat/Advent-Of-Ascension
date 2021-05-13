package net.tslat.aoa3.worldgen.worlds.runandor;

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
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.RunicTreeGenerator;

import java.util.function.BiConsumer;

public class RunandorBiome extends AoABiome {
	private int chunkNum = RandomUtil.randomNumberUpTo(26);

	public RunandorBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(AoABlocks.RUNIC_GRASS.get().getDefaultState(), AoABlocks.RUNIC_STONE.get().getDefaultState(), AoABlocks.RUNIC_STONE.get().getDefaultState()))
				.temperature(0.5F)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MOUNTAIN};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 255);
	}

	@Override
	public void generateStructuredChunk(IWorld world, ChunkPrimer chunk, SharedSeedRandom rand, BiConsumer<BlockPos, BlockState> blockPlacer) {
		BlockPos.Mutable pos = new BlockPos.Mutable();

		if (chunkNum > 25)
			chunkNum = 0;

		setAllBlocksInRegion(blockPlacer, 0, 2, 0, 15, 11 + chunkNum, 15, AoABlocks.RUNIC_STONE.get().getDefaultState());
		setAllBlocksInRegion(blockPlacer, 0, 11 + chunkNum, 0, 15, 11 + chunkNum, 15, 11 + chunkNum <= 14 ? AoABlocks.RUNIC_STONE.get().getDefaultState() : AoABlocks.RUNIC_GRASS.get().getDefaultState());

		if (chunkNum < 4) {
			for (int x = 0; x <= 15; x++) {
				for (int y = 12 + chunkNum; y <= 15; y++) {
					for (int z = 0; z <= 15; z++) {
						blockPlacer.accept(pos.setPos(x, y, z), Blocks.WATER.getDefaultState());
					}
				}
			}
		}

		chunkNum++;

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
		for (int i = 0; i < AoAConfig.SERVER.ORES.elecaniumVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.ELECANIUM_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.elecaniumMinOresPerVein.get(), AoAConfig.SERVER.ORES.elecaniumMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.RUNIC_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberBetween(1, 15), rand.randomNumberBetween(2, 36), rand.randomNumberBetween(1, 15)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 85; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(3)) {
					case 0:
						world.setBlockState(pos.up(), AoABlocks.RUNIC_BUSH.get().getDefaultState(), 2);
						break;
					case 1:
						world.setBlockState(pos.up(), AoABlocks.RUNE_BULBS.get().getDefaultState(), 2);
						break;
					case 2:
						world.setBlockState(pos.up(), AoABlocks.MAGIAS.get().getDefaultState(), 2);
						break;
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 2; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() == surfaceBlock)
				new RunicTreeGenerator(null).generate(world, rand.source(), pos.up());
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.clunkheadArenaSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(6);
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = chunkGenerator.getHeight(x + 12, z + 10, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 12, y - 1, z + 10)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ClunkheadArena", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.runicTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("RunicTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.spectralCageSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(13);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 8, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("SpectralCage", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.runeRandomisationStationSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("RuneRandomisationStation", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.runeTemplarBunkerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 3;

			if (chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG) > 11)
				StructuresHandler.generateStructure("RuneTemplarBunker", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
