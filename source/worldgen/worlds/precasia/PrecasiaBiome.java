package net.tslat.aoa3.worldgen.worlds.precasia;

import net.minecraft.block.Block;
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
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;
import net.tslat.aoa3.worldgen.trees.LucalusTreeGenerator;
import net.tslat.aoa3.worldgen.trees.StranglewoodTreeGenerator;

public class PrecasiaBiome extends AoABiome {
	public PrecasiaBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.PRECASIA_CAVES, new ProbabilityConfig(1 / 7f)));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.PRECASIAN_GRASS.get().getDefaultState(), AoABlocks.PRECASIAN_SURFACE_ROCK.get().getDefaultState(), AoABlocks.PRECASIAN_LOWER_ROCK.get().getDefaultState()))
				.temperature(2)
				.downfall(0)
				.waterColor(NumberUtil.RGB(0, 0, 255))
				.waterFogColor(NumberUtil.RGB(0, 0, 255))
				.depth(0)
				.scale(0.4f)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE};
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
		for (int i = 0; i < AoAConfig.SERVER.ORES.skullboneFragmentVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.SKULLBONE_FRAGMENTS_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.skullboneFragmentMinOresPerVein.get(), AoAConfig.SERVER.ORES.skullboneFragmentMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.PRECASIAN_LOWER_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberUpTo(40), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.footboneFragmentVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.FOOTBONE_FRAGMENTS_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.footboneFragmentMinOresPerVein.get(), AoAConfig.SERVER.ORES.footboneFragmentMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.PRECASIAN_LOWER_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberUpTo(40), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.legboneFragmentVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.LEGBONE_FRAGMENTS_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.legboneFragmentMinOresPerVein.get(), AoAConfig.SERVER.ORES.legboneFragmentMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.PRECASIAN_LOWER_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberUpTo(40), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < AoAConfig.SERVER.ORES.chestboneFragmentVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.CHESTBONE_FRAGMENTS_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.chestboneFragmentMinOresPerVein.get(), AoAConfig.SERVER.ORES.chestboneFragmentMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.PRECASIAN_LOWER_ROCK.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberUpTo(40), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 25; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				if (rand.fiftyFifty()) {
					world.setBlockState(pos.up(), AoABlocks.LUCON_GRASS.get().getDefaultState(), 2);
				}
				else {
					world.setBlockState(pos.up(), AoABlocks.TANGLE_THORNS.get().getDefaultState(), 2);
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 3; i++) {
			switch (rand.randomNumberUpTo(3)) {
				case 0:
				case 1:
					posX = basePos.getX() + rand.randomNumberUpTo(14);
					posZ = basePos.getZ() + rand.randomNumberUpTo(14);
					posY = chunkGenerator.getHeight(posX + 8, posZ + 8, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 8, posY - 1, posZ + 8)).getBlock() == surfaceBlock)
						new StranglewoodTreeGenerator(null).generate(world, rand.source(), pos.up());
					break;
				case 2:
					posX = basePos.getX() + rand.randomNumberUpTo(16);
					posZ = basePos.getZ() + rand.randomNumberUpTo(16);
					posY = chunkGenerator.getHeight(posX + 6, posZ + 6, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)).getBlock() == surfaceBlock)
						new LucalusTreeGenerator(null).generate(world, rand.source(), pos.up());
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

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.kaiyuTempleSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = chunkGenerator.getHeight(x + 22, z + 22, Heightmap.Type.WORLD_SURFACE_WG);

			StructuresHandler.generateStructure("KaiyuTemple", world, rand.source(), pos.setPos(x, y - 30, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.skeletalArmyArenaSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ() + rand.randomNumberUpTo(7);
			y = chunkGenerator.getHeight(x + 22, z + 13, Heightmap.Type.WORLD_SURFACE_WG);

			Block block1 = world.getBlockState(pos.setPos(x + 1, y - 1, z + 8)).getBlock();
			Block block2 = world.getBlockState(pos.setPos(x + 28, y - 1, z + 11)).getBlock();

			if ((block1.getBlock() == surfaceBlock.getBlock() || block1 == AoABlocks.PRECASIAN_SURFACE_ROCK.get()) && (block2.getBlock() == surfaceBlock.getBlock() || block2 == AoABlocks.PRECASIAN_SURFACE_ROCK.get()))
				StructuresHandler.generateStructure("SkeletalArmyArena", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.jungleLottoHutSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("JungleLottoHut", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.opteryxNestSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 4, z + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 4, y - 1, z + 4)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("OpteryxNest", world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.precasianDenSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(2);
			z = basePos.getZ();
			y = rand.randomNumberBetween(5, 20);

			AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

			switch (rand.randomNumberUpTo(3)) {
				case 0:
					structure = StructuresHandler.getStructure("DiocusDen");
					break;
				case 1:
					structure = StructuresHandler.getStructure("IosaurDen");
					break;
				case 2:
					structure = StructuresHandler.getStructure("SpinoledonDen");
					break;
			}

			StructuresHandler.generateStructure(structure, world, rand.source(), pos.setPos(x, y, z));
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.lifeRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			StructuresHandler.generateStructure("LifeRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
