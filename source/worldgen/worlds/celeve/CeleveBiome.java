package net.tslat.aoa3.worldgen.worlds.celeve;

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
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAWorldGen;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.worldgen.AoABiome;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.CelevusTreeGenerator;

public class CeleveBiome extends AoABiome {
	public CeleveBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.CELEVIAN_GRASS.get().getDefaultState(), AoABlocks.CELEVIAN_DIRT.get().getDefaultState(), AoABlocks.CELEVIAN_DIRT.get().getDefaultState()))
				.temperature(0.5f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0.1f)
				.scale(0.1f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.VOID};
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

		for (int i = 0; i < 60; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(5)) {
					case 0:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.BLUE_CELEVIANS.get().getDefaultState(), 2);
						break;
					case 1:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.RED_CELEVIANS.get().getDefaultState(), 2);
						break;
					case 2:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.WHITE_CELEVIANS.get().getDefaultState(), 2);
						break;
					case 3:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.PURPLE_CELEVIANS.get().getDefaultState(), 2);
						break;
					case 4:
						StructuresHandler.generateStructure("Celebulb", world, rand.source(), pos.setPos(posX, posY, posZ));
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
			posY = chunkGenerator.getHeight(posX + 2, posZ + 2, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() == surfaceBlock)
				new CelevusTreeGenerator(null, null, rand.source()).generate(world, rand.source(), pos.up());
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.gyroPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(11);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(40, 60);

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 17, y + 7, z + 17)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("GyroPlatform", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.celevianLottoBalloonSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(10, 30);

			if (world.getBlockState(pos.setPos(x + 2, y, z + 2)).getBlock() == Blocks.AIR && world.getBlockState(pos.setPos(x + 8, y + 17, z + 8)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("CelevianLottoBalloon", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.toyTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ToyTower", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.compassRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 15;

			StructuresHandler.generateStructure("CompassRuneShrine", world, rand.source(), pos.setPos(x, y, z ));
		}
	}
}
