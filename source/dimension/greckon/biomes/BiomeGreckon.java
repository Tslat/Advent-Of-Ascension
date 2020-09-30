package net.tslat.aoa3.dimension.greckon.biomes;

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
import net.tslat.aoa3.worldgen.trees.WorldGenHauntedTree;

import java.awt.*;
import java.util.Random;

public class BiomeGreckon extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Greckon");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(500);
		properties.setWaterColor(new Color(153, 50, 204).getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.1f);
	}

	public BiomeGreckon() {
		super(properties);
		setRegistryName("aoa3", "greckon");
		this.topBlock = BlockRegister.getUnmappedBlock("greckon_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("greckon_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeGreckonDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.PINK.getRGB();
	}

	public class BiomeGreckonDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.ghastly.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.GHASTLY_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.ghastly.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.ghastly.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.GRECKON_STONE)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(25) + 3, rand.nextInt(16)));
			}

			for (int i = 0; i < ConfigurationUtil.OreConfig.ghoulish.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.GHOULISH_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.ghoulish.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.ghoulish.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.GRECKON_STONE)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(30) + 30, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 15; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
					world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.HAUNTED_FLOWER.getDefaultState());
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			posX = basePos.getX() + rand.nextInt(16);
			posZ = basePos.getZ() + rand.nextInt(16);
			posY = world.getHeight(posX + 7, posZ + 7);

			if (world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 7)) == biome.topBlock)
				new WorldGenHauntedTree(null).generate(world, rand, pos.up());
		}
	}
}
