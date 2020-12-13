package net.tslat.aoa3.worldgen.worlds.dustopia;

import net.minecraft.block.Block;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
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
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.DawnwoodTreeGenerator;

public class DustopiaBiome extends AoABiome {
	public DustopiaBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.FADED_GRASS.get().getDefaultState(), AoABlocks.BLACKENED_SOIL.get().getDefaultState(), AoABlocks.DARKENED_ROCK.get().getDefaultState()))
				.temperature(0.25F)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 0))
				.waterFogColor(NumberUtil.RGB(0, 0, 0))
				.depth(0)
				.scale(0.25f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 0);
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

		for (int i = 0; i < 8; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				if (rand.fiftyFifty()) {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DAWN_BUSH.get().getDefaultState(), 2);
				}
				else {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DAWN_FLOWER.get().getDefaultState(), 2);
				}
			}
		}

		for (int i = 0; i < 25; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
				world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DAWN_GRASS.get().getDefaultState(), 2);
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		if (rand.oneInNChance(5)) {
			switch (rand.randomNumberUpTo(3)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 2, posZ + 2, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("DawnCage1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 1, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("DawnCage2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 5, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("DawnCage3", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 5; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
				new DawnwoodTreeGenerator(null).generate(world, rand.source(), pos.up());
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.primordialShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 2, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("PrimordialShrine", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.crusiliskCaveSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = chunkGenerator.getHeight(x + 5, z + 13, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 13)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CrusiliskCave", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.merkyreTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(15);
			y = chunkGenerator.getHeight(x + 7, z + 9, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 9)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("MerkyreTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.arkzyneOutpostSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(15);
			y = chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ArkzyneOutpost", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lottoCageSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 2, z + 2, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 2, y - 1, z + 2)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("LottoCage", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.dustopianVillageSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ() + rand.randomNumberUpTo(7);
			y = chunkGenerator.getHeight(x + 16, z + 11, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 16, y - 1, z + 17)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("DustopianVillage", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
