package net.tslat.aoa3.dimension.creeponia.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.worldgen.trees.WorldGenCreepTree;

import java.awt.*;
import java.util.Random;

public class BiomeCreeponia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Creeponia");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.4f);
		properties.setRainfall(100);
		properties.setWaterColor(Color.GREEN.getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.25f);
	}

	public BiomeCreeponia() {
		super(properties);
		setRegistryName("aoa3", "creeponia");
		this.topBlock = BlockRegister.getUnmappedBlock("creeponia_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("creeponia_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeCreeponiaDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.FOREST);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.YELLOW.getRGB();
	}

	public class BiomeCreeponiaDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.ornamyte.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.ORNAMYTE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.ornamyte.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.ornamyte.maxOresPerVein) + 1), block -> block.getBlock() == BlockRegister.CREEP_STONE || block.getBlock() == BlockRegister.PRIMED_STONE || block.getBlock() == BlockRegister.UNSTABLE_STONE).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(12) + 3, rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.gemenyte.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.GEMENYTE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.gemenyte.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.gemenyte.maxOresPerVein) + 1), block -> block.getBlock() == BlockRegister.CREEP_STONE || block.getBlock() == BlockRegister.PRIMED_STONE || block.getBlock() == BlockRegister.UNSTABLE_STONE).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(15) + 22, rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.jewelyte.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.JEWELYTE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.jewelyte.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.jewelyte.maxOresPerVein) + 1), block -> block.getBlock() == BlockRegister.CREEP_STONE || block.getBlock() == BlockRegister.PRIMED_STONE || block.getBlock() == BlockRegister.UNSTABLE_STONE).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(15) + 22, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 60; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					if (rand.nextBoolean()) {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.CREEP_GRASS.getDefaultState());
					}
					else {
						world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.CREEP_FLOWERS.getDefaultState());
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 7; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX + 3, posZ + 3);

				if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)) == biome.topBlock)
					new WorldGenCreepTree(null).generate(world, rand, pos.up());
			}
		}
	}
}
