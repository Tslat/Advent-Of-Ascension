package net.tslat.aoa3.dimension.shyrelands.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoABiomeDecorator;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.worldgen.trees.WorldGenShyreTree;

import java.awt.*;
import java.util.Random;

public class BiomeShyrelands extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Shyrelands");

	static {
		properties.setRainDisabled();
		properties.setTemperature(2.0f);
		properties.setRainfall(500f);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.1f);
	}

	public BiomeShyrelands() {
		super(properties);
		setRegistryName("aoa3", "shyrelands");
		this.topBlock = BlockRegister.getUnmappedBlock("shyrelands_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("shyrelands_stone").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeShyrelandsDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}

	public class BiomeShyrelandsDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < Math.max(ConfigurationUtil.OreConfig.shyre.minBlocksPerChunk, rand.nextInt(ConfigurationUtil.OreConfig.shyre.maxBlocksPerChunk)); i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = rand.nextInt(25) + 2;

				if (world.getBlockState(pos.setPos(posX, posY, posZ)) == biome.fillerBlock) {
					if (rand.nextInt(8) == 0) {
						world.setBlockState(pos, BlockRegister.SHYREGEM_ORE.getDefaultState());
					}
					else {
						world.setBlockState(pos, BlockRegister.SHYRESTONE_ORE.getDefaultState());
					}
				}
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 9; i++) {
				posX = basePos.getX() + rand.nextInt(14);
				posZ = basePos.getZ() + rand.nextInt(14);
				posY = 54;

				if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR) {
					world.setBlockState(pos.up(), BlockRegister.SHYRE_GLASS.getDefaultState());
					StructuresHandler.generateStructure("InvertedShyreStock", world, rand, pos);
				}
			}

			for (int i = 0; i < 64; i++) {
				posX = basePos.getX() + rand.nextInt(14);
				posZ = basePos.getZ() + rand.nextInt(14);
				posY = 31;

				if (world.isAirBlock(pos.setPos(posX, posY, posZ)) && world.getBlockState(pos.down()) == biome.topBlock) {
					switch (rand.nextInt(5)) {
						case 0:
							if (rand.nextInt(3) == 0)
								StructuresHandler.generateStructure("ShyreStock", world, rand, pos);
							break;
						case 1:
							world.setBlockState(pos, BlockRegister.SHYRE_WEED.getDefaultState());
							break;
						case 2:
							world.setBlockState(pos, BlockRegister.ARCBULB.getDefaultState());
							break;
						case 3:
							world.setBlockState(pos, BlockRegister.ARCFLOWER.getDefaultState());
							break;
						case 4:
							world.setBlockState(pos, BlockRegister.HORIZON_DAISIES.getDefaultState());
							break;
					}
				}
			}

			if (rand.nextInt(40) == 0) {
				posX = basePos.getX() + rand.nextInt(14);
				posZ = basePos.getZ() + rand.nextInt(14);

				if (world.isAirBlock(pos.setPos(posX, 31, posZ)) && world.getBlockState(pos.down()) == biome.topBlock)
					world.setBlockState(pos, BlockRegister.TRILLIAD_BLOOM.getDefaultState());
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < rand.nextInt(8); i++) {
				posX = basePos.getX() + rand.nextInt(12);
				posZ = basePos.getZ() + rand.nextInt(12);
				posY = 31;

				if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock && world.getBlockState(pos.setPos(posX + 1, posY, posZ + 1)).getMaterial().isReplaceable())
					new WorldGenShyreTree(null, null, rand).generate(world, rand, pos);
			}
		}
	}
}
