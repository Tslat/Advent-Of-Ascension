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
		if (ConfigurationUtil.amethystVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.amethystVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(40);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreAmethyst.getDefaultState(), Math.max(ConfigurationUtil.amethystMinOres, rand.nextInt(ConfigurationUtil.amethystMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.rositeVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.rositeVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(25);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreRosite.getDefaultState(), Math.max(ConfigurationUtil.rositeMinOres, rand.nextInt(ConfigurationUtil.rositeMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.limoniteVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.limoniteVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(50);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreLimonite.getDefaultState(), Math.max(ConfigurationUtil.limoniteMinOres, rand.nextInt(ConfigurationUtil.limoniteMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.runiumVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.runiumVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(20);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreRunium.getDefaultState(), Math.max(ConfigurationUtil.runiumMinOres, rand.nextInt(ConfigurationUtil.runiumMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.jadeVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.jadeVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(20);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreJade.getDefaultState(), Math.max(ConfigurationUtil.jadeMinOres, rand.nextInt(ConfigurationUtil.jadeMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}

		if (ConfigurationUtil.sapphireVeinCount > 0) {
			for (int i = 0; i < rand.nextInt(ConfigurationUtil.sapphireVeinCount); i++) {
				int posX = chunkX + rand.nextInt(16);
				int posY = rand.nextInt(10);
				int posZ = chunkZ + rand.nextInt(16);

				new WorldGenMinable(BlockRegister.oreSapphire.getDefaultState(), Math.max(ConfigurationUtil.sapphireMinOres, rand.nextInt(ConfigurationUtil.sapphireMaxOres))).generate(world, rand, new BlockPos(posX, posY, posZ));
			}
		}
	}

	private static void doStructureGen(World world, Random rand, int chunkX, int chunkZ) {
		Biome biome = world.getBiome(new BlockPos(chunkX, 0, chunkZ));

		if (biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN) {
			if (ConfigurationUtil.spawnChanceAmphibiyteCove > 0 && rand.nextInt(ConfigurationUtil.spawnChanceAmphibiyteCove) == 0) {
				int posX = chunkX + rand.nextInt(13);
				int posZ = chunkZ + rand.nextInt(12);
				int posY = world.getHeight(posX + 8, posZ + 8);

				Block block = world.getBlockState(new BlockPos(posX + 8, posY - 1, posZ + 9)).getBlock();

				if (block == Blocks.SAND || block == Blocks.GRAVEL)
					StructuresHandler.generateStructure("AmphibiyteCove", world, rand, new BlockPos(posX, posY - 8, posZ));
			}
		}

		if (ConfigurationUtil.spawnChanceRuinedTeleporterFrame > 0 && rand.nextInt(ConfigurationUtil.spawnChanceRuinedTeleporterFrame) == 0) {
			int posX = chunkX + rand.nextInt(16);
			int posZ = chunkZ + rand.nextInt(15);
			int posY = rand.nextInt(10) + 10;

			if (world.getBlockState(new BlockPos(posX + 5, posY, posZ + 7)).getBlock() != Blocks.AIR)
				StructuresHandler.generateStructure("RuinedTeleporterFrame", world, rand, new BlockPos(posX, posY, posZ));
		}

		if (ConfigurationUtil.spawnChanceWindRuneShrine > 0 && rand.nextInt(ConfigurationUtil.spawnChanceWindRuneShrine) == 0) {
			int posX = chunkX + rand.nextInt(16);
			int posZ = chunkZ + rand.nextInt(16);
			int posY = world.getHeight(posX, posZ);

			if (posY >= 95 && world.getBlockState(new BlockPos(posX + 3, posY + 30, posZ + 3)).getBlock() == Blocks.AIR) {
				StructuresHandler.generateStructure("WindRuneShrine", world, rand, new BlockPos(posX, Math.min(posY + 30, 240), posZ));
			}
		}

	}
}
