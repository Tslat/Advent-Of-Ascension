package net.tslat.aoa3.worldgen.worlds.lborean;

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
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

public class LBoreanBiome extends AoABiome {
	public LBoreanBiome() {
		super(getBuilder());

		for (int i = 0; i < 8; i++) {
			addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AoAWorldGen.Features.BIOME_FRIENDLY_LAKE.get().withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
		}
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.CORAL_GRASS.get().getDefaultState(), AoABlocks.CORAL_SOIL.get().getDefaultState(), AoABlocks.CORAL_ROCK.get().getDefaultState()))
				.temperature(0.5f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0)
				.scale(0.05f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 255);
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

		for (int i = 0; i < 65; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY, posZ).down()).getBlock() == surfaceBlock) {
				if (rand.oneInNChance(6500)) {
					world.setBlockState(pos, AoABlocks.RED_WATERWEEDS.get().getDefaultState(), 2);
				}
				else {
					switch (rand.randomNumberUpTo(5)) {
						case 0:
							world.setBlockState(pos, AoABlocks.BUREAL_STOCKS.get().getDefaultState(), 2);
							break;
						case 1:
							world.setBlockState(pos, AoABlocks.GREEN_WATERWEEDS.get().getDefaultState(), 2);
							break;
						case 2:
							world.setBlockState(pos, AoABlocks.WHITE_WATERWEEDS.get().getDefaultState(), 2);
							break;
						case 3:
							world.setBlockState(pos, AoABlocks.YELLOW_WATERWEEDS.get().getDefaultState(), 2);
							break;
						case 4:
							world.setBlockState(pos, AoABlocks.BLUE_OCEALITES.get().getDefaultState(), 2);
							break;
					}
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX = basePos.getX() + rand.randomNumberUpTo(16);
		int posY;
		int posZ = basePos.getZ() + rand.randomNumberUpTo(16);

		switch (rand.randomNumberUpTo(6)) {
			case 0:
				posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("GreenCoral1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 1:
				posY = chunkGenerator.getHeight(posX + 1, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 10)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("GreenCoral2", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 2:
				posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("PinkCoral1", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 3:
				posY = chunkGenerator.getHeight(posX + 1, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 10, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("OrangeCoral", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 4:
				posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("WhiteCoral", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
			case 5:
				posY = chunkGenerator.getHeight(posX + 4, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

				if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
					StructuresHandler.generateStructure("YellowCoral", world, rand.source(), pos.setPos(posX, posY, posZ));
				break;
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.aquaticCastleSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(6);
			z = basePos.getZ() + rand.randomNumberUpTo(5);
			y = chunkGenerator.getHeight(x + 12, z + 12, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 12, y - 1, z + 12)).getBlock() == surfaceBlock || world.getBlockState(pos).getBlock() == Blocks.WATER)
				StructuresHandler.generateStructure("AquaticCastle", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.drownedLottoStandSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 6, z + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 7)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("DrownedLottoStand", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.dracyonFountainSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("DracyonFountain", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.hydroPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 3, y - 1, z + 3)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("HydroPlatform", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.waterRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("WaterRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
