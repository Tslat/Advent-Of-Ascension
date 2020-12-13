package net.tslat.aoa3.worldgen.worlds.gardencia;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
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

public class GardenciaBiome extends AoABiome {
	public GardenciaBiome() {
		super(getBuilder());

		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AoAWorldGen.Features.BIOME_FRIENDLY_LAKE.get().withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AoAWorldGen.Features.BIOME_FRIENDLY_LAKE.get().withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(AoABlocks.AROMATIC_GRASS.get().getDefaultState(), AoABlocks.AROMATIC_DIRT.get().getDefaultState(), AoABlocks.AROMATIC_STONE.get().getDefaultState()))
				.temperature(0.5f)
				.downfall(0.7f)
				.waterColor(NumberUtil.RGB(25, 209, 25))
				.waterFogColor(NumberUtil.RGB(25, 209, 25))
				.depth(0)
				.scale(0.3f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET, BiomeDictionary.Type.HILLS};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 255, 0);
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

		for (int i = 0; i < 59; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (posY > 67 && world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock && world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
				switch (rand.randomNumberUpTo(3)) {
					case 0:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DAILEERS.get().getDefaultState(), 2);
						break;
					case 1:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.LYLIPS.get().getDefaultState(), 2);
						break;
					case 2:
						StructuresHandler.generateStructure("GardenGrass", world, rand.source(), pos.setPos(posX, posY, posZ));
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
			if (rand.fiftyFifty())
				continue;

			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (posY > 67) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.randomNumberUpTo(10)) {
					case 0:
						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("RoseTree");

						break;
					case 1:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("BlueStarflower1");

						break;
					case 2:
						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("BlueStarflower2");

						break;
					case 3:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("RoseStarflower1");

						break;
					case 4:
						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("RoseStarflower2");

						break;
					case 5:
						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("Sunflower1");

						break;
					case 6:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("Sunflower2");

						break;
					case 7:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("BlueTulip");

						break;
					case 8:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("PurpleTulip");

						break;
					case 9:
						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
							structure = StructuresHandler.getStructure("MagentaTulip");

						break;
				}

				StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(posX, posY, posZ));
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.floroCastleSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(5);
			z = basePos.getZ() + rand.randomNumberUpTo(5);
			y = 63;

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.WATER)
				StructuresHandler.generateStructure("FloroCastle", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.gardenCastleSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ() + rand.randomNumberUpTo(9);
			y = chunkGenerator.getHeight(x + 17, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 17, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("GardenCastle", world, rand.source(), pos.setPos(x, y - 1, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lottoSkyFlowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("LottoSkyFlower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.wizardFlowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("WizardFlower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.dayseeFlowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 7, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("DayseeFlower", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.kineticRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			StructuresHandler.generateStructure("KineticRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
