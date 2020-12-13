package net.tslat.aoa3.worldgen.worlds.creeponia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
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
import net.tslat.aoa3.worldgen.trees.CreepTreeGenerator;

public class CreeponiaBiome extends AoABiome {
	public CreeponiaBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.CREEPONIA_CAVES, new ProbabilityConfig(1 / 7f)));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.TIERED,
						new TieredSurfaceBuilderConfig(
								AoABlocks.CREEP_GRASS.get().getDefaultState(),
								AoABlocks.CREEP_DIRT.get().getDefaultState(),
								new Tuple<BlockState, Integer>(AoABlocks.PRIMED_STONE.get().getDefaultState(), 20),
								new Tuple<BlockState, Integer>(AoABlocks.PRESSED_CREEP_STONE.get().getDefaultState(), 40),
								new Tuple<BlockState, Integer>(AoABlocks.CREEP_STONE.get().getDefaultState(), 256)
								))
				.temperature(0.4f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 255, 0))
				.waterFogColor(NumberUtil.RGB(0, 255, 0))
				.depth(0)
				.scale(0.25f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.MAGICAL};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 255, 0);
	}

	@Override
	public void decorate(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> chunkGenerator, IWorld world, long seed, SharedSeedRandom rand, BlockPos pos) {
		super.decorate(stage, chunkGenerator, world, seed, rand, pos);

		Biome biome = world.getBiomeManager().getBiome(pos);
		RandomUtil.EasyRandom random = new RandomUtil.EasyRandom(rand);

		if (stage == GenerationStage.Decoration.UNDERGROUND_ORES) {
			doOreGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.VEGETAL_DECORATION) {
			doPlantGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
			doTreeGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}
	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int i = 0; i < AoAConfig.SERVER.ORES.ornamyteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.ORNAMYTE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.ornamyteMinOresPerVein.get(), AoAConfig.SERVER.ORES.ornamyteMaxOresPerVein.get() + 1), block -> block.getBlock() == AoABlocks.CREEP_STONE.get() || block.getBlock() == AoABlocks.PRIMED_STONE.get() || block.getBlock() == AoABlocks.PRESSED_CREEP_STONE.get())
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(3, 15), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.gemenyteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.GEMENYTE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.gemenyteMinOresPerVein.get(), AoAConfig.SERVER.ORES.gemenyteMaxOresPerVein.get() + 1), block -> block.getBlock() == AoABlocks.CREEP_STONE.get() || block.getBlock() == AoABlocks.PRIMED_STONE.get() || block.getBlock() == AoABlocks.PRESSED_CREEP_STONE.get())
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(22, 37), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.jewelyteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.JEWELYTE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.jewelyteMinOresPerVein.get(), AoAConfig.SERVER.ORES.jewelyteMaxOresPerVein.get() + 1), block -> block.getBlock() == AoABlocks.CREEP_STONE.get() || block.getBlock() == AoABlocks.PRIMED_STONE.get() || block.getBlock() == AoABlocks.PRESSED_CREEP_STONE.get())
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(22, 37), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < 128; i++) {
			int posX = basePos.getX() + rand.randomNumberUpTo(16);
			int posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			int posY = rand.randomNumberBetween(2, 50);
			BlockPos blockPos = new BlockPos(posX, posY, posZ);
			Block bl = world.getBlockState(blockPos).getBlock();

			if (bl == AoABlocks.CREEP_STONE.get() || bl == AoABlocks.PRESSED_CREEP_STONE.get() || bl == AoABlocks.PRIMED_STONE.get())
				world.setBlockState(blockPos, AoABlocks.CREEP_CRYSTAL.get().getDefaultState(), 2);
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
				if (rand.fiftyFifty()) {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.CREEP_BUSH.get().getDefaultState(), 2);
				}
				else {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.CREEP_FLOWERS.get().getDefaultState(), 2);
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 7; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
				new CreepTreeGenerator(null).generate(world, rand.source(), pos.up());
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.creeperHQSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(6);
			z = basePos.getZ() + rand.randomNumberUpTo(6);
			y = chunkGenerator.getHeight(x + 9, z + 10, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 9, y - 1, z + 10)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CreeperHQ", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.creeponianLottoStandSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 3, y - 1, z + 3)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CreeponianLottoStand", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.explosivesTowerSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 3, y - 1, z + 3)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ExplosivesTower", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.creeponiaBankSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 6, z + 6, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("CreeponiaBank", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
