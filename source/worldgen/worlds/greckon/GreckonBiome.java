package net.tslat.aoa3.worldgen.worlds.greckon;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
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
import net.tslat.aoa3.worldgen.WorldGenMinable;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.HauntedTreeGenerator;

public class GreckonBiome extends AoABiome {
	public GreckonBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.GRECKON_CAVES, new ProbabilityConfig(1 / 7f)));
		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, AoAWorldGen.Features.BIOME_FRIENDLY_LAKE.get().withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(16))));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.GRECKON_GRASS.get().getDefaultState(), AoABlocks.GRECKON_DIRT.get().getDefaultState(), AoABlocks.GRECKON_STONE.get().getDefaultState()))
				.temperature(0.5f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(153, 50, 204))
				.waterFogColor(NumberUtil.RGB(153, 50, 204))
				.depth(0)
				.scale(1.125f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 175, 175);
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
		for (int i = 0; i < AoAConfig.SERVER.ORES.ghastlyVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.GHASTLY_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.ghastlyMinOresPerVein.get(), AoAConfig.SERVER.ORES.ghastlyMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.GRECKON_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(3, 28), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.ghoulishVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.GHOULISH_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.ghoulishMinOresPerVein.get(), AoAConfig.SERVER.ORES.ghoulishMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.GRECKON_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(30, 60), rand.randomNumberUpTo(16)));
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
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
				world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.HAUNTED_FLOWER.get().getDefaultState(), 2);
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX = basePos.getX() + rand.randomNumberUpTo(16);
		int posZ = basePos.getZ() + rand.randomNumberUpTo(16);
		int posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

		if (world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 7)).getBlock() == surfaceBlock)
			new HauntedTreeGenerator(null).generate(world, rand.source(), pos.up());
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.hauntedMazeSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(5);
			z = basePos.getZ() + rand.randomNumberUpTo(14);
			y = chunkGenerator.getHeight(x + 12, z + 8, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 12, y - 1, z + 8)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("HauntedMaze", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.hauntedLottoRockSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG) + 25;

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("HauntedLottoRock", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.facelessTreeSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("FacelessTree", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.stormRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("StormRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
