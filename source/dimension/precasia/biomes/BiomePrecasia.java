package net.tslat.aoa3.dimension.precasia.biomes;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.Random;

public class BiomePrecasia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Precasian Forest");

	static {
		properties.setRainDisabled();
		properties.setTemperature(2.0f);
		properties.setRainfall(100);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.4f);
	}

	public BiomePrecasia() {
		super(properties);
		setRegistryName("aoa3", "precasia");
		this.topBlock = BlockRegister.grassPrecasia.getDefaultState();
		this.fillerBlock = BlockRegister.stonePrecasiaHigh.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomePrecasiaDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SPARSE);
	}

	public class BiomePrecasiaDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.skullboneFragments.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreSkullboneFragments.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.skullboneFragments.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.skullboneFragments.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stonePrecasiaLow)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(40), rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.footboneFragments.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreFootboneFragments.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.footboneFragments.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.footboneFragments.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stonePrecasiaLow)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(40), rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.legboneFragments.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreLegboneFragments.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.legboneFragments.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.legboneFragments.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stonePrecasiaLow)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(40), rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.chestboneFragments.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreChestboneFragments.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.chestboneFragments.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.chestboneFragments.maxOresPerVein)), BlockMatcher.forBlock(BlockRegister.stonePrecasiaLow)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(40), rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 25; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					if (rand.nextBoolean()) {
						world.setBlockState(pos.up(), BlockRegister.plantLuconGrass.getDefaultState());
					}
					else {
						world.setBlockState(pos.up(), BlockRegister.plantTangleThorns.getDefaultState());
					}
				}
			}

		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 3; i++) {
				switch (rand.nextInt(4)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(14);
						posZ = basePos.getZ() + rand.nextInt(14);
						posY = world.getHeight(posX + 8, posZ + 8);

						if (world.getBlockState(pos.setPos(posX + 8, posY - 1, posZ + 8)) == biome.topBlock)
							StructuresHandler.generateStructure("StranglewoodTree1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 6, posZ + 5);

						if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 5)) == biome.topBlock)
							StructuresHandler.generateStructure("StranglewoodTree2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(14);
						posZ = basePos.getZ() + rand.nextInt(14);
						posY = world.getHeight(posX + 8, posZ + 8);

						if (world.getBlockState(pos.setPos(posX + 8, posY - 1, posZ + 8)) == biome.topBlock)
							StructuresHandler.generateStructure("StranglewoodTree3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = world.getHeight(posX + 6, posZ + 6);

						if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)) == biome.topBlock)
							StructuresHandler.generateStructure("LucalusTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
