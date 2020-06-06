package net.tslat.aoa3.dimension.nether;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.Random;

public class NetherGen {
	public static void generate(World world, Random random, int chunkX, int chunkZ, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		doOreGen(world, random, chunkX * 16, chunkZ * 16);
		doStructureGen(world, random, chunkX * 16 + 1, chunkZ * 16 + 1);
	}

	private static void doOreGen(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < ConfigurationUtil.OreConfig.emberstone.veinsPerChunk; i++) {
			int posX = chunkX + rand.nextInt(16);
			int posY = 5 + rand.nextInt(120);
			int posZ = chunkZ + rand.nextInt(16);

			new WorldGenMinable(BlockRegister.EMBERSTONE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.emberstone.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.emberstone.maxOresPerVein) + 1), BlockMatcher.forBlock(Blocks.NETHERRACK)).generate(world, rand, new BlockPos(posX, posY, posZ));
		}
	}

	private static void doStructureGen(World world, Random rand, int baseX, int baseZ) {
		if (ConfigurationUtil.StructureConfig.nether.nethengeicPitSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.nether.nethengeicPitSpawnChance) == 0) {
			int posX = baseX + rand.nextInt(14);
			int posZ = baseZ + rand.nextInt(14);
			int posY = rand.nextInt(35) + 25;

			if (world.getBlockState(new BlockPos(posX, posY - 1, posZ)).getBlock() == Blocks.NETHERRACK)
				StructuresHandler.generateStructure("NethengeicPit", world, rand, new BlockPos(posX, posY, posZ));
		}

		if (ConfigurationUtil.StructureConfig.nether.fireRuneShrineSpawnChance > 0 && rand.nextInt(ConfigurationUtil.StructureConfig.nether.fireRuneShrineSpawnChance) == 0) {
			int posX = baseX + rand.nextInt(16);
			int posZ = baseZ + rand.nextInt(16);
			int posY = rand.nextInt(50) + 10;

			if (world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR)
				StructuresHandler.generateStructure("FireRuneShrine", world, rand, new BlockPos(posX, posY, posZ));
		}
	}
}
