package net.tslat.aoa3.dimension.lelyetia.biomes;

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

import java.awt.*;
import java.util.Random;

public class BiomeLelyetia extends Biome {
	private static final BiomeProperties properties = new BiomeProperties("Lelyetia");

	static {
		properties.setRainDisabled();
		properties.setTemperature(2.0f);
		properties.setRainfall(500);
		properties.setRainDisabled();
		properties.setBaseHeight(0f);
		properties.setHeightVariation(0.1f);
	}

	public BiomeLelyetia() {
		super(properties);
		setRegistryName("aoa3", "lelyetia");
		this.topBlock = BlockRegister.grassLelyetia.getDefaultState();
		this.fillerBlock = BlockRegister.stoneLelyetia.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator = new BiomeLelyetiaDecorator();

	}

	public void biomeInit() {
		BiomeDictionary.addTypes(this, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.VOID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return Color.BLUE.getRGB();
	}

	public class BiomeLelyetiaDecorator extends AoABiomeDecorator {
		@Override
		protected void doPlantGen(final World world, final Biome biome, final Random rand, final BlockPos basePos, final BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
			for (int i = 0; i < 51; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 54;

				if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == BlockRegister.grassLelyetiaDown)
					world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantLelyetianGrassDown.getDefaultState());
			}

			for (int i = 0; i < 51; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY, posZ)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()) == biome.topBlock)
					world.setBlockState(pos.setPos(posX, posY, posZ), BlockRegister.plantLelyetianGrass.getDefaultState());
			}

			for (int i = 0; i < 11; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX, posZ);

				if (world.getBlockState(pos.setPos(posX, posY - 1, posZ)) == biome.topBlock) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("LelyetianWiggler", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("LelyetianStem", world, rand, pos.setPos(posX, posY, posZ));
					}
				}
			}

			for (int i = 0; i < 11; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 54;

				if (world.getBlockState(pos.setPos(posX, posY + 1, posZ)).getBlock() == BlockRegister.grassLelyetiaDown) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("InvertedLelyetianWiggler", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("InvertedLelyetianStem", world, rand, pos.setPos(posX, posY, posZ));
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

				if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()) == biome.topBlock) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("ChurryTree1", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("ChurryTree2", world, rand, pos.setPos(posX, posY, posZ));
					}
				}
			}

			for (int i = 0; i < 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = world.getHeight(posX + 3, posZ + 3);

				if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()) == biome.topBlock) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("AchonyTree1", world, rand, pos.setPos(posX, posY, posZ));
					}
					else {
						StructuresHandler.generateStructure("AchonyTree2", world, rand, pos.setPos(posX, posY, posZ));
					}
				}
			}

			for (int i = 0; i < 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 54;

				if (world.getBlockState(pos.setPos(posX + 4, posY, posZ + 4)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == BlockRegister.grassLelyetiaDown) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("InvertedChurryTree1", world, rand, pos.setPos(posX, posY - 39, posZ));
					}
					else {
						StructuresHandler.generateStructure("InvertedChurryTree2", world, rand, pos.setPos(posX, posY - 51, posZ));
					}
				}
			}

			for (int i = 0; i < 2; i++) {
				posX = basePos.getX() + rand.nextInt(16);
				posZ = basePos.getZ() + rand.nextInt(16);
				posY = 54;

				if (world.getBlockState(pos.setPos(posX + 3, posY, posZ + 3)).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == BlockRegister.grassLelyetiaDown) {
					if (rand.nextBoolean()) {
						StructuresHandler.generateStructure("InvertedAchonyTree1", world, rand, pos.setPos(posX, posY - 24, posZ));
					}
					else {
						StructuresHandler.generateStructure("InvertedAchonyTree2", world, rand, pos.setPos(posX, posY - 31, posZ));
					}
				}
			}
		}
	}
}
