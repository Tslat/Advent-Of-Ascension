package net.tslat.aoa3.worldgen.worlds.haven;

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
import net.tslat.aoa3.worldgen.trees.HavenTreeGenerator;

public class HavenBiome extends AoABiome {
	public HavenBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.WEIGHTLESS_GRASS.get().getDefaultState(), AoABlocks.WEIGHTLESS_DIRT.get().getDefaultState(), AoABlocks.WEIGHTLESS_STONE.get().getDefaultState()))
				.temperature(0.5f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0.1f)
				.scale(0.2f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID};
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
		else if (stage == GenerationStage.Decoration.UNDERGROUND_ORES) {
			doOreGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
		else if (stage == GenerationStage.Decoration.SURFACE_STRUCTURES) {
			doStructureGen(world, biome, random, pos, new BlockPos.Mutable(), chunkGenerator);
		}
	}

	protected void doOreGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		for (int i = 0; i < AoAConfig.SERVER.ORES.crystalliteVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.CRYSTALLITE_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.crystalliteMinOresPerVein.get(), AoAConfig.SERVER.ORES.crystalliteMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.WEIGHTLESS_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(30, 70), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 50; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(7)) {
					case 0:
					case 1:
					case 2:
					case 3:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.HAVEN_GRASS_PLANT.get().getDefaultState(), 2);
						break;
					case 4:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.BLUE_DAYLOOMS.get().getDefaultState(), 2);
						break;
					case 5:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.YELLOW_DAYLOOMS.get().getDefaultState(), 2);
						break;
					case 6:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.PINK_DAYLOOMS.get().getDefaultState(), 2);
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.randomNumberUpTo(3)) {
					case 0:
						structure = StructuresHandler.getStructure("BlueHavendales");
						break;
					case 1:
						structure = StructuresHandler.getStructure("PinkHavendales");
						break;
					case 2:
						structure = StructuresHandler.getStructure("YellowHavendales");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(posX, posY, posZ));
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 3; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);

			posY = chunkGenerator.getHeight(posX + 7, posZ + 7, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 7)).getBlock() == surfaceBlock)
				new HavenTreeGenerator(null, null, rand.source()).generate(world, rand.source(), pos);
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.dawnlightDungeonSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(10);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(10, 35);

			if (world.getBlockState(pos.setPos(x + 10, y + 7, z + 5)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("DawnlightDungeon", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.floatingLottoFountainSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = rand.randomNumberBetween(10, 50);

			if (world.getBlockState(pos.setPos(x + 3, y + 8, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("FloatingLottoFountain", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.guardianTowerSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 14, y - 1, z + 14)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("GuardianTower", world, rand.source(), pos.setPos(x, y - 1, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.strikeRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("StrikeRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
