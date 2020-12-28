package net.tslat.aoa3.worldgen.worlds.crystevia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
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
import net.tslat.aoa3.worldgen.WorldGenMinable;
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.util.Random;

public class CrysteviaBiome extends AoABiome {
	public CrysteviaBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.CRYSTALLISED_ROCK.get().getDefaultState(), AoABlocks.CRYSTALLISED_ROCK.get().getDefaultState(), AoABlocks.CRYSTALLISED_ROCK.get().getDefaultState()))
				.temperature(0.4f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(204, 0, 102))
				.waterFogColor(NumberUtil.RGB(204, 0, 102))
				.depth(0.1f)
				.scale(0.2f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 255, 0);
	}

	@Override
	public void buildSurface(Random random, IChunk chunkIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
		super.buildSurface(random, chunkIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);

		for (int i = 0; i <= 4; i++) {
			chunkIn.setBlockState(new BlockPos(x, i, z), AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState(), false);
			chunkIn.setBlockState(new BlockPos(x, 127 - i, z), AoABlocks.DIMENSIONAL_FABRIC.get().getDefaultState(), false);
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
			doMiscGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int i = 0; i < AoAConfig.SERVER.ORES.blueGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.BLUE_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.blueGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.blueGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.greenGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.GREEN_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.greenGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.greenGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.redGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.RED_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.redGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.redGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.purpleGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.PURPLE_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.purpleGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.purpleGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.whiteGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.WHITE_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.whiteGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.whiteGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.yellowGemstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.YELLOW_GEMSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.yellowGemstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.yellowGemstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.CRYSTALLISED_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(10, 110), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 15; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = findNextSuitableYCoord(world, posX, rand.randomNumberBetween(20, 100), posZ);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				if (posY > 0) {
					switch (rand.randomNumberUpTo(6)) {
						case 0:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.BLUE_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
						case 1:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.GREEN_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
						case 2:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.PURPLE_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
						case 3:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.RED_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
						case 4:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.WHITE_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
						case 5:
							world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.YELLOW_CRYSTAL_PLANT.get().getDefaultState(), 2);
							break;
					}
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < rand.randomNumberUpTo(6); i++) {
			String colour;

			switch (rand.randomNumberUpTo(6)) {
				case 0:
					colour = "Blue";
					break;
				case 1:
					colour = "Green";
					break;
				case 2:
					colour = "Purple";
					break;
				case 3:
					colour = "Red";
					break;
				case 4:
					colour = "White";
					break;
				case 5:
				default:
					colour = "Yellow";
					break;
			}

			switch (rand.randomNumberUpTo(6)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX + 1, rand.randomNumberBetween(20, 100), posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX, rand.randomNumberBetween(20, 100), posZ);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX + 1, rand.randomNumberBetween(20, 100), posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk3", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX, rand.randomNumberBetween(20, 100), posZ);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk4", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX + 1, rand.randomNumberBetween(20, 100), posZ + 1);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk5", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = findNextSuitableYCoord(world, posX, rand.randomNumberBetween(20, 100), posZ);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure(colour + "CrystalChunk6", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
			}
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		if (rand.oneInNChance(10)) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = rand.randomNumberBetween(10, 120);

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == surfaceBlock) {
				world.setBlockState(pos.setPos(posX, posY, posZ), Blocks.WATER.getDefaultState(), 2);

				while (posY > 2 && world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() != Blocks.AIR) {
					world.setBlockState(pos.setPos(posX, posY - 1, posZ), Blocks.AIR.getDefaultState(), 2);

					posY--;
				}
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.crystalBuildingSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(20, 100);

			switch (rand.randomNumberUpTo(4)) {
				case 0:
					if (world.getBlockState(pos.setPos(x + 5, y + 3, z + 5)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalExtensionStation", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 1:
					if (world.getBlockState(pos.setPos(x + 4, y + 3, z + 4)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalLottoOverlook", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 2:
					if (world.getBlockState(pos.setPos(x + 3, y + 3, z + 3)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("PowerStation", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 3:
					if (world.getBlockState(pos.setPos(x + 4, y + 1, z + 4)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("CrystalTradingPost", world, rand.source(), pos.setPos(x, y, z));
					break;
			}
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.crystalTransferHutSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(20, 100);

			if (world.getBlockState(pos.setPos(x + 5, y, z + 5)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 5, y + 9, z + 5)).getBlock() == Blocks.AIR) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.randomNumberUpTo(6)) {
					case 0:
						structure = StructuresHandler.getStructure("BlueCrystalTransferHut");
						break;
					case 1:
						structure = StructuresHandler.getStructure("GreenCrystalTransferHut");
						break;
					case 2:
						structure = StructuresHandler.getStructure("PurpleCrystalTransferHut");
						break;
					case 3:
						structure = StructuresHandler.getStructure("RedCrystalTransferHut");
						break;
					case 4:
						structure = StructuresHandler.getStructure("WhiteCrystalTransferHut");
						break;
					case 5:
						structure = StructuresHandler.getStructure("YellowCrystalTransferHut");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(x, y, z));
			}
		}
	}

	private int findNextSuitableYCoord(IWorld world, int posX, int posY, int posZ) {
		BlockPos.Mutable testPos = new BlockPos.Mutable(posX, posY, posZ);

		if (world.getBlockState(testPos).getBlock() == Blocks.AIR) {
			while (testPos.getY() >= 2 && world.getBlockState(testPos.move(Direction.DOWN)).getBlock() == Blocks.AIR) {
				;
			}

			Block rock = AoABlocks.CRYSTALLISED_ROCK.get();

			if (world.getBlockState(testPos).getBlock() == rock)
				return testPos.getY() + 1;

		}

		return 0;
	}
}
