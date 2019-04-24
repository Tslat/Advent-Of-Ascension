package net.tslat.aoa3.dimension.crystevia.biomes;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
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

public class BiomeCrystevia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Crystevia");

	static {
		properties.setRainDisabled();
		properties.setTemperature(0.4f);
		properties.setRainfall(100);
		properties.setWaterColor(13369446);
		properties.setRainDisabled();
		properties.setBaseHeight(0.1f);
		properties.setHeightVariation(0.2f);
	}

	public BiomeCrystevia() {
		super(properties);
		setRegistryName("aoa3", "crystevia");
		this.topBlock = BlockRegister.stoneCrystevia.getDefaultState();
		this.fillerBlock = BlockRegister.stoneCrystevia.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeCrysteviaDecorator();
	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.WET);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.YELLOW.getRGB();
	}

	public class BiomeCrysteviaDecorator extends AoABiomeDecorator {
		@Override
		protected void doOreGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (ConfigurationUtil.blueCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.oreBlueCrystal.getDefaultState(), Math.max(ConfigurationUtil.blueCrystalMinOres, rand.nextInt(ConfigurationUtil.blueCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}

			if (ConfigurationUtil.greenCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.oreGreenCrystal.getDefaultState(), Math.max(ConfigurationUtil.greenCrystalMinOres, rand.nextInt(ConfigurationUtil.greenCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}

			if (ConfigurationUtil.redCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.oreRedCrystal.getDefaultState(), Math.max(ConfigurationUtil.redCrystalMinOres, rand.nextInt(ConfigurationUtil.redCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}

			if (ConfigurationUtil.purpleCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.orePurpleCrystal.getDefaultState(), Math.max(ConfigurationUtil.purpleCrystalMinOres, rand.nextInt(ConfigurationUtil.purpleCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}

			if (ConfigurationUtil.whiteCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.oreWhiteCrystal.getDefaultState(), Math.max(ConfigurationUtil.whiteCrystalMinOres, rand.nextInt(ConfigurationUtil.whiteCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}

			if (ConfigurationUtil.yellowCrystalVeinCount > 0) {
				for (int i = 0; i < 4; i++) {
					new WorldGenMinable(BlockRegister.oreYellowCrystal.getDefaultState(), Math.max(ConfigurationUtil.yellowCrystalMinOres, rand.nextInt(ConfigurationUtil.yellowCrystalMaxOres)), BlockMatcher.forBlock(BlockRegister.stoneCrystevia)).generate(world, rand, basePos.add(rand.nextInt(16), rand.nextInt(100) + 10, rand.nextInt(16)));
				}
			}
		}

		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 15; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = findNextSuitableYCoord(world, posX, 20 + rand.nextInt(80), posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					if (posY > 0) {
						switch (rand.nextInt(6)) {
							case 0:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalBlue.getDefaultState());
								break;
							case 1:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalGreen.getDefaultState());
								break;
							case 2:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalPurple.getDefaultState());
								break;
							case 3:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalRed.getDefaultState());
								break;
							case 4:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalWhite.getDefaultState());
								break;
							case 5:
								world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantCrystalYellow.getDefaultState());
								break;
						}
					}
				}
			}
		}

		@Override
		protected void doTreeGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < rand.nextInt(6); i++) {
				String colour;

				switch (rand.nextInt(6)) {
					case 0:
						colour = "Blue";
						break;
					case 1:
						colour = "Green";
						break;
					case 2:
						colour = "Purple";
						break;
					case 3:
						colour = "Red";
						break;
					case 4:
						colour = "White";
						break;
					case 5:
					default:
						colour = "Yellow";
						break;
				}

				switch (rand.nextInt(6)) {
					case 0:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX + 1, 20 + rand.nextInt(80), posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk1", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 1:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX, 20 + rand.nextInt(80), posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk2", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 2:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX + 1, 20 + rand.nextInt(80), posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk3", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 3:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX, 20 + rand.nextInt(80), posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk4", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 4:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX + 1, 20 + rand.nextInt(80), posZ + 1);

						if (world.getBlockState(pos.setPos(posX + 1, posY - 1, posZ + 1)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk5", world, rand, pos.setPos(posX, posY, posZ));
						break;
					case 5:
						posX = basePos.getX() + rand.nextInt(16);
						posZ = basePos.getZ() + rand.nextInt(16);
						posY = findNextSuitableYCoord(world, posX, 20 + rand.nextInt(80), posZ);

						if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock)
							StructuresHandler.generateStructure(colour + "CrystalChunk6", world, rand, pos.setPos(posX, posY, posZ));
						break;
				}
			}
		}

		@Override
		protected void doMiscGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			if (rand.nextInt(10) == 0) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 110 + rand.nextInt(10);

				if (world.getBlockState(pos.setPos(posX, posY, posZ)) == biome.topBlock) {
					world.setBlockState(pos.setPos(posX, posY, posZ), Blocks.WATER.getDefaultState());

					while (world.getBlockState(pos.setPos(posX, posY - 1, posZ)).getBlock() != Blocks.AIR) {
						world.setBlockState(pos.setPos(posX, posY - 1, posZ), Blocks.AIR.getDefaultState());

						posY--;
					}
				}
			}
		}

		private int findNextSuitableYCoord(World world, int posX, int posY, int posZ) {
			BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(posX, posY, posZ);

			if (world.getBlockState(testPos).getBlock() == Blocks.AIR) {
				while (world.getBlockState(testPos.move(EnumFacing.DOWN)).getBlock() == Blocks.AIR) {
					;
				}

				if (world.getBlockState(testPos).getBlock() == BlockRegister.stoneCrystevia)
					return testPos.getY() + 1;

				return 0;
			}
			else {
				return 0;
			}
		}
	}
}
