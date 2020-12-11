package net.tslat.aoa3.worldgen.worlds.abyss;

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
import net.minecraft.world.gen.feature.ProbabilityConfig;
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
import net.tslat.aoa3.worldgen.trees.BloodtwisterTreeGenerator;
import net.tslat.aoa3.worldgen.trees.EyeHangerTreeGenerator;
import net.tslat.aoa3.worldgen.trees.EyebushTreeGenerator;
import net.tslat.aoa3.worldgen.trees.ShadowTreeGenerator;

public class AbyssBiome extends AoABiome {
	public AbyssBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.ABYSS_CAVES, new ProbabilityConfig(1 / 7f)));
	}

	private static Biome.Builder getBuilder() {
		Biome.Builder builder = new Biome.Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.ABYSSAL_GRASS.get().getDefaultState(), AoABlocks.ABYSSAL_STONE.get().getDefaultState(), AoABlocks.ABYSSAL_STONE.get().getDefaultState()))
				.temperature(0)
				.downfall(100)
				.waterColor(NumberUtil.RGB(255, 0, 0))
				.waterFogColor(NumberUtil.RGB(255, 0, 0))
				.depth(0)
				.scale(0.4f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(255, 0, 0);
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
		for (int i = 0; i < AoAConfig.SERVER.ORES.bloodstoneVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.BLOODSTONE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.bloodstoneMinOresPerVein.get(), AoAConfig.SERVER.ORES.bloodstoneMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.ABYSSAL_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(45, 50), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 20; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
				world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.TUBEICLES.get().getDefaultState(), 2);
		}

		for (int i = 0; i < 4; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(5)) {
					case 0:
					case 1:
						StructuresHandler.generateStructure("BloodPine", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 2:
					case 3:
						StructuresHandler.generateStructure("EyeShrub", world, rand.source(), pos.setPos(posX, posY, posZ));
						break;
					case 4:
						StructuresHandler.generateStructure("BloodSpikes", world, rand.source(), pos.setPos(posX, posY, posZ));
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
			switch (rand.randomNumberUpTo(11)) {
				case 0:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 1, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree3", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 2, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree4", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 4:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree5", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 5:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ + 1, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree6", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 6:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("TentacleTree7", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 7:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
						new BloodtwisterTreeGenerator(null).generate(world, rand.source(), pos.add(3, 0, 3));
					break;
				case 8:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 5, posZ + 5, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5)).getBlock() == surfaceBlock)
						new ShadowTreeGenerator(null).generate(world, rand.source(), pos.add(5, 0, 5));
					break;
				case 9:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
						new EyebushTreeGenerator(null).generate(world, rand.source(), pos.add(3, 0, 3));
					break;
				case 10:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() == surfaceBlock)
						new EyeHangerTreeGenerator(null).generate(world, rand.source(), pos.add(3, 0, 3));
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

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.illusionTreeSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(11);
			z = basePos.getZ() + rand.randomNumberUpTo(10);
			y = chunkGenerator.getHeight(x + 9, z + 9, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 9, y - 1, z + 9)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("IllusionTree", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.jaweHutSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 5, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("JaweHut", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.fleshTempleSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x, z + 6, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x, y - 1, z + 6)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("FleshTemple", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.abyssalLottoHutSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 2, z + 2, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			if (world.getBlockState(pos.setPos(x + 2, y + 3, z + 2)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("AbyssalLottoHut", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.shadowlordPlatformSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 2, z + 2, Heightmap.Type.WORLD_SURFACE_WG) + 5 + rand.randomNumberUpTo(5);

			if (world.getBlockState(pos.setPos(x + 2, y, z + 2)).getMaterial().isReplaceable())
				StructuresHandler.generateStructure("ShadowlordPlatform", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.eyeBulbGrottoSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 1, z + 1, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 1, y - 1, z + 1)).getBlock() == surfaceBlock && !world.isAirBlock(pos.north()) && !world.isAirBlock(pos.south()) && !world.isAirBlock(pos.east()) && !world.isAirBlock(pos.west()))
				StructuresHandler.generateStructure("EyeBulbGrotto", world, rand.source(), pos.setPos(x, y - 1, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.witherRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 12;

			if (world.getBlockState(pos.setPos(x, y, z)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("WitherRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
