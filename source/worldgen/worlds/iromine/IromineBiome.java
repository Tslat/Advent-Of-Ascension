package net.tslat.aoa3.worldgen.worlds.iromine;

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
import net.tslat.aoa3.worldgen.trees.IrodustTreeGenerator;
import net.tslat.aoa3.worldgen.trees.IrogoldTreeGenerator;

public class IromineBiome extends AoABiome {
	public IromineBiome() {
		super(getBuilder());

		addCarver(GenerationStage.Carving.AIR, createCarver(AoAWorldGen.Carvers.IROMINE_CAVES, new ProbabilityConfig(1 / 7f)));
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.IROGRASS.get().getDefaultState(), AoABlocks.IROSTONE.get().getDefaultState(), AoABlocks.IROSTONE.get().getDefaultState()))
				.temperature(0.4f)
				.downfall(0)
				.waterColor(NumberUtil.RGB(255, 249, 173))
				.waterFogColor(NumberUtil.RGB(255, 249, 173))
				.depth(0)
				.scale(0.5F)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE};
	}

	@Override
	public int getSkyColor() {
		return NumberUtil.RGB(0, 0, 0);
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
		for (int i = 0; i < AoAConfig.SERVER.ORES.lyonVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.LYON_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.lyonMinOresPerVein.get(), AoAConfig.SERVER.ORES.lyonMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.IROSTONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(45, 65), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 40; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() == surfaceBlock) {
				switch (rand.randomNumberUpTo(3)) {
					case 0:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.IRO_GRASS.get().getDefaultState(), 2);
						break;
					case 1:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.LURCHIANS.get().getDefaultState(), 2);
						break;
					case 2:
						world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.IROTOPS.get().getDefaultState(), 2);
						break;
				}
			}
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int posX;
		int posY;
		int posZ;

		if (rand.oneInNChance(50)) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 3, posZ + 3, Heightmap.Type.WORLD_SURFACE_WG) + 15 + rand.randomNumberUpTo(5);

			if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR) {
				if (rand.fiftyFifty()) {
					StructuresHandler.generateStructure("IroDoubler", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
				else {
					StructuresHandler.generateStructure("IroFloater", world, rand.source(), pos.setPos(posX, posY, posZ));
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
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = chunkGenerator.getHeight(posX + 4, posZ + 4, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() == surfaceBlock) {
				if (rand.fiftyFifty()) {
					new IrogoldTreeGenerator(null).generate(world, rand.source(), pos.up());
				}
				else {
					new IrodustTreeGenerator(null).generate(world, rand.source(), pos.up());
				}
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		Block surfaceBlock = biome.getSurfaceBuilder().getConfig().getTop().getBlock();
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.iroMazeSpawnChance.get())) {
			x = basePos.getX();
			z = basePos.getZ();
			y = chunkGenerator.getHeight(x + 20, z + 14, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 20, y - 1, z + 14)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("IroMaze", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.chargingPadsSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(13);
			z = basePos.getZ() + rand.randomNumberUpTo(13);
			y = chunkGenerator.getHeight(x + 8, z + 8, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ChargingPads", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.professorsLabSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(13);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 8, z + 5, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 5)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("ProfessorsLab", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.iroPillarSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG);

			if (world.getBlockState(pos.setPos(x + 3, y - 1, z + 3)).getBlock() == surfaceBlock)
				StructuresHandler.generateStructure("IroPillar", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.iroPassageSpawnChance.get())) {
			switch (rand.randomNumberUpTo(4)) {
				case 0:
					x = basePos.getX() + rand.randomNumberUpTo(16);
					z = basePos.getZ() + rand.randomNumberUpTo(16);
					y = chunkGenerator.getHeight(x + 6, z + 6, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(x + 6, y - 1, z + 6)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("EnforcerTower", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 1:
					x = basePos.getX() + rand.randomNumberUpTo(16);
					z = basePos.getZ() + rand.randomNumberUpTo(16);
					y = chunkGenerator.getHeight(x + 5, z + 6, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(x + 5, y - 1, z + 6)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("MechyonTemple", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 2:
					x = basePos.getX() + rand.randomNumberUpTo(16);
					z = basePos.getZ() + rand.randomNumberUpTo(10);
					y = chunkGenerator.getHeight(x + 7, z + 10, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(x + 7, y - 1, z + 10)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("IroPassage1", world, rand.source(), pos.setPos(x, y, z));
					break;
				case 3:
					x = basePos.getX() + rand.randomNumberUpTo(13);
					z = basePos.getZ() + rand.randomNumberUpTo(14);
					y = chunkGenerator.getHeight(x + 8, z + 8, Heightmap.Type.WORLD_SURFACE_WG);

					if (world.getBlockState(pos.setPos(x + 8, y - 1, z + 8)).getBlock() == surfaceBlock)
						StructuresHandler.generateStructure("IroPassage2", world, rand.source(), pos.setPos(x, y, z));
					break;
			}
		}

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.energyRuneShrineSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = chunkGenerator.getHeight(x + 3, z + 3, Heightmap.Type.WORLD_SURFACE_WG) + 20;

			if (world.getBlockState(pos.setPos(x + 3, y, z + 3)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("EnergyRuneShrine", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
