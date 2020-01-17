package net.tslat.aoa3.dimension.abyss.biomes;

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

public class BiomeAbyss extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Abyssal Wastelands");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0f);
		properties.setRainfall(100);
		properties.setWaterColor(Color.RED.getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.4f);
	}

	public BiomeAbyss() {
		super(properties);
		setRegistryName("aoa3", "abyss");
		this.topBlock = BlockRegister.getUnmappedBlock("abyss_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("abyss_stone").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeAbyssDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SPOOKY);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.RED.getRGB();
	}

	public class BiomeAbyssDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.bloodstone.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreBloodstone.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.bloodstone.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.bloodstone.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stoneAbyss))
						.generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(5) + 45, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 20; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
					world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantTubeicles.getDefaultState());
			}

			for (int i = 0; i < 4; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(5)) {
						case 0:
						case 1:
							StructuresHandler.generateStructure("BloodPine", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 2:
						case 3:
							StructuresHandler.generateStructure("EyeShrub", world, rand, pos.setPos(posX, posY, posZ));
							break;
						case 4:
							StructuresHandler.generateStructure("BloodSpikes", world, rand, pos.setPos(posX, posY, posZ));
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 2; i++) {
				switch (rand.nextInt(11)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 1, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 2, posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree4", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ + 1);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree5", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ + 1);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree6", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 6:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX, posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure("TentacleTree7", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 7:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("AbyssalTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 8:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 5, posZ + 5);

						if (world.getBlockState(pos.setPos(posX + 5, posY - 1, posZ + 5)) == biome.topBlock)
							StructuresHandler.generateStructure("AbyssalTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 9:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("AbyssalTree3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 10:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
							StructuresHandler.generateStructure("AbyssalTree4", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
