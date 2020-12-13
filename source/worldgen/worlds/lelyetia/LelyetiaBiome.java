package net.tslat.aoa3.worldgen.worlds.lelyetia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.AchonyTreeGenerator;
import net.tslat.aoa3.worldgen.trees.ChurryTreeGenerator;
import net.tslat.aoa3.worldgen.trees.InvertedAchonyTreeGenerator;
import net.tslat.aoa3.worldgen.trees.InvertedChurryTreeGenerator;

public class LelyetiaBiome extends AoABiome {
	public LelyetiaBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.VOID, new SurfaceBuilderConfig(AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), AoABlocks.LELYETIAN_STONE.get().getDefaultState(), AoABlocks.INVERTED_LELYETIAN_GRASS.get().getDefaultState()))
				.temperature(2)
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
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.HOT, BiomeDictionary.Type.VOID};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 255);
	}

	@Override
	public void generateStructuredChunk(WorldGenRegion world, SharedSeedRandom rand, IChunk chunk, int startX, int startZ) {
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);
		BlockPos.Mutable pos = new BlockPos.Mutable();
		
		if (random.percentChance(0.364)) {
			switch (random.randomNumberUpTo(7)) {
				case 0:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 0);
					genLandStrip(chunk, 0, 55, 15, 15, 59, 15);
					genLandStrip(chunk, 15, 55, 0, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 0, 0, 59, 15);
					chunk.setBlockState(pos.setPos(1, 59, 1), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(14, 59, 14), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(1, 59, 14), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(14, 59, 1), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);

					genLandStrip(chunk, 1, 55, 1, 14, 58, 1);
					genLandStrip(chunk, 1, 55, 14, 14, 58, 14);
					genLandStrip(chunk, 14, 55, 1, 14, 58, 14);
					genLandStrip(chunk, 1, 55, 1, 1, 58, 14);
					chunk.setBlockState(pos.setPos(2, 58, 2), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(13, 58, 13), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(2, 58, 13), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(13, 58, 2), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);

					genLandStrip(chunk, 2, 55, 2, 13, 57, 2);
					genLandStrip(chunk, 2, 55, 13, 13, 57, 13);
					genLandStrip(chunk, 13, 55, 2, 13, 57, 13);
					genLandStrip(chunk, 2, 55, 2, 2, 57, 13);
					chunk.setBlockState(pos.setPos(3, 57, 3), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(12, 57, 12), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(3, 57, 12), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);
					chunk.setBlockState(pos.setPos(12, 57, 3), AoABlocks.LELYETIAN_GRASS.get().getDefaultState(), false);

					genLandStrip(chunk, 3, 55, 3, 12, 56, 3);
					genLandStrip(chunk, 3, 55, 12, 12, 56, 12);
					genLandStrip(chunk, 12, 55, 3, 12, 56, 12);
					genLandStrip(chunk, 3, 55, 3, 3, 56, 12);
					break;
				case 1:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 1);
					genLandStrip(chunk, 0, 55, 14, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 2, 1, 59, 13);
					genLandStrip(chunk, 14, 55, 2, 15, 59, 13);

					genLandStrip(chunk, 4, 55, 4, 11, 59, 5);
					genLandStrip(chunk, 4, 55, 10, 11, 59, 11);
					genLandStrip(chunk, 4, 55, 6, 5, 59, 9);
					genLandStrip(chunk, 10, 55, 6, 11, 59, 9);
					break;
				case 2:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 0);
					genLandStrip(chunk, 0, 55, 15, 15, 59, 15);
					genLandStrip(chunk, 15, 55, 0, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 0, 0, 59, 15);

					genLandStrip(chunk, 1, 55, 1, 6, 59, 6);
					genLandStrip(chunk, 9, 55, 1, 14, 59, 6);
					genLandStrip(chunk, 1, 55, 9, 6, 59, 14);
					genLandStrip(chunk, 9, 55, 9, 14, 59, 14);
					break;
				case 3:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 1);
					genLandStrip(chunk, 0, 55, 14, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 2, 1, 59, 13);
					genLandStrip(chunk, 14, 55, 2, 15, 59, 13);

					genLandStrip(chunk, 2, 55, 2, 13, 57, 3);
					genLandStrip(chunk, 2, 55, 12, 13, 57, 13);
					genLandStrip(chunk, 2, 55, 2, 3, 57, 11);
					genLandStrip(chunk, 12, 55, 2, 13, 57, 11);

					genLandStrip(chunk, 4, 55, 4, 11, 56, 5);
					genLandStrip(chunk, 4, 55, 10, 11, 56, 11);
					genLandStrip(chunk, 4, 55, 4, 5, 56, 9);
					genLandStrip(chunk, 10, 55, 4, 11, 56, 9);
					break;
				case 4:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 4);
					genLandStrip(chunk, 0, 55, 11, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 5, 4, 59, 10);
					genLandStrip(chunk, 11, 55, 5, 15, 59, 10);

					genLandStrip(chunk, 5, 55, 10, 6, 59, 10);
					genLandStrip(chunk, 5, 55, 9, 5, 59, 9);

					genLandStrip(chunk, 9, 55, 10, 10, 59, 10);
					genLandStrip(chunk, 10, 55, 9, 10, 59, 9);

					genLandStrip(chunk, 5, 55, 5, 5, 59, 6);
					genLandStrip(chunk, 6, 55, 5, 6, 59, 5);

					genLandStrip(chunk, 10, 55, 5, 10, 59, 6);
					genLandStrip(chunk, 9, 55, 5, 9, 59, 5);
					break;
				case 5:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 1);
					genLandStrip(chunk, 0, 55, 14, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 2, 1, 59, 13);
					genLandStrip(chunk, 14, 55, 2, 15, 59, 13);

					setAllBlocksInRegion(chunk, 7, 58, 2, 8, 58, 13, AoABlocks.LELYETIAN_STONE.get().getDefaultState());
					setAllBlocksInRegion(chunk, 2, 58, 7, 6, 58, 8, AoABlocks.LELYETIAN_STONE.get().getDefaultState());
					setAllBlocksInRegion(chunk, 9, 58, 7, 13, 58, 8, AoABlocks.LELYETIAN_STONE.get().getDefaultState());
					setAllBlocksInRegion(chunk, 7, 59, 2, 8, 59, 13, AoABlocks.LELYETIAN_GRASS.get().getDefaultState());
					setAllBlocksInRegion(chunk, 2, 59, 7, 6, 59, 8, AoABlocks.LELYETIAN_GRASS.get().getDefaultState());
					setAllBlocksInRegion(chunk, 9, 59, 7, 13, 59, 8, AoABlocks.LELYETIAN_GRASS.get().getDefaultState());
					break;
				case 6:
					genLandStrip(chunk, 0, 55, 0, 15, 59, 0);
					genLandStrip(chunk, 0, 55, 15, 15, 59, 15);
					genLandStrip(chunk, 15, 55, 0, 15, 59, 15);
					genLandStrip(chunk, 0, 55, 0, 0, 59, 15);

					genLandStrip(chunk, 7, 55, 1, 8, 59, 14);
					genLandStrip(chunk, 1, 55, 7, 6, 59, 8);
					genLandStrip(chunk, 9, 55, 7, 14, 59, 8);

					genLandStrip(chunk, 1, 55, 1, 6, 58, 1);
					genLandStrip(chunk, 1, 55, 6, 6, 58, 6);
					genLandStrip(chunk, 1, 55, 2, 1, 58, 5);
					genLandStrip(chunk, 6, 55, 2, 6, 58, 5);
					genLandStrip(chunk, 2, 55, 2, 5, 57, 2);
					genLandStrip(chunk, 2, 55, 5, 5, 57, 5);
					genLandStrip(chunk, 2, 55, 3, 2, 57, 4);
					genLandStrip(chunk, 5, 55, 3, 5, 57, 4);

					genLandStrip(chunk, 1, 55, 9, 6, 58, 9);
					genLandStrip(chunk, 1, 55, 14, 6, 58, 14);
					genLandStrip(chunk, 1, 55, 10, 1, 58, 13);
					genLandStrip(chunk, 6, 55, 10, 6, 58, 13);
					genLandStrip(chunk, 2, 55, 10, 5, 57, 10);
					genLandStrip(chunk, 2, 55, 13, 5, 57, 13);
					genLandStrip(chunk, 2, 55, 11, 2, 57, 12);
					genLandStrip(chunk, 5, 55, 11, 5, 57, 12);

					genLandStrip(chunk, 9, 55, 1, 14, 58, 1);
					genLandStrip(chunk, 9, 55, 6, 14, 58, 6);
					genLandStrip(chunk, 9, 55, 2, 9, 58, 5);
					genLandStrip(chunk, 14, 55, 2, 14, 58, 5);
					genLandStrip(chunk, 10, 55, 2, 13, 57, 2);
					genLandStrip(chunk, 10, 55, 5, 13, 57, 5);
					genLandStrip(chunk, 10, 55, 3, 10, 57, 4);
					genLandStrip(chunk, 13, 55, 3, 13, 57, 4);

					genLandStrip(chunk, 9, 55, 9, 14, 58, 9);
					genLandStrip(chunk, 9, 55, 14, 14, 58, 14);
					genLandStrip(chunk, 9, 55, 10, 9, 58, 13);
					genLandStrip(chunk, 14, 55, 10, 14, 58, 13);
					genLandStrip(chunk, 10, 55, 10, 13, 57, 10);
					genLandStrip(chunk, 10, 55, 13, 13, 57, 13);
					genLandStrip(chunk, 10, 55, 11, 10, 57, 12);
					genLandStrip(chunk, 13, 55, 11, 13, 57, 12);
					break;
			}
		}
		else {
			genLandStrip(chunk, 0, 55, 0, 15, 59, 15);
		}
	}

	private void genLandStrip(IChunk chunk, final int lowerX, final int lowerY, final int lowerZ, final int upperX, final int upperY, final int upperZ) {
		setAllBlocksInRegion(chunk, lowerX, lowerY, lowerZ, upperX, lowerY, upperZ, AoABlocks.INVERTED_LELYETIAN_GRASS.get().getDefaultState());
		setAllBlocksInRegion(chunk, lowerX, lowerY + 1, lowerZ, upperX, upperY - 1, upperZ, AoABlocks.LELYETIAN_STONE.get().getDefaultState());
		setAllBlocksInRegion(chunk, lowerX, upperY, lowerZ, upperX, upperY, upperZ, AoABlocks.LELYETIAN_GRASS.get().getDefaultState());
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
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 51; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 54;

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == AoABlocks.INVERTED_LELYETIAN_GRASS.get())
				world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.LELYETIAN_WEEDS_DOWN.get().getDefaultState(), 2);
		}

		for (int i = 0; i < 51; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()).getBlock() == surfaceBlock)
				world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.LELYETIAN_WEEDS.get().getDefaultState(), 2);
		}

		for (int i = 0; i < 11; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				if (rand.fiftyFifty()) {
					StructuresHandler.generateStructure("LelyetianWiggler", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
				else {
					StructuresHandler.generateStructure("LelyetianStem", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
			}
		}

		for (int i = 0; i < 11; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 54;

			if (world.getBlockState(pos.setPos(posX, posY + 1, posZ)).getBlock() == AoABlocks.INVERTED_LELYETIAN_GRASS.get()) {
				if (rand.fiftyFifty()) {
					StructuresHandler.generateStructure("InvertedLelyetianWiggler", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
				else {
					StructuresHandler.generateStructure("InvertedLelyetianStem", world, rand.source(), pos.setPos(posX, posY, posZ));
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

			if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()).getBlock() == surfaceBlock)
				new ChurryTreeGenerator(null).generate(world, rand.source(), pos);
		}

		for (int i = 0; i < 2; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()).getBlock() == surfaceBlock)
				new AchonyTreeGenerator(null).generate(world, rand.source(), pos);
		}

		for (int i = 0; i < 2; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 54;

			if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == AoABlocks.INVERTED_LELYETIAN_GRASS.get())
				new InvertedChurryTreeGenerator(null).generate(world, rand.source(), pos);
		}

		for (int i = 0; i < 2; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 54;

			if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == AoABlocks.INVERTED_LELYETIAN_GRASS.get())
				new InvertedAchonyTreeGenerator(null).generate(world, rand.source(), pos);
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lelyetianTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 60;

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(x + 7, y, z + 7)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("LelyetianTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.zhinxEnclaveSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 15;

			if (world.getBlockState(pos.setPos(x + 4, 55, z + 6)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("ZhinxEnclave", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.paraviteHiveSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 36;

			if (world.getBlockState(pos.setPos(x + 6, 55, z + 6)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("ParaviteHive", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.boneyDungeonSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 31;

			StructuresHandler.generateStructure("BoneyDungeon", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.grawPillarSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 60;

			if (world.getBlockState(pos.setPos(x + 2, y - 1, z + 2)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("GrawPillar", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
