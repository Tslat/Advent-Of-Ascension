package net.tslat.aoa3.dimension.barathos.biomes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.awt.*;
import java.util.Random;

public class BiomeBarathos extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Barathos");

	static {
		properties.setRainDisabled();
		properties.setTemperature(2.0f);
		properties.setRainfall(500);
		properties.setRainDisabled();
		properties.setBaseHeight(0.0f);
		properties.setHeightVariation(0.35f);
	}

	public BiomeBarathos() {
		super(properties);
		setRegistryName("aoa3", "barathos");
		topBlock = BlockRegister.getUnmappedBlock("baron_ground").getDefaultState();
		fillerBlock = BlockRegister.getUnmappedBlock("baron_stone").getDefaultState();
		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		this.decorator = new BiomeBarathosDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.ORANGE.getRGB();
	}

	public static class BiomeBarathosDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.varsium.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.VARSIUM_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.varsium.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.varsium.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.BARON_STONE))
						.generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(38) + 25, rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.baronyte.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.BARONYTE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.baronyte.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.baronyte.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.BARON_STONE)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(10) + 25, rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.blazium.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.BLAZIUM_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.blazium.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.blazium.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.BARATHOS_HELLSTONE)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(20), rand.nextInt(16)));
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			IBlockState testBlock;

			switch (rand.nextInt(4)) {
				case 0:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 3, posZ + 2);

					testBlock = world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 2));

					if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
						StructuresHandler.generateStructure("SmallBaronRock1", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 1:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 1, posZ + 3);

					testBlock = world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 3));

					if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
						StructuresHandler.generateStructure("SmallBaronRock2", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 2:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 2, posZ + 1);

					testBlock = world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1));

					if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
						StructuresHandler.generateStructure("SmallBaronRock3", world, rand, pos.setPos(posX, posY, posZ));
					break;
				case 3:
					posX = basePos.getX() + rand.nextInt(16);
					posZ = basePos.getZ() + rand.nextInt(16);
					posY = world.getHeight(posX + 1, posZ + 1);

					testBlock = world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1));

					if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
						StructuresHandler.generateStructure("SmallBaronRock4", world, rand, pos.setPos(posX, posY, posZ));
					break;
			}

			if (rand.nextInt(15) == 0) {
				switch (rand.nextInt(8)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 4, posZ + 5);

						testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock1", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 5, posZ + 5);

						testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock2", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 4, posZ + 4);

						testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock3", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 5, posZ + 5);

						testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock4", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(15);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 5, posZ + 5);

						testBlock = world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock5", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 7, posZ + 4);

						testBlock = world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 4));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock6", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 6:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 4, posZ + 4);

						testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock7", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
					case 7:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 4, posZ + 5);

						testBlock = world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 5));

						if (testBlock == biome.topBlock || testBlock == biome.fillerBlock)
							StructuresHandler.generateStructure("BaronRock8", world, rand, pos.setPos(posX, posY - 1, posZ));
						break;
				}
			}
		}
	}
}
