package net.tslat.aoa3.worldgen.worlds.deeplands;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
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
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

public class DeeplandsBiome extends AoABiome {
	public DeeplandsBiome() {
		super(getBuilder());
	}

	private static Builder getBuilder() {
		Builder builder = new Builder();

		builder.precipitation(RainType.NONE)
				.surfaceBuilder(AoAWorldGen.SurfaceBuilders.OCEANLESS, new SurfaceBuilderConfig(AoABlocks.DENSE_STONE.get().getDefaultState(), AoABlocks.DENSE_STONE.get().getDefaultState(), AoABlocks.DENSE_STONE.get().getDefaultState()))
				.temperature(0)
				.downfall(0)
				.waterColor(NumberUtil.RGB(200, 200, 200))
				.waterFogColor(NumberUtil.RGB(200, 200, 200))
				.depth(0)
				.scale(0)
				.category(Category.NONE);

		return builder;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeTypes() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.SPARSE};
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
		for (int i = 0; i < AoAConfig.SERVER.ORES.chargedRuniumVeinsPerChunk.get(); i++) {
			new WorldGenMinable(AoABlocks.CHARGED_RUNIUM_ORE.get().getDefaultState(), rand.randomNumberBetween(AoAConfig.SERVER.ORES.chargedRuniumMinOresPerVein.get(), AoAConfig.SERVER.ORES.chargedRuniumMaxOresPerVein.get() + 1), BlockMatcher.forBlock(AoABlocks.DENSE_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(70, 115), rand.randomNumberUpTo(16)));
		}

		for (int i = 0; i < 4; i++) {
			new WorldGenMinable(AoABlocks.DEEP_CASE.get().getDefaultState(), 5, BlockMatcher.forBlock(AoABlocks.DENSE_STONE.get()))
					.generate(world, rand.source(), basePos.add(rand.randomNumberUpTo(16), rand.randomNumberBetween(70, 115), rand.randomNumberUpTo(16)));
		}
	}

	protected void doPlantGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int posX;
		int posY;
		int posZ;

		for (int i = 0; i < 30; i++) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 20;

			if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
				if (rand.fiftyFifty()) {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DEEP_GRASS.get().getDefaultState(), 2);
				}
				else {
					world.setBlockState(pos.setPos(posX, posY, posZ), AoABlocks.DEEP_BLOOMS.get().getDefaultState(), 2);
				}
			}
		}
	}

	protected void doTreeGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int posX;
		int posY;
		int posZ;

		if (rand.oneInNChance(3)) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 20;

			if (rand.fiftyFifty()) {
				if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR)
					StructuresHandler.generateStructure("Deepshroom1", world, rand.source(), pos.setPos(posX, posY, posZ));
			}
			else {
				if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR)
					StructuresHandler.generateStructure("Deepshroom2", world, rand.source(), pos.setPos(posX, posY, posZ));
			}
		}
	}

	protected void doMiscGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int posX;
		int posY;
		int posZ;

		world.setBlockState(pos.setPos(basePos.getX() + rand.randomNumberUpTo(15), 19, basePos.getZ() + rand.randomNumberUpTo(15)), AoABlocks.DEEP_CRYSTAL.get().getDefaultState(), 2);

		if (rand.oneInNChance(25)) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = rand.randomNumberBetween(19, 21);


			if (world.getBlockState(pos.setPos(posX, 20, posZ)).getBlock() == Blocks.AIR) {
				if (rand.fiftyFifty()) {
					StructuresHandler.generateStructure("DeepSpire1", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
				else {
					StructuresHandler.generateStructure("DeepSpire2", world, rand.source(), pos.setPos(posX, posY, posZ));
				}
			}
		}

		if (rand.oneInNChance(6)) {
			posX = basePos.getX() + rand.randomNumberUpTo(16);
			posZ = basePos.getZ() + rand.randomNumberUpTo(16);
			posY = 20;

			switch (rand.randomNumberUpTo(3)) {
				case 0:
					if (world.getBlockState(pos.setPos(posX + 6, posY, posZ + 6)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("FossilisedRibs1", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 1:
					if (world.getBlockState(pos.setPos(posX + 6, posY, posZ + 6)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("FossilisedRibs2", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
				case 2:
					if (world.getBlockState(pos.setPos(posX + 7, posY, posZ + 7)).getBlock() == Blocks.AIR)
						StructuresHandler.generateStructure("BoneCircle", world, rand.source(), pos.setPos(posX, posY, posZ));
					break;
			}
		}
	}

	protected void doStructureGen(final IWorld world, final Biome biome, final RandomUtil.EasyRandom rand, final BlockPos basePos, final BlockPos.Mutable pos, ChunkGenerator<? extends GenerationSettings> chunkGenerator) {
		int x;
		int z;
		int y;

		((SharedSeedRandom)rand.source()).setLargeFeatureSeed(chunkGenerator.getSeed(), basePos.getX() << 4, basePos.getZ() << 4);

		if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.arocknidCaveSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(15);
			y = 20;

			if (world.getBlockState(pos.setPos(x + 7, y, z + 7)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ArocknidCave", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.chargingStationSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(15);
			y = 20;

			if (world.getBlockState(pos.setPos(x + 7, y, z + 7)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("ChargingStation", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.krorPillarsSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(15);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 20;

			if (world.getBlockState(pos.setPos(x + 7, y, z)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("KrorPillars", world, rand.source(), pos.setPos(x, y, z));
		}
		else if (rand.percentChance(AoAConfig.SERVER.STRUCTURES.deepLottoShelterSpawnChance.get())) {
			x = basePos.getX() + rand.randomNumberUpTo(16);
			z = basePos.getZ() + rand.randomNumberUpTo(16);
			y = 20;

			if (world.getBlockState(pos.setPos(x + 9, y, z + 9)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("DeepLottoShelter", world, rand.source(), pos.setPos(x, y, z));
		}
	}
}
