package net.tslat.aoa3.dimension.haven.biomes;

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
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.worldgen.trees.WorldGenHavenTree;

import java.awt.*;
import java.util.Random;

public class BiomeHaven extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Haven");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.5f);
		properties.setRainfall(0f);
		properties.setRainDisabled();
		properties.setBaseHeight(0.1f);
		properties.setHeightVariation(0.2f);
	}

	public BiomeHaven() {
		super(properties);
		setRegistryName("aoa3", "haven");
		this.topBlock = BlockRegister.getUnmappedBlock("haven_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("haven_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeHavenDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.VOID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.GREEN.getRGB();
	}

	public class BiomeHavenDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.crystallite.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.CRYSTALLITE_ORE.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.crystallite.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.crystallite.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.HAVEN_STONE)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(40) + 30, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 50; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					switch (rand.nextInt(7)) {
						case 0:
						case 1:
						case 2:
						case 3:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.HAVEN_GRASS_PLANT.getDefaultState());
							break;
						case 4:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.DAYLOOMS_BLUE.getDefaultState());
							break;
						case 5:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.DAYLOOMS_YELLOW.getDefaultState());
							break;
						case 6:
							world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.DAYLOOMS_PINK.getDefaultState());
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

					switch (rand.nextInt(3)) {
						case 0:
							structure = StructuresHandler.getStructure("BlueHavendales");
							break;
						case 1:
							structure = StructuresHandler.getStructure("PinkHavendales");
							break;
						case 2:
							structure = StructuresHandler.getStructure("YellowHavendales");
							break;
					}

					StructuresHandler.generateStructure(structure, world, rand, pos.setPos(posX, posY, posZ));
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 3; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);

				posY = world.getHeight(posX + 7, posZ + 7);

				if (world.getBlockState(pos.setPos(posX + 7, posY - 1, posZ + 7)) == biome.topBlock)
					new WorldGenHavenTree(null, null, rand).generate(world, rand, pos);
			}
		}
	}
}
