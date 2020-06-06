package net.tslat.aoa3.dimension.overworld;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.Random;

public class OverworldGen {
	public static void generate(World world, Random random, int chunkX, int chunkZ, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		doOreGen(world, random, chunkX * 16, chunkZ * 16);
		doStructureGen(world, random, chunkX * 16 + 1, chunkZ * 16 + 1);
	}

	private static void doOreGen(World world, Random rand, int chunkX, int chunkZ) {
		if (ConfigurationUtil.OreConfig.amethyst.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.amethyst.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 14 + rand.nextInt(17);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.AMETHYST_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.amethyst.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.amethyst.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.OreConfig.rosite.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.rosite.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 17 + rand.nextInt(31);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.ROSITE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.rosite.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.rosite.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.OreConfig.limonite.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.limonite.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 8 + rand.nextInt(60);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.LIMONITE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.limonite.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.limonite.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.OreConfig.runium.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.runium.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 5 + rand.nextInt(128);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.RUNIUM_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.runium.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.runium.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.OreConfig.jade.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.jade.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 7 + rand.nextInt(13);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.JADE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.jade.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.jade.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.OreConfig.sapphire.maxVeinsPerChunk > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.OreConfig.sapphire.maxVeinsPerChunk); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = 4 + rand.nextInt(8);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.SAPPHIRE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.sapphire.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.sapphire.maxOresPerVein) + 1)).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}
	}

	private static void doStructureGen(World world, Random rand, int chunkX, int chunkZ) {
		Biome biome = world.getBiome(new BlockPos(chunkX, 0, chunkZ));

		if (biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN) {
			if (ConfigurationUtil.StructureConfig.overworld.amphibiyteCoveSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.overworld.amphibiyteCoveSpawnChance) == 0) {
				int posX = chunkX + rand.nextInt(13);
				int posZ = chunkZ + rand.nextInt(12);
				int posY = world.getHeight(posX + 8, posZ + 8);

				Block block = world.getBlockState(new BlockPos(posX + 8, posY - 1, posZ + 9)).getBlock();

				if (block == Blocks.SAND || block == Blocks.GRAVEL)
					StructuresHandler.generateStructure("AmphibiyteCove", world, rand, new BlockPos(posX, posY - 8, posZ));
			}
		}

		if (ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance) == 0) {
			int posX = chunkX + rand.nextInt(16);
			int posZ = chunkZ + rand.nextInt(15);
			int posY = rand.nextInt(10) + 10;

			if (world.getBlockState(new BlockPos(posX + 5, posY, posZ + 7)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("RuinedTeleporterFrame", world, rand, new BlockPos(posX, posY, posZ));
		}

		if (ConfigurationUtil.StructureConfig.overworld.windRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.overworld.windRuneShrineSpawnChance) == 0) {
			int posX = chunkX + rand.nextInt(16);
			int posZ = chunkZ + rand.nextInt(16);
			int posY = world.getHeight(posX, posZ);

			if (posY >= 95 && world.getBlockState(new BlockPos(posX + 3, posY + 30, posZ + 3)).getBlock() == Blocks.AIR) {
				StructuresHandler.generateStructure("WindRuneShrine", world, rand, new BlockPos(posX, Math.min(posY + 30, 240), posZ));
			}
		}

	}
}
