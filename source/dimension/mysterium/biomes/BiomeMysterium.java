package net.tslat.aoa3.dimension.mysterium.biomes;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
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

import java.awt.*;
import java.util.Random;

public class BiomeMysterium extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Mysterium");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.4f);
		properties.setRainfall(100);
		properties.setWaterColor(new Color(88, 51, 142).getRGB());
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.22f);
	}

	public BiomeMysterium() {
		super(properties);
		setRegistryName("aoa3", "mysterium");
		this.topBlock = BlockRegister.getUnmappedBlock("mysterium_grass").getDefaultState();
		this.fillerBlock = BlockRegister.getUnmappedBlock("mysterium_dirt").getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeMysteriumDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MUSHROOM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.RED.getRGB();
	}

	public class BiomeMysteriumDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < ConfigurationUtil.OreConfig.mystite.veinsPerChunk; i++) {
				new WorldGenMinable(BlockRegister.oreMystite.getDefaultState(), Math.max(ConfigurationUtil.OreConfig.mystite.minOresPerVein, rand.nextInt(ConfigurationUtil.OreConfig.mystite.maxOresPerVein) + 1), BlockMatcher.forBlock(BlockRegister.stoneMysterium)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(20) + 2, rand.nextInt(16)));
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (world.getBlockState(basePos).getBlock() != Blocks.WATER) {
				for (int x = 0; x < 16; x++) {
					for (int z = 0; z < 16; z++) {
						if (rand.nextBoolean()) {
							posX = basePos.getX() + x;
							posZ = basePos.getZ() + z;
							posY = world.getHeight(posX, posZ);

							if (world.getBlockState(pos.setPos(posX, posY -1, posZ)) == biome.topBlock && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
								switch (rand.nextInt(5)) {
									case 0:
										world.setBlockState(pos.up(), BlockRegister.plantMysticBush.getDefaultState());
										break;
									case 1:
										world.setBlockState(pos.up(), BlockRegister.plantMysticFerns.getDefaultState());
										break;
									case 2:
										world.setBlockState(pos.up(), BlockRegister.plantRainbowGrass.getDefaultState());
										break;
									case 3:
										world.setBlockState(pos.up(), BlockRegister.plantRainbowGrass2.getDefaultState());
										break;
									case 4:
										world.setBlockState(pos.up(), BlockRegister.plantRainbowGrass3.getDefaultState());
										break;
								}
							}
						}
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				AoAStructure structure = StructuresHandler.EMPTY_STRUCTURE;

				switch (rand.nextInt(4)) {
					case 0:
						structure = StructuresHandler.getStructure("TinyBlueMushroom");
						break;
					case 1:
						structure = StructuresHandler.getStructure("TinyGreenMushroom");
						break;
					case 2:
						structure = StructuresHandler.getStructure("TinyOrangeMushroom");
						break;
					case 3:
						structure = StructuresHandler.getStructure("TinyYellowMushroom");
						break;
				}

				StructuresHandler.generateStructure(structure, world, rand, pos.setPos(posX, posY, posZ));
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 3; i++) {
				switch (rand.nextInt(6)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(4);
						posZ = basePos.getZ() + rand.nextInt(4);
						posY = world.getHeight(posX + 6, posZ + 6);

						if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("BlueMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(4);
						posZ = basePos.getZ() + rand.nextInt(4);
						posY = world.getHeight(posX + 6, posZ + 6);

						if (world.getBlockState(pos.setPos(posX + 6, posY - 1, posZ + 6)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("GreenMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(8);
						posZ = basePos.getZ() + rand.nextInt(8);
						posY = world.getHeight(posX + 4, posZ + 4);

						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("OrangeMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(8);
						posZ = basePos.getZ() + rand.nextInt(8);
						posY = world.getHeight(posX + 4, posZ + 4);

						if (world.getBlockState(pos.setPos(posX + 4, posY - 1, posZ + 4)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("PurpleMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(9);
						posZ = basePos.getZ() + rand.nextInt(9);
						posY = world.getHeight(posX + 3, posZ + 3);

						if (world.getBlockState(pos.setPos(posX + 3, posY - 1, posZ + 3)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("YellowMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(10);
						posZ = basePos.getZ() + rand.nextInt(10);
						posY = world.getHeight(posX + 2, posZ + 2);

						if (world.getBlockState(pos.setPos(posX + 2, posY - 1, posZ + 2)).getBlock() != Blocks.AIR)
							StructuresHandler.generateStructure("MiniBlueMushroomTree", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}
	}
}
