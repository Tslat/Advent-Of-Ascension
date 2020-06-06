package net.tslat.aoa3.dimension.runandor.biomes;

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
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.worldgen.trees.WorldGenRunicTree;

import java.awt.*;
import java.util.Random;

public class BiomeRunandor extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Runandor");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(0f);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.4f);
	}

	public BiomeRunandor() {
		super(properties);
		setRegistryName("aoa3", "runandor");
		this.topBlock = BlockRegister.getUnmappedBlock("runic_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("runic_stone").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeRunandorDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.MOUNTAIN);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}

	public class BiomeRunandorDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(World world, Biome biome, Random rand, BlockPos basePos, BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.elecanium.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.ELECANIUM_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.elecanium.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.elecanium.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.RUNIC_STONE))
						.generate(world, rand, basePos.add(rand.nextInt(14) + 1, rand.nextInt(35) + 1, rand.nextInt(14) + 1));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 85; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(3)) {
						case 0:
							world.setBlockState(pos.up(), BlockRegister.RUNIC_BUSH.getDefaultState());
							break;
						case 1:
							world.setBlockState(pos.up(), BlockRegister.RUNE_BULBS.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos.up(), BlockRegister.MAGIAS.getDefaultState());
							break;
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX + 4, posZ + 4);

				if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)) == biome.topBlock)
					new WorldGenRunicTree(null).generate(world, rand, pos.up());
			}
		}
	}
}
