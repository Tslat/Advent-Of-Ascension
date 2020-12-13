package net.tslat.aoa3.worldgen.worlds.candyland;

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
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.util.Random;

public class CandylandBiome extends AoABiome {
	public CandylandBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(AoABlocks.CANDIED_GRASS.get().getDefaultState(), AoABlocks.CANDIED_DIRT.get().getDefaultState(), AoABlocks.CANDIED_DIRT.get().getDefaultState()))
				.temperature(0.4f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(153, 51, 51))
				.waterFogColor(NumberUtil.RGB(153, 51, 51))
				.depth(0)
				.scale(0.1f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 255, 0);
	}

	@Override
	public void buildSurface(Random rand, IChunk chunk, int x, int z, int startHeight, double noise, BlockState fillerBlock, BlockState oceanFluid, int seaLevel, long seed) {
		super.buildSurface(rand, chunk, x, z, startHeight, noise, fillerBlock, oceanFluid, seaLevel, seed);

		chunk.setBlockState(new BlockPos(x, 1, z), AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState(), false);
		chunk.setBlockState(new BlockPos(x, 0, z), AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState(), false);
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

		for (int i = 0; i < 10; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
				switch (rand.randomNumberUpTo(5)) {
					case 0:
						StructuresHandler.generateStructure("CandyTube", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 1:
						StructuresHandler.generateStructure("CandyStack", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 2:
						StructuresHandler.generateStructure("GreenPeppermintStack", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 3:
						StructuresHandler.generateStructure("RedPeppermintStack", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 4:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.CANDYCANE.get().getDefaultState(), 2);
						break;
				}
			}
		}

		for (int i = 0; i < 100; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
				if (rand.fiftyFifty()) {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.PINK_CANDY_GRASS.get().getDefaultState(), 2);
				}
				else {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.BLUE_CANDY_GRASS.get().getDefaultState(), 2);
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		switch (rand.randomNumberUpTo(12)) {
			case 0:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 1, posZ + 6,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 6)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CandyCane1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 1:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 4, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CandyCane2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 2:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 1, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CandyCane3", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 3:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 1, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CandyCane4", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 4:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CottonCandyTree1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 5:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("CottonCandyTree2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 6:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("AquaCottonCandyTree1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 7:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("AquaCottonCandyTree2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 8:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("Lollypop1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 9:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 2, posZ + 2,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("Lollypop2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 10:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX + 4, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("SwirlPop1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 11:
				posX = basePos.getX() + rand.randomNumberUpTo(16);
				posZ = basePos.getZ() + rand.randomNumberUpTo(16);
				posY = chunkGenerator.getHeight(posX, posZ + 4,  Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 4)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("SwirlPop2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
		}
	}
	
	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		if (rand.oneInNChance(3)) {
			switch (rand.randomNumberUpTo(6)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("ChocolateBar1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 1, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("ChocolateBar2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("WhiteChocolateBar1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 1, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("WhiteChocolateBar2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ + 1,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("DarkChocolateBar1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 1, posZ,  Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("DarkChocolateBar2", world, rand.source(), pos.setPos(posX, posY, posZ));
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
		
		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.cottonCandyTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CottonCandyTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.gingerbreadHouseSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("GingerbreadHouse", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.gingerbirdAviarySpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("GingerbirdAviary", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.candyLottoPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 6, z + 6,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CandyLottoPlatform", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.infestedCandyCaneSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 6, z + 6,  Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("InfestedCandyCane", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
