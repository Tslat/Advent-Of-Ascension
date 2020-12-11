package net.tslat.aoa3.worldgen.worlds.barathos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.WorldGenMinable;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.surfacebuilder.config.TieredSurfaceBuilderConfig;

import java.util.Random;

public class BarathosBiome extends AoABiome {
	public BarathosBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.BARATHOS_CAVES, new ProbabilityConfig(1f)));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.TIERED, new TieredSurfaceBuilderConfig(AoABlocks.BARON_GROUND.get().getDefaultState(), AoABlocks.BARON_STONE.get().getDefaultState(), new Tuple<BlockState, Integer>(AoABlocks.HELLSTONE.get().getDefaultState(), 25), new Tuple<BlockState, Integer>(AoABlocks.BARON_STONE.get().getDefaultState(), 255)))
				.temperature(2)
				.downfall(500)
				.waterColor(NumberUtil.RGB(255, 255, 255))
				.waterFogColor(NumberUtil.RGB(255, 255, 255))
				.depth(0)
				.scale(0.35f)
				.category(Category.MESA);

		return builder;
	}

	@Override
	public void buildSurface(Random rand, IChunk chunk, int x, int z, int topBlock, double noise, BlockState fillerBlock, BlockState defaultFluid, int seaLevel, long seed) {
		super.buildSurface(rand, chunk, x, z, topBlock, noise, fillerBlock, defaultFluid, seaLevel, seed);

		ChunkPos chunkPos = chunk.getPos();

		((SharedSeedRandom)rand).setDecorationSeed(seed, chunkPos.x, chunkPos.x);

		if (x - chunkPos.getXStart() == 13 + rand.nextInt(4) || z - chunkPos.getZStart() == 13 + rand.nextInt(4))
			chunk.setBlockState(new BlockPos(x, topBlock - 1, z), fillerBlock, false);
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 200, 0);
	}

	@Override
	public void decorate(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld world, long seed, SharedSeedRandom rand, BlockPos pos) {
		super.decorate(stage, chunkGenerator, world, seed, rand, pos);

		Biome biome = world.getBiomeManager().getBiome(pos);
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);

		if (stage == GenerationStage.Decoration.UNDERGROUND_ORES) {
			doOreGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doMiscGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int i = 0; i < AoAConfig.SERVER.ORES.varsiumVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.VARSIUM_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.varsiumMinOresPerVein.get(), AoAConfig.SERVER.ORES.varsiumMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.BARON_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(25, 63), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.baronyteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.BARONYTE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.baronyteMinOresPerVein.get(), AoAConfig.SERVER.ORES.baronyteMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.BARON_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(25, 35), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.blaziumVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.BLAZIUM_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.blaziumMinOresPerVein.get(), AoAConfig.SERVER.ORES.blaziumMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.HELLSTONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberUpTo(20), rand.randomNumberUpTo(16)));
		}
	}
	
	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		Block subsurfaceBlock = biome.getSurfaceBuilder().getConfig().getUnder().getBlock();
		int posX;
		int posY;
		int posZ;
		Block testBlock;

		switch (rand.randomNumberUpTo(4)) {
			case 0:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 3, posZ + 2, Heightmap.Type.WORLD_SURFACE_WG);

				testBlock = world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 2)).getBlock();

				if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
					StructuresHandler.generateStructure("SmallBaronRock1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 1:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 1, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

				testBlock = world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 3)).getBlock();

				if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
					StructuresHandler.generateStructure("SmallBaronRock2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 2:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

				testBlock = world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1)).getBlock();

				if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
					StructuresHandler.generateStructure("SmallBaronRock3", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 3:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 1, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

				testBlock = world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock();

				if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
					StructuresHandler.generateStructure("SmallBaronRock4", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
		}

		if (rand.oneInNChance(15)) {
			switch (rand.randomNumberUpTo(8)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock1", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 5, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock2", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock3", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 5, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock4", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(15);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 5, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock5", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 7, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 4)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock6", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 6:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock7", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
				case 7:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 4, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5)).getBlock();

					if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
						StructuresHandler.generateStructure("BaronRock8", world, rand.source(), pos.setPos(posX, posY - 1, posZ));
					break;
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		Block subsurfaceBlock = biome.getSurfaceBuilder().getConfig().getUnder().getBlock();
		int x;
		int z;
		int y;
		
		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.baronessArenaSpawnChance.get())) {
			y = chunkGenerator.getHeight(basePos.getX() + 15, basePos.getZ() + 15, Heightmap.Type.WORLD_SURFACE_WG);

			Block testBlock = world.getBlockState(pos.setPos(basePos.getX() + 15, y - 1, basePos.getZ() + 15)).getBlock();

			if (testBlock == surfaceBlock || testBlock == subsurfaceBlock)
				StructuresHandler.generateStructure("BaronessArena", world, rand.source(), pos.setPos(basePos.getX(), y, basePos.getZ()));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.hiveNestSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(10);
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = chunkGenerator.getHeight(x + 9, z + 11, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("HiveNest", world, rand.source(), pos);
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.baronCastleSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(14);
			y = chunkGenerator.getHeight(x + 7, z + 15, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x, y - 1, z)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("BaronCastle", world, rand.source(), pos);
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.baronessHouseSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(10);
			z = basePos.getZ() + rand.randomNumberUpTo(11);
			y = chunkGenerator.getHeight(x + 10, z + 9, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 10, y - 1, z + 9)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("BaronessHouse", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.powerRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			StructuresHandler.generateStructure("PowerRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
